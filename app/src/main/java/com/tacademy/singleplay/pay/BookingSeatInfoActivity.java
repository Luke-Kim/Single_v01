package com.tacademy.singleplay.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.bookingdetail.EmptySeatAdapter;
import com.tacademy.singleplay.data2.EmptySeat;
import com.tacademy.singleplay.data2.EmptySeatInfo;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.detail.UserActivity;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.EmptySeatRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class BookingSeatInfoActivity extends AppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @Nullable@BindView(R.id.seat_rv)
    RecyclerView recyclerView;

    @BindView(R.id.seat_image)
    ImageView seat_image;
    PhotoViewAttacher mAttacher;

    EmptySeatAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_seat_info);
        ButterKnife.bind(this);

        final Button btn = (Button)findViewById(R.id.btn_nextstep);

        // photoview opensource 설정
        mAttacher = new PhotoViewAttacher(seat_image);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_XY);

        /////////////////////////////////// photoview

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingSeatInfoActivity.this, SelectPayActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

        ////////////////////////////리사이클 뷰어 설정
        mAdapter = new EmptySeatAdapter();
        mAdapter.setOnSeatAdapterClickListener(new EmptySeatAdapter.OnSeatAdapterClickListener() {
            @Override
            public void onSeatAdapterClickListener(View view, EmptySeatInfo emptySeatInfo, int posion) {
                BookingManager.getInstance().setUsableSeatNo(emptySeatInfo.getUsableSeatNo() + "");
                BookingManager.getInstance().setSeatClass(emptySeatInfo.getSeatClass());
                BookingManager.getInstance().setOriPrice(emptySeatInfo.getPrice());
            }
        });

        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        initData();
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
                intent = new Intent(BookingSeatInfoActivity.this, BookingPersonInfoActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            case R.id.detail_menu :
                intent = new Intent(BookingSeatInfoActivity.this, UserActivity.class);
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

    private void initData() {

        String playId = BookingManager.getInstance().getPlayId();
        EmptySeatRequest request = new EmptySeatRequest(MyApplication.getContext(), playId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<EmptySeat>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<EmptySeat>> request, ResultsList<EmptySeat> result) {
                EmptySeatInfo[] datas = result.getResult().getSeatInfo();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<EmptySeat>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
    EmptySeat emptySeat;
    public void setData(EmptySeat emptySeat) {
        this.emptySeat = emptySeat;
        Glide.with(MyApplication.getContext())
                .load(emptySeat.getPlaceImage())
                .into(seat_image);
    }
}
