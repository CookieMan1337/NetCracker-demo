package com.netcracker.modelDTO;

import com.netcracker.model.Available;

public class AvailableDTO {
    private long item_id;
    private long storage_id;
    private long quantity;
    private long deliverytime;
    private String action = "";

    public AvailableDTO() {
    }

    public AvailableDTO(long item_id, long storage_id, long quantity, long deliverytime) {
        this.item_id = item_id;
        this.storage_id = storage_id;
        this.quantity = quantity;
        this.deliverytime = deliverytime;
    }

    public AvailableDTO(long quantity, long deliverytime) {
        this.quantity = quantity;
        this.deliverytime = deliverytime;
    }

    public AvailableDTO(Available available) {
        this.item_id = available.getItem().getId();
        this.storage_id = available.getStorage().getId();
        this.quantity = available.getQuantity();
        this.deliverytime = available.getDeliverytime();
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public long getStorage_id() {
        return storage_id;
    }

    public void setStorage_id(long storage_id) {
        this.storage_id = storage_id;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvailableDTO)) return false;

        AvailableDTO that = (AvailableDTO) o;

        if (item_id != that.item_id) return false;
        if (storage_id != that.storage_id) return false;
        if (quantity != that.quantity) return false;
        return deliverytime == that.deliverytime;
    }

    @Override
    public int hashCode() {
        int result = (int) (item_id ^ (item_id >>> 32));
        result = 31 * result + (int) (storage_id ^ (storage_id >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (deliverytime ^ (deliverytime >>> 32));
        return result;
    }
}
