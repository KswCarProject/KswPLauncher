package com.wits.ksw.launcher.adpater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentOne;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentThree;
import com.wits.ksw.launcher.view.bmwevoid6gs.Bmwid6gsFragmentTwo;
import java.util.ArrayList;
import java.util.List;

public class BmwId6gSHomePagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment fragmentOne = new Bmwid6gsFragmentOne();
    private Fragment fragmentThree = new Bmwid6gsFragmentThree();
    private Fragment fragmentTwo = new Bmwid6gsFragmentTwo();

    public BmwId6gSHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
        this.fragmentList.add(this.fragmentThree);
    }

    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    public int getCount() {
        return this.fragmentList.size();
    }
}
