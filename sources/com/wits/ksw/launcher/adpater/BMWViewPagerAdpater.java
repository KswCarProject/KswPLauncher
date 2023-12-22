package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.CarFragment;
import com.wits.ksw.launcher.view.MediaFragment;
import com.wits.ksw.launcher.view.NaviFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BMWViewPagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment musicFragment;
    private Fragment naviFragment;
    private Fragment setFragment;

    public BMWViewPagerAdpater(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.naviFragment = new NaviFragment();
        this.musicFragment = new MediaFragment();
        this.setFragment = new CarFragment();
        this.fragmentList.add(this.naviFragment);
        this.fragmentList.add(this.musicFragment);
        this.fragmentList.add(this.setFragment);
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
}
