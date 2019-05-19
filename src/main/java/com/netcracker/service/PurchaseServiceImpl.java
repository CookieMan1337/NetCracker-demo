package com.netcracker.service;

import com.netcracker.dao.PurchaseDAO;
import com.netcracker.model.Purchase;
import com.netcracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    @Qualifier(value = "purchaseDAO")
    private PurchaseDAO purchaseDAO;

    public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
    }

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDAO.addPurchase(purchase);
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseDAO.getPurchasesByUser(user);
    }

    @Override
    @Transactional
    public List<Purchase> getPurchasesByLogin(String login) {
        return purchaseDAO.getPurchasesByLogin(login);
    }

    @Override
    @Transactional
    public Purchase getPurchaseById(Long id) {
        return purchaseDAO.getPurchaseById(id);
    }
}
