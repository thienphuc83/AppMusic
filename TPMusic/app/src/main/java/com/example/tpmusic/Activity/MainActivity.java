package com.example.tpmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tpmusic.Adapter.MainViewPagerAdapter;
import com.example.tpmusic.Fragment.Fragment_TimKiem;
import com.example.tpmusic.Fragment.Fragment_TrangChu;
import com.example.tpmusic.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        Init();
    }

    private void Init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.AddFragment(new Fragment_TrangChu(),"");
        mainViewPagerAdapter.AddFragment(new Fragment_TimKiem(), "");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.house);
        tabLayout.getTabAt(1).setIcon(R.drawable.search);
    }

    private void AnhXa() {
        tabLayout = findViewById(R.id.myTadlayout);
        viewPager = findViewById(R.id.myViewpager);

    }
}