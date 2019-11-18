package com.example.movierater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {
    private String[] myDataset;

    private ArrayList<String> m_fav_movie_titles = new ArrayList<>();
    //also need to store info for images

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

    public FavoritesAdapter(Context context, ArrayList<String> fav_movie_titles) {

        mContext = context;
        m_fav_movie_titles = fav_movie_titles;

    }//MyAdapter constructor

    public FavoritesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }//onCreateViewHolder

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.favMovieTitle.setText(m_fav_movie_titles.get(position));
        //holder.favMovieImage.somehow get an image into here

        holder.favListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, m_fav_movie_titles.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }//onBindViewHolder

    @Override
    public int getItemCount() {

        return m_fav_movie_titles.size();

    }//getItemCount


}//class MyAdapter
