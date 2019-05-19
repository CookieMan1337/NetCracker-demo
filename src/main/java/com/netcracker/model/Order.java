package com.netcracker.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Order implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    public Order() {
    }

    public Order(User user, Item item, int quantity) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (quantity != order.quantity) return false;
        if (!user.equals(order.user)) return false;
        return item.equals(order.item);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + item.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
