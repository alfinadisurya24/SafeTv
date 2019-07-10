package com.example.safetv;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.app.ProgressDialog;
import android.widget.ListAdapter;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Home extends AppCompatActivity  {
    private ImageView saya,kategori;
    private SessionManager sessionManager;
    private EditText pencarianet;
    private static String URLstring = "http://192.168.5.31/safetv/tampilan_home.php";
    private static String URLstring3 = "http://192.168.5.31/safetv/cari_data.php";
    private ListView listView;
    ArrayList<DataModel> dataModelArrayList;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();


        listView = findViewById(R.id.listview);
        saya = findViewById(R.id.saya);
        kategori = findViewById(R.id.kategorihome);
        pencarianet = findViewById(R.id.pencarian);

        saya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Home.this, Saya.class);
                startActivity(go);
            }
        });

        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Home.this, kategori.class);
                startActivity(go);
            }
        });

        retrieveJSON();

        pencarianet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    retrieveJSON();
                }
                else {
                    retrieveSearch();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void retrieveJSON() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("result");

                            for (int i = 0; i < dataArray.length(); i++) {

                                DataModel playerModel = new DataModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                playerModel.setID(dataobj.getString("id"));
                                playerModel.setJudul(dataobj.getString("judul"));
                                playerModel.setNamaakun(dataobj.getString("nama"));
                                playerModel.setKategori(dataobj.getString("kategori"));
                                playerModel.setThumbnailURL(dataobj.getString("thumbnail"));
                                playerModel.setPhotoURL(dataobj.getString("photo"));
                                playerModel.setVideoURL(dataobj.getString("video"));


                                dataModelArrayList.add(playerModel);

                                setupListview();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void retrieveSearch() {

        final String keyword = pencarianet.getText().toString().trim();

        StringRequest stringRequest9 = new StringRequest(Request.Method.POST, URLstring3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response4) {

                        Log.d("strrrrr", ">>" + response4);

                        try {

                            JSONObject obj5 = new JSONObject(response4);
                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj5.getJSONArray("result");

                            for (int i = 0; i < dataArray.length(); i++) {

                                DataModel playerModel = new DataModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                playerModel.setJudul(dataobj.getString("judul"));
                                playerModel.setNamaakun(dataobj.getString("nama"));
                                playerModel.setKategori(dataobj.getString("kategori"));
                                playerModel.setThumbnailURL(dataobj.getString("thumbnail"));
                                playerModel.setPhotoURL(dataobj.getString("photo"));
                                playerModel.setVideoURL(dataobj.getString("video"));


                                dataModelArrayList.add(playerModel);

                                setupListview();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Home.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new HashMap<>();
                params.put("keyword", keyword);
                return params;
            }
        };
        RequestQueue requestQueue5 = Volley.newRequestQueue(this);

        requestQueue5.add(stringRequest9);
    }

    private void setupListview(){
        listAdapter = new ListAdapters(this, dataModelArrayList);
        listView.setAdapter(listAdapter);
    }





}

