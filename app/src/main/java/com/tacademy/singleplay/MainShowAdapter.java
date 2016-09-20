package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.data2.ShowList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class MainShowAdapter extends RecyclerView.Adapter<MainShowHolder>
                                implements MainShowHolder.OnShowItemClickListener{

    List<ShowList> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(ShowList[] showList) {
        items.addAll(Arrays.asList(showList));
        notifyDataSetChanged();
    }

    @Override
    public MainShowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_show_list, parent, false);
        MainShowHolder sh = new MainShowHolder(view);
        sh.setOnShowItemClickListener(this);
        return sh;
    }

    @Override
    public void onBindViewHolder(MainShowHolder holder, int position) {
        MainShowHolder msh = (MainShowHolder)holder;
        msh.setData(items.get(position));
        return;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnShowAdapterItemClickLIstener {
        public void onShowAdapterItemClick(View view, ShowList showList, int position);
    }

    OnShowAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnShowAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onShowItemClick(View view, ShowList showList, int position) {
        if (listener != null) {
            listener.onShowAdapterItemClick(view, showList, position);
        }
    }
}
