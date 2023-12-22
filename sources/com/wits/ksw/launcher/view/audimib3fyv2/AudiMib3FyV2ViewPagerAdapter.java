package com.wits.ksw.launcher.view.audimib3fyv2;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class AudiMib3FyV2ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public Fragment fragmentPage1;
    public Fragment fragmentPage2;

    public AudiMib3FyV2ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentPage1 = new AudiMib3FyV2FragmentOne();
        this.fragmentPage2 = new AudiMib3FyV2FragmentTwo();
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
