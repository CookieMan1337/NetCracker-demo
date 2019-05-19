package com.netcracker.dao;

import com.netcracker.model.Shop;

import java.util.List;

public interface ShopDAO {
    void addShop(Shop shop);

    List<Shop> findAllShops();

    Shop findById(Long id);

    void editShop(Shop shop);

    void removeShopById(Long id);

    void removeShop(Shop shop);

}
