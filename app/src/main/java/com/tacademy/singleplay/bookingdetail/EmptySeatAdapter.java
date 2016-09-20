package com.tacademy.singleplay.bookingdetail;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.EmptySeatInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class EmptySeatAdapter extends RecyclerView.Adapter<EmptySeatHolder> implements EmptySeatHolder.OnSeatItemClickListener{

    List<EmptySeatInfo> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(EmptySeatInfo[] seatList) {
        items.addAll(Arrays.asList(seatList));
        notifyDataSetChanged();
    }

    @Override
    public EmptySeatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seat_choose, parent, false);
        EmptySeatHolder sh = new EmptySeatHolder(view);
        sh.setOnSeatItemClickListener(this);
        return sh;
    }

    int checkedPosition = -1;
    @Override
    public void onBindViewHolder(EmptySeatHolder holder, int position) {
        EmptySeatHolder esh = (EmptySeatHolder)holder;
        holder.setChecked(checkedPosition == position);
        esh.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnSeatAdapterClickListener {
        public void onSeatAdapterClickListener(View view, EmptySeatInfo emptySeatInfo, int posion);
    }

    OnSeatAdapterClickListener listener;
    public void setOnSeatAdapterClickListener(OnSeatAdapterClickListener listener) {
        this.listener = listener;
    }

    private void setSeatItemChecked(int position, boolean isChecked){
        if (checkedPosition != position) {
            if (isChecked) {
                checkedPosition = position;
                notifyDataSetChanged();
            }
        } else {
            if (!isChecked) {
                checkedPosition = -1;
                notifyDataSetChanged();
            }
        }
    }

    public SparseBooleanArray getCheckedItemsPositions() {
        throw new IllegalArgumentException("invalid mode");
    }

    @Override
    public void onSeatItemClick(View view, EmptySeatInfo emptySeatInfo, int position) {
        setSeatItemChecked(position, true);
        if (listener != null) {
            listener.onSeatAdapterClickListener(view, emptySeatInfo, position);
        }
    }
}
