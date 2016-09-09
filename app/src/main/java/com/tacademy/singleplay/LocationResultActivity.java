package com.tacademy.singleplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.tacademy.singleplay.data2.Location;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.LocationRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationResultActivity extends AppCompatActivity {

    @BindView(R.id.location_rc)
    RecyclerView listView;

    LocationAdapter mAdapter;

    private static final String ACTION = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_result);
        ButterKnife.bind(this);

        mAdapter = new LocationAdapter();
        listView.setAdapter(mAdapter);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        initData(location);
    }

    private void initData(String location) {
                LocationRequest request = new LocationRequest(MyApplication.getContext(), ACTION, location);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Location[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Location[]>> request, ResultsList<Location[]> result) {
                Toast.makeText(MyApplication.getContext(), "지역검색 성공", Toast.LENGTH_SHORT).show();
                Location[] datas = result.getResults();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Location[]>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MyApplication.getContext(), "지역검색 실패", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
