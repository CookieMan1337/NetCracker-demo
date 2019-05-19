package com.netcracker.dao;

import com.netcracker.model.Shop;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDAOImpl extends AbstractDao<Long, Shop> implements ShopDAO {

    private static final Logger logger = LoggerFactory.getLogger(ShopDAOImpl.class);

    @Override
    public void addShop(Shop shop) {
        persist(shop);
        logger.info("Shop added successfully, Shop details=" + shop);
    }

    @Override
    public List<Shop> findAllShops() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        List<Shop> list = (List<Shop>) criteria.list();
        for (Shop shop : list) {
            logger.info("Shop list::" + shop);
        }
        return list;
    }

    @Override
    public Shop findById(Long id) {
        logger.info("Shop found successfully with id" + id);
        return getByKey(id);
    }

    @Override
    public void editShop(Shop shop) {
        update(shop);
        logger.info("Shop updated successfully, Shop details=" + shop);
    }

    @Override
    public void removeShopById(Long id) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
        Shop shop = (Shop) criteria.uniqueResult();
        logger.info("Shop deleted successfully, Shop details=" + shop);
        delete(shop);
    }

    @Override
    public void removeShop(Shop shop) {
        logger.info("Shop deleted successfully, Shop details=" + shop);
        delete(shop);
    }
}
