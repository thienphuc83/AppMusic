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
import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachPlayListAdapter extends RecyclerView.Adapter<DanhSachPlayListAdapter.ViewHolder>{

    Context context;
    ArrayList<PlayList> mangPlaylist;

    public DanhSachPlayListAdapter(Context context, ArrayList<PlayList> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danhsachplaylist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = mangPlaylist.get(position);
        Picasso.with(context).load(playList.getIcon()).into(holder.imgHinhnen);
        holder.tvtenplaylist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhnen;
        TextView tvtenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenplaylist = itemView.findViewById(R.id.tvtenplaylisttrongdanhsach);
            imgHinhnen = itemView.findViewById(R.id.imgdanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist", mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
