package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;
import static com.example.honeystore.api.FakeApi.getProducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        System.out.print(cart) ;
        if (cart.isEmpty()) {
            Toast.makeText(CartActivity.this, "Your cart is empty! ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(CartActivity.this,MainActivity.class);
            startActivity(intent);
            return;
        }
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

        LinearLayout scrollView = (LinearLayout) findViewById(R.id.cartScrollView);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        double totalCartPrice=0;
        for (final ExtendedProduct p : list) {
            final double[] totalPriceForProduct = {p.getPrice() * p.getAmount()};
            ConstraintLayout cl = (ConstraintLayout) inflater.inflate(R.layout.cart_item_view, null);
            cl.setId(p.getId());
            ConstraintLayout line = (ConstraintLayout) inflater.inflate(R.layout.line, null);
            ((ImageView) cl.findViewById(R.id.productImageView)).setImageResource(p.getImage());
            ((TextView) cl.findViewById(R.id.productLabel)).setText(p.getLabel());
            ((TextView) cl.findViewById(R.id.productPrice)).setText(Double.toString( p.getPrice()));
            ((TextView) cl.findViewById(R.id.amountEditText)).setText((Integer.toString(p.getAmount())));
            ((TextView) cl.findViewById(R.id.totalPrice)).setText((Double.toString(p.getPrice()*p.getAmount())));
//            cl.setOnClickListener(v -> {
//                Intent intent = new Intent(CartActivity.this,ProductDetailsActivity.class);
//                intent.putExtra("productInfo",p);
//                startActivity(intent);
//            });
System.out.println(cl.getChildAt(p.getId()));
            ((EditText)cl.findViewById(R.id.amountEditText)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String amountString =((EditText)findViewById(R.id.amountEditText)).getText().toString();

                    System.out.println("AMOUNT JE "+amountString);
                    int amount = (amountString.isEmpty() ? 0 : Integer.valueOf(amountString));
                    System.out.println(p.getLabel());
                    totalPriceForProduct[0] =amount*p.getPrice();
                    ((TextView)findViewById(R.id.totalPrice)).setText(Double.toString(amount*p.getPrice()));
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String amountString =((EditText)findViewById(R.id.amountEditText)).getText().toString();


                }
            });

            totalCartPrice+=totalPriceForProduct[0];
            scrollView.addView(cl);
            scrollView.addView(line);
        }

        ConstraintLayout cartBottom = (ConstraintLayout) inflater.inflate(R.layout.cart_bottom_view, null);
//        Button buttonOrder = (Button) inflater.inflate(R.id.orderButton,null);
        Button btn = new Button(this);

        btn.setText("ORDER");
        int id = 939;
        btn.setId(id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "CREATED ORDER!", Toast.LENGTH_SHORT).show();
                scrollView.removeAllViews();
            }
        });
        btn.setBackgroundColor(Color.rgb(255,193,7));


        scrollView.addView(cartBottom);
        scrollView.addView(btn);
        ((TextView)findViewById(R.id.totalCartPrice)).setText(Double.toString(totalCartPrice));

    }
}