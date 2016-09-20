package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.data2.WishList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class WishListAdapter extends RecyclerView.Adapter<WishListViewHolder> implements WishListViewHolder.OnWishListItemClickListener{

    List<WishList> items = new ArrayList<>();

    public WishListAdapter() {

    }

//    public void add(WishList wishList) {
//        items.add(wishList);
//        notifyDataSetChanged();
//    }

    public void addAll(WishList[] results) {
        this.items.addAll(Arrays.asList(results));
        notifyDataSetChanged();
    }


    @Override
    public WishListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_wish_list, parent, false); //ViewHolder에 넣어줄 view를 찾는 과정.
        WishListViewHolder vh = new WishListViewHolder(v);
        vh.setOnWishListItemClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(WishListViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnWishAdapterItemClickListener {
        public void onWishAdapterItemClick(View view, WishList wishList, int position);
    }

    OnWishAdapterItemClickListener listener;
    public void setOnWishAdapterItemCLickListener(OnWishAdapterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
public void onWishListItemClick(View view, WishList wishList, int position) {
    if(listener != null) {
        listener.onWishAdapterItemClick(view, wishList, position);
    }
}
}
