package com.tacademy.singleplay.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.EventNotice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class EventNoticeAdapter extends RecyclerView.Adapter<EventNoticeViewHolder> implements EventNoticeViewHolder.OnEventNoticeItemClickListener {

    List<EventNotice> items = new ArrayList<>();

    public EventNoticeAdapter() {

    }

//    public void add(EventNotice eventNotice) {
//        items.add(eventNotice);
//        notifyDataSetChanged();
//    }

    public void addAll(EventNotice[] result){
        items.addAll(Arrays.asList(result));
        notifyDataSetChanged();
    }

    @Override
    public EventNoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_event_notice, parent, false);
        EventNoticeViewHolder vh = new EventNoticeViewHolder(v);
        vh.setOnEventNoticeItemClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventNoticeViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnEventNoticeAdapterItemClickLIstener {
        public void onEventNoticeAdapterItemClick(View view, EventNotice eventNotice, int position);
    }

    OnEventNoticeAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnEventNoticeAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onEventNoticeItemClick(View view, EventNotice eventNotice, int position) {
        if (listener != null) {
            listener.onEventNoticeAdapterItemClick(view, eventNotice, position);
        }
    }
}