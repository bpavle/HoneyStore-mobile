package com.example.honeystore.api;

import android.graphics.drawable.Drawable;

import com.example.honeystore.R;
import com.example.honeystore.data.Product;
import com.example.honeystore.data.User;

import java.util.ArrayList;

public class FakeApi {

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(1,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(2,"Medenjak","Najukusniji medenjak na svetu",300,"din",R.drawable.medenjaci));
        list.add(new Product(3,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(4,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(5,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(6,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(7,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(8,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(9,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        list.add(new Product(10,"Tegla","Prazna tegla za med",100,"din",R.drawable.tegla));
        return list;
    }

    public static User getUserById(int id){
        User user = new User(1,"ROLE_CUSTOMER","Pera","Peric","pera@mail.com","password","0114556","Janka Jankovica 6");
        return user;
    }
}
