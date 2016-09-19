package com.tacademy.singleplay.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingCheckedDialogFragment extends Fragment {


    public BookingCheckedDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_checked_dialog, container, false);
        return view;
    }

}
