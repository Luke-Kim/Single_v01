package com.tacademy.singleplay;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.tacademy.singleplay.data2.Push;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.UserInfo;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.manager.PropertyManager;
import com.tacademy.singleplay.manager.UserInfoManager;
import com.tacademy.singleplay.request.PushRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    CheckBox[] weekView = new CheckBox[7];
    CheckBox[] themeView = new CheckBox[3];

    String[] isDay = new String[7];
    String[] isTheme = new String[3];

    boolean isForced = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        ButterKnife.bind(this);

        weekView[0] = checkBox_mon;
        weekView[1] = checkBox_tue;
        weekView[2] = checkBox_wed;
        weekView[3] = checkBox_thur;
        weekView[4] = checkBox_fri;
        weekView[5] = checkBox_sat;
        weekView[6] = checkBox_sun;

        themeView[0] = checkBox_musical;
        themeView[1] = checkBox_opera;
        themeView[2] = checkBox_concert;

        if (PropertyManager.getInstance().isCheckLogin()) {
            for (int i = 0; i < 7; i++) {
                isDay[i] = "" + UserInfoManager.getInstance().getDay()[i];
            }
            for (int i = 0; i < 3; i++) {
                isTheme[i] = "" + UserInfoManager.getInstance().getTheme()[i];
            }


            for (int i = 0; i < 7; i++) {
                if (isDay[i] == "1") {
                    weekView[i].setChecked(true);
                }
            }
            for (int i = 0; i < 3; i++) {
                if (isTheme[i] == "1") {
                    themeView[i].setChecked(true);
                }
            }
        }

        Toast.makeText(PushActivity.this, "" + isDay[0] + isDay[1] + isDay[2] + isDay[3] + isDay[4] + isDay[5] + isDay[6], Toast.LENGTH_SHORT).show();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn_check_day.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (btn_check_day.isChecked()) {
                    isForced = true;
                    checkBox_mon.setChecked(isForced);
                    checkBox_tue.setChecked(isForced);
                    checkBox_wed.setChecked(isForced);
                    checkBox_thur.setChecked(isForced);
                    checkBox_fri.setChecked(isForced);
                    checkBox_sat.setChecked(isForced);
                    checkBox_sun.setChecked(isForced);
                    switch_notice.setChecked(isForced);
                    for (int i = 0; i < 7; i++) {
                        isDay[i] = "1";
                    }
                    isForced = false;
                } else {
                    checkBox_mon.setChecked(isForced);
                    checkBox_tue.setChecked(isForced);
                    checkBox_wed.setChecked(isForced);
                    checkBox_thur.setChecked(isForced);
                    checkBox_fri.setChecked(isForced);
                    checkBox_sat.setChecked(isForced);
                    checkBox_sun.setChecked(isForced);
                    for (int i = 0; i < 7; i++) {
                        isDay[i] = "0";
                    }
                }
            }
        });

        btn_check_category.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (btn_check_category.isChecked()) {
                    isForced = true;
                    checkBox_musical.setChecked(isForced);
                    checkBox_opera.setChecked(isForced);
                    checkBox_concert.setChecked(isForced);
                    switch_notice.setChecked(isChecked);
                    for (int i = 0; i < 3; i++) {
                        isTheme[i] = "1";
                    }
                    isForced = false;
                } else {
                    checkBox_musical.setChecked(isForced);
                    checkBox_opera.setChecked(isForced);
                    checkBox_concert.setChecked(isForced);
                    for (int i = 0; i < 3; i++) {
                        isTheme[i] = "0";
                    }
                }
            }
        });

        switch_notice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String noti;
                if (b) {
                    noti = "on";
                } else {
                    noti = "off";
                }
                if (PropertyManager.getInstance().isCheckLogin()) {
                    PushRequest request = new PushRequest(MyApplication.getContext(), noti, isDay, isTheme);
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Push>() {
                        @Override
                        public void onSuccess(NetworkRequest<Push> request, Push result) {
                            int[] day = new int[7];
                            int[] theme = new int[3];
                            for (int i = 0; i < 7; i++) {
                                day[i] = Integer.parseInt(isDay[i]);
                            }
                            for (int i = 0; i < 3; i++) {
                                theme[i] = Integer.parseInt(isTheme[i]);
                            }
                            UserInfoManager.getInstance().setDay(day);
                            UserInfoManager.getInstance().setTheme(theme);
                            Toast.makeText(PushActivity.this, "알람설정 성공", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<Push> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(PushActivity.this, "알람설정 실패 : " + errorCode, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(PushActivity.this, "로그인후 사용해주삼", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBox_mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[0] = "1";
                } else {
                    isDay[0] = "0";
                }
            }
        });

        checkBox_tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[1] = "1";
                } else {
                    isDay[1] = "0";
                }
            }
        });

        checkBox_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[2] = "1";
                } else {
                    isDay[2] = "0";
                }
            }
        });

        checkBox_thur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[3] = "1";
                } else {
                    isDay[3] = "0";
                }
            }
        });

        checkBox_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[4] = "1";
                } else {
                    isDay[4] = "0";
                }
            }
        });

        checkBox_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[5] = "1";
                } else {
                    isDay[5] = "0";
                }
            }
        });

        checkBox_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isDay[6] = "1";
                } else {
                    isDay[6] = "0";
                }
            }
        });

        checkBox_musical.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isTheme[0] = "1";
                } else {
                    isTheme[0] = "0";
                }
            }
        });

        checkBox_opera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isTheme[1] = "1";
                } else {
                    isTheme[1] = "0";
                }
            }
        });

        checkBox_concert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isTheme[1] = "1";
                } else {
                    isTheme[1] = "0";
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
