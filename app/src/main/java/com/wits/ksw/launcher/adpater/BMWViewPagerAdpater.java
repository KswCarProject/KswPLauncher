package com.wits.ksw.launcher.adpater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wits.ksw.launcher.view.CarFragment;
import com.wits.ksw.launcher.view.MediaFragment;
import com.wits.ksw.launcher.view.NaviFragment;
import java.util.ArrayList;
import java.util.List;

public class BMWViewPagerAdpater extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment musicFragment = new MediaFragment();
    private Fragment naviFragment = new NaviFragment();
    private Fragment setFragment = new CarFragment();

    public BMWViewPagerAdpater(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList.add(this.naviFragment);
        this.fragmentList.add(this.musicFragment);
        this.fragmentList.add(this.setFragment);
    }

    public Fragment getItem(int position) {
        if (this.fragmentList == null) {
            return null;
        }
        return this.fragmentList.get(position);
    }

    public int getCount() {
        if (this.fragmentList == null) {
            return 0;
        }
        return this.fragmentList.size();
    }
}
