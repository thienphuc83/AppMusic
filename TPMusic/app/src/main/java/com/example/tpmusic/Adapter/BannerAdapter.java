package com.example.tpmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tpmusic.Activity.DanhSachBaiHatActivity;
import com.example.tpmusic.Model.QuangCao;
import com.example.tpmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> quangCaoArrayList;

    public BannerAdapter(Context context, ArrayList<QuangCao> quangCaoArrayList) {
        this.context = context;
        this.quangCaoArrayList = quangCaoArrayList;
    }

    @Override
    public int getCount() {
        return quangCaoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imgBackgroundBanner = view.findViewById(R.id.imgBackgroundBanner);
        ImageView imgBanner = view.findViewById(R.id.imgBanner);
        TextView tvTitle = view.findViewById(R.id.tvTitleBanner);
        TextView tvNoidung = view.findViewById(R.id.tvNoiDungBanner);

        Picasso.with(context).load(quangCaoArrayList.get(position).getHinhAnh()).into(imgBackgroundBanner);
        Picasso.with(context).load(quangCaoArrayList.get(position).getHinhBaiHat()).into(imgBanner);
        tvTitle.setText(quangCaoArrayList.get(position).getTenBaiHat());
        tvNoidung.setText(quangCaoArrayList.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner", quangCaoArrayList.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
