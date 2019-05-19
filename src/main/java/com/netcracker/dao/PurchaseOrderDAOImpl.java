package com.netcracker.dao;

import com.netcracker.model.Purchase;
import com.netcracker.model.PurchaseOrder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class PurchaseOrderDAOImpl extends AbstractDao<Purchase, PurchaseOrder> implements PurchaseOrderDAO {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderDAOImpl.class);


    @Autowired
    @Qualifier(value = "purchaseDAO")
    private PurchaseDAO purchaseDAO;

    public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
        this.purchaseDAO = purchaseDAO;
    }

    @Override
    public void addOrder(PurchaseOrder order) {
        persist(order);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderByPurchase(Purchase purchase) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("purchase", purchase));
        List<PurchaseOrder> list = (List<PurchaseOrder>) criteria.list();
        for (PurchaseOrder purchaseOrder : list) {
            logger.info("Order list::" + purchaseOrder);
        }
        return list;
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrderById(Long id) {
        Purchase purchase = purchaseDAO.getPurchaseById(id);
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("purchase", purchase));
        List<PurchaseOrder> list = (List<PurchaseOrder>) criteria.list();
        for (PurchaseOrder purchaseOrder : list) {
            logger.info("Order list::" + purchaseOrder);
        }
        return list;
    }
}


