package com.netcracker.dao;

import com.netcracker.model.Item;
import com.netcracker.model.Order;
import com.netcracker.model.User;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);

    List<Order> findOrderByUserId(long id);

    List<Order> findOrderByUser(User user);

    List<Order> findOrderByLogin(String login);

    Order findOrder(User user, Item item);

    Order findOrderByVars(String login, long item_id);

    void updateOrder(Order order);

    void deleteOrder(Order order);
}
