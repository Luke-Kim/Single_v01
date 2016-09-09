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

        toolbarTitleView.setText("서울");
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.setShowList(action, category, sort);

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();

        tabs.addTab(tabs.newTab().setText("전체"));
        tabs.addTab(tabs.newTab().setText("뮤지컬"));
        tabs.addTab(tabs.newTab().setText("오페라"));
        tabs.addTab(tabs.newTab().setText("콘서트"));
        tabs.addTab(tabs.newTab().setText("MD추천"));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                changeRadio(i);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                category = position;
                mAdapter.setShowList(action, category, sort);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioScore.setChecked(true);
    }

    FragmentManager ft = getSupportFragmentManager();
    LocationFragment lf = new LocationFragment();
    boolean isLocation = false;

    private static final int ACTION_SHOW = 0;

    private static final int SORT_SCORE = 0;
    private static final int SORT_NEW = 1;
    private static final int SORT_DISCOUNT = 2;

    public int action = ACTION_SHOW;
    public int category = 0;
    public int sort = SORT_SCORE;

    private void changeRadio(int id) {
        switch (id) {
            case R.id.radioButton_score :
                sort = SORT_SCORE;
                mAdapter.setShowList(action, category, sort);
                break;
            case R.id.radioButton_new :
                sort = SORT_NEW;
                mAdapter.setShowList(action, category, sort);
                break;
            case R.id.radioButton_discount :
                sort = SORT_DISCOUNT;
                mAdapter.setShowList(action, category, sort);
                break;
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.setShowList(action, category, sort);

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();

        tabs.addTab(tabs.newTab().setText("전체"));
        tabs.addTab(tabs.newTab().setText("뮤지컬"));
        tabs.addTab(tabs.newTab().setText("오페라"));
        tabs.addTab(tabs.newTab().setText("콘서트"));
        tabs.addTab(tabs.newTab().setText("MD추천"));
        pager.setCurrentItem(category);
//        Toast.makeText(MainActivity.this, ""+sort, Toast.LENGTH_SHORT).show();
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public int getCategory() {
        return category;
    }

    public int getSort() {
        return sort;
    }

    @OnClick(R.id.text_toolbarTitle)
    public void onToolbarTitle() {
//            startActivity(new Intent(MainActivity.this, LocationActivity.class));

        if (!isLocation) {
            isLocation = true;
            toolbarTitleView.setText("지역");
            detail.setVisible(false);
            wishlist.setVisible(false);
            back.setVisible(true);
            Toast.makeText(MainActivity.this, "click title", Toast.LENGTH_SHORT).show();
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
            toolbarTitleView.setText("서울");
            Toast.makeText(MainActivity.this, "click title 2", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MainActivity.this, "click title 3", Toast.LENGTH_SHORT).show();
            tabPagerContainer.setVisibility(View.VISIBLE);
            isLocation = false;
            ft.beginTransaction()
                    .replace(R.id.location_container, lf)
                    .remove(lf)
                    .commit();
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

    public void goDetailActivity(int playId, String playName) {
        BookingManager.getInstance().setPlayId(""+playId);
        BookingManager.getInstance().setPlayName(playName);
        Intent intent = new Intent(MainActivity.this, ShowDetailActivity.class);
        startActivity(intent);
    }
}