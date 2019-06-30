package com.example.safetv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(dataModelArrayList.get(position).getThumbnailURL()).into(holder.imgThumbnail);
        Picasso.get().load(dataModelArrayList.get(position).getPhotoURL()).into(holder.imgPhoto);
        holder.tvjudul.setText(dataModelArrayList.get(position).getJudul());
        holder.tvnama.setText(dataModelArrayList.get(position).getNamaakun());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvjudul, tvnama;
        protected ImageView imgPhoto,imgThumbnail;
    }
}