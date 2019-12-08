package com.example.movierater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Set;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;


import androidx.appcompat.app.AppCompatActivity;

public class ResultsAlt extends AppCompatActivity {

    // imageView = findViewById(R.id.imageView);
   // private RecyclerView recyclerView;
   // private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager layoutManager;
    RelativeLayout resultID;
    ImageView imageType;
    android.widget.Button return_search;
    String movieID;
    String url;
    DatabaseReference myRef;
    Movie m = new Movie();
    int counter = 0;



    EditText title,director, duration, rating, release, netflix, hulu, prime, hbo, youtube, google;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.results);
        resultID = findViewById(R.id.results_id);

        Snackbar snackbar = Snackbar
                .make(resultID, "Movie not found, but here is a suggestion", Snackbar.LENGTH_LONG);
        snackbar.show();
        //recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);
        imageType=findViewById(R.id.imag);
        FirebaseApp.initializeApp(ResultsAlt.this);
        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Movie");
        Intent intent = getIntent();
        HashMap<Integer, Integer> hmap = (HashMap<Integer, Integer>) intent.getSerializableExtra("Hash");
        //url = "https://firebasestorage.googleapis.com/v0/b/movierater-e19d2.appspot.com/o/Interstellar.jpg?alt=media&token=f88715f2-abb3-4f26-9171-bf0c5c4db050";
        //Glide.with(getApplicationContext()).load(url).into(imageType);
//      //mAdapter = new MyAdapter(myDataset);
        //recyclerView.setAdapter(mAdapter);
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


        TreeMap<Integer, Integer> sorted = new TreeMap<>(hmap);
        Set<Entry<Integer,Integer>> mappings = sorted.entrySet();

        final Query query =  myRef.orderByChild("movie_id");

        for(Entry<Integer, Integer> mapping : mappings){
            if(counter == 1) {
                break;
            }


            movieID = Integer.toString(mapping.getValue());


            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.d("GG", movieID);
                        Movie bassam = snapshot.getValue(Movie.class);
                        Log.d("Snapsht", Integer.toString(bassam.movie_id));
                        if (movieID.equals(Integer.toString(bassam.movie_id))) {
                            m = snapshot.getValue(Movie.class); // grabbing value from database and storing into m object

                            Log.d("gg", m.title);
                            // do something, such as display a XML saying "not foun
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
                            url = m.image;
                            //url = "https://firebasestorage.googleapis.com/v0/b/movierater-e19d2.appspot.com/o/Interstellar.jpg?alt=media&token=f88715f2-abb3-4f26-9171-bf0c5c4db050";
                            Glide.with(getApplicationContext()).load(url).into(imageType);


                        }


                    }




                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            counter++;
            if(counter == 1)
                break;
        }

        return_search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                startActivity(new Intent(ResultsAlt.this, Search.class));

            }
        });



    }

}

//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    private String[] mDataset;
//
//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public TextView textView;
//        public MyViewHolder(TextView v) {
//            super(v);
//            textView = v;
//        }
//    }
//
//    // Provide a suitable constructor (depends on the kind of dataset)
//    public MyAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }
//
//    // Create new views (invoked by the layout manager)
//    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
//                                                     int viewType) {
//        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
//        ...
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset[position]);
//
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return mDataset.length;
//    }
//}
