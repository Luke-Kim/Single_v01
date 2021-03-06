package com.tacademy.singleplay.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Booking;
import com.tacademy.singleplay.data2.BookingCancel;
import com.tacademy.singleplay.data2.BookingDetail;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.BookingCancelRequest;
import com.tacademy.singleplay.request.BookingDetailRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckedBookingActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_play_name)
    TextView txt_play_name;
    @BindView(R.id.txt_play_day)
    TextView txt_play_day;
    @BindView(R.id.txt_play_time)
    TextView txt_play_time;
    @BindView(R.id.txt_seat_class)
    TextView txt_seat_class;
    @BindView(R.id.txt_seat_info)
    TextView txt_seat_info;
    @BindView(R.id.txt_settlement)
    TextView txt_settlement;
    @BindView(R.id.txt_reservation_no)
    TextView txt_reservation_no;
    @BindView(R.id.image_poster)
    ImageView image_poster;
    @BindView(R.id.txt_place_name)
    TextView txt_place_name;

    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.btn_cancel)
    Button btn_cancel;
    @BindView(R.id.btn_finish)
    Button btn_finish;
    @BindView(R.id.image_stamp)
    ImageView stampView;

    String rid;
    String rsvId;
    int status;
    Booking booking;
    String from;
    String showname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_booking);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        from = getIntent().getStringExtra("from");

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from.equals("SelectPayActivity")) {
                    Intent intent;
                    intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                } else {
                }
                finish();
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (from.equals("SelectPayActivity")) {
                    Intent intent;
                    intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                finish();
            }
        });

        initData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (from.equals("SelectPayActivity")) {
            Intent intent;
            intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        finish();
    }

    public void initData() {
        rid = BookingManager.getInstance().getRid();
        BookingDetailRequest request = new BookingDetailRequest(this, rid);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<BookingDetail>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<BookingDetail>> request, ResultsList<BookingDetail> result) {

                txt_play_name.setText(result.getResult().getPlayName());
                txt_play_day.setText(result.getResult().getPlayDay());
                txt_play_time.setText(result.getResult().getPlayTime());
                txt_place_name.setText(result.getResult().getPlaceName());
                txt_seat_class.setText(result.getResult().getSeatClass());
                txt_seat_info.setText(result.getResult().getSeatInfo());
                txt_settlement.setText(result.getResult().getSettlement() + "원");
                txt_reservation_no.setText(result.getResult().getReservationNo());
                Glide.with(image_poster.getContext())
                        .load(result.getResult().getPoster())
                        .into(image_poster);
                rsvId = "" + result.getResult().getRsvId();
                status = result.getResult().getStatus();
                showname = result.getResult().getPlayName();


                if (status == 0) {
                    txt_reservation_no.setText("예약 취소된 공연입니다");
                    btn_confirm.setVisibility(View.INVISIBLE);
                    btn_cancel.setVisibility(View.INVISIBLE);
                    btn_finish.setVisibility(View.VISIBLE);
                }
                String[] Day = result.getResult().getPlayDay().split("-");
                for (int i = 0; i < 3; i++) {
                    playDay[i] = Integer.parseInt(Day[i]);

                }
                if (status == 1) {
                    timeGap();
                }
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<BookingDetail>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckedBookingActivity.this, ShowCancelActivity.class);
                intent.putExtra("name", showname);
                startActivityForResult(intent, 0);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                if (from.equals("SelectPayActivity")) {
                    intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1 :
                BookingCancelRequest request = new BookingCancelRequest(MyApplication.getContext(), "" + rsvId);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<BookingCancel>() {
                    @Override
                    public void onSuccess(NetworkRequest<BookingCancel> request, BookingCancel result) {

                        status = 0;
                        BookingCancelCheckFinal dialog = new BookingCancelCheckFinal(CheckedBookingActivity.this);
                        dialog.show();

                        txt_reservation_no.setText("예약 취소된 공연입니다");
                        btn_confirm.setVisibility(View.INVISIBLE);
                        btn_cancel.setVisibility(View.INVISIBLE);
                        btn_finish.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFail(NetworkRequest<BookingCancel> request, int errorCode, String errorMessage, Throwable e) {

                    }
                });
                break;
            default:
                break;
        }
    }

    int[] nowTime = new int[3];
    int[] playDay = new int[3];

    private void timeGap() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy:MM:dd");
        String strNow = sdfNow.format(date);
        String[] strNowTime = strNow.split(":");

        for (int i = 0; i < 3; i++) {
            nowTime[i] = Integer.parseInt(strNowTime[i]);
        }

        for (int i = 0; i < 3; i++) {
            //비교해서 하나라도 얻어 걸리면 리뷰 ㄱㄱ
            if (nowTime[0] > playDay[0]) { //연 비교
                setStamp();
            } else if (nowTime[1] > playDay[1]) { //월 비교
                setStamp();
            } else if (nowTime[2] > playDay[2]) { //일 비교
                setStamp();
            } else {
            }
        }
    }
    public void setStamp() {
        stampView.setVisibility(View.VISIBLE);
        btn_confirm.setVisibility(View.INVISIBLE);
        btn_cancel.setVisibility(View.INVISIBLE);
        btn_finish.setVisibility(View.VISIBLE);
    }
}
