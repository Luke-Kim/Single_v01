package com.tacademy.singleplay.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.BookingList;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.BookingListRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingListActivity extends AppCompatActivity {

    BookingListAdapter bookingListAdapter;

    @BindView(R.id.my_toolbar)
    Toolbar my_toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);
        ButterKnife.bind(this);

        setSupportActionBar(my_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookingListAdapter = new BookingListAdapter();
        bookingListAdapter.setOnAdapterItemClickListener(new BookingListAdapter.OnBookingAdapterItemClickLIstener() {
            @Override
            public void onBookingAdapterItemClick(View view, BookingList bookingList, int position) {
                BookingManager.getInstance().setRid("" + bookingList.getRsvId());
                Intent intent = new Intent(BookingListActivity.this, CheckedBookingActivity.class);
                intent.putExtra("from", "BookingActivity");
                startActivity(intent);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bookingListAdapter);

        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        BookingListRequest request = new BookingListRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<BookingList[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<BookingList[]>> request, ResultsList<BookingList[]> result) {

                bookingListAdapter.addAll(result.getResults());
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<BookingList[]>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}