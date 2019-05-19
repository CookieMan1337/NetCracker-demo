package com.netcracker.dao;

import com.netcracker.model.Purchase;
import com.netcracker.model.User;

import java.util.List;

public interface PurchaseDAO {
    void addPurchase(Purchase purchase);

    List<Purchase> getPurchasesByUser(User user);

    List<Purchase> getPurchasesByLogin(String login);

    Purchase getPurchaseById(Long id);
}
