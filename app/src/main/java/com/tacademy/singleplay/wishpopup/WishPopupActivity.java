package com.tacademy.singleplay.wishpopup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.WishListAddRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishPopupActivity extends AppCompatActivity {

    @BindView(R.id.rcv_wish_pop)
    RecyclerView rcv;

    WishPopAdater mAdapter;
    public static final String EXTRA_PLAYID = "play id";
    int playId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_popup);
        ButterKnife.bind(this);

        Intent intent = new Intent();
        playId = intent.getIntExtra(EXTRA_PLAYID, 0);

        setFinishOnTouchOutside(true);
        this.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new WishPopAdater();


        rcv.setAdapter(mAdapter);
        rcv.setLayoutManager(lm);
        rcv.setHasFixedSize(true);

        initData();
    }

    private void initData() {
        WishListAddRequest request = new WishListAddRequest(this, EXTRA_PLAYID);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<String[]>>() {
                    @Override
                    public void onSuccess(NetworkRequest<ResultsList<String[]>> request, ResultsList<String[]> result) {
                        mAdapter.addAll(result.getResults());
                    }

                    @Override
                    public void onFail(NetworkRequest<ResultsList<String[]>> request, int errorCode, String errorMessage, Throwable e) {

                    }
                }
        );
    }
}
