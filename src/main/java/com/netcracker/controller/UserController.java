package com.netcracker.controller;

import com.netcracker.model.User;
import com.netcracker.service.UserProfileService;
import com.netcracker.service.UserService;
import com.netcracker.validator.UserValidator;
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
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private AuthenticationTrustResolver authenticationTrustResolver;
    private UserProfileService userProfileService;
    private UserValidator userValidator;
    private UserService userService;

    static private User user = new User();
    static private int ordersCount = 0;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "authenticationTrustResolver")
    public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
        this.authenticationTrustResolver = authenticationTrustResolver;
    }


    @Autowired
    @Qualifier(value = "userValidator")
    public void setShopValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }


    @Autowired
    @Qualifier(value = "userProfileService")
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String mainPage(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        model.addAttribute("loggedinuser", getPrincipal());
        ////getUserOrders(model);

        return "users/userpage";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id,
                         Model model) {
        User user = userService.findById(id);
        if (user != null)
            userService.deleteUserByLogin(user.getLogin());
        return "redirect:/users/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/users/";
        }
        model.addAttribute("userProfileList", userProfileService.findAll());
        model.addAttribute("userAttribute", user);
        model.addAttribute("loggedinuser", getPrincipal());
        ////getUserOrders(model);

        return "users/userform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String add(@ModelAttribute("userAttribute") @Valid User user, BindingResult result, Model model) {
        user.setPassword(userService.findById(user.getId()).getPassword());
        if (result.hasErrors()) {
            model.addAttribute("userProfileList", userProfileService.findAll());
            model.addAttribute("loggedinuser", getPrincipal());
            ////getUserOrders(model);

            return "users/userform";
        }
        userService.updateUser(user);
        return "redirect:/users/";
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

    /*private void getUserOrders(Model model){
        model.addAttribute("loggedinuser", getPrincipal());
        if(!getPrincipal().equals("anonymousUser"))
        {
            User user = getUser();
            if(user == null || !user.getLogin().equals(getPrincipal())) {
                setUser(userService.findByLogin(getPrincipal()));
            }
            model.addAttribute("ordersCount", getOrdersCount());
        }
    }

    static User getUser() {
        return user;
    }

    static void setUser(User user2) {
        user = user2;
        ordersCount=user.getCart().size();
    }
     static void setOrdersCount(int count){
        ordersCount = count;
     }

    static Integer getOrdersCount(){
        return ordersCount;
    }

    static User addToCart(Order order){
        user.addToCart(order);
        return user;
    }
*/
}
