package com.netcracker.dao;

import com.netcracker.model.Storage;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StorageDAOImpl extends AbstractDao<Long, Storage> implements StorageDAO {
    private static final Logger logger = LoggerFactory.getLogger(StorageDAOImpl.class);

    @Override
    public void addStorage(Storage storage) {
        persist(storage);
    }

    @Override
    public List<Storage> findAllStorages() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        List<Storage> list = (List<Storage>) criteria.list();
        for (Storage storage : list) {
            logger.info("Storage list::" + storage);
        }
        return list;
    }

    @Override
    public Storage findById(Long id) {
        logger.info("Storage found successfully with id" + id);
        return getByKey(id);
    }

    @Override
    public void editStorage(Storage storage) {
        update(storage);
        logger.info("Storage updated successfully, Storage details=" + storage);
    }

    @Override
    public void removeStorageById(Long id) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("id", id));
        Storage storage = (Storage) criteria.uniqueResult();
        logger.info("Storage deleted successfully, Storage details=" + storage);
        delete(storage);
    }

    @Override
    public void removeStorage(Storage storage) {
        logger.info("Storage deleted successfully, Storage details=" + storage);
        delete(storage);
    }
}
