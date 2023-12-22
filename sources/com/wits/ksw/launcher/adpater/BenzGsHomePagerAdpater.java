package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.benzgs.BenzGsFrametOne;
import com.wits.ksw.launcher.view.benzgs.BenzGsFrametTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BenzGsHomePagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment fragmentOne;
    private Fragment fragmentTwo;

    public BenzGsHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentOne = new BenzGsFrametOne();
        this.fragmentTwo = new BenzGsFrametTwo();
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
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
