package com.netcracker.dao;

import com.netcracker.model.Available;
import com.netcracker.model.AvailableId;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvailableDAOImpl extends AbstractDao<AvailableId, Available> implements AvailableDAO {

    private static final Logger logger = LoggerFactory.getLogger(AvailableDAOImpl.class);

    @Override
    public void addItem(Available available) {
        persist(available);
        logger.info("Available entity added successfully, entity details=" + available);
    }

    @Override
    public List<Available> findAllAvailables() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        List<Available> list = (List<Available>) criteria.list();
        for (Available available : list) {
            logger.info("Available list::" + available);
        }
        return list;
    }

    @Override
    public Available findById(AvailableId id) {
        return getByKey(id);
    }

    @Override
    public void editAvailable(Available available) {
        update(available);
    }

    @Override
    public void removeAvailableById(AvailableId id) {
        Criteria criteria = createEntityCriteria().add(Restrictions.idEq(id));
        Available available = (Available) criteria.uniqueResult();
        logger.info("Available entity deleted successfully, entity details=" + available);
        delete(available);
    }

    @Override
    public void removeAvailable(Available available) {
        delete(available);
    }
}
