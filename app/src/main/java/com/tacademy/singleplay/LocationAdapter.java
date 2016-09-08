package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.data2.Location;
import com.tacademy.singleplay.data2.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationHolder>
                                implements LocationHolder.OnLocationItemClickListener{

    List<Location> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(Location[] showList) {
        items.addAll(Arrays.asList(showList));
        notifyDataSetChanged();
    }

    @Override
    public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_show_list, parent, false);
        LocationHolder lh = new LocationHolder(view);
        lh.setOnLocationItemClickListener(this);
        return lh;
    }

    @Override
    public void onBindViewHolder(LocationHolder holder, int position) {
        LocationHolder lh = (LocationHolder)holder;
        lh.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnLocationAdapterItemClickLIstener {
        public void onLocationAdapterItemClick(View view, Location location, int position);
    }

    OnLocationAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnLocationAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onLocationItemClick(View view, Location location, int position) {
        if (listener != null) {
            listener.onLocationAdapterItemClick(view, location, position);
        }
    }
}
