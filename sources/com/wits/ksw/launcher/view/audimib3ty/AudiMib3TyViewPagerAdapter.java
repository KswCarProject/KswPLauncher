package com.wits.ksw.launcher.view.audimib3ty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class AudiMib3TyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    public Fragment fragmentPage1 = new AudiMib3TyFragmentOne();
    public Fragment fragmentPage2 = new AudiMib3TyFragmentTwo();

    public AudiMib3TyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentPage1);
        this.fragmentList.add(this.fragmentPage2);
    }

    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
