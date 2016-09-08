package com.tacademy.singleplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tacademy.singleplay.data2.Location;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.LocationRequest;

import org.w3c.dom.Text;

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

    public LocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);
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
        setRequest("서울시 전체");
    }

    @OnClick(R.id.seoul_gangnam) //강남구/송파구
    public void onGangnam() {
        setRequest("서울시 강남구");
    }

    @OnClick(R.id.seoul_seocho) //서초구/관악구/동작구
    public void onSeocho() {
        setRequest("서울시 서초구");
    }

    @OnClick(R.id.seoul_junggu) //중구/동대문구/성동구
    public void onJunggu() {
        setRequest("서울시 중구");
    }

    @OnClick(R.id.seoul_jonglo) //종로구
    public void onJonglo() {
        setRequest("서울시 종로구");
    }

    @OnClick(R.id.seoul_mafo) //마포구/서대문구/용산구
    public void onMafo() {
        setRequest("서울시 마포구");
    }

    @OnClick(R.id.seoul_youngdengfo) //영등포구/강서구/양천구
    public void Youngdengfo() {
        setRequest("서울시 영등포구");
    }

    @OnClick(R.id.seoul_guro) //구로구/금천구
    public void onGuro() {
        setRequest("서울시 구로구");
    }

    @OnClick(R.id.seoul_gangbuck) //강북구/성북구/노원구
    public void onGangbuck() {
        setRequest("서울시 강북구");
    }

    @OnClick(R.id.seoul_junglang) //중랑구/강동구/광진구
    public void onJunglang() {
        setRequest("서울시 중랑구");
    }

    @OnClick(R.id.image_gg) //경기/인천
    public void onGg() {
        setRequest("경기/인천");
    }

    @OnClick(R.id.image_chch) //충청/대전
    public void onChch() {
        setRequest("충청/대전");
    }

    @OnClick(R.id.image_gb)
    public void onGb() { //경북/대구
        setRequest("경북/대구");
    }

    @OnClick(R.id.image_gn) //경남/부산
    public void onGn() {
        setRequest("경남/부산");
    }

    @OnClick(R.id.image_jn) //전남/광주
    public void onJn() {
        setRequest("전남/광주");
    }

    @OnClick(R.id.image_jb) //전북/전주
    public void onJb() {
        setRequest("전북/전주");
    }

    @OnClick(R.id.image_jj) //제주
    public void onJj() {
        setRequest("제주");
    }

    @OnClick(R.id.image_gw) //강원
    public void onGw() {
        setRequest("강원");
    }





    private void setRequest(String location) {
        LocationRequest request = new LocationRequest(MyApplication.getContext(), ACTION, location);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Location[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Location[]>> request, ResultsList<Location[]> result) {
                Toast.makeText(MyApplication.getContext(), "지역검색 성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Location[]>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MyApplication.getContext(), "지역검색 실패", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
