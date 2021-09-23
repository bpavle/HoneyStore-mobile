package com.example.honeystore.fakedb;

import com.example.honeystore.data.ExtendedProduct;
import com.example.honeystore.data.User;

import java.util.ArrayList;

public class Storage {
    User user;
    ArrayList<ExtendedProduct> cart;

    public Storage(User user, ArrayList<ExtendedProduct> cart) {
        this.user = user;
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<ExtendedProduct> getCart() {
        return cart;
    }

    public void setCart(ArrayList<ExtendedProduct> cart) {
        this.cart = cart;
    }
}
