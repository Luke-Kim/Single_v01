package com.tacademy.singleplay.detail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.EventNotice;
import com.tacademy.singleplay.data2.EventNoticeDetail;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.EventNoticeDetailRequest;
import com.tacademy.singleplay.request.EventNoticeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventNoticeActivity extends AppCompatActivity {
    EventNoticeAdapter eventNoticeAdapter;

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.rc_event)
    RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.image_thumbnail)
    ImageView image_thumbnail;
    @Nullable
    @BindView(R.id.txt_list)
    TextView txt_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_notice);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        eventNoticeAdapter = new EventNoticeAdapter();
        eventNoticeAdapter.setOnAdapterItemClickListener(new EventNoticeAdapter.OnEventNoticeAdapterItemClickLIstener() {
            @Override
            public void onEventNoticeAdapterItemClick(View view, EventNotice eventNotice, int position) {
                //화면이 먼저 디테일로 전환되는게 아니라 리퀘스트 요청하고 원하는 디테일 데이터를 다 받아와서 성공 되었을때 디테일 화면으로 전환 되어야한다!!
                String bid = eventNotice.getBoardNo() + "";
                EventNoticeDetailRequest request = new EventNoticeDetailRequest(EventNoticeActivity.this, bid);
                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<EventNoticeDetail>>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultsList<EventNoticeDetail>> request, ResultsList<EventNoticeDetail> result) {
                        Toast.makeText(EventNoticeActivity.this, "성공", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EventNoticeActivity.this, EventDetailActivity.class)); // 성공되면 디테일로 화면이 전환된다!!
                        finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultsList<EventNoticeDetail>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(EventNoticeActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(eventNoticeAdapter);
        recyclerView.setLayoutManager(manager);


//        recyclerView.setAdapter(new EventNoticeAdapter(getApplicationContext(), items, R.layout.activity_event_notice));

        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        EventNoticeRequest request = new EventNoticeRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<EventNotice[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<EventNotice[]>> request, ResultsList<EventNotice[]> result) {
                eventNoticeAdapter.addAll(result.getResults());
                Toast.makeText(EventNoticeActivity.this, "성공", Toast.LENGTH_SHORT).show();
                if (result != null) {

                }
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<EventNotice[]>> request, int errorCode, String errorMessage, Throwable e) {
                Log.e("error", request + " , " + errorCode + " , " + errorMessage);

                Toast.makeText(EventNoticeActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
