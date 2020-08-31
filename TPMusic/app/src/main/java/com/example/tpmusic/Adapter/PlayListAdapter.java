package com.example.tpmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView tvTenPlaylist;
        ImageView imgIconPlaylist, imgBackgroundPlaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater layoutInflater= LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_playlist, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTenPlaylist = convertView.findViewById(R.id.tvTenplaylist);
            viewHolder.imgIconPlaylist = convertView.findViewById(R.id.imgIconPlaylist);
            viewHolder.imgBackgroundPlaylist = convertView.findViewById(R.id.imgBackgroundPlaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlayList playList = getItem(position);
        Picasso.with(getContext()).load(playList.getHinhPlaylist()).into(viewHolder.imgBackgroundPlaylist);
        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgIconPlaylist);
        viewHolder.tvTenPlaylist.setText(playList.getTen());

        return convertView;
    }
}
