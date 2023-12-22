package skin.support.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.p004v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;
import skin.support.C1899R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes.dex */
public class SkinCompatSpinner extends AppCompatSpinner implements SkinCompatSupportable {
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mPopupBackgroundResId;
    private static final String TAG = SkinCompatSpinner.class.getSimpleName();
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {16843505};

    public SkinCompatSpinner(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatSpinner(Context context, int mode) {
        this(context, null, C1899R.attr.spinnerStyle, mode);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, C1899R.attr.spinnerStyle);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        this(context, attrs, defStyleAttr, mode, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r2 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r2.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003f, code lost:
        if (r2 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
        this.mPopupBackgroundResId = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, C1899R.styleable.Spinner, defStyleAttr, 0);
        if (getPopupContext() != null) {
            if (mode == -1) {
                if (Build.VERSION.SDK_INT >= 11) {
                    TypedArray aa = null;
                    try {
                        try {
                            aa = context.obtainStyledAttributes(attrs, ATTRS_ANDROID_SPINNERMODE, defStyleAttr, 0);
                            mode = aa.hasValue(0) ? aa.getInt(0, 0) : mode;
                        } catch (Exception e) {
                            Log.i(TAG, "Could not read android:spinnerMode", e);
                        }
                    } catch (Throwable th) {
                        if (aa != null) {
                            aa.recycle();
                        }
                        throw th;
                    }
                } else {
                    mode = 1;
                }
            }
            if (mode == 1) {
                TypedArray pa = getPopupContext().obtainStyledAttributes(attrs, C1899R.styleable.Spinner, defStyleAttr, 0);
                this.mPopupBackgroundResId = pa.getResourceId(C1899R.styleable.Spinner_android_popupBackground, 0);
                pa.recycle();
            }
        }
        a.recycle();
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = skinCompatBackgroundHelper;
        skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override // android.support.p004v7.widget.AppCompatSpinner, android.widget.Spinner
    public void setPopupBackgroundResource(int resId) {
        super.setPopupBackgroundResource(resId);
        this.mPopupBackgroundResId = resId;
        applyPopupBackground();
    }

    private void applyPopupBackground() {
        int checkResourceId = SkinCompatHelper.checkResourceId(this.mPopupBackgroundResId);
        this.mPopupBackgroundResId = checkResourceId;
        if (checkResourceId != 0) {
            setPopupBackgroundDrawable(SkinCompatResources.getDrawableCompat(getContext(), this.mPopupBackgroundResId));
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        applyPopupBackground();
    }
}
