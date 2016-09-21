package com.tacademy.singleplay.detail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.StarScore;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.manager.ReviewManager;
import com.tacademy.singleplay.request.StarScoreRequest;

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
        String playId = ReviewManager.getInstance().getPlayId();
        String playName = ReviewManager.getInstance().getPlayName();
        StarScoreRequest request = new StarScoreRequest(MyApplication.getContext(), playId, playName, "8.5");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<StarScore>() {
            @Override
            public void onSuccess(NetworkRequest<StarScore> request, StarScore result) {
                Toast.makeText(MyApplication.getContext(), "리뷰완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<StarScore> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}
