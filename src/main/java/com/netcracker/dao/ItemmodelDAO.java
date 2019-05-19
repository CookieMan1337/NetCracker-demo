package com.netcracker.dao;

import com.netcracker.model.ItemModel;

import java.util.List;

public interface ItemmodelDAO {
    void addItem(ItemModel itemmodel);

    List<ItemModel> findAllItems();

    List<ItemModel> findAllItemsByName();

    ItemModel findById(Long id);

    ItemModel findByName(String name);

    void editItemmodel(ItemModel itemmodel);

    void removeItemmodelById(Integer id);

    void removeItemmodel(ItemModel itemmodel);
}
