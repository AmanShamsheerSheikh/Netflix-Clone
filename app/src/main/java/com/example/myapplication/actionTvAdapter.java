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

public class actionTvAdapter extends RecyclerView.Adapter<actionTvAdapter.MovieHolder> {
    private Context context;
    private ArrayList<Movie> tvList;
    private OnTvListener monTvListenera;
    public actionTvAdapter(Context context, ArrayList<Movie> movieList,OnTvListener onTvListenera) {
        this.context = context;
        this.tvList = movieList;
        this.monTvListenera = onTvListenera;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layoutlistitem,parent,false);
        return new MovieHolder(view,monTvListenera);
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
        OnTvListener monTvListenerA;
        public MovieHolder(@NonNull View itemView,OnTvListener onTvListenera) {
            super(itemView);
            image = itemView.findViewById(R.id.imageL);
            monTvListenerA = onTvListenera;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            monTvListenerA.onTvClickAction(getAdapterPosition());
        }
    }
    public interface OnTvListener{
        void onTvClickAction(int position) ;
    }
}
