package com.netcracker.controller;

import com.netcracker.model.Storage;
import com.netcracker.service.StorageService;
import com.netcracker.service.UserService;
import com.netcracker.validator.StorageValidator;
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
@RequestMapping(value = "/storages")
public class StorageController {
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private StorageService storageService;
    private StorageValidator storageValidator;
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "storageService")
    public void setStorageService(StorageService is) {
        this.storageService = is;
    }

    @Autowired
    @Qualifier(value = "storageValidator")
    public void setStorageValidator(StorageValidator storageValidator) {
        this.storageValidator = storageValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(storageValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
        List<Storage> storages = storageService.findAllStorages();
        model.addAttribute("storages", storages);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "storages/storagespage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String getAdd(Model model) {
        model.addAttribute("storageAttribute", new Storage());
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "storages/storageform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @Transactional
    public String updateGet(Model model) {
        return getAdd(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String add(@ModelAttribute("storageAttribute") @Valid Storage storage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("storageAttribute", storage);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "storages/storageform";
        }
        if (storage.getId() != 0)
            storageService.editStorage(storage);
        else
            storageService.addStorage(storage);
        return "redirect:/storages/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id,
                         Model model) {
        Storage storage = storageService.findById(id);
        if (storage != null)
            storageService.removeStorage(storage);
        return "redirect:/storages/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, Model model) {
        Storage storage = storageService.findById(id);
        if (storage == null) {
            return "redirect:/storages/";
        }
        model.addAttribute("storageAttribute", storage);
        model.addAttribute("edited", true);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "storages/storageform";
    }

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
