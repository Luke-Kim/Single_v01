package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.data2.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchHolder>
                            implements SearchHolder.OnSearchItemClickListener{

    List<Search> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(Search[] showList) {
        items.addAll(Arrays.asList(showList));
        notifyDataSetChanged();
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search, parent, false);
        SearchHolder sh = new SearchHolder(view);
        sh.setOnSearchItemClickListener(this);
        return sh;
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        SearchHolder sh = (SearchHolder)holder;
        sh.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnSearchAdapterItemClickLIstener {
        public void onSearchAdapterItemClick(View view, Search search, int position);
    }

    OnSearchAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnSearchAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onSearchItemClick(View view, Search search, int position) {
        if (listener != null) {
            listener.onSearchAdapterItemClick(view, search, position);
        }
    }
}
