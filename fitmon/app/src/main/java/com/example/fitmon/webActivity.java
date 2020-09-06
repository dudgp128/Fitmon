package com.example.fitmon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Calendar;

public class webActivity extends AppCompatActivity {

    WebView mWebView;
    VideoView videoView;

    int exe_mode;
    int check_date = 0;
    int st_year, st_month, st_day, st_hour, st_min, st_sec;
    int fin_year, fin_month, fin_day, fin_hour, fin_min, fin_sec;
    int exe_hour, exe_min, exe_sec;
    int wholeT, upperT, lowerT;

    SharedPreferences record;
    SharedPreferences.Editor recordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        videoView = (VideoView) findViewById(R.id.monster);

        //Uri videoUri= Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        Uri videoUri= Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);

        //VideoView가 보여줄 동영상의 경로 주소(Uri) 설정하기
        videoView.setVideoURI(videoUri);

        //동영상을 읽어오는데 시간이 걸리므로..
        //비디오 로딩 준비가 끝났을 때 실행하도록..
        //리스너 설정
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //비디오 시작
                videoView.start();
            }
        });

        mWebView = (WebView) findViewById(R.id.web);
        mWebView.getSettings().setJavaScriptEnabled(true);

        //whole, upper, lower 불러오기
        record = getSharedPreferences("exe", 0);
        wholeT = record.getInt("wholeTime", -1);
        upperT = record.getInt("upperTime", -1);
        lowerT = record.getInt("lowerTime", -1);

        Intent intent = getIntent();
        String link = intent.getStringExtra("goLink");

        exe_mode = intent.getIntExtra("mode", -1);
        st_year = intent.getIntExtra("year", -1);
        st_month = intent.getIntExtra("month", -1);
        st_day = intent.getIntExtra("day", -1);
        st_hour = intent.getIntExtra("hour", -1);
        st_min = intent.getIntExtra("min", -1);
        st_sec = intent.getIntExtra("sec", -1);

        mWebView.loadUrl(link);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClientClass());
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) ) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐

            Calendar now = Calendar.getInstance();      //현재 날짜와 시간정보 저장
            fin_year = now.get(Calendar.YEAR);          //현재 년도
            fin_month = now.get(Calendar.MONTH) + 1;    //현재 달          //month는 0 to 11
            fin_day = now.get(Calendar.DAY_OF_MONTH);   //현재 날짜 저장
            fin_hour = now.get(Calendar.HOUR_OF_DAY);
            fin_min = now.get(Calendar.MINUTE);
            fin_sec = now.get(Calendar.SECOND);

            if((st_year == fin_year) && (st_month == fin_month) && (st_day == fin_day)) {
                //int형 check_date == 1, 이 경우 다른 운동기록 그대로
                check_date = 1;
                exe_hour = fin_hour - st_hour;
                exe_min = fin_min - st_min;
                exe_sec = fin_sec - st_sec;
                if (exe_sec < 0) {
                    exe_min--;
                    exe_sec += 60;
                }
                if (exe_min < 0) {
                    exe_hour--;
                    exe_min += 60;
                }
            }
            else {
                //int형 check_date == 2, 이 경우 다른 운동기록 초기화
                //fin의 hour, min, sec 그대로 보내
                check_date = 2;
                exe_hour = fin_hour;
                exe_min = fin_min;
                exe_sec = fin_sec;
            }


            if (check_date == 1) {
                switch (exe_mode) {
                    case 1:
                        wholeT += (exe_hour) * 60 + exe_min;
                        break;
                    case 2:
                        upperT += (exe_hour) * 60 + exe_min;
                        break;
                    case 3:
                        lowerT += (exe_hour) * 60 + exe_min;
                        break;
                    default:
                        break;
                }
            }
            else if (check_date == 2) {
                switch (exe_mode) {
                    case 1:
                        wholeT = (exe_hour) * 60 + exe_min;
                        upperT = 0;
                        lowerT = 0;
                        break;
                    case 2:
                        wholeT = 0;
                        upperT = (exe_hour) * 60 + exe_min;
                        lowerT = 0;
                        break;
                    case 3:
                        wholeT = 0;
                        upperT = 0;
                        lowerT = (exe_hour) * 60 + exe_min;
                        break;
                    default:
                        break;
                }
            }

            record = getSharedPreferences("exe", 0);    //stamp라는 그룹이름으로 객체를 가져옴. 없으면 자동생성
            recordEdit = record.edit();        //데이터를 저장하기 위한 edit 생성
            recordEdit.putInt("wholeTime", wholeT);
            recordEdit.putInt("upperTime", upperT);
            recordEdit.putInt("lowerTime", lowerT);
            recordEdit.putInt("finYear", fin_year);
            recordEdit.putInt("finMonth", fin_month);
            recordEdit.putInt("finDay", fin_day);
            recordEdit.commit();

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}