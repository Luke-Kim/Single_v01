package com.tacademy.singleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.data.SignInData;
import com.tacademy.singleplay.data2.BookingListAdd;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.ShowDetail;
import com.tacademy.singleplay.data2.WishListDelete;
import com.tacademy.singleplay.login.InsertPersonInfoActivity;
import com.tacademy.singleplay.login.LoginActivity;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.pay.BookingPersonInfoActivity;
import com.tacademy.singleplay.request.BookingListAddRequest;
import com.tacademy.singleplay.request.ShowDetailReqest;
import com.tacademy.singleplay.request.WishListDeletRequest;
import com.tacademy.singleplay.wishpopup.WishPopupActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDetailActivity extends AppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.castPager)
    ViewPager pager;
    @BindView(R.id.image_poster)
    ImageView posterView;
    @BindView(R.id.text_theme)
    TextView themeView;
    @BindView(R.id.text_play_name)
    TextView playNameView;
    @BindView(R.id.text_star_score)
    TextView scoreView;
    @BindView(R.id.text_review_cnt)
    TextView reviewCountView;
    @BindView(R.id.text_place)
    TextView placeView;
    @BindView(R.id.text_day)
    TextView dayView;
    @BindView(R.id.text_time)
    TextView timeView;
    @BindView(R.id.text_time_two)
    TextView timeTwoView;
    @BindView(R.id.text_vip_empty)
    TextView vipEmptyView;
    @BindView(R.id.text_r_empty)
    TextView rEmptyView;
    @BindView(R.id.text_s_empty)
    TextView sEmptyView;
    @BindView(R.id.text_vip_price)
    TextView vipPriceView;
    @BindView(R.id.text_vip_sale)
    TextView vipSaleView;
    @BindView(R.id.text_r_price)
    TextView rPriceView;
    @BindView(R.id.text_r_sale)
    TextView rSaleView;
    @BindView(R.id.text_s_price)
    TextView sPriceView;
    @BindView(R.id.text_s_sale)
    TextView sSaleView;
    @BindView(R.id.btn_wish)
    CheckBox btn_wish;
    @BindView(R.id.img_category)
    ImageView category_img;

    CastingAdapter mAdapter;

    SignInData signInData;

    boolean isCheckSet = false;
    String playId;
    int wishId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_detail);
        ButterKnife.bind(this);
        signInData = InsertPersonInfoActivity.signInData;

        mAdapter = new CastingAdapter(getLayoutInflater());
        pager.setAdapter(mAdapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        playId = BookingManager.getInstance().getPlayId();
        final ShowDetailReqest reqest = new ShowDetailReqest(MyApplication.getContext(), playId);
        NetworkManager.getInstance().getNetworkData(reqest, new NetworkManager.OnResultListener<ResultsList<ShowDetail>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<ShowDetail>> request, ResultsList<ShowDetail> result) {

                String[] cast = result.getResult().getCast();
                mAdapter.addAll(Arrays.asList(cast));
                playNameView.setText(result.getResult().getPlayName());
                themeView.setText(result.getResult().getTheme());
                placeView.setText(result.getResult().getPlaceName());
                dayView.setText(result.getResult().getPlayDay());
                timeView.setText(result.getResult().getPlayTime()); //배열이길래 [0]처리
                timeTwoView.setText(result.getResult().getPlayTime());
                vipPriceView.setText(result.getResult().getVIPprice() + "");
                vipSaleView.setText(result.getResult().getSaleVIPprice() + "");
                rPriceView.setText(result.getResult().getRprice() + "");
                rSaleView.setText(result.getResult().getSaleRprice() + "");
                sPriceView.setText(result.getResult().getSprice() + "");
                sSaleView.setText(result.getResult().getSaleSprice() + "");
                scoreView.setText(result.getResult().getStarScore() + "");
                reviewCountView.setText(result.getResult().getUserCount() + "");
                Glide.with(MyApplication.getContext())
                        .load(result.getResult().getPoster()) //배열되있길래 [0]처리
                        .into(posterView);
                if (result.getResult().getIsWish() == 1) {
                    btn_wish.setChecked(true);
                }
                wishId = result.getResult().getWid();
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<ShowDetail>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

        btn_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btn_wish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(ShowDetailActivity.this, "위시리스트 등록", Toast.LENGTH_SHORT).show();
                    Intent wish_intent = new Intent(ShowDetailActivity.this, WishPopupActivity.class);
                    wish_intent.putExtra(WishPopupActivity.EXTRA_PLAYID, playId);
                    startActivity(wish_intent);
                } else if (!isChecked) {
                    WishListDeletRequest request = new WishListDeletRequest(MyApplication.getContext(), wishId + "");
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<WishListDelete>() {
                        @Override
                        public void onSuccess(NetworkRequest<WishListDelete> request, WishListDelete result) {
                            Toast.makeText(ShowDetailActivity.this, "위시리스트 해제", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<WishListDelete> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(ShowDetailActivity.this, "위시리스트 해제 실패", Toast.LENGTH_SHORT).show();
                            btn_wish.setChecked(true);
                        }
                    });
                }
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signInData != null) {
                    BookingListAddRequest request = new BookingListAddRequest(MyApplication.getContext());
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<BookingListAdd>() {
                        @Override
                        public void onSuccess(NetworkRequest<BookingListAdd> request, BookingListAdd result) {
                            Toast.makeText(ShowDetailActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<BookingListAdd> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(ShowDetailActivity.this, "실패" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(ShowDetailActivity.this, BookingPersonInfoActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    Toast.makeText(ShowDetailActivity.this, "회원가입 후 사용해주세요", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShowDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ShowDetailActivity.this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
