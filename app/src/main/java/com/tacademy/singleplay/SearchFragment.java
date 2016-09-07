package com.tacademy.singleplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.data.ShowData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MESSAGE = "param1";

    // TODO: Rename and change types of parameters
    private String message;
    MainRecyclerAdapter mainRecyclerAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param message Parameter 1.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String message) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        mainRecyclerAdapter = new MainRecyclerAdapter();
        mainRecyclerAdapter.setOnAdapterItemClickListener(new MainRecyclerAdapter.OnShowAdapterItemClickLIstener() {
            @Override
            public void onShowAdapterItemClick(View view, ShowData showData, int position) {
                Intent intent = new Intent(getContext(), ShowDetailActivity.class);
                startActivity(intent);

            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_search_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mainRecyclerAdapter);
        initData();

        return view;
    }
    private void initData() {

        ShowData[] item = new ShowData[5];
        item[0] = new ShowData(R.drawable.a, "#1");
        item[1] = new ShowData(R.drawable.b, "#2");
        item[2] = new ShowData(R.drawable.c, "#3");
        item[3] = new ShowData(R.drawable.d, "#4");
        item[4] = new ShowData(R.drawable.e, "#5");

        for (int i = 0; i < 5; i++) mainRecyclerAdapter.add(item[i]);

    }
}
