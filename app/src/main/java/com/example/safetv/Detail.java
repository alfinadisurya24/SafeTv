package com.example.safetv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    private TextView tvjudul, tvnama_akun;
    private ImageView imgphoto;

    private void initializeWidgets(){
        tvjudul = findViewById(R.id.judul);
        tvnama_akun = findViewById(R.id.namaakun);
        imgphoto = findViewById(R.id.photo_akun);
    }

    private void receiveAndShowData(){
        Intent i = this.getIntent();
        String judul = i.getExtras().getString("JUDUL_KEY");
        String nama = i.getExtras().getString("NAMAAKUN_KEY");
        String photo = i.getExtras().getString("PHOTO_KEY");

        tvjudul.setText(judul);
        tvnama_akun.setText(nama);
        Picasso.get().load(photo).placeholder(R.drawable.ic_launcher_background).into(imgphoto);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initializeWidgets();
        receiveAndShowData();
    }
}
