package com.example.safetv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class kategori extends AppCompatActivity {

    private ImageView video,saya,goolahraga,gomotivasi,gowawasan,gohiburan;
    private static String URLstring = "http://192.168.5.31/safetv/tampilan_kategori_olahraga.php";
    private static String URLstring1 = "http://192.168.5.31/safetv/tampilan_kategori_wawasan.php";
    private static String URLstring2 = "http://192.168.5.31/safetv/tampilan_kategori_motivasi.php";
    private static String URLstring3 = "http://192.168.5.31/safetv/tampilan_kategori_hiburan.php";
    private static String URLstring4 = "http://192.168.5.31/safetv/tampilan_home.php";
    private ListView listView1;
    ArrayList<DataModel> dataModelArrayList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        saya = findViewById(R.id.saya);
        video = findViewById(R.id.video);
        goolahraga = findViewById(R.id.goOlahraga);
        gomotivasi = findViewById(R.id.goMotivasi);
        gohiburan = findViewById(R.id.goHiburan);
        gowawasan = findViewById(R.id.goWawasan);
        listView1 = findViewById(R.id.listview1);

        retrieveJSON();


        saya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(kategori.this, Saya.class);
                startActivity(go);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(kategori.this, Home.class));
            }
        });
        goolahraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilOlahraga();
            }
        });
        gomotivasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilMotivasi();
            }
        });
        gowawasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilWawasan();
            }
        });
        gohiburan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilHiburan();
            }
        });


    }

    private void tampilHiburan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring3,
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

    private void tampilWawasan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring1,
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

    private void tampilMotivasi() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring2,
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

    private void tampilOlahraga() {
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

    private void retrieveJSON() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring4,
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

    private void setupListview(){
        listAdapter = new ListAdapters(this, dataModelArrayList);
        listView1.setAdapter(listAdapter);
    }
}
