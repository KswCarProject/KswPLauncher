package com.wits.ksw.settings.audi_mib3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import com.wits.ksw.R;
import com.wits.ksw.settings.BaseActivity;

public class AudiMib3SubActivity extends BaseActivity {
    RelativeLayout contentLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_audi_mib3_sub);
        ButterKnife.inject((Activity) this);
    }
}
