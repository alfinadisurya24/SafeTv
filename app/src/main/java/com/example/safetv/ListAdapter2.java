package com.example.safetv;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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

public class ListAdapter2 extends BaseAdapter {
    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapter2(Context context, ArrayList<DataModel> dataModelArrayList) {

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
        ListAdapter2.ViewHolder holder;

        if (convertView == null) {
            holder = new ListAdapter2.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listview_detail, null, true);

            holder.imgThumbnail5 = (ImageView) convertView.findViewById(R.id.thumbnails);
            holder.imgPhoto5 = (ImageView) convertView.findViewById(R.id.photo_akuns);
            holder.tvjudul5 = (TextView) convertView.findViewById(R.id.juduls);
            holder.tvnama5 = (TextView) convertView.findViewById(R.id.namaakuns);
            holder.tvkategori5 = (TextView) convertView.findViewById(R.id.kategoris);
            holder.detail = (LinearLayout) convertView.findViewById(R.id.listdetail);

            convertView.setTag(holder);
        }else {

            holder = (ListAdapter2.ViewHolder)convertView.getTag();
        }

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), Detail.class);
                String judulKey = ((TextView) v.findViewById(R.id.juduls)).getText().toString();
                String namaAkunKey = ((TextView) v.findViewById(R.id.namaakuns)).getText().toString();
                String kategoriKey = ((TextView) v.findViewById(R.id.kategoris)).getText().toString();
                String photoAkunKey = dataModelArrayList.get(position).getPhotoURL();
                String videoKey = dataModelArrayList.get(position).getVideoURL();
                String video_user_id = dataModelArrayList.get(position).getID();

                riwayat2(video_user_id);

                i.putExtra("JUDUL_KEY", judulKey);
                i.putExtra("NAMA_AKUN_KEY", namaAkunKey);
                i.putExtra("KATEGORI_KEY", kategoriKey);
                i.putExtra("PHOTO_AKUN_KEY", photoAkunKey);
                i.putExtra("VIDEO_KEY", videoKey);
                context.startActivity(i);
            }
        });


        Picasso.get().load(dataModelArrayList.get(position).getThumbnailURL()).into(holder.imgThumbnail5);
        Picasso.get().load(dataModelArrayList.get(position).getPhotoURL()).into(holder.imgPhoto5);
        holder.tvjudul5.setText(dataModelArrayList.get(position).getJudul());
        holder.tvkategori5.setText(dataModelArrayList.get(position).getKategori());
        holder.tvnama5.setText(dataModelArrayList.get(position).getNamaakun());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvjudul5, tvnama5, tvkategori5;
        protected ImageView imgPhoto5,imgThumbnail5;
        protected LinearLayout detail;

    }
    private SessionManager sessionManager;
    String getId;

    private void riwayat2(final String videoUid){

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
}
