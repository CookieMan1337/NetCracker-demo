package com.netcracker.dao;

import com.netcracker.model.ItemModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemmodelDAOImpl extends AbstractDao<Long, ItemModel> implements ItemmodelDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItemmodelDAOImpl.class);

    @Override
    public void addItem(ItemModel itemmodel) {
        logger.info("Item model added successfully, Item details=" + itemmodel);
        persist(itemmodel);
    }

    @Override
    public List<ItemModel> findAllItems() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        List<ItemModel> list = (List<ItemModel>) criteria.list();
        for (ItemModel item : list) {
            logger.info("Item bodytype list::" + item);
        }
        return list;
    }

    @Override
    public List<ItemModel> findAllItemsByName() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        List<ItemModel> list = (List<ItemModel>) criteria.list();
        for (ItemModel item : list) {
            logger.info("Item bodytype list::" + item);
        }
        return list;
    }

    @Override
    public ItemModel findById(Long id) {
        logger.info("Item model found successfully with id" + id);
        return getByKey(id);
    }

    @Override
    public ItemModel findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("name", name).ignoreCase());
        ItemModel itemModel = (ItemModel) criteria.uniqueResult();
        logger.info("Item model found successfully with name=" + name);
        return itemModel;
    }

    @Override
    public void editItemmodel(ItemModel itemmodel) {
        update(itemmodel);
        logger.info("Item model updated successfully, Item details=" + itemmodel);
    }

    @Override
    public void removeItemmodelById(Integer id) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
        ItemModel itemmodel = (ItemModel) criteria.uniqueResult();
        logger.info("Item model deleted successfully, Item details=" + itemmodel);
        delete(itemmodel);
    }

    @Override
    public void removeItemmodel(ItemModel itemmodel) {
        logger.info("Item model deleted successfully, Item details=" + itemmodel);
        delete(itemmodel);

    }
}
