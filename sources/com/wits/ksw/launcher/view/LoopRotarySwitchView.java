package com.wits.ksw.launcher.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.wits.ksw.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LoopRotarySwitchView extends RelativeLayout {
    private static final int LoopR = 200;
    private static final String TAG = "LoopRotarySwitchView";
    private static final int horizontal = 1;
    /* access modifiers changed from: private */
    public static Boolean isCanSwitchItem = true;
    private static final int vertical = 0;
    /* access modifiers changed from: private */
    public float angle;
    /* access modifiers changed from: private */
    public AutoScrollDirection autoRotatinDirection;
    private boolean autoRotation;
    int bottom;
    private float distance;
    Handler handlerFocus;
    /* access modifiers changed from: private */
    public boolean isCanClickListener;
    private float last_angle;
    private float limitX;
    LoopRotarySwitchViewHandler loopHandler;
    /* access modifiers changed from: private */
    public int loopRotationX;
    /* access modifiers changed from: private */
    public int loopRotationZ;
    private Context mContext;
    private GestureDetector mGestureDetector;
    private int mOrientation;
    private float multiple;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public OnItemSelectedListener onItemSelectedListener;
    private OnLoopViewTouchListener onLoopViewTouchListener;
    /* access modifiers changed from: private */
    public float r;
    private ValueAnimator rAnimation;
    private ValueAnimator restAnimator;
    /* access modifiers changed from: private */
    public int selectItem;
    /* access modifiers changed from: private */
    public int size;
    /* access modifiers changed from: private */
    public boolean touching;
    private View viewPreFocus;
    /* access modifiers changed from: private */
    public List<View> views;
    private float x;
    private ValueAnimator xAnimation;
    private ValueAnimator zAnimation;

    public enum AutoScrollDirection {
        left,
        right
    }

    public interface OnItemClickListener {
        void onItemClick(int i, View view);
    }

    public interface OnItemSelectedListener {
        void selected(int i, View view);
    }

    public interface OnLoopViewTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    static /* synthetic */ float access$218(LoopRotarySwitchView x0, double x1) {
        float f = (float) (((double) x0.angle) + x1);
        x0.angle = f;
        return f;
    }

    public LoopRotarySwitchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoopRotarySwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopRotarySwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mOrientation = 1;
        this.restAnimator = null;
        this.rAnimation = null;
        this.zAnimation = null;
        this.xAnimation = null;
        this.loopRotationX = 0;
        this.loopRotationZ = 0;
        this.mGestureDetector = null;
        this.selectItem = 0;
        this.size = 0;
        this.r = 200.0f;
        this.multiple = 2.0f;
        this.distance = 2.0f * 200.0f;
        this.angle = 0.0f;
        this.last_angle = 0.0f;
        this.autoRotation = false;
        this.touching = false;
        this.autoRotatinDirection = AutoScrollDirection.left;
        this.views = new ArrayList();
        this.onItemSelectedListener = null;
        this.onLoopViewTouchListener = null;
        this.onItemClickListener = null;
        this.isCanClickListener = true;
        this.limitX = 30.0f;
        this.viewPreFocus = null;
        this.loopHandler = new LoopRotarySwitchViewHandler(PathInterpolatorCompat.MAX_NUM_POINTS) {
            public void doScroll() {
                try {
                    if (LoopRotarySwitchView.this.size != 0) {
                        int perAngle = 0;
                        switch (AnonymousClass11.$SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection[LoopRotarySwitchView.this.autoRotatinDirection.ordinal()]) {
                            case 1:
                                perAngle = 360 / LoopRotarySwitchView.this.size;
                                break;
                            case 2:
                                perAngle = -360 / LoopRotarySwitchView.this.size;
                                break;
                        }
                        if (LoopRotarySwitchView.this.angle == 360.0f) {
                            float unused = LoopRotarySwitchView.this.angle = 0.0f;
                        }
                        LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                        loopRotarySwitchView.AnimRotationTo(loopRotarySwitchView.angle + ((float) perAngle), (Runnable) null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.handlerFocus = new Handler(new Handler.Callback() {
            public final boolean handleMessage(Message message) {
                return LoopRotarySwitchView.this.lambda$new$0$LoopRotarySwitchView(message);
            }
        });
        this.bottom = 0;
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopRotarySwitchView);
        this.mOrientation = typedArray.getInt(2, 1);
        this.autoRotation = typedArray.getBoolean(0, false);
        this.r = typedArray.getDimension(3, 200.0f);
        int direction = typedArray.getInt(1, 0);
        typedArray.recycle();
        this.mGestureDetector = new GestureDetector(context, getGeomeryController());
        if (this.mOrientation == 1) {
            this.loopRotationZ = 0;
        } else {
            this.loopRotationZ = 90;
        }
        if (direction == 0) {
            this.autoRotatinDirection = AutoScrollDirection.left;
        } else {
            this.autoRotatinDirection = AutoScrollDirection.right;
        }
        this.loopHandler.setLoop(this.autoRotation);
    }

    /* renamed from: com.wits.ksw.launcher.view.LoopRotarySwitchView$11  reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection;

        static {
            int[] iArr = new int[AutoScrollDirection.values().length];
            $SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection = iArr;
            try {
                iArr[AutoScrollDirection.left.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection[AutoScrollDirection.right.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private <T> void sortList(List<View> list) {
        Comparator comparator = new SortComparator();
        T[] array = list.toArray(new Object[list.size()]);
        Arrays.sort(array, comparator);
        int i = 0;
        ListIterator<View> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.set(array[i]);
            i++;
        }
        for (int j = 0; j < list.size(); j++) {
            list.get(j).bringToFront();
        }
    }

    private class SortComparator implements Comparator<View> {
        private SortComparator() {
        }

        public int compare(View lhs, View rhs) {
            try {
                return (int) ((lhs.getScaleX() * 1000.0f) - (rhs.getScaleX() * 1000.0f));
            } catch (Exception e) {
                return 0;
            }
        }
    }

    private GestureDetector.SimpleOnGestureListener getGeomeryController() {
        return new GestureDetector.SimpleOnGestureListener() {
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                LoopRotarySwitchView.access$218(loopRotarySwitchView, (Math.cos(Math.toRadians((double) loopRotarySwitchView.loopRotationZ)) * ((double) (distanceX / 4.0f))) + (Math.sin(Math.toRadians((double) LoopRotarySwitchView.this.loopRotationZ)) * ((double) (distanceY / 4.0f))));
                LoopRotarySwitchView.this.initView();
                return true;
            }
        };
    }

    public /* synthetic */ boolean lambda$new$0$LoopRotarySwitchView(Message message) {
        switch (message.what) {
            case 0:
                Log.i(TAG, "handler i=" + message.arg1);
                setSelectItem(message.arg1);
                return false;
            default:
                return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0194 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initView() {
        /*
            r21 = this;
            r0 = r21
            r1 = 0
        L_0x0003:
            java.util.List<android.view.View> r2 = r0.views
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0198
            float r2 = r0.angle
            r3 = 1127481344(0x43340000, float:180.0)
            float r2 = r2 + r3
            int r3 = r1 * 360
            int r4 = r0.size
            int r3 = r3 / r4
            float r3 = (float) r3
            float r2 = r2 - r3
            double r2 = (double) r2
            double r4 = java.lang.Math.toRadians(r2)
            double r4 = java.lang.Math.sin(r4)
            float r4 = (float) r4
            float r5 = r0.r
            float r4 = r4 * r5
            double r5 = java.lang.Math.toRadians(r2)
            double r5 = java.lang.Math.cos(r5)
            float r5 = (float) r5
            float r6 = r0.r
            float r5 = r5 * r6
            float r7 = r0.distance
            float r8 = r7 - r5
            float r7 = r7 + r6
            float r8 = r8 / r7
            java.util.List<android.view.View> r6 = r0.views
            java.lang.Object r6 = r6.get(r1)
            android.view.View r6 = (android.view.View) r6
            r7 = 1056964608(0x3f000000, float:0.5)
            float r9 = java.lang.Math.max(r8, r7)
            r6.setScaleX(r9)
            java.util.List<android.view.View> r6 = r0.views
            java.lang.Object r6 = r6.get(r1)
            android.view.View r6 = (android.view.View) r6
            float r9 = java.lang.Math.max(r8, r7)
            r6.setScaleY(r9)
            java.util.List<android.view.View> r6 = r0.views
            java.lang.Object r6 = r6.get(r1)
            android.view.View r6 = (android.view.View) r6
            float r7 = java.lang.Math.max(r8, r7)
            r6.setAlpha(r7)
            int r6 = r0.loopRotationX
            double r6 = (double) r6
            double r9 = java.lang.Math.toRadians(r2)
            double r9 = java.lang.Math.cos(r9)
            double r6 = r6 * r9
            double r6 = java.lang.Math.toRadians(r6)
            double r6 = java.lang.Math.sin(r6)
            float r6 = (float) r6
            float r7 = r0.r
            float r6 = r6 * r7
            int r7 = r0.loopRotationZ
            int r7 = -r7
            double r9 = (double) r7
            double r9 = java.lang.Math.toRadians(r9)
            double r9 = java.lang.Math.sin(r9)
            float r7 = (float) r9
            float r7 = -r7
            float r7 = r7 * r4
            int r9 = r0.loopRotationZ
            int r9 = -r9
            double r9 = (double) r9
            double r9 = java.lang.Math.toRadians(r9)
            double r9 = java.lang.Math.cos(r9)
            float r9 = (float) r9
            float r9 = r9 * r4
            float r9 = r9 - r4
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            float r11 = r4 + r9
            r10.setTranslationX(r11)
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            float r11 = r6 + r7
            r10.setTranslationY(r11)
            r10 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r12 = r2 % r10
            r14 = 0
            int r14 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r14 >= 0) goto L_0x00c3
            double r12 = r12 + r10
        L_0x00c3:
            r14 = 4644407484470001664(0x4074400000000000, double:324.0)
            int r16 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            r17 = 360(0x168, float:5.04E-43)
            if (r16 >= 0) goto L_0x00f2
            int r16 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r16 < 0) goto L_0x00d7
            r16 = r4
            r20 = r5
            goto L_0x00f6
        L_0x00d7:
            java.util.List<android.view.View> r14 = r0.views
            java.lang.Object r14 = r14.get(r1)
            android.view.View r14 = (android.view.View) r14
            double r10 = r10 - r12
            int r15 = r0.size
            int r15 = r17 / r15
            r16 = r4
            r20 = r5
            double r4 = (double) r15
            double r10 = r10 / r4
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r4 - r10
            float r4 = (float) r4
            r14.setAlpha(r4)
            goto L_0x0158
        L_0x00f2:
            r16 = r4
            r20 = r5
        L_0x00f6:
            r4 = r2
            r10 = 4641803840935428096(0x406b000000000000, double:216.0)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            r18 = 4643070478330626048(0x406f800000000000, double:252.0)
            if (r10 >= 0) goto L_0x011c
            int r10 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r10 >= 0) goto L_0x011c
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            double r18 = r18 - r12
            int r11 = r0.size
            int r11 = r17 / r11
            double r14 = (double) r11
            double r14 = r18 / r14
            float r11 = (float) r14
            r10.setAlpha(r11)
            goto L_0x0157
        L_0x011c:
            int r10 = (r18 > r12 ? 1 : (r18 == r12 ? 0 : -1))
            if (r10 > 0) goto L_0x013e
            int r10 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x0125
            goto L_0x013e
        L_0x0125:
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            r11 = 0
            r10.setAlpha(r11)
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            r11 = 0
            r10.setClickable(r11)
            goto L_0x0157
        L_0x013e:
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            r11 = 1065353216(0x3f800000, float:1.0)
            r10.setAlpha(r11)
            java.util.List<android.view.View> r10 = r0.views
            java.lang.Object r10 = r10.get(r1)
            android.view.View r10 = (android.view.View) r10
            r11 = 1
            r10.setClickable(r11)
        L_0x0157:
        L_0x0158:
            java.util.List<android.view.View> r4 = r0.views
            java.lang.Object r4 = r4.get(r1)
            android.view.View r4 = (android.view.View) r4
            android.view.View$OnFocusChangeListener r4 = r4.getOnFocusChangeListener()
            if (r4 != 0) goto L_0x0194
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "initView: setOnFocusChangeListener view="
            java.lang.StringBuilder r4 = r4.append(r5)
            java.util.List<android.view.View> r5 = r0.views
            java.lang.Object r5 = r5.get(r1)
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "LoopRotarySwitchView"
            android.util.Log.i(r5, r4)
            java.util.List<android.view.View> r4 = r0.views
            java.lang.Object r4 = r4.get(r1)
            android.view.View r4 = (android.view.View) r4
            com.wits.ksw.launcher.view.LoopRotarySwitchView$3 r5 = new com.wits.ksw.launcher.view.LoopRotarySwitchView$3
            r5.<init>()
            r4.setOnFocusChangeListener(r5)
        L_0x0194:
            int r1 = r1 + 1
            goto L_0x0003
        L_0x0198:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.clear()
            r2 = 0
        L_0x01a1:
            java.util.List<android.view.View> r3 = r0.views
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x01b7
            java.util.List<android.view.View> r3 = r0.views
            java.lang.Object r3 = r3.get(r2)
            android.view.View r3 = (android.view.View) r3
            r1.add(r3)
            int r2 = r2 + 1
            goto L_0x01a1
        L_0x01b7:
            r0.sortList(r1)
            r21.postInvalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.view.LoopRotarySwitchView.initView():void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
        if (this.autoRotation) {
            LoopRotarySwitchViewHandler loopRotarySwitchViewHandler = this.loopHandler;
            loopRotarySwitchViewHandler.sendEmptyMessageDelayed(1000, loopRotarySwitchViewHandler.loopTime);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r2, int b) {
        super.onLayout(changed, l, t, r2, b);
        if (changed) {
            checkChildView();
            OnItemSelectedListener onItemSelectedListener2 = this.onItemSelectedListener;
            if (onItemSelectedListener2 != null) {
                this.isCanClickListener = true;
                int i = this.selectItem;
                onItemSelectedListener2.selected(i, this.views.get(i));
            }
            RAnimation();
        }
    }

    public void RAnimation() {
        RAnimation(1.0f, this.r);
    }

    public void RAnimation(boolean fromZeroToLoopR) {
        if (fromZeroToLoopR) {
            RAnimation(1.0f, 200.0f);
        } else {
            RAnimation(200.0f, 1.0f);
        }
    }

    public void RAnimation(float from, float to) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{from, to});
        this.rAnimation = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = LoopRotarySwitchView.this.r = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.rAnimation.setInterpolator(new DecelerateInterpolator());
        this.rAnimation.setDuration(0);
        this.rAnimation.start();
    }

    public void checkChildView() {
        for (int i = 0; i < this.views.size(); i++) {
            this.views.remove(i);
        }
        int i2 = getChildCount();
        this.size = i2;
        for (int i3 = 0; i3 < i2; i3++) {
            View view = getChildAt(i3);
            final int position = i3;
            this.views.add(view);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Log.i(LoopRotarySwitchView.TAG, "onClick: isCanClickListener=" + LoopRotarySwitchView.this.isCanClickListener + " v=" + v);
                    if (position != LoopRotarySwitchView.this.selectItem && LoopRotarySwitchView.isCanSwitchItem.booleanValue()) {
                        LoopRotarySwitchView.this.setSelectItem(position);
                    }
                    if (LoopRotarySwitchView.this.isCanClickListener && LoopRotarySwitchView.this.onItemClickListener != null) {
                        LoopRotarySwitchView.this.onItemClickListener.onItemClick(position, (View) LoopRotarySwitchView.this.views.get(position));
                    }
                }
            });
        }
        setFocusView();
    }

    private void restPosition() {
        float finall;
        int i = this.size;
        if (i != 0) {
            float part = (float) (360 / i);
            float f = this.angle;
            if (f < 0.0f) {
                part = -part;
            }
            float minvalue = ((float) ((int) (f / part))) * part;
            float maxvalue = (((float) ((int) (f / part))) * part) + part;
            if (f >= 0.0f) {
                if (f - this.last_angle > 0.0f) {
                    finall = maxvalue;
                } else {
                    finall = minvalue;
                }
            } else if (f - this.last_angle < 0.0f) {
                finall = maxvalue;
            } else {
                finall = minvalue;
            }
            AnimRotationTo(finall, (Runnable) null);
        }
    }

    /* access modifiers changed from: private */
    public void AnimRotationTo(float finall, final Runnable complete) {
        float f = this.angle;
        if (f != finall) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, finall});
            this.restAnimator = ofFloat;
            ofFloat.setInterpolator(new DecelerateInterpolator());
            this.restAnimator.setDuration(300);
            this.restAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (!LoopRotarySwitchView.this.touching) {
                        float unused = LoopRotarySwitchView.this.angle = ((Float) animation.getAnimatedValue()).floatValue();
                        LoopRotarySwitchView.this.initView();
                    }
                }
            });
            this.restAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animation) {
                    Log.d(LoopRotarySwitchView.TAG, "onAnimationStart: ");
                    Boolean unused = LoopRotarySwitchView.isCanSwitchItem = false;
                }

                public void onAnimationEnd(Animator animation) {
                    Log.d(LoopRotarySwitchView.TAG, "onAnimationEnd: ");
                    Boolean unused = LoopRotarySwitchView.isCanSwitchItem = true;
                    if (!LoopRotarySwitchView.this.touching) {
                        LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                        int unused2 = loopRotarySwitchView.selectItem = loopRotarySwitchView.calculateItem();
                        Log.i(LoopRotarySwitchView.TAG, "onAnimationEnd: selectitem=" + LoopRotarySwitchView.this.selectItem);
                        if (LoopRotarySwitchView.this.selectItem < 0) {
                            LoopRotarySwitchView loopRotarySwitchView2 = LoopRotarySwitchView.this;
                            int unused3 = loopRotarySwitchView2.selectItem = loopRotarySwitchView2.size + LoopRotarySwitchView.this.selectItem;
                        }
                        if (LoopRotarySwitchView.this.onItemSelectedListener != null) {
                            LoopRotarySwitchView.this.onItemSelectedListener.selected(LoopRotarySwitchView.this.selectItem, (View) LoopRotarySwitchView.this.views.get(LoopRotarySwitchView.this.selectItem));
                        }
                        LoopRotarySwitchView.this.setFocusView();
                        boolean unused4 = LoopRotarySwitchView.this.isCanClickListener = true;
                    }
                }

                public void onAnimationCancel(Animator animation) {
                }

                public void onAnimationRepeat(Animator animation) {
                }
            });
            if (complete != null) {
                this.restAnimator.addListener(new Animator.AnimatorListener() {
                    public void onAnimationStart(Animator animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                        complete.run();
                    }

                    public void onAnimationCancel(Animator animation) {
                    }

                    public void onAnimationRepeat(Animator animation) {
                    }
                });
            }
            this.restAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public void setFocusView() {
        Iterator<View> it = this.views.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            View view = it.next();
            if (this.views.get(this.selectItem).getId() == view.getId()) {
                z = true;
            }
            view.setSelected(z);
        }
        if (!this.views.get(this.selectItem).hasFocus()) {
            this.views.get(this.selectItem).setFocusable(true);
            this.views.get(this.selectItem).setFocusableInTouchMode(true);
            this.views.get(this.selectItem).requestFocus();
            this.views.get(this.selectItem).setFocusableInTouchMode(false);
        }
    }

    /* access modifiers changed from: private */
    public int calculateItem() {
        float f = this.angle % 360.0f;
        this.angle = f;
        int i = this.size;
        return ((int) (f / ((float) (360 / i)))) % i;
    }

    private boolean onTouch(MotionEvent event) {
        if (event.getAction() == 0) {
            this.last_angle = this.angle;
            this.touching = true;
        }
        if (this.mGestureDetector.onTouchEvent(event)) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (event.getAction() != 1 && event.getAction() != 3) {
            return true;
        }
        this.touching = false;
        restPosition();
        return true;
    }

    public boolean onTouchEvent(MotionEvent event) {
        OnLoopViewTouchListener onLoopViewTouchListener2 = this.onLoopViewTouchListener;
        if (onLoopViewTouchListener2 != null) {
            onLoopViewTouchListener2.onTouch(event);
        }
        isCanClickListener(event);
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouch(ev);
        OnLoopViewTouchListener onLoopViewTouchListener2 = this.onLoopViewTouchListener;
        if (onLoopViewTouchListener2 != null) {
            onLoopViewTouchListener2.onTouch(ev);
        }
        isCanClickListener(ev);
        return super.dispatchTouchEvent(ev);
    }

    public void isCanClickListener(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.x = event.getX();
                if (this.autoRotation) {
                    this.loopHandler.removeMessages(1000);
                    return;
                }
                return;
            case 1:
            case 3:
                if (this.autoRotation) {
                    LoopRotarySwitchViewHandler loopRotarySwitchViewHandler = this.loopHandler;
                    loopRotarySwitchViewHandler.sendEmptyMessageDelayed(1000, loopRotarySwitchViewHandler.loopTime);
                }
                float x2 = event.getX();
                float f = this.x;
                if (x2 - f > this.limitX || f - event.getX() > this.limitX) {
                    this.isCanClickListener = false;
                    return;
                } else {
                    this.isCanClickListener = true;
                    return;
                }
            default:
                return;
        }
    }

    public List<View> getViews() {
        return this.views;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle2) {
        this.angle = angle2;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float distance2) {
        this.distance = distance2;
    }

    public float getR() {
        return this.r;
    }

    public int getSelectItem() {
        return this.selectItem;
    }

    public void setSelectItem(int pos) {
        Log.i(TAG, "setSelectItem: pos=" + pos);
        ValueAnimator valueAnimator = this.restAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.restAnimator.end();
            this.restAnimator.cancel();
        }
        if (pos >= 0 && pos < this.views.size()) {
            int calculateItem = calculateItem();
            this.bottom = calculateItem;
            if (calculateItem == pos) {
                Log.e("setToPos", "bottom");
            }
            int difPos = this.bottom - pos;
            if (((double) difPos) < 0.0d - Math.floor((double) (this.views.size() / 2))) {
                difPos += this.views.size();
            }
            if (((double) difPos) > Math.floor((double) (this.views.size() / 2))) {
                difPos -= this.views.size();
            }
            if (this.views.size() % 2 == 0 && difPos == this.views.size() / 2) {
                difPos = (this.views.size() / 2) + 0;
            }
            float angle1 = this.angle - ((((float) difPos) * 360.0f) / ((float) this.views.size()));
            Log.i(TAG, "setSelectItem: difPos=" + difPos + " angle1=" + angle1);
            AnimRotationTo(angle1, (Runnable) null);
        }
    }

    public LoopRotarySwitchView setR(float r2) {
        this.r = r2;
        this.distance = this.multiple * r2;
        return this;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener2) {
        this.onItemSelectedListener = onItemSelectedListener2;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void setOnLoopViewTouchListener(OnLoopViewTouchListener onLoopViewTouchListener2) {
        this.onLoopViewTouchListener = onLoopViewTouchListener2;
    }

    public LoopRotarySwitchView setAutoRotation(boolean autoRotation2) {
        this.autoRotation = autoRotation2;
        this.loopHandler.setLoop(autoRotation2);
        return this;
    }

    public long getAutoRotationTime() {
        return this.loopHandler.loopTime;
    }

    public LoopRotarySwitchView setAutoRotationTime(long autoRotationTime) {
        this.loopHandler.setLoopTime(autoRotationTime);
        return this;
    }

    public boolean isAutoRotation() {
        return this.autoRotation;
    }

    public LoopRotarySwitchView setMultiple(float mMultiple) {
        this.multiple = mMultiple;
        this.distance = this.r * mMultiple;
        return this;
    }

    public LoopRotarySwitchView setAutoScrollDirection(AutoScrollDirection mAutoScrollDirection) {
        this.autoRotatinDirection = mAutoScrollDirection;
        return this;
    }

    public void createXAnimation(int from, int to, boolean start) {
        ValueAnimator valueAnimator = this.xAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.xAnimation.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{from, to});
        this.xAnimation = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int unused = LoopRotarySwitchView.this.loopRotationX = ((Integer) animation.getAnimatedValue()).intValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.xAnimation.setInterpolator(new DecelerateInterpolator());
        this.xAnimation.setDuration(2000);
        if (start) {
            this.xAnimation.start();
        }
    }

    public ValueAnimator createZAnimation(int from, int to, boolean start) {
        ValueAnimator valueAnimator = this.zAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.zAnimation.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{from, to});
        this.zAnimation = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int unused = LoopRotarySwitchView.this.loopRotationZ = ((Integer) animation.getAnimatedValue()).intValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.zAnimation.setInterpolator(new DecelerateInterpolator());
        this.zAnimation.setDuration(2000);
        if (start) {
            this.zAnimation.start();
        }
        return this.zAnimation;
    }

    public LoopRotarySwitchView setOrientation(int mOrientation2) {
        boolean z = true;
        if (mOrientation2 != 1) {
            z = false;
        }
        setHorizontal(z, false);
        return this;
    }

    public LoopRotarySwitchView setHorizontal(boolean horizontal2, boolean anim) {
        if (!anim) {
            if (horizontal2) {
                setLoopRotationZ(0);
            } else {
                setLoopRotationZ(90);
            }
            initView();
        } else if (horizontal2) {
            createZAnimation(getLoopRotationZ(), 0, true);
        } else {
            createZAnimation(getLoopRotationZ(), 90, true);
        }
        return this;
    }

    public LoopRotarySwitchView setLoopRotationX(int loopRotationX2) {
        this.loopRotationX = loopRotationX2;
        return this;
    }

    public LoopRotarySwitchView setLoopRotationZ(int loopRotationZ2) {
        this.loopRotationZ = loopRotationZ2;
        return this;
    }

    public int getLoopRotationX() {
        return this.loopRotationX;
    }

    public int getLoopRotationZ() {
        return this.loopRotationZ;
    }

    public ValueAnimator getRestAnimator() {
        return this.restAnimator;
    }

    public ValueAnimator getrAnimation() {
        return this.rAnimation;
    }

    public void setzAnimation(ValueAnimator zAnimation2) {
        this.zAnimation = zAnimation2;
    }

    public ValueAnimator getzAnimation() {
        return this.zAnimation;
    }

    public void setxAnimation(ValueAnimator xAnimation2) {
        this.xAnimation = xAnimation2;
    }

    public ValueAnimator getxAnimation() {
        return this.xAnimation;
    }
}
