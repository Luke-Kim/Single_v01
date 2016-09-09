package com.tacademy.singleplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    LoginManager mLoginManager; // 버튼과 똑같이 로그인 할 수 있는 역할이다.

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

    private  void setButtonLabel() { // 로그인하거나 로그아웃 하면 버튼의 라벨이 바뀜
        if(isLogin()) {
            facebookButton.setText("facebook_logout");
        } else {
            facebookButton.setText("facebook_login");
        }

    }
    AccessTokenTracker mTracker;

    @Override
    protected void onStart() { //로그인 되어있는지 아닌지 트래킹하고 싶은 경우 액세스 토큰 이용
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
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS); // 공개범위 설정
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK); //로그인 대행해주는 agent로 무엇을 쓸 건지 정함, NATIVE_WITH_FALLBACK : 페이스북 있으면 페북 띄워주고, 없으면 웹화면 띄워주겠다 라는 의미
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() { // 콜백매니저 등록
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, "login manager...", Toast.LENGTH_SHORT).show();
                AccessToken token = AccessToken.getCurrentAccessToken();
                Log.i("jeahyun : ", token.getToken()+"");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
//        mLoginManager.logInWithReadPermissions(this, Arrays.asList("email"));
    }

    private boolean isLogin() { //액세스 토큰(로그인을 하면 가지는 정보) 가지고 로그인 여부를 확인 할수 있음!
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}