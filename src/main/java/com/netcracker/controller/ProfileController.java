package com.netcracker.controller;

import com.netcracker.model.Purchase;
import com.netcracker.model.User;
import com.netcracker.modelDTO.ProfileDTO;
import com.netcracker.service.PurchaseService;
import com.netcracker.service.UserProfileService;
import com.netcracker.service.UserService;
import com.netcracker.validator.ProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {
    private AuthenticationTrustResolver authenticationTrustResolver;
    private UserService userService;
    private UserProfileService userProfileService;
    private ProfileValidator profileValidator;
    private PurchaseService purchaseService;


    @Autowired(required = true)
    @Qualifier(value = "purchaseService")
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;

    }
/*  DEBUG ONLY CAN BE REMOVED
     private ItemService itemService;
    @Autowired
    @Qualifier(value = "itemService")
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }*/
    /*
        Setters and configuration
    */

    @Autowired
    @Qualifier(value = "profileValidator")
    public void setProfileValidator(ProfileValidator profileValidator) {
        this.profileValidator = profileValidator;
    }

    @Autowired
    @Qualifier(value = "authenticationTrustResolver")
    public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
        this.authenticationTrustResolver = authenticationTrustResolver;
    }

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "userProfileService")
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }


    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(profileValidator);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String mainPage(Model model) {
        User user = userService.findByLogin(getPrincipal());
        model.addAttribute("loggedinuser", user.getLogin());
        model.addAttribute("profileAttribute", new ProfileDTO(user));
        //getUserOrders(model);
        return "profile/mainPage";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @Transactional
    public String getOrders(Model model) {
        User user = userService.findByLogin(getPrincipal());
        List<Purchase> list = purchaseService.getPurchasesByLogin(getPrincipal());
        Collections.reverse(list);
        model.addAttribute("purchaseList", list);
        model.addAttribute("loggedinuser", user.getLogin());
        //getUserOrders(model);
        return "profile/orders";
    }

    @RequestMapping(value = {"/updateinfo"}, method = RequestMethod.POST)
    @Transactional
    public String updateInfo(@ModelAttribute("profileAttribute") @Valid ProfileDTO profileDTO, BindingResult result, Model model) {
        User user = userService.findByLogin(getPrincipal());
        if (result.hasErrors() || user.getId() != profileDTO.getId()) {
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "profile/mainPage";
        }
        user.setEmail(profileDTO.getEmail());
        user.setFirstname(profileDTO.getFirstname());
        user.setLastname(profileDTO.getLastname());
        userService.updateUser(user);
        model.addAttribute("profileAttribute", profileDTO);
        model.addAttribute("msg", "Info has changed successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "profile/mainPage";
    }

    @RequestMapping(value = {"/updatepass"}, method = RequestMethod.POST)
    @Transactional
    public String updatePass(@ModelAttribute("profileAttribute") @Valid ProfileDTO profileDTO, BindingResult result, Model model) {
        User user = userService.findByLogin(getPrincipal());
        if (result.hasErrors() || user.getId() != profileDTO.getId()) {
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "profile/mainPage";
        }
        user.setPassword(profileDTO.getPassword());
        userService.saveUser(user);
        profileDTO.copyProperties(user);
        model.addAttribute("profileAttribute", profileDTO);
        model.addAttribute("msg", "Password has changed successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "profile/mainPage";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
//    private void getUserOrders(Model model){
//        model.addAttribute("loggedinuser", getPrincipal());
//        if(!getPrincipal().equals("anonymousUser"))
//        {
//            User user = UserController.getUser();
//            if(user == null || !user.getLogin().equals(getPrincipal())) {
//                UserController.setUser(userService.findByLogin(getPrincipal()));
//            }
//            model.addAttribute("ordersCount", UserController.getOrdersCount());
//        }
//    }

}
