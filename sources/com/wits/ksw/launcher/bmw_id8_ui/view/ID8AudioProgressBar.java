package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;

/* loaded from: classes15.dex */
public class ID8AudioProgressBar extends View {
    private static final String TAG = "ID8AudioProgressBar";
    private Bitmap bgBitmap;
    private Paint bgPatin;
    public ContentObserver contentObserver;
    private int height;
    private int[] mDrawables;
    private OnTouchChangeListener mTouchChangeListener;
    private int mValue;
    private OnValueChangeListener mValueChangeListener;
    private int max;
    private Bitmap progressBitmap;
    private int width;

    /* loaded from: classes15.dex */
    public interface OnTouchChangeListener {
        void onStartTrackingTouch(ID8AudioProgressBar progressBar);

        void onStopTrackingTouch(ID8AudioProgressBar progressBar);
    }

    /* loaded from: classes15.dex */
    public interface OnValueChangeListener {
        void onValueChange(ID8AudioProgressBar progressBar, int value);
    }

    public ID8AudioProgressBar(Context context) {
        this(context, null);
    }

    public ID8AudioProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ID8AudioProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDrawables = new int[]{C0899R.C0900drawable.id8_settings_audio_volume_l12, C0899R.C0900drawable.id8_settings_audio_volume_l11, C0899R.C0900drawable.id8_settings_audio_volume_l10, C0899R.C0900drawable.id8_settings_audio_volume_l9, C0899R.C0900drawable.id8_settings_audio_volume_l8, C0899R.C0900drawable.id8_settings_audio_volume_l7, C0899R.C0900drawable.id8_settings_audio_volume_l6, C0899R.C0900drawable.id8_settings_audio_volume_l5, C0899R.C0900drawable.id8_settings_audio_volume_l4, C0899R.C0900drawable.id8_settings_audio_volume_l3, C0899R.C0900drawable.id8_settings_audio_volume_l2, C0899R.C0900drawable.id8_settings_audio_volume_l1, C0899R.C0900drawable.id8_settings_audio_volume_0, C0899R.C0900drawable.id8_settings_audio_volume_1, C0899R.C0900drawable.id8_settings_audio_volume_2, C0899R.C0900drawable.id8_settings_audio_volume_3, C0899R.C0900drawable.id8_settings_audio_volume_4, C0899R.C0900drawable.id8_settings_audio_volume_5, C0899R.C0900drawable.id8_settings_audio_volume_6, C0899R.C0900drawable.id8_settings_audio_volume_7, C0899R.C0900drawable.id8_settings_audio_volume_8, C0899R.C0900drawable.id8_settings_audio_volume_9, C0899R.C0900drawable.id8_settings_audio_volume_10, C0899R.C0900drawable.id8_settings_audio_volume_11, C0899R.C0900drawable.id8_settings_audio_volume_12};
        this.contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.bmw_id8_ui.view.ID8AudioProgressBar.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                Log.d(ID8AudioProgressBar.TAG, "ID8_SKIN onChange");
            }
        };
        TypedArray typedArray = context.obtainStyledAttributes(attrs, C0899R.styleable.ID8_Audio_ProgressBar);
        this.max = typedArray.getInt(0, 24);
        this.mValue = typedArray.getInt(1, 12);
        Log.i(TAG, "ID8AudioProgressBar: max " + this.max + " mValue " + this.mValue);
        this.bgBitmap = BitmapFactory.decodeResource(context.getResources(), this.mDrawables[13]);
        Paint paint = new Paint();
        this.bgPatin = paint;
        paint.setAntiAlias(true);
        registerSkinObserver(context);
    }

    private void registerSkinObserver(Context context) {
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(ID8LauncherConstants.ID8_SKIN), true, this.contentObserver);
    }

    public static void setId8AudioProgressBarValue(ID8AudioProgressBar iD8AudioProgressBar, int value) {
        Log.i(TAG, "setId8AudioProgressBarValue: value " + value);
        iD8AudioProgressBar.setProgress(value);
    }

    public int getValue() {
        return this.mValue;
    }

    private void setProgress(int value) {
        Log.i(TAG, "setProgress: value " + value);
        this.mValue = value;
        int min = Math.min(value, this.max);
        this.mValue = min;
        this.mValue = Math.max(min, 0);
        invalidate();
    }

    private void setProgressByDrag(float x) {
        int percent = Math.max(Math.min(Math.round((this.max * x) / this.width), this.max), 0);
        Log.i(TAG, "setProgressByDrag: percent " + percent + " mValue " + this.mValue);
        if (this.mValue != percent) {
            this.mValue = percent;
            OnValueChangeListener onValueChangeListener = this.mValueChangeListener;
            if (onValueChangeListener != null) {
                onValueChangeListener.onValueChange(this, percent);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = this.bgBitmap.getWidth();
        int height = this.bgBitmap.getHeight();
        this.height = height;
        setMeasuredDimension(this.width, height);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), this.mDrawables[this.mValue]);
        this.progressBitmap = decodeResource;
        canvas.drawBitmap(decodeResource, 0.0f, 0.0f, this.bgPatin);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        OnTouchChangeListener onTouchChangeListener;
        if (event.getAction() == 0 && (onTouchChangeListener = this.mTouchChangeListener) != null) {
            onTouchChangeListener.onStartTrackingTouch(this);
        }
        float x = event.getX();
        Log.w(TAG, "onTouchEvent: x : " + x);
        setProgressByDrag(x);
        return true;
    }

    @Override // android.view.View
    public boolean isClickable() {
        return true;
    }

    @Override // android.view.View
    public boolean hasFocusable() {
        return true;
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.mValueChangeListener = listener;
    }

    public void setOnTouchChangeListener(OnTouchChangeListener listener) {
        this.mTouchChangeListener = listener;
    }
}
