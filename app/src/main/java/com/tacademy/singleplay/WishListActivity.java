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

import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.WishList;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.WishListRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishListActivity extends AppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.wishList_rv)
    RecyclerView recyclerView;
    @BindView(R.id.no_wish)
    ImageView no_wish;
    @BindView(R.id.txt_no_wish)
    TextView txt_no_wish;

    WishListAdapter wishListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        wishListAdapter = new WishListAdapter();
        wishListAdapter.setOnWishAdapterItemCLickListener(new WishListAdapter.OnWishAdapterItemClickListener() {
            @Override
            public void onWishAdapterItemClick(View view, WishList wishList, int position) {
                int playId = wishList.getPlayId();
                String playName = wishList.getPlayName();
                goDetailActivity(playId, playName);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(wishListAdapter);
            }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initData();
    }

    public void goDetailActivity(int playId, String playName) {
        BookingManager.getInstance().setPlayId("" + playId);
        BookingManager.getInstance().setPlayName(playName);
        Intent intent = new Intent(WishListActivity.this, ShowDetailActivity.class);
        intent.putExtra("from", "WishListActivity");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        WishListRequest request = new WishListRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<WishList[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<WishList[]>> request, ResultsList<WishList[]> result) {

                wishListAdapter.clear();
                wishListAdapter.addAll(result.getResults());

                int wishItemCNT = wishListAdapter.getItemCount();

                if (wishItemCNT == 0) {
                    txt_no_wish.setVisibility(View.VISIBLE);
                    no_wish.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onFail(NetworkRequest<ResultsList<WishList[]>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}
