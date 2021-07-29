package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.wits.ksw.MainActivity;

public class CustomBmwMusicLayout extends FrameLayout {
    private static final String TAG = CustomBmwMusicLayout.class.getName();

    public CustomBmwMusicLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomBmwMusicLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 21) {
            try {
                Log.i(TAG, "dispatchKeyEvent: KeyEvent.KEYCODE_DPAD_LEFT,  MainActivity.mainActivity.refreshLastViewFocused()");
                MainActivity.mainActivity.refreshLastViewFocused();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (event.getKeyCode() == 22) {
                Log.i(TAG, "dispatchKeyEvent: KeyEvent.KEYCODE_DPAD_RIGHT,---------------------------");
                return true;
            }
            return super.dispatchKeyEvent(event);
        }
    }
}
