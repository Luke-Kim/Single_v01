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
import android.widget.Toast;

import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.Search;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.SearchRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_toolbar)
    TextView titleView;
    @BindView(R.id.search_rc)
    RecyclerView listView;
    @BindView(R.id.no_search)
    ImageView noSearchView;
    @BindView(R.id.txt_no_search)
    TextView noSearchMessage;

    SearchAdapter mAdapter;

    public static final String TITLE = "title";
    public static final String ACTION = "2";
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result2);
        ButterKnife.bind(this);

//        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        Intent intent = getIntent();
        title = intent.getStringExtra(TITLE);
        titleView.setText(title);

//        Toast.makeText(SearchResultActivity.this, title, Toast.LENGTH_SHORT).show();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new SearchAdapter();
        listView.setAdapter(mAdapter);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(layoutManager);

        init();
    }


    public void init() {
//        FragmentTransaction ft = getSupportFragmentManager()
//                .beginTransaction();
//                ft.replace(R.id.search_container, SearchFragment.newInstance(title))
//                .commit();
        SearchRequest request = new SearchRequest(MyApplication.getContext(), ACTION, title);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Search[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Search[]>> request, ResultsList<Search[]> result) {

                Search[] datas = result.getResults();
                mAdapter.clear();
                mAdapter.addAll(datas);
                int cnt = mAdapter.getItemCount();
                if (cnt == 0) {
                    noSearchView.setVisibility(View.VISIBLE);
                    noSearchMessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Search[]>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(SearchResultActivity.this, "finish", Toast.LENGTH_SHORT).show();
            finish();
        }
        return true;
    }

    public void goDetailActivity(int playId, String playName) {
        BookingManager.getInstance().setPlayId("" + playId);
        BookingManager.getInstance().setPlayName(playName);
        Intent intent = new Intent(SearchResultActivity.this, ShowDetailActivity.class);
        startActivity(intent);
    }
}
