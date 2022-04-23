package com.wits.ksw.launcher.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspFour;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspThree;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo;
import java.util.ArrayList;
import java.util.List;

public class BmwId6CuspViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentID6CuspFour fragmentID6CuspFour = new FragmentID6CuspFour();
    public FragmentID6CuspOne fragmentID6CuspOne = new FragmentID6CuspOne();
    public FragmentID6CuspThree fragmentID6CuspThree = new FragmentID6CuspThree();
    public FragmentID6CuspTwo fragmentID6CuspTwo = new FragmentID6CuspTwo();
    private List<Fragment> fragmentList = new ArrayList();

    public BmwId6CuspViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentID6CuspOne);
        this.fragmentList.add(this.fragmentID6CuspTwo);
        this.fragmentList.add(this.fragmentID6CuspThree);
        this.fragmentList.add(this.fragmentID6CuspFour);
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
