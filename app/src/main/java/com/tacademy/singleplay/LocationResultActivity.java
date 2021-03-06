package com.tacademy.singleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.singleplay.data2.Location;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.LocationRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationResultActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar)
    Toolbar my_toolbar;
    @BindView(R.id.location_rc)
    RecyclerView recyclerView;
    @BindView(R.id.location_toolbar_name)
    TextView location_toolbar_name;
    @BindView(R.id.no_search)
    ImageView noSearchView;
    @BindView(R.id.txt_no_search)
    TextView noSearchMessage;

    LocationAdapter locationAdapter;

    private static final String ACTION = "1";

    String locationTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_result);
        ButterKnife.bind(this);

        locationTitle = getIntent().getStringExtra("location");

        location_toolbar_name.setText(locationTitle);

        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        locationAdapter = new LocationAdapter();
        recyclerView.setAdapter(locationAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        initData(location);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData(String location) {
                LocationRequest request = new LocationRequest(MyApplication.getContext(), ACTION, location);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Location[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Location[]>> request, ResultsList<Location[]> result) {

                Location[] datas = result.getResults();
                locationAdapter.clear();
                locationAdapter.addAll(datas);
                int cnt = locationAdapter.getItemCount();
                if(cnt == 0) {
                    noSearchView.setVisibility(View.VISIBLE);
                    noSearchMessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Location[]>> request, int errorCode, String errorMessage, Throwable e) {


            }
        });
    }


}
