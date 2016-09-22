package com.tacademy.singleplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.FaceBook;
import com.tacademy.singleplay.data2.Logout;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.UserInfo;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.manager.PropertyManager;
import com.tacademy.singleplay.manager.UserInfoManager;
import com.tacademy.singleplay.request.FacebookLoginRequest;
import com.tacademy.singleplay.request.LogoutRequest;
import com.tacademy.singleplay.request.UserInfoRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.btn_fb)
    RelativeLayout facebookButton;

    CallbackManager callbackManager;
    LoginManager mLoginManager;
    public static String token2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        mLoginManager = LoginManager.getInstance(); // LoginManager 획득

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin()) {
                    logoutFacebook();
                } else {
                    loginFacebook();
                }
            }
        });
//        setButtonLabel();
    }

//    private void setButtonLabel() {
//        if (isLogin()) {
//            facebookButton.setText("logout");
//        } else {
//            facebookButton.setText("login");
//        }
//
//    }

    AccessTokenTracker mTracker;

    @Override
    protected void onStart() {
        super.onStart();
        if (mTracker == null) {
            mTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//                    setButtonLabel();
                }
            };
        } else {
            mTracker.startTracking();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTracker.stopTracking();
    }

    private void logoutFacebook() {
        mLoginManager.logOut();
        PropertyManager.getInstance().setCheckLogin(false);
        clearUserInfo();
        LogoutRequest request = new LogoutRequest(MyApplication.getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Logout>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Logout>> request, ResultsList<Logout> result) {
                Toast.makeText(LoginActivity.this, "로그아웃 성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Logout>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(LoginActivity.this, "로그아웃 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginFacebook() {
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
//                Log.i("jeahyun : ", accessToken.getToken()+"");
                String token = accessToken.getToken();
                token2 = token;
                String regid = PropertyManager.getInstance().getRegistrationToken();
                Log.i("123123 : ", regid);
                FacebookLoginRequest request = new FacebookLoginRequest(LoginActivity.this, token, regid);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<FaceBook>>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultsList<FaceBook>> request, ResultsList<FaceBook> result) {
                        Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_SHORT).show();
                        getUserInfo();
                        PropertyManager.getInstance().setCheckLogin(true);
                        startActivity(new Intent(LoginActivity.this, com.tacademy.singleplay.login.SignInActivity.class));
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultsList<FaceBook>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(LoginActivity.this, "실패" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                        if (isLogin()) {
                            //
                        }
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
        mLoginManager.logInWithReadPermissions(this, Arrays.asList("email"));
    }

    private void login(String token) {

    }

    private boolean isLogin() { //token 값 가지고 로그인 여부를 확인 할수 있음
//        AccessToken token = AccessToken.getCurrentAccessToken();
//        return token != null;
        return PropertyManager.getInstance().isCheckLogin();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void getUserInfo() {
        UserInfoRequest request = new UserInfoRequest(MyApplication.getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<UserInfo>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<UserInfo>> request, ResultsList<UserInfo> result) {
                UserInfoManager.getInstance().setPhone(result.getResult().getPhone());
                UserInfoManager.getInstance().setCoupons(result.getResult().getCoupons());
                UserInfoManager.getInstance().setName(result.getResult().getName());
                UserInfoManager.getInstance().setTheme(result.getResult().getTheme());
                UserInfoManager.getInstance().setDay(result.getResult().getDay());
                UserInfoManager.getInstance().setEmail(result.getResult().getEmail());
                UserInfoManager.getInstance().setMileage(result.getResult().getMileage());
                UserInfoManager.getInstance().setNoti(result.getResult().getNoti());
                UserInfoManager.getInstance().setWishnoti(result.getResult().getWishnoti());
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<UserInfo>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    public void clearUserInfo() {
        UserInfoManager.getInstance().setPhone("");
        UserInfoManager.getInstance().setCoupons(0);
        UserInfoManager.getInstance().setName("");
        int[] theme = {0, 0, 0};
        UserInfoManager.getInstance().setTheme(theme);
        int[] day = {0, 0, 0, 0, 0, 0, 0,};
        UserInfoManager.getInstance().setDay(day);
        UserInfoManager.getInstance().setMileage(0);
        UserInfoManager.getInstance().setNoti("off");
        UserInfoManager.getInstance().setWishnoti("off");
    }
}