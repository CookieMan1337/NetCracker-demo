package com.netcracker.service;

import com.netcracker.model.Purchase;
import com.netcracker.model.User;

import java.util.List;

public interface PurchaseService {
    void addPurchase(Purchase purchase);

    List<Purchase> getPurchasesByUser(User user);

    List<Purchase> getPurchasesByLogin(String login);

    Purchase getPurchaseById(Long id);
}
