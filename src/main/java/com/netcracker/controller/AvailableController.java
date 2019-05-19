package com.netcracker.controller;

import com.netcracker.model.Available;
import com.netcracker.model.AvailableId;
import com.netcracker.model.Item;
import com.netcracker.model.Storage;
import com.netcracker.modelDTO.AvailableDTO;
import com.netcracker.service.AvailableService;
import com.netcracker.service.ItemService;
import com.netcracker.service.StorageService;
import com.netcracker.service.UserService;
import com.netcracker.validator.AvailableValidator;
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
@RequestMapping(value = "/available")
public class AvailableController {
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private AvailableService availableService;
    private ItemService itemService;
    private StorageService storageService;

    private AvailableValidator availableValidator;
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "availableService")
    public void setAvailableService(AvailableService availableService) {
        this.availableService = availableService;
    }

    @Autowired(required = true)
    @Qualifier(value = "itemService")
    public void setItemService(ItemService is) {
        this.itemService = is;
    }

    @Autowired(required = true)
    @Qualifier(value = "storageService")
    public void setStorageService(StorageService is) {
        this.storageService = is;
    }

    @Autowired
    @Qualifier(value = "availableValidator")
    public void setAvailableValidator(AvailableValidator availableValidator) {
        this.availableValidator = availableValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(availableValidator);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
        List<Available> list = availableService.findAllAvailables();
        model.addAttribute("availables", list);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "available/availablepage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String getAdd(Model model) {
        model.addAttribute("itemlist", itemService.findAllItems());
        model.addAttribute("storagelist", storageService.findAllStorages());
        AvailableDTO availableDTO = new AvailableDTO();
        availableDTO.setAction("add");
        model.addAttribute("availableAttribute", availableDTO);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);
        return "available/availableform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @Transactional
    public String updateGet(Model model) {
        return getAdd(model);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String updatePost(@ModelAttribute("availableAttribute") @Valid AvailableDTO availableDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("itemlist", itemService.findAllItems());
            model.addAttribute("storagelist", storageService.findAllStorages());
            model.addAttribute("availableAttribute", availableDTO);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "available/availableform";
        }
        Item item = itemService.findById(availableDTO.getItem_id());
        Storage storage = storageService.findById(availableDTO.getStorage_id());
        Available available = null;
        if (item != null && storage != null) {
            available = availableService.findById(new AvailableId(item, storage));
            if (available != null) {
                available.setQuantity(availableDTO.getQuantity());
                available.setDeliverytime(availableDTO.getDeliverytime());
                availableService.editAvailable(available);
            } else {
                available = new Available();
                available.setPrimaryKey(new AvailableId(item, storage));
                available.setQuantity(availableDTO.getQuantity());
                available.setDeliverytime(availableDTO.getDeliverytime());
                availableService.addAvailable(available);
            }
        }
        return "redirect:/available/";
    }

    @RequestMapping(value = "/delete/{id}/{id2}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id, @PathVariable Long id2,
                         Model model) {
        Item item = itemService.findById(id);
        Storage storage = storageService.findById(id2);
        if (item != null && storage != null) {
            AvailableId avId = new AvailableId(item, storage);
            availableService.removeAvailableById(avId);
        }
        return "redirect:/available/";
    }

    @RequestMapping(value = "/edit/{id}/{id2}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, @PathVariable Long id2, Model model) {
        Item item = itemService.findById(id);
        Storage storage = storageService.findById(id2);
        Available available = null;
        model.addAttribute("loggedinuser", getPrincipal());
        if (item != null && storage != null) {
            available = availableService.findById(new AvailableId(item, storage));
        }
        if (available == null) {
            return "redirect:/available/";
        } else {
            List<Item> itemlist = itemService.findAllItems();
            List<Storage> storageList = storageService.findAllStorages();
            model.addAttribute("itemlist", itemlist);
            model.addAttribute("storagelist", storageList);
            AvailableDTO availableDTO = new AvailableDTO();
            availableDTO.setAction("edit");
            model.addAttribute("availableAttribute", availableDTO);
            model.addAttribute("edited", true);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);
            return "available/availableform";
        }
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
