package com.wits.ksw.settings.audi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;
import com.wits.ksw.R;

@BindingMethods({@BindingMethod(attribute = "space", method = "setSpace", type = AudioSeekBar.class), @BindingMethod(attribute = "title", method = "setTitle", type = AudioSeekBar.class), @BindingMethod(attribute = "progress", method = "setProgress", type = AudioSeekBar.class), @BindingMethod(attribute = "max", method = "setMax", type = AudioSeekBar.class), @BindingMethod(attribute = "LVisibility", method = "setLVisibility", type = AudioSeekBar.class), @BindingMethod(attribute = "RVisibility", method = "setRVisibility", type = AudioSeekBar.class), @BindingMethod(attribute = "OnSeekBarChangeListener", method = "setOnSeekBarChangeListener", type = AudioSeekBar.class)})
public class AudioSeekBar extends LinearLayout {
    private static final String TAG = ("KSWLauncher." + AudioSeekBar.class.getSimpleName());
    private static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private TextView ltextView;
    private TextView rtextView;
    private SeekBar seekBar;
    private Space space;
    private TextView titleView;

    public AudioSeekBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public AudioSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AudioSeekBar);
        View view = LayoutInflater.from(context).inflate(R.layout.audi_seekbar, this);
        this.space = (Space) view.findViewById(R.id.space);
        this.titleView = (TextView) view.findViewById(R.id.audio_seekbar_title);
        this.ltextView = (TextView) view.findViewById(R.id.audio_seekbar_left_text);
        this.rtextView = (TextView) view.findViewById(R.id.audio_seekbar_right_text);
        this.seekBar = (SeekBar) view.findViewById(R.id.audio_seekbar);
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case 0:
                    setMax(a.getInt(attr, 100));
                    break;
                case 1:
                    int space2 = a.getInt(attr, 0);
                    setProgress(space2);
                    setLText(space2);
                    setRText(space2);
                    break;
                case 2:
                    setSpace(a.getInt(attr, 0));
                    break;
                case 3:
                    setTitle(a.getResourceId(attr, 0));
                    break;
                case 4:
                    setLVisibility(VISIBILITY_FLAGS[a.getInt(attr, 0)]);
                    break;
                case 5:
                    setRVisibility(VISIBILITY_FLAGS[a.getInt(attr, 0)]);
                    break;
            }
        }
        a.recycle();
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener) {
        if (listener != null) {
            this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    AudioSeekBar.this.setRText(progress);
                    AudioSeekBar.this.setLText(progress);
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setRText(int progress) {
        TextView textView = this.rtextView;
        textView.setText("" + progress);
    }

    /* access modifiers changed from: private */
    public void setLText(int progress) {
        TextView textView = this.ltextView;
        textView.setText("" + progress);
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
