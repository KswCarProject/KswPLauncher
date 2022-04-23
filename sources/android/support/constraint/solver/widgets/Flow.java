package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Flow extends VirtualLayout {
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE = 0;
    private ConstraintWidget[] mAlignedBiggestElementsInCols = null;
    private ConstraintWidget[] mAlignedBiggestElementsInRows = null;
    private int[] mAlignedDimensions = null;
    private ArrayList<WidgetsList> mChainList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ConstraintWidget[] mDisplayedWidgets;
    /* access modifiers changed from: private */
    public int mDisplayedWidgetsCount = 0;
    /* access modifiers changed from: private */
    public float mFirstHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mFirstVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mFirstVerticalStyle = -1;
    /* access modifiers changed from: private */
    public int mHorizontalAlign = 2;
    /* access modifiers changed from: private */
    public float mHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mHorizontalGap = 0;
    /* access modifiers changed from: private */
    public int mHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastHorizontalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastHorizontalStyle = -1;
    /* access modifiers changed from: private */
    public float mLastVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mLastVerticalStyle = -1;
    private int mMaxElementsWrap = -1;
    private int mOrientation = 0;
    /* access modifiers changed from: private */
    public int mVerticalAlign = 2;
    /* access modifiers changed from: private */
    public float mVerticalBias = 0.5f;
    /* access modifiers changed from: private */
    public int mVerticalGap = 0;
    /* access modifiers changed from: private */
    public int mVerticalStyle = -1;
    private int mWrapMode = 0;

    public void copy(ConstraintWidget src, HashMap<ConstraintWidget, ConstraintWidget> map) {
        super.copy(src, map);
        Flow srcFLow = (Flow) src;
        this.mHorizontalStyle = srcFLow.mHorizontalStyle;
        this.mVerticalStyle = srcFLow.mVerticalStyle;
        this.mFirstHorizontalStyle = srcFLow.mFirstHorizontalStyle;
        this.mFirstVerticalStyle = srcFLow.mFirstVerticalStyle;
        this.mLastHorizontalStyle = srcFLow.mLastHorizontalStyle;
        this.mLastVerticalStyle = srcFLow.mLastVerticalStyle;
        this.mHorizontalBias = srcFLow.mHorizontalBias;
        this.mVerticalBias = srcFLow.mVerticalBias;
        this.mFirstHorizontalBias = srcFLow.mFirstHorizontalBias;
        this.mFirstVerticalBias = srcFLow.mFirstVerticalBias;
        this.mLastHorizontalBias = srcFLow.mLastHorizontalBias;
        this.mLastVerticalBias = srcFLow.mLastVerticalBias;
        this.mHorizontalGap = srcFLow.mHorizontalGap;
        this.mVerticalGap = srcFLow.mVerticalGap;
        this.mHorizontalAlign = srcFLow.mHorizontalAlign;
        this.mVerticalAlign = srcFLow.mVerticalAlign;
        this.mWrapMode = srcFLow.mWrapMode;
        this.mMaxElementsWrap = srcFLow.mMaxElementsWrap;
        this.mOrientation = srcFLow.mOrientation;
    }

    public void setOrientation(int value) {
        this.mOrientation = value;
    }

    public void setFirstHorizontalStyle(int value) {
        this.mFirstHorizontalStyle = value;
    }

    public void setFirstVerticalStyle(int value) {
        this.mFirstVerticalStyle = value;
    }

    public void setLastHorizontalStyle(int value) {
        this.mLastHorizontalStyle = value;
    }

    public void setLastVerticalStyle(int value) {
        this.mLastVerticalStyle = value;
    }

    public void setHorizontalStyle(int value) {
        this.mHorizontalStyle = value;
    }

    public void setVerticalStyle(int value) {
        this.mVerticalStyle = value;
    }

    public void setHorizontalBias(float value) {
        this.mHorizontalBias = value;
    }

    public void setVerticalBias(float value) {
        this.mVerticalBias = value;
    }

    public void setFirstHorizontalBias(float value) {
        this.mFirstHorizontalBias = value;
    }

    public void setFirstVerticalBias(float value) {
        this.mFirstVerticalBias = value;
    }

    public void setLastHorizontalBias(float value) {
        this.mLastHorizontalBias = value;
    }

    public void setLastVerticalBias(float value) {
        this.mLastVerticalBias = value;
    }

    public void setHorizontalAlign(int value) {
        this.mHorizontalAlign = value;
    }

    public void setVerticalAlign(int value) {
        this.mVerticalAlign = value;
    }

    public void setWrapMode(int value) {
        this.mWrapMode = value;
    }

    public void setHorizontalGap(int value) {
        this.mHorizontalGap = value;
    }

    public void setVerticalGap(int value) {
        this.mVerticalGap = value;
    }

    public void setMaxElementsWrap(int value) {
        this.mMaxElementsWrap = value;
    }

    /* access modifiers changed from: private */
    public final int getWidgetWidth(ConstraintWidget widget, int max) {
        if (widget == null) {
            return 0;
        }
        if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (widget.mMatchConstraintDefaultWidth == 0) {
                return 0;
            }
            if (widget.mMatchConstraintDefaultWidth == 2) {
                int value = (int) (widget.mMatchConstraintPercentWidth * ((float) max));
                if (value != widget.getWidth()) {
                    measure(widget, ConstraintWidget.DimensionBehaviour.FIXED, value, widget.getVerticalDimensionBehaviour(), widget.getHeight());
                }
                return value;
            } else if (widget.mMatchConstraintDefaultWidth == 1) {
                return widget.getWidth();
            } else {
                if (widget.mMatchConstraintDefaultWidth == 3) {
                    return (int) ((((float) widget.getHeight()) * widget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return widget.getWidth();
    }

    /* access modifiers changed from: private */
    public final int getWidgetHeight(ConstraintWidget widget, int max) {
        if (widget == null) {
            return 0;
        }
        if (widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            if (widget.mMatchConstraintDefaultHeight == 0) {
                return 0;
            }
            if (widget.mMatchConstraintDefaultHeight == 2) {
                int value = (int) (widget.mMatchConstraintPercentHeight * ((float) max));
                if (value != widget.getHeight()) {
                    measure(widget, widget.getHorizontalDimensionBehaviour(), widget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, value);
                }
                return value;
            } else if (widget.mMatchConstraintDefaultHeight == 1) {
                return widget.getHeight();
            } else {
                if (widget.mMatchConstraintDefaultHeight == 3) {
                    return (int) ((((float) widget.getWidth()) * widget.mDimensionRatio) + 0.5f);
                }
            }
        }
        return widget.getHeight();
    }

    /* JADX WARNING: type inference failed for: r20v0 */
    /* JADX WARNING: type inference failed for: r20v1 */
    /* JADX WARNING: type inference failed for: r20v2 */
    /* JADX WARNING: type inference failed for: r20v3 */
    /* JADX WARNING: type inference failed for: r20v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measure(int r24, int r25, int r26, int r27) {
        /*
            r23 = this;
            r6 = r23
            r7 = r24
            r8 = r25
            r9 = r26
            r10 = r27
            int r0 = r6.mWidgetsCount
            r11 = 0
            if (r0 <= 0) goto L_0x001c
            boolean r0 = r23.measureChildren()
            if (r0 != 0) goto L_0x001c
            r6.setMeasure(r11, r11)
            r6.needsCallbackFromSolver(r11)
            return
        L_0x001c:
            r12 = 0
            r13 = 0
            int r14 = r23.getPaddingLeft()
            int r15 = r23.getPaddingRight()
            int r16 = r23.getPaddingTop()
            int r17 = r23.getPaddingBottom()
            r0 = 2
            int[] r5 = new int[r0]
            int r0 = r8 - r14
            int r0 = r0 - r15
            int r1 = r6.mOrientation
            r4 = 1
            if (r1 != r4) goto L_0x0040
            int r2 = r10 - r16
            int r0 = r2 - r17
            r18 = r0
            goto L_0x0042
        L_0x0040:
            r18 = r0
        L_0x0042:
            r0 = -1
            if (r1 != 0) goto L_0x0052
            int r1 = r6.mHorizontalStyle
            if (r1 != r0) goto L_0x004b
            r6.mHorizontalStyle = r11
        L_0x004b:
            int r1 = r6.mVerticalStyle
            if (r1 != r0) goto L_0x005e
            r6.mVerticalStyle = r11
            goto L_0x005e
        L_0x0052:
            int r1 = r6.mHorizontalStyle
            if (r1 != r0) goto L_0x0058
            r6.mHorizontalStyle = r11
        L_0x0058:
            int r1 = r6.mVerticalStyle
            if (r1 != r0) goto L_0x005e
            r6.mVerticalStyle = r11
        L_0x005e:
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r6.mWidgets
            r1 = 0
            r2 = 0
            r19 = r1
        L_0x0064:
            int r1 = r6.mWidgetsCount
            r3 = 8
            if (r2 >= r1) goto L_0x007a
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r6.mWidgets
            r1 = r1[r2]
            int r4 = r1.getVisibility()
            if (r4 != r3) goto L_0x0076
            int r19 = r19 + 1
        L_0x0076:
            int r2 = r2 + 1
            r4 = 1
            goto L_0x0064
        L_0x007a:
            int r1 = r6.mWidgetsCount
            if (r19 <= 0) goto L_0x00a6
            int r2 = r6.mWidgetsCount
            int r2 = r2 - r19
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = new android.support.constraint.solver.widgets.ConstraintWidget[r2]
            r2 = 0
            r4 = 0
        L_0x0086:
            int r11 = r6.mWidgetsCount
            if (r4 >= r11) goto L_0x00a0
            android.support.constraint.solver.widgets.ConstraintWidget[] r11 = r6.mWidgets
            r11 = r11[r4]
            r21 = r1
            int r1 = r11.getVisibility()
            if (r1 == r3) goto L_0x009a
            r0[r2] = r11
            int r2 = r2 + 1
        L_0x009a:
            int r4 = r4 + 1
            r1 = r21
            r11 = 0
            goto L_0x0086
        L_0x00a0:
            r21 = r1
            r1 = r2
            r11 = r0
            r4 = r1
            goto L_0x00ab
        L_0x00a6:
            r21 = r1
            r11 = r0
            r4 = r21
        L_0x00ab:
            r6.mDisplayedWidgets = r11
            r6.mDisplayedWidgetsCount = r4
            int r0 = r6.mWrapMode
            switch(r0) {
                case 0: goto L_0x00e0;
                case 1: goto L_0x00cd;
                case 2: goto L_0x00bb;
                default: goto L_0x00b4;
            }
        L_0x00b4:
            r21 = r4
            r22 = r5
            r20 = 1
            goto L_0x00f2
        L_0x00bb:
            int r3 = r6.mOrientation
            r0 = r23
            r1 = r11
            r2 = r4
            r21 = r4
            r20 = 1
            r4 = r18
            r22 = r5
            r0.measureAligned(r1, r2, r3, r4, r5)
            goto L_0x00f2
        L_0x00cd:
            r21 = r4
            r22 = r5
            r20 = 1
            int r3 = r6.mOrientation
            r0 = r23
            r1 = r11
            r2 = r21
            r4 = r18
            r0.measureChainWrap(r1, r2, r3, r4, r5)
            goto L_0x00f2
        L_0x00e0:
            r21 = r4
            r22 = r5
            r20 = 1
            int r3 = r6.mOrientation
            r0 = r23
            r1 = r11
            r2 = r21
            r4 = r18
            r0.measureNoWrap(r1, r2, r3, r4, r5)
        L_0x00f2:
            r0 = 0
            r1 = r22[r0]
            int r1 = r1 + r14
            int r1 = r1 + r15
            r2 = r22[r20]
            int r2 = r2 + r16
            int r2 = r2 + r17
            r3 = 0
            r4 = 0
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r12 = 1073741824(0x40000000, float:2.0)
            if (r7 != r12) goto L_0x0108
            r3 = r25
            goto L_0x0112
        L_0x0108:
            if (r7 != r5) goto L_0x010f
            int r3 = java.lang.Math.min(r1, r8)
            goto L_0x0112
        L_0x010f:
            if (r7 != 0) goto L_0x0112
            r3 = r1
        L_0x0112:
            if (r9 != r12) goto L_0x0117
            r4 = r27
            goto L_0x0121
        L_0x0117:
            if (r9 != r5) goto L_0x011e
            int r4 = java.lang.Math.min(r2, r10)
            goto L_0x0121
        L_0x011e:
            if (r9 != 0) goto L_0x0121
            r4 = r2
        L_0x0121:
            r6.setMeasure(r3, r4)
            r6.setWidth(r3)
            r6.setHeight(r4)
            int r5 = r6.mWidgetsCount
            if (r5 <= 0) goto L_0x0130
            r0 = r20
        L_0x0130:
            r6.needsCallbackFromSolver(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Flow.measure(int, int, int, int):void");
    }

    private class WidgetsList {
        /* access modifiers changed from: private */
        public ConstraintWidget biggest = null;
        int biggestDimension = 0;
        private ConstraintAnchor mBottom;
        private int mCount = 0;
        private int mHeight = 0;
        private ConstraintAnchor mLeft;
        private int mMax = 0;
        private int mNbMatchConstraintsWidgets = 0;
        private int mOrientation = 0;
        private int mPaddingBottom = 0;
        private int mPaddingLeft = 0;
        private int mPaddingRight = 0;
        private int mPaddingTop = 0;
        private ConstraintAnchor mRight;
        private int mStartIndex = 0;
        private ConstraintAnchor mTop;
        private int mWidth = 0;

        public WidgetsList(int orientation, ConstraintAnchor left, ConstraintAnchor top, ConstraintAnchor right, ConstraintAnchor bottom, int max) {
            this.mOrientation = orientation;
            this.mLeft = left;
            this.mTop = top;
            this.mRight = right;
            this.mBottom = bottom;
            this.mPaddingLeft = Flow.this.getPaddingLeft();
            this.mPaddingTop = Flow.this.getPaddingTop();
            this.mPaddingRight = Flow.this.getPaddingRight();
            this.mPaddingBottom = Flow.this.getPaddingBottom();
            this.mMax = max;
        }

        public void setup(int orientation, ConstraintAnchor left, ConstraintAnchor top, ConstraintAnchor right, ConstraintAnchor bottom, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom, int max) {
            this.mOrientation = orientation;
            this.mLeft = left;
            this.mTop = top;
            this.mRight = right;
            this.mBottom = bottom;
            this.mPaddingLeft = paddingLeft;
            this.mPaddingTop = paddingTop;
            this.mPaddingRight = paddingRight;
            this.mPaddingBottom = paddingBottom;
            this.mMax = max;
        }

        public void clear() {
            this.biggestDimension = 0;
            this.biggest = null;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mStartIndex = 0;
            this.mCount = 0;
            this.mNbMatchConstraintsWidgets = 0;
        }

        public void setStartIndex(int value) {
            this.mStartIndex = value;
        }

        public int getWidth() {
            if (this.mOrientation == 0) {
                return this.mWidth - Flow.this.mHorizontalGap;
            }
            return this.mWidth;
        }

        public int getHeight() {
            if (this.mOrientation == 1) {
                return this.mHeight - Flow.this.mVerticalGap;
            }
            return this.mHeight;
        }

        public void add(ConstraintWidget widget) {
            if (this.mOrientation == 0) {
                int width = Flow.this.getWidgetWidth(widget, this.mMax);
                if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    width = 0;
                }
                int gap = Flow.this.mHorizontalGap;
                if (widget.getVisibility() == 8) {
                    gap = 0;
                }
                this.mWidth += width + gap;
                int height = Flow.this.getWidgetHeight(widget, this.mMax);
                if (this.biggest == null || this.biggestDimension < height) {
                    this.biggest = widget;
                    this.biggestDimension = height;
                    this.mHeight = height;
                }
            } else {
                int width2 = Flow.this.getWidgetWidth(widget, this.mMax);
                int height2 = Flow.this.getWidgetHeight(widget, this.mMax);
                if (widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mNbMatchConstraintsWidgets++;
                    height2 = 0;
                }
                int gap2 = Flow.this.mVerticalGap;
                if (widget.getVisibility() == 8) {
                    gap2 = 0;
                }
                this.mHeight += height2 + gap2;
                if (this.biggest == null || this.biggestDimension < width2) {
                    this.biggest = widget;
                    this.biggestDimension = width2;
                    this.mWidth = width2;
                }
            }
            this.mCount++;
        }

        public void createConstraints(boolean isInRtl, int chainIndex, boolean isLastChain) {
            int count = this.mCount;
            int i = 0;
            while (i < count && this.mStartIndex + i < Flow.this.mDisplayedWidgetsCount) {
                ConstraintWidget widget = Flow.this.mDisplayedWidgets[this.mStartIndex + i];
                if (widget != null) {
                    widget.resetAnchors();
                }
                i++;
            }
            if (count != 0 && this.biggest != null) {
                boolean singleChain = isLastChain && chainIndex == 0;
                int firstVisible = -1;
                int lastVisible = -1;
                for (int i2 = 0; i2 < count; i2++) {
                    int index = i2;
                    if (isInRtl) {
                        index = (count - 1) - i2;
                    }
                    if (this.mStartIndex + index >= Flow.this.mDisplayedWidgetsCount) {
                        break;
                    }
                    if (Flow.this.mDisplayedWidgets[this.mStartIndex + index].getVisibility() == 0) {
                        if (firstVisible == -1) {
                            firstVisible = i2;
                        }
                        lastVisible = i2;
                    }
                }
                ConstraintWidget previous = null;
                if (this.mOrientation == 0) {
                    ConstraintWidget verticalWidget = this.biggest;
                    verticalWidget.setVerticalChainStyle(Flow.this.mVerticalStyle);
                    int padding = this.mPaddingTop;
                    if (chainIndex > 0) {
                        padding += Flow.this.mVerticalGap;
                    }
                    verticalWidget.mTop.connect(this.mTop, padding);
                    if (isLastChain) {
                        verticalWidget.mBottom.connect(this.mBottom, this.mPaddingBottom);
                    }
                    if (chainIndex > 0) {
                        this.mTop.mOwner.mBottom.connect(verticalWidget.mTop, 0);
                    }
                    ConstraintWidget baselineVerticalWidget = verticalWidget;
                    if (Flow.this.mVerticalAlign == 3 && !verticalWidget.hasBaseline()) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= count) {
                                break;
                            }
                            int index2 = i3;
                            if (isInRtl) {
                                index2 = (count - 1) - i3;
                            }
                            if (this.mStartIndex + index2 >= Flow.this.mDisplayedWidgetsCount) {
                                break;
                            }
                            ConstraintWidget widget2 = Flow.this.mDisplayedWidgets[this.mStartIndex + index2];
                            if (widget2.hasBaseline()) {
                                baselineVerticalWidget = widget2;
                                break;
                            }
                            i3++;
                        }
                    }
                    int i4 = 0;
                    while (i4 < count) {
                        int index3 = i4;
                        if (isInRtl) {
                            index3 = (count - 1) - i4;
                        }
                        if (this.mStartIndex + index3 < Flow.this.mDisplayedWidgetsCount) {
                            ConstraintWidget widget3 = Flow.this.mDisplayedWidgets[this.mStartIndex + index3];
                            if (i4 == 0) {
                                widget3.connect(widget3.mLeft, this.mLeft, this.mPaddingLeft);
                            }
                            if (index3 == 0) {
                                int style = Flow.this.mHorizontalStyle;
                                float bias = Flow.this.mHorizontalBias;
                                if (this.mStartIndex == 0 && Flow.this.mFirstHorizontalStyle != -1) {
                                    style = Flow.this.mFirstHorizontalStyle;
                                    bias = Flow.this.mFirstHorizontalBias;
                                } else if (isLastChain && Flow.this.mLastHorizontalStyle != -1) {
                                    style = Flow.this.mLastHorizontalStyle;
                                    bias = Flow.this.mLastHorizontalBias;
                                }
                                widget3.setHorizontalChainStyle(style);
                                widget3.setHorizontalBiasPercent(bias);
                            }
                            if (i4 == count - 1) {
                                widget3.connect(widget3.mRight, this.mRight, this.mPaddingRight);
                            }
                            if (previous != null) {
                                widget3.mLeft.connect(previous.mRight, Flow.this.mHorizontalGap);
                                if (i4 == firstVisible) {
                                    widget3.mLeft.setGoneMargin(this.mPaddingLeft);
                                }
                                previous.mRight.connect(widget3.mLeft, 0);
                                if (i4 == lastVisible + 1) {
                                    previous.mRight.setGoneMargin(this.mPaddingRight);
                                }
                            }
                            if (widget3 != verticalWidget) {
                                if (Flow.this.mVerticalAlign != 3 || !baselineVerticalWidget.hasBaseline() || widget3 == baselineVerticalWidget || !widget3.hasBaseline()) {
                                    switch (Flow.this.mVerticalAlign) {
                                        case 0:
                                            widget3.mTop.connect(verticalWidget.mTop, 0);
                                            break;
                                        case 1:
                                            widget3.mBottom.connect(verticalWidget.mBottom, 0);
                                            break;
                                        default:
                                            if (!singleChain) {
                                                widget3.mTop.connect(verticalWidget.mTop, 0);
                                                widget3.mBottom.connect(verticalWidget.mBottom, 0);
                                                break;
                                            } else {
                                                widget3.mTop.connect(this.mTop, this.mPaddingTop);
                                                widget3.mBottom.connect(this.mBottom, this.mPaddingBottom);
                                                break;
                                            }
                                    }
                                } else {
                                    widget3.mBaseline.connect(baselineVerticalWidget.mBaseline, 0);
                                }
                            }
                            previous = widget3;
                            i4++;
                        } else {
                            return;
                        }
                    }
                    return;
                }
                ConstraintWidget horizontalWidget = this.biggest;
                horizontalWidget.setHorizontalChainStyle(Flow.this.mHorizontalStyle);
                int padding2 = this.mPaddingLeft;
                if (chainIndex > 0) {
                    padding2 += Flow.this.mHorizontalGap;
                }
                if (isInRtl) {
                    horizontalWidget.mRight.connect(this.mRight, padding2);
                    if (isLastChain) {
                        horizontalWidget.mLeft.connect(this.mLeft, this.mPaddingRight);
                    }
                    if (chainIndex > 0) {
                        this.mRight.mOwner.mLeft.connect(horizontalWidget.mRight, 0);
                    }
                } else {
                    horizontalWidget.mLeft.connect(this.mLeft, padding2);
                    if (isLastChain) {
                        horizontalWidget.mRight.connect(this.mRight, this.mPaddingRight);
                    }
                    if (chainIndex > 0) {
                        this.mLeft.mOwner.mRight.connect(horizontalWidget.mLeft, 0);
                    }
                }
                int i5 = 0;
                while (i5 < count && this.mStartIndex + i5 < Flow.this.mDisplayedWidgetsCount) {
                    ConstraintWidget widget4 = Flow.this.mDisplayedWidgets[this.mStartIndex + i5];
                    if (i5 == 0) {
                        widget4.connect(widget4.mTop, this.mTop, this.mPaddingTop);
                        int style2 = Flow.this.mVerticalStyle;
                        float bias2 = Flow.this.mVerticalBias;
                        if (this.mStartIndex == 0 && Flow.this.mFirstVerticalStyle != -1) {
                            style2 = Flow.this.mFirstVerticalStyle;
                            bias2 = Flow.this.mFirstVerticalBias;
                        } else if (isLastChain && Flow.this.mLastVerticalStyle != -1) {
                            style2 = Flow.this.mLastVerticalStyle;
                            bias2 = Flow.this.mLastVerticalBias;
                        }
                        widget4.setVerticalChainStyle(style2);
                        widget4.setVerticalBiasPercent(bias2);
                    }
                    if (i5 == count - 1) {
                        widget4.connect(widget4.mBottom, this.mBottom, this.mPaddingBottom);
                    }
                    if (previous != null) {
                        widget4.mTop.connect(previous.mBottom, Flow.this.mVerticalGap);
                        if (i5 == firstVisible) {
                            widget4.mTop.setGoneMargin(this.mPaddingTop);
                        }
                        previous.mBottom.connect(widget4.mTop, 0);
                        if (i5 == lastVisible + 1) {
                            previous.mBottom.setGoneMargin(this.mPaddingBottom);
                        }
                    }
                    if (widget4 != horizontalWidget) {
                        if (!isInRtl) {
                            switch (Flow.this.mHorizontalAlign) {
                                case 0:
                                    widget4.mLeft.connect(horizontalWidget.mLeft, 0);
                                    break;
                                case 1:
                                    widget4.mRight.connect(horizontalWidget.mRight, 0);
                                    break;
                                case 2:
                                    if (!singleChain) {
                                        widget4.mLeft.connect(horizontalWidget.mLeft, 0);
                                        widget4.mRight.connect(horizontalWidget.mRight, 0);
                                        break;
                                    } else {
                                        widget4.mLeft.connect(this.mLeft, this.mPaddingLeft);
                                        widget4.mRight.connect(this.mRight, this.mPaddingRight);
                                        break;
                                    }
                            }
                        } else {
                            switch (Flow.this.mHorizontalAlign) {
                                case 0:
                                    widget4.mRight.connect(horizontalWidget.mRight, 0);
                                    break;
                                case 1:
                                    widget4.mLeft.connect(horizontalWidget.mLeft, 0);
                                    break;
                                case 2:
                                    widget4.mLeft.connect(horizontalWidget.mLeft, 0);
                                    widget4.mRight.connect(horizontalWidget.mRight, 0);
                                    break;
                            }
                        }
                    }
                    previous = widget4;
                    i5++;
                }
            }
        }

        public void measureMatchConstraints(int availableSpace) {
            int i = this.mNbMatchConstraintsWidgets;
            if (i != 0) {
                int count = this.mCount;
                int widgetSize = availableSpace / i;
                int i2 = 0;
                while (i2 < count && this.mStartIndex + i2 < Flow.this.mDisplayedWidgetsCount) {
                    ConstraintWidget widget = Flow.this.mDisplayedWidgets[this.mStartIndex + i2];
                    if (this.mOrientation == 0) {
                        if (widget != null && widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mMatchConstraintDefaultWidth == 0) {
                            Flow.this.measure(widget, ConstraintWidget.DimensionBehaviour.FIXED, widgetSize, widget.getVerticalDimensionBehaviour(), widget.getHeight());
                        }
                    } else if (widget != null && widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mMatchConstraintDefaultHeight == 0) {
                        Flow.this.measure(widget, widget.getHorizontalDimensionBehaviour(), widget.getWidth(), ConstraintWidget.DimensionBehaviour.FIXED, widgetSize);
                    }
                    i2++;
                }
                recomputeDimensions();
            }
        }

        private void recomputeDimensions() {
            this.mWidth = 0;
            this.mHeight = 0;
            this.biggest = null;
            this.biggestDimension = 0;
            int count = this.mCount;
            int i = 0;
            while (i < count && this.mStartIndex + i < Flow.this.mDisplayedWidgetsCount) {
                ConstraintWidget widget = Flow.this.mDisplayedWidgets[this.mStartIndex + i];
                if (this.mOrientation == 0) {
                    int width = widget.getWidth();
                    int gap = Flow.this.mHorizontalGap;
                    if (widget.getVisibility() == 8) {
                        gap = 0;
                    }
                    this.mWidth += width + gap;
                    int height = Flow.this.getWidgetHeight(widget, this.mMax);
                    if (this.biggest == null || this.biggestDimension < height) {
                        this.biggest = widget;
                        this.biggestDimension = height;
                        this.mHeight = height;
                    }
                } else {
                    int width2 = Flow.this.getWidgetWidth(widget, this.mMax);
                    int height2 = Flow.this.getWidgetHeight(widget, this.mMax);
                    int gap2 = Flow.this.mVerticalGap;
                    if (widget.getVisibility() == 8) {
                        gap2 = 0;
                    }
                    this.mHeight += height2 + gap2;
                    if (this.biggest == null || this.biggestDimension < width2) {
                        this.biggest = widget;
                        this.biggestDimension = width2;
                        this.mWidth = width2;
                    }
                }
                i++;
            }
        }
    }

    private void measureChainWrap(ConstraintWidget[] widgets, int count, int orientation, int max, int[] measured) {
        WidgetsList list;
        int nbMatchConstraintsWidgets;
        int nbMatchConstraintsWidgets2;
        boolean doWrap;
        int i;
        int nbMatchConstraintsWidgets3;
        boolean doWrap2;
        int i2;
        int i3 = count;
        int i4 = max;
        if (i3 != 0) {
            this.mChainList.clear();
            WidgetsList list2 = new WidgetsList(orientation, this.mLeft, this.mTop, this.mRight, this.mBottom, max);
            this.mChainList.add(list2);
            int nbMatchConstraintsWidgets4 = 0;
            if (orientation == 0) {
                int width = 0;
                WidgetsList list3 = list2;
                int i5 = 0;
                while (i5 < i3) {
                    ConstraintWidget widget = widgets[i5];
                    int w = getWidgetWidth(widget, i4);
                    if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        nbMatchConstraintsWidgets3 = nbMatchConstraintsWidgets4 + 1;
                    } else {
                        nbMatchConstraintsWidgets3 = nbMatchConstraintsWidgets4;
                    }
                    boolean doWrap3 = (width == i4 || (this.mHorizontalGap + width) + w > i4) && list3.biggest != null;
                    if (doWrap3 || i5 <= 0 || (i2 = this.mMaxElementsWrap) <= 0 || i5 % i2 != 0) {
                        doWrap2 = doWrap3;
                    } else {
                        doWrap2 = true;
                    }
                    if (doWrap2) {
                        WidgetsList list4 = new WidgetsList(orientation, this.mLeft, this.mTop, this.mRight, this.mBottom, max);
                        list4.setStartIndex(i5);
                        this.mChainList.add(list4);
                        list3 = list4;
                        width = w;
                    } else if (i5 > 0) {
                        width += this.mHorizontalGap + w;
                    } else {
                        width = w;
                    }
                    list3.add(widget);
                    i5++;
                    nbMatchConstraintsWidgets4 = nbMatchConstraintsWidgets3;
                }
                list = list3;
            } else {
                int height = 0;
                WidgetsList list5 = list2;
                int i6 = 0;
                while (i6 < i3) {
                    ConstraintWidget widget2 = widgets[i6];
                    int h = getWidgetHeight(widget2, i4);
                    if (widget2.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        nbMatchConstraintsWidgets2 = nbMatchConstraintsWidgets4 + 1;
                    } else {
                        nbMatchConstraintsWidgets2 = nbMatchConstraintsWidgets4;
                    }
                    boolean doWrap4 = (height == i4 || (this.mVerticalGap + height) + h > i4) && list5.biggest != null;
                    if (doWrap4 || i6 <= 0 || (i = this.mMaxElementsWrap) <= 0 || i6 % i != 0) {
                        doWrap = doWrap4;
                    } else {
                        doWrap = true;
                    }
                    if (doWrap) {
                        WidgetsList list6 = new WidgetsList(orientation, this.mLeft, this.mTop, this.mRight, this.mBottom, max);
                        list6.setStartIndex(i6);
                        this.mChainList.add(list6);
                        list5 = list6;
                        height = h;
                    } else if (i6 > 0) {
                        height += this.mVerticalGap + h;
                    } else {
                        height = h;
                    }
                    list5.add(widget2);
                    i6++;
                    nbMatchConstraintsWidgets4 = nbMatchConstraintsWidgets2;
                }
                list = list5;
            }
            int listCount = this.mChainList.size();
            ConstraintAnchor left = this.mLeft;
            ConstraintAnchor top = this.mTop;
            ConstraintAnchor right = this.mRight;
            ConstraintAnchor bottom = this.mBottom;
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            WidgetsList widgetsList = list;
            ConstraintAnchor left2 = left;
            boolean needInternalMeasure = getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (nbMatchConstraintsWidgets4 <= 0 || !needInternalMeasure) {
                int i7 = nbMatchConstraintsWidgets4;
            } else {
                int i8 = 0;
                while (i8 < listCount) {
                    boolean needInternalMeasure2 = needInternalMeasure;
                    WidgetsList current = this.mChainList.get(i8);
                    if (orientation == 0) {
                        nbMatchConstraintsWidgets = nbMatchConstraintsWidgets4;
                        current.measureMatchConstraints(i4 - current.getWidth());
                    } else {
                        nbMatchConstraintsWidgets = nbMatchConstraintsWidgets4;
                        current.measureMatchConstraints(i4 - current.getHeight());
                    }
                    i8++;
                    needInternalMeasure = needInternalMeasure2;
                    nbMatchConstraintsWidgets4 = nbMatchConstraintsWidgets;
                }
                int i9 = nbMatchConstraintsWidgets4;
            }
            int i10 = 0;
            int paddingTop2 = paddingTop;
            int paddingRight2 = paddingRight;
            int paddingBottom2 = paddingBottom;
            ConstraintAnchor left3 = left2;
            int maxWidth = 0;
            int maxHeight = 0;
            while (i10 < listCount) {
                WidgetsList current2 = this.mChainList.get(i10);
                if (orientation == 0) {
                    if (i10 < listCount - 1) {
                        bottom = this.mChainList.get(i10 + 1).biggest.mTop;
                        paddingBottom2 = 0;
                    } else {
                        bottom = this.mBottom;
                        paddingBottom2 = getPaddingBottom();
                    }
                    ConstraintAnchor constraintAnchor = top;
                    current2.setup(orientation, left3, top, right, bottom, paddingLeft, paddingTop2, paddingRight2, paddingBottom2, max);
                    ConstraintAnchor top2 = current2.biggest.mBottom;
                    paddingTop2 = 0;
                    int maxWidth2 = Math.max(maxWidth, current2.getWidth());
                    maxHeight += current2.getHeight();
                    if (i10 > 0) {
                        maxHeight += this.mVerticalGap;
                    }
                    maxWidth = maxWidth2;
                    top = top2;
                } else {
                    ConstraintAnchor top3 = top;
                    int maxHeight2 = maxHeight;
                    int maxWidth3 = maxWidth;
                    if (i10 < listCount - 1) {
                        right = this.mChainList.get(i10 + 1).biggest.mLeft;
                        paddingRight2 = 0;
                    } else {
                        right = this.mRight;
                        paddingRight2 = getPaddingRight();
                    }
                    current2.setup(orientation, left3, top3, right, bottom, paddingLeft, paddingTop2, paddingRight2, paddingBottom2, max);
                    left3 = current2.biggest.mRight;
                    paddingLeft = 0;
                    maxWidth = maxWidth3 + current2.getWidth();
                    int maxHeight3 = Math.max(maxHeight2, current2.getHeight());
                    if (i10 > 0) {
                        maxWidth += this.mHorizontalGap;
                        maxHeight = maxHeight3;
                        top = top3;
                    } else {
                        maxHeight = maxHeight3;
                        top = top3;
                    }
                }
                i10++;
                int i11 = count;
                int i12 = max;
            }
            ConstraintAnchor constraintAnchor2 = top;
            measured[0] = maxWidth;
            measured[1] = maxHeight;
        }
    }

    private void measureNoWrap(ConstraintWidget[] widgets, int count, int orientation, int max, int[] measured) {
        WidgetsList list;
        int i = count;
        if (i != 0) {
            if (this.mChainList.size() == 0) {
                list = new WidgetsList(orientation, this.mLeft, this.mTop, this.mRight, this.mBottom, max);
                this.mChainList.add(list);
            } else {
                list = this.mChainList.get(0);
                list.clear();
                ConstraintAnchor constraintAnchor = this.mLeft;
                ConstraintAnchor constraintAnchor2 = this.mTop;
                ConstraintAnchor constraintAnchor3 = this.mRight;
                list.setup(orientation, constraintAnchor, constraintAnchor2, constraintAnchor3, this.mBottom, getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom(), max);
            }
            for (int i2 = 0; i2 < i; i2++) {
                list.add(widgets[i2]);
            }
            measured[0] = list.getWidth();
            measured[1] = list.getHeight();
        }
    }

    private void measureAligned(ConstraintWidget[] widgets, int count, int orientation, int max, int[] measured) {
        ConstraintWidget widget;
        ConstraintWidget[] constraintWidgetArr = widgets;
        int i = count;
        int i2 = orientation;
        int i3 = max;
        boolean done = false;
        int rows = 0;
        int cols = 0;
        if (i2 == 0) {
            cols = this.mMaxElementsWrap;
            if (cols <= 0) {
                int w = 0;
                cols = 0;
                for (int i4 = 0; i4 < i; i4++) {
                    if (i4 > 0) {
                        w += this.mHorizontalGap;
                    }
                    ConstraintWidget widget2 = constraintWidgetArr[i4];
                    if (widget2 != null) {
                        w += getWidgetWidth(widget2, i3);
                        if (w > i3) {
                            break;
                        }
                        cols++;
                    }
                }
            }
        } else {
            rows = this.mMaxElementsWrap;
            if (rows <= 0) {
                int h = 0;
                rows = 0;
                for (int i5 = 0; i5 < i; i5++) {
                    if (i5 > 0) {
                        h += this.mVerticalGap;
                    }
                    ConstraintWidget widget3 = constraintWidgetArr[i5];
                    if (widget3 != null) {
                        h += getWidgetHeight(widget3, i3);
                        if (h > i3) {
                            break;
                        }
                        rows++;
                    }
                }
            }
        }
        if (this.mAlignedDimensions == null) {
            this.mAlignedDimensions = new int[2];
        }
        int w2 = 1;
        if ((rows == 0 && i2 == 1) || (cols == 0 && i2 == 0)) {
            done = true;
        }
        while (!done) {
            if (i2 == 0) {
                rows = (int) Math.ceil((double) (((float) i) / ((float) cols)));
            } else {
                cols = (int) Math.ceil((double) (((float) i) / ((float) rows)));
            }
            ConstraintWidget[] constraintWidgetArr2 = this.mAlignedBiggestElementsInCols;
            if (constraintWidgetArr2 == null || constraintWidgetArr2.length < cols) {
                this.mAlignedBiggestElementsInCols = new ConstraintWidget[cols];
            } else {
                Arrays.fill(constraintWidgetArr2, (Object) null);
            }
            ConstraintWidget[] constraintWidgetArr3 = this.mAlignedBiggestElementsInRows;
            if (constraintWidgetArr3 == null || constraintWidgetArr3.length < rows) {
                this.mAlignedBiggestElementsInRows = new ConstraintWidget[rows];
            } else {
                Arrays.fill(constraintWidgetArr3, (Object) null);
            }
            int i6 = 0;
            while (i6 < cols) {
                int j = 0;
                while (j < rows) {
                    int index = (j * cols) + i6;
                    if (i2 == w2) {
                        index = (i6 * rows) + j;
                    }
                    if (index < constraintWidgetArr.length && (widget = constraintWidgetArr[index]) != null) {
                        int w3 = getWidgetWidth(widget, i3);
                        ConstraintWidget[] constraintWidgetArr4 = this.mAlignedBiggestElementsInCols;
                        if (constraintWidgetArr4[i6] == null || constraintWidgetArr4[i6].getWidth() < w3) {
                            this.mAlignedBiggestElementsInCols[i6] = widget;
                        }
                        int h2 = getWidgetHeight(widget, i3);
                        ConstraintWidget[] constraintWidgetArr5 = this.mAlignedBiggestElementsInRows;
                        if (constraintWidgetArr5[j] == null || constraintWidgetArr5[j].getHeight() < h2) {
                            this.mAlignedBiggestElementsInRows[j] = widget;
                        }
                    }
                    j++;
                    w2 = 1;
                }
                i6++;
                w2 = 1;
            }
            int w4 = 0;
            for (int i7 = 0; i7 < cols; i7++) {
                ConstraintWidget widget4 = this.mAlignedBiggestElementsInCols[i7];
                if (widget4 != null) {
                    if (i7 > 0) {
                        w4 += this.mHorizontalGap;
                    }
                    w4 += getWidgetWidth(widget4, i3);
                }
            }
            int h3 = 0;
            for (int j2 = 0; j2 < rows; j2++) {
                ConstraintWidget widget5 = this.mAlignedBiggestElementsInRows[j2];
                if (widget5 != null) {
                    if (j2 > 0) {
                        h3 += this.mVerticalGap;
                    }
                    h3 += getWidgetHeight(widget5, i3);
                }
            }
            measured[0] = w4;
            measured[1] = h3;
            if (i2 == 0) {
                if (w4 <= i3) {
                    done = true;
                } else if (cols > 1) {
                    cols--;
                } else {
                    done = true;
                }
            } else if (h3 <= i3) {
                done = true;
            } else if (rows > 1) {
                rows--;
            } else {
                done = true;
            }
            w2 = 1;
        }
        int[] iArr = this.mAlignedDimensions;
        iArr[0] = cols;
        iArr[1] = rows;
    }

    private void createAlignedConstraints(boolean isInRtl) {
        ConstraintWidget widget;
        if (this.mAlignedDimensions != null && this.mAlignedBiggestElementsInCols != null && this.mAlignedBiggestElementsInRows != null) {
            for (int i = 0; i < this.mDisplayedWidgetsCount; i++) {
                this.mDisplayedWidgets[i].resetAnchors();
            }
            int[] iArr = this.mAlignedDimensions;
            int cols = iArr[0];
            int rows = iArr[1];
            ConstraintWidget previous = null;
            for (int i2 = 0; i2 < cols; i2++) {
                int index = i2;
                if (isInRtl) {
                    index = (cols - i2) - 1;
                }
                ConstraintWidget widget2 = this.mAlignedBiggestElementsInCols[index];
                if (!(widget2 == null || widget2.getVisibility() == 8)) {
                    if (i2 == 0) {
                        widget2.connect(widget2.mLeft, this.mLeft, getPaddingLeft());
                        widget2.setHorizontalChainStyle(this.mHorizontalStyle);
                        widget2.setHorizontalBiasPercent(this.mHorizontalBias);
                    }
                    if (i2 == cols - 1) {
                        widget2.connect(widget2.mRight, this.mRight, getPaddingRight());
                    }
                    if (i2 > 0) {
                        widget2.connect(widget2.mLeft, previous.mRight, this.mHorizontalGap);
                        previous.connect(previous.mRight, widget2.mLeft, 0);
                    }
                    previous = widget2;
                }
            }
            for (int j = 0; j < rows; j++) {
                ConstraintWidget widget3 = this.mAlignedBiggestElementsInRows[j];
                if (!(widget3 == null || widget3.getVisibility() == 8)) {
                    if (j == 0) {
                        widget3.connect(widget3.mTop, this.mTop, getPaddingTop());
                        widget3.setVerticalChainStyle(this.mVerticalStyle);
                        widget3.setVerticalBiasPercent(this.mVerticalBias);
                    }
                    if (j == rows - 1) {
                        widget3.connect(widget3.mBottom, this.mBottom, getPaddingBottom());
                    }
                    if (j > 0) {
                        widget3.connect(widget3.mTop, previous.mBottom, this.mVerticalGap);
                        previous.connect(previous.mBottom, widget3.mTop, 0);
                    }
                    previous = widget3;
                }
            }
            for (int i3 = 0; i3 < cols; i3++) {
                for (int j2 = 0; j2 < rows; j2++) {
                    int index2 = (j2 * cols) + i3;
                    if (this.mOrientation == 1) {
                        index2 = (i3 * rows) + j2;
                    }
                    ConstraintWidget[] constraintWidgetArr = this.mDisplayedWidgets;
                    if (!(index2 >= constraintWidgetArr.length || (widget = constraintWidgetArr[index2]) == null || widget.getVisibility() == 8)) {
                        ConstraintWidget biggestInCol = this.mAlignedBiggestElementsInCols[i3];
                        ConstraintWidget biggestInRow = this.mAlignedBiggestElementsInRows[j2];
                        if (widget != biggestInCol) {
                            widget.connect(widget.mLeft, biggestInCol.mLeft, 0);
                            widget.connect(widget.mRight, biggestInCol.mRight, 0);
                        }
                        if (widget != biggestInRow) {
                            widget.connect(widget.mTop, biggestInRow.mTop, 0);
                            widget.connect(widget.mBottom, biggestInRow.mBottom, 0);
                        }
                    }
                }
            }
        }
    }

    public void addToSolver(LinearSystem system) {
        super.addToSolver(system);
        boolean isInRtl = getParent() != null ? ((ConstraintWidgetContainer) getParent()).isRtl() : false;
        switch (this.mWrapMode) {
            case 0:
                if (this.mChainList.size() > 0) {
                    this.mChainList.get(0).createConstraints(isInRtl, 0, true);
                    break;
                }
                break;
            case 1:
                int count = this.mChainList.size();
                int i = 0;
                while (i < count) {
                    this.mChainList.get(i).createConstraints(isInRtl, i, i == count + -1);
                    i++;
                }
                break;
            case 2:
                createAlignedConstraints(isInRtl);
                break;
        }
        needsCallbackFromSolver(false);
    }
}
