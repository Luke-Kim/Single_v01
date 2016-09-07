package com.tacademy.singleplay.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.EventNoticeDetail;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.EventNoticeDetailRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.notice_img)
    ImageView imageView;

//    EventNoticeDetail eventNoticeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void initData() {
        EventNoticeDetailRequest request = new EventNoticeDetailRequest(this, "1");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<EventNoticeDetail>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<EventNoticeDetail>> request, ResultsList<EventNoticeDetail> result) {
                Glide.with(imageView.getContext())
                        .load(result.getResult().getImage())  //string 이니까 Image 말고 그냥 getResult()
                        .into(imageView);

                Toast.makeText(EventDetailActivity.this, "성공", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFail(NetworkRequest<ResultsList<EventNoticeDetail>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(EventDetailActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
