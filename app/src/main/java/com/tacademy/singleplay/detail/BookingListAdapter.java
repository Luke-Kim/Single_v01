package com.tacademy.singleplay.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.BookingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class BookingListAdapter extends RecyclerView.Adapter<BookingListViewHolder> implements BookingListViewHolder.OnBookingListItemClickListener {

    List<BookingList> items = new ArrayList<>();

//    public void add(BookingList bookingList) {
//        items.add(bookingList);
//        notifyDataSetChanged();
//    }

    public void addAll(BookingList[] results) {
        items.addAll(Arrays.asList(results));
        notifyDataSetChanged();
    }

    public BookingListAdapter() {

    }

    @Override
    public BookingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_booking_list, parent, false);
        BookingListViewHolder vh = new BookingListViewHolder(v);
        vh.setOnBookingListItemClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookingListViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnBookingAdapterItemClickLIstener {
        public void onBookingAdapterItemClick(View view, BookingList bookingList, int position);
    }

    OnBookingAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnBookingAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onBookingListItemClick(View view, BookingList bookingList, int position) {
        if (listener != null) {
            listener.onBookingAdapterItemClick(view, bookingList, position);
        }
    }
}
