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

public class adventureAdapter extends RecyclerView.Adapter<adventureAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movieList;
    OnMovieListenerAdventure onMovieListenerAdventure;
    public adventureAdapter(Context context, ArrayList<Movie> movieList,OnMovieListenerAdventure onMovieListenerAdventure) {
        this.context = context;
        this.movieList = movieList;
        this.onMovieListenerAdventure = onMovieListenerAdventure;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlistitem,parent,false);
        return new MovieHolder(view,onMovieListenerAdventure);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+ movie.poster_path).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        OnMovieListenerAdventure monMovieListenerAdventure;
        public MovieHolder(@NonNull View itemView,OnMovieListenerAdventure onMovieListenerAdventure) {
            super(itemView);
            image = itemView.findViewById(R.id.imageL);
            monMovieListenerAdventure = onMovieListenerAdventure;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieListenerAdventure.onMovieClickAdventure(getAdapterPosition());
        }
    }
    public interface OnMovieListenerAdventure{

        void onMovieClickAdventure(int position);
    }
}
