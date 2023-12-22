package com.wits.ksw.launcher.adpater;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.CarFragmentV2;
import com.wits.ksw.launcher.view.MediaFragmentV2;
import com.wits.ksw.launcher.view.NaviFragmentV2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class BMWViewPagerAdpaterV2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment musicFragment;
    private Fragment naviFragment;
    private Fragment setFragment;

    public BMWViewPagerAdpaterV2(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.naviFragment = new NaviFragmentV2();
        this.musicFragment = new MediaFragmentV2();
        this.setFragment = new CarFragmentV2();
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
