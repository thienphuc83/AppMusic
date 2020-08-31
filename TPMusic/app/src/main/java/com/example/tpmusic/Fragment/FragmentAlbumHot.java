package com.example.tpmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Activity.DanhSachAllAlbumActivity;
import com.example.tpmusic.Adapter.AlbumAdapter;
import com.example.tpmusic.Model.Album;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAlbumHot extends Fragment {
    View view;
    RecyclerView recyclerView;
    TextView tvXemThemAlbum;
    AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_albumhot, container,false);
        AnhXa();
        GetData();

        tvXemThemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachAllAlbumActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void AnhXa() {
        recyclerView = view.findViewById(R.id.recyclerviewAlbum);
        tvXemThemAlbum = view.findViewById(R.id.tvXemthemAlum);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
//                Log.d("EEE", albumArrayList.get(0).getTenAlbum());
                albumAdapter = new AlbumAdapter(getActivity(),albumArrayList);
                LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
