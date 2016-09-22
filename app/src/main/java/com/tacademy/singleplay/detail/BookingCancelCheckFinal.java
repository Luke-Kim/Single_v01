package com.tacademy.singleplay.detail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.tacademy.singleplay.R;

import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-22.
 */
public class BookingCancelCheckFinal extends Dialog {
    public BookingCancelCheckFinal(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cancel_check_final);
        ButterKnife.bind(this);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                BookingCancelCheckFinal.this.dismiss();
            }
        };
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}