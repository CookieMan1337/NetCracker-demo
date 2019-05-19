package com.netcracker.modelDTO;

import com.netcracker.model.Shop;
import com.netcracker.model.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShopDTO {
    private long id;
    private String name;
    private String address;
    private List<Long> logShops = new ArrayList<>();
    private List<Long> storages = new ArrayList<>();
    private String phone;

    public ShopDTO(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.address = shop.getAddress();
        Set<Storage> list = shop.getStorages();
        for (Storage st : list) {
            storages.add(st.getId());
        }
        this.phone = shop.getPhone();
/*        List<LogShop> list2 = (List<LogShop>) shop.getLogShops();
        for (LogShop ls:  list2) {
            logShops.add(ls.getId());
        }*/
    }

    public ShopDTO(long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public ShopDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getLogShops() {
        return logShops;
    }

    public void setLogShops(List<Long> logShops) {
        this.logShops = logShops;
    }

    public List<Long> getStorages() {
        return storages;
    }

    public void setStorages(List<Long> storages) {
        this.storages = storages;
    }

    public void addStorage(Long id) {
        storages.add(id);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopDTO)) return false;

        ShopDTO shopDTO = (ShopDTO) o;

        if (id != shopDTO.id) return false;
        if (!name.equals(shopDTO.name)) return false;
        if (!address.equals(shopDTO.address)) return false;
        return phone.equals(shopDTO.phone);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }
}
