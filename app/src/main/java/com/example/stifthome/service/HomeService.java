package com.example.stifthome.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public abstract class HomeService {

    private Context context;

    private String baseUrl = "http://raspi.stift.me:8080/api/";

    public HomeService(Context context) {
        this.context = context;
    }


    protected void switchIt(String path) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl + path;

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response.toString();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);
    }

    protected void getState(String path, final LightCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl + path;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.state(Boolean.parseBoolean(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);

    }

    protected void getFloat(String path, final MessCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl + path;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.value(Float.parseFloat(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(stringRequest);

    }


}
