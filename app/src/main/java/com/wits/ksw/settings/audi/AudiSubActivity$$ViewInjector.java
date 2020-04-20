package com.wits.ksw.settings.audi;

import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.wits.ksw.R;

public class AudiSubActivity$$ViewInjector {
    public static void inject(ButterKnife.Finder finder, AudiSubActivity target, Object source) {
        target.contentLayout = (RelativeLayout) finder.findRequiredView(source, R.id.audiSubContentLinearLayout, "field 'contentLayout'");
    }

    public static void reset(AudiSubActivity target) {
        target.contentLayout = null;
    }
}
