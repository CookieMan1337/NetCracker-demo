package com.netcracker.service;

import com.netcracker.dao.OrderDAO;
import com.netcracker.dao.UserDao;
import com.netcracker.model.Item;
import com.netcracker.model.Order;
import com.netcracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private UserDao userDao;


    @Autowired
    @Qualifier(value = "orderDAO")
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Autowired
    @Qualifier(value = "userDAO")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public List<Order> getAllOrders(User user) {
        return orderDAO.findOrderByUser(user);
    }

    @Override
    public List<Order> getAllOrdersByLogin(String login) {
        return orderDAO.findOrderByLogin(login);
    }

    @Override
    public List<Order> getOrderById(long id) {
        return orderDAO.findOrderByUserId(id);
    }

    @Override
    public Order getOrder(User user, Item item) {
        return orderDAO.findOrder(user, item);
    }

    @Override
    public Order getOrderByVars(String login, long item_id) {
        return orderDAO.findOrderByVars(login, item_id);
    }

    @Override
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Override
    public void delellAllOrderPerUser(String login) {
        for (Order order : orderDAO.findOrderByLogin(login)) {
            orderDAO.deleteOrder(order);
        }
    }


    @Override
    public void updateOrder(Order order) {
        Order entity = orderDAO.findOrder(order.getUser(), order.getItem());
        entity.setQuantity(order.getQuantity());
        orderDAO.updateOrder(entity);
    }


}
