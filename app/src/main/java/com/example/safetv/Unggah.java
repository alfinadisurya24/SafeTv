package com.example.safetv;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Unggah extends AppCompatActivity {

    private static final String TAG = Unggah.class.getSimpleName();
    private static String URL_UPLOADS = "http://192.168.5.31/safetv/upload_video.php";
    private static final int IMAGE_REQUEST=2;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText editjudul;
    private ImageView upload,thumbnail,video,img_thumbnail;
    private Bitmap bitmap;
    private Uri filePath;
    public static final String UPLOAD_KEY = "thumbnail";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unggah);

        radioGroup = findViewById(R.id.radiogroup);
        thumbnail = findViewById(R.id.c_thumbnail);
        img_thumbnail = findViewById(R.id.img_thumbnail);
        upload = findViewById(R.id.upload);
        editjudul = findViewById(R.id.etjudul);

        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadThumbnail();
            }
        });

    }


    public void PilihKategori(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }

    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img_thumbnail.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void UploadThumbnail(){

        class UploadThumbnail extends AsyncTask<Bitmap,Void,String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Unggah.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Unggah.this, "Unggahan Sukses", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String thumbnail = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                data.put("kategori", radioButton.getText().toString().trim());
                data.put("judul", editjudul.getText().toString().trim());
                data.put(UPLOAD_KEY, thumbnail);
                String result = rh.sendPostRequest(URL_UPLOADS,data);

                return result;
            }
        }

        UploadThumbnail ui = new UploadThumbnail();
        ui.execute(bitmap);
    }

    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodeImage;
    }


}