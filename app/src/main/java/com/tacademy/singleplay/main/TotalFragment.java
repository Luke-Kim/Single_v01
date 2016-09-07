package com.tacademy.singleplay.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.MainShowAdapter;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.ShowList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.ShowListNotThemeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String action;
    private String sort;


    public TotalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param action Parameter 1.
     * @param sort Parameter 2.
     * @return A new instance of fragment TotalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TotalFragment newInstance(String action, String sort) {
        TotalFragment fragment = new TotalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, action);
        args.putString(ARG_PARAM2, sort);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.fragment_musical_rv)
    RecyclerView listView;

    MainShowAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainShowAdapter();
        if (getArguments() != null) {
            action = getArguments().getString(ARG_PARAM1);
            sort = getArguments().getString(ARG_PARAM2);
        }

        mAdapter.setOnAdapterItemClickListener(new MainShowAdapter.OnShowAdapterItemClickLIstener() {
            @Override
            public void onShowAdapterItemClick(View view, ShowList showList, int position) {
                int playId = showList.getPlayId();
                String playName = showList.getPlayName();
                ((MainActivity) getActivity()).goDetailActivity(playId, playName);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_total, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        ButterKnife.bind(this, view);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setRequest();
    }

    private void setRequest() {
        ShowListNotThemeRequest request = new ShowListNotThemeRequest(MyApplication.getContext(), action, sort);

        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<ShowList[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<ShowList[]>> request, ResultsList<ShowList[]> result) {
                ShowList[] datas = result.getResults();
                mAdapter.clear();
                mAdapter.addAll(datas);
            }

            @Override
            public void onFail(NetworkRequest<ResultsList<ShowList[]>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}
