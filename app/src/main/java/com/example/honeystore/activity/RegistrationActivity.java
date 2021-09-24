package com.example.honeystore.activity;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.honeystore.R;
import com.example.honeystore.api.FakeApi;
import com.example.honeystore.data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        findViewById(R.id.RegistrationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.Name)).getText().toString();
                String surname = ((EditText)findViewById(R.id.Surname)).getText().toString();
                String phone = ((EditText)findViewById(R.id.Phone)).getText().toString();
                String address = ((EditText)findViewById(R.id.Address)).getText().toString();
                String email = ((EditText)findViewById(R.id.Email)).getText().toString();
                String password = ((EditText)findViewById(R.id.Password)).getText().toString();
                String confirm = ((EditText)findViewById(R.id.Confirm)).getText().toString();
                System.out.println("password: "+password + " confirm: "+confirm);
                if(!password.equals(confirm)) {
                    Toast.makeText(RegistrationActivity.this, "Passwords did not match", Toast.LENGTH_LONG).show();
                    return;
                }

                User newUser = new User("ROLE_CUSTOMER",name,surname,email,password,phone,address);
                if (FakeApi.registerUser(newUser)) {
                    Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                    Toast.makeText(RegistrationActivity.this, "Registred new user", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "User with email: " + email + " already has an account!", Toast.LENGTH_LONG).show();
                //TODO: focus on email input and make it red :)
                }
            }
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);

        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_about:
                        Intent intent2 = new Intent(RegistrationActivity.this, AboutActivity.class);
                        intent2.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;
                    case R.id.ic_contact:
                        Intent intent3 = new Intent(RegistrationActivity.this, ContactActivity.class);
                        intent3.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;
                    case R.id.ic_profile:
                        Intent intent4 = new Intent(RegistrationActivity.this, ProfileActivity.class);
                        intent4.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        //intent4.setFlags(Integer.parseInt(intent4.ACTION_SEARCH));
                        startActivity(intent4);
                        break;
                    case R.id.ic_home:
                        Intent intent0 = new Intent(RegistrationActivity.this,MainActivity.class);
                        intent0.setFlags(FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent0);
                        break;

                }

                return false;
            }
        });
    }
}