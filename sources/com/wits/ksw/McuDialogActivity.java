package com.wits.ksw;

import android.app.Activity;
import android.os.Bundle;
import com.wits.ksw.settings.utlis_view.DialogViews;

/* loaded from: classes17.dex */
public class McuDialogActivity extends Activity {
    private DialogViews dialogViews;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dialogViews = new DialogViews(this);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.dialogViews.KswAPPupdateMcu(getString(C0899R.string.update_mcu_file));
    }
}
