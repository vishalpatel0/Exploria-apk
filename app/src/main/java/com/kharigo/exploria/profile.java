package com.kharigo.exploria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.kharigo.exploria.home.a;

public class profile extends AppCompatActivity {
ImageButton BTN_Back;   profadepter a;
    List<Product_list> Product_list;   RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView f = findViewById(R.id.imageView2);
        BTN_Back = findViewById(R.id.BTN_Profile);
        BTN_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), home.class);
                startActivity(i);
                finish();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.ddd);
        GridLayoutManager manager;
        recyclerView.setHasFixedSize(true);
        manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        Product_list = new ArrayList<>();
        a = new profadepter(profile.this, Product_list);
        recyclerView.setAdapter(a);
        Glide.with(this)
                .load("https://www.seekpng.com/png/detail/174-1741541_man-face-logo-men-face-logo-design.png\n")
                .into(f);live();
                ImageView imageView3 = findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               a(getApplicationContext()); finishAffinity();
            }
        });
    }

    private void  live() {
        String url = "https://www.kharigo.com/job/home.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        live.setVisibility(View.VISIBLE);
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                Product_list.add(new Product_list(
                                        product.getString("id"),
                                        product.getString("type"),
                                        product.getString("user"),
                                        product.getString("info"),
                                        product.getString("img"),
                                        product.getString("like"),
                                        product.getString("comment"),
                                        product.getString("share"),
                                        product.getString("map"),
                                        product.getString("time"),
                                        product.getString("other")
                                )); }a.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });Volley.newRequestQueue(this).add(stringRequest);

    }


}