package com.example.tpmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.tpmusic.Adapter.DanhSachPlayListAdapter;
import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {

    Toolbar toolbarDanhsachPlaylist;
    RecyclerView rvDanhsachplaylist;
    DanhSachPlayListAdapter danhSachPlayListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        AnhXa();
        GetData();
        init();

    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> callback= dataservice.GetDanhSachPlayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangplaylist = (ArrayList<PlayList>) response.body();
//                Log.d("mmm", mangplaylist.get(0).getIdPlaylist());
                danhSachPlayListAdapter = new DanhSachPlayListAdapter(DanhSachPlayListActivity.this, mangplaylist);
                rvDanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this,2));
                rvDanhsachplaylist.setAdapter(danhSachPlayListAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbarDanhsachPlaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbarDanhsachPlaylist.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbarDanhsachPlaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void AnhXa() {
        toolbarDanhsachPlaylist = findViewById(R.id.toolbardanhsachplaylist);
        rvDanhsachplaylist = findViewById(R.id.rvdanhsachplaylist);
    }
}