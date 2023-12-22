package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspFour;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspOne;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspThree;
import com.wits.ksw.launcher.view.id6_cusp.FragmentID6CuspTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BmwId6CuspViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentID6CuspFour fragmentID6CuspFour;
    public FragmentID6CuspOne fragmentID6CuspOne;
    public FragmentID6CuspThree fragmentID6CuspThree;
    public FragmentID6CuspTwo fragmentID6CuspTwo;
    private List<Fragment> fragmentList;

    public BmwId6CuspViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentID6CuspOne = new FragmentID6CuspOne();
        this.fragmentID6CuspTwo = new FragmentID6CuspTwo();
        this.fragmentID6CuspThree = new FragmentID6CuspThree();
        this.fragmentID6CuspFour = new FragmentID6CuspFour();
        this.fragmentList.add(this.fragmentID6CuspOne);
        this.fragmentList.add(this.fragmentID6CuspTwo);
        this.fragmentList.add(this.fragmentID6CuspThree);
        this.fragmentList.add(this.fragmentID6CuspFour);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list != null && !list.isEmpty()) {
            return this.fragmentList.get(i);
        }
        return null;
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list != null && !list.isEmpty()) {
            return this.fragmentList.size();
        }
        return 0;
    }
}
