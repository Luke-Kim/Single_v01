package com.tacademy.singleplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {

    @BindView(R.id.grid_seoul)
    android.support.v7.widget.GridLayout seoulView;

    private static final String ACTION = "1";
    LocationAdapter mAdapter;

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);
        mAdapter = new LocationAdapter();
        return view;
    }

    boolean isCheckSeoul = false;
    @OnClick(R.id.image_seoul)
    public void onSeoul() {
        if (!isCheckSeoul) {
            seoulView.setVisibility(View.VISIBLE);
            isCheckSeoul = true;
        } else {
            seoulView.setVisibility(View.GONE);
            isCheckSeoul = false;
        }
    }

    @OnClick(R.id.seoul_total) //서울전체
    public void onTotal() {
        setIntent("서울시");
    }

    @OnClick(R.id.seoul_gangnam) //강남구/송파구
    public void onGangnam() {
        setIntent("강남구/송파구");
    }

    @OnClick(R.id.seoul_seocho) //서초구/관악구/동작구
    public void onSeocho() {
        setIntent("서초구/관악구/동작구");
    }

    @OnClick(R.id.seoul_junggu) //중구/동대문구/성동구
    public void onJunggu() {
        setIntent("중구/동대문구/성동구");
    }

    @OnClick(R.id.seoul_jonglo) //종로구
    public void onJonglo() {
        setIntent("종로구");
    }

    @OnClick(R.id.seoul_mafo) //마포구/서대문구/용산구
    public void onMafo() {
        setIntent("마포구/서대문구/용산구");
    }

    @OnClick(R.id.seoul_youngdengfo) //영등포구/강서구/양천구
    public void Youngdengfo() {
        setIntent("영등포구/강서구/양천구");
    }

    @OnClick(R.id.seoul_guro) //구로구/금천구
    public void onGuro() {
        setIntent("구로구/금천구");
    }

    @OnClick(R.id.seoul_gangbuck) //강북구/성북구/노원구
    public void onGangbuck() {
        setIntent("강북구/성북구/노원구");
    }

    @OnClick(R.id.seoul_junglang) //중랑구/강동구/광진구
    public void onJunglang() {
        setIntent("중랑구/강동구/광진구");
    }

    @OnClick(R.id.image_gg) //경기/인천
    public void onGg() {
        setIntent("경기/인천");
    }

    @OnClick(R.id.image_chch) //충청/대전
    public void onChch() {
        setIntent("충청/대전");
    }

    @OnClick(R.id.image_gb)
    public void onGb() { //경북/대구
        setIntent("경북/대구");
    }

    @OnClick(R.id.image_gn) //경남/부산
    public void onGn() {
        setIntent("경남/부산");
    }

    @OnClick(R.id.image_jn) //전남/광주
    public void onJn() {
        setIntent("전남/광주");
    }

    @OnClick(R.id.image_jb) //전북/전주
    public void onJb() {
        setIntent("전북/전주");
    }

    @OnClick(R.id.image_jj) //제주
    public void onJj() {
        setIntent("제주");
    }

    @OnClick(R.id.image_gw) //강원
    public void onGw() {
        setIntent("강원");
    }

    private void setIntent(String location) {
        ((MainActivity)getActivity()).goLocationResultActivity(location);
    }
}
