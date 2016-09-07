package com.tacademy.singleplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

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

}
