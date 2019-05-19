package com.netcracker.dao;

import com.netcracker.model.Purchase;
import com.netcracker.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderDAO {
    void addOrder(PurchaseOrder order);

    List<PurchaseOrder> getPurchaseOrderByPurchase(Purchase purchase);

    List<PurchaseOrder> getPurchaseOrderById(Long id);
}
