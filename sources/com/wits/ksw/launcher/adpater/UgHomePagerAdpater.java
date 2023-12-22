package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.p006ug.UgHomeFragmentFour;
import com.wits.ksw.launcher.view.p006ug.UgHomeFragmentOne;
import com.wits.ksw.launcher.view.p006ug.UgHomeFragmentThree;
import com.wits.ksw.launcher.view.p006ug.UgHomeFragmentTwo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class UgHomePagerAdpater extends FragmentPagerAdapter {
    private Fragment fragmentFour;
    private List<Fragment> fragmentList;
    private Fragment fragmentOne;
    private Fragment fragmentThree;
    private Fragment fragmentTwo;

    public UgHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentOne = new UgHomeFragmentOne();
        this.fragmentTwo = new UgHomeFragmentTwo();
        this.fragmentThree = new UgHomeFragmentThree();
        this.fragmentFour = new UgHomeFragmentFour();
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
        this.fragmentList.add(this.fragmentThree);
        this.fragmentList.add(this.fragmentFour);
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
