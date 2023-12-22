package com.wits.ksw.launcher.view.ntg5;

import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.support.p004v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ibm.icu.text.SCSU;
import com.wits.ksw.launcher.adpater.BenzNTG5RecyclerViewAdapter;

/* loaded from: classes3.dex */
public class NTG5ImageView extends AppCompatImageView implements View.OnFocusChangeListener {
    public static final String TAG = "NTG5ImageView";
    private BenzNTG5RecyclerViewAdapter adapter;
    private TextView mTextView;
    private int screenWidth;

    public NTG5ImageView(Context context) {
        this(context, null);
    }

    public TextView getTextView() {
        return this.mTextView;
    }

    public BenzNTG5RecyclerViewAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(BenzNTG5RecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    public void bindTextView(TextView textView) {
        this.mTextView = textView;
    }

    public NTG5ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.screenWidth = 0;
        setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.ntg5.-$$Lambda$K9Okm5wilKXreWe5S-ReH_Q0YVM
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                NTG5ImageView.this.onFocusChange(view, z);
            }
        });
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.screenWidth = displayMetrics.widthPixels;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d(TAG, "onFocusChange: " + hasFocus);
        if (hasFocus) {
            textViewOnFocus();
        } else {
            textViewUnFocus();
        }
    }

    private void textViewOnFocus() {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setTextSize(30.0f);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mTextView.getLayoutParams();
            if (this.screenWidth == 1920) {
                lp.setMargins(0, 0, 0, 211);
            } else {
                lp.setMargins(0, 0, 0, 144);
            }
            this.mTextView.setLayoutParams(lp);
        }
        BenzNTG5RecyclerViewAdapter benzNTG5RecyclerViewAdapter = this.adapter;
        if (benzNTG5RecyclerViewAdapter != null) {
            benzNTG5RecyclerViewAdapter.setFocusPosition(this);
        }
    }

    private void textViewUnFocus() {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setTextSize(22.0f);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mTextView.getLayoutParams();
            if (this.screenWidth == 1920) {
                lp.setMargins(0, 0, 0, SCSU.UDEFINE1);
            } else {
                lp.setMargins(0, 0, 0, 155);
            }
            this.mTextView.setLayoutParams(lp);
        }
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (event.getAction() == 0) {
            if (keyCode == 19 || keyCode == 20) {
                return true;
            }
        } else if (event.getAction() == 1) {
            if (keyCode == 20) {
                sendKeyDownUpSync(22);
                Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_UP---->>>KEYCODE_DPAD_RIGHT");
                return true;
            } else if (keyCode == 19) {
                sendKeyDownUpSync(21);
                Log.i(TAG, "dispatchKeyEvent: KEYCODE_DPAD_DOWN---->>>KEYCODE_DPAD_LEFT");
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public static void sendKeyDownUpSync(final int key) {
        new Handler().post(new Runnable() { // from class: com.wits.ksw.launcher.view.ntg5.NTG5ImageView.1
            @Override // java.lang.Runnable
            public void run() {
                new Thread(new Runnable() { // from class: com.wits.ksw.launcher.view.ntg5.NTG5ImageView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Instrumentation instrumentation = new Instrumentation();
                        instrumentation.sendKeyDownUpSync(key);
                    }
                }).start();
            }
        });
    }
}
