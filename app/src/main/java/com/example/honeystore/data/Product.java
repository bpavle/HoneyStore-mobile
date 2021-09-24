package com.example.honeystore.data;

import android.widget.ImageView;

import java.io.Serializable;

public class Product implements Serializable {
//how to add image here
    int id;
    String label;
    String info;
    double price;
    String currency;
    int imageResId;
    public Product(int id, String label, String info, double price, String currency, int imageResId) {
        this.id = id;
        this.label = label;
        this.info = info;
        this.price = price;
        this.currency = currency;
        this.imageResId = imageResId;
    }

    public int getImage() {
        return imageResId;
    }

    public void setImage(int imageResId) {
        this.imageResId = imageResId;
    }



    public Product(int id, String label, String info, double price, String currency) {
        this.id = id;
        this.label = label;
        this.info = info;
        this.price = price;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
