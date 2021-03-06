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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
    @Nullable
    @BindView(R.id.seat_rv)
    RecyclerView recyclerView;

    @BindView(R.id.seat_image)
    ImageView seat_image;

    @BindView(R.id.show_time)
    TextView show_time;
    @BindView(R.id.show_location)
    TextView show_location;
    @BindView(R.id.show_day)
    TextView show_day;
    @BindView(R.id.show_name)
    TextView show_name;
    @BindView(R.id.show_theme)
    ImageView themeView;

    PhotoViewAttacher mAttacher;
    EmptySeatAdapter mAdapter;

    String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_seat_info);
        ButterKnife.bind(this);

        final Button btn = (Button) findViewById(R.id.btn_nextstep);

        Intent intent = getIntent();
        theme = intent.getStringExtra("theme");
        switch (theme) {
            case "뮤지컬": {

                themeView.setBackgroundResource(R.drawable.contents_category_musical);
                break;
            }
            case "오페라": {
                themeView.setBackgroundResource(R.drawable.contents_category_opera);
                break;
            }
            case "콘서트": {
                themeView.setBackgroundResource(R.drawable.contents_category_concert);
                break;
            }
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        btn.setEnabled(false);
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
                btn.setEnabled(true);
                BookingManager.getInstance().setUsableSeatNo(emptySeatInfo.getUsableSeatNo() + "");
                BookingManager.getInstance().setSeatClass(emptySeatInfo.getSeatClass());
                BookingManager.getInstance().setOriPrice(emptySeatInfo.getPrice());
                BookingManager.getInstance().setTotalPrice(emptySeatInfo.getPrice());
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
            case android.R.id.home:
                intent = new Intent(BookingSeatInfoActivity.this, BookingPersonInfoActivity.class);
                intent.putExtra("theme",theme);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            case R.id.detail_menu:
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
//        Log.i("playID", playId);
        EmptySeatRequest request = new EmptySeatRequest(MyApplication.getContext(), playId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<EmptySeat>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<EmptySeat>> request, ResultsList<EmptySeat> result) {

                show_day.setText(result.getResult().getPlayDay());
                show_time.setText(result.getResult().getPlayTime());
                show_location.setText(result.getResult().getPlaceName());
                show_name.setText(result.getResult().getPlayName());
                Glide.with(BookingSeatInfoActivity.this)
                        .load(result.getResult().getPlaceImage())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String s,
                                                       Target<GlideDrawable> target, boolean b) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable glideDrawable,
                                                           String s, Target<GlideDrawable> target, boolean b,
                                                           boolean b1) {
                                if (mAttacher != null) {
                                    mAttacher.update();
                                } else {
                                    mAttacher = new PhotoViewAttacher(seat_image);
                                }
                                // }
                                return false;
                            }
                        }).diskCacheStrategy(DiskCacheStrategy.ALL).into(seat_image);

                EmptySeatInfo[] datas = result.getResult().getSeatInfo();
                mAdapter.clear();
                mAdapter.addAll(datas);

            }

            @Override
            public void onFail(NetworkRequest<ResultsList<EmptySeat>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}