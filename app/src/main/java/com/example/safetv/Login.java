package com.example.safetv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private ImageView daftar, masuk;
    private EditText email,password;
    private static String url_login = "http://"+Konfigurasi.IP+"/safetv/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        daftar =  findViewById(R.id.daftar);
        masuk = findViewById(R.id.masuk);
        email = findViewById(R.id.etemail);
        password = findViewById(R.id.etpass);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Login.this, Daftar.class);
                startActivity(go);
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memail = email.getText().toString().trim();
                String mpass = password.getText().toString().trim();

                if (!memail.isEmpty() || !mpass.isEmpty()){
                    Login(memail,mpass);
                } else {
                    email.setError("Silahkan masukkan email");
                    password.setError("Silahkan masukkan password");
                }
            }
        });

    }

    private void Login(final String email, final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")){
                        for (int i=0 ; i<jsonArray.length() ; i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String nama = object.getString("nama").trim();
                            String email = object.getString("email").trim();
                            String id = object.getString("id").trim();

                            sessionManager.createSession(nama, email, id);

                            Intent intent = new Intent(Login.this, Home.class);
                            intent.putExtra("namaakun", nama);
                            startActivity(intent);
                            finish();

                            Toast.makeText(Login.this,
                                    "Login Sukses. \nNama Kamu : "+nama+"\nEmail Kamu : "+email, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show();
                }
            }
        },
         new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Toast.makeText(Login.this, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show();
         }
        })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
