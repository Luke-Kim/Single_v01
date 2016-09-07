package com.tacademy.singleplay.wishpopup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.WishList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class WishPopAdater extends RecyclerView.Adapter<WishPopHolder> {
    List<String> items = new ArrayList<>();

    public void add(WishList wishList) {
//        items.add(wishList);
        notifyDataSetChanged();
    }

    @Override
    public WishPopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_wish_pop, null);
        WishPopHolder vh = new WishPopHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(WishPopHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    public void addAll(WishListAdd[] results) {
//        items.addAll(Arrays.asList(results));
//        notifyDataSetChanged();
//    }

    public void addAll(String[] results) {
        items.addAll(Arrays.asList(results));
        notifyDataSetChanged();
    }
}
