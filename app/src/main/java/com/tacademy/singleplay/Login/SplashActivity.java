package com.tacademy.singleplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.FaceBook;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.PropertyManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.FacebookLoginRequest;

public class SplashActivity extends AppCompatActivity {

    LoginManager loginManager;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        if (PropertyManager.getInstance().isCheckLogin()) {
            processFacebookLogin();
        }

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
//        handler.sendEmptyMessageDelayed(0, 2000);
        handler.sendEmptyMessageDelayed(0, 1000);

        if (PropertyManager.getInstance().isCheckLogin()) {

        }
    }

    private void processFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            String token = accessToken.getToken();
            String regid = PropertyManager.getInstance().getRegistrationToken();


            FacebookLoginRequest request = new FacebookLoginRequest(MyApplication.getContext(), token, regid);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<FaceBook>>() {
                @Override
                public void onSuccess(NetworkRequest<ResultsList<FaceBook>> request, ResultsList<FaceBook> result) {
                    Toast.makeText(SplashActivity.this, "자동 로그인 성공", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFail(NetworkRequest<ResultsList<FaceBook>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(SplashActivity.this, "자동 로그인 실패", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    FacebookLoginRequest request = new FacebookLoginRequest(MyApplication.getContext(), accessToken.getToken(),
                                                                                                        PropertyManager.getInstance().getRegistrationToken());
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<FaceBook>>() {
                        @Override
                        public void onSuccess(NetworkRequest<ResultsList<FaceBook>> request, ResultsList<FaceBook> result) {
                            Toast.makeText(SplashActivity.this, "자동 로그인 성공", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<ResultsList<FaceBook>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(SplashActivity.this, "자동 로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
