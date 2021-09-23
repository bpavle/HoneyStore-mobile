package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.honeystore.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactActivity extends AppCompatActivity {
    private static final LatLng GREENBELT1 = new LatLng(14.553048, 121.020030);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        MapView mapView =(MapView) findViewById(R.id.mapView);

        ((MapView) findViewById(R.id.mapView)).getMapAsync(new OnMapReadyCallback() {
            @Override public void onMapReady(GoogleMap googleMap) {
               googleMap.addMarker(new MarkerOptions().position(GREENBELT1).title("Mesto").snippet("AA"));
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
                        Intent intent2 = new Intent(ContactActivity.this, AboutActivity.class);
                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
//                        Intent intent3 = new Intent(MainActivity.this, ContactActivity.class);
//                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(ContactActivity.this, ProfileActivity.class);
                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                       Intent intent0 = new Intent(ContactActivity.this,MainActivity.class);
                        intent0.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }

                return false;
            }
        });
    }
}