package com.Lab2;

import java.util.Date;

public class Item {
    private String name;
    private String manufacturer;
    private Date dateCreated;
    private float price;

    public Item(String name, String manufacturer, Date dateCreated, float price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.dateCreated = dateCreated;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", dateCreated=" + dateCreated +
                ", price=" + price +
                '}';
    }

    public void doSmth(){
        System.out.println("doing smth...");
    }
}
