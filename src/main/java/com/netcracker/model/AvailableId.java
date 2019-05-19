package com.netcracker.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class AvailableId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    public AvailableId() {
    }

    public AvailableId(Item item, Storage storage) {
        this.item = item;
        this.storage = storage;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailableId)) return false;

        AvailableId that = (AvailableId) o;

        if (!item.equals(that.item)) return false;
        return storage.equals(that.storage);
    }

    @Override
    public int hashCode() {
        int result = item.hashCode();
        result = 31 * result + storage.hashCode();
        return result;
    }
}
