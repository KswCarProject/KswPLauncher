package com.wits.ksw.launcher.view.lexusls.adapter;

import android.support.p001v4.app.Fragment;
import android.support.p001v4.app.FragmentManager;
import android.support.p001v4.app.FragmentPagerAdapter;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomOne;
import com.wits.ksw.launcher.view.lexusls.FragmentLexusLsBottomTwoV2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class LexusLsBottomViewPagerAdpaterV2 extends FragmentPagerAdapter {
    public FragmentLexusLsBottomOne fragmentLexusLsBottomOne;
    public FragmentLexusLsBottomTwoV2 fragmentLexusLsBottomTwoV2;
    private List<Fragment> fragmentList;

    public LexusLsBottomViewPagerAdpaterV2(FragmentManager fm) {
        super(fm);
        this.fragmentList = new ArrayList();
        this.fragmentLexusLsBottomOne = new FragmentLexusLsBottomOne();
        this.fragmentLexusLsBottomTwoV2 = new FragmentLexusLsBottomTwoV2();
        this.fragmentList.add(this.fragmentLexusLsBottomOne);
        this.fragmentList.add(this.fragmentLexusLsBottomTwoV2);
    }

    @Override // android.support.p001v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.fragmentList;
        if (list != null && !list.isEmpty()) {
            return this.fragmentList.get(i);
        }
        return null;
    }

    @Override // android.support.p001v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list != null && !list.isEmpty()) {
            return this.fragmentList.size();
        }
        return 0;
    }
}
