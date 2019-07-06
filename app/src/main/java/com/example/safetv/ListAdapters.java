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

import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class ListAdapters extends BaseAdapter {
    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapters(Context context, ArrayList<DataModel> dataModelArrayList) {

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
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listview_home, null, true);

            holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
            holder.imgPhoto = (ImageView) convertView.findViewById(R.id.photo_akun);
            holder.tvjudul = (TextView) convertView.findViewById(R.id.judul);
            holder.tvnama = (TextView) convertView.findViewById(R.id.namaakun);
            holder.tvkategori = (TextView) convertView.findViewById(R.id.kategori);
            holder.home = (LinearLayout) convertView.findViewById(R.id.listhome);

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), Detail.class);
                String judulKey = ((TextView) v.findViewById(R.id.judul)).getText().toString();
                String namaAkunKey = ((TextView) v.findViewById(R.id.namaakun)).getText().toString();
                String kategoriKey = ((TextView) v.findViewById(R.id.kategori)).getText().toString();
                String photoAkunKey = dataModelArrayList.get(position).getPhotoURL();
                String videoKey = dataModelArrayList.get(position).getVideoURL();

                i.putExtra("JUDUL_KEY", judulKey);
                i.putExtra("NAMA_AKUN_KEY", namaAkunKey);
                i.putExtra("KATEGORI_KEY", kategoriKey);
                i.putExtra("PHOTO_AKUN_KEY", photoAkunKey);
                i.putExtra("VIDEO_KEY", videoKey);
                context.startActivity(i);
            }
        });


        Picasso.get().load(dataModelArrayList.get(position).getThumbnailURL()).into(holder.imgThumbnail);
        Picasso.get().load(dataModelArrayList.get(position).getPhotoURL()).into(holder.imgPhoto);
        holder.tvjudul.setText(dataModelArrayList.get(position).getJudul());
        holder.tvkategori.setText(dataModelArrayList.get(position).getKategori());
        holder.tvnama.setText(dataModelArrayList.get(position).getNamaakun());

        holder.mediacontroller3 = new MediaController(context.getApplicationContext());
        holder.mediacontroller3.setAnchorView(holder.tampilVideo);
        String uriPath = dataModelArrayList.get(position).getVideoURL();
        holder.uri3 = Uri.parse(uriPath);

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvjudul, tvnama, tvkategori;
        protected ImageView imgPhoto,imgThumbnail;
        protected LinearLayout home;
        protected VideoView tampilVideo;
        private MediaController mediacontroller3;
        private Uri uri3;

    }
}
