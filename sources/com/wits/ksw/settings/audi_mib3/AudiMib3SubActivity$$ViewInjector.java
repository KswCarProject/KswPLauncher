package com.wits.ksw.settings.audi_mib3;

import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.wits.ksw.R;

public class AudiMib3SubActivity$$ViewInjector {
    public static void inject(ButterKnife.Finder finder, AudiMib3SubActivity target, Object source) {
        target.contentLayout = (RelativeLayout) finder.findRequiredView(source, R.id.audiSubContentLinearLayout, "field 'contentLayout'");
    }

    public static void reset(AudiMib3SubActivity target) {
        target.contentLayout = null;
    }
}
