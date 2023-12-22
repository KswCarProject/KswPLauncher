package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.id6.FragmentID6Four;
import com.wits.ksw.launcher.view.id6.FragmentID6One;
import com.wits.ksw.launcher.view.id6.FragmentID6Three;
import com.wits.ksw.launcher.view.id6.FragmentID6Two;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BmwId6ViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentID6Four fragmentID6Four;
    public FragmentID6One fragmentID6One;
    public FragmentID6Three fragmentID6Three;
    public FragmentID6Two fragmentID6Two;
    private List<Fragment> fragmentList;

    public BmwId6ViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentID6One = new FragmentID6One();
        this.fragmentID6Two = new FragmentID6Two();
        this.fragmentID6Three = new FragmentID6Three();
        this.fragmentID6Four = new FragmentID6Four();
        this.fragmentList.add(this.fragmentID6One);
        this.fragmentList.add(this.fragmentID6Two);
        this.fragmentList.add(this.fragmentID6Three);
        this.fragmentList.add(this.fragmentID6Four);
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
