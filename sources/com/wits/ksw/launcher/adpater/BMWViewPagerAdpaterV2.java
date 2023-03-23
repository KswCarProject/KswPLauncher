package com.wits.ksw.launcher.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.CarFragmentV2;
import com.wits.ksw.launcher.view.MediaFragmentV2;
import com.wits.ksw.launcher.view.NaviFragmentV2;
import java.util.ArrayList;
import java.util.List;

public class BMWViewPagerAdpaterV2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment musicFragment = new MediaFragmentV2();
    private Fragment naviFragment = new NaviFragmentV2();
    private Fragment setFragment = new CarFragmentV2();

    public BMWViewPagerAdpaterV2(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList.add(this.naviFragment);
        this.fragmentList.add(this.musicFragment);
        this.fragmentList.add(this.setFragment);
    }

    public Fragment getItem(int position) {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return null;
        }
        return list.get(position);
    }

    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
