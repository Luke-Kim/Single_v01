package com.tacademy.singleplay.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Booking;
import com.tacademy.singleplay.data2.Discount;
import com.tacademy.singleplay.data2.DiscountCoupons;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.detail.CheckedBookingActivity;
import com.tacademy.singleplay.detail.UserActivity;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.BookingRequest;
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
    @BindView(R.id.edit_point)
    EditText inputView;
    @BindView(R.id.text_total)
    TextView totalView;
    @BindView(R.id.text_ori)
    TextView oriView;
    @BindView(R.id.text_discount)
    TextView discountView;
    @BindView(R.id.chk_coupon)
    CheckBox chk_coupon;
    @BindView(R.id.chk_mileage)
    CheckBox chk_mileage;
    @BindView(R.id.mileage_layout)
    RelativeLayout mileage_layout;
    @BindView(R.id.coupon_conainer)
    FrameLayout coupon_layout;

    private static final String KEY_COUPON = "coupon";
    private static final String KEY_POINT = "point";

    CouponAdapter mAdapter;

    int totalPrice, oriPrice, discountPrice = 0, discountPercent, usePoint;
    int discountCoupon = 0, discountPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pay);
        ButterKnife.bind(this);

        oriPrice = BookingManager.getInstance().getOriPrice();
        totalPrice = BookingManager.getInstance().getTotalPrice();
        oriView.setText("" + oriPrice);
        totalView.setText("" + totalPrice);

        mAdapter = new CouponAdapter();
        mAdapter.setOnAdapterItemClickListener(new CouponAdapter.OnCouponAdapterItemClickLIstener() {
            @Override
            public void onCouponAdapterItemClick(View view, DiscountCoupons coupons, int position) {
                BookingManager.getInstance().setUseCoupon(coupons.getCouponNo() + "");
                discountPercent = coupons.getSaveOff();
                priceCalculator(KEY_COUPON, oriPrice * discountPercent / 100);
            }
        });

        coupon_layout.setVisibility(View.GONE);
        mileage_layout.setVisibility(View.GONE);
        chk_coupon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    coupon_layout.setVisibility(View.VISIBLE);
                } else {
                    coupon_layout.setVisibility(View.GONE);
                }
            }
        });

        chk_mileage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    mileage_layout.setVisibility(View.VISIBLE);
                } else {
                    mileage_layout.setVisibility(View.GONE);
                }
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
                pointView.setText("" + result.getResults().getMileage());
                DiscountCoupons[] datas = result.getResults().getCoupons();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Discount>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

        inputView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputPoint = inputView.getText().toString();
                if (!TextUtils.isEmpty(inputPoint)) {
                    BookingManager.getInstance().setUseMileage(inputPoint);
                    priceCalculator(KEY_POINT, Integer.parseInt(inputPoint));
                }
            }
        });

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup_paid);

        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                if (radioButton != null) {
                    Toast.makeText(SelectPayActivity.this, "현재는 선택이 불가능 합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btn = (Button) findViewById(R.id.btn_complete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookingRequest request = new BookingRequest(MyApplication.getContext(),
                        BookingManager.getInstance().getPlayId(),
                        BookingManager.getInstance().getPlayName(),
                        BookingManager.getInstance().getUsableSeatNo(),
                        BookingManager.getInstance().getSeatClass(),
                        BookingManager.getInstance().getBooker(),
                        BookingManager.getInstance().getBookerPhone(),
                        BookingManager.getInstance().getBookerEmail(),
                        BookingManager.getInstance().getUseCoupon(),
                        BookingManager.getInstance().getUseMileage(),
                        BookingManager.getInstance().getTotalPrice() + "");
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Booking>>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultsList<Booking>> request, ResultsList<Booking> result) {
                        Toast.makeText(SelectPayActivity.this, "예약성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SelectPayActivity.this, CheckedBookingActivity.class);
                        startActivity(intent);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultsList<Booking>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(SelectPayActivity.this, "예약실패", Toast.LENGTH_SHORT).show();
                    }
                });

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
            case android.R.id.home:
                intent = new Intent(SelectPayActivity.this, BookingSeatInfoActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            case R.id.detail_menu:
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

    public void priceCalculator(String Key, int discountPrice) {
        if (Key == KEY_COUPON) {
            discountCoupon = discountPrice;
        } else if (Key == KEY_POINT) {
            discountPoint = discountPrice;
        }
        this.discountPrice = discountCoupon + discountPoint;
        totalPrice = oriPrice - this.discountPrice;
        totalView.setText(totalPrice + "");
        discountView.setText(this.discountPrice + "");
        BookingManager.getInstance().setTotalPrice(totalPrice);
    }
}
