package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ActionBarContextView extends AbsActionBarView {
    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public /* bridge */ /* synthetic */ void animateToVisibility(int i) {
        super.animateToVisibility(i);
    }

    public /* bridge */ /* synthetic */ boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return super.setupAnimatorToVisibility(i, j);
    }

    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.ActionMode, defStyle, 0);
        ViewCompat.setBackground(this, a.getDrawable(R.styleable.ActionMode_background));
        this.mTitleStyleRes = a.getResourceId(R.styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = a.getResourceId(R.styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = a.getLayoutDimension(R.styleable.ActionMode_height, 0);
        this.mCloseItemLayout = a.getResourceId(R.styleable.ActionMode_closeItemLayout, R.layout.abc_action_mode_close_item_material);
        a.recycle();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public void setContentHeight(int height) {
        this.mContentHeight = height;
    }

    public void setCustomView(View view) {
        if (this.mCustomView != null) {
            removeView(this.mCustomView);
        }
        this.mCustomView = view;
        if (!(view == null || this.mTitleLayout == null)) {
            removeView(this.mTitleLayout);
            this.mTitleLayout = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        initTitle();
    }

    public void setSubtitle(CharSequence subtitle) {
        this.mSubtitle = subtitle;
        initTitle();
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    private void initTitle() {
        if (this.mTitleLayout == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            this.mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(R.id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(R.id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean hasTitle = !TextUtils.isEmpty(this.mTitle);
        boolean hasSubtitle = !TextUtils.isEmpty(this.mSubtitle);
        int i = 8;
        this.mSubtitleView.setVisibility(hasSubtitle ? 0 : 8);
        LinearLayout linearLayout = this.mTitleLayout;
        if (hasTitle || hasSubtitle) {
            i = 0;
        }
        linearLayout.setVisibility(i);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    public void initForMode(final ActionMode mode) {
        if (this.mClose == null) {
            this.mClose = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
            addView(this.mClose);
        } else if (this.mClose.getParent() == null) {
            addView(this.mClose);
        }
        this.mClose.findViewById(R.id.action_mode_close_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mode.finish();
            }
        });
        MenuBuilder menu = (MenuBuilder) mode.getMenu();
        if (this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        menu.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        ViewCompat.setBackground(this.mMenuView, (Drawable) null);
        addView(this.mMenuView, layoutParams);
    }

    public void closeMode() {
        if (this.mClose == null) {
            killMode();
        }
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }

    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxHeight;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(heightMeasureSpec) != 0) {
            int contentWidth = View.MeasureSpec.getSize(widthMeasureSpec);
            if (this.mContentHeight > 0) {
                maxHeight = this.mContentHeight;
            } else {
                maxHeight = View.MeasureSpec.getSize(heightMeasureSpec);
            }
            int verticalPadding = getPaddingTop() + getPaddingBottom();
            int availableWidth = (contentWidth - getPaddingLeft()) - getPaddingRight();
            int height = maxHeight - verticalPadding;
            int childSpecHeight = View.MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE);
            if (this.mClose != null) {
                int availableWidth2 = measureChildView(this.mClose, availableWidth, childSpecHeight, 0);
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
                availableWidth = availableWidth2 - (lp.leftMargin + lp.rightMargin);
            }
            if (this.mMenuView != null && this.mMenuView.getParent() == this) {
                availableWidth = measureChildView(this.mMenuView, availableWidth, childSpecHeight, 0);
            }
            if (this.mTitleLayout != null && this.mCustomView == null) {
                if (this.mTitleOptional) {
                    this.mTitleLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), childSpecHeight);
                    int titleWidth = this.mTitleLayout.getMeasuredWidth();
                    boolean titleFits = titleWidth <= availableWidth;
                    if (titleFits) {
                        availableWidth -= titleWidth;
                    }
                    this.mTitleLayout.setVisibility(titleFits ? 0 : 8);
                } else {
                    availableWidth = measureChildView(this.mTitleLayout, availableWidth, childSpecHeight, 0);
                }
            }
            if (this.mCustomView != null) {
                ViewGroup.LayoutParams lp2 = this.mCustomView.getLayoutParams();
                int customWidthMode = lp2.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                int i = widthMode;
                this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(lp2.width >= 0 ? Math.min(lp2.width, availableWidth) : availableWidth, customWidthMode), View.MeasureSpec.makeMeasureSpec(lp2.height >= 0 ? Math.min(lp2.height, height) : height, lp2.height != -2 ? 1073741824 : Integer.MIN_VALUE));
            }
            if (this.mContentHeight <= 0) {
                int measuredHeight = 0;
                int count = getChildCount();
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 < count) {
                        int paddedViewHeight = getChildAt(i3).getMeasuredHeight() + verticalPadding;
                        if (paddedViewHeight > measuredHeight) {
                            measuredHeight = paddedViewHeight;
                        }
                        i2 = i3 + 1;
                    } else {
                        setMeasuredDimension(contentWidth, measuredHeight);
                        return;
                    }
                }
            } else {
                setMeasuredDimension(contentWidth, maxHeight);
            }
        } else {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int x = isLayoutRtl ? (r - l) - getPaddingRight() : getPaddingLeft();
        int y = getPaddingTop();
        int contentHeight = ((b - t) - getPaddingTop()) - getPaddingBottom();
        if (!(this.mClose == null || this.mClose.getVisibility() == 8)) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            int startMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
            int endMargin = isLayoutRtl ? lp.leftMargin : lp.rightMargin;
            int x2 = next(x, startMargin, isLayoutRtl);
            int i = startMargin;
            x = next(positionChild(this.mClose, x2, y, contentHeight, isLayoutRtl) + x2, endMargin, isLayoutRtl);
        }
        int x3 = x;
        if (!(this.mTitleLayout == null || this.mCustomView != null || this.mTitleLayout.getVisibility() == 8)) {
            x3 += positionChild(this.mTitleLayout, x3, y, contentHeight, isLayoutRtl);
        }
        if (this.mCustomView != null) {
            int x4 = x3 + positionChild(this.mCustomView, x3, y, contentHeight, isLayoutRtl);
        }
        int x5 = isLayoutRtl ? getPaddingLeft() : (r - l) - getPaddingRight();
        if (this.mMenuView != null) {
            int x6 = x5 + positionChild(this.mMenuView, x5, y, contentHeight, !isLayoutRtl);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 32) {
            event.setSource(this);
            event.setClassName(getClass().getName());
            event.setPackageName(getContext().getPackageName());
            event.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(event);
    }

    public void setTitleOptional(boolean titleOptional) {
        if (titleOptional != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = titleOptional;
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }
}
