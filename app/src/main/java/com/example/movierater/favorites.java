package com.example.movierater;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class favorites extends AppCompatActivity {


    ArrayList<String> favMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        favMovies = new ArrayList<>();


        //for testing
        String myDataset[] = {"one","two","three","four","five","six","seven","eight","nine","ten"};
        for(int i = 0; i < 10; i++) {

            favMovies.add(myDataset[i]);

        }

        RecyclerView favoritesList = (RecyclerView)findViewById(R.id.fav_list);

        //will improve performance, not necessary
        //might not work
        favoritesList.setHasFixedSize(true);

        //specify an adapter
        RecyclerView.Adapter favAdapter = new FavoritesAdapter(this, favMovies);
        favoritesList.setAdapter(favAdapter);

        //use a linear layout manager
        //RecyclerView.LayoutManager favManager = new LinearLayoutManager(this);
        favoritesList.setLayoutManager( new LinearLayoutManager(this));

    }

}

