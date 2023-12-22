package com.wits.ksw.launcher.land_rover.adapter;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.land_rover.fragment.LandroverOneFragment;
import com.wits.ksw.launcher.land_rover.fragment.LandroverTwoFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class LandRoverViewPagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment oneFragment;
    private Fragment twoFragment;

    public LandRoverViewPagerAdpater(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.oneFragment = new LandroverOneFragment();
        this.twoFragment = new LandroverTwoFragment();
        this.fragmentList.add(this.oneFragment);
        this.fragmentList.add(this.twoFragment);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
