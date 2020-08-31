package com.example.tpmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Adapter.BaiHatHotAdapter;
import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FramentBaiHatHot extends Fragment {
    View view;
    RecyclerView recyclerBaihathot;
    BaiHatHotAdapter baiHatHotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihathot, container, false);
        recyclerBaihathot = view.findViewById(R.id.recyclerviewBaihatHot);

        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetBaiHatHot();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
//                Log.d("RRR", baiHatArrayList.get(0).getTenBaiHat());

                baiHatHotAdapter = new BaiHatHotAdapter(getActivity(),baiHatArrayList);
                LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerBaihathot.setLayoutManager(layoutManager);
                recyclerBaihathot.setAdapter(baiHatHotAdapter);


            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
