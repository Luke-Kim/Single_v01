package com.tacademy.singleplay.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tacademy.singleplay.inquirydetail.InquiryPagerAdapter;
import com.tacademy.singleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InquiryActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @Nullable @BindView(R.id.rc_event)
    RecyclerView recyclerView;
//
//    @BindView(R.id.tabs)
//    TabLayout tabs;

    @BindView(R.id.inquiry_tabs)
    TabLayout inquiry_tabs;

    @Nullable@BindView(R.id.pager)
    ViewPager pager;


    InquiryPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);

        ButterKnife.bind(this);
//        tabs = (TabLayout)findViewById(R.id.inquiry_tabs);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAdapter = new InquiryPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mAdapter);
        inquiry_tabs.setupWithViewPager(pager);
        inquiry_tabs.removeAllTabs();

        inquiry_tabs.addTab(inquiry_tabs.newTab().setText("자주묻는 질문"));
        inquiry_tabs.addTab(inquiry_tabs.newTab().setText("결제/취소"));
        inquiry_tabs.addTab(inquiry_tabs.newTab().setText("로그인/계정"));
        inquiry_tabs.addTab(inquiry_tabs.newTab().setText("기타"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
