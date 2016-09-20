package com.tacademy.singleplay.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.Coupon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponViewHolder> {

    List<Coupon> items = new ArrayList<>();

    public CouponAdapter() {
    }


//    public void add(CouponData couponData) {
//        items.add(couponData);
//        notifyDataSetChanged();
//    }

    public void addAll(Coupon[] result) {
        items.addAll(Arrays.asList(result));
        notifyDataSetChanged();
    }



    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_coupon_recycler, null);
        CouponViewHolder vh = new CouponViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CouponViewHolder holder, int position) {
        holder.setData(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}
