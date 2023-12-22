package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import com.wits.ksw.MainActivity;
import skin.support.widget.SkinCompatImageView;

/* loaded from: classes16.dex */
public class CustomSkinImageView extends SkinCompatImageView {
    private static final String TAG = CustomSkinImageView.class.getName();
    private Context mContext;

    public CustomSkinImageView(Context context) {
        this(context, null);
    }

    public CustomSkinImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setDefaultFocusHighlightEnabled(false);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 21) {
            try {
                Log.i(TAG, "dispatchKeyEvent: KeyEvent.KEYCODE_DPAD_LEFT,  MainActivity.mainActivity.refreshLastViewFocused()");
                MainActivity.mainActivity.refreshLastViewFocused();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (event.getKeyCode() == 22) {
            Log.i(TAG, "dispatchKeyEvent: KeyEvent.KEYCODE_DPAD_RIGHT,---------------------------");
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
