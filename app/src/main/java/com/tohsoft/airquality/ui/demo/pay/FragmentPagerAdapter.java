package com.tohsoft.airquality.ui.demo.pay;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    private Context mContext;

    public FragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BreezometerFragment();
        } else if (position == 1)
            return new IqAirFragment();
        else return new AmbeeFragment();

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Breezometer";
            case 1:
                return "IqAir";
            case 2:
                return "Ambee";
            default:
                return null;
        }
    }
}
