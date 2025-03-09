package edu.unisabana.dyas.samples.entities;

import java.io.Serializable;

public class ItemRentado implements Serializable {

    private Item item;
    private String fechainiciorenta;
    private String fechafinrenta;

    public ItemRentado() {
    }

    public ItemRentado(Item item, String fechainiciorenta, String fechafinrenta) {
        this.item = item;
        this.fechainiciorenta = fechainiciorenta;
        this.fechafinrenta = fechafinrenta;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getFechainiciorenta() {
        return fechainiciorenta;
    }

    public void setFechainiciorenta(String fechainiciorenta) {
        this.fechainiciorenta = fechainiciorenta;
    }

    public String getFechafinrenta() {
        return fechafinrenta;
    }

    public void setFechafinrenta(String fechafinrenta) {
        this.fechafinrenta = fechafinrenta;
    }

    @Override
    public String toString() {
        return "ItemRentado{" +
                "itemId=" + (item != null ? item.getId() : "null") +
                ", fechainiciorenta=" + fechainiciorenta +
                ", fechafinrenta=" + fechafinrenta +
                '}';
    }
}
