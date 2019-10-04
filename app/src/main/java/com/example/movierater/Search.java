package  com.example.movierater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class Search extends AppCompatActivity{
    android.widget.Button search_btn;
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
        FirebaseApp.initializeApp(Search.this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Movie");
        search_id = findViewById(R.id.search_id);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = movie.getText().toString().trim();




               Query query =  myRef.orderByChild("title").equalTo(title);
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

               //Log.d("debug2", m.title);

                /*
                m.title = "Interstellar";
                m.duration = 169;
                m.release_year = 2014;
                m.google_play = true;
                m.hbo_now = false;
                m.netflix = false;
                m.youtube_tv = true;
                m.sling_tv = false;
                m.prime_video = true;
                m.hulu = false;
                myRef.push().setValue(m);
                */



            }

        });

}
}