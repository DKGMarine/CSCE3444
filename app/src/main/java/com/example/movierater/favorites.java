package com.example.movierater;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class favorites extends AppCompatActivity {

    ArrayList<Movie> favMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        favMovies = (ArrayList<Movie>) getIntent().getSerializableExtra("MovieList");



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

