package com.netcracker.controller;

import com.netcracker.model.Order;
import com.netcracker.model.Purchase;
import com.netcracker.model.PurchaseOrder;
import com.netcracker.model.User;
import com.netcracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    private AuthenticationTrustResolver authenticationTrustResolver;
    private UserService userService;
    private ItemService itemService;
    private OrderService orderService;
    private PurchaseService purchaseService;
    private PurchaseOrderService purchaseOrderService;

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
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired(required = true)
    @Qualifier(value = "itemService")
    public void setItemService(ItemService is) {
        this.itemService = is;
    }

    @Autowired(required = true)
    @Qualifier(value = "purchaseService")
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Autowired(required = true)
    @Qualifier(value = "purchaseOrderService")
    public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getlist(Model model) {
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("orderList", orderService.getAllOrdersByLogin(getPrincipal()));
        return "cart/itemlist";
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @Transactional
    public void addToCart(@RequestParam("itemId") long id, @RequestParam("quantity") int quantity) {
        Order order = orderService.getOrderByVars(getPrincipal(), id);
        if (order == null) {
            User user = userService.findByLogin(getPrincipal());
            order = new Order(user, itemService.findById(id), quantity);
            orderService.addOrder(order);
            user.addOrder(order);
            userService.updateUser(user);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Transactional
    public String delete(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderByVars(getPrincipal(), id);
        if (order != null) {
            orderService.deleteOrder(order);
        }
        return "redirect:/cart/";
    }


    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    @Transactional
    public String checkout(Model model) {
        List<Order> list = orderService.getAllOrders(userService.findByLogin(getPrincipal()));
        Purchase purchase = new Purchase();
        purchase.setUser(userService.findByLogin(getPrincipal()));
        purchase.setDate(new Date());
        purchaseService.addPurchase(purchase);
        Set<PurchaseOrder> purchaseOrders = new HashSet<>();
        for (Order order : list) {
            PurchaseOrder purchaseOrder = new PurchaseOrder(purchase, order.getItem(), order.getQuantity());
            purchaseOrderService.addOrder(purchaseOrder);
            purchaseOrders.add(purchaseOrder);
        }
        purchase.setPurchaseOrders(purchaseOrders);
        orderService.delellAllOrderPerUser(getPrincipal());
        return "cart/success";
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
