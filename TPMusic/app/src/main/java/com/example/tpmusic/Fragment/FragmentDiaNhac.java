package com.example.tpmusic.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tpmusic.R;
import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentDiaNhac extends Fragment {
    View view;
    CircleImageView imgDianhac;
    public CircleLineVisualizer hieuung;
    public ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dianhac, container, false);
        imgDianhac = view.findViewById(R.id.imgdianhac);
        hieuung = view.findViewById(R.id.hieuung);

        objectAnimator = ObjectAnimator.ofFloat(imgDianhac,"rotation",0f, 360f); // quay tròn từ 0=>360 độ
        objectAnimator.setDuration(20000); // trong 10s
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE); // bắt đầu
        objectAnimator.setRepeatMode(ValueAnimator.RESTART); // lặp lại
        objectAnimator.setInterpolator(new LinearInterpolator()); // không bị giật
        objectAnimator.start();
        return view;
    }
    public void Playnhac(String hinhanh) {
        Picasso.with(getActivity()).load(hinhanh).into(imgDianhac);
    }
    public void Hieuung(){

    }
}
