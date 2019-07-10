package com.example.safetv;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class Riwayat extends AppCompatActivity {

    private static String URLstring = "http://192.168.5.31/safetv/tampil_riwayat.php";
    private ListView listView7;
    ArrayList<DataModel> dataModelArrayList2;
    private ListAdapter listAdapter2;
    private SessionManager sessionManager;
    private ImageView deletes;
    String getId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        listView7 = findViewById(R.id.listview7);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

//        deletes = findViewById(R.id.deleteVIdeoSaya);
//        deletes.setVisibility(View.GONE);

        retrieveJSON();

    }

    private void retrieveJSON() {

        StringRequest stringRequest6 = new StringRequest(Request.Method.POST, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            dataModelArrayList2 = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("result");

                            for (int i = 0; i < dataArray.length(); i++) {

                                DataModel playerModel = new DataModel();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                playerModel.setID(dataobj.getString("id_history"));
                                playerModel.setJudul(dataobj.getString("judul"));
                                playerModel.setNamaakun(dataobj.getString("nama"));
                                playerModel.setKategori(dataobj.getString("kategori"));
                                playerModel.setThumbnailURL(dataobj.getString("thumbnail"));
                                playerModel.setPhotoURL(dataobj.getString("photo"));

                                dataModelArrayList2.add(playerModel);
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
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user_id", getId);
                return params;
            }
        };
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);

        requestQueue2.add(stringRequest6);


    }
    private void setupListview(){
        listAdapter2 = new ListAdapterRiwayat(this, dataModelArrayList2);
        listView7.setAdapter(listAdapter2);
    }

}
