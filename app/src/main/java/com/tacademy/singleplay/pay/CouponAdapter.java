package com.tacademy.singleplay.pay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.DiscountCoupons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponHolder>
                            implements CouponHolder.OnCouponItemClickListener{

    List<DiscountCoupons> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(DiscountCoupons[] coupon) {
        items.addAll(Arrays.asList(coupon));
        notifyDataSetChanged();
}


    @Override
    public CouponHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_paycoupon, parent, false);
        CouponHolder ch = new CouponHolder(view);
        ch.setOnCouponItemClickListener(this);
        return ch;
    }

    @Override
    public void onBindViewHolder(CouponHolder holder, int position) {
        CouponHolder ch = (CouponHolder)holder;
        ch.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnCouponAdapterItemClickLIstener {
        public void onCouponAdapterItemClick(View view, DiscountCoupons coupons, int position);
    }

    OnCouponAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnCouponAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onCouponItemClick(View view, DiscountCoupons discountCoupons, int position) {
        if (listener != null) {
            listener.onCouponAdapterItemClick(view, discountCoupons, position);
        }
    }
}
