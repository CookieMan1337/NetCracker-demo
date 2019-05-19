package com.netcracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "itemmodels")
public class ItemModel implements Serializable {
    @Id
    @SequenceGenerator(name = "itemmodel_seq", sequenceName = "itemmodels_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "itemmodel_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "itemModel", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();


    public Set<Item> getItems() {
        return items;
    }


    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public ItemModel(String name) {
        this.name = name;
    }

    public ItemModel() {
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

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemModel)) return false;

        ItemModel itemModel = (ItemModel) o;

        if (id != itemModel.id) return false;
        return name.equals(itemModel.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ItemModel(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ')';
    }
}
