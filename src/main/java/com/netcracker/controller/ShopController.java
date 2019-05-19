package com.netcracker.controller;

import com.netcracker.model.Shop;
import com.netcracker.model.Storage;
import com.netcracker.modelDTO.ShopDTO;
import com.netcracker.service.ShopService;
import com.netcracker.service.StorageService;
import com.netcracker.service.UserService;
import com.netcracker.validator.ShopValidator;
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
@RequestMapping(value = "/shops")
public class ShopController {
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    private ShopService shopService;
    private StorageService storageService;
    private ShopValidator shopValidator;

    private UserService userService;

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

    @Autowired(required = true)
    @Qualifier(value = "storageService")
    public void setStorageService(StorageService is) {
        this.storageService = is;
    }

    @Autowired
    @Qualifier(value = "shopValidator")
    public void setShopValidator(ShopValidator shopValidator) {
        this.shopValidator = shopValidator;
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(shopValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
        List<Shop> shops = shopService.findAllShops();
        model.addAttribute("shops", shops);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "shops/shopspage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional
    public String getAdd(Model model) {
        model.addAttribute("shopAttribute", new ShopDTO());

        List<Storage> storageList = storageService.findAllStorages();
        model.addAttribute("storageList", storageList);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "shops/shopform";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public String add(@ModelAttribute("shopAttribute") @Valid ShopDTO shopDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(shopDTO);
            List<Storage> storageList = storageService.findAllStorages();
            model.addAttribute("storageList", storageList);
            model.addAttribute("loggedinuser", getPrincipal());
            //getUserOrders(model);

            return "shops/shopform";
        }
        Shop shop = new Shop(shopDTO);
        for (Long id : shopDTO.getStorages()) {
            Storage storage = storageService.findById(id);
            shop.addStorage(storage);
        }
        /*for (Long id:shopDTO.getLogShops()) {
            Storage storage = logshopService.findById(id);
            shop.addStorage(storage);
            logger.warn("Shop storages id: " + id+", storage is "+shop.getStorages().size());
        }*/
        if (shopDTO.getId() != 0) {
            shop.setId(shopDTO.getId());
            shopService.editShop(shop);
        } else
            shopService.addShop(shop);
        return "redirect:/shops/";
    }

    /*@RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public String add(@ModelAttribute("shopAttribute") Shop shop) {
        shopService.addShop(shop);
        return "redirect:/shops/";
    }*/

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id, Model model) {
        Shop shop = shopService.findById(id);
        if (shop != null)
            shopService.removeShop(shop);
        return "redirect:/shops/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @Transactional
    public String getEdit(@PathVariable Long id, Model model) {
        Shop shop = shopService.findById(id);
        /*if(shop==null)
        {
            return "redirect:/shops/";
        }*/
        ShopDTO shopDTO = new ShopDTO(shop);
        model.addAttribute("shopAttribute", shopDTO);
        List<Storage> storageList = storageService.findAllStorages();
        model.addAttribute("storageList", storageList);
        model.addAttribute("edited", true);
        model.addAttribute("loggedinuser", getPrincipal());
        //getUserOrders(model);

        return "shops/shopform";
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
