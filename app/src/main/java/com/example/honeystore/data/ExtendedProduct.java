package com.example.honeystore.data;

public class ExtendedProduct extends Product{
    int amount;

    public ExtendedProduct(int id, String label, String info, double price, String currency, int imageResId,int amount) {
        super(id, label, info, price, currency, imageResId);
        this.amount=amount;

    }

    public ExtendedProduct(int id, String label, String info, double price, String currency) {
        super(id, label, info, price, currency);
        this.amount=amount;
    }
}
