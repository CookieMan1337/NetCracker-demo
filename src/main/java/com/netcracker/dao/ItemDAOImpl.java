package com.netcracker.dao;

import com.netcracker.model.Item;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl extends AbstractDao<Long, Item> implements ItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItemDAOImpl.class);

    public void addItem(Item item) {
        persist(item);
        logger.info("Item added successfully, Item details=" + item);
    }

    public List<Item> findAllItems() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        List<Item> list = (List<Item>) criteria.list();
        for (Item item : list) {
            logger.info("Item list::" + item);
        }
        return list;

    }

    public Item findById(Long id) {
        logger.info("Item found successfully with id" + id);
        return getByKey(id);
    }

    public void editItem(Item item) {
        update(item);
        logger.info("Item updated successfully, Item details=" + item);
    }

    public void removeItemById(Long id) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
        Item item = (Item) criteria.uniqueResult();
        logger.info("Item deleted successfully, Item details=" + item);
        delete(item);
    }

    public void removeItem(Item item) {
        logger.info("Item deleted successfully, Item details=" + item);
        delete(item);
    }
}
