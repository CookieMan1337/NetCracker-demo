package com.netcracker.controller;

import com.netcracker.model.User;
import com.netcracker.model.UserProfile;
import com.netcracker.service.ShopService;
import com.netcracker.service.UserProfileService;
import com.netcracker.service.UserService;
import com.netcracker.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private AuthenticationTrustResolver authenticationTrustResolver;
    private UserService userService;
    private UserProfileService userProfileService;
    private ShopService shopService;
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    private UserValidator userValidator;

    /*
        Setters and configuration
    */

    @Autowired
    @Qualifier(value = "userValidator")
    public void setShopValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
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

    @Autowired(required = true)
    @Qualifier(value = "shopService")
    public void setShopService(ShopService is) {
        this.shopService = is;
    }

    @Autowired
    @Qualifier(value = "userProfileService")
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Autowired
    @Qualifier(value = "tokenBasedService")
    public void setPersistentTokenBasedRememberMeServices(PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices) {
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    /*
        Views
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @Transactional
    public String printWelcome(Model model) {
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
  /*      User user = userService.findByLogin("Anhedonia");
        user.setPassword("Anhedonia");
        userService.updateUser(user);*/
 /*       User user2 = userService.findByLogin("moder");
        user2.setPassword("moder");
        userService.updateUser(user2);*/
        return "startPage";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @Transactional
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
//            UserController.setUser(new User());
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Transactional
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = {"/signin"}, method = RequestMethod.GET)
    @Transactional
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "registration";
    }

    @RequestMapping(value = {"/signin"}, method = RequestMethod.POST)
    @Transactional
    public String saveUser(@Valid User user, BindingResult result,
                           Model model) {
        user.getUserProfiles().add(userProfileService.findByType("USER"));
        if (result.hasErrors()) {
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);
            return "registration";
        }
        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
         * and applying it on field [sso] of Model class [User].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        userService.saveUser(user);
        model.addAttribute("success", "User " + user.getFirstname() + " " + user.getLastname() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "registrationsuccess";
    }


    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    @Transactional
    public String accessDeniedPage(Model model) {
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "accessDenied";
    }

    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @Transactional
    public String informationPage(Model model) {
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "information";
    }

    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public String storesPage(Model model) {
        model.addAttribute("shopList", shopService.findAllShops());
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "stores";
    }


    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
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
