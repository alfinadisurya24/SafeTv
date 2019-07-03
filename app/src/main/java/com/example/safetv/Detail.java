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


    private void receiveAndShowData(){
        Intent i = this.getIntent();
        String judul = i.getExtras().getString("JUDUL_KEY");
        String nama = i.getExtras().getString("NAMA_AKUN_KEY");
        String photo = i.getExtras().getString("PHOTO_AKUN_KEY");
        tvjudul.setText(judul);
        tvnama_akun.setText(nama);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvjudul = findViewById(R.id.judulDetail);
        tvnama_akun = findViewById(R.id.namaakunDetail);
        imgphoto = findViewById(R.id.photo_akunDetail);

        receiveAndShowData();
    }
}
