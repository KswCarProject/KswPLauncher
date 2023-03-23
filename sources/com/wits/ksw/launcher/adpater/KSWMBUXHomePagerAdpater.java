package com.wits.ksw.launcher.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentOne;
import com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentThree;
import com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentTwo;
import java.util.ArrayList;
import java.util.List;

public class KSWMBUXHomePagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private KswMbuxHomeFragmentOne fragmentOne = new KswMbuxHomeFragmentOne();
    private KswMbuxHomeFragmentThree fragmentThree = new KswMbuxHomeFragmentThree();
    private KswMbuxHomeFragmentTwo fragmentTwo = new KswMbuxHomeFragmentTwo();

    public KSWMBUXHomePagerAdpater(FragmentManager fm) {
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
