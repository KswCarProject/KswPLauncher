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
        this.distance = this.multiple * this.r;
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
                        LoopRotarySwitchView.this.AnimRotationTo(LoopRotarySwitchView.this.angle + ((float) perAngle), (Runnable) null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.handlerFocus = new Handler(new Handler.Callback() {
            public final boolean handleMessage(Message message) {
                return LoopRotarySwitchView.lambda$new$0(LoopRotarySwitchView.this, message);
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
        static final /* synthetic */ int[] $SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection = new int[AutoScrollDirection.values().length];

        static {
            try {
                $SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection[AutoScrollDirection.left.ordinal()] = 1;
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
                float unused = LoopRotarySwitchView.this.angle = (float) (((double) LoopRotarySwitchView.this.angle) + (Math.cos(Math.toRadians((double) LoopRotarySwitchView.this.loopRotationZ)) * ((double) (distanceX / 4.0f))) + (Math.sin(Math.toRadians((double) LoopRotarySwitchView.this.loopRotationZ)) * ((double) (distanceY / 4.0f))));
                LoopRotarySwitchView.this.initView();
                return true;
            }
        };
    }

    public static /* synthetic */ boolean lambda$new$0(LoopRotarySwitchView loopRotarySwitchView, Message message) {
        if (message.what != 0) {
            return false;
        }
        Log.i(TAG, "handler i=" + message.arg1);
        loopRotarySwitchView.setSelectItem(message.arg1);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x019d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initView() {
        /*
            r23 = this;
            r0 = r23
            r2 = 0
        L_0x0003:
            java.util.List<android.view.View> r3 = r0.views
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x01a1
            float r3 = r0.angle
            r4 = 1127481344(0x43340000, float:180.0)
            float r3 = r3 + r4
            int r4 = r2 * 360
            int r5 = r0.size
            int r4 = r4 / r5
            float r4 = (float) r4
            float r3 = r3 - r4
            double r3 = (double) r3
            double r5 = java.lang.Math.toRadians(r3)
            double r5 = java.lang.Math.sin(r5)
            float r5 = (float) r5
            float r6 = r0.r
            float r5 = r5 * r6
            double r6 = java.lang.Math.toRadians(r3)
            double r6 = java.lang.Math.cos(r6)
            float r6 = (float) r6
            float r7 = r0.r
            float r6 = r6 * r7
            float r7 = r0.distance
            float r7 = r7 - r6
            float r8 = r0.distance
            float r9 = r0.r
            float r8 = r8 + r9
            float r7 = r7 / r8
            java.util.List<android.view.View> r8 = r0.views
            java.lang.Object r8 = r8.get(r2)
            android.view.View r8 = (android.view.View) r8
            r9 = 1056964608(0x3f000000, float:0.5)
            float r10 = java.lang.Math.max(r7, r9)
            r8.setScaleX(r10)
            java.util.List<android.view.View> r8 = r0.views
            java.lang.Object r8 = r8.get(r2)
            android.view.View r8 = (android.view.View) r8
            float r10 = java.lang.Math.max(r7, r9)
            r8.setScaleY(r10)
            java.util.List<android.view.View> r8 = r0.views
            java.lang.Object r8 = r8.get(r2)
            android.view.View r8 = (android.view.View) r8
            float r9 = java.lang.Math.max(r7, r9)
            r8.setAlpha(r9)
            int r8 = r0.loopRotationX
            double r8 = (double) r8
            double r10 = java.lang.Math.toRadians(r3)
            double r10 = java.lang.Math.cos(r10)
            double r8 = r8 * r10
            double r8 = java.lang.Math.toRadians(r8)
            double r8 = java.lang.Math.sin(r8)
            float r8 = (float) r8
            float r9 = r0.r
            float r8 = r8 * r9
            int r9 = r0.loopRotationZ
            int r9 = -r9
            double r9 = (double) r9
            double r9 = java.lang.Math.toRadians(r9)
            double r9 = java.lang.Math.sin(r9)
            float r9 = (float) r9
            float r9 = -r9
            float r9 = r9 * r5
            int r10 = r0.loopRotationZ
            int r10 = -r10
            double r10 = (double) r10
            double r10 = java.lang.Math.toRadians(r10)
            double r10 = java.lang.Math.cos(r10)
            float r10 = (float) r10
            float r10 = r10 * r5
            float r10 = r10 - r5
            java.util.List<android.view.View> r11 = r0.views
            java.lang.Object r11 = r11.get(r2)
            android.view.View r11 = (android.view.View) r11
            float r12 = r5 + r10
            r11.setTranslationX(r12)
            java.util.List<android.view.View> r11 = r0.views
            java.lang.Object r11 = r11.get(r2)
            android.view.View r11 = (android.view.View) r11
            float r12 = r8 + r9
            r11.setTranslationY(r12)
            r11 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r13 = r3 % r11
            r15 = 0
            int r15 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r15 >= 0) goto L_0x00c6
            double r13 = r13 + r11
        L_0x00c6:
            r15 = 4644407484470001664(0x4074400000000000, double:324.0)
            int r17 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            r18 = 360(0x168, float:5.04E-43)
            if (r17 >= 0) goto L_0x00f9
            int r17 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r17 < 0) goto L_0x00da
            r19 = r5
            r20 = r6
            goto L_0x00fd
        L_0x00da:
            java.util.List<android.view.View> r15 = r0.views
            java.lang.Object r15 = r15.get(r2)
            android.view.View r15 = (android.view.View) r15
            r16 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r11 = r11 - r13
            int r1 = r0.size
            int r1 = r18 / r1
            r19 = r5
            r20 = r6
            double r5 = (double) r1
            double r11 = r11 / r5
            double r5 = r16 - r11
            float r1 = (float) r5
            r15.setAlpha(r1)
            r21 = r3
            r3 = 0
            goto L_0x0163
        L_0x00f9:
            r19 = r5
            r20 = r6
        L_0x00fd:
            r5 = r3
            r11 = 4641803840935428096(0x406b000000000000, double:216.0)
            int r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            r11 = 4643070478330626048(0x406f800000000000, double:252.0)
            if (r1 >= 0) goto L_0x0124
            int r1 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r1 >= 0) goto L_0x0124
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            double r11 = r11 - r13
            int r15 = r0.size
            int r15 = r18 / r15
            r21 = r3
            double r3 = (double) r15
            double r11 = r11 / r3
            float r3 = (float) r11
            r1.setAlpha(r3)
            r3 = 0
            goto L_0x0162
        L_0x0124:
            r21 = r3
            int r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r1 > 0) goto L_0x0148
            int r1 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r1 <= 0) goto L_0x012f
            goto L_0x0148
        L_0x012f:
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            r3 = 0
            r1.setAlpha(r3)
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            r3 = 0
            r1.setClickable(r3)
            goto L_0x0162
        L_0x0148:
            r3 = 0
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            r4 = 1065353216(0x3f800000, float:1.0)
            r1.setAlpha(r4)
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            r4 = 1
            r1.setClickable(r4)
        L_0x0162:
        L_0x0163:
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            android.view.View$OnFocusChangeListener r1 = r1.getOnFocusChangeListener()
            if (r1 != 0) goto L_0x019d
            java.lang.String r1 = "LoopRotarySwitchView"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "initView: setOnFocusChangeListener view="
            r4.append(r5)
            java.util.List<android.view.View> r5 = r0.views
            java.lang.Object r5 = r5.get(r2)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.i(r1, r4)
            java.util.List<android.view.View> r1 = r0.views
            java.lang.Object r1 = r1.get(r2)
            android.view.View r1 = (android.view.View) r1
            com.wits.ksw.launcher.view.LoopRotarySwitchView$3 r4 = new com.wits.ksw.launcher.view.LoopRotarySwitchView$3
            r4.<init>()
            r1.setOnFocusChangeListener(r4)
        L_0x019d:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x01a1:
            r3 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.clear()
        L_0x01ab:
            r2 = r3
            java.util.List<android.view.View> r3 = r0.views
            int r3 = r3.size()
            if (r2 >= r3) goto L_0x01c0
            java.util.List<android.view.View> r3 = r0.views
            java.lang.Object r3 = r3.get(r2)
            r1.add(r3)
            int r3 = r2 + 1
            goto L_0x01ab
        L_0x01c0:
            r0.sortList(r1)
            r23.postInvalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.view.LoopRotarySwitchView.initView():void");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
        if (this.autoRotation) {
            this.loopHandler.sendEmptyMessageDelayed(1000, this.loopHandler.loopTime);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r2, int b) {
        super.onLayout(changed, l, t, r2, b);
        if (changed) {
            checkChildView();
            if (this.onItemSelectedListener != null) {
                this.isCanClickListener = true;
                this.onItemSelectedListener.selected(this.selectItem, this.views.get(this.selectItem));
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
        this.rAnimation = ValueAnimator.ofFloat(new float[]{from, to});
        this.rAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
        int count = getChildCount();
        this.size = count;
        for (int i2 = 0; i2 < count; i2++) {
            View view = getChildAt(i2);
            final int position = i2;
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
        if (this.size != 0) {
            float part = (float) (360 / this.size);
            if (this.angle < 0.0f) {
                part = -part;
            }
            float minvalue = ((float) ((int) (this.angle / part))) * part;
            float maxvalue = (((float) ((int) (this.angle / part))) * part) + part;
            if (this.angle >= 0.0f) {
                if (this.angle - this.last_angle > 0.0f) {
                    finall = maxvalue;
                } else {
                    finall = minvalue;
                }
            } else if (this.angle - this.last_angle < 0.0f) {
                finall = maxvalue;
            } else {
                finall = minvalue;
            }
            AnimRotationTo(finall, (Runnable) null);
        }
    }

    /* access modifiers changed from: private */
    public void AnimRotationTo(float finall, final Runnable complete) {
        if (this.angle != finall) {
            this.restAnimator = ValueAnimator.ofFloat(new float[]{this.angle, finall});
            this.restAnimator.setInterpolator(new DecelerateInterpolator());
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
                        int unused2 = LoopRotarySwitchView.this.selectItem = LoopRotarySwitchView.this.calculateItem();
                        Log.i(LoopRotarySwitchView.TAG, "onAnimationEnd: selectitem=" + LoopRotarySwitchView.this.selectItem);
                        if (LoopRotarySwitchView.this.selectItem < 0) {
                            int unused3 = LoopRotarySwitchView.this.selectItem = LoopRotarySwitchView.this.size + LoopRotarySwitchView.this.selectItem;
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
        this.angle %= 360.0f;
        return ((int) (this.angle / ((float) (360 / this.size)))) % this.size;
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
        if (this.onLoopViewTouchListener != null) {
            this.onLoopViewTouchListener.onTouch(event);
        }
        isCanClickListener(event);
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouch(ev);
        if (this.onLoopViewTouchListener != null) {
            this.onLoopViewTouchListener.onTouch(ev);
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
                    this.loopHandler.sendEmptyMessageDelayed(1000, this.loopHandler.loopTime);
                }
                if (event.getX() - this.x > this.limitX || this.x - event.getX() > this.limitX) {
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
        if (this.restAnimator != null && this.restAnimator.isRunning()) {
            this.restAnimator.end();
            this.restAnimator.cancel();
        }
        if (pos >= 0 && pos < this.views.size()) {
            this.bottom = calculateItem();
            if (this.bottom == pos) {
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
        this.distance = this.multiple * this.r;
        return this;
    }

    public LoopRotarySwitchView setAutoScrollDirection(AutoScrollDirection mAutoScrollDirection) {
        this.autoRotatinDirection = mAutoScrollDirection;
        return this;
    }

    public void createXAnimation(int from, int to, boolean start) {
        if (this.xAnimation != null && this.xAnimation.isRunning()) {
            this.xAnimation.cancel();
        }
        this.xAnimation = ValueAnimator.ofInt(new int[]{from, to});
        this.xAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
        if (this.zAnimation != null && this.zAnimation.isRunning()) {
            this.zAnimation.cancel();
        }
        this.zAnimation = ValueAnimator.ofInt(new int[]{from, to});
        this.zAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
