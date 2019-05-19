package com.netcracker.model;

import com.netcracker.modelDTO.ShopDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop implements Serializable {
    @Id
    @SequenceGenerator(name = "shops_seq", sequenceName = "shops_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "shops_seq")
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "phone")
    @NotEmpty
    private String phone;

/*    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<LogShop> logShops = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "shop_storage",
            joinColumns = @JoinColumn(name = "id_shop", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_storage", nullable = false)
    )
    private Set<Storage> storages = new HashSet<>();

    public Shop(ShopDTO shopDTO) {
        this.name = shopDTO.getName();
        this.address = shopDTO.getAddress();
        this.phone = shopDTO.getPhone();
    }

    public Shop(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Shop() {
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


    public Set<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Set<Storage> storages) {
        this.storages = storages;
    }

    public void addStorage(Storage storage) {
        storages.add(storage);
    }
/*
    public Set<LogShop> getLogShops() {
        return logShops;
    }

    public void setLogShops(Set<LogShop> logShops) {
        this.logShops = logShops;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;

        Shop shop = (Shop) o;

        if (id != shop.id) return false;
        if (!name.equals(shop.name)) return false;
        if (!address.equals(shop.address)) return false;
        return phone.equals(shop.phone);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
