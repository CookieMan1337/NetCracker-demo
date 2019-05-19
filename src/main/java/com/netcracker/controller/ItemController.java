package com.netcracker.controller;

import com.netcracker.model.Item;
import com.netcracker.model.ItemModel;
import com.netcracker.model.Itembodytype;
import com.netcracker.modelDTO.ItemDTO;
import com.netcracker.service.ItemService;
import com.netcracker.service.ItembodyService;
import com.netcracker.service.ItemmodelService;
import com.netcracker.service.UserService;
import com.netcracker.validator.ItemValidator;
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
@RequestMapping("/items")
public class ItemController {
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private ItemValidator itemValidator;

    private ItemService itemService;
    private ItembodyService itembodyService;
    private ItemmodelService itemmodelService;
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "itemService")
    public void setItemService(ItemService is) {
        this.itemService = is;
    }

    @Autowired(required = true)
    @Qualifier(value = "itembodytypeService")
    public void setItembodyService(ItembodyService is) {
        this.itembodyService = is;
    }

    @Autowired(required = true)
    @Qualifier(value = "itemmodelService")
    public void setItemmodelService(ItemmodelService is) {
        this.itemmodelService = is;
    }

    @Autowired
    @Qualifier(value = "itemValidator")
    public void setItemValidator(ItemValidator itemValidator) {
        this.itemValidator = itemValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(itemValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "items/itemspage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String getAdd(Model model) {
        /*
         List<Itembodytype> itembodytypeList = itembodyService.findAllItems();
        List<ItemModel> itemModelList = itemmodelService.findAllItems();
         */
        List<Itembodytype> itembodytypeList = itembodyService.findAllItemsByName();
        List<ItemModel> itemModelList = itemmodelService.findAllItemsByName();
        model.addAttribute("bodylist", itembodytypeList);
        model.addAttribute("modellist", itemModelList);
        model.addAttribute("itemAttribute", new ItemDTO());
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "items/itemform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @Transactional
    public String updateGet(Model model) {
        return getAdd(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String updatePost(@ModelAttribute("itemAttribute") @Valid ItemDTO itemDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            /*
            List<Itembodytype> itembodytypeList = itembodyService.findAllItems();
            List<ItemModel> itemModelList = itemmodelService.findAllItems();
            */
            List<Itembodytype> itembodytypeList = itembodyService.findAllItemsByName();
            List<ItemModel> itemModelList = itemmodelService.findAllItemsByName();
            model.addAttribute("bodylist", itembodytypeList);
            model.addAttribute("modellist", itemModelList);
            model.addAttribute("itemAttribute", itemDTO);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "items/itemform";
        }
        Item item = new Item(
                itemDTO.getName(),
                itemmodelService.findById(itemDTO.getModel()),
                itemDTO.getColor(),
                itemDTO.getPrice(),
                itembodyService.findById(itemDTO.getBodytype()),
                itemDTO.getDescription()
        );
        if (itemDTO.getId() != 0) {
            item.setId(itemDTO.getId());
            itemService.editItem(item);
        } else {
            itemService.addItem(item);
        }


        return "redirect:/items/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id,
                         Model model) {
        Item item = itemService.findById(id);
        if (item != null)
            itemService.removeItem(item);
        return "redirect:/items/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        if (item == null) {
            return "redirect:/items/";
        }
        /*
            List<Itembodytype> itembodytypeList = itembodyService.findAllItems();
            List<ItemModel> itemModelList = itemmodelService.findAllItems();
            */
        List<Itembodytype> itembodytypeList = itembodyService.findAllItemsByName();
        List<ItemModel> itemModelList = itemmodelService.findAllItemsByName();
        model.addAttribute("bodylist", itembodytypeList);
        model.addAttribute("modellist", itemModelList);
        model.addAttribute("itemAttribute", new ItemDTO(item));
        //model.addAttribute("shoplist", itemService.getShops(item));
        model.addAttribute("edited", true);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "items/itemform";
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
