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
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Logout;
import com.tacademy.singleplay.data2.Profile;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.PropertyManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.manager.UserInfoManager;
import com.tacademy.singleplay.request.LogoutRequest;
import com.tacademy.singleplay.request.ProfileRequest;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        txt_email.setText(UserInfoManager.getInstance().getEmail());
        txt_phone.setText(UserInfoManager.getInstance().getPhone());
        textView.setText(UserInfoManager.getInstance().getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                PropertyManager.getInstance().setCheckLogin(false);
                LogoutRequest request = new LogoutRequest(MyApplication.getContext());
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Logout>>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultsList<Logout>> request, ResultsList<Logout> result) {
                        Toast.makeText(ProfileActivity.this, "로그아웃 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultsList<Logout>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(ProfileActivity.this, "로그아웃 실패", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(ProfileActivity.this, UserActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });
    }

    @OnClick(R.id.btn_change)
    public void onChange() {
        String userEmail = txt_email.getText().toString();
        String userPhone = txt_phone.getText().toString();

        ProfileRequest request = new ProfileRequest(this, userEmail, userPhone);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Profile>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Profile>> request, ResultsList<Profile> result) {
                Toast.makeText(ProfileActivity.this, "성공", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Profile>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(ProfileActivity.this, "실패" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                Log.i("onfail ",errorMessage+" , "+errorCode );
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

}
