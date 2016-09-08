package com.tacademy.singleplay.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.BookingCancel;
import com.tacademy.singleplay.data2.BookingDetail;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.BookingCancelRequest;
import com.tacademy.singleplay.request.BookingDetailRequest;

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

    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.btn_cancel)
    Button btn_cancel;
    @BindView(R.id.btn_finish)
    Button btn_finish;
//    @BindView(R.id.txt_reservation_no)
//    TextView txt_reservation_no;

    int rsvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_booking);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSimple();
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

        initData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }

    public void initData() {

        BookingDetailRequest request = new BookingDetailRequest(this, "8");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<BookingDetail>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<BookingDetail>> request, ResultsList<BookingDetail> result) {
                Toast.makeText(CheckedBookingActivity.this, "성공", Toast.LENGTH_SHORT).show();

                txt_play_name.setText(result.getResult().getPlayName());
                txt_play_day.setText(result.getResult().getPlayDay());
                txt_play_time.setText(result.getResult().getPlayTime());
                txt_seat_class.setText(result.getResult().getSeatClass());
                txt_seat_info.setText(result.getResult().getSeatInfo());
                txt_settlement.setText(result.getResult().getSettlement()+"");
                txt_reservation_no.setText(result.getResult().getReservationNo());
                Glide.with(image_poster.getContext())
                        .load(result.getResult().getPoster())
                        .into(image_poster);
                rsvId = result.getResult().getRsvId();
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<BookingDetail>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(CheckedBookingActivity.this, "실패"+errorCode+errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                intent = new Intent(CheckedBookingActivity.this, MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            case R.id.detail_menu:
                intent = new Intent(CheckedBookingActivity.this, UserActivity.class);
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

    private void DialogSimple() {
        AlertDialog.Builder simpleDialog = new AlertDialog.Builder(this);
        simpleDialog.setMessage("예매를 취소하시겠습니까?");
        simpleDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CheckedBookingActivity.this, "예매가 취소되었습니다", Toast.LENGTH_SHORT).show();
                BookingCancelRequest request = new BookingCancelRequest(MyApplication.getContext(), ""+rsvId);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<BookingCancel>() {
                    @Override
                    public void onSuccess(NetworkRequest<BookingCancel> request, BookingCancel result) {
                        Toast.makeText(CheckedBookingActivity.this, "성공", Toast.LENGTH_SHORT).show();

                        txt_reservation_no.setText("예약 취소된 공연입니다");
                        btn_confirm.setVisibility(View.INVISIBLE);
                        btn_cancel.setVisibility(View.INVISIBLE);
                        btn_finish.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFail(NetworkRequest<BookingCancel> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(CheckedBookingActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        simpleDialog.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(CheckedBookingActivity.this, "취소되지 않았습니다", Toast.LENGTH_SHORT).show();
            }
        });
        simpleDialog.setCancelable(false);
        AlertDialog alert = simpleDialog.create();
        alert.setTitle("예매취소");
        dialog = simpleDialog.create();
        dialog.show();
    }

    AlertDialog dialog;
}
