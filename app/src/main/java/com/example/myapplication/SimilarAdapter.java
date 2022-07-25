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

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> movieList;
    private OnSimialrListener monSimilarListener;
    public SimilarAdapter(Context context, ArrayList<Movie> movieList,OnSimialrListener onSimilarListener) {
        this.context = context;
        this.movieList = movieList;
        this.monSimilarListener = onSimilarListener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlistitem,parent,false);
        return new MovieHolder(view,monSimilarListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+ movie.poster_path).into(holder.image);
    }

    public void updateMovie(ArrayList<Movie> list){
        movieList.clear();
        movieList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        OnSimialrListener monSimilarListener;
        public MovieHolder(@NonNull View itemView,OnSimialrListener onSimilarListener) {
            super(itemView);
            image = itemView.findViewById(R.id.imageL);
            monSimilarListener = onSimilarListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            monSimilarListener.onSimilarClick(getAbsoluteAdapterPosition());
        }
    }
    public interface OnSimialrListener{
        void onSimilarClick(int position);
    }
}
