package com.example.movierater;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class favorites extends AppCompatActivity {

    ArrayList<Integer> favMovieIds;
    ArrayList<Movie> favMovies;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        favMovieIds = new ArrayList<>();
        favMovies = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Movie");

        //for testing
        for(int i = 0; i < 10; i++) {

            favMovieIds.add(i+1);

        }

        for(int i = 0; i < 10; i++) {
            Movie temp = new Movie();
            temp.title = "movie " + i;
            favMovies.add(temp);
        }
        // */

        /*
        //get movies

        ////GET INFO HERE////
        for(int i = 0; i < favMovieIds.size(); i++) {

            final int ID = favMovieIds.get(i);

            Query query = myRef.orderByChild("movie_id");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (dataSnapshot.exists()) {
                            Movie tempMovie = snapshot.getValue(Movie.class);
                            if(tempMovie.movie_id == ID) {
                                favMovies.add(snapshot.getValue(Movie.class));
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        // */

        RecyclerView favoritesList;
        favoritesList = (RecyclerView)findViewById(R.id.fav_list);

        //will improve performance, not necessary
        //might not work
        //favoritesList.setHasFixedSize(true);

        //use a linear layout manager
        RecyclerView.LayoutManager favManager = new LinearLayoutManager(this);
        favoritesList.setLayoutManager( favManager);

        //specify an adapter
        RecyclerView.Adapter favAdapter = new FavoritesAdapter(this, favMovies);
        favoritesList.setAdapter(favAdapter);



    }

}

