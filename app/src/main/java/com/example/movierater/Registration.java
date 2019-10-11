package com.example.movierater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Registration extends AppCompatActivity {

    EditText email, password, password_verify;
    android.widget.Button sign_up;
    User user;
    DatabaseReference myRef;
    String email2;
    RelativeLayout regi;
    String p1 , p2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        //Variable init
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        password_verify = (EditText) findViewById(R.id.password_verify);
        sign_up = (android.widget.Button) findViewById(R.id.sign_up);

        //Init Firebase
        FirebaseApp.initializeApp(Registration.this);
        regi = findViewById(R.id.regi);
        user = new User();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User");
        sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                email2 = email.getText().toString().trim();
                p1 = password.getText().toString().trim();
                p2 = password_verify.getText().toString().trim();

                if(p1.equals(p2)){

                    Query query = myRef.orderByChild("email").equalTo(email2);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (dataSnapshot.exists()) {
                                    Snackbar snackbar = Snackbar
                                            .make(regi, "Email already in use", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    startActivity(new Intent(Registration.this, Registration.class));

                                }


                            }
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }



                if(p1.equals(p2)) {
                    user.setEmail(email.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());

                    myRef.push().setValue(user);
                    startActivity(new Intent(Registration.this, Search.class));

                }

                    Snackbar snackbar = Snackbar
                            .make(regi, "Passwords do not match", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }






        });

    }

}
