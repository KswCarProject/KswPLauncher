package com.wits.ksw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.widgets.analyzer.BasicMeasure;
import android.support.p001v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import com.ibm.icu.text.BreakIterator;

/* loaded from: classes17.dex */
public class CircleIndicator extends View {
    private int indicatorHeight;
    private float indicatorSpace;
    private int indicatorWidth;
    private int mBgColor;
    private Paint mBgPaint;
    private Drawable mCircle_draw;
    private int mForeColor;
    private Paint mFrantPain;
    private int mNumber;
    private float mOffset;
    private float mRadius;
    private float startOffset;

    public CircleIndicator(Context context) {
        super(context);
        this.mBgColor = SupportMenu.CATEGORY_MASK;
        this.mForeColor = -16776961;
        this.mNumber = 5;
        this.mRadius = 10.0f;
        this.indicatorSpace = 10.0f * 3.0f;
        this.startOffset = 60.0f;
        this.indicatorWidth = 100;
        this.indicatorHeight = 100;
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mBgColor = SupportMenu.CATEGORY_MASK;
        this.mForeColor = -16776961;
        this.mNumber = 5;
        this.mRadius = 10.0f;
        this.indicatorSpace = 10.0f * 3.0f;
        this.startOffset = 60.0f;
        this.indicatorWidth = 100;
        this.indicatorHeight = 100;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, C0899R.styleable.CircleIndicator);
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

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < this.mNumber; i++) {
            canvas.drawCircle(this.startOffset + (this.indicatorSpace * i), this.indicatorHeight / 2, this.mRadius, this.mBgPaint);
        }
        canvas.drawCircle(this.startOffset + this.mOffset, this.indicatorHeight / 2, this.mRadius, this.mFrantPain);
    }

    public void setOffset(int position) {
        int i = this.mNumber;
        if (i == 0) {
            return;
        }
        this.mOffset = (position % i) * this.indicatorSpace;
        postInvalidate();
    }

    public void setNumber(int number) {
        this.mNumber = number;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.indicatorWidth = getMySize(BreakIterator.WORD_IDEO_LIMIT, widthMeasureSpec);
        int mySize = getMySize(100, heightMeasureSpec);
        this.indicatorHeight = mySize;
        setMeasuredDimension(this.indicatorWidth, mySize);
        this.startOffset = (this.indicatorWidth / 2) - ((this.indicatorSpace * (this.mNumber - 1)) / 2.0f);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case Integer.MIN_VALUE:
                return size;
            case 0:
                return defaultSize;
            case BasicMeasure.EXACTLY /* 1073741824 */:
                return size;
            default:
                return defaultSize;
        }
    }
}
