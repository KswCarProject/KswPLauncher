package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ButtonBarLayout extends LinearLayout {
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize = -1;
    private int mMinimumHeight = 0;

    public ButtonBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonBarLayout);
        this.mAllowStacking = ta.getBoolean(R.styleable.ButtonBarLayout_allowStacking, true);
        ta.recycle();
    }

    public void setAllowStacking(boolean allowStacking) {
        if (this.mAllowStacking != allowStacking) {
            this.mAllowStacking = allowStacking;
            if (!this.mAllowStacking && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int initialWidthMeasureSpec;
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        if (this.mAllowStacking) {
            if (widthSize > this.mLastWidthSize && isStacked()) {
                setStacked(false);
            }
            this.mLastWidthSize = widthSize;
        }
        boolean needsRemeasure = false;
        if (isStacked() || View.MeasureSpec.getMode(widthMeasureSpec) != 1073741824) {
            initialWidthMeasureSpec = widthMeasureSpec;
        } else {
            initialWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(widthSize, Integer.MIN_VALUE);
            needsRemeasure = true;
        }
        super.onMeasure(initialWidthMeasureSpec, heightMeasureSpec);
        if (this.mAllowStacking && !isStacked()) {
            if ((-16777216 & getMeasuredWidthAndState()) == 16777216) {
                setStacked(true);
                needsRemeasure = true;
            }
        }
        if (needsRemeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int minHeight = 0;
        int firstVisible = getNextVisibleChildIndex(0);
        if (firstVisible >= 0) {
            View firstButton = getChildAt(firstVisible);
            LinearLayout.LayoutParams firstParams = (LinearLayout.LayoutParams) firstButton.getLayoutParams();
            minHeight = 0 + getPaddingTop() + firstButton.getMeasuredHeight() + firstParams.topMargin + firstParams.bottomMargin;
            if (isStacked()) {
                int secondVisible = getNextVisibleChildIndex(firstVisible + 1);
                if (secondVisible >= 0) {
                    minHeight += getChildAt(secondVisible).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                }
            } else {
                minHeight += getPaddingBottom();
            }
        }
        if (ViewCompat.getMinimumHeight(this) != minHeight) {
            setMinimumHeight(minHeight);
        }
    }

    private int getNextVisibleChildIndex(int index) {
        int count = getChildCount();
        for (int i = index; i < count; i++) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
        }
        return -1;
    }

    public int getMinimumHeight() {
        return Math.max(this.mMinimumHeight, super.getMinimumHeight());
    }

    private void setStacked(boolean stacked) {
        setOrientation(stacked);
        setGravity(stacked ? 5 : 80);
        View spacer = findViewById(R.id.spacer);
        if (spacer != null) {
            spacer.setVisibility(stacked ? 8 : 4);
        }
        for (int i = getChildCount() - 2; i >= 0; i--) {
            bringChildToFront(getChildAt(i));
        }
    }

    private boolean isStacked() {
        return getOrientation() == 1;
    }
}
