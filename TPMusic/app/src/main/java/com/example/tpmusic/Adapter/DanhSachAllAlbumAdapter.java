package com.example.tpmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Activity.DanhSachBaiHatActivity;
import com.example.tpmusic.Model.Album;
import com.example.tpmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachAllAlbumAdapter extends RecyclerView.Adapter<DanhSachAllAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public DanhSachAllAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_allalbum, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
        holder.tvTenAlbum.setText(album.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenAlbum;
        ImageView imgHinhAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenAlbum = itemView.findViewById(R.id.tvtenalbumtrongdanhsach);
            imgHinhAlbum = itemView.findViewById(R.id.imgalbumtrongdanhsach);

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
