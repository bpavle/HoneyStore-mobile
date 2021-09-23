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
import com.example.honeystore.api.FakeApi;
import com.example.honeystore.data.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packProducts(getProducts());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
                        Intent intent2 = new Intent(MainActivity.this, AboutActivity.class);
                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
                        Intent intent3 = new Intent(MainActivity.this, ContactActivity.class);
                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(MainActivity.this, ProfileActivity.class);
                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                       /* Intent intent0 = new Intent(MainActivity.this,MainActivity.class);
                        intent0.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);*/
                        break;

                }

                return false;
            }
        });
    }

    public void packProducts(ArrayList<Product> list) {
        //ArrayList<Product> list = FakeApi.getProducts();
        LinearLayout chairpersonScrollView = (LinearLayout) findViewById(R.id.productsScrollView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (final Product p : list) {
            ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.product_view, null);
            ConstraintLayout line = (ConstraintLayout) inflater.inflate(R.layout.line, null);
            ((ImageView) cl.findViewById(R.id.productImageView)).setImageResource(p.getImage());
            ((TextView) cl.findViewById(R.id.productLabel)).setText(p.getLabel());
            ((TextView) cl.findViewById(R.id.productPrice)).setText(Double.toString( p.getPrice()));
            cl.findViewById(R.id.imageViewMore).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Clicked on product with id: "+ p.getId());
                    //getSectionsByProduct(c.getProductId());
                }
            });
            chairpersonScrollView.addView(cl);
            chairpersonScrollView.addView(line);
        }

    }


}