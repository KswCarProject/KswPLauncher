package com.wits.ksw.launcher.view.benzntg6fyv2;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class BenzNtg6FyViewPagerAdapterV2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment fragmentPage1;
    private Fragment fragmentPage2;

    public BenzNtg6FyViewPagerAdapterV2(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentPage1 = new BenzNtg6FyFragmentOneV2();
        this.fragmentPage2 = new BenzNtg6FyFragmentTwoV2();
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
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
