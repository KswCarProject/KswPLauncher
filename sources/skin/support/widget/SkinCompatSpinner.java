package skin.support.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import skin.support.R;
import skin.support.content.res.SkinCompatResources;

public class SkinCompatSpinner extends AppCompatSpinner implements SkinCompatSupportable {
    private static final int[] ATTRS_ANDROID_SPINNERMODE = {16843505};
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = SkinCompatSpinner.class.getSimpleName();
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private int mPopupBackgroundResId;

    public SkinCompatSpinner(Context context) {
        this(context, (AttributeSet) null);
    }

    public SkinCompatSpinner(Context context, int mode) {
        this(context, (AttributeSet) null, R.attr.spinnerStyle, mode);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.spinnerStyle);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public SkinCompatSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        this(context, attrs, defStyleAttr, mode, (Resources.Theme) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r2 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r2.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        if (r2 == null) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SkinCompatSpinner(android.content.Context r7, android.util.AttributeSet r8, int r9, int r10, android.content.res.Resources.Theme r11) {
        /*
            r6 = this;
            r6.<init>(r7, r8, r9, r10, r11)
            r0 = 0
            r6.mPopupBackgroundResId = r0
            int[] r1 = skin.support.R.styleable.Spinner
            android.content.res.TypedArray r1 = r7.obtainStyledAttributes(r8, r1, r9, r0)
            android.content.Context r2 = r6.getPopupContext()
            if (r2 == 0) goto L_0x0062
            r2 = -1
            if (r10 != r2) goto L_0x004a
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 11
            if (r2 < r3) goto L_0x0049
            r2 = 0
            int[] r3 = ATTRS_ANDROID_SPINNERMODE     // Catch:{ Exception -> 0x0036 }
            android.content.res.TypedArray r3 = r7.obtainStyledAttributes(r8, r3, r9, r0)     // Catch:{ Exception -> 0x0036 }
            r2 = r3
            boolean r3 = r2.hasValue(r0)     // Catch:{ Exception -> 0x0036 }
            if (r3 == 0) goto L_0x002e
            int r3 = r2.getInt(r0, r0)     // Catch:{ Exception -> 0x0036 }
            r10 = r3
        L_0x002e:
            if (r2 == 0) goto L_0x0042
        L_0x0030:
            r2.recycle()
            goto L_0x0042
        L_0x0034:
            r0 = move-exception
            goto L_0x0043
        L_0x0036:
            r3 = move-exception
            java.lang.String r4 = TAG     // Catch:{ all -> 0x0034 }
            java.lang.String r5 = "Could not read android:spinnerMode"
            android.util.Log.i(r4, r5, r3)     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x0042
            goto L_0x0030
        L_0x0042:
            goto L_0x004a
        L_0x0043:
            if (r2 == 0) goto L_0x0048
            r2.recycle()
        L_0x0048:
            throw r0
        L_0x0049:
            r10 = 1
        L_0x004a:
            r2 = 1
            if (r10 != r2) goto L_0x0062
            android.content.Context r2 = r6.getPopupContext()
            int[] r3 = skin.support.R.styleable.Spinner
            android.content.res.TypedArray r2 = r2.obtainStyledAttributes(r8, r3, r9, r0)
            int r3 = skin.support.R.styleable.Spinner_android_popupBackground
            int r0 = r2.getResourceId(r3, r0)
            r6.mPopupBackgroundResId = r0
            r2.recycle()
        L_0x0062:
            r1.recycle()
            skin.support.widget.SkinCompatBackgroundHelper r0 = new skin.support.widget.SkinCompatBackgroundHelper
            r0.<init>(r6)
            r6.mBackgroundTintHelper = r0
            r0.loadFromAttributes(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: skin.support.widget.SkinCompatSpinner.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

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

    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        applyPopupBackground();
    }
}
