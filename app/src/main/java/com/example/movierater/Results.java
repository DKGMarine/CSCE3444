package  com.example.movierater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.movierater.Movie;

public class Results extends AppCompatActivity{

    android.widget.Button return_search;
    EditText title,director, duration, rating, release, netflix, hulu, prime, hbo, youtube, google;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Movie m = (Movie) getIntent().getSerializableExtra("Movie");


        title = (EditText) findViewById(R.id.title);
        duration = (EditText) findViewById(R.id.duration);
        director = (EditText) findViewById(R.id.director);
        release = (EditText) findViewById(R.id.release);
        rating = (EditText) findViewById(R.id.rating);
        netflix = (EditText) findViewById(R.id.netflix);
        hulu = (EditText) findViewById(R.id.hulu);
        prime = (EditText) findViewById(R.id.prime);
        hbo = (EditText) findViewById(R.id.hbo);
        youtube = (EditText) findViewById(R.id.youtube);
        google = (EditText) findViewById(R.id.google);
        return_search = (android.widget.Button) findViewById(R.id.return_search);


        title.setText("Title - " + m.title);
        duration.setText("Length - " + Integer.toString(m.duration) + "m");
        director.setText("Director - " + m.director);
        release.setText("Release Year - " + Integer.toString(m.release_year));
        rating.setText("User's average rating - " + Float.toString(m.rating));
        netflix.setText("Netflix - " + Boolean.toString(m.netflix));
        hulu.setText("Hulu - " + Boolean.toString(m.hulu));
        prime.setText("Prime Video- " + Boolean.toString(m.prime_video));
        hbo.setText("HBO Now - " + Boolean.toString(m.hbo_now));
        youtube.setText("Youtube TV - " + Boolean.toString(m.youtube_tv));
        google.setText("Google Play - " + Boolean.toString(m.google_play));


        return_search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                 startActivity(new Intent(Results.this, Search.class));

            }
        });


    }






}
