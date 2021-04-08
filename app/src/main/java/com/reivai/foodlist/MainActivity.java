package com.reivai.foodlist;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv_food;
    ItemFoodAdapter adapter;

    RequestQueue queue;

    List<FoodModel> foodModels = new ArrayList<>();
    String url = "http://34.101.86.157:7000/foods";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Food List");

        rv_food = findViewById(R.id.rv_food);
        rv_food.setHasFixedSize(true);
        rv_food.setLayoutManager(new GridLayoutManager(this, 2));

        getFood();

    }

    private void getFood() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    FoodModel foodModel = new FoodModel();
                    JSONObject obj = null;

                    try {
                        obj = response.getJSONObject(i);
                        foodModel.setName(obj.getString("name"));
                        foodModel.setImage(obj.getString("image"));
                        foodModel.setDesc(obj.getString("desc"));
                        Log.d("wakacaw", "image: " + foodModel.getImage());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    foodModels.add(foodModel);
                }
                adapter = new ItemFoodAdapter(foodModels, MainActivity.this);
                rv_food.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue = Volley.newRequestQueue(this);
        queue.add(arrayRequest);
    }


}
