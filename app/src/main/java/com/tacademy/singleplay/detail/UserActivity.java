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

import com.tacademy.singleplay.PushActivity;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Profile;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.login.LoginActivity;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.manager.PropertyManager;
import com.tacademy.singleplay.manager.UserInfoManager;
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
    protected void onPostResume() {
        super.onPostResume();
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        Toast.makeText(UserActivity.this, "이름 : " + UserInfoManager.getInstance().getCoupons(), Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


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
        Toast.makeText(UserActivity.this, "" + checkLogin, Toast.LENGTH_SHORT).show();
        if (checkLogin) {
            profileView.setText("프로필 수정 및 로그아웃");
            profileView.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.GONE);
            user_name.setText(UserInfoManager.getInstance().getName());
            coupone_count.setText("" + UserInfoManager.getInstance().getCoupons());
            txt_mileage.setText("" + UserInfoManager.getInstance().getMileage());
        } else {
            loginView.setVisibility(View.VISIBLE);

            user_name.setVisibility(View.GONE);
            profileView.setVisibility(View.GONE);
        }
    }

}