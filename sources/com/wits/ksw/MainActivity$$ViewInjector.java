package com.wits.ksw;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import butterknife.ButterKnife;

public class MainActivity$$ViewInjector {
    public static void inject(ButterKnife.Finder finder, MainActivity target, Object source) {
        target.id6MainViewPager = (ViewPager) finder.findRequiredView(source, R.id.id6_main_view_pager, "field 'id6MainViewPager'");
        target.id6LeftBtn = (ImageView) finder.findRequiredView(source, R.id.id6_left_btn, "field 'id6LeftBtn'");
        target.id6RightBtn = (ImageView) finder.findRequiredView(source, R.id.id6_right_btn, "field 'id6RightBtn'");
    }

    public static void reset(MainActivity target) {
        target.id6MainViewPager = null;
        target.id6LeftBtn = null;
        target.id6RightBtn = null;
    }
}
