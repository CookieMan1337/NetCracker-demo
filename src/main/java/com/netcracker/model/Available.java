package com.netcracker.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "available")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.item",
                joinColumns = @JoinColumn(name = "item_id")),
        @AssociationOverride(name = "primaryKey.storage",
                joinColumns = @JoinColumn(name = "storage_id"))})
public class Available implements Serializable {
    @EmbeddedId
    private AvailableId primaryKey = new AvailableId();

    @Column(name = "quantity")
    @NotNull
    private long quantity;

    @Column(name = "deliverytime")
    @NotNull
    private long deliverytime;

    @Transient
    public Item getItem() {
        return primaryKey.getItem();
    }

    public void setItem(Item item) {
        primaryKey.setItem(item);
    }

    @Transient
    public Storage getStorage() {
        return primaryKey.getStorage();
    }

    public void setStorage(Storage storage) {
        primaryKey.setStorage(storage);
    }

    public AvailableId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(AvailableId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(long deliverytime) {
        this.deliverytime = deliverytime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Available)) return false;

        Available available = (Available) o;

        if (quantity != available.quantity) return false;
        if (deliverytime != available.deliverytime) return false;
        return primaryKey.equals(available.primaryKey);
    }

    @Override
    public int hashCode() {
        int result = primaryKey.hashCode();
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (deliverytime ^ (deliverytime >>> 32));
        return result;
    }
}
