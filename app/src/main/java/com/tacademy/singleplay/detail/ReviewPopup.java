package com.tacademy.singleplay.detail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
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
    @BindView(R.id.rank_dialog_text1)
    TextView titleView;
    @BindView(R.id.ratingBar2)
    RatingBar ratingBar;

    String playId;
    String playName;
    String score;
    public ReviewPopup(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.review_dialog);
        ButterKnife.bind(this);

        playId = ReviewManager.getInstance().getPlayId();
        playName = ReviewManager.getInstance().getPlayName();

        titleView.setText(playName);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                score = "" + ratingBar.getRating();
            }
        });
//        ratingBar.on
    }


    @OnClick(R.id.btn_starscore)
    public void clickScore(){

        StarScoreRequest request = new StarScoreRequest(MyApplication.getContext(), playId, playName, score);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<StarScore>() {
            @Override
            public void onSuccess(NetworkRequest<StarScore> request, StarScore result) {
                Toast.makeText(MyApplication.getContext(), "리뷰완료", Toast.LENGTH_SHORT).show();
                ReviewPopup.this.dismiss();
            }

            @Override
            public void onFail(NetworkRequest<StarScore> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}
