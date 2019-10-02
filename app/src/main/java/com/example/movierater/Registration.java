package com.example.movierater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    EditText email, password;
    android.widget.Button sign_up;
    User user;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sign_up = (android.widget.Button) findViewById(R.id.sign_up);
        FirebaseApp.initializeApp(Registration.this);
        //Toast.makeText(MainActivity.this, "FireBase connection successful", Toast.LENGTH_LONG).show();
        user = new User();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User");

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setEmail(email.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());

                myRef.push().setValue(user);

                Toast.makeText(Registration.this, "Inserted User Successfully", Toast.LENGTH_LONG).show();

                startActivity(new Intent(Registration.this, Search.class));
            }

        });

    }

}
