package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honeystore.R;
import com.example.honeystore.data.ExtendedProduct;
import com.example.honeystore.data.Product;
import com.example.honeystore.fakedb.Storage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {
    Product p;
    int amount=1;
    boolean isLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        isLoggedIn =((Storage)this.getApplication()).isLoggedIn();
        Intent intent = getIntent();
         p = (Product) intent.getSerializableExtra("productInfo");
        ((ImageView) findViewById(R.id.productImageView)).setImageResource(p.getImage());
        ((TextView) findViewById(R.id.productLabel)).setText(p.getLabel());
        ((TextView) findViewById(R.id.productPrice)).setText(Double.toString(p.getPrice()));
        ((TextView)findViewById(R.id.totalPrice)).setText(Double.toString(p.getPrice()));



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
                        Intent intent2 = new Intent(ProductDetailsActivity.this, AboutActivity.class);
                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
                        Intent intent3 = new Intent(ProductDetailsActivity.this, ContactActivity.class);
                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(ProductDetailsActivity.this, ProfileActivity.class);
                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                        Intent intent0 = new Intent(ProductDetailsActivity.this,MainActivity.class);
                        intent0.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }

                return false;
            }
        });

        ((EditText)findViewById(R.id.amountEditText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String amountString =((EditText)findViewById(R.id.amountEditText)).getText().toString();

                System.out.println("AMOUNT JE "+amountString);
                amount = (amountString.isEmpty() ? 0 : Integer.valueOf(amountString));

                ((TextView)findViewById(R.id.totalPrice)).setText(Double.toString(amount*p.getPrice()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                Intent i = new Intent(this,CartActivity.class);
                this.startActivity(i);
                return true;
            case R.id.logout:
                ((Storage)this.getApplication()).unsetCart();//maybe i should not empty cart
                ((Storage)this.getApplication()).setUser(null);
                ((Storage)this.getApplication()).setLoggedIn(false);
                Toast.makeText(this,"You have logged out",Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        if (isLoggedIn)
            menu.findItem(R.id.logout).setVisible(true);
        // open new activity on click
        return true;
    }

    public void addToCart(View v){
        Intent intent= getIntent();
        //Check if there are is any amount of this product in cart
        ArrayList<ExtendedProduct> cart = ((Storage)this.getApplication()).getCart();
        if(!cart.isEmpty())
        for (ExtendedProduct extendedProduct : cart) {
            if(extendedProduct.getId()==p.getId()) {
             extendedProduct.setAmount(amount+extendedProduct.getAmount());
                Toast.makeText(this,"Adding "+amount+" "+p.getLabel()+" to chart",Toast.LENGTH_LONG).show();
                return;
            };
        }
        ExtendedProduct ep = new ExtendedProduct(p,amount);
        ((Storage)this.getApplication()).addToCart(ep);
        Toast.makeText(this,"Adding "+amount+" "+p.getLabel()+" to chart",Toast.LENGTH_LONG).show();
    }
}