package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentOne;
import com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentThree;
import com.wits.ksw.launcher.view.kswmbux.KswMbuxHomeFragmentTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class KSWMBUXHomePagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private KswMbuxHomeFragmentOne fragmentOne;
    private KswMbuxHomeFragmentThree fragmentThree;
    private KswMbuxHomeFragmentTwo fragmentTwo;

    public KSWMBUXHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentOne = new KswMbuxHomeFragmentOne();
        this.fragmentTwo = new KswMbuxHomeFragmentTwo();
        this.fragmentThree = new KswMbuxHomeFragmentThree();
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
        this.fragmentList.add(this.fragmentThree);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        return this.fragmentList.size();
    }
}
