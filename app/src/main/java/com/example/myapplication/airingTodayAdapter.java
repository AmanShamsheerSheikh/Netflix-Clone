package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class airingTodayAdapter extends RecyclerView.Adapter<airingTodayAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> tvList;
    private OnTvListener monTvListener;
    public airingTodayAdapter(Context context, ArrayList<Movie> movieList,OnTvListener onTvListener) {
        this.context = context;
        this.tvList = movieList;
        this.monTvListener = onTvListener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlistitem,parent,false);
        return new MovieHolder(view,monTvListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = tvList.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+ movie.poster_path).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        OnTvListener monTvListener;
        public MovieHolder(@NonNull View itemView,OnTvListener onTvListener) {
            super(itemView);
            image = itemView.findViewById(R.id.imageL);
            monTvListener = onTvListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            monTvListener.onTvClick(getAdapterPosition());
        }
    }
    public interface OnTvListener{
        void onTvClick(int position) ;
    }
}
