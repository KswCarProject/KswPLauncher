package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.HelperWidget;
import android.util.AttributeSet;
import android.util.SparseArray;

public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;
    private android.support.constraint.solver.widgets.Barrier mBarrier;
    private int mIndicatedType;
    private int mResolvedType;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setVisibility(8);
    }

    public int getType() {
        return this.mIndicatedType;
    }

    public void setType(int type) {
        this.mIndicatedType = type;
    }

    private void updateType(ConstraintWidget widget, int type, boolean isRtl) {
        this.mResolvedType = type;
        if (Build.VERSION.SDK_INT < 17) {
            int i = this.mIndicatedType;
            if (i == 5) {
                this.mResolvedType = 0;
            } else if (i == 6) {
                this.mResolvedType = 1;
            }
        } else if (isRtl) {
            int i2 = this.mIndicatedType;
            if (i2 == 5) {
                this.mResolvedType = 1;
            } else if (i2 == 6) {
                this.mResolvedType = 0;
            }
        } else {
            int i3 = this.mIndicatedType;
            if (i3 == 5) {
                this.mResolvedType = 0;
            } else if (i3 == 6) {
                this.mResolvedType = 1;
            }
        }
        if (widget instanceof android.support.constraint.solver.widgets.Barrier) {
            ((android.support.constraint.solver.widgets.Barrier) widget).setBarrierType(this.mResolvedType);
        }
    }

    public void resolveRtl(ConstraintWidget widget, boolean isRtl) {
        updateType(widget, this.mIndicatedType, isRtl);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        this.mBarrier = new android.support.constraint.solver.widgets.Barrier();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(a.getInt(attr, 0));
                } else if (attr == R.styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.mBarrier.setAllowsGoneWidget(a.getBoolean(attr, true));
                } else if (attr == R.styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.mBarrier.setMargin(a.getDimensionPixelSize(attr, 0));
                }
            }
        }
        this.mHelperWidget = this.mBarrier;
        validateParams();
    }

    public void setAllowsGoneWidget(boolean supportGone) {
        this.mBarrier.setAllowsGoneWidget(supportGone);
    }

    public boolean allowsGoneWidget() {
        return this.mBarrier.allowsGoneWidget();
    }

    public void setDpMargin(int margin) {
        android.support.constraint.solver.widgets.Barrier barrier = this.mBarrier;
        barrier.setMargin((int) ((((float) margin) * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public int getMargin() {
        return this.mBarrier.getMargin();
    }

    public void setMargin(int margin) {
        this.mBarrier.setMargin(margin);
    }

    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget child, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> mapIdToWidget) {
        super.loadParameters(constraint, child, layoutParams, mapIdToWidget);
        if (child instanceof android.support.constraint.solver.widgets.Barrier) {
            android.support.constraint.solver.widgets.Barrier barrier = (android.support.constraint.solver.widgets.Barrier) child;
            updateType(barrier, constraint.layout.mBarrierDirection, ((ConstraintWidgetContainer) child.getParent()).isRtl());
            barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
            barrier.setMargin(constraint.layout.mBarrierMargin);
        }
    }
}
