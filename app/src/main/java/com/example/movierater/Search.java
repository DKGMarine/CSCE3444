package  com.example.movierater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.movierater.Movie;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;


public class Search extends AppCompatActivity{
    android.widget.Button search_btn;
    android.widget.Button favorites_btn;
    EditText movie;
    String title;
    DatabaseReference myRef;
    Movie m;
    RelativeLayout search_id;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        movie = (EditText) findViewById(R.id.movie);
        search_btn = (android.widget.Button) findViewById(R.id.search_btn);
        favorites_btn = findViewById(R.id.favorites_btn);
        FirebaseApp.initializeApp(Search.this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Movie");
        search_id = findViewById(R.id.search_id);
        m = new Movie();

        favorites_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Search.this, favorites.class));
            }

        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = movie.getText().toString().trim();

               Query query =  myRef.orderByChild("title").equalTo(title);
                Levenshtein_Searc fun = new Levenshtein_Searc();
                int tempNumber = fun.calculate("abdc", "abcdef");
                String tempo = String.valueOf(tempNumber);
                Log.d("Bassam", tempo);
               query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                if(dataSnapshot.exists()) {
                                    m = snapshot.getValue(Movie.class); // grabbing value from database and storing into m object
                                    // do something, such as display a XML saying "not foun
                                    Intent intent = new Intent(Search.this, Results.class);
                                    intent.putExtra("Movie", m);
                                    startActivity(intent);

                                }

                            }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Snackbar snackbar = Snackbar
                        .make(search_id, "Movie not found", Snackbar.LENGTH_LONG);
                snackbar.show();


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




            }

        });//search btn OnClickListener



}
}

class Levenshtein_Searc {
    static int calculate(String x, String y) {
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

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    //Only Targets API and Above. Not sure how it work on lower ones.
    @TargetApi(24)
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}

