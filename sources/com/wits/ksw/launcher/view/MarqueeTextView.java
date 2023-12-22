package com.wits.ksw.launcher.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.p004v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

/* loaded from: classes16.dex */
public class MarqueeTextView extends AppCompatTextView implements SkinCompatSupportable {
    public String TAG;
    private SkinCompatBackgroundHelper mBackgroundTintHelper;
    private SkinCompatTextHelper mTextHelper;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TAG = MarqueeTextView.class.getSimpleName();
        if (UiThemeUtils.isBMW_ID8_UI(context) || UiThemeUtils.isUI_PEMP_ID8(context) || UiThemeUtils.isALS_ID7_UI(context) || UiThemeUtils.isUI_GS_ID8(context)) {
            SkinCompatBackgroundHelper skinCompatBackgroundHelper = new SkinCompatBackgroundHelper(this);
            this.mBackgroundTintHelper = skinCompatBackgroundHelper;
            skinCompatBackgroundHelper.loadFromAttributes(attrs, defStyleAttr);
            SkinCompatTextHelper create = SkinCompatTextHelper.create(this);
            this.mTextHelper = create;
            create.loadFromAttributes(attrs, defStyleAttr);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt where) {
        super.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        super.setText(text, type);
        Log.d(this.TAG, "text:" + ((Object) text));
    }

    @Override // android.support.p004v7.widget.AppCompatTextView, android.view.View
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(resId);
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.onSetBackgroundResource(resId);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int resId) {
        setTextAppearance(getContext(), resId);
    }

    @Override // android.support.p004v7.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.onSetTextAppearance(context, resId);
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        SkinCompatBackgroundHelper skinCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (skinCompatBackgroundHelper != null) {
            skinCompatBackgroundHelper.applySkin();
        }
        SkinCompatTextHelper skinCompatTextHelper = this.mTextHelper;
        if (skinCompatTextHelper != null) {
            skinCompatTextHelper.applySkin();
        }
    }
}
