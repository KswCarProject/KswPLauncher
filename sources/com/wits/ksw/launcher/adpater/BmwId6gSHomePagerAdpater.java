package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentOne;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentThree;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BmwId6gSHomePagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment fragmentOne;
    private Fragment fragmentThree;
    private Fragment fragmentTwo;

    public BmwId6gSHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentOne = new Bmwid6gsFragmentOne();
        this.fragmentTwo = new Bmwid6gsFragmentTwo();
        this.fragmentThree = new Bmwid6gsFragmentThree();
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
        this.fragmentList.add(this.fragmentThree);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        return this.fragmentList.size();
    }
}
