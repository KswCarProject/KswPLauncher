package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v7.appcompat.R;
import android.widget.TextView;
import java.lang.ref.WeakReference;

class AppCompatTextHelper {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private boolean mAsyncFontPending;
    @NonNull
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mStyle = 0;
    private final TextView mView;

    AppCompatTextHelper(TextView view) {
        this.mView = view;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(this.mView);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r19, int r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            android.widget.TextView r3 = r0.mView
            android.content.Context r3 = r3.getContext()
            android.support.v7.widget.AppCompatDrawableManager r4 = android.support.v7.widget.AppCompatDrawableManager.get()
            int[] r5 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper
            r6 = 0
            android.support.v7.widget.TintTypedArray r5 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r3, r1, r5, r2, r6)
            int r7 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_textAppearance
            r8 = -1
            int r7 = r5.getResourceId(r7, r8)
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft
            boolean r9 = r5.hasValue(r9)
            if (r9 == 0) goto L_0x0032
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft
            int r9 = r5.getResourceId(r9, r6)
            android.support.v7.widget.TintInfo r9 = createTintInfo(r3, r4, r9)
            r0.mDrawableLeftTint = r9
        L_0x0032:
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop
            boolean r9 = r5.hasValue(r9)
            if (r9 == 0) goto L_0x0046
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop
            int r9 = r5.getResourceId(r9, r6)
            android.support.v7.widget.TintInfo r9 = createTintInfo(r3, r4, r9)
            r0.mDrawableTopTint = r9
        L_0x0046:
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight
            boolean r9 = r5.hasValue(r9)
            if (r9 == 0) goto L_0x005a
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight
            int r9 = r5.getResourceId(r9, r6)
            android.support.v7.widget.TintInfo r9 = createTintInfo(r3, r4, r9)
            r0.mDrawableRightTint = r9
        L_0x005a:
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom
            boolean r9 = r5.hasValue(r9)
            if (r9 == 0) goto L_0x006e
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom
            int r9 = r5.getResourceId(r9, r6)
            android.support.v7.widget.TintInfo r9 = createTintInfo(r3, r4, r9)
            r0.mDrawableBottomTint = r9
        L_0x006e:
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 17
            if (r9 < r10) goto L_0x009c
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart
            boolean r9 = r5.hasValue(r9)
            if (r9 == 0) goto L_0x0088
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart
            int r9 = r5.getResourceId(r9, r6)
            android.support.v7.widget.TintInfo r9 = createTintInfo(r3, r4, r9)
            r0.mDrawableStartTint = r9
        L_0x0088:
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd
            boolean r9 = r5.hasValue(r9)
            if (r9 == 0) goto L_0x009c
            int r9 = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd
            int r9 = r5.getResourceId(r9, r6)
            android.support.v7.widget.TintInfo r9 = createTintInfo(r3, r4, r9)
            r0.mDrawableEndTint = r9
        L_0x009c:
            r5.recycle()
            android.widget.TextView r9 = r0.mView
            android.text.method.TransformationMethod r9 = r9.getTransformationMethod()
            boolean r9 = r9 instanceof android.text.method.PasswordTransformationMethod
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 23
            if (r7 == r8) goto L_0x00fd
            int[] r8 = android.support.v7.appcompat.R.styleable.TextAppearance
            android.support.v7.widget.TintTypedArray r5 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r3, (int) r7, (int[]) r8)
            if (r9 != 0) goto L_0x00c8
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x00c8
            r8 = 1
            int r11 = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r10 = r5.getBoolean(r11, r6)
            r11 = r8
        L_0x00c8:
            r0.updateTypefaceAndStyle(r3, r5)
            int r8 = android.os.Build.VERSION.SDK_INT
            if (r8 >= r15) goto L_0x00fa
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x00dd
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor
            android.content.res.ColorStateList r12 = r5.getColorStateList(r8)
        L_0x00dd:
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x00eb
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint
            android.content.res.ColorStateList r13 = r5.getColorStateList(r8)
        L_0x00eb:
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x00fa
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink
            android.content.res.ColorStateList r8 = r5.getColorStateList(r8)
            r14 = r8
        L_0x00fa:
            r5.recycle()
        L_0x00fd:
            int[] r8 = android.support.v7.appcompat.R.styleable.TextAppearance
            android.support.v7.widget.TintTypedArray r5 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes(r3, r1, r8, r2, r6)
            if (r9 != 0) goto L_0x0114
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x0114
            r11 = 1
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps
            boolean r10 = r5.getBoolean(r8, r6)
        L_0x0114:
            int r8 = android.os.Build.VERSION.SDK_INT
            if (r8 >= r15) goto L_0x0144
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x0127
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor
            android.content.res.ColorStateList r8 = r5.getColorStateList(r8)
            r12 = r8
        L_0x0127:
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x0136
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint
            android.content.res.ColorStateList r8 = r5.getColorStateList(r8)
            r13 = r8
        L_0x0136:
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x0144
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink
            android.content.res.ColorStateList r14 = r5.getColorStateList(r8)
        L_0x0144:
            int r8 = android.os.Build.VERSION.SDK_INT
            r15 = 28
            if (r8 < r15) goto L_0x0161
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize
            boolean r8 = r5.hasValue(r8)
            if (r8 == 0) goto L_0x0161
            int r8 = android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize
            r15 = -1
            int r8 = r5.getDimensionPixelSize(r8, r15)
            if (r8 != 0) goto L_0x0161
            android.widget.TextView r8 = r0.mView
            r15 = 0
            r8.setTextSize(r6, r15)
        L_0x0161:
            r0.updateTypefaceAndStyle(r3, r5)
            r5.recycle()
            if (r12 == 0) goto L_0x016e
            android.widget.TextView r8 = r0.mView
            r8.setTextColor(r12)
        L_0x016e:
            if (r13 == 0) goto L_0x0175
            android.widget.TextView r8 = r0.mView
            r8.setHintTextColor(r13)
        L_0x0175:
            if (r14 == 0) goto L_0x017c
            android.widget.TextView r8 = r0.mView
            r8.setLinkTextColor(r14)
        L_0x017c:
            if (r9 != 0) goto L_0x0183
            if (r11 == 0) goto L_0x0183
            r0.setAllCaps(r10)
        L_0x0183:
            android.graphics.Typeface r8 = r0.mFontTypeface
            if (r8 == 0) goto L_0x0190
            android.widget.TextView r8 = r0.mView
            android.graphics.Typeface r15 = r0.mFontTypeface
            int r6 = r0.mStyle
            r8.setTypeface(r15, r6)
        L_0x0190:
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper r6 = r0.mAutoSizeTextHelper
            r6.loadFromAttributes(r1, r2)
            boolean r6 = android.support.v4.widget.AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE
            if (r6 == 0) goto L_0x01df
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper r6 = r0.mAutoSizeTextHelper
            int r6 = r6.getAutoSizeTextType()
            if (r6 == 0) goto L_0x01df
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper r6 = r0.mAutoSizeTextHelper
            int[] r6 = r6.getAutoSizeTextAvailableSizes()
            int r8 = r6.length
            if (r8 <= 0) goto L_0x01df
            android.widget.TextView r8 = r0.mView
            int r8 = r8.getAutoSizeStepGranularity()
            float r8 = (float) r8
            r15 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r8 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r8 == 0) goto L_0x01d4
            android.widget.TextView r8 = r0.mView
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper r15 = r0.mAutoSizeTextHelper
            int r15 = r15.getAutoSizeMinTextSize()
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper r2 = r0.mAutoSizeTextHelper
            int r2 = r2.getAutoSizeMaxTextSize()
            r16 = r4
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper r4 = r0.mAutoSizeTextHelper
            int r4 = r4.getAutoSizeStepGranularity()
            r17 = r5
            r5 = 0
            r8.setAutoSizeTextTypeUniformWithConfiguration(r15, r2, r4, r5)
            goto L_0x01e3
        L_0x01d4:
            r16 = r4
            r17 = r5
            r5 = 0
            android.widget.TextView r2 = r0.mView
            r2.setAutoSizeTextTypeUniformWithPresetSizes(r6, r5)
            goto L_0x01e3
        L_0x01df:
            r16 = r4
            r17 = r5
        L_0x01e3:
            int[] r2 = android.support.v7.appcompat.R.styleable.AppCompatTextView
            android.support.v7.widget.TintTypedArray r2 = android.support.v7.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r3, (android.util.AttributeSet) r1, (int[]) r2)
            int r4 = android.support.v7.appcompat.R.styleable.AppCompatTextView_firstBaselineToTopHeight
            r5 = -1
            int r4 = r2.getDimensionPixelSize(r4, r5)
            int r6 = android.support.v7.appcompat.R.styleable.AppCompatTextView_lastBaselineToBottomHeight
            int r6 = r2.getDimensionPixelSize(r6, r5)
            int r8 = android.support.v7.appcompat.R.styleable.AppCompatTextView_lineHeight
            int r8 = r2.getDimensionPixelSize(r8, r5)
            r2.recycle()
            if (r4 == r5) goto L_0x0206
            android.widget.TextView r15 = r0.mView
            android.support.v4.widget.TextViewCompat.setFirstBaselineToTopHeight(r15, r4)
        L_0x0206:
            if (r6 == r5) goto L_0x020d
            android.widget.TextView r15 = r0.mView
            android.support.v4.widget.TextViewCompat.setLastBaselineToBottomHeight(r15, r6)
        L_0x020d:
            if (r8 == r5) goto L_0x0214
            android.widget.TextView r5 = r0.mView
            android.support.v4.widget.TextViewCompat.setLineHeight(r5, r8)
        L_0x0214:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray a) {
        String fontFamilyName;
        this.mStyle = a.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
        boolean z = true;
        if (a.hasValue(R.styleable.TextAppearance_android_fontFamily) || a.hasValue(R.styleable.TextAppearance_fontFamily)) {
            this.mFontTypeface = null;
            int fontFamilyId = a.hasValue(R.styleable.TextAppearance_fontFamily) ? R.styleable.TextAppearance_fontFamily : R.styleable.TextAppearance_android_fontFamily;
            if (!context.isRestricted()) {
                final WeakReference<TextView> textViewWeak = new WeakReference<>(this.mView);
                try {
                    this.mFontTypeface = a.getFont(fontFamilyId, this.mStyle, new ResourcesCompat.FontCallback() {
                        public void onFontRetrieved(@NonNull Typeface typeface) {
                            AppCompatTextHelper.this.onAsyncTypefaceReceived(textViewWeak, typeface);
                        }

                        public void onFontRetrievalFailed(int reason) {
                        }
                    });
                    if (this.mFontTypeface != null) {
                        z = false;
                    }
                    this.mAsyncFontPending = z;
                } catch (Resources.NotFoundException | UnsupportedOperationException e) {
                }
            }
            if (this.mFontTypeface == null && (fontFamilyName = a.getString(fontFamilyId)) != null) {
                this.mFontTypeface = Typeface.create(fontFamilyName, this.mStyle);
            }
        } else if (a.hasValue(R.styleable.TextAppearance_android_typeface)) {
            this.mAsyncFontPending = false;
            switch (a.getInt(R.styleable.TextAppearance_android_typeface, 1)) {
                case 1:
                    this.mFontTypeface = Typeface.SANS_SERIF;
                    return;
                case 2:
                    this.mFontTypeface = Typeface.SERIF;
                    return;
                case 3:
                    this.mFontTypeface = Typeface.MONOSPACE;
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onAsyncTypefaceReceived(WeakReference<TextView> textViewWeak, Typeface typeface) {
        if (this.mAsyncFontPending) {
            this.mFontTypeface = typeface;
            TextView textView = (TextView) textViewWeak.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.mStyle);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetTextAppearance(Context context, int resId) {
        ColorStateList textColor;
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, resId, R.styleable.TextAppearance);
        if (a.hasValue(R.styleable.TextAppearance_textAllCaps)) {
            setAllCaps(a.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
        }
        if (Build.VERSION.SDK_INT < 23 && a.hasValue(R.styleable.TextAppearance_android_textColor) && (textColor = a.getColorStateList(R.styleable.TextAppearance_android_textColor)) != null) {
            this.mView.setTextColor(textColor);
        }
        if (a.hasValue(R.styleable.TextAppearance_android_textSize) && a.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        updateTypefaceAndStyle(context, a);
        a.recycle();
        if (this.mFontTypeface != null) {
            this.mView.setTypeface(this.mFontTypeface, this.mStyle);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllCaps(boolean allCaps) {
        this.mView.setAllCaps(allCaps);
    }

    /* access modifiers changed from: package-private */
    public void applyCompoundDrawablesTints() {
        if (!(this.mDrawableLeftTint == null && this.mDrawableTopTint == null && this.mDrawableRightTint == null && this.mDrawableBottomTint == null)) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
            applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
            applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
        }
        if (Build.VERSION.SDK_INT < 17) {
            return;
        }
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawables2 = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawables2[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawables2[2], this.mDrawableEndTint);
        }
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo info) {
        if (drawable != null && info != null) {
            AppCompatDrawableManager.tintDrawable(drawable, info, this.mView.getDrawableState());
        }
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager drawableManager, int drawableId) {
        ColorStateList tintList = drawableManager.getTintList(context, drawableId);
        if (tintList == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = tintList;
        return tintInfo;
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
            autoSizeText();
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTextSize(int unit, float size) {
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled()) {
            setTextSizeInternal(unit, size);
        }
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    private void setTextSizeInternal(int unit, float size) {
        this.mAutoSizeTextHelper.setTextSizeInternal(unit, size);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeWithDefaults(int autoSizeTextType) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(autoSizeTextType);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
    }

    /* access modifiers changed from: package-private */
    public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] presetSizes, int unit) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    /* access modifiers changed from: package-private */
    public int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    /* access modifiers changed from: package-private */
    public int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }
}
