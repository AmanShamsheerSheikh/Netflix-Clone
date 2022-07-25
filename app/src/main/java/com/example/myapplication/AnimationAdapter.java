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

public class AnimationAdapter extends RecyclerView.Adapter<AnimationAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movieList;
    OnMovieListenerAnimation onMovieListenerAnimation;
    public AnimationAdapter(Context context, ArrayList<Movie> movieList,OnMovieListenerAnimation onMovieListenerAnimation) {
        this.context = context;
        this.movieList = movieList;
        this.onMovieListenerAnimation = onMovieListenerAnimation;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlistitem,parent,false);
        return new MovieHolder(view,onMovieListenerAnimation);
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
        OnMovieListenerAnimation monMovieListenerAnimation;
        public MovieHolder(@NonNull View itemView,OnMovieListenerAnimation onMovieListenerAnimation) {
            super(itemView);
            image = itemView.findViewById(R.id.imageL);
            monMovieListenerAnimation = onMovieListenerAnimation;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            monMovieListenerAnimation.onMovieClickAnimation(getAdapterPosition());
        }
    }
    public interface OnMovieListenerAnimation{
        void onMovieClickAnimation(int postion);
    }
}
