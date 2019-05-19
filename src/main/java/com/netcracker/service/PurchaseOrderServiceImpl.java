package com.netcracker.service;

import com.netcracker.dao.PurchaseOrderDAO;
import com.netcracker.model.Purchase;
import com.netcracker.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    @Qualifier(value = "purchaseOrderDAO")
    private PurchaseOrderDAO purchaseOrderDAO;

    public void setPurchaseOrderDAO(PurchaseOrderDAO purchaseOrderDAO) {
        this.purchaseOrderDAO = purchaseOrderDAO;
    }

    @Override
    public void addOrder(PurchaseOrder order) {
        purchaseOrderDAO.addOrder(order);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderByPurchase(Purchase purchase) {
        return purchaseOrderDAO.getPurchaseOrderByPurchase(purchase);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderDAO.getPurchaseOrderById(id);
    }
}
