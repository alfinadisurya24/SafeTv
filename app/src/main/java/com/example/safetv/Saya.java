package com.example.safetv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class Saya extends AppCompatActivity {
    private TextView namaakun;
    private ImageView logout,beranda;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saya);

        sessionManager = new SessionManager(this);

        logout = findViewById(R.id.logout);
        namaakun = findViewById(R.id.nama);
        beranda = findViewById(R.id.beranda);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mNama = user.get(sessionManager.NAMA);

        namaakun.setText(mNama);

        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Saya.this, Home.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });
    }
}