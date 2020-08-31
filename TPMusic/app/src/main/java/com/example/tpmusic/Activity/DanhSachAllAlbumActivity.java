package com.example.tpmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tpmusic.Adapter.DanhSachAllAlbumAdapter;
import com.example.tpmusic.Model.Album;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAllAlbumActivity extends AppCompatActivity {

    Toolbar toolbaralbum;
    RecyclerView rvdanhsachalbum;
    ArrayList<Album> mangalbum;
    DanhSachAllAlbumAdapter danhSachAllAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_all_album);
        AnhXa();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetDanhSachAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                mangalbum= (ArrayList<Album>) response.body();
//                Log.d("HHH", mangalbum.get(0).getTenAlbum());
                danhSachAllAlbumAdapter = new DanhSachAllAlbumAdapter(DanhSachAllAlbumActivity.this, mangalbum);
                rvdanhsachalbum.setLayoutManager(new GridLayoutManager(DanhSachAllAlbumActivity.this, 2));
                rvdanhsachalbum.setAdapter(danhSachAllAlbumAdapter);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

    }

    private void AnhXa() {
        toolbaralbum = findViewById(R.id.toolbaralbum);
        rvdanhsachalbum =findViewById(R.id.rvdanhsachalbum);

        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Album");
        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}