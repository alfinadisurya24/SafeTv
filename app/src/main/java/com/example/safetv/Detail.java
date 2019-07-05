package com.example.safetv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class Detail extends AppCompatActivity {

    private TextView tvjudul, tvnama_akun,tvkategori;
    private ImageView imgphoto;
    private ImageView saya;



    private void receiveAndShowData(){
        Intent i = this.getIntent();
        String judul = i.getExtras().getString("JUDUL_KEY");
        String nama = i.getExtras().getString("NAMA_AKUN_KEY");
        String kategori = i.getExtras().getString("KATEGORI_KEY");
        String photo = i.getExtras().getString("PHOTO_AKUN_KEY");
        tvjudul.setText(judul);
        tvnama_akun.setText(nama);
        tvkategori.setText(kategori);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvjudul = findViewById(R.id.judulDetail);
        tvnama_akun = findViewById(R.id.namaakunDetail);
        tvkategori = findViewById(R.id.kategoriDetail);
        imgphoto = findViewById(R.id.photo_akunDetail);


        saya = findViewById(R.id.saya);



        saya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Detail.this, Saya.class);
                startActivity(go);
            }
        });

        receiveAndShowData();
    }


}
