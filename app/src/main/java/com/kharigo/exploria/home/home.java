package com.kharigo.exploria.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kharigo.exploria.MainActivity;
import com.kharigo.exploria.R;
import com.kharigo.exploria.map;
import com.kharigo.exploria.profile_save.profile;
import com.kharigo.exploria.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager manager;
    Product_list_adepter a;
    private List<Product_list> Product_list;
    ImageButton BTN_Profile,BTN_Map,BTN_camera;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


// test use
        final user user=new user(getApplicationContext());
        if (user.getM() != "") { //login
            final String name = user.getName();
        } else {  // not login
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

        BTN_camera = findViewById(R.id.BTN_camera);
        BTN_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
        BTN_Map = findViewById(R.id.BTN_Map);
        BTN_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), map.class);
                startActivity(i);
            }
        });
        BTN_Profile = findViewById(R.id.BTN_Profile);
        BTN_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), profile.class);
                startActivity(i);
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        manager = new GridLayoutManager(home.this, 1);
        recyclerView.setLayoutManager(manager);
        Product_list = new ArrayList<>();
        a = new Product_list_adepter(home.this, Product_list);
        recyclerView.setAdapter(a);
        live();

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
                                ));
                            }
                            a.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String body;
                        Log.i("-------",""+error);

                        //get status code here
                        //get response body and parse with appropriate encoding
                        if(error.networkResponse.data!=null) {
                            try {
                                body = new String(error.networkResponse.data,"UTF-8");
                                Log.i("-------",""+error);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }




}