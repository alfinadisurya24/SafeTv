package com.example.safetv;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ListviewVideoSaya extends AppCompatActivity {

    private ImageView deleteVideoSaya;
    private String id;
    private static String URLDelete = "http://192.168.5.31/safetv/delete_video_saya.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_video_saya);

        deleteVideoSaya = findViewById(R.id.deleteVIdeoSaya);
    }

}
