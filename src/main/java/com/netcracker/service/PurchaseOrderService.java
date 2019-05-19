package com.netcracker.service;

import com.netcracker.model.Purchase;
import com.netcracker.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    void addOrder(PurchaseOrder order);

    List<PurchaseOrder> getPurchaseOrderByPurchase(Purchase purchase);

    List<PurchaseOrder> getPurchaseOrderById(Long id);

}
