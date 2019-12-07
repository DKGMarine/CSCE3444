package com.example.movierater;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {

    private ArrayList<Movie> m_fav_movies = new ArrayList<>();

    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView favMovieImage;
        public TextView favMovieTitle;
        public RelativeLayout favListItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            favMovieImage =  itemView.findViewById(R.id.fav_movie_image);
            favMovieTitle = itemView.findViewById(R.id.fav_movie_title);
            favListItem = itemView.findViewById(R.id.fav_list_item);
        }

    }//class MyViewHolder

    public FavoritesAdapter(Context context, ArrayList<Movie> fav_movies) {

        mContext = context;
        m_fav_movies = fav_movies;

    }//MyAdapter constructor

    public FavoritesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }//onCreateViewHolder

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.favMovieTitle.setText(m_fav_movies.get(position).title);
        Glide.with(mContext.getApplicationContext()).load(m_fav_movies.get(position).image).into(holder.favMovieImage);

        holder.favListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //move to the movie's page
                Toast.makeText(mContext, m_fav_movies.get(position).title, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, Results.class);
                intent.putExtra("Movie", m_fav_movies.get(position));
                mContext.startActivity(intent);
                //*/
            }
        });

    }//onBindViewHolder

    @Override
    public int getItemCount() {

        return m_fav_movies.size();

    }//getItemCount


}//class MyAdapter
