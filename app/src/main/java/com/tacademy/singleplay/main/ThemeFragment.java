package com.tacademy.singleplay.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.MainShowAdapter;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.ResultsList;
import com.tacademy.singleplay.data.ShowList;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.manager.ShowListManager;
import com.tacademy.singleplay.request.ShowListRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ACTION = "param1";
    private static final String ARG_THEME = "param2";
    private static final String ARG_SORT = "param3";

    // TODO: Rename and change types of parameters
    private String action;
    private String theme;
    private String sort;
    //    private boolean isStart = true;
    MainShowAdapter mAdapter;


    public ThemeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param action Parameter 1.
     * @param theme  Parameter 2.
     * @param theme  Parameter 3.
     * @return A new instance of fragment ThemeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThemeFragment newInstance(String action, String theme, String sort) {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ACTION, action);
        args.putString(ARG_THEME, theme);
        args.putString(ARG_SORT, sort);
        fragment.setArguments(args);
        return fragment;
    }

    TextView titleView;
    @BindView(R.id.fragment_theme_rv)
    RecyclerView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainShowAdapter();
        if (getArguments() != null) {
            action = getArguments().getString(ARG_ACTION);
            theme = getArguments().getString(ARG_THEME);
            sort = getArguments().getString(ARG_SORT);
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
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(mAdapter);

        sort = ShowListManager.getInstance().getSort();

        ShowListRequest request = new ShowListRequest(MyApplication.getContext(), action, theme, sort);
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
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getActivity() == null) return;
        if (isVisibleToUser) {
        }
    }

}
