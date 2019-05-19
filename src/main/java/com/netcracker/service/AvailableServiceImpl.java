package com.netcracker.service;

import com.netcracker.dao.AvailableDAO;
import com.netcracker.dao.ItemDAO;
import com.netcracker.dao.StorageDAO;
import com.netcracker.model.Available;
import com.netcracker.model.AvailableId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.util.List;

@Service
public class AvailableServiceImpl implements AvailableService {

    private AvailableDAO availableDAO;
    private ItemDAO itemDAO;
    private StorageDAO storageDAO;

    @Autowired
    public void setAvailableDAO(AvailableDAO availableDAO) {
        this.availableDAO = availableDAO;
    }

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Autowired
    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }


    @Override
    public void addAvailable(Available available) {
        availableDAO.addItem(available);
    }

    @Override
    @Transient
    public List<Available> findAllAvailables() {
        return availableDAO.findAllAvailables();
    }

    @Override
    public Available findById(AvailableId id) {
        return availableDAO.findById(id);
    }

    //TODO не работает
    @Override
    @Transactional
    public Available findByNumId(long item, long storage) {
        AvailableId availableId = new AvailableId(itemDAO.findById(item), storageDAO.findById(storage));
        return availableDAO.findById(availableId);
    }

    @Override
    public void editAvailable(Available available) {
        Available entity = availableDAO.findById(available.getPrimaryKey());
        if (entity != null) {
            entity.setQuantity(available.getQuantity());
            entity.setDeliverytime(available.getDeliverytime());
        }
        availableDAO.editAvailable(entity);
    }

    @Override
    public void removeAvailableById(AvailableId id) {
        availableDAO.removeAvailableById(id);
    }

    @Override
    public void removeAvailable(Available available) {
        availableDAO.removeAvailable(available);
    }


}
