package com.wits.ksw.launcher.als_id7.adapter;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.als_id7.fragment.DashVideoFragment;
import com.wits.ksw.launcher.als_id7.fragment.MusicPhoneFragment;
import com.wits.ksw.launcher.als_id7.fragment.NaviCarFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class BMWId7ViewPagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment musicFragment;
    private Fragment naviFragment;
    private Fragment videoFragment;

    public BMWId7ViewPagerAdpater(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.naviFragment = new NaviCarFragment();
        this.musicFragment = new MusicPhoneFragment();
        this.videoFragment = new DashVideoFragment();
        this.fragmentList.add(this.naviFragment);
        this.fragmentList.add(this.musicFragment);
        this.fragmentList.add(this.videoFragment);
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
