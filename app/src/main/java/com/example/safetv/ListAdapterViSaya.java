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

public class ListAdapterViSaya extends BaseAdapter {

        private Context context;
        private ArrayList<DataModel> dataModelArrayList;

    public ListAdapterViSaya(Context context, ArrayList<DataModel> dataModelArrayList) {

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
            ListAdapterViSaya.ViewHolder holder;

        if (convertView == null) {
            holder = new ListAdapterViSaya.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listview_video_saya, null, true);

            holder.imgThumbnail2 = (ImageView) convertView.findViewById(R.id.thumbnails2);
            holder.imgPhoto2 = (ImageView) convertView.findViewById(R.id.photo_akuns2);
            holder.tvjudul2 = (TextView) convertView.findViewById(R.id.juduls2);
            holder.tvnama2 = (TextView) convertView.findViewById(R.id.namaakuns2);
            holder.tvkategori2 = (TextView) convertView.findViewById(R.id.kategori2);
//            holder.deletes = (ImageView) convertView.findViewById(R.id.deleteVIdeoSaya);

            convertView.setTag(holder);
        }else {

            holder = (ListAdapterViSaya.ViewHolder)convertView.getTag();
        }

//        holder.deletes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        Picasso.get().load(dataModelArrayList.get(position).getThumbnailURL()).into(holder.imgThumbnail2);
        Picasso.get().load(dataModelArrayList.get(position).getPhotoURL()).into(holder.imgPhoto2);
        holder.tvjudul2.setText(dataModelArrayList.get(position).getJudul());
        holder.tvkategori2.setText(dataModelArrayList.get(position).getKategori());
        holder.tvnama2.setText(dataModelArrayList.get(position).getNamaakun());

        return convertView;
    }

        private class ViewHolder {

            protected TextView tvjudul2, tvnama2, tvkategori2;
            protected ImageView imgPhoto2,imgThumbnail2,deletes;
        }
}
