package com.tacademy.singleplay.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.StarScore;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.StarScoreRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarScoreActivity extends AppCompatActivity {

    @BindView(R.id.txt_theme)
    TextView txt_theme;
    @BindView(R.id.txt_show_title)
    TextView txt_show_title;
    @BindView(R.id.btn_send_starscore)
    Button send_starscore;

    public static final String EXTRA_PLAYID = "play id";
    int playId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_score);
        ButterKnife.bind(this);

        Intent intent = new Intent();
        playId = intent.getIntExtra(EXTRA_PLAYID, 0);

        setFinishOnTouchOutside(true);
        this.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER);

        initData();
    }

    public void initData() {
        StarScoreRequest request = new StarScoreRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<StarScore>() {
            @Override
            public void onSuccess(NetworkRequest<StarScore> request, StarScore result) {
                Toast.makeText(StarScoreActivity.this, "성공"+result.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<StarScore> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(StarScoreActivity.this, "실패"+errorCode+errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
