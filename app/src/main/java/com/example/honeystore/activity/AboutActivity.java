package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.honeystore.R;
import com.example.honeystore.fakedb.Storage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {
boolean isLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        isLoggedIn = ((Storage)this.getApplication()).isLoggedIn();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().setGroupCheckable(0, true, true);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
//                        Intent intent2 = new Intent(AboutActivity.this, AboutActivity.class);
//                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
                        Intent intent3 = new Intent(AboutActivity.this, ContactActivity.class);
                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(AboutActivity.this, ProfileActivity.class);
                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                        Intent intent0 = new Intent(AboutActivity.this,MainActivity.class);
                        intent0.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }

                return false;
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
}