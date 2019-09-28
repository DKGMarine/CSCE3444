package com.example.movierater;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Damn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        Toast.makeText(MainActivity.this, "FireBase YE", Toast.LENGTH_LONG).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.setValue("Karla 2");
        //Hey
        //new comment
        //josh's comment
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
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
