package com.netcracker.service;

import com.netcracker.model.ItemModel;

import java.util.List;

public interface ItemmodelService {
    void addItem(ItemModel itemModel);

    List<ItemModel> findAllItems();

    List<ItemModel> findAllItemsByName();

    ItemModel findById(Long id);

    void editItemModel(ItemModel itemModel);

    void removeItemModelById(Integer id);

    void removeItemmodel(ItemModel itemModel);

    boolean isNameUnique(String name);

}
