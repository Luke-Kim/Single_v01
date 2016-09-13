package com.tacademy.singleplay.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.SignInData;
import com.tacademy.singleplay.data2.Profile;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.UserInfo;
import com.tacademy.singleplay.manager.LoginCheckManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.ProfileRequest;
import com.tacademy.singleplay.request.UserInfoRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_logout)
    Button btn_logout;
    @BindView(R.id.image_userImage)
    ImageView imageView;
    @BindView(R.id.txt_userName)
    TextView textView;
    @BindView(R.id.txt_email)
    EditText txt_email;
    @BindView(R.id.txt_phone)
    EditText txt_phone;

    LoginManager mLoginManager;

    String userName;
    String userEmail;
    String userPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mLoginManager = LoginManager.getInstance();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                logoutFacebook();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });

        UserInfoRequest request = new UserInfoRequest(MyApplication.getContext());
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<UserInfo>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<UserInfo>> request, ResultsList<UserInfo> result) {
                userName = result.getResult().getName();
                userEmail = result.getResult().getEmail();
                userPhone = result.getResult().getPhone();
                textView.setText(userName);
                txt_email.setText(userEmail);
                txt_phone.setText(userPhone);
                Toast.makeText(ProfileActivity.this, userName + ", " + userEmail + ", " + userPhone, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<UserInfo>> request, int errorCode, String errorMessage, Throwable e) {
            }
        });
    }

    @OnClick(R.id.btn_change)
    public void onChangeButton() {
        userName = textView.getText().toString();
        userEmail = txt_email.getText().toString();
        userPhone = txt_phone.getText().toString();
        ProfileRequest request = new ProfileRequest(MyApplication.getContext(), userName, userEmail, userPhone);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Profile>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Profile>> request, ResultsList<Profile> result) {
                Toast.makeText(ProfileActivity.this, "성공", Toast.LENGTH_SHORT).show();
//                imageView.setImageResource(Integer.parseInt(result.getResult().getProfileImg()+""));

            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Profile>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(ProfileActivity.this, "실패" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                Log.i("onfail",errorMessage+" , "+errorCode );
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutFacebook() {
        mLoginManager.logOut();
        LoginCheckManager.getInstance().setCheckLogin(false);
    }
}
