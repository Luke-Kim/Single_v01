package com.tacademy.singleplay.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.tacademy.singleplay.PushActivity;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Profile;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.login.LoginActivity;
import com.tacademy.singleplay.manager.PropertyManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.ProfileRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.profileView)
    TextView profileView;
    @BindView(R.id.txt_login)
    TextView loginView;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.coupon_count)
    TextView coupone_count;
    @BindView(R.id.txt_mileage)
    TextView txt_mileage;

    String userImage = "asdf";
    String token;
    boolean checkLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
//        signInData = InsertPersonInfoActivity.signInData;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, ProfileActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });


        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //    @OnClick(R.id.btn_coupon)
//    public void btn_couponClick(){
//        Intent intent = new Intent(UserActivity.this, CouponActivity.class);
//        startActivity(intent);
//    }
    @OnClick(R.id.coupon_layout)
    public void coupon_layoutClick() {
        Intent intent = new Intent(UserActivity.this, CouponActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.pushView)
    public void pushClick() {
        Intent intent = new Intent(UserActivity.this, PushActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bookingView)
    public void bookingClick() {
        Intent intent = new Intent(UserActivity.this, BookingListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.eventView)
    public void eventClick() {
        Intent intent = new Intent(UserActivity.this, EventNoticeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.inquiryView)
    public void inquiryClick() {
        Intent intent = new Intent(UserActivity.this, InquiryActivity.class);
        startActivity(intent);
    }

    public void initData() {

        final String userName = user_name.getText().toString();
//        String userImage = imageView.getDrawable().toString();
        String userEmail = "";
        String userPhone = "";
        checkLogin = PropertyManager.getInstance().isCheckLogin();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String token = LoginActivity.token2;

        Toast.makeText(UserActivity.this, "" + checkLogin, Toast.LENGTH_SHORT).show();
        if (!checkLogin) {
            ProfileRequest request = new ProfileRequest(this, userName, userImage, userEmail, userPhone);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Profile>>() {
                @Override
                public void onSuccess(NetworkRequest<ResultsList<Profile>> request, ResultsList<Profile> result) {
                    Toast.makeText(UserActivity.this, "성공", Toast.LENGTH_SHORT).show();
                    user_name.setVisibility(View.GONE);
                    profileView.setVisibility(View.GONE);
                }

                @Override
                public void onFail(NetworkRequest<ResultsList<Profile>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(UserActivity.this, "실패" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i("onfail", errorMessage + " , " + errorCode);
                }
            });
        } else {
//            getUserInfo();
            profileView.setText("프로필 수정 및 로그아웃");
            profileView.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.GONE);
        }
    }

//    public void getUserInfo() {
//        UserInfoRequest request = new UserInfoRequest(MyApplication.getContext());
//        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<UserInfo>>() {
//            @Override
//            public void onSuccess(NetworkRequest<ResultsList<UserInfo>> request, ResultsList<UserInfo> result) {
//                UserInfoManager.getInstance().setPhone(result.getResult().getPhone());
//                UserInfoManager.getInstance().setCoupons(result.getResult().getCoupons());
//                UserInfoManager.getInstance().setName(result.getResult().getName());
//                UserInfoManager.getInstance().setTheme(result.getResult().getTheme());
//                UserInfoManager.getInstance().setDay(result.getResult().getDay());
//                UserInfoManager.getInstance().setEmail(result.getResult().getEmail());
//                UserInfoManager.getInstance().setMileage(result.getResult().getMileage());
//
//                user_name.setText(UserInfoManager.getInstance().getName());
//                coupone_count.setText(UserInfoManager.getInstance().getCoupons() + "");
//                txt_mileage.setText(UserInfoManager.getInstance().getMileage() + "");
//            }
//
//            @Override
//            public void onFail(NetworkRequest<ResultsList<UserInfo>> request, int errorCode, String errorMessage, Throwable e) {
//
//            }
//        });
//    }
}