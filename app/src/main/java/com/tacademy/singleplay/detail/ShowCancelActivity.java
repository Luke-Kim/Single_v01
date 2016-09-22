package com.tacademy.singleplay.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tacademy.singleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowCancelActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.showname)
    TextView showname_View;
    @BindView(R.id.cancelBtn)
    Button cancelBtn;
    @BindView(R.id.btn_no)
    Button btn_no;
    int code = 1;
    Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cancel);
        ButterKnife.bind(this);
        setFinishOnTouchOutside(true);
        display = ShowCancelActivity.this.getWindowManager().getDefaultDisplay();

        showname_View.setText(getIntent().getStringExtra("name"));
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowCancelActivity.this, CheckedBookingActivity.class);
                intent.putExtra("code", 1);
                setResult(1, intent);
                finish();
            }
        });
        btn_no.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                finish();
                return false;
            }
        });
    }
}
