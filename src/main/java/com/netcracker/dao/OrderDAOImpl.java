package com.netcracker.dao;

import com.netcracker.model.Item;
import com.netcracker.model.Order;
import com.netcracker.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class OrderDAOImpl extends AbstractDao<User, Order> implements OrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);
    @Autowired
    @Qualifier(value = "userDAO")
    private UserDao userDao;
    @Autowired
    @Qualifier(value = "itemDAO")
    private ItemDAO itemDAO;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }


    @Override
    public void addOrder(Order order) {
        persist(order);
    }

    //Doesnt work, exception
    @Override
    public List<Order> findOrderByUserId(long id) {
        User user = userDao.findById(id);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user));
        List<Order> list = (List<Order>) criteria.list();
        for (Order order : list) {
            logger.info("Order list::" + order);
        }
        return list;
    }

    @Override
    public List<Order> findOrderByUser(User user) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user));
        List<Order> list = (List<Order>) criteria.list();
        for (Order order : list) {
            logger.info("Order list::" + order);
        }
        return list;
    }

    @Override
    public List<Order> findOrderByLogin(String login) {
        User user = userDao.findByLogin(login);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user));
        List<Order> list = (List<Order>) criteria.list();
        for (Order order : list) {
            logger.info("Order list::" + order);
        }
        return list;
    }

    @Override
    public Order findOrder(User user, Item item) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user))
                .add(Restrictions.eq("item", item));
        Order order = (Order) criteria.uniqueResult();
        logger.info("Order item::" + order);
        return order;
    }

    @Override
    public Order findOrderByVars(String login, long item_id) {
        User user = userDao.findByLogin(login);
        Item item = itemDAO.findById(item_id);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user)).add(Restrictions.eq("item", item));
        Order order = (Order) criteria.uniqueResult();
        logger.info("Order item::" + order);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        update(order);

    }

    @Override
    public void deleteOrder(Order order) {
        delete(order);
    }


}
