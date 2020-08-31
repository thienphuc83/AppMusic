package com.example.tpmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.tpmusic.Activity.DanhSachAllChuDeActivity;
import com.example.tpmusic.Activity.DanhSachBaiHatActivity;
import com.example.tpmusic.Activity.DanhSachTheLoaiTheoChuDeActivity;
import com.example.tpmusic.Adapter.PlayListAdapter;
import com.example.tpmusic.Model.ChuDe;
import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.Model.TheLoai;
import com.example.tpmusic.Model.TheLoaiTrongNgay;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChuDeTheLoai extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvXemthemChudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chudetheloai, container, false);
        AnhXa();
        GetData();
        tvXemthemChudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachAllChuDeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<TheLoaiTrongNgay> callback = dataservice.GetChuDeTheLoaiTrongNgay();
        callback.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay= response.body();
//                Log.d("CCC", theLoaiTrongNgay.getTheLoai().get(0).getTenTheLoai());

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoaiTrongNgay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList= new ArrayList<>();
                theLoaiArrayList.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(600,350);
                layoutParams.setMargins(10, 20, 10, 10);

                for(int i=0; i<(chuDeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView= new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe()!=null){
                        Picasso.with(getContext()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachTheLoaiTheoChuDeActivity.class);
                            intent.putExtra("chude", chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for(int j=0; j<(theLoaiArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView= new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai()!=null){
                        Picasso.with(getContext()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);

                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        horizontalScrollView = view.findViewById(R.id.HorizontalScrollView);
        tvXemthemChudetheloai = view.findViewById(R.id.tvXemthemChudetheloai);

    }
}
