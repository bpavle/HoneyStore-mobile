package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;
import static com.example.honeystore.api.FakeApi.getProducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.honeystore.R;
import com.example.honeystore.data.ExtendedProduct;
import com.example.honeystore.data.Product;
import com.example.honeystore.fakedb.Storage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ArrayList<ExtendedProduct> cart = ((Storage)this.getApplication()).getCart();
        if(!cart.isEmpty())
        packProducts(cart);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
                        Intent intent2 = new Intent(CartActivity.this, AboutActivity.class);
                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
                        Intent intent3 = new Intent(CartActivity.this, ContactActivity.class);
                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(CartActivity.this, ProfileActivity.class);
                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                       Intent intent0 = new Intent(CartActivity.this,MainActivity.class);
                        intent0.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }

                return false;
            }
        });
    }

    public void packProducts(ArrayList<ExtendedProduct> list) {
        //ArrayList<Product> list = FakeApi.getProducts();
        if (list.isEmpty()) {
            return;
        }
        LinearLayout chairpersonScrollView = (LinearLayout) findViewById(R.id.cartScrollView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (final ExtendedProduct p : list) {
            ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.cart_item_view, null);
            ConstraintLayout line = (ConstraintLayout) inflater.inflate(R.layout.line, null);
            ((ImageView) cl.findViewById(R.id.productImageView)).setImageResource(p.getImage());
            ((TextView) cl.findViewById(R.id.productLabel)).setText(p.getLabel());
            ((TextView) cl.findViewById(R.id.productPrice)).setText(Double.toString( p.getPrice()));
            ((TextView) cl.findViewById(R.id.amountEditText)).setText((Integer.toString(p.getAmount())));
            ((TextView) cl.findViewById(R.id.totalPrice)).setText((Double.toString(p.getPrice()*p.getAmount())));
            chairpersonScrollView.addView(cl);
            chairpersonScrollView.addView(line);
        }

    }
}