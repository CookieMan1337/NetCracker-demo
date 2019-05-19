package com.netcracker.service;

import com.netcracker.dao.ItemmodelDAO;
import com.netcracker.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemmodelServiceImpl implements ItemmodelService {
    private ItemmodelDAO itemmodelDAO;

    @Autowired
    public void setItemmodelDAO(ItemmodelDAO itemmodelDAO) {
        this.itemmodelDAO = itemmodelDAO;
    }

    @Override
    public void addItem(ItemModel itemModel) {
        itemmodelDAO.addItem(itemModel);
    }

    @Override
    public List<ItemModel> findAllItems() {
        return itemmodelDAO.findAllItems();
    }

    @Override
    public List<ItemModel> findAllItemsByName() {
        return itemmodelDAO.findAllItemsByName();
    }

    @Override
    public ItemModel findById(Long id) {
        return itemmodelDAO.findById(id);
    }

    @Override
    public void editItemModel(ItemModel itemModel) {
        ItemModel entity = itemmodelDAO.findById(itemModel.getId());
        if (entity != null) {
            entity.setName(itemModel.getName());
        }
        itemmodelDAO.editItemmodel(entity);
    }

    @Override
    public void removeItemModelById(Integer id) {
        itemmodelDAO.removeItemmodelById(id);
    }

    @Override
    public void removeItemmodel(ItemModel itemModel) {
        itemmodelDAO.removeItemmodel(itemModel);

    }

    @Override
    public boolean isNameUnique(String name) {
        return itemmodelDAO.findByName(name) == null;
    }
}
