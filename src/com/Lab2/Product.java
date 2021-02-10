package com.Lab2;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Product extends Item {
    private Date useUntil;
    private String group;

    public Product(String name, String manufacturer, Date dateCreated, float price, Date useUntil, String group) {
        super(name, manufacturer, dateCreated, price);
        this.useUntil = useUntil;
        this.group = group;
    }

    public Date getUseUntil() {
        return useUntil;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public float getPrice() {
        if (useUntil.after(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            return super.getPrice() * 0.5f;
        }

        return super.getPrice();
    }
}
