package com.example.movierater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class favorites extends AppCompatActivity {
    RecyclerView favoritesList;
    RecyclerView.Adapter favAdapter;
    RecyclerView.LayoutManager favManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(Bundle savedInstanceState);
        setContentView(R.layout.favorites);

        favoritesList = (RecyclerView)findViewById(R.id.favList);

        //will improve performance, not necessary
        //might not work
        favoritesList.setHasFixedSize(true);

        //use a linear layout manager
        favManager = new LinearLayoutManager(this);
        favoritesList.setLayoutManager(favManager);

        //specify an adapter
        favAdapter = MyAdapter(myDataset);
        favoritesList.setAdapter(favAdapter);


    }


}

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] myDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }

    }//class MyViewHolder

    public MyAdapter(String[] myDataset) {

        myDataset = myDataset;

    }//MyAdapter constructor

    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        //add stuff here?

        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }//onCreateViewHolder

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textView.setText(myDataset[position]);

    }//onBindViewHolder

    @Override
    public int getItemCount() {

        return myDataset.length;

    }//getItemCount


}//class MyAdapter
