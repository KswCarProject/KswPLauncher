package com.wits.ksw.launcher.bmw_id8_ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
public class ID8ProgressBar extends View {
    private static final String TAG = "ID8ProgressBar";
    private Bitmap bgBitmap;
    private Paint bgPatin;
    public ContentObserver contentObserver;
    private Rect dstRect;
    private int height;
    private OnTouchChangeListener mTouchChangeListener;
    private OnValueChangeListener mValueChangeListener;
    private int max;
    private int offsetX;
    private float progress;
    private Bitmap progressAngeBitmap;
    private Bitmap progressAngeBitmapBlue;
    private Bitmap progressAngeBitmapRed;
    private Bitmap progressAngeBitmapYellow;
    private Bitmap progressBitmap;
    private Bitmap progressBitmapBlue;
    private Bitmap progressBitmapRed;
    private Bitmap progressBitmapYellow;
    private Rect srcRect;
    private Bitmap thumbBitmap;
    private int value;
    private int width;

    /* loaded from: classes15.dex */
    public interface OnTouchChangeListener {
        void onStartTrackingTouch(ID8ProgressBar progressBar);

        void onStopTrackingTouch(ID8ProgressBar progressBar);
    }

    /* loaded from: classes15.dex */
    public interface OnValueChangeListener {
        void onValueChange(ID8ProgressBar progressBar, int value, float progress);
    }

    public ID8ProgressBar(Context context) {
        this(context, null);
    }

    public ID8ProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ID8ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.contentObserver = new ContentObserver(new Handler()) { // from class: com.wits.ksw.launcher.bmw_id8_ui.view.ID8ProgressBar.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                Log.d(ID8ProgressBar.TAG, "onChange: 11111111111");
                ID8ProgressBar.this.configSkinResources();
                ID8ProgressBar.this.invalidate();
            }
        };
        TypedArray typedArray = context.obtainStyledAttributes(attrs, C0899R.styleable.ID8ProgressBar);
        this.max = typedArray.getInt(0, 100);
        this.value = typedArray.getInt(1, 80);
        this.thumbBitmap = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar_slider);
        this.bgBitmap = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar_n);
        this.progressBitmapYellow = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar_y_d);
        this.progressAngeBitmapYellow = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar2_y_d);
        this.progressBitmapBlue = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar_b_d);
        this.progressAngeBitmapBlue = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar2_b_d);
        this.progressBitmapRed = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar_r_d);
        this.progressAngeBitmapRed = BitmapFactory.decodeResource(context.getResources(), C0899R.C0900drawable.id8_settings_system_progress_bar2_r_d);
        configSkinResources();
        this.offsetX = this.thumbBitmap.getWidth();
        this.srcRect = new Rect(0, 0, this.progressBitmap.getWidth(), this.progressBitmap.getHeight());
        Paint paint = new Paint();
        this.bgPatin = paint;
        paint.setAntiAlias(true);
        setValue(this.value);
        registerSkinObserver(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configSkinResources() {
        String skinName = ID8LauncherConstants.loadCurrentSkin();
        Log.w(TAG, "ID8ProgressBar: " + skinName);
        if (ID8LauncherConstants.ID8_SKIN_SPORT.equals(skinName)) {
            this.progressBitmap = this.progressBitmapRed;
            this.progressAngeBitmap = this.progressAngeBitmapRed;
        } else if (ID8LauncherConstants.ID8_SKIN_EFFICIENT.equals(skinName)) {
            this.progressBitmap = this.progressBitmapBlue;
            this.progressAngeBitmap = this.progressAngeBitmapBlue;
        } else {
            this.progressBitmap = this.progressBitmapYellow;
            this.progressAngeBitmap = this.progressAngeBitmapYellow;
        }
    }

    private void registerSkinObserver(Context context) {
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(ID8LauncherConstants.ID8_SKIN), true, this.contentObserver);
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    public void setValue(int i) {
        if (i > this.max || i < 0) {
            return;
        }
        this.value = i;
        setProgressBySet(i);
    }

    private void setProgressBySet(int value) {
        float percent = value / this.max;
        if (percent < 0.0f) {
            percent = 0.0f;
        }
        if (percent > 1.0f) {
            percent = 1.0f;
        }
        this.progress = percent;
        OnValueChangeListener onValueChangeListener = this.mValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this, value, percent);
        }
        invalidate();
    }

    private void setProgressByDrag(float x) {
        float percent = x / (this.width - this.offsetX);
        if (percent < 0.0f) {
            percent = 0.0f;
        }
        if (percent > 1.0f) {
            percent = 1.0f;
        }
        int i = this.max;
        int i2 = (int) (i * percent);
        this.value = i2;
        float percent2 = i2 / i;
        this.progress = percent2;
        OnValueChangeListener onValueChangeListener = this.mValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this, i2, percent2);
        }
        invalidate();
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
        canvas.drawBitmap(this.bgBitmap, 0.0f, 0.0f, this.bgPatin);
        float f = this.progress;
        int currentPosition = (int) ((this.width - this.offsetX) * f);
        if (f == 0.0f) {
            canvas.drawBitmap(this.thumbBitmap, 0.0f, 0.0f, this.bgPatin);
            return;
        }
        Rect rect = new Rect(0, 0, currentPosition, this.height);
        this.dstRect = rect;
        canvas.drawBitmap(this.progressBitmap, this.srcRect, rect, this.bgPatin);
        if (this.progress != 0.0f) {
            canvas.drawBitmap(this.progressAngeBitmap, currentPosition - 3, 0.0f, this.bgPatin);
        }
        canvas.drawBitmap(this.thumbBitmap, currentPosition, 0.0f, this.bgPatin);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        float x;
        OnTouchChangeListener onTouchChangeListener;
        if (event.getAction() == 0 && (onTouchChangeListener = this.mTouchChangeListener) != null) {
            onTouchChangeListener.onStartTrackingTouch(this);
        }
        float x2 = event.getX();
        if (x2 < 0.0f) {
            return true;
        }
        int i = this.offsetX;
        if (x2 < i) {
            x = 0.0f;
        } else {
            x = x2 - i;
        }
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

    public int getValue() {
        return this.value;
    }
}
