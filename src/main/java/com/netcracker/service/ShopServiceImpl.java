package com.netcracker.service;

import com.netcracker.dao.ShopDAO;
import com.netcracker.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {


    private ShopDAO shopDAO;

    @Autowired
    public void setShopDAO(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    @Override
    public void addShop(Shop shop) {
        shopDAO.addShop(shop);
    }

    @Override
    @Transactional
    public List<Shop> findAllShops() {
        return shopDAO.findAllShops();
    }

    @Override
    @Transactional
    public Shop findById(Long id) {
        return shopDAO.findById(id);
    }

    @Override
    public void editShop(Shop shop) {
        Shop entity = shopDAO.findById(shop.getId());
        if (entity != null) {
            entity.setName(shop.getName());
            entity.setAddress(shop.getAddress());
            entity.setStorages(shop.getStorages());
            entity.setPhone(shop.getPhone());
        }
        shopDAO.editShop(entity);
    }

    @Override
    public void removeShopById(Long id) {
        shopDAO.removeShopById(id);
    }

    @Override
    public void removeShop(Shop shop) {
        shopDAO.removeShop(shop);
    }
}
