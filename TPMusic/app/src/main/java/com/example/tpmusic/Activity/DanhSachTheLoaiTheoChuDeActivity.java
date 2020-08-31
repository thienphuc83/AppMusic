package com.example.tpmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tpmusic.Adapter.TheLoaiTheoChuDeAdapter;
import com.example.tpmusic.Model.ChuDe;
import com.example.tpmusic.Model.TheLoai;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiTheoChuDeActivity extends AppCompatActivity {

    RecyclerView rvDanhsachtheloai;
    Toolbar toolbarTheLoai;
    ChuDe chuDe;
    TheLoai theLoai;
    ArrayList<TheLoai> mangtheloai;
    TheLoaiTheoChuDeAdapter theLoaiTheoChuDeAdapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de);
        GetIntent();
        AnhXa();
        GetData();

    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback = dataservice.GetDanhSachTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                mangtheloai = (ArrayList<TheLoai>) response.body();
//                Log.d("JJJ", mangtheloai.get(0).getTenTheLoai() );
                theLoaiTheoChuDeAdapter = new TheLoaiTheoChuDeAdapter(DanhSachTheLoaiTheoChuDeActivity.this, mangtheloai);
                rvDanhsachtheloai.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiTheoChuDeActivity.this, 2));
                rvDanhsachtheloai.setAdapter(theLoaiTheoChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        toolbarTheLoai = findViewById(R.id.toolbardanhsachtheloai);
        rvDanhsachtheloai = findViewById(R.id.rvdanhsachtheloai);

        setSupportActionBar(toolbarTheLoai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbarTheLoai.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent= getIntent();
        if (intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
//            Toast.makeText(this, chuDe.getIdChuDe(), Toast.LENGTH_SHORT).show();
        }
    }
}