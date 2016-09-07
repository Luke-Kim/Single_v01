package com.tacademy.singleplay;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CastingAdapter extends PagerAdapter{
    LayoutInflater inflater;

    public CastingAdapter(LayoutInflater inflater){
        this.inflater=inflater;
    }

    List<String> items = new ArrayList<>();

    public void addAll(List<String> cast) {
        items.addAll(cast);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.view_casting, null);
        ImageView img = (ImageView)view.findViewById(R.id.image_photo);
        Glide.with(MyApplication.getContext())
                .load(items.get(position))
                .into(img);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.35f;
    }
}