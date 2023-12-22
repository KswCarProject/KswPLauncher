package com.wits.ksw.settings.audi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;
import com.wits.ksw.C0899R;

/* loaded from: classes17.dex */
public class AudioSeekBar extends LinearLayout {
    private static final String TAG = "KswApplication." + AudioSeekBar.class.getSimpleName();
    private static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private TextView ltextView;
    private TextView rtextView;
    private SeekBar seekBar;
    private Space space;
    private TextView titleView;

    public AudioSeekBar(Context context) {
        this(context, null);
    }

    public AudioSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, C0899R.styleable.AudioSeekBar);
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.audi_seekbar, this);
        this.space = (Space) view.findViewById(C0899R.C0901id.space);
        this.titleView = (TextView) view.findViewById(C0899R.C0901id.audio_seekbar_title);
        this.ltextView = (TextView) view.findViewById(C0899R.C0901id.audio_seekbar_left_text);
        this.rtextView = (TextView) view.findViewById(C0899R.C0901id.audio_seekbar_right_text);
        this.seekBar = (SeekBar) view.findViewById(C0899R.C0901id.audio_seekbar);
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case 0:
                    int max = a.getInt(attr, 100);
                    setMax(max);
                    break;
                case 1:
                    int progress = a.getInt(attr, 0);
                    setProgress(progress);
                    setLText(progress);
                    setRText(progress);
                    break;
                case 2:
                    int space = a.getInt(attr, 0);
                    setSpace(space);
                    break;
                case 3:
                    int resid = a.getResourceId(attr, 0);
                    setTitle(resid);
                    break;
                case 4:
                    int lvisibility = a.getInt(attr, 0);
                    setLVisibility(VISIBILITY_FLAGS[lvisibility]);
                    break;
                case 5:
                    int rvisibility = a.getInt(attr, 0);
                    setRVisibility(VISIBILITY_FLAGS[rvisibility]);
                    break;
            }
        }
        a.recycle();
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        if (listener == null) {
            return;
        }
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wits.ksw.settings.audi.widget.AudioSeekBar.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AudioSeekBar.this.setRText(progress);
                AudioSeekBar.this.setLText(progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRText(int progress) {
        this.rtextView.setText("" + progress);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLText(int progress) {
        this.ltextView.setText("" + progress);
    }

    private void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            this.titleView.setText(title);
        } else {
            this.titleView.setText("");
        }
    }

    private void setTitle(int resid) {
        this.titleView.setText(resid);
    }

    private void setProgress(int progress) {
        this.seekBar.setProgress(progress);
    }

    private void setMax(int max) {
        this.seekBar.setMax(max);
    }

    private void setLVisibility(int lvisibility) {
        this.ltextView.setVisibility(lvisibility);
    }

    private void setRVisibility(int lvisibility) {
        this.rtextView.setVisibility(lvisibility);
    }

    private void setSpace(int s) {
        this.space.setMinimumWidth(s);
    }
}
