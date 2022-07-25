package com.example.myapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SimilarAdapter.OnSimialrListener,searchAdapter.OnMovieListenerAdventure {


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    EditText search;
    TextView similarT;
    RequestQueue requestQueue;
    RecyclerView searchR,similarR;
    Movie m;
    ImageView searchBtn;
    ArrayList<Movie> list;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = view.findViewById(R.id.searchE);
        similarT = view.findViewById(R.id.similarT);
        similarT.setVisibility(View.INVISIBLE);
        requestQueue = Singleton.getmInstance(getContext()).getRequestQueue();
        searchR = view.findViewById(R.id.searched);
        searchBtn = view.findViewById(R.id.search_button);
        list = new ArrayList<>();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clicked");
                list.clear();
                fetchMovie(search.getText());
                similarT.setVisibility(View.VISIBLE);
            }
        });

    }
    public void fetchMovie(Editable q){
        String url = String.format("https://api.themoviedb.org/3/search/multi?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1&include_adult=false&query=%s",q);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                boolean found = false;
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
                                m = movie;
                                inRecylcerView(movie);
                                fetchSimilar(m);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
    private void inRecylcerView(Movie movie){
        searchR.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        searchAdapter searchAdapter = new searchAdapter(getContext(),movie,this);
        searchR.setAdapter(searchAdapter);
    }
    public void fetchSimilar(Movie mov){
        String url = null;
        if(mov.type.equals("movie")){
            url = String.format("https://api.themoviedb.org/3/movie/%s/similar?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1",mov.id);
        }else{
            url = String.format("https://api.themoviedb.org/3/tv/%s/similar?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US&page=1",mov.id);
        }
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
                                String q;
                                if(mov.type.equals("movie")){
                                    q = "title";
                                }else{
                                    q = "name";
                                }
                                String title = jsonObject.getString(q);
                                String overview = jsonObject.getString("overview");
                                String poster = jsonObject.getString("poster_path");
                                String back = jsonObject.getString("backdrop_path");
                                int id = jsonObject.getInt("id");
                                double rating = jsonObject.getDouble("vote_average");
                                JSONArray genre = jsonObject.getJSONArray("genre_ids");
                                Movie movie = new Movie(back,title,poster,overview,id,rating,genre,mov.type);
                                list.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        inRecylcerView(list);
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
        similarR = getView().findViewById(R.id.similar);
        similarR.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        SimilarAdapter similarAdapter = new SimilarAdapter(getContext(),list,this);
        similarR.setAdapter(similarAdapter);

    }

    @Override
    public void onSimilarClick(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(list.get(position).id));
        intent.putExtra("rating",String.valueOf(list.get(position).rating));
        intent.putExtra("overview",list.get(position).overview);
        intent.putExtra("title",list.get(position).title);
        intent.putExtra("type",list.get(position).type);

        startActivity(intent);
    }

    @Override
    public void onMovieClickAdventure(int position) {
        Intent intent = new Intent(getContext(),VideoActivity.class);
        intent.putExtra("id",String.valueOf(m.id));
        intent.putExtra("rating",String.valueOf(m.rating));
        intent.putExtra("overview",m.overview);
        intent.putExtra("title",m.title);
        intent.putExtra("type",m.type);

        startActivity(intent);
    }
}