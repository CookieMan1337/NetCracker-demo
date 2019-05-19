package com.netcracker.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items")
public class Item implements Serializable {
    private static final long serialVersionUID = 7440297955003302414L;


    @Id
    @SequenceGenerator(name = "items_seq", sequenceName = "items_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "items_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "model", nullable = false)
    private ItemModel itemModel;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description = "";

    @ManyToOne
    @JoinColumn(name = "bodytype", nullable = false)
    private Itembodytype itembodytype;

   /* //TODO ВЕРНУТЬ НА ALL
    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    private Set<LogShop> logShops = new HashSet<>();*/

    @OneToMany(mappedBy = "primaryKey.item", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private Set<Available> availables = new HashSet<>();


    public Item(String name, ItemModel itemModel, String color, int price, Itembodytype itembodytype, String description) {
        this.name = name;
        this.itemModel = itemModel;
        this.color = color;
        this.price = price;
        this.itembodytype = itembodytype;
        this.description = description;
    }

    public Item() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public ItemModel getItemModel() {
        return itemModel;
    }

    public void setItemModel(ItemModel itemModel) {
        this.itemModel = itemModel;
    }


    public Itembodytype getItembodytype() {
        return itembodytype;
    }

    public void setItembodytype(Itembodytype itembodytype) {
        this.itembodytype = itembodytype;
    }

/*
    public Set<LogShop> getLogShops() {
        return logShops;
    }

    public void setLogShops(Set<LogShop> logShops) {
        this.logShops = logShops;
    }*/


    public Set<Available> getAvailables() {
        return availables;
    }

    public void setAvailables(Set<Available> availables) {
        this.availables = availables;
    }

    public void addAvailable(Available available) {
        this.availables.add(available);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (price != item.price) return false;
        if (!name.equals(item.name)) return false;
        if (!itemModel.equals(item.itemModel)) return false;
        if (!color.equals(item.color)) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        return itembodytype.equals(item.itembodytype);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + itemModel.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + itembodytype.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemModel=" + itemModel.getName() +
                ", color='" + color + '\'' +
                ", itembodytype='" + itembodytype.getName() + "')";
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
