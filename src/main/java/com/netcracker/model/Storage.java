package com.netcracker.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "storages")
public class Storage implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "storage_seq")
    @SequenceGenerator(name = "storage_seq", sequenceName = "storages_id_seq")
    @NotNull
    private long id;

    @Column(name = "address")
    @Size(min = 3, message = "Length must be > 3")
    private String address;

    @Column(name = "capacity")
    private long capacity;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "primaryKey.storage", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private Set<Available> availables = new HashSet<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "storages")
    private Set<Shop> shops = new HashSet<Shop>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Shop> getShops() {
        return shops;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

    public Set<Available> getAvailables() {
        return availables;
    }

    public void setAvailables(Set<Available> availables) {
        this.availables = availables;
    }

    public void addAvailable(Available available) {
        this.availables.add(available);
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Storage)) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        if (capacity != storage.capacity) return false;
        if (!address.equals(storage.address)) return false;
        return name.equals(storage.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + address.hashCode();
        result = 31 * result + (int) (capacity ^ (capacity >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    public String getIdAsString() {
        return Long.toString(id);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

