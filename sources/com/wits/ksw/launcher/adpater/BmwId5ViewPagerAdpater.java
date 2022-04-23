package com.wits.ksw.launcher.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.id5.FragmentId5Left;
import com.wits.ksw.launcher.view.id5.FragmentId5Right;
import java.util.ArrayList;
import java.util.List;

public class BmwId5ViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentId5Left fragmentId5Left = new FragmentId5Left();
    public FragmentId5Right fragmentId5Right = new FragmentId5Right();
    private List<Fragment> fragmentList = new ArrayList();

    public BmwId5ViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentId5Left);
        this.fragmentList.add(this.fragmentId5Right);
    }

    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.fragmentList.get(i);
    }

    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.fragmentList.size();
    }
}
