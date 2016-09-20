package com.tacademy.singleplay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tacademy.singleplay.data.ResultsList;
import com.tacademy.singleplay.data.Search;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.SearchRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "param1";

    // TODO: Rename and change types of parameters

    private static final String KEYWORD = "2";
    private String title;
    SearchAdapter mAdapter;

    @BindView(R.id.fragment_search_rv)
    RecyclerView listView;

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
        args.putString(ARG_TITLE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new SearchAdapter();
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
        mAdapter.setOnAdapterItemClickListener(new SearchAdapter.OnSearchAdapterItemClickLIstener() {
            @Override
            public void onSearchAdapterItemClick(View view, Search showList, int position) {
                int playId = showList.getId();
                String playName = showList.getName();
                ((SearchResultActivity) getActivity()).goDetailActivity(playId, playName);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        ButterKnife.bind(this, view);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(mAdapter);

        setRequest();

        return view;
    }

    private void setRequest() {
        SearchRequest request = new SearchRequest(MyApplication.getContext(), KEYWORD, title);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<Search[]>>() {
            @Override
            public void onSuccess(NetworkRequest<ResultsList<Search[]>> request, ResultsList<Search[]> result) {
                Toast.makeText(MyApplication.getContext(), "성공", Toast.LENGTH_SHORT).show();
                Search[] datas = result.getResults();
                mAdapter.clear();
                mAdapter.addAll(datas);

            }

            @Override
            public void onFail(NetworkRequest<ResultsList<Search[]>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(MyApplication.getContext(), "실패"+errorCode+errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
