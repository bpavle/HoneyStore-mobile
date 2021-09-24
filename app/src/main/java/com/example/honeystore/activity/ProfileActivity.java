package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.honeystore.R;
import com.example.honeystore.data.User;
import com.example.honeystore.fakedb.Storage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    boolean isLoggedIn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        isLoggedIn = ((Storage)this.getApplication()).isLoggedIn();
        if(!((Storage)this.getApplication()).isLoggedIn()){
            Intent i = new Intent(ProfileActivity.this,LoginActivity.class);
            startActivity(i);
            return;
        }
        user = ((Storage)this.getApplication()).getUser();

      ((EditText)findViewById(R.id.Name)).setText(user.getName());
         ((EditText)findViewById(R.id.Surname)).setText(user.getSurname());
         ((EditText)findViewById(R.id.Phone)).setText(user.getPhone());
         ((EditText)findViewById(R.id.Address)).setText(user.getAddress());
         ((EditText)findViewById(R.id.Email)).setText(user.getEmail());
         ((EditText)findViewById(R.id.Password)).setText(user.getPassword());



         findViewById(R.id.changeDataButton).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (!((EditText)findViewById(R.id.Password)).getText().toString().equals(((EditText)findViewById(R.id.Confirm)).getText().toString())){
                     Toast.makeText(ProfileActivity.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
                 return;
                 }
                 user.setName(((EditText)findViewById(R.id.Name)).getText().toString());
                 user.setSurname(((EditText)findViewById(R.id.Surname)).getText().toString());
                 user.setPhone(((EditText)findViewById(R.id.Phone)).getText().toString());
                 user.setAddress(((EditText)findViewById(R.id.Address)).getText().toString());
                 user.setEmail(((EditText)findViewById(R.id.Email)).getText().toString());
                 user.setPassword(((EditText)findViewById(R.id.Password)).getText().toString());
                 Toast.makeText(ProfileActivity.this, "Changes successful", Toast.LENGTH_SHORT).show();

             }
         });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().setGroupCheckable(0, true, true);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
                        Intent intent2 = new Intent(ProfileActivity.this, AboutActivity.class);
                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
                        Intent intent3 = new Intent(ProfileActivity.this, ContactActivity.class);
                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
//                        Intent intent4 = new Intent(ProfileActivity.this, ProfileActivity.class);
//                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
//                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
//                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                       Intent intent0 = new Intent(ProfileActivity.this,MainActivity.class);
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