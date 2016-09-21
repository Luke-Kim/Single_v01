package com.tacademy.singleplay;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.data2.BookingListAdd;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.ShowDetail;
import com.tacademy.singleplay.data2.WishListDelete;
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
    @BindView(R.id.vip_call)
    TextView vip_call;
    @BindView(R.id.r_call)
    TextView r_call;
    @BindView(R.id.s_call)
    TextView s_call;

    CastingAdapter mAdapter;

//    SignInData signInData;

    boolean isCheckSet = false;
    String playId;
    int wishId;
    int uid;
    boolean isStart = true;
    int isWish;
    String theme;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_detail);
        ButterKnife.bind(this);
//        signInData = InsertPersonInfoActivity.signInData;

        mAdapter = new CastingAdapter(getLayoutInflater());
        pager.setAdapter(mAdapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        from = getIntent().getStringExtra("from");
        playId = BookingManager.getInstance().getPlayId();
        ShowDetailReqest reqest = new ShowDetailReqest(MyApplication.getContext(), playId);
        NetworkManager.getInstance().getNetworkData(reqest, new NetworkManager.OnResultListener<ResultsList<ShowDetail>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<ShowDetail>> request, ResultsList<ShowDetail> result) {
                uid = result.getResult().getUid();
                String[] cast = result.getResult().getCast();
                mAdapter.addAll(Arrays.asList(cast));
                playNameView.setText(result.getResult().getPlayName());
                theme = result.getResult().getTheme();
                switch (theme) {
                    case "뮤지컬": {
                        themeView.setBackgroundResource(R.drawable.contents_category_musical);
                        break;
                    }
                    case "오페라": {
                        themeView.setBackgroundResource(R.drawable.contents_category_opera);
                        break;
                    }
                    case "콘서트": {
                        themeView.setBackgroundResource(R.drawable.contents_category_concert);
                        break;
                    }
                }
//                themeView.setText(result.getResult().getTheme());
                placeView.setText(result.getResult().getPlaceName());
                dayView.setText(result.getResult().getPlayDay());
                timeView.setText(result.getResult().getPlayTime()); //배열이길래 [0]처리
                timeTwoView.setText(result.getResult().getPlayTime());
                vipPriceView.setText(result.getResult().getVIPprice() + "");
                vipPriceView.setPaintFlags(vipPriceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // 취소선 넣기
                vipSaleView.setText(result.getResult().getSaleVIPprice() + "");
                rPriceView.setText(result.getResult().getRprice() + "");
                rPriceView.setPaintFlags(rPriceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // 취소선 넣기
                rSaleView.setText(result.getResult().getSaleRprice() + "");
                sPriceView.setText(result.getResult().getSprice() + "");
                sPriceView.setPaintFlags(sPriceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);  // 취소선 넣기
                sSaleView.setText(result.getResult().getSaleSprice() + "");
                scoreView.setText(result.getResult().getStarScore() + "");
                reviewCountView.setText(result.getResult().getUserCount() + "");
                int[] list = result.getResult().getUsableSeat();
                vipEmptyView.setText(list[0] + "");
                if (list[0] == 0) {
                    vip_call.setText("좌석없음");
                    vipEmptyView.setTextColor(Color.WHITE);
                }
                rEmptyView.setText(list[1] + "");
                if (list[1] == 0) {
                    r_call.setText("좌석없음");
                    rEmptyView.setTextColor(Color.WHITE);
                }
                sEmptyView.setText(list[2] + "");
                if (list[2] == 0) {
                    r_call.setText("좌석없음");
                    sEmptyView.setTextColor(Color.WHITE);
                }
                Glide.with(MyApplication.getContext())
                        .load(result.getResult().getPoster()) //배열되있길래 [0]처리
                        .into(posterView);
                isWish = result.getResult().getIsWish();
                Toast.makeText(ShowDetailActivity.this, "위시 여부 : " + isWish, Toast.LENGTH_SHORT).show();
                if (isWish == 1) {
                    btn_wish.setChecked(true);
                }
                wishId = result.getResult().getWid();

                isStart = false;
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<ShowDetail>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

        Toast.makeText(ShowDetailActivity.this, "" + isWish, Toast.LENGTH_SHORT).show();
        btn_wish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (uid != 0) {
                    if (isStart == false) {
                        if (isChecked) {
                            Toast.makeText(ShowDetailActivity.this, "위시리스트 등록", Toast.LENGTH_SHORT).show();
                            Intent wish_intent = new Intent(ShowDetailActivity.this, WishPopupActivity.class);
                            wish_intent.putExtra("KEY_ISWISH", isWish);
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
                } else {
                    Toast.makeText(ShowDetailActivity.this, "로그인 후 사용해 주세요", Toast.LENGTH_SHORT).show();
                    btn_wish.setChecked(false);
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uid == 0) {
                    Toast.makeText(ShowDetailActivity.this, "회원가입 후 사용해주세요", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShowDetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } else {
                    BookingListAddRequest request = new BookingListAddRequest(MyApplication.getContext());
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<BookingListAdd>() {
                        @Override
                        public void onSuccess(NetworkRequest<BookingListAdd> request, BookingListAdd result) {
                            Toast.makeText(ShowDetailActivity.this, "성공" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFail(NetworkRequest<BookingListAdd> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(ShowDetailActivity.this, "실패" + errorCode + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(ShowDetailActivity.this, BookingPersonInfoActivity.class);
                    intent.putExtra("theme", theme);
                    startActivity(intent);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if (item.getItemId() == android.R.id.home) {
            if (TextUtils.isEmpty(from)) {
                intent = new Intent(ShowDetailActivity.this, MainActivity.class);
            } else if (from.equals("MainActivity")) {
                intent = new Intent(ShowDetailActivity.this, MainActivity.class);
            } else {
                intent = new Intent(ShowDetailActivity.this, WishListActivity.class);
            }
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
