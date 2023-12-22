package com.wits.ksw.launcher.als_id7_ui.adapter;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiCarFragment;
import com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiMediaFragment;
import com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiNaviFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class AlsId7UiViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private Fragment musicFragment;
    private Fragment naviFragment;
    private Fragment setFragment;

    public AlsId7UiViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.naviFragment = new AlsId7UiNaviFragment();
        this.musicFragment = new AlsId7UiMediaFragment();
        this.setFragment = new AlsId7UiCarFragment();
        this.fragmentList.add(this.naviFragment);
        this.fragmentList.add(this.musicFragment);
        this.fragmentList.add(this.setFragment);
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
