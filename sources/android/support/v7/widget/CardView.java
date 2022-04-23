package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.v7.cardview.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = {16842801};
    private static final CardViewImpl IMPL;
    private final CardViewDelegate mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new CardViewApi21Impl();
        } else if (Build.VERSION.SDK_INT >= 17) {
            IMPL = new CardViewApi17Impl();
        } else {
            IMPL = new CardViewBaseImpl();
        }
        IMPL.initStatic();
    }

    public CardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CardView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.cardViewStyle);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ColorStateList backgroundColor;
        float maxElevation;
        int i;
        Rect rect = new Rect();
        this.mContentPadding = rect;
        this.mShadowBounds = new Rect();
        AnonymousClass1 r4 = new CardViewDelegate() {
            private Drawable mCardBackground;

            public void setCardBackground(Drawable drawable) {
                this.mCardBackground = drawable;
                CardView.this.setBackgroundDrawable(drawable);
            }

            public boolean getUseCompatPadding() {
                return CardView.this.getUseCompatPadding();
            }

            public boolean getPreventCornerOverlap() {
                return CardView.this.getPreventCornerOverlap();
            }

            public void setShadowPadding(int left, int top, int right, int bottom) {
                CardView.this.mShadowBounds.set(left, top, right, bottom);
                CardView cardView = CardView.this;
                CardView.super.setPadding(cardView.mContentPadding.left + left, CardView.this.mContentPadding.top + top, CardView.this.mContentPadding.right + right, CardView.this.mContentPadding.bottom + bottom);
            }

            public void setMinWidthHeightInternal(int width, int height) {
                if (width > CardView.this.mUserSetMinWidth) {
                    CardView.super.setMinimumWidth(width);
                }
                if (height > CardView.this.mUserSetMinHeight) {
                    CardView.super.setMinimumHeight(height);
                }
            }

            public Drawable getCardBackground() {
                return this.mCardBackground;
            }

            public View getCardView() {
                return CardView.this;
            }
        };
        this.mCardViewDelegate = r4;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardView, defStyleAttr, R.style.CardView);
        if (a.hasValue(R.styleable.CardView_cardBackgroundColor)) {
            backgroundColor = a.getColorStateList(R.styleable.CardView_cardBackgroundColor);
        } else {
            TypedArray aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int themeColorBackground = aa.getColor(0, 0);
            aa.recycle();
            float[] hsv = new float[3];
            Color.colorToHSV(themeColorBackground, hsv);
            if (hsv[2] > 0.5f) {
                i = getResources().getColor(R.color.cardview_light_background);
            } else {
                i = getResources().getColor(R.color.cardview_dark_background);
            }
            backgroundColor = ColorStateList.valueOf(i);
        }
        float radius = a.getDimension(R.styleable.CardView_cardCornerRadius, 0.0f);
        float elevation = a.getDimension(R.styleable.CardView_cardElevation, 0.0f);
        float maxElevation2 = a.getDimension(R.styleable.CardView_cardMaxElevation, 0.0f);
        this.mCompatPadding = a.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = a.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
        int defaultPadding = a.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
        rect.left = a.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, defaultPadding);
        rect.top = a.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, defaultPadding);
        rect.right = a.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, defaultPadding);
        rect.bottom = a.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, defaultPadding);
        if (elevation > maxElevation2) {
            maxElevation = elevation;
        } else {
            maxElevation = maxElevation2;
        }
        this.mUserSetMinWidth = a.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = a.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
        a.recycle();
        int i2 = defaultPadding;
        IMPL.initialize(r4, context, backgroundColor, radius, elevation, maxElevation);
    }

    public void setPadding(int left, int top, int right, int bottom) {
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    public void setUseCompatPadding(boolean useCompatPadding) {
        if (this.mCompatPadding != useCompatPadding) {
            this.mCompatPadding = useCompatPadding;
            IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }
    }

    public void setContentPadding(int left, int top, int right, int bottom) {
        this.mContentPadding.set(left, top, right, bottom);
        IMPL.updatePadding(this.mCardViewDelegate);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CardViewImpl cardViewImpl = IMPL;
        if (!(cardViewImpl instanceof CardViewApi21Impl)) {
            int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
            switch (widthMode) {
                case Integer.MIN_VALUE:
                case BasicMeasure.EXACTLY:
                    widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) cardViewImpl.getMinWidth(this.mCardViewDelegate)), View.MeasureSpec.getSize(widthMeasureSpec)), widthMode);
                    break;
            }
            int minWidth = View.MeasureSpec.getMode(heightMeasureSpec);
            switch (minWidth) {
                case Integer.MIN_VALUE:
                case BasicMeasure.EXACTLY:
                    heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil((double) cardViewImpl.getMinHeight(this.mCardViewDelegate)), View.MeasureSpec.getSize(heightMeasureSpec)), minWidth);
                    break;
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMinimumWidth(int minWidth) {
        this.mUserSetMinWidth = minWidth;
        super.setMinimumWidth(minWidth);
    }

    public void setMinimumHeight(int minHeight) {
        this.mUserSetMinHeight = minHeight;
        super.setMinimumHeight(minHeight);
    }

    public void setCardBackgroundColor(int color) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(color));
    }

    public void setCardBackgroundColor(ColorStateList color) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, color);
    }

    public ColorStateList getCardBackgroundColor() {
        return IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public void setRadius(float radius) {
        IMPL.setRadius(this.mCardViewDelegate, radius);
    }

    public float getRadius() {
        return IMPL.getRadius(this.mCardViewDelegate);
    }

    public void setCardElevation(float elevation) {
        IMPL.setElevation(this.mCardViewDelegate, elevation);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float maxElevation) {
        IMPL.setMaxElevation(this.mCardViewDelegate, maxElevation);
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean preventCornerOverlap) {
        if (preventCornerOverlap != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = preventCornerOverlap;
            IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }
    }
}
