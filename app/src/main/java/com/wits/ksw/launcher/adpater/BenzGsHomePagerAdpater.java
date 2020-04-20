package com.wits.ksw.launcher.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.benzgs.BenzGsFrametOne;
import com.wits.ksw.launcher.view.benzgs.BenzGsFrametTwo;
import java.util.ArrayList;
import java.util.List;

public class BenzGsHomePagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment fragmentOne = new BenzGsFrametOne();
    private Fragment fragmentTwo = new BenzGsFrametTwo();

    public BenzGsHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
    }

    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    public int getCount() {
        return this.fragmentList.size();
    }
}
