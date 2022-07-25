package com.example.myapplication;

import org.json.JSONArray;

import java.util.ArrayList;

public class Movie {
    String backdrop_path, title,poster_path,overview;
    int id;
    double rating;
    JSONArray genre_id;
    String type;

    public Movie(String backdrop_path, String title, String poster_path, String overview, int id, double rating, JSONArray genre_id,String type) {
        this.backdrop_path = backdrop_path;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.id = id;
        this.rating = rating;
        this.genre_id = genre_id;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public JSONArray getGenre_id() {
        return genre_id;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setGenre_id(JSONArray genre_id) {
        this.genre_id = genre_id;
    }
}
