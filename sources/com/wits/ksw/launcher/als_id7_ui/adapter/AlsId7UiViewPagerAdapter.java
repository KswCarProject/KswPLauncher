package com.wits.ksw.launcher.als_id7_ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiCarFragment;
import com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiMediaFragment;
import com.wits.ksw.launcher.als_id7_ui.fragment.AlsId7UiNaviFragment;
import java.util.ArrayList;
import java.util.List;

public class AlsId7UiViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment musicFragment = new AlsId7UiMediaFragment();
    private Fragment naviFragment = new AlsId7UiNaviFragment();
    private Fragment setFragment = new AlsId7UiCarFragment();

    public AlsId7UiViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList.add(this.naviFragment);
        this.fragmentList.add(this.musicFragment);
        this.fragmentList.add(this.setFragment);
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
