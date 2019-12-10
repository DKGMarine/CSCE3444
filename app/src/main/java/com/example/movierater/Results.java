package  com.example.movierater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Results extends AppCompatActivity{
    // imageView = findViewById(R.id.imageView);
    ImageView imageType;
    android.widget.Button return_search;
    android.widget.Button addFave;

    String url;

    Movie m;

    EditText title,director, duration, rating, release, netflix, hulu, prime, hbo, youtube, google;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.results);
        imageType=findViewById(R.id.imag);
        m = (Movie) getIntent().getSerializableExtra("Movie");
        url = m.image;
        //url = "https://firebasestorage.googleapis.com/v0/b/movierater-e19d2.appspot.com/o/Interstellar.jpg?alt=media&token=f88715f2-abb3-4f26-9171-bf0c5c4db050";
        Glide.with(getApplicationContext()).load(url).into(imageType);


        //image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        duration = findViewById(R.id.duration);
        director = findViewById(R.id.director);
        release = findViewById(R.id.release);
        rating = findViewById(R.id.rating);
        netflix = findViewById(R.id.netflix);
        hulu = findViewById(R.id.hulu);
        prime = findViewById(R.id.prime);
        hbo = findViewById(R.id.hbo);
        youtube = findViewById(R.id.youtube);
        google = findViewById(R.id.google);
        return_search = findViewById(R.id.return_search);
        addFave = findViewById(R.id.add_to_favorites);

        //image.setText(m.image);
        title.setText("Title - " + m.title);
        duration.setText("Length - " + m.duration + "m");
        director.setText("Director - " + m.director);
        release.setText("Release Year - " + m.release_year);
        rating.setText("User's average rating - " + m.rating);
        netflix.setText("Netflix - " + m.netflix);
        hulu.setText("Hulu - " + m.hulu);
        prime.setText("Prime Video- " + m.prime_video);
        hbo.setText("HBO Now - " + m.hbo_now);
        youtube.setText("Youtube TV - " + m.youtube_tv);
        google.setText("Google Play - " + m.google_play);





        return_search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                 startActivity(new Intent(Results.this, Search.class));

            }
        });

        addFave.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                saveFavorite();
                addFave.setEnabled(false);

            }
        });

    }//onCreate

    public void saveFavorite() {

        FileOutputStream out = null;

        try {
            out = openFileOutput("favoritesList.txt", MODE_APPEND);
            out.write(Integer.toString(m.movie_id).getBytes());
            out.write("\n".getBytes());

            Toast.makeText(this, m.title + " added to favorites list.", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Write Error: Favorites List", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }//saveFavorite

}//Results class
