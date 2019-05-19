package com.netcracker.modelDTO;

import com.netcracker.model.Item;

public class ItemDTO {
    private long id;
    private long model;
    private String name;
    private String color;
    private long bodytype;
    private int price;
    private String description;

    public ItemDTO(long id, long model, String name, String color, long bodytype, int price, String description) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.color = color;
        this.bodytype = bodytype;
        this.price = price;
        this.description = description;
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.model = item.getItemModel().getId();
        this.name = item.getName();
        this.color = item.getColor();
        this.bodytype = item.getItembodytype().getId();
        this.price = item.getPrice();
        this.description = item.getDescription();
    }

    public ItemDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getModel() {
        return model;
    }

    public void setModel(long model) {
        this.model = model;
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

    public long getBodytype() {
        return bodytype;
    }

    public void setBodytype(long bodytype) {
        this.bodytype = bodytype;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDTO)) return false;

        ItemDTO itemDTO = (ItemDTO) o;

        if (id != itemDTO.id) return false;
        if (model != itemDTO.model) return false;
        if (bodytype != itemDTO.bodytype) return false;
        if (price != itemDTO.price) return false;
        if (!name.equals(itemDTO.name)) return false;
        if (!color.equals(itemDTO.color)) return false;
        return description != null ? description.equals(itemDTO.description) : itemDTO.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (model ^ (model >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + (int) (bodytype ^ (bodytype >>> 32));
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
