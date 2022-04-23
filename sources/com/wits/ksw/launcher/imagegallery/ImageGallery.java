package com.wits.ksw.launcher.imagegallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import com.wits.ksw.R;

public class ImageGallery extends FrameLayout implements View.OnTouchListener {
    private boolean isClickMode;
    private OnGalleryPageSelectListener mOnGalleryPageSelectListener;
    private BasePageTransformer mPageTransformer;
    private long mStartDownTime = -1;
    private PointF mStartPoint = new PointF();
    private int mTapTimeout;
    private int mTouchSlop;
    private ViewPager mViewPager;
    private int mViewPagerWidth;

    public interface OnGalleryPageSelectListener {
        void onGalleryPageSelected(int i);
    }

    public ImageGallery(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public ImageGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ImageGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dStyableImageGallery, defStyleAttr, 0);
        int itemWidth = a.getDimensionPixelOffset(1, -1);
        int itemHeight = a.getDimensionPixelOffset(0, -1);
        a.recycle();
        ViewPager viewPager = new ViewPager(context);
        this.mViewPager = viewPager;
        viewPager.setClipChildren(false);
        this.mViewPager.setOverScrollMode(2);
        this.mViewPager.setHorizontalScrollBarEnabled(false);
        this.mViewPager.setOffscreenPageLimit(5);
        setPageTransformer(new BottomScalePageTransformer());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(itemWidth, itemHeight);
        params.gravity = 17;
        addView(this.mViewPager, params);
        setClipChildren(false);
        setOnTouchListener(this);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mTapTimeout = ViewConfiguration.getTapTimeout();
        this.mViewPagerWidth = itemWidth;
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (this.mViewPager.getAdapter() == null || this.mViewPager.getAdapter().getCount() == 0) {
            return true;
        }
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case 0:
                this.isClickMode = true;
                this.mStartDownTime = SystemClock.uptimeMillis();
                this.mStartPoint.set(event.getX(), event.getY());
                break;
            case 1:
            case 3:
                if (this.isClickMode) {
                    performClickItem(this.mStartPoint.x);
                }
                this.isClickMode = false;
                this.mStartDownTime = -1;
                break;
            case 2:
                if (Math.abs(x - this.mStartPoint.x) > ((float) this.mTouchSlop) || Math.abs(y - this.mStartPoint.y) > ((float) this.mTouchSlop)) {
                    this.isClickMode = false;
                    break;
                }
        }
        return this.mViewPager.dispatchTouchEvent(event);
    }

    private void performClickItem(float x) {
        float centerDistance = x - (((float) getWidth()) / 2.0f);
        boolean isRightSide = centerDistance >= 0.0f;
        float abs = Math.abs(centerDistance);
        int i = this.mViewPagerWidth;
        int space = ((int) (abs + (((float) i) / 2.0f))) / i;
        int position = this.mViewPager.getCurrentItem() + (isRightSide ? space : -space);
        if (position >= 0 && position < this.mViewPager.getAdapter().getCount() && this.mViewPager.getCurrentItem() != position) {
            this.mViewPager.setCurrentItem(position, true);
            OnGalleryPageSelectListener onGalleryPageSelectListener = this.mOnGalleryPageSelectListener;
            if (onGalleryPageSelectListener != null) {
                onGalleryPageSelectListener.onGalleryPageSelected(position);
            }
        }
    }

    public void setPageTransformer(BasePageTransformer transformer) {
        this.mPageTransformer = transformer;
        this.mViewPager.setPageTransformer(true, transformer);
    }

    public void setPageOffscreenLimit(int limit) {
        this.mViewPager.setOffscreenPageLimit(limit);
    }

    public void setAdapter(PagerAdapter adapter) {
        this.mViewPager.setAdapter(adapter);
    }

    public void setSelection(int itemPosition, boolean smoothScroll) {
        this.mViewPager.setCurrentItem(itemPosition, smoothScroll);
    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        this.mViewPager.addOnPageChangeListener(listener);
    }

    public void setOnGalleryPageSelectListener(OnGalleryPageSelectListener listener) {
        this.mOnGalleryPageSelectListener = listener;
    }
}
