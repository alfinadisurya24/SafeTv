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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ListAdapters1 extends BaseAdapter {
    private Context context;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapters1(Context context, ArrayList<DataModel> dataModelArrayList) {

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
        ListAdapters1.ViewHolder holder;

        if (convertView == null) {
            holder = new ListAdapters1.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listview_video_saya, null, true);

            holder.imgThumbnail2 = (ImageView) convertView.findViewById(R.id.thumbnails2);
            holder.imgPhoto2 = (ImageView) convertView.findViewById(R.id.photo_akuns2);
            holder.tvjudul2 = (TextView) convertView.findViewById(R.id.juduls2);
            holder.tvnama2 = (TextView) convertView.findViewById(R.id.namaakuns2);
            holder.tvkategori2 = (TextView) convertView.findViewById(R.id.kategori2);
            holder.home = (LinearLayout) convertView.findViewById(R.id.listhome);

            convertView.setTag(holder);
        }else {

            holder = (ListAdapters1.ViewHolder)convertView.getTag();
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

        Picasso.get().load(dataModelArrayList.get(position).getThumbnailURL()).into(holder.imgThumbnail2);
        Picasso.get().load(dataModelArrayList.get(position).getPhotoURL()).into(holder.imgPhoto2);
        holder.tvjudul2.setText(dataModelArrayList.get(position).getJudul());
        holder.tvkategori2.setText(dataModelArrayList.get(position).getKategori());
        holder.tvnama2.setText(dataModelArrayList.get(position).getNamaakun());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvjudul2, tvnama2, tvkategori2;
        protected ImageView imgPhoto2,imgThumbnail2;
        protected LinearLayout home;
    }
}
