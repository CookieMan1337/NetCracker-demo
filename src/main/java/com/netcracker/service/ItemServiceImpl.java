package com.netcracker.service;

import com.netcracker.dao.ItemDAO;
import com.netcracker.dao.StorageDAO;
import com.netcracker.model.Available;
import com.netcracker.model.Item;
import com.netcracker.model.Shop;
import com.netcracker.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO;
    private StorageDAO storageDAO;

    @Autowired
    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Autowired
    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Override
    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    @Override
    public List<Item> findAllItems() {
        return itemDAO.findAllItems();
    }

    @Override
    public Item findById(Long id) {
        return itemDAO.findById(id);
    }

    @Override
    public void editItem(Item item) {
        Item entity = itemDAO.findById(item.getId());
        if (entity != null) {
            entity.setItemModel(item.getItemModel());
            entity.setName(item.getName());
            entity.setColor(item.getColor());
            entity.setItembodytype(item.getItembodytype());
            entity.setPrice(item.getPrice());
            entity.setDescription(item.getDescription());
        }
        itemDAO.editItem(entity);
    }

    @Override
    public void removeItemById(Long id) {
        itemDAO.removeItemById(id);
    }

    @Override
    public void removeItem(Item item) {
        itemDAO.removeItem(item);
    }

    @Override
    public Set<Shop> getShops(Item item) {
        List<Storage> storageList = new ArrayList<>();
        for (Available available : item.getAvailables()) {
            storageList.add(available.getPrimaryKey().getStorage());
        }
        Set<Shop> shopSet = new HashSet<>();
        for (Storage storage : storageList) {
            shopSet.addAll(storage.getShops());
        }
        return shopSet;
    }


}
