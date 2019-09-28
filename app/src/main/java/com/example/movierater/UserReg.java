package com.example.movierater;

import com.google.firebase.database.*;
import android.util.Log;


public class UserReg {

    String email;
    String password;

    UserReg(String email, String password){
        this.email    = email;
        this.password = password;
    }


//    public void increaseDatabase(String email, String password){
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myref = database.getReference();
//        myref.child("users").child("awais@gmailcom").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                dataSnapshot.getRef().child("leftSpace").setValue(newValue);
//                dialog.dismiss();
//
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("User", databaseError.getMessage());
//            }
//        });
//
//
//
//    }



}
