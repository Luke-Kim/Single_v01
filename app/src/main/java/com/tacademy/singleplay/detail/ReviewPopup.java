package com.tacademy.singleplay.detail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.tacademy.singleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class ReviewPopup extends Dialog {

    @BindView(R.id.btn_starscore)
    Button btn_starscore;

    public ReviewPopup(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.review_dialog);
        ButterKnife.bind(this);

//        btn_starscore = (Button)findViewById(R.id.btn_starscore);



//        btn_starscore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // request
//            }
//        });
    }
    @OnClick(R.id.btn_starscore)
    public void clickScore(){

    }
}
