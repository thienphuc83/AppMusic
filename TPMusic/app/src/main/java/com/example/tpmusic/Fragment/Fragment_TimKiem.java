package com.example.tpmusic.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Adapter.SearchBaiHatAdapter;
import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {
    View view;
    Toolbar toolbarSearch;
    RecyclerView rvSearch;
    TextView tvkothay;

    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_timkiem, container, false);
        toolbarSearch = view.findViewById(R.id.toolbarsearch);
        rvSearch = view.findViewById(R.id.rvdanhsachsearch);
        tvkothay = view.findViewById(R.id.tvsearchkothay);
        // gắn searchview vào toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarSearch);
        toolbarSearch.setTitle("");
        setHasOptionsMenu(true);



        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Log.d("GGG", query);
                SearchTuKhoaBaiHat(query);//query là từ khóa
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaBaiHat(String query){
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.ReSearchBaiHat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size()>0){
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(),mangbaihat);
                    rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvSearch.setAdapter(searchBaiHatAdapter);

                    tvkothay.setVisibility(View.GONE);
                    rvSearch.setVisibility(View.VISIBLE);
                }else {
                    tvkothay.setVisibility(View.VISIBLE);
                    rvSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }



}
