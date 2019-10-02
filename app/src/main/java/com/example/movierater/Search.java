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

import com.example.movierater.Movie;
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
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        movie = (EditText) findViewById(R.id.movie);
        search_btn = (android.widget.Button) findViewById(R.id.search_btn);
        FirebaseApp.initializeApp(Search.this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Movie");


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = movie.getText().toString().trim();

               Query query =  myRef.orderByChild("title").equalTo("Interstellar");
               query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                                m = snapshot.getValue(Movie.class); // grabbing value from database and storing into m object
                                    // do something, such as display a XML saying "not foun
                                 Intent intent = new Intent(Search.this, Results.class);
                                 intent.putExtra("Movie", m);
                                 startActivity(intent);


                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



               //Log.d("debug2", m.title);



                /*
                m.setTitle("Interstellar");
                m.setDuration(169);
                m.setRelease_year(2014);
                m.setGoogle_play(true);
                m.setHbo_now(false);
                m.setNetflix(false);
                m.setYoutube_tv(true);
                m.setSling_tv(false);
                m.setPrime_video(true);
                m.setHulu(false);

                myRef.push().setValue(m);
                */



            }

        });

}
}