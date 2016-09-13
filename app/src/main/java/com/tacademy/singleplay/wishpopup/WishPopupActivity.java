package com.tacademy.singleplay.wishpopup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.widget.Toast;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.WishListAddRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishPopupActivity extends AppCompatActivity {

    @BindView(R.id.rcv_wish_pop)
    RecyclerView rcv;
//    @BindView(R.id.wish_popup_layout)
//    RelativeLayout wish_popup_layout;

    WishPopAdater mAdapter;
//    public static final String EXTRA_PLAYID = "play id";
    String playId;
    int isWish;

    private Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_popup);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        playId = intent.getIntExtra(EXTRA_PLAYID, 0);
        playId = BookingManager.getInstance().getPlayId();

        display = WishPopupActivity.this.getWindowManager().getDefaultDisplay();

        isWish = getIntent().getIntExtra("KEY_ISWISH", -1);

        setFinishOnTouchOutside(true);
        this.getWindow().setGravity(Gravity.BOTTOM);
        this.getWindow().setLayout(display.getWidth(), 450);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new WishPopAdater();


        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(layoutManager);
        rcv.setHasFixedSize(true);

        initData();
    }

    private void initData() {
        WishListAddRequest request = new WishListAddRequest(this, playId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<String[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<String[]>> request, ResultsList<String[]> result) {
                if (isWish == 0) {
                    mAdapter.addAll(result.getResults());
                } else  {
                    String errorMessage = result.getError();
                    Toast.makeText(WishPopupActivity.this, ""+errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<String[]>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(WishPopupActivity.this, "실패"+errorCode+errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
