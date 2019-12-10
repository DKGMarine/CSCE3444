package  com.example.movierater;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.lang.String;

import java.util.Arrays;
import java.util.HashMap;


public class Search extends AppCompatActivity{

    HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();


    android.widget.Button search_btn;
    android.widget.Button favorites_btn;
    EditText movie;
    String title;
    Integer ID;
    int logic = 0;
    int counter = 0;
    DatabaseReference myRef;
    Movie m;
    RelativeLayout search_id;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.search);
        movie = (EditText) findViewById(R.id.movie);
        search_btn = (android.widget.Button) findViewById(R.id.search_btn);
        favorites_btn = findViewById(R.id.favorites_btn);
        FirebaseApp.initializeApp(Search.this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Movie");

        m = new Movie();
        final Levenshtein_Searc fun = new Levenshtein_Searc();
        final Query query =  myRef.orderByChild("title");
        favorites_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Search.this, favorites.class));
                //this needs to send either an arraylist of movie id's, or the user id
            }

        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = movie.getText().toString().trim();

               //.equalTo(title);
               // int tempNumber = fun.calculate("abdc", "abcdef");
                //String tempo = String.valueOf(tempNumber);
             //   Log.d("Bassam", tempo);
               query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                                m = snapshot.getValue(Movie.class); // grabbing value from database and storing into m object
                                // do something, such as display a XML saying "not foun
                                int tempNumber = fun.hammingDist(m.title, title);
                                ID = m.movie_id;
                                hmap.put(tempNumber, ID);
                                //String temp = tempNumber.toString();
                                counter++;
                                //Log.d("ex", m.title + " and " + title + " = " + Integer.toString(tempNumber));
                                if(tempNumber == 0) {
                                    logic = 1;
                                    //if(dataSnapshot.exists()) {
                                   // m = snapshot.getValue(Movie.class); // grabbing value from database and storing into m object
                                    // do something, such as display a XML saying "not foun
                                    Intent intent = new Intent(Search.this, Results.class);
                                    intent.putExtra("Movie", m);
                                    startActivity(intent);
                                    //  }
                                }


                            }

                            if(logic == 0) {
                                Intent intent2 = new Intent(Search.this, ResultsAlt.class);
                                intent2.putExtra("Hash", hmap);
                                startActivity(intent2);
                            }

                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                /*
                m.image = ("https://firebasestorage.googleapis.com/v0/b/movierater-e19d2.appspot.com/o/Interstellar.jpg?alt=media&token=f88715f2-abb3-4f26-9171-bf0c5c4db050");
                m.title = "Interstellar";
                m.duration = 169;
                m.release_year = 2014;
                m.movie_id = 1;
                m.director = "Christopher Nolan";
                m.rating = 0;
                m.google_play = true;
                m.hbo_now = false;
                m.netflix = false;
                m.youtube_tv = true;
                m.prime_video = true;
                m.hulu = false;
                myRef.push().setValue(m);

                */


            }

        });//search btn OnClickListener



}
}



class Levenshtein_Searc {
    public int calculate(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = calculate(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculate(x, y.substring(1)) + 1;
        int deletion = calculate(x.substring(1), y) + 1;

        return min(substitution, insertion, deletion);
    }

    static int hammingDist(String str1, String str2)
    {
        int i = 0, count = 0;
        str2 = str2.toLowerCase();
        str1 = str1.toLowerCase();
        while (i < str1.length() && str2.length() > i)
        {
            if (str1.charAt(i) != str2.charAt(i))
                count++;
            i++;
        }
        return count;
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    //Only Targets API and Above. Not sure how it work on lower ones.
    @TargetApi(24)
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}

