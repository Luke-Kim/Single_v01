package com.tacademy.singleplay.inquirydetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.tacademy.singleplay.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdFragment extends Fragment {
    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mChildListContent = null;
     ExpandableListView mListView;

    public IdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_id, container, false);
        mListView = (ExpandableListView)view.findViewById(R.id.inq_exp);
        mGroupList = new ArrayList<>();
        mChildList = new ArrayList<>();
        mChildListContent = new ArrayList<>();

        mGroupList.add("질의응답 1");
        mGroupList.add("질의응답 2");
        mGroupList.add("질의응답 3");

        mChildListContent.add("고오오오옹오오오오오오오오오오오오지지지지이이이이이이이이이이이이이사사사사사사사사사사사사사사사하하하하하하하하하하하아아아아아아아앙");

        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);
        mListView.setAdapter(new InquiryExpListAdapter(getContext(), mGroupList, mChildList));

        return view;
    }
}
