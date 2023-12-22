package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.id5.FragmentId5Left;
import com.wits.ksw.launcher.view.id5.FragmentId5Right;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BmwId5ViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentId5Left fragmentId5Left;
    public FragmentId5Right fragmentId5Right;
    private List<Fragment> fragmentList;

    public BmwId5ViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentId5Left = new FragmentId5Left();
        this.fragmentId5Right = new FragmentId5Right();
        this.fragmentList.add(this.fragmentId5Left);
        this.fragmentList.add(this.fragmentId5Right);
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
