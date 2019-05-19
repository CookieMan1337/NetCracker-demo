package com.netcracker.service;

import com.netcracker.model.Item;
import com.netcracker.model.Order;
import com.netcracker.model.User;

import java.util.List;

public interface OrderService {

    void addOrder(Order order);

    List<Order> getAllOrders(User user);

    List<Order> getAllOrdersByLogin(String login);

    List<Order> getOrderById(long id);

    Order getOrder(User user, Item item);

    Order getOrderByVars(String login, long item_id);

    void deleteOrder(Order order);

    void delellAllOrderPerUser(String login);

    void updateOrder(Order order);
    /*
        void addShop(Shop shop);
    List<Shop> findAllShops();
    Shop findById(Long id);
    void editShop(Shop shop);
    void removeShopById(Long id);
    void removeShop(Shop shop);
     */
}
