package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements movieAdapter.onMovieListener,TrendingAdapter.onMovieListener,PopularMovieAdapter.onMovieListener,PopularTvAdapter.onMovieListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    RecyclerView carousel,trendingR,popularMR,popularTR;
    ArrayList<Movie> mList,tList,mpList,tpList;
    RequestQueue requestQueue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        tList = new ArrayList<>();
        tpList = new ArrayList<>();
        mpList = new ArrayList<>();
        requestQueue = Singleton.getmInstance(getContext()).getRequestQueue();
        fetchMovies();
        fetchTrending();
        fetchMoviesPopular();
        fetchTvPopular();
    }
    public void fetchMovies(){
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String overview = jsonObject.getString("overview");
                                String poster = jsonObject.getString("poster_path");
                                String back = jsonObject.getString("backdrop_path");
                                int id = jsonObject.getInt("id");
                                double rating = jsonObject.getDouble("vote_average");
                                JSONArray genre = jsonObject.getJSONArray("genre_ids");
                                Movie movie = new Movie(back,title,poster,overview,id,rating,genre,"movie");
                                mList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerView(mList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private void inRecylcerView(ArrayList<Movie>list){
        carousel = getView().findViewById(R.id.carousel);
        carousel.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        movieAdapter mAdapter = new movieAdapter(getContext(),list,this);
        carousel.setAdapter(mAdapter);
    }

    public void fetchTrending(){
        String url = "https://api.themoviedb.org/3/trending/all/day?api_key=382de7762baef9fbbe2b6b099dd9b13a";
//        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=74319c5bf763433593c752ceeecedd6b";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String q = null;
                                String type = null;
                                try{
                                    String title = jsonObject.getString("title");
                                    q = title;
                                    type = "movie";
                                }catch (Exception e){

                                }
                                try{
                                    String name = jsonObject.getString("name");
                                    q = name;
                                    type = "tv";
                                }catch (Exception e){

                                }
                                String overview = jsonObject.getString("overview");
                                String poster = jsonObject.getString("poster_path");
                                String back = jsonObject.getString("backdrop_path");
                                int id = jsonObject.getInt("id");
                                double rating = jsonObject.getDouble("vote_average");
                                JSONArray genre = jsonObject.getJSONArray("genre_ids");
                                Movie movie = new Movie(back,q,poster,overview,id,rating,genre,type);
                                System.out.println(q);
                                tList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerViewTrending(tList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private void inRecylcerViewTrending(ArrayList<Movie>list){
        trendingR = getView().findViewById(R.id.trendingR);
        trendingR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        TrendingAdapter trendingAdapter = new TrendingAdapter(getContext(),list,this);
        trendingR.setAdapter(trendingAdapter);
    }

    public void fetchMoviesPopular(){
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String overview = jsonObject.getString("overview");
                                String poster = jsonObject.getString("poster_path");
                                String back = jsonObject.getString("backdrop_path");
                                int id = jsonObject.getInt("id");
                                double rating = jsonObject.getDouble("vote_average");
                                JSONArray genre = jsonObject.getJSONArray("genre_ids");
                                Movie movie = new Movie(back,title,poster,overview,id,rating,genre,"movie");
                                mpList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerViewPopular(mpList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private void inRecylcerViewPopular(ArrayList<Movie>list){
        popularMR = getView().findViewById(R.id.popularMR);
        popularMR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        PopularMovieAdapter popularMovieAdapter = new PopularMovieAdapter(getContext(),list,this);
        popularMR.setAdapter(popularMovieAdapter);
    }

    public void fetchTvPopular(){
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("name");
                                String overview = jsonObject.getString("overview");
                                String poster = jsonObject.getString("poster_path");
                                String back = jsonObject.getString("backdrop_path");
                                int id = jsonObject.getInt("id");
                                double rating = jsonObject.getDouble("vote_average");
                                JSONArray genre = jsonObject.getJSONArray("genre_ids");
                                Movie movie = new Movie(back,title,poster,overview,id,rating,genre,"tv");
                                tpList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerViewPopularTv(tpList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private void inRecylcerViewPopularTv(ArrayList<Movie>list){
        popularTR = getView().findViewById(R.id.popularTR);
        popularTR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        PopularTvAdapter popularTvAdapter = new PopularTvAdapter(getContext(),list,this);
        popularTR.setAdapter(popularTvAdapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(mList.get(position).id));
        intent.putExtra("rating",String.valueOf(mList.get(position).rating));
        intent.putExtra("overview",mList.get(position).overview);
        intent.putExtra("title",mList.get(position).title);
        intent.putExtra("type",mList.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onMovieClickTrending(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(tList.get(position).id));
        intent.putExtra("rating",String.valueOf(tList.get(position).rating));
        intent.putExtra("overview",tList.get(position).overview);
        intent.putExtra("title",tList.get(position).title);
        intent.putExtra("type",tList.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onMovieClickPopularMovie(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(mpList.get(position).id));
        intent.putExtra("rating",String.valueOf(mpList.get(position).rating));
        intent.putExtra("overview",mpList.get(position).overview);
        intent.putExtra("title",mpList.get(position).title);
        intent.putExtra("type",mpList.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onMovieClickPopularTv(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(tpList.get(position).id));
        intent.putExtra("rating",String.valueOf(tpList.get(position).rating));
        intent.putExtra("overview",tpList.get(position).overview);
        intent.putExtra("title",tpList.get(position).title);
        intent.putExtra("type",tpList.get(position).type);
        startActivity(intent);
    }
}