package com.example.movierater;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button login;
    Button signup;
    EditText email;
    EditText password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login      = findViewById(R.id.login);
        signup     = findViewById(R.id.signup);
        email    = findViewById(R.id.email);
        password = findViewById(R.id.password);
        FirebaseApp.initializeApp(MainActivity.this);
        Toast.makeText(MainActivity.this, "FireBase YE", Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        //Signup checks if its aval or not
        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (email.getText().toString().matches("") || email.getText().toString().matches("Email") ){
                    Toast.makeText(MainActivity.this, "Please enter an email", Toast.LENGTH_LONG).show();

                }
                else if (password.getText().toString().matches("") || password.getText().toString().matches("Password")){

                    Toast.makeText(MainActivity.this, "Password Not Found", Toast.LENGTH_LONG).show();
                }

            }
        });



        //Login checks if its aval or not
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (email.getText().toString().matches("") || email.getText().toString().matches("Email") ){
                    Toast.makeText(MainActivity.this, "Please enter an email", Toast.LENGTH_LONG).show();

                }
                else if (password.getText().toString().matches("") || password.getText().toString().matches("Password")){

                    Toast.makeText(MainActivity.this, "Password Not Found", Toast.LENGTH_LONG).show();
                }
            }
        });




        //Make Sure google service is up to date for initialize error
        //Do not forget to refactor to solve merger error!
        //After everything, open Logcat and search for "Value is" to make sure database is reading
        //A Toast will show up and say it has been connected


    }
}
