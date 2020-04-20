package com.wits.ksw.launcher.adpater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wits.ksw.launcher.view.id6.FragmentID6Four;
import com.wits.ksw.launcher.view.id6.FragmentID6One;
import com.wits.ksw.launcher.view.id6.FragmentID6Three;
import com.wits.ksw.launcher.view.id6.FragmentID6Two;
import java.util.ArrayList;
import java.util.List;

public class BmwId6ViewPagerAdpater extends FragmentPagerAdapter {
    public FragmentID6Four fragmentID6Four = new FragmentID6Four();
    public FragmentID6One fragmentID6One = new FragmentID6One();
    public FragmentID6Three fragmentID6Three = new FragmentID6Three();
    public FragmentID6Two fragmentID6Two = new FragmentID6Two();
    private List<Fragment> fragmentList = new ArrayList();

    public BmwId6ViewPagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentID6One);
        this.fragmentList.add(this.fragmentID6Two);
        this.fragmentList.add(this.fragmentID6Three);
        this.fragmentList.add(this.fragmentID6Four);
    }

    public Fragment getItem(int i) {
        if (this.fragmentList == null || this.fragmentList.isEmpty()) {
            return null;
        }
        return this.fragmentList.get(i);
    }

    public int getCount() {
        if (this.fragmentList == null || this.fragmentList.isEmpty()) {
            return 0;
        }
        return this.fragmentList.size();
    }
}
