package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.KswId7MainFirstFragment;
import com.wits.ksw.launcher.view.KswId7MainSecondFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class KSWID7ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private KswId7MainFirstFragment kswId7MainFirstFragment;
    private KswId7MainSecondFragment kswId7MainSecondFragment;

    public KSWID7ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.kswId7MainFirstFragment = new KswId7MainFirstFragment();
        this.kswId7MainSecondFragment = new KswId7MainSecondFragment();
        this.fragmentList.add(this.kswId7MainFirstFragment);
        this.fragmentList.add(this.kswId7MainSecondFragment);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void hideArrow() {
        this.kswId7MainFirstFragment.hideArrow();
        this.kswId7MainSecondFragment.hideArrow();
    }

    public void showArrow() {
        this.kswId7MainFirstFragment.showArrow();
        this.kswId7MainSecondFragment.showArrow();
    }
}
