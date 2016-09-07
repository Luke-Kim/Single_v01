package com.tacademy.singleplay;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class WishAdapter extends PagerAdapter{
    LayoutInflater inflater;

    public WishAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = inflater.inflate(R.layout.view_wish_poster, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.img_wish_poster);
        imageView.setImageResource(R.drawable.sample_0+position);

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
        return 0.25f;
    }
}
