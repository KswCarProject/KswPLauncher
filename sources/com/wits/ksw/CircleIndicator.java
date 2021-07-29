package com.wits.ksw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import com.ibm.icu.text.BreakIterator;

public class CircleIndicator extends View {
    private int indicatorHeight = 100;
    private float indicatorSpace = (10.0f * 3.0f);
    private int indicatorWidth = 100;
    private int mBgColor = SupportMenu.CATEGORY_MASK;
    private Paint mBgPaint;
    private Drawable mCircle_draw;
    private int mForeColor = -16776961;
    private Paint mFrantPain;
    private int mNumber = 5;
    private float mOffset;
    private float mRadius = 10.0f;
    private float startOffset = 60.0f;

    public CircleIndicator(Context context) {
        super(context);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
        this.indicatorSpace = typedArray.getDimension(3, this.indicatorSpace);
        this.mRadius = typedArray.getDimension(2, this.mRadius);
        this.mBgColor = typedArray.getColor(0, this.mBgColor);
        this.mForeColor = typedArray.getColor(1, this.mForeColor);
        initPaint();
        typedArray.recycle();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mFrantPain = paint;
        paint.setAntiAlias(true);
        this.mFrantPain.setStyle(Paint.Style.FILL);
        this.mFrantPain.setColor(this.mForeColor);
        Paint paint2 = new Paint();
        this.mBgPaint = paint2;
        paint2.setAntiAlias(true);
        this.mBgPaint.setStyle(Paint.Style.FILL);
        this.mBgPaint.setColor(this.mBgColor);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.mNumber; i++) {
            canvas.drawCircle(this.startOffset + (this.indicatorSpace * ((float) i)), (float) (this.indicatorHeight / 2), this.mRadius, this.mBgPaint);
        }
        canvas.drawCircle(this.startOffset + this.mOffset, (float) (this.indicatorHeight / 2), this.mRadius, this.mFrantPain);
    }

    public void setOffset(int position) {
        int i = this.mNumber;
        if (i != 0) {
            this.mOffset = ((float) (position % i)) * this.indicatorSpace;
            postInvalidate();
        }
    }

    public void setNumber(int number) {
        this.mNumber = number;
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.indicatorWidth = getMySize(BreakIterator.WORD_IDEO_LIMIT, widthMeasureSpec);
        int mySize = getMySize(100, heightMeasureSpec);
        this.indicatorHeight = mySize;
        setMeasuredDimension(this.indicatorWidth, mySize);
        this.startOffset = ((float) (this.indicatorWidth / 2)) - ((this.indicatorSpace * ((float) (this.mNumber - 1))) / 2.0f);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case Integer.MIN_VALUE:
                return size;
            case 0:
                return defaultSize;
            case 1073741824:
                return size;
            default:
                return mySize;
        }
    }
}
