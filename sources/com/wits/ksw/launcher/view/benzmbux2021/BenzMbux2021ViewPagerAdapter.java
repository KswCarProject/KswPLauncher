package com.wits.ksw.launcher.view.benzmbux2021;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class BenzMbux2021ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment fragmentPage1;
    private Fragment fragmentPage2;
    private Fragment fragmentPage3;

    public BenzMbux2021ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentPage1 = new BenzMbux2021FragmentOne();
        this.fragmentPage2 = new BenzMbux2021FragmentTwo();
        this.fragmentPage3 = new BenzMbux2021FragmentThree();
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
        this.fragmentList.add(this.fragmentPage3);
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

    public BenzMbux2021FragmentTwo getFragmentPage2() {
        return (BenzMbux2021FragmentTwo) this.fragmentPage2;
    }
}
