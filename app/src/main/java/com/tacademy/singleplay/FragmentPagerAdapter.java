package com.tacademy.singleplay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.tacademy.singleplay.main.ConcertFragment;
import com.tacademy.singleplay.main.MDRecFragment;
import com.tacademy.singleplay.main.MusicalFragment;
import com.tacademy.singleplay.main.OperaFragment;
import com.tacademy.singleplay.main.TotalFragment;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    int action, theme, sort;
    public void setShowList(int action, int theme, int sort) {
        this.action = action;
        this.theme = theme;
        this.sort = sort; //""+action, ""+theme, ""+sort
    }

    MDRecFragment fragment = new MDRecFragment();
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return TotalFragment.newInstance("" + action, "" + sort);
            case 1 :
                return MusicalFragment.newInstance(""+action, ""+theme, ""+sort);

            case 2 :
                return OperaFragment.newInstance(""+action, ""+theme, ""+sort);

            case 3 :
                return ConcertFragment.newInstance(""+action, ""+theme, ""+sort);

            case 4 :
                return MDRecFragment.newInstance(""+action, ""+theme, ""+sort);

            default:
                return null;
        }
//        return new MusicalFragment();
//        return MusicalFragment.newInstance(""+action, ""+theme, ""+sort);
    }

    @Override
    public int getCount() {
        return 4;
    }

}