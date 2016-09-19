package com.tacademy.singleplay.inquirydetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.tacademy.singleplay.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends Fragment {
//    private ArrayList<String> mGroupList = null;
//    private ArrayList<ArrayList<String>> mChildList = null;
//    private ArrayList<String> mChildListContent = null;

    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

    ExpandableListView mListView;

    public PayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        mListView = (ExpandableListView)view.findViewById(R.id.inq_exp);
        setArrayData();
        mListView.setAdapter(new InquiryExpListAdapter(getContext(), arrayGroup, arrayChild));
        return view;
    }

    private void setArrayData(){
        if(arrayChild.size() < 1){

            arrayGroup.add("현장결제말고 다른 결제 방법은 없나요?");

//        ArrayList<String> arrayQ1 = new ArrayList<String>();
//        arrayQ1.add("공연 후에는 공연장에 입장하실 수 없으며 현장결제는 노쇼로 간주되어 다음 예매시 불이익을 받게됩니다. 이점 유의하시어 공연시작 전에 맞추어 도착해 주시길 바랍니다.");
            ArrayList<String> arrayQ2 = new ArrayList<String>();
            arrayQ2.add("현제 싱글플레이는 다른 결제방법은 제공하지 않고 있습니다. 차후 버전 업데이트를 통하여 다른 결제 방법을 제공할 예정이니 다소 불편하시더라도 양해해주시길 바랍니다.");
//        ArrayList<String> arrayQ3 = new ArrayList<String>();
//        arrayQ3.add("위시리스트 알림은 더보기 페이지 -> PUSH알림 설정 에서 설정 가능합니다.");

//        arrayChild.put(arrayGroup.get(0), arrayQ1);
            arrayChild.put(arrayGroup.get(0), arrayQ2);
//        arrayChild.put(arrayGroup.get(2), arrayQ3);
        }
    }
}
