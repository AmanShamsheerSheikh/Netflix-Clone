package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MovieFragment extends Fragment implements nowMovieAdapter.onMovieListener,ActionAdapter.onMovieListenerAction,adventureAdapter.OnMovieListenerAdventure,ComedyAdapter.OnMovieListenerComedy{


    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }
    private RecyclerView nowPlayingRecylcerview,actionR,adventureR,animationR,comedyR,docR,crimeR;
    private RequestQueue requestQueue,requestQueue2,requestQueue3,requestQueue4;
    private ArrayList<Movie> movieList,movieList1,movieList2,movieList3,movieList4,movieList5,movieList6;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Singleton.getmInstance(getContext()).getRequestQueue();
        movieList = new ArrayList<>();
        movieList1 = new ArrayList<>();
        movieList2 = new ArrayList<>();
        movieList3 = new ArrayList<>();
        movieList4 = new ArrayList<>();
        movieList5 = new ArrayList<>();
        movieList6 = new ArrayList<>();
        fetchMovies();
        fetchMovies1();
        fetchMovies2();
        fetchMovies4();
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
                                movieList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerView(movieList);
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
        nowPlayingRecylcerview = getView().findViewById(R.id.nowPlaying);
        nowPlayingRecylcerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        nowMovieAdapter adapter = new nowMovieAdapter(getContext(),list,this);
        nowPlayingRecylcerview.setAdapter(adapter);
    }


    public void fetchMovies1(){
//        String url = "https://api.themoviedb.org/3/search/movie?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&query=action";
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US";
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
                                movieList1.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerView1(movieList1);
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
    public void inRecylcerView1(ArrayList<Movie> movieList){
        actionR = getView().findViewById(R.id.actionR);
        actionR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        ActionAdapter actionAdapter = new ActionAdapter(getContext(),movieList,this);
        actionR.setAdapter(actionAdapter);
    }
    public void fetchMovies2(){
//        String url = "https://api.themoviedb.org/3/search/movie?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&query=Adventure";
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=2";
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
                                movieList2.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecylcerViewAdventure(movieList2);

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

    public void inRecylcerViewAdventure(ArrayList<Movie> list){
        adventureR = getView().findViewById(R.id.adventureR);
        adventureR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adventureAdapter adapter = new adventureAdapter(getContext(),list,this);
        adventureR.setAdapter(adapter);
    }



    public void fetchMovies4() {
//        String url = "https://api.themoviedb.org/3/search/movie?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&query=Comedy";
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String overview = jsonObject.getString("overview");
                                String poster = jsonObject.getString("poster_path");
                                String back = jsonObject.getString("backdrop_path");
                                int id = jsonObject.getInt("id");
                                double rating = jsonObject.getDouble("vote_average");
                                JSONArray genre = jsonObject.getJSONArray("genre_ids");
                                Movie movie = new Movie(back, title, poster, overview, id, rating, genre,"movie");
                                movieList4.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecyclcerViewcomedy(movieList4);
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
    public void inRecyclcerViewcomedy(ArrayList<Movie>list){
        comedyR = getView().findViewById(R.id.comedyR);
        comedyR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        ComedyAdapter adapter = new ComedyAdapter(getContext(), list, this);
        comedyR.setAdapter(adapter);
    }


    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(movieList.get(position).id));
        intent.putExtra("rating",String.valueOf(movieList.get(position).rating));
        intent.putExtra("overview",movieList.get(position).overview);
        intent.putExtra("title",movieList.get(position).title);
        intent.putExtra("type",movieList.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onMovieClickAction(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(movieList1.get(position).id));
        intent.putExtra("rating",String.valueOf(movieList1.get(position).rating));
        intent.putExtra("overview",movieList1.get(position).overview);
        intent.putExtra("title",movieList1.get(position).title);
        intent.putExtra("type",movieList1.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onMovieClickAdventure(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(movieList2.get(position).id));
        intent.putExtra("rating",String.valueOf(movieList2.get(position).rating));
        intent.putExtra("overview",movieList2.get(position).overview);
        intent.putExtra("title",movieList2.get(position).title);
        intent.putExtra("type",movieList2.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onMovieClickComedy(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(movieList4.get(position).id));
        intent.putExtra("rating",String.valueOf(movieList4.get(position).rating));
        intent.putExtra("overview",movieList4.get(position).overview);
        intent.putExtra("title",movieList4.get(position).title);
        intent.putExtra("type",movieList4.get(position).type);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }
}