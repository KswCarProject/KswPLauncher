package com.wits.ksw.settings.audi;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.wits.ksw.C0899R;
import com.wits.ksw.settings.BaseActivity;

/* loaded from: classes13.dex */
public class AudiSubActivity extends BaseActivity {
    RelativeLayout contentLayout;
    public TextView tv_title_set;

    @Override // com.wits.ksw.settings.BaseActivity, android.support.p004v7.app.AppCompatActivity, android.support.p001v4.app.FragmentActivity, android.support.p001v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0899R.C0902layout.activity_audi_sub);
        this.tv_title_set = (TextView) findViewById(C0899R.C0901id.tv_title_set);
        ButterKnife.inject(this);
    }
}
