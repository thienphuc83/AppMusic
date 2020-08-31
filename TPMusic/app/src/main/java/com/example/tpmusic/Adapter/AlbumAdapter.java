package com.example.tpmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Activity.DanhSachBaiHatActivity;
import com.example.tpmusic.Model.Album;
import com.example.tpmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Album album = albumArrayList.get(position);
        holder.tvTenCaSi.setText(album.getTenCaSiAlbum());
        holder.tvTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgalbum);
//        Picasso.get().load(album.getHinhAlbum()).into(holder.imgalbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgalbum;
        TextView tvTenAlbum, tvTenCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgalbum = itemView.findViewById(R.id.imgalbum);
            tvTenAlbum = itemView.findViewById(R.id.tvTenalbum);
            tvTenCaSi = itemView.findViewById(R.id.tvTencasi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemalbum", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
