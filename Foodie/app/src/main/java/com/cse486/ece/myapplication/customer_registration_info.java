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

public class customer_registration_info extends AppCompatActivity {

    EditText cfullName, cemail, cpassword, caddress, cphone, cnid;
    Button button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration_info);

        cfullName = findViewById(R.id.customerfullname);
        cemail = findViewById(R.id.Email);
        cpassword = findViewById(R.id.pass);
        caddress = findViewById(R.id.address);
        cphone = findViewById(R.id.phone);
        cnid = findViewById(R.id.nidno);
        button= findViewById(R.id.customersignupbutton);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = cemail.getText().toString().trim();
                String password = cpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    cemail.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    cpassword.setError("password is required!");
                    return;
                }

                if(password.length()<5){

                    cpassword.setError("password must be >= 5 characters!");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(customer_registration_info.this,"User has been created", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(customer_registration_info.this, customer_registration_login.class));
                        }
                        else{
                            Toast.makeText(customer_registration_info.this, "Error " +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}