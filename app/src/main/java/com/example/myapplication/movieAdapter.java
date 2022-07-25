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

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movieList;
    private onMovieListener mOnMovieListener;
    public movieAdapter(Context context, ArrayList<Movie> movieList,onMovieListener OnMovieListener) {
        this.context = context;
        this.movieList = movieList;
        this.mOnMovieListener = OnMovieListener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carouselitem,parent,false);
        return new MovieHolder(view,mOnMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+ movie.backdrop_path).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        onMovieListener monMovieListener;
        public MovieHolder(@NonNull View itemView,onMovieListener OnMovieListener) {
            super(itemView);
            image = itemView.findViewById(R.id.imageL);
            monMovieListener = OnMovieListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            monMovieListener.onMovieClick(getAdapterPosition());
        }
    }
    public interface onMovieListener{
        void onMovieClick(int position);
    }
}
