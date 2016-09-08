package com.tacademy.singleplay;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.manager.BookingManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.text_title)
    TextView titleView;

    public static final String TITLE = "title";
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result2);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        Intent intent = getIntent();
        title = intent.getStringExtra(TITLE);
        titleView.setText(title);
        Toast.makeText(SearchResultActivity.this, title, Toast.LENGTH_SHORT).show();

        init();
    }


    public void init() {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
                ft.replace(R.id.search_container, SearchFragment.newInstance(title))
                .commit();
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
        BookingManager.getInstance().setPlayId(""+playId);
        BookingManager.getInstance().setPlayName(playName);
        Intent intent = new Intent(SearchResultActivity.this, ShowDetailActivity.class);
        startActivity(intent);
    }
}
