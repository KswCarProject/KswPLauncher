package com.wits.ksw.launcher.adpater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.ug.UgHomeFragmentFour;
import com.wits.ksw.launcher.view.ug.UgHomeFragmentOne;
import com.wits.ksw.launcher.view.ug.UgHomeFragmentThree;
import com.wits.ksw.launcher.view.ug.UgHomeFragmentTwo;
import java.util.ArrayList;
import java.util.List;

public class UgHomePagerAdpater extends FragmentPagerAdapter {
    private Fragment fragmentFour = new UgHomeFragmentFour();
    private List<Fragment> fragmentList = new ArrayList();
    private Fragment fragmentOne = new UgHomeFragmentOne();
    private Fragment fragmentThree = new UgHomeFragmentThree();
    private Fragment fragmentTwo = new UgHomeFragmentTwo();

    public UgHomePagerAdpater(FragmentManager fm) {
        super(fm);
        this.fragmentList.add(this.fragmentOne);
        this.fragmentList.add(this.fragmentTwo);
        this.fragmentList.add(this.fragmentThree);
        this.fragmentList.add(this.fragmentFour);
    }

    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    public int getCount() {
        return this.fragmentList.size();
    }
}
