package com.example.tpmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.R;

import java.util.ArrayList;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<BaiHat> mangbaihat;

    public PlayMusicAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_playmusic, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.tvtencasi.setText(baiHat.getCaSi());
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvindex.setText(position +1+ "");


    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvindex, tvtenbaihat, tvtencasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.tvthututrongdanhsachplay);
            tvtenbaihat = itemView.findViewById(R.id.tvtenbaihattrongdanhsachplay);
            tvtencasi = itemView.findViewById(R.id.tvtencasitrongdanhsachplay);
        }
    }
}
