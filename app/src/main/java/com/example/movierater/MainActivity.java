package com.example.movierater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;
import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    android.widget.Button login,sign_up;
    DatabaseReference myRef;
    private String TAG = "Damn";
    String password_s;
    String email_s;
    RelativeLayout main;
    User user;
    //comment for josh, testing github
    //Comment for update to see what pull does
    //Make sure username is email
    //Complexity of Password?
    //User Login information?
    //
    // hello world
    // Weston's test comment v2
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        TypefaceProvider.registerDefaultIconSets();
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (android.widget.Button)findViewById(R.id.login);
        sign_up = (android.widget.Button)findViewById(R.id.sign_up);
        FirebaseApp.initializeApp(MainActivity.this);
        Toast.makeText(MainActivity.this, "FireBase connection successful", Toast.LENGTH_LONG).show();
        main = findViewById(R.id.main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User");

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                email_s = email.getText().toString().trim();
                password_s = password.getText().toString().trim();

                Query query =  myRef.orderByChild("email").equalTo(email_s);
                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            if (dataSnapshot.exists()) {

                                user = snapshot.getValue(User.class);

                                if(user.getPassword().equals(password_s))
                                   startActivity(new Intent(MainActivity.this, Search.class));


                            }
                        }

                                }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Snackbar snackbar = Snackbar
                        .make(main, "Invalid email and/or password", Snackbar.LENGTH_LONG);
                snackbar.show();

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
