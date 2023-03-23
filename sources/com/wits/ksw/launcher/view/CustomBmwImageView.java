package com.wits.ksw.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.wits.ksw.MainActivity;

public class CustomBmwImageView extends ImageView {
    private static final String TAG = CustomBmwImageView.class.getName();
    private Context mContext;

    public CustomBmwImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CustomBmwImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setDefaultFocusHighlightEnabled(false);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == 21 && event.getAction() == 0) {
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
