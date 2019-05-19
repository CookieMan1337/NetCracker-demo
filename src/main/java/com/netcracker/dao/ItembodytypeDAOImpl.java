package com.netcracker.dao;

import com.netcracker.model.Itembodytype;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItembodytypeDAOImpl extends AbstractDao<Long, Itembodytype> implements ItembodytypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(ItembodytypeDAOImpl.class);

    @Override
    public void addItem(Itembodytype itembodytype) {
        persist(itembodytype);
        logger.info("Item bodytype added successfully, Item bodytype details=" + itembodytype);
    }

    @Override
    public List<Itembodytype> findAllItems() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        List<Itembodytype> list = (List<Itembodytype>) criteria.list();
        for (Itembodytype item : list) {
            logger.info("Item bodytype list::" + item);
        }
        return list;
    }

    @Override
    public List<Itembodytype> findAllItemsByName() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        List<Itembodytype> list = (List<Itembodytype>) criteria.list();
        for (Itembodytype item : list) {
            logger.info("Item bodytype list::" + item);
        }
        return list;
    }

    @Override
    public Itembodytype findById(Long id) {
        logger.info("Item bodytype found successfully with id=" + id);
        return getByKey(id);
    }

    @Override
    public Itembodytype findByName(String name) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("name", name).ignoreCase());
        Itembodytype itembodytype = (Itembodytype) criteria.uniqueResult();
        logger.info("Item bodytype found successfully with name=" + name);
        return itembodytype;
    }

    @Override
    public void editItembodytype(Itembodytype itembodytype) {
        update(itembodytype);
        logger.info("Item bodytype updated successfully, Item bodytype details=" + itembodytype);
    }

    @Override
    public void removeItembodytypeById(Integer id) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
        Itembodytype itembodytype = (Itembodytype) criteria.uniqueResult();
        logger.info("Item bodytype deleted successfully, Item details=" + itembodytype);
        delete(itembodytype);
    }

    @Override
    public void removeItembody(Itembodytype itembodytype) {
        delete(itembodytype);
        logger.info("Item bodytype deleted successfully, Item details=" + itembodytype);
    }
}
