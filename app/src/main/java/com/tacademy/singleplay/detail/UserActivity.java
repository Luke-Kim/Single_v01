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

import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.PushActivity;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.SignInData;
import com.tacademy.singleplay.data2.FaceBook;
import com.tacademy.singleplay.data2.Profile;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.UserInfo;
import com.tacademy.singleplay.login.LoginActivity;
import com.tacademy.singleplay.manager.LoginCheckManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.FacebookLoginRequest;
import com.tacademy.singleplay.request.ProfileRequest;
import com.tacademy.singleplay.request.UserInfoRequest;

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
    int uid;
    boolean isLogin;

    SignInData signInData;

    public int SIGNIN_CHECK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        isLogin = LoginCheckManager.getInstance().isCheckLogin();
        if (isLogin) { //로그인 된상태
            profileView.setText("프로필 수정 및 로그아웃");
            profileView.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.GONE);

            String userName = LoginCheckManager.getInstance().getUserName();
            int couponCnt = LoginCheckManager.getInstance().getCouponCnt();
            int mileage = LoginCheckManager.getInstance().getMileage();
            user_name.setText(userName);
            coupone_count.setText("" + couponCnt);
            txt_mileage.setText("" + mileage);
        } else { //로그인 안된 상태
            user_name.setVisibility(View.GONE);
            profileView.setVisibility(View.GONE);
        }

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

        Toast.makeText(UserActivity.this, "" + LoginCheckManager.getInstance().isCheckLogin(), Toast.LENGTH_SHORT).show();

        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initData() {
//        if (LoginCheckManager.getInstance().isCheckLogin()) { //로그인안된 상태
//            user_name.setVisibility(View.GONE);
//            profileView.setVisibility(View.GONE);
//        } else { //로그인된 상태라서 프로필수정 가능하고 유저정보 띄어주는거
//            profileView.setText("프로필 수정 및 로그아웃");
//            profileView.setVisibility(View.VISIBLE);
//            loginView.setVisibility(View.GONE);
//
//            String userName = LoginCheckManager.getInstance().getUserName();
//            int couponCnt = LoginCheckManager.getInstance().getCouponCnt();
//            int mileage = LoginCheckManager.getInstance().getMileage();
//            user_name.setText(userName);
//            coupone_count.setText("" + couponCnt);
//            txt_mileage.setText("" + mileage);
//        }
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
}