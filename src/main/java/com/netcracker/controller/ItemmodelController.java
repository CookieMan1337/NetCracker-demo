package com.netcracker.controller;


import com.netcracker.model.ItemModel;
import com.netcracker.service.ItemmodelService;
import com.netcracker.service.UserService;
import com.netcracker.validator.ItemModelValidator;
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
@RequestMapping("/itemmodels")
public class ItemmodelController {
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private ItemModelValidator itemModelValidator;
    private ItemmodelService itemmodelServiceImpl;
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "itemmodelService")
    public void setItemmodelService(ItemmodelService is) {
        this.itemmodelServiceImpl = is;
    }

    @Autowired
    @Qualifier(value = "itemModelValidator")
    public void setItemModelValidator(ItemModelValidator itemModelValidator) {
        this.itemModelValidator = itemModelValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(itemModelValidator);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
//        List<ItemModel> itemModels = itemmodelServiceImpl.findAllItems();
        List<ItemModel> itemModels = itemmodelServiceImpl.findAllItemsByName();
        model.addAttribute("itemmodels", itemModels);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "itemmodels/itemmodelspage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String getAdd(Model model) {
        model.addAttribute("itemmodelAttribute", new ItemModel());
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "itemmodels/itemform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @Transactional
    public String updateGet(Model model) {
        return getAdd(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String add(@ModelAttribute("itemmodelAttribute") @Valid ItemModel itemModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("itemmodelAttribute", itemModel);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);
            return "itemmodels/itemform";
        }
        if (itemModel.getId() != 0)
            itemmodelServiceImpl.editItemModel(itemModel);
        else
            itemmodelServiceImpl.addItem(itemModel);
        return "redirect:/itemmodels/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id,
                         Model model) {
        ItemModel itemModel = itemmodelServiceImpl.findById(id);
        if (itemModel != null)
            itemmodelServiceImpl.removeItemmodel(itemModel);
        return "redirect:/itemmodels/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, Model model) {
        ItemModel item = itemmodelServiceImpl.findById(id);
        if (item == null) {
            return "redirect:/itemmodels/";
        }
        model.addAttribute("itemmodelAttribute", itemmodelServiceImpl.findById(id));
        model.addAttribute("edited", true);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "itemmodels/itemform";
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