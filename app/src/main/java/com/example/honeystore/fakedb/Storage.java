package com.example.honeystore.fakedb;

import android.app.Application;

import com.example.honeystore.data.ExtendedProduct;
import com.example.honeystore.data.Product;
import com.example.honeystore.data.User;

import java.util.ArrayList;

/**
 * This is global storage for the application
 *
 *
 * Usage
 * // set values like so
 * ((Storage) this.getApplication()).setSomeVariable("foo");
 *
 * // get values like so
 * String s = ((MyApplication) this.getApplication()).getSomeVariable();
 */
public class Storage extends Application {
    User user = null;
    boolean isLoggedIn = false;
    ArrayList<ExtendedProduct> cart = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();

    static  public void allUsers(){
        users.add((new User(1,
                "ROLE_CUSTOMER",
                "Kupoje",
                "Kupic",
                "kupac@mail.com",
                "password",
                "82937498234",
                "Milentija Popovica 2")));
         users.add((new User(1,
                 "ROLE_CUSTOMER",
                 "Kupoje",
                 "Kupic",
                 "kupac@mail.com",
                 "password",
                 "82937498234",
                 "Milentija Popovica 2")));

     }


    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
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

    public void addToCart(ExtendedProduct ep ){
        cart.add(ep);
    }


    public void unsetCart(){
        cart.clear();
    }
    public void setCart(ArrayList<ExtendedProduct> cart) {
        this.cart = cart;
    }

    public int countProductsWithId(int id){
        int count=0;

        for (ExtendedProduct p : cart){
            if (p.getId() == id) count++;
        }
        return count;
    }
}
