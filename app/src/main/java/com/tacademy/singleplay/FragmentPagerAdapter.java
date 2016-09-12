package com.tacademy.singleplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tacademy.singleplay.main.ThemeFragment;
import com.tacademy.singleplay.manager.ShowListManager;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String sort = ShowListManager.getInstance().getSort();
        return ThemeFragment.newInstance("0" , ""+position, sort);
    }

    @Override
    public int getCount() {
        return 5;
    }

}