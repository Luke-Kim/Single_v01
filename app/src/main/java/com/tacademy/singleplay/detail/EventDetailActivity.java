package com.tacademy.singleplay.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.EventNotice;
import com.tacademy.singleplay.data2.EventNoticeDetail;
import com.tacademy.singleplay.data2.ResultsList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.notice_img)
    ImageView imageView;

    EventNoticeAdapter mAdapter;


    ResultsList<EventNoticeDetail> resultsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        mAdapter = new EventNoticeAdapter();
        EventNoticeDetail intent = (EventNoticeDetail)getIntent().getSerializableExtra("result");
        Glide.with(imageView.getContext())
                .load(intent.getImage())  //string 이니까 Image 말고 그냥 getResult()
                .into(imageView);

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
        mAdapter.setOnAdapterItemClickListener(new EventNoticeAdapter.OnEventNoticeAdapterItemClickLIstener() {
            @Override
            public void onEventNoticeAdapterItemClick(View view, EventNotice eventNotice, int position) {

            }
        });


    }
}
