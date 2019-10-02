package com.example.movierater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    android.widget.Button login,sign_up;
    DatabaseReference myRef;
    private String TAG = "Damn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (android.widget.Button)findViewById(R.id.login);
        sign_up = (android.widget.Button)findViewById(R.id.sign_up);
        FirebaseApp.initializeApp(MainActivity.this);
        Toast.makeText(MainActivity.this, "FireBase connection successful", Toast.LENGTH_LONG).show();
       // user = new User();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User");

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               startActivity(new Intent(MainActivity.this, Search.class));


            }

        });

        sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                startActivity(new Intent(MainActivity.this, Registration.class));



            }

        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = "Hey";
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Make Sure google service is up to date for initialize error
        //Do not forget to refactor to solve merger error!
        //After everything, open Logcat and search for "Value is" to make sure database is reading
        //A Toast will show up and say it has been connected


    }
}
