package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(@Nullable Context context) {
        super(context);
    }

    public AlertDialogLayout(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!tryOnMeasure(widthMeasureSpec, heightMeasureSpec)) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean tryOnMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childHeightSpec;
        int i = widthMeasureSpec;
        int i2 = heightMeasureSpec;
        int count = getChildCount();
        View middlePanel = null;
        View buttonPanel = null;
        View topPanel = null;
        for (int i3 = 0; i3 < count; i3++) {
            View child = getChildAt(i3);
            if (child.getVisibility() != 8) {
                int id = child.getId();
                if (id == R.id.topPanel) {
                    topPanel = child;
                } else if (id == R.id.buttonPanel) {
                    buttonPanel = child;
                } else if ((id != R.id.contentPanel && id != R.id.customPanel) || middlePanel != null) {
                    return false;
                } else {
                    middlePanel = child;
                }
            }
        }
        int i4 = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int childState = 0;
        int usedHeight = getPaddingTop() + getPaddingBottom();
        if (topPanel != null) {
            topPanel.measure(i, 0);
            usedHeight += topPanel.getMeasuredHeight();
            childState = View.combineMeasuredStates(0, topPanel.getMeasuredState());
        }
        int buttonHeight = 0;
        int buttonWantsHeight = 0;
        if (buttonPanel != null) {
            buttonPanel.measure(i, 0);
            buttonHeight = resolveMinimumHeight(buttonPanel);
            buttonWantsHeight = buttonPanel.getMeasuredHeight() - buttonHeight;
            usedHeight += buttonHeight;
            childState = View.combineMeasuredStates(childState, buttonPanel.getMeasuredState());
        }
        int middleHeight = 0;
        if (middlePanel != null) {
            if (i4 == 0) {
                childHeightSpec = 0;
                View view = topPanel;
            } else {
                View view2 = topPanel;
                childHeightSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, heightSize - usedHeight), i4);
            }
            middlePanel.measure(i, childHeightSpec);
            middleHeight = middlePanel.getMeasuredHeight();
            usedHeight += middleHeight;
            childState = View.combineMeasuredStates(childState, middlePanel.getMeasuredState());
        }
        int remainingHeight = heightSize - usedHeight;
        if (buttonPanel != null) {
            int usedHeight2 = usedHeight - buttonHeight;
            int heightToGive = Math.min(remainingHeight, buttonWantsHeight);
            if (heightToGive > 0) {
                remainingHeight -= heightToGive;
                buttonHeight += heightToGive;
            }
            buttonPanel.measure(i, View.MeasureSpec.makeMeasureSpec(buttonHeight, 1073741824));
            usedHeight = usedHeight2 + buttonPanel.getMeasuredHeight();
            childState = View.combineMeasuredStates(childState, buttonPanel.getMeasuredState());
            remainingHeight = remainingHeight;
        }
        if (middlePanel == null || remainingHeight <= 0) {
            int heightMode = i4;
        } else {
            int heightToGive2 = remainingHeight;
            middlePanel.measure(i, View.MeasureSpec.makeMeasureSpec(middleHeight + heightToGive2, i4));
            usedHeight = (usedHeight - middleHeight) + middlePanel.getMeasuredHeight();
            int i5 = i4;
            childState = View.combineMeasuredStates(childState, middlePanel.getMeasuredState());
            remainingHeight -= heightToGive2;
        }
        int maxWidth = 0;
        int i6 = 0;
        while (i6 < count) {
            View child2 = getChildAt(i6);
            int remainingHeight2 = remainingHeight;
            View buttonPanel2 = buttonPanel;
            if (child2.getVisibility() != 8) {
                maxWidth = Math.max(maxWidth, child2.getMeasuredWidth());
            }
            i6++;
            remainingHeight = remainingHeight2;
            buttonPanel = buttonPanel2;
        }
        View view3 = buttonPanel;
        setMeasuredDimension(View.resolveSizeAndState(maxWidth + getPaddingLeft() + getPaddingRight(), i, childState), View.resolveSizeAndState(usedHeight, i2, 0));
        if (widthMode == 1073741824) {
            return true;
        }
        forceUniformWidth(count, i2);
        return true;
    }

    private void forceUniformWidth(int count, int heightMeasureSpec) {
        int uniformMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams) child.getLayoutParams();
                if (lp.width == -1) {
                    int oldHeight = lp.height;
                    lp.height = child.getMeasuredHeight();
                    measureChildWithMargins(child, uniformMeasureSpec, 0, heightMeasureSpec, 0);
                    lp.height = oldHeight;
                }
            }
        }
    }

    private static int resolveMinimumHeight(View v) {
        int minHeight = ViewCompat.getMinimumHeight(v);
        if (minHeight > 0) {
            return minHeight;
        }
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            if (vg.getChildCount() == 1) {
                return resolveMinimumHeight(vg.getChildAt(0));
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childTop;
        int count;
        int i;
        int majorGravity;
        int childLeft;
        AlertDialogLayout alertDialogLayout = this;
        int paddingLeft = getPaddingLeft();
        int width = right - left;
        int childRight = width - getPaddingRight();
        int childSpace = (width - paddingLeft) - getPaddingRight();
        int totalLength = getMeasuredHeight();
        int count2 = getChildCount();
        int gravity = getGravity();
        int majorGravity2 = gravity & 112;
        int minorGravity = gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (majorGravity2 == 16) {
            childTop = getPaddingTop() + (((bottom - top) - totalLength) / 2);
        } else if (majorGravity2 != 80) {
            childTop = getPaddingTop();
        } else {
            childTop = ((getPaddingTop() + bottom) - top) - totalLength;
        }
        Drawable dividerDrawable = getDividerDrawable();
        int i2 = 0;
        int dividerHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();
        while (true) {
            int i3 = i2;
            if (i3 < count2) {
                View child = alertDialogLayout.getChildAt(i3);
                if (child == null || child.getVisibility() == 8) {
                    i = i3;
                    majorGravity = majorGravity2;
                    count = count2;
                } else {
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    LinearLayoutCompat.LayoutParams lp = (LinearLayoutCompat.LayoutParams) child.getLayoutParams();
                    int layoutGravity = lp.gravity;
                    if (layoutGravity < 0) {
                        layoutGravity = minorGravity;
                    }
                    int i4 = layoutGravity;
                    int layoutGravity2 = GravityCompat.getAbsoluteGravity(layoutGravity, ViewCompat.getLayoutDirection(this)) & 7;
                    majorGravity = majorGravity2;
                    if (layoutGravity2 != 1) {
                        childLeft = layoutGravity2 != 5 ? lp.leftMargin + paddingLeft : (childRight - childWidth) - lp.rightMargin;
                    } else {
                        childLeft = ((((childSpace - childWidth) / 2) + paddingLeft) + lp.leftMargin) - lp.rightMargin;
                    }
                    if (alertDialogLayout.hasDividerBeforeChildAt(i3)) {
                        childTop += dividerHeight;
                    }
                    int childTop2 = childTop + lp.topMargin;
                    i = i3;
                    count = count2;
                    setChildFrame(child, childLeft, childTop2, childWidth, childHeight);
                    childTop = childTop2 + childHeight + lp.bottomMargin;
                }
                i2 = i + 1;
                majorGravity2 = majorGravity;
                count2 = count;
                alertDialogLayout = this;
            } else {
                int i5 = count2;
                return;
            }
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }
}
