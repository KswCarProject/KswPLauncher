package com.wits.ksw.launcher.view.p006ug;

import android.content.Context;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import com.wits.ksw.MainActivity;

/* renamed from: com.wits.ksw.launcher.view.ug.UgHomeImageView */
/* loaded from: classes9.dex */
public class UgHomeImageView extends AppCompatImageView {
    private static final String TAG = "KswApplication." + UgHomeImageView.class.getSimpleName();

    public UgHomeImageView(Context context) {
        this(context, null);
    }

    public UgHomeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 21) {
            try {
                Log.i(TAG, "KEYCODE_DPAD_LEFT");
                MainActivity.mainActivity.ugBinding.musicButton.setFocusable(true);
                MainActivity.mainActivity.ugBinding.musicButton.setFocusableInTouchMode(true);
                MainActivity.mainActivity.ugBinding.musicButton.requestFocus();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getKeyCode() == 22) {
            Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_RIGHT");
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
