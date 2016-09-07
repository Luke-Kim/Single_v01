package com.tacademy.singleplay.inquirydetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class InquiryPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    public InquiryPagerAdapter(FragmentManager ifm) {
        super(ifm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new QAFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new PayFragment();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new IdFragment();
            case 3:
                return new ETCFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
