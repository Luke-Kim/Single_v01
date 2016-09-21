package com.tacademy.singleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.detail.UserActivity;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.ShowListManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text_toolbarTitle)
    TextView toolbarTitleView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.location_container)
    FrameLayout locationContainer;
    @BindView(R.id.tab_pager_container)
    LinearLayout tabPagerContainer;

    @BindView(R.id.view_radiogroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButton_score)
    RadioButton radioScore;
    @BindView(R.id.radioButton_new)
    RadioButton radioNew;
    @BindView(R.id.radioButton_discount)
    RadioButton radiodiscount;

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;
    FragmentPagerAdapter mAdapter;

    @BindView(R.id.search_container)
    LinearLayout searchContainer;

    MenuItem detail, wishlist, back;
    private BackPressedCloseHandler backPressedCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        backPressedCloseHandler = new BackPressedCloseHandler(this);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager());

        ShowListManager.getInstance().setSort("0");
        ShowListManager.getInstance().setPosition(0);

        String test = "17:50";
        String[] testAraay = test.split(":");
        Toast.makeText(MainActivity.this, "시간 : " + testAraay[0] + ",  분 : " + testAraay[1], Toast.LENGTH_SHORT).show();

        setPager();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton_score:
                        ShowListManager.getInstance().setSort("0");
                        break;
                    case R.id.radioButton_new:
                        ShowListManager.getInstance().setSort("1");
                        break;
                    case R.id.radioButton_discount:
                        ShowListManager.getInstance().setSort("2");
                        break;
                }
                setPager();

            }
        });

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                ShowListManager.getInstance().setPosition(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.search_iocn);

        radioScore.setChecked(true);
    }

    FragmentManager ft = getSupportFragmentManager();
    LocationFragment lf = new LocationFragment();
    boolean isLocation = false;


    @OnClick(R.id.text_toolbarTitle)
    public void onToolbarTitle() {
//            startActivity(new Intent(MainActivity.this, LocationActivity.class));

        if (!isLocation) {
            isLocation = true;
            detail.setVisible(false);
            wishlist.setVisible(false);
            back.setVisible(true);
            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_up_in);
            ft.beginTransaction()
                    .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                    .replace(R.id.location_container, lf)
                    .addToBackStack("Back Stack")
                    .commit();

        } else {
            isLocation = false;
            detail.setVisible(true);
            wishlist.setVisible(true);
            back.setVisible(false);
            ft.beginTransaction()
                    .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                    .remove(lf)
                    .commit();
        }
    }

    SearchFragment sf = new SearchFragment();
    @OnClick(R.id.btn_search)
    public void onSearch() {
        ft.beginTransaction()
                .replace(R.id.location_container, sf)
                .addToBackStack("Back Stack")
                .commit();
    }

    @OnClick(R.id.btn_search_cancel)
    public void onSearchCancel() {
        toolbar.setVisibility(View.VISIBLE);
        searchContainer.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (isLocation) {
            detail.setVisible(true);
            wishlist.setVisible(true);
            back.setVisible(false);
            tabPagerContainer.setVisibility(View.VISIBLE);
            isLocation = false;
            ft.beginTransaction()
                    .replace(R.id.location_container, lf)
                    .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                    .remove(lf)
                    .commit();
            return;
        }
        if (isSearch) {
            toolbar.setVisibility(View.VISIBLE);
            searchContainer.setVisibility(View.GONE);
        }
        backPressedCloseHandler.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        detail = menu.findItem(R.id.menu_detail);
        wishlist = menu.findItem(R.id.menu_wishlist);
        back = menu.findItem(R.id.menu_finish);
        return true;
    }

    boolean isSearch = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_detail: {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                break;
            }
            case android.R.id.home: {
                if (!isSearch) {
                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    break;
                }
            }
            case R.id.menu_wishlist: {
                startActivity(new Intent(MainActivity.this, WishListActivity.class));
                break;
            }
            case R.id.menu_finish: {
                isLocation = false;
                detail.setVisible(true);
                wishlist.setVisible(true);
                back.setVisible(false);
                Toast.makeText(MainActivity.this, "click title 4", Toast.LENGTH_SHORT).show();
                ft.beginTransaction()
                        .setCustomAnimations(R.anim.slide_up_in, R.anim.slide_up_out)
                        .remove(lf)
                        .commit();
            }
        }
        return true;
    }

    public void setPager() {
        int posion = ShowListManager.getInstance().getPosition();
        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.removeAllTabs();
        tabs.addTab(tabs.newTab().setText("전체"));
        tabs.addTab(tabs.newTab().setText("뮤지컬"));
        tabs.addTab(tabs.newTab().setText("오페라"));
        tabs.addTab(tabs.newTab().setText("콘서트"));
        tabs.addTab(tabs.newTab().setText("MD추천"));
        pager.setCurrentItem(posion);
    }

    public void goDetailActivity(int playId, String playName) {
        BookingManager.getInstance().setPlayId(""+playId);
        BookingManager.getInstance().setPlayName(playName);
        Intent intent = new Intent(MainActivity.this, ShowDetailActivity.class);
        intent.putExtra("from", "MainActivity");
        startActivity(intent);
    }

    public void goLocationResultActivity(String location) {
        Intent intent = new Intent(MainActivity.this, LocationResultActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }
}