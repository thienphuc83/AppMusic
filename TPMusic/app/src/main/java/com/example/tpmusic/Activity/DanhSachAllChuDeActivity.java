package com.example.tpmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tpmusic.Adapter.DanhSachChuDeAdapter;
import com.example.tpmusic.Model.ChuDe;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAllChuDeActivity extends AppCompatActivity {

    Toolbar toolbarChude;
    RecyclerView rvDanhsachchude;
    ArrayList<ChuDe> mangchude;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_all_chu_de);
        AnhXa();
        Init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<ChuDe>> callback= dataservice.GetDanhSachChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                mangchude = (ArrayList<ChuDe>) response.body();
//                Log.d("nani", mangchude.get(0).getTenChuDe());
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachAllChuDeActivity.this, mangchude);
                rvDanhsachchude.setLayoutManager(new GridLayoutManager(DanhSachAllChuDeActivity.this, 1));
                rvDanhsachchude.setAdapter(danhSachChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbarChude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chủ đề");
        toolbarChude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChude = findViewById(R.id.toolbardanhsachchude);
        rvDanhsachchude = findViewById(R.id.rvdanhsachchude);
    }
}