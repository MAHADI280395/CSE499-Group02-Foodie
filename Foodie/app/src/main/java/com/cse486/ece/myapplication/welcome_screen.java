package com.cse486.ece.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome_screen extends AppCompatActivity {

     private Button chefbutton;
     private Button customerbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        chefbutton = findViewById(R.id.chefbutton);
        customerbutton = findViewById(R.id.customerButton);

        chefbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome_screen.this, chef_registration_login.class);
                startActivity(intent);
            }
        });

        customerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome_screen.this, customer_registration_login.class);
                startActivity(intent);
            }
        });


    }


}