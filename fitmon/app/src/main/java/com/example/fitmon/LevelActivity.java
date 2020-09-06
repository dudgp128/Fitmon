package com.example.fitmon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class LevelActivity extends AppCompatActivity {

    Button easy, normal, hard, favorite;

    ImageButton btn_result;
    ImageButton btn_logout;
    TextView tv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        easy = (Button)findViewById(R.id.Easy);
        normal = (Button)findViewById(R.id.Normal);
        hard = (Button)findViewById(R.id.Hard);
        favorite = (Button)findViewById(R.id.Favorite);

        btn_result = (ImageButton)findViewById(R.id.btn_result);
        btn_logout = (ImageButton)findViewById(R.id.btn_logout);
        tv_welcome = (TextView)findViewById(R.id.welcome);

        //LoginActivity의 변수 사용
        if(LoginActivity.naver)
            tv_welcome.setText(LoginActivity.naverid + "님 환영합니다.");
        else if(LoginActivity.kakao)
            tv_welcome.setText(LoginActivity.id + "님 환영합니다.");

        //통계창으로 가는 버튼
        btn_result.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatementActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃 이벤트 발생
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MainActivity.nologin == true){
                    //커스텀 토스트 띄우기
                    LayoutInflater inflater = getLayoutInflater();
                    View toastDesign = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.cl_toast_container));

                    TextView text = toastDesign.findViewById(R.id.tv_toast_msg);
                    text.setText("비회원이라서 로그아웃이 불가합니다."); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 40); // CENTER를 기준으로 0, 0 위치에 메시지 출력
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(toastDesign);
                    toast.show();
                }
                else{
                    LoginActivity.login_check = false;
                    //커스텀 토스트 띄우기
                    LayoutInflater inflater = getLayoutInflater();
                    View toastDesign = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.cl_toast_container));

                    TextView text = toastDesign.findViewById(R.id.tv_toast_msg);
                    text.setText("로그아웃 되었습니다"); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 40); // CENTER를 기준으로 0, 0 위치에 메시지 출력
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(toastDesign);
                    toast.show();

                    //네이버 로그아웃
                    LoginActivity.mOAuthLoginInstance.logout(LoginActivity.mContext);

                    //카카오 로그아웃
                    UserManagement.getInstance()
                            .requestLogout(new LogoutResponseCallback() {
                                @Override
                                public void onCompleteLogout() {
                                    finish();
                                }
                            });

                    Intent intent = new Intent(LevelActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        View.OnClickListener levelClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                switch (view.getId()){
                    case R.id.Easy:
                        intent = new Intent(getApplicationContext(), EasyActivity.class);
                        break;
                    case R.id.Normal:
                        intent = new Intent(getApplicationContext(), NormalActivity.class);
                        break;
                    case R.id.Hard:
                        intent = new Intent(getApplicationContext(), HardActivity.class);
                        break;
                    case R.id.Favorite:
                        intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                        break;
                }
                startActivity(intent);
            }
        };

        easy.setOnClickListener(levelClick);
        normal.setOnClickListener(levelClick);
        hard.setOnClickListener(levelClick);
        favorite.setOnClickListener(levelClick);

    }
}