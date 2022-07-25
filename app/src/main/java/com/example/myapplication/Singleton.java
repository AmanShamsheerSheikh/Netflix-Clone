package com.example.myapplication;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private RequestQueue requestQueue;
    private static volatile Singleton mInstance = null;
    private Singleton(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized Singleton getmInstance(Context context){
        if(mInstance == null){
            synchronized (Singleton.class){
                if(mInstance == null){
                    mInstance = new Singleton(context);
                }
            }

        }
        return mInstance;
    }
    public RequestQueue getRequestQueue(){return requestQueue;}
}
