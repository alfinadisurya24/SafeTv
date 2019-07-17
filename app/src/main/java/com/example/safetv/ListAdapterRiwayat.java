package com.example.safetv;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListAdapterRiwayat extends BaseAdapter {
    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapterRiwayat(Context context, ArrayList<DataModel> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListAdapterRiwayat.ViewHolder holder;

        if (convertView == null) {
            holder = new ListAdapterRiwayat.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listviewriwayat, null, true);

            holder.imgThumbnail9 = (ImageView) convertView.findViewById(R.id.thumbnailsRiwayat);
            holder.imgPhoto9 = (ImageView) convertView.findViewById(R.id.photo_akunsRiwayat);
            holder.tvjudul9 = (TextView) convertView.findViewById(R.id.judulsRiwayat);
            holder.tvnama9 = (TextView) convertView.findViewById(R.id.namaakunsRiwayat);
            holder.tvkategori9 = (TextView) convertView.findViewById(R.id.kategoriRiwayat);
            holder.deleteRiwayat = (ImageView) convertView.findViewById(R.id.deleteRiwayat);

            convertView.setTag(holder);
        }else {

            holder = (ListAdapterRiwayat.ViewHolder)convertView.getTag();
        }

                String video_user_id = dataModelArrayList.get(position).getID();

                riwayat1(video_user_id);


        holder.deleteRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video_riwayat_id = dataModelArrayList.get(position).getID();
                deleteRiwayat(video_riwayat_id);
                context.startActivity(new Intent(context.getApplicationContext(), Saya.class));
            }
        });

        Picasso.get().load(dataModelArrayList.get(position).getThumbnailURL()).into(holder.imgThumbnail9);
        Picasso.get().load(dataModelArrayList.get(position).getPhotoURL()).into(holder.imgPhoto9);
        holder.tvjudul9.setText(dataModelArrayList.get(position).getJudul());
        holder.tvkategori9.setText(dataModelArrayList.get(position).getKategori());
        holder.tvnama9.setText(dataModelArrayList.get(position).getNamaakun());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvjudul9, tvnama9, tvkategori9;
        protected ImageView imgPhoto9,imgThumbnail9,deleteRiwayat;
    }

    private SessionManager sessionManager;
    String getId;

    private void riwayat1(final String videoUid){

        sessionManager = new SessionManager(context.getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        String URL = "http://"+Konfigurasi.IP+"/safetv/riwayat.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new HashMap<>();
                params.put("video_user_id", videoUid);
                params.put("user_id", getId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void deleteRiwayat(final String getIdRiwayat) {


        String URL2 = "http://"+Konfigurasi.IP+"/safetv/delete_riwayat.php";

        StringRequest stringRequest90 = new StringRequest(Request.Method.POST, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Berhasil Menghapus Riwayat", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error"+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new HashMap<>();
                params.put("id_history", getIdRiwayat);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest90);
    }
}
