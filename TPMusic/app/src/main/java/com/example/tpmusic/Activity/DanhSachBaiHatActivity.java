package com.example.tpmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tpmusic.Adapter.DanhSachBaiHatAdapter;
import com.example.tpmusic.Model.Album;
import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.Model.QuangCao;

import com.example.tpmusic.Model.TheLoai;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    ImageView imgDanhsachbaihat;
    ArrayList<BaiHat> mangbaihat;
    DanhSachBaiHatAdapter danhSachBaiHatAdapter;
    PlayList playList;
    QuangCao quangcao;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); // kiểm tra mạng
        AnhXa();
        DataIntent();
        Init();
        if (quangcao !=null && !quangcao.getTenBaiHat().equals("")){
            SetValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());
        }
        if (playList !=null && !playList.getTen().equals("")){
            SetValueInView(playList.getTen(), playList.getIcon());
            GetDataPlayList(playList.getIdPlaylist());
        }
        if (theLoai !=null && !theLoai.getTenTheLoai().equals("")){
            SetValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album !=null && !album.getTenAlbum().equals("")){
            SetValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idalbum){
        Dataservice dataservice= APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoAlbum(idalbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                EventClick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai){
        Dataservice dataservice= APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoTheLoai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
//                Log.d("AAA", mangbaihat.get(0).getTenBaiHat());
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                EventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlayList(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoPlayList(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
//                Log.d("AAA", mangbaihat.get(0).getTenBaiHat());
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                EventClick();
            }
            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void GetDataQuangCao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
//                Log.d("SSS",mangbaihat.get(0).getIdBaiHat());
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this,mangbaihat);
                recyclerDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                EventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void SetValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);

            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgDanhsachbaihat);

    }

    private void Init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.BLACK);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);

        floatingActionButton.setEnabled(false);
    }

    private void AnhXa() {
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout);
        coordinatorLayout= findViewById(R.id.coordinatorLayout);
        toolbar = findViewById(R.id.toolbar);
        recyclerDanhSachBaiHat = findViewById(R.id.recyclerviewDanhsachbaihat);
        imgDanhsachbaihat = findViewById(R.id.imgDanhsach);
        floatingActionButton=findViewById(R.id.floatingactionbutton);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent!=null){
            if (intent.hasExtra("banner")){
                quangcao = (QuangCao) intent.getSerializableExtra("banner");
//                Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
//                Toast.makeText(this, playList.getIdPlaylist(), Toast.LENGTH_SHORT).show();

            }
            if (intent.hasExtra("idtheloai")){
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");

            }
            if (intent.hasExtra("itemalbum")){
                album = (Album) intent.getSerializableExtra("itemalbum");

            }
        }
    }

    private void EventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayMusicActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
            }
        });

    }

}