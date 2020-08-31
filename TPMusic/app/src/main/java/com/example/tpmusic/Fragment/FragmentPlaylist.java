package com.example.tpmusic.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tpmusic.Activity.DanhSachBaiHatActivity;
import com.example.tpmusic.Activity.DanhSachPlayListActivity;
import com.example.tpmusic.Adapter.PlayListAdapter;
import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIRetrofitClient;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPlaylist extends Fragment {

    View view;
    ListView lvPlaylist;
    TextView tvTitlePlaylist, tvNotePlaylist, tvXemthemPlaylist;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangplaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        AnhXa();
        GetData();
        tvXemthemPlaylist.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachPlayListActivity.class);
//                intent.putExtra("danhsachplaylist", )
                startActivity(intent);
            }
        });
        return view;
    }

    private void AnhXa() {
        lvPlaylist = view.findViewById(R.id.lvplaylist);
        tvTitlePlaylist = view.findViewById(R.id.tvnhipdieu);
        tvNotePlaylist = view.findViewById(R.id.tvnote);
        tvXemthemPlaylist = view.findViewById(R.id.tvxemthemplaylist);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> callback= dataservice.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangplaylist= (ArrayList<PlayList>) response.body();
//                Log.d("AAA", mangplaylist.get(0).getTen());
                playListAdapter =new PlayListAdapter(getActivity(), android.R.layout.simple_list_item_1, mangplaylist);
                lvPlaylist.setAdapter(playListAdapter);
                setListViewHeightBasedOnChildren(lvPlaylist);

                lvPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                        intent.putExtra("itemplaylist", mangplaylist.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    // set lại chiều cao khi hiện listview trong fragment
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
