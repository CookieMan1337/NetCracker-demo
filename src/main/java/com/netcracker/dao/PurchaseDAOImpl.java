package com.netcracker.dao;

import com.netcracker.model.Purchase;
import com.netcracker.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PurchaseDAOImpl extends AbstractDao<Integer, Purchase> implements PurchaseDAO {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseDAOImpl.class);


    @Autowired
    @Qualifier(value = "userDAO")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addPurchase(Purchase purchase) {
        persist(purchase);
    }

    @Override
    public List<Purchase> getPurchasesByUser(User user) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user));
        List<Purchase> list = (List<Purchase>) criteria.list();
        for (Purchase purchase : list) {
            Hibernate.initialize(purchase.getPurchaseOrders());
            logger.info("Purchases list::" + purchase);
        }
        return list;
    }

    @Override
    public List<Purchase> getPurchasesByLogin(String login) {
        User user = userDao.findByLogin(login);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("user", user));
        List<Purchase> list = (List<Purchase>) criteria.list();
        for (Purchase purchase : list) {
            Hibernate.initialize(purchase.getPurchaseOrders());
            logger.info("Purchases list::" + purchase);
        }
        return list;
    }

    @Override
    public Purchase getPurchaseById(Long id) {

        Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
        Purchase purchase = (Purchase) criteria.list();
        logger.info("Purchase::" + purchase);
        Hibernate.initialize(purchase.getPurchaseOrders());
        return purchase;
    }
}
