package com.wits.ksw.launcher.land_rover.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.land_rover.fragment.LandroverOneFragment;
import com.wits.ksw.launcher.land_rover.fragment.LandroverTwoFragment;
import java.util.ArrayList;
import java.util.List;

public class LandRoverViewPagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment oneFragment = new LandroverOneFragment();
    private Fragment twoFragment = new LandroverTwoFragment();

    public LandRoverViewPagerAdpater(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList.add(this.oneFragment);
        this.fragmentList.add(this.twoFragment);
    }

    public Fragment getItem(int position) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
