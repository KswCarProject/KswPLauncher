package com.wits.ksw.launcher.view.benzmbux2021;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class BenzMbux2021ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment fragmentPage1 = new BenzMbux2021FragmentOne();
    private Fragment fragmentPage2 = new BenzMbux2021FragmentTwo();
    private Fragment fragmentPage3 = new BenzMbux2021FragmentThree();

    public BenzMbux2021ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
        this.fragmentList.add(this.fragmentPage3);
    }

    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

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
