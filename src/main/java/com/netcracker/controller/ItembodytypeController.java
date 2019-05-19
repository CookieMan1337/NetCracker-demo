package com.netcracker.controller;


import com.netcracker.model.Itembodytype;
import com.netcracker.service.ItembodyService;
import com.netcracker.service.UserService;
import com.netcracker.validator.ItembodytypeValidator;
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
@RequestMapping("/bodytypes")
public class ItembodytypeController {

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private ItembodytypeValidator itembodytypeValidator;
    private ItembodyService itembodyServiceImpl;

    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "itembodytypeService")
    public void setItembodyService(ItembodyService is) {
        this.itembodyServiceImpl = is;
    }

    @Autowired
    @Qualifier(value = "itembodytypeValidator")
    public void setItembodytypeValidator(ItembodytypeValidator itembodytypeValidator) {
        this.itembodytypeValidator = itembodytypeValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(itembodytypeValidator);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
//        List<Itembodytype> itembodytypes = itembodyServiceImpl.findAllItems();
        List<Itembodytype> itembodytypes = itembodyServiceImpl.findAllItemsByName();
        model.addAttribute("itembodytypes", itembodytypes);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "bodytypes/bodytypespage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String getAdd(Model model) {
        model.addAttribute("itembodytypeAttribute", new Itembodytype());
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "bodytypes/bodytypeform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @Transactional
    public String updateGet(Model model) {
        return getAdd(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String add(@ModelAttribute("itembodytypeAttribute") @Valid Itembodytype itembodytype, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("itembodytypeAttribute", itembodytype);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "bodytypes/bodytypeform";
        }
        if (itembodytype.getId() != 0)
            itembodyServiceImpl.editItembodytype(itembodytype);
        else
            itembodyServiceImpl.addItem(itembodytype);
        return "redirect:/bodytypes/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id,
                         Model model) {
        Itembodytype itembodytype = itembodyServiceImpl.findById(id);
        if (itembodytype != null)
            itembodyServiceImpl.removeItembody(itembodytype);
        //model.addAttribute("id", id);
        return "redirect:/bodytypes/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, Model model) {
        Itembodytype item = itembodyServiceImpl.findById(id);
        if (item == null) {
            return "redirect:/bodytypes/";
        }
        model.addAttribute("itembodytypeAttribute", itembodyServiceImpl.findById(id));
        model.addAttribute("edited", true);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "bodytypes/bodytypeform";
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
