package com.Lab2;

import java.util.Date;

public class Electronics extends Item {
    private String type;
    private Date guaranteeEnd;

    public Electronics(String name, String manufacturer, Date dateCreated, float price, String type, Date guaranteeEnd) {
        super(name, manufacturer, dateCreated, price);
        this.type = type;
        this.guaranteeEnd = guaranteeEnd;
    }

    public Electronics(String name, String manufacturer, Date dateCreated, float price, String type) {
        super(name, manufacturer, dateCreated, price);
        this.type = type;
        this.guaranteeEnd = null;
    }

    public String getType() {
        return type;
    }

    public Date getGuaranteeEnd() {
        return guaranteeEnd;
    }

    public void doSmth(String s) {
        System.out.println("Doing smth better faster harder stronger");
    }
}
