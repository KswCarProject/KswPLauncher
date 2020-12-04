package com.wits.ksw.settings.audi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;

public class AudiSubActivity extends BaseActivity {
    @InjectView(2131230807)
    RelativeLayout contentLayout;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_audi_sub);
        ButterKnife.inject((Activity) this);
    }
}
