package com.tacademy.singleplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.FaceBook;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.FacebookLoginRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.btn_facebook)
    Button facebookButton;
    @BindView(R.id.btn_naver)
    Button btn_naver;
    @BindView(R.id.btn_kakao)
    Button btn_kakao;

    CallbackManager callbackManager;
    LoginManager mLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        mLoginManager = LoginManager.getInstance(); // LoginManager 획득

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin()) {
                    logoutFacebook();
                } else {
                    loginFacebook();
                }
            }
        });
        setButtonLabel();

        btn_naver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private  void setButtonLabel() {
        if(isLogin()) {
            facebookButton.setText("logout");
        } else {
            facebookButton.setText("login");
        }

    }
    AccessTokenTracker mTracker;

    @Override
    protected void onStart() {
        super.onStart();
        if (mTracker == null) {
            mTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    setButtonLabel();
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
    }

    private void loginFacebook() {
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "login manager...", Toast.LENGTH_SHORT).show();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
//                Log.i("jeahyun : ", accessToken.getToken()+"");
                String token = accessToken.getToken();
                FacebookLoginRequest request = new FacebookLoginRequest(LoginActivity.this, token);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<FaceBook>>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultsList<FaceBook>> request, ResultsList<FaceBook> result) {
                        Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, SignInActivity.class));
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultsList<FaceBook>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(LoginActivity.this, "실패"+errorCode+errorMessage, Toast.LENGTH_SHORT).show();
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

    private boolean isLogin() { //이거 가지고 로그인 여부를 확인 할수 있음
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}