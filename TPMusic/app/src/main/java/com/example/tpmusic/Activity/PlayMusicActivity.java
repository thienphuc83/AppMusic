package com.example.tpmusic.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chootdev.blurimg.BlurImage;
import com.example.tpmusic.Adapter.ViewPagerPlayListNhac;
import com.example.tpmusic.Fragment.FragmentDiaNhac;
import com.example.tpmusic.Fragment.FragmentPlayDanhSachBaiHat;
import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {

    Toolbar toolbarPlay;
    TextView tvTimesong, tvTotaltimesong;
    SeekBar seekBar;
    ImageView imgrewind, imgplay, imgnext, imganhnen;
    ImageButton imgrandom, imgreplay;
    ViewPager viewPagerPlaymusic;
    MediaPlayer mediaPlayer;

    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlayListNhac viewPagerPlayListNhac;

    FragmentDiaNhac fragmentDiaNhac;
    FragmentPlayDanhSachBaiHat fragmentPlayDanhSachBaiHat;

    int position = 0;
    boolean replay = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); /// kiểm tra kết nối mạng
        GetDataIntent();
        AnhXa();
        EvenClick();


    }

    private void AnhNen(String img) {
        BlurImage.withContext(this)
                .setBlurRadius(15.5f)
                .setBitmapScale(0.1f)
                .blurFromUri(img)
                .into(imganhnen);
    }

    private void EvenClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlayListNhac.getItem(1) != null) {
                    if (mangbaihat.size() > 0) {
                        fragmentDiaNhac.Playnhac(mangbaihat.get(0).getHinhBaiHat());
                        AnhNen(mangbaihat.get(0).getHinhBaiHat()); /// mờ
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play);
                    if (fragmentDiaNhac.objectAnimator != null) {
                        fragmentDiaNhac.objectAnimator.pause();
                    }// cho dùng quay

                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.pause);
                    if (fragmentDiaNhac.objectAnimator != null) {
                        fragmentDiaNhac.objectAnimator.resume();
                    }
                }
            }
        });
        imgreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (replay == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgreplay.setImageResource(R.drawable.replay_color);
                        imgrandom.setImageResource(R.drawable.random);
                    }
                    imgreplay.setImageResource(R.drawable.replay_color);
                    replay = true;
                } else {
                    imgreplay.setImageResource(R.drawable.replay);
                    replay = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (replay == true) {
                        replay = false;
                        imgrandom.setImageResource(R.drawable.random_color);
                        imgreplay.setImageResource(R.drawable.replay);
                    }
                    imgrandom.setImageResource(R.drawable.random_color);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.random);
                    checkrandom = false;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release(); // đồng bộ lại
                        mediaPlayer = null;

                    }
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if (replay == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;

                            }
                            position = index;
                        }
                        if (position>(mangbaihat.size())-1){
                            position=0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragmentDiaNhac.Playnhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        AnhNen(mangbaihat.get(position).getHinhBaiHat());
                        UpdateTime();

                    }
                }
                imgrewind.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgrewind.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgrewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release(); // đồng bộ lại
                        mediaPlayer = null;

                    }
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        position--;
                        if(position <0){
                            position = mangbaihat.size() -1;
                        }

                        if (replay == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragmentDiaNhac.Playnhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        AnhNen(mangbaihat.get(position).getHinhBaiHat());
                        UpdateTime();

                    }
                }
                imgrewind.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgrewind.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
//            Toast.makeText(this, baiHat.getTenBaiHat(), Toast.LENGTH_SHORT).show();
                mangbaihat.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
//            for (int i=0;i<mangbaihat.size();i++){
//                Log.d("XXX", mangbaihat.get(i).getTenBaiHat());
//            }
                mangbaihat = baiHatArrayList;

            }
        }
    }

    private void AnhXa() {
        toolbarPlay = findViewById(R.id.toolbarPlaymusic);
        tvTimesong = findViewById(R.id.tvphuthientai);
        tvTotaltimesong = findViewById(R.id.tvphuttong);
        seekBar = findViewById(R.id.seekbarplay);
        imgrandom = findViewById(R.id.imgramdom);
        imgrewind = findViewById(R.id.imgrewind);
        imgplay = findViewById(R.id.imgplay);
        imgnext = findViewById(R.id.imgnext);
        imgreplay = findViewById(R.id.imgreplay);
        imganhnen = findViewById(R.id.imganhnenplaynhac);
        viewPagerPlaymusic = findViewById(R.id.viewpagerplaymusic);

        setSupportActionBar(toolbarPlay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlay.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop(); //dừng
                mangbaihat.clear(); // khong bị áp chồng lên nhau
            }
        });
        toolbarPlay.setTitleTextColor(Color.BLACK);
        fragmentDiaNhac = new FragmentDiaNhac();
        fragmentPlayDanhSachBaiHat = new FragmentPlayDanhSachBaiHat();
        viewPagerPlayListNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        viewPagerPlayListNhac.AddFragment(fragmentPlayDanhSachBaiHat);
        viewPagerPlayListNhac.AddFragment(fragmentDiaNhac);
        viewPagerPlaymusic.setAdapter(viewPagerPlayListNhac);

        fragmentDiaNhac = (FragmentDiaNhac) viewPagerPlayListNhac.getItem(1);
        if (mangbaihat.size() > 0) {
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            AnhNen(mangbaihat.get(0).getHinhBaiHat()); /// mờ
            imgplay.setImageResource(R.drawable.pause);

        }

        

    }

    class PlayMp3 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler2.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if (replay == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;

                            }
                            position = index;
                        }
                        if (position>(mangbaihat.size())-1){
                            position=0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragmentDiaNhac.Playnhac(mangbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        AnhNen(mangbaihat.get(position).getHinhBaiHat());
                    }
                    imgrewind.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgrewind.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 5000);

                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler3.postDelayed(this,1000);
                }
            }
        },1000);
    }
}