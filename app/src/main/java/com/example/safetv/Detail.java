package com.example.safetv;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
import java.util.Map;

public class Detail extends AppCompatActivity {

    private TextView tvjudul, tvnama_akun,tvkategori;
    private ImageView imgphoto;
    private ImageView saya, home;
    private static String URLstring = "http://192.168.5.31/safetv/tampilan_home.php";
    protected VideoView tampilVideoDetail;
    private MediaController mediacontroller4;
    private Uri uri4;
    private ListView listViews6;
    ArrayList<DataModel> dataModelArrayList5;
    private ListAdapter listAdapter4;

    private void receiveAndShowData(){
        Intent i = this.getIntent();
        String judul = i.getExtras().getString("JUDUL_KEY");
        String nama = i.getExtras().getString("NAMA_AKUN_KEY");
        String kategori = i.getExtras().getString("KATEGORI_KEY");
        String photo = i.getExtras().getString("PHOTO_AKUN_KEY");
        String video = i.getExtras().getString("VIDEO_KEY");
        tvjudul.setText(judul);
        tvnama_akun.setText(nama);
        tvkategori.setText(kategori);
        Picasso.get().load(photo).into(imgphoto);

        mediacontroller4 = new MediaController(this);
        mediacontroller4.setAnchorView(tampilVideoDetail);
//        uri4 = Uri.parse(video);
        tampilVideoDetail.setMediaController(mediacontroller4);
        tampilVideoDetail.setVideoPath(String.valueOf(video));
        tampilVideoDetail.requestFocus();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvjudul = findViewById(R.id.judulDetail);
        tvnama_akun = findViewById(R.id.namaakunDetail);
        tvkategori = findViewById(R.id.kategoriDetail);
        imgphoto = findViewById(R.id.photo_akunDetail);
        tampilVideoDetail = findViewById(R.id.getvideo);
        home = findViewById(R.id.beranda);
        listViews6 = findViewById(R.id.listview6);

        saya = findViewById(R.id.saya);

        saya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Detail.this, Saya.class);
                startActivity(go);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detail.this, Home.class));
            }
        });

        tampilVideoDetail.start();

        receiveAndShowData();
        retrieveJSON();
    }

    private void retrieveJSON() {

        StringRequest stringRequest5 = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            dataModelArrayList5 = new ArrayList<>();
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

                                dataModelArrayList5.add(playerModel);



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
                })
        {};


        RequestQueue requestQueue4 = Volley.newRequestQueue(this);

        requestQueue4.add(stringRequest5);


    }

    private void setupListview(){
        listAdapter4 = new ListAdapter2(this, dataModelArrayList5);
        listViews6.setAdapter(listAdapter4);
    }


}
