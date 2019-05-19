package com.netcracker.service;

import com.netcracker.model.Item;
import com.netcracker.model.Shop;

import java.util.List;
import java.util.Set;

public interface ItemService {
    void addItem(Item item);

    List<Item> findAllItems();

    Item findById(Long id);

    void editItem(Item item);

    void removeItemById(Long id);

    void removeItem(Item item);

    Set<Shop> getShops(Item item);

}
