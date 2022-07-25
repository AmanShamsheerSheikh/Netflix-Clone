package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TvFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TvFragment extends Fragment implements airingTodayAdapter.OnTvListener,actionTvAdapter.OnTvListener,ComedyTvAdapter.OnTvListener,crimeTvAdapter.OnTvListener {


    public TvFragment() {
        // Required empty public constructor
    }

    public static TvFragment newInstance(String param1, String param2) {
        TvFragment fragment = new TvFragment();
        return fragment;
    }
    RecyclerView airingTodayR,actionTvR,comedyTvR,crimeTvR;
    RequestQueue requestQueue;
    ArrayList<Movie> tvList,actionTvL,comedyTvL,crimeTvL;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Singleton.getmInstance(getContext()).getRequestQueue();
        tvList = new ArrayList<>();
        actionTvL = new ArrayList<>();
        comedyTvL = new ArrayList<>();
        comedyTvL = new ArrayList<>();
        crimeTvL = new ArrayList<>();
        fetchTvShows();
        fetchTvShowsAction();
        fetchTvShowsComedy();
        fetchTvShowsCrime();
    }

    public void fetchTvShows(){
        String url = "https://api.themoviedb.org/3/tv/airing_today?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1";
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
                                tvList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecyclerView(tvList);
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
    public void inRecyclerView(ArrayList<Movie> list){
        airingTodayR = getView().findViewById(R.id.airingTodayR);
        airingTodayR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        airingTodayAdapter adapter = new airingTodayAdapter(getContext(),list,this);
        airingTodayR.setAdapter(adapter);
    }

    public void fetchTvShowsAction(){
        String url = "https://api.themoviedb.org/3/search/tv?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1&include_adult=false&query=mystery";

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
                                actionTvL.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecyclerViewAction(actionTvL);
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
    public void inRecyclerViewAction(ArrayList<Movie> list){
        actionTvR = getView().findViewById(R.id.actionTvR);
        actionTvR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        actionTvAdapter actionTvAdapter = new actionTvAdapter(getContext(),list,this);
        actionTvR.setAdapter(actionTvAdapter);
    }

    public void fetchTvShowsComedy(){
        String url = "https://api.themoviedb.org/3/search/tv?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1&include_adult=false&query=family";
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
                                comedyTvL.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecyclerViewComedy(comedyTvL);
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
    public void inRecyclerViewComedy(ArrayList<Movie> list){
        comedyTvR = getView().findViewById(R.id.comedyTvR);
        comedyTvR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        ComedyTvAdapter comedyTvAdapter = new ComedyTvAdapter(getContext(),list,this);
        comedyTvR.setAdapter(comedyTvAdapter);
    }

    public void fetchTvShowsCrime(){
        String url = "https://api.themoviedb.org/3/search/tv?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1&include_adult=false&query=crime";
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
                                crimeTvL.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        inRecyclerViewCrime(crimeTvL);
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
    public void inRecyclerViewCrime(ArrayList<Movie> list){
        crimeTvR = getView().findViewById(R.id.crimeTvR);
        crimeTvR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        crimeTvAdapter crimeTvAdapter = new crimeTvAdapter(getContext(),list,this);
        crimeTvR.setAdapter(crimeTvAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }


    @Override
    public void onTvClick(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(tvList.get(position).id));
        intent.putExtra("rating",String.valueOf(tvList.get(position).rating));
        intent.putExtra("overview",tvList.get(position).overview);
        intent.putExtra("title",tvList.get(position).title);
        intent.putExtra("type",tvList.get(position).type);
        startActivity(intent);
    }

    @Override
    public void onTvClickAction(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(actionTvL.get(position).id));
        intent.putExtra("rating",String.valueOf(actionTvL.get(position).rating));
        intent.putExtra("overview",actionTvL.get(position).overview);
        intent.putExtra("title",actionTvL.get(position).title);
        intent.putExtra("type",actionTvL.get(position).type);
        startActivity(intent);
    }

    @Override
    public void OnTvClickComedy(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(comedyTvL.get(position).id));
        intent.putExtra("rating",String.valueOf(comedyTvL.get(position).rating));
        intent.putExtra("overview",comedyTvL.get(position).overview);
        intent.putExtra("title",comedyTvL.get(position).title);
        intent.putExtra("type",comedyTvL.get(position).type);
        startActivity(intent);
    }

    @Override
    public void OnTvClickCrime(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(crimeTvL.get(position).id));
        intent.putExtra("rating",String.valueOf(crimeTvL.get(position).rating));
        intent.putExtra("overview",crimeTvL.get(position).overview);
        intent.putExtra("title",crimeTvL.get(position).title);
        intent.putExtra("type",crimeTvL.get(position).type);
        startActivity(intent);
    }


}