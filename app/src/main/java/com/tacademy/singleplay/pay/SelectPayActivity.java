package com.tacademy.singleplay.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Discount;
import com.tacademy.singleplay.data2.DiscountCoupons;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.detail.CheckedBookingActivity;
import com.tacademy.singleplay.detail.UserActivity;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.DiscountRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectPayActivity extends AppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView listView;
    @BindView(R.id.text_point)
    TextView pointView;

    CouponAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pay);
        ButterKnife.bind(this);

        mAdapter = new CouponAdapter();
        mAdapter.setOnAdapterItemClickListener(new CouponAdapter.OnCouponAdapterItemClickLIstener() {
            @Override
            public void onCouponAdapterItemClick(View view, DiscountCoupons coupons, int position) {
                // 쿠폰누르면 BookingManager에 쿠폰번호 추가해줄꺼
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(mAdapter);

        DiscountRequest request = new DiscountRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Discount>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Discount>> request, ResultsList<Discount> result) {
                //pointView.setText("" + result.getResult().getMileage());
                DiscountCoupons[] datas = result.getResults().getCoupons();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Discount>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup_paid);

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)findViewById(id);
                if(radioButton != null) {
                    Toast.makeText(SelectPayActivity.this, "현재는 선택이 불가능 합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btn = (Button)findViewById(R.id.btn_complete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPayActivity.this, CheckedBookingActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home :
                intent = new Intent(SelectPayActivity.this, BookingSeatInfoActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            case R.id.detail_menu :
                intent = new Intent(SelectPayActivity.this, UserActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
