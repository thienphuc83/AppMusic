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
import com.example.tpmusic.Model.TheLoai;
import com.example.tpmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<TheLoaiTheoChuDeAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public TheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_theloaitheochude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        holder.tvTenTheloai.setText(theLoai.getTenTheLoai());
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgTheloai);
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTheloai;
        TextView tvTenTheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTheloai = itemView.findViewById(R.id.imgtheloaitheochude);
            tvTenTheloai = itemView.findViewById(R.id.tvtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
