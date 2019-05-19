package com.netcracker.dao;

import com.netcracker.model.Item;

import java.util.List;

public interface ItemDAO {
    void addItem(Item item);

    List<Item> findAllItems();

    Item findById(Long id);

    void editItem(Item item);

    void removeItemById(Long id);

    void removeItem(Item item);
}
