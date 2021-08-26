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

public class chef_registration_info extends AppCompatActivity {

    EditText mfullName, memail, mpassword, maddress, mphone, mnid;
    Button button;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_registration_info);

        mfullName = findViewById(R.id.cheffullname);
        memail = findViewById(R.id.Email);
        mpassword = findViewById(R.id.pass);
        maddress = findViewById(R.id.address);
        mphone = findViewById(R.id.phone);
        mnid = findViewById(R.id.nidno);
        button= findViewById(R.id.chefbutton);

        mAuth = FirebaseAuth.getInstance();
        /*if(mAuth.getCurrentUser() != null){

            startActivity(new Intent(chef_registration_info.this, chef_registration_login.class));
            finish();
        }*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    memail.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){

                    mpassword.setError("password is required!");
                    return;
                }

                if(password.length()<5){

                    mpassword.setError("password must be >= 5 characters!");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(chef_registration_info.this,"User has been created", Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(chef_registration_info.this, chef_registration_login.class));
                        }
                        else{
                            Toast.makeText(chef_registration_info.this, "Error " +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}