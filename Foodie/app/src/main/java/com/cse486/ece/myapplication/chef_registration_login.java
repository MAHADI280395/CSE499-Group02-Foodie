package com.cse486.ece.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class chef_registration_login extends AppCompatActivity {

    EditText editText1, editText2 ;
    Button regbutton1, chefloginbutton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registration_login);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        chefloginbutton = findViewById(R.id.chefbutton);
        regbutton1 = findViewById(R.id.regbutton);

        mAuth = FirebaseAuth.getInstance();



        chefloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText1.getText().toString().trim();
                String password = editText2.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    editText1.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    editText2.setError("password is required!");
                    return;
                }

                if(password.length()<5){

                    editText2.setError("password must be >= 5 characters!");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(chef_registration_login.this, "You are logged in!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(chef_registration_login.this, chef_work_station.class));
                            finish();
                        }
                        else{
                            Toast.makeText(chef_registration_login.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        regbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chef_registration_login.this, chef_registration_info.class));
                finish();
            }
        });


    }
}