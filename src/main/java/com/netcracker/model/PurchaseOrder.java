package com.netcracker.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class PurchaseOrder implements Serializable {

    @Id
    @JoinColumn(name = "purchase_id")
    @ManyToOne
    private Purchase purchase;

    @Id
    @JoinColumn(name = "item_id")
    @ManyToOne
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Purchase purchase, Item item, int quantity) {
        this.purchase = purchase;
        this.item = item;
        this.quantity = quantity;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
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
        if (!(o instanceof PurchaseOrder)) return false;

        PurchaseOrder that = (PurchaseOrder) o;

        if (quantity != that.quantity) return false;
        if (!purchase.equals(that.purchase)) return false;
        return item.equals(that.item);
    }

    @Override
    public int hashCode() {
        int result = purchase.hashCode();
        result = 31 * result + item.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
