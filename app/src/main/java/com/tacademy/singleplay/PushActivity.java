package com.tacademy.singleplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PushActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.push_notice_switch)
    Switch switch_notice;

    @BindView(R.id.btn_check_day)
    CheckBox btn_check_day;
    @BindView(R.id.btn_check_category)
    CheckBox btn_check_category;

    @BindView(R.id.checkBox_mon)
    CheckBox checkBox_mon;
    @BindView(R.id.checkBox_tue)
    CheckBox checkBox_tue;
    @BindView(R.id.checkBox_wed)
    CheckBox checkBox_wed;
    @BindView(R.id.checkBox_thur)
    CheckBox checkBox_thur;
    @BindView(R.id.checkBox_fri)
    CheckBox checkBox_fri;
    @BindView(R.id.checkBox_sat)
    CheckBox checkBox_sat;
    @BindView(R.id.checkBox_sun)
    CheckBox checkBox_sun;

    @BindView(R.id.checkBox_musical)
    CheckBox checkBox_musical;
    @BindView(R.id.checkBox_opera)
    CheckBox checkBox_opera;
    @BindView(R.id.checkBox_concert)
    CheckBox checkBox_concert;



    //CheckBox checkView;
    boolean isForced = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        checkView = (CheckBox)findViewById(R.id.checkBox_mon);
//
//        checkView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                boolean checked = checkView.isChecked();
//                if(isForced) {
//                    checkView.setChecked(true);
//                }
//            }
//        }

        btn_check_day.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(btn_check_day.isChecked()) {
//                    boolean checked = btn_check_day.isChecked();
                    isForced = true;
                    checkBox_mon.setChecked(isForced);
                    checkBox_tue.setChecked(isForced);
                    checkBox_wed.setChecked(isForced);
                    checkBox_thur.setChecked(isForced);
                    checkBox_fri.setChecked(isForced);
                    checkBox_sat.setChecked(isForced);
                    checkBox_sun.setChecked(isForced);
                    switch_notice.setChecked(isForced);
                    isForced = false;
                } else {
//                    boolean checked = btn_check_day.isChecked();
                    checkBox_mon.setChecked(isForced);
                    checkBox_tue.setChecked(isForced);
                    checkBox_wed.setChecked(isForced);
                    checkBox_thur.setChecked(isForced);
                    checkBox_fri.setChecked(isForced);
                    checkBox_sat.setChecked(isForced);
                    checkBox_sun.setChecked(isForced);
                }
            }
        });

        btn_check_category.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(btn_check_category.isChecked()) {
                    isForced = true;
                    checkBox_musical.setChecked(isForced);
                    checkBox_opera.setChecked(isForced);
                    checkBox_concert.setChecked(isForced);
                    switch_notice.setChecked(isChecked);
                    isForced = false;
                } else {
                    checkBox_musical.setChecked(isForced);
                    checkBox_opera.setChecked(isForced);
                    checkBox_concert.setChecked(isForced);
                }
            }
        });

    }





//    @OnClick(R.id.btn_check_day)
//    public void onCheckDay() {
//        if(btn_check_day.isChecked()) {
//
//        } else {
//
//        }
//
//    }
//
//
//
//
//    @OnClick(R.id.btn_check_category)
//    public void onCheckCategory() {
//
//    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
