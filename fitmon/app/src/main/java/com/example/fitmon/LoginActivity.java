package com.example.fitmon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ApiErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    public static boolean login_check = false;

    //카카오 로그인에 사용
    private SessionCallback sessionCallback;
    //네이버 로그인에 사용
    private static String OAUTH_CLIENT_ID = "eAL0cXCr6sWcz6kjpyjY";
    private static String OAUTH_CLIENT_SECRET = "ucH6u3gdcY";
    private static String OAUTH_CLIENT_NAME = "네이버 아이디로 로그인";
    public static OAuthLoginButton mOAuthLoginButton;
    public static OAuthLogin mOAuthLoginInstance;
    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //카카오 로그인에 사용
        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

        //네이버 로그인에 사용
        //1 초기화
        mContext = this;
        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.showDevelopersLog(true);
        mOAuthLoginInstance.init(LoginActivity.this, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);
        //2 로그인 버튼 세팅
        mOAuthLoginButton = (OAuthLoginButton) findViewById(R.id.btn_naver_login);
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }

    public static boolean naver;    //로그인 후 화면에 사용될 변수
    public static String naverid;
    public static String naverNick;
    public String password;

    //네이버 로그인 부분 로그인 핸들러
    public OAuthLoginHandler mOAuthLoginHandler = new NaverLoginHandler(this);

    public class NaverLoginHandler extends OAuthLoginHandler {
        public final WeakReference<LoginActivity> mActivity;

        public NaverLoginHandler(LoginActivity activity) {
            mActivity = new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void run(boolean success) {
            LoginActivity activity = mActivity.get();

            if (success) {
                password = mOAuthLoginInstance.getAccessToken(activity);
                ProfileTask task = new ProfileTask();
                // 이 클래스가 유저정보를 가져오는 업무를 담당
                task.execute(password);

                String accessToken = mOAuthLoginInstance.getAccessToken(activity);
                String refreshToken = mOAuthLoginInstance.getRefreshToken(activity);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(activity);
                String tokenType = mOAuthLoginInstance.getTokenType(activity);

                naver = true;

                //커스텀 토스트 띄우기
                LayoutInflater inflater = getLayoutInflater();
                View toastDesign = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.cl_toast_container));

                TextView text = toastDesign.findViewById(R.id.tv_toast_msg);
                text.setText(naverid + "님 환영합니다."); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM, 0, 40); // CENTER를 기준으로 0, 0 위치에 메시지 출력
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastDesign);
                toast.show();

                login_check = true;
                MainActivity.nologin = false;

                Intent intent = new Intent(LoginActivity.this, LevelActivity.class);
                startActivity(intent);
                finish();

            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(activity).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(activity);
                Toast.makeText(activity, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ProfileTask extends AsyncTask<String, Void, String> {
        String result;

        @Override
        protected String doInBackground(String... strings) {
            String token = strings[0];  // 네이버 로그인 접근 토큰
            String header = "Bearer " + token; // Bearer 다음에 공백 추가
            try {
                String apiURL = "https://openapi.naver.com/v1/nid/me";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", header);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                result = response.toString();
                br.close();
                System.out.println(response.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
            //result 값은 JSONObject 형태로 넘어옴
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                //넘어온 result 값을 JSONObject 로 변환해주고, 값을 가져오면 됨
                JSONObject object = new JSONObject(result);
                if (object.getString("resultcode").equals("00")) {
                    JSONObject jsonObject = new JSONObject(object.getString("response"));
                    naverid = jsonObject.getString("id");
                    naverNick = jsonObject.getString("nickname");
                    Log.d("jsonObject", jsonObject.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //카카오 로그인 부분
    public static long id;
    public static boolean kakao;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    //커스텀 토스트 띄우기
                    LayoutInflater inflater = getLayoutInflater();
                    View toastDesign = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.cl_toast_container));

                    TextView text = toastDesign.findViewById(R.id.tv_toast_msg);
                    text.setText(result.getId()+ "님 환영합니다."); // toast_design.xml 파일에서 직접 텍스트를 지정 가능

                    id = result.getId();
                    kakao = true;

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 40); // CENTER를 기준으로 0, 0 위치에 메시지 출력
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(toastDesign);
                    toast.show();

                    login_check = true;
                    MainActivity.nologin = false;

                    Intent intent = new Intent(LoginActivity.this, LevelActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}