package com.netcracker.service;

import com.netcracker.dao.StorageDAO;
import com.netcracker.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private StorageDAO storageDAO;

    @Autowired
    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Override
    public void addStorage(Storage storage) {
        storageDAO.addStorage(storage);
    }

    @Override
    public List<Storage> findAllStorages() {
        return storageDAO.findAllStorages();
    }

    @Override
    public Storage findById(Long id) {
        return storageDAO.findById(id);
    }

    @Override
    public void editStorage(Storage storage) {
        Storage entity = storageDAO.findById(storage.getId());
        if (entity != null) {
            entity.setAddress(storage.getAddress());
            entity.setCapacity(storage.getCapacity());
        }
        storageDAO.editStorage(entity);
    }

    @Override
    public void removeStorageById(Long id) {
        storageDAO.removeStorageById(id);
    }

    @Override
    public void removeStorage(Storage storage) {
        storageDAO.removeStorage(storage);
    }
}
