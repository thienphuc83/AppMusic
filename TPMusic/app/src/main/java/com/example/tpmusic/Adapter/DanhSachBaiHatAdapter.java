package com.example.tpmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tpmusic.Activity.PlayMusicActivity;
import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.R;
import com.example.tpmusic.Service.APIService;
import com.example.tpmusic.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danhsachbaihat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BaiHat baiHat = baiHatArrayList.get(position);
        holder.tvcasi.setText(baiHat.getCaSi());
        holder.tvtenbaihat.setText(baiHat.getTenBaiHat());
        holder.tvindex.setText(position +1 +"");

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvindex, tvtenbaihat, tvcasi;
        ImageView imgluotthich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.tvthututrongdanhsach);
            tvtenbaihat = itemView.findViewById(R.id.tvtenbaihattrongdanhsach);
            tvcasi = itemView.findViewById(R.id.tvtencasitrongdanhsach);
            imgluotthich = itemView.findViewById(R.id.imgluotthichtrongdanhsach);

            // lượt thích
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.heart);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThichBaiHat("1", baiHatArrayList.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("Ok")){
                                Toast.makeText(context, "Đã thích!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
