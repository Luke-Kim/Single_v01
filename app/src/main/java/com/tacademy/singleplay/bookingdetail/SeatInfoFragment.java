package com.tacademy.singleplay.bookingdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeatInfoFragment extends Fragment {


    public SeatInfoFragment() {
        // Required empty public constructor
    }
    CustomView customView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seat_info, container, false);
        customView = (CustomView)view.findViewById(R.id.custom_view);

//        ImageButton btn = (ImageButton)view.findViewById(R.id.btn_zooming);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return view;
    }

}
