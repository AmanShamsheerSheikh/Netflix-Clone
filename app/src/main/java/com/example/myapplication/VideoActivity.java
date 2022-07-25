package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoActivity extends AppCompatActivity{
    YouTubePlayerView youTubePlayerView;
    Button btn;
    RequestQueue requestQueue;
    TextView Title;
    TextView Rating;
    TextView Overview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String rating = intent.getStringExtra("rating");
        String overview = intent.getStringExtra("overview");
        String title = intent.getStringExtra("title");
        String type = intent.getStringExtra("type");
        setContentView(R.layout.activity_video);
        youTubePlayerView = findViewById(R.id.youtubePlayerView);
        requestQueue = Singleton.getmInstance(this).getRequestQueue();
        btn = findViewById(R.id.play);
        Title = findViewById(R.id.title);
        Rating = findViewById(R.id.rating);
        Overview = findViewById(R.id.overview);
        Title.setText(title);
        Rating.setText(rating);
        Overview.setText(overview);
        fetchKey(id,type);
    }
    public void fetchKey(String id,String type) {
        String url = null;
        if(type.equals("tv")){
            url = String.format("https://api.themoviedb.org/3/tv/%s/videos?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US",id);
        }else if(type.equals("movie")){
            url = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=382de7762baef9fbbe2b6b099dd9b13a&language=en-US",id);
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
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String name = jsonObject.getString("name");
                            String key = jsonObject.getString("key");
                            runVideo(key);
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
    public void runVideo(String key){
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        youTubePlayer.loadVideo(key,0);
                    }
                });

            }
        });
    }
}