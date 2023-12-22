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
import com.wits.ksw.C0899R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes16.dex */
public class LoopRotarySwitchView extends RelativeLayout {
    private static final int LoopR = 200;
    private static final String TAG = "LoopRotarySwitchView";
    private static final int horizontal = 1;
    private static Boolean isCanSwitchItem = true;
    private static final int vertical = 0;
    private float angle;
    private AutoScrollDirection autoRotatinDirection;
    private boolean autoRotation;
    int bottom;
    private float distance;
    Handler handlerFocus;
    private boolean isCanClickListener;
    private float last_angle;
    private float limitX;
    LoopRotarySwitchViewHandler loopHandler;
    private int loopRotationX;
    private int loopRotationZ;
    private Context mContext;
    private GestureDetector mGestureDetector;
    private int mOrientation;
    private float multiple;
    private OnItemClickListener onItemClickListener;
    private OnItemSelectedListener onItemSelectedListener;
    private OnLoopViewTouchListener onLoopViewTouchListener;

    /* renamed from: r */
    private float f195r;
    private ValueAnimator rAnimation;
    private ValueAnimator restAnimator;
    private int selectItem;
    private int size;
    private boolean touching;
    private View viewPreFocus;
    private List<View> views;

    /* renamed from: x */
    private float f196x;
    private ValueAnimator xAnimation;
    private ValueAnimator zAnimation;

    /* loaded from: classes16.dex */
    public enum AutoScrollDirection {
        left,
        right
    }

    /* loaded from: classes16.dex */
    public interface OnItemClickListener {
        void onItemClick(int item, View view);
    }

    /* loaded from: classes16.dex */
    public interface OnItemSelectedListener {
        void selected(int item, View view);
    }

    /* loaded from: classes16.dex */
    public interface OnLoopViewTouchListener {
        void onTouch(MotionEvent event);
    }

    static /* synthetic */ float access$218(LoopRotarySwitchView x0, double x1) {
        float f = (float) (x0.angle + x1);
        x0.angle = f;
        return f;
    }

    public LoopRotarySwitchView(Context context) {
        this(context, null);
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
        this.f195r = 200.0f;
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
        this.loopHandler = new LoopRotarySwitchViewHandler(PathInterpolatorCompat.MAX_NUM_POINTS) { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.1
            @Override // com.wits.ksw.launcher.view.LoopRotarySwitchViewHandler
            public void doScroll() {
                try {
                    if (LoopRotarySwitchView.this.size != 0) {
                        int perAngle = 0;
                        switch (C108611.f197xcbdbe9ad[LoopRotarySwitchView.this.autoRotatinDirection.ordinal()]) {
                            case 1:
                                perAngle = 360 / LoopRotarySwitchView.this.size;
                                break;
                            case 2:
                                perAngle = (-360) / LoopRotarySwitchView.this.size;
                                break;
                        }
                        if (LoopRotarySwitchView.this.angle == 360.0f) {
                            LoopRotarySwitchView.this.angle = 0.0f;
                        }
                        LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                        loopRotarySwitchView.AnimRotationTo(loopRotarySwitchView.angle + perAngle, null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.handlerFocus = new Handler(new Handler.Callback() { // from class: com.wits.ksw.launcher.view.-$$Lambda$LoopRotarySwitchView$5rHmlGhZkb5CATT-mKmVRrCSyfg
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return LoopRotarySwitchView.this.lambda$new$0$LoopRotarySwitchView(message);
            }
        });
        this.bottom = 0;
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, C0899R.styleable.LoopRotarySwitchView);
        this.mOrientation = typedArray.getInt(2, 1);
        this.autoRotation = typedArray.getBoolean(0, false);
        this.f195r = typedArray.getDimension(3, 200.0f);
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

    /* renamed from: com.wits.ksw.launcher.view.LoopRotarySwitchView$11 */
    /* loaded from: classes16.dex */
    static /* synthetic */ class C108611 {

        /* renamed from: $SwitchMap$com$wits$ksw$launcher$view$LoopRotarySwitchView$AutoScrollDirection */
        static final /* synthetic */ int[] f197xcbdbe9ad;

        static {
            int[] iArr = new int[AutoScrollDirection.values().length];
            f197xcbdbe9ad = iArr;
            try {
                iArr[AutoScrollDirection.left.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f197xcbdbe9ad[AutoScrollDirection.right.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void sortList(List<View> list) {
        Comparator comparator = new SortComparator();
        Object[] array = list.toArray(new Object[list.size()]);
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

    /* loaded from: classes16.dex */
    private class SortComparator implements Comparator<View> {
        private SortComparator() {
        }

        @Override // java.util.Comparator
        public int compare(View lhs, View rhs) {
            try {
                int result = (int) ((lhs.getScaleX() * 1000.0f) - (rhs.getScaleX() * 1000.0f));
                return result;
            } catch (Exception e) {
                return 0;
            }
        }
    }

    private GestureDetector.SimpleOnGestureListener getGeomeryController() {
        return new GestureDetector.SimpleOnGestureListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                LoopRotarySwitchView.access$218(loopRotarySwitchView, (Math.cos(Math.toRadians(loopRotarySwitchView.loopRotationZ)) * (distanceX / 4.0f)) + (Math.sin(Math.toRadians(LoopRotarySwitchView.this.loopRotationZ)) * (distanceY / 4.0f)));
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

    /* JADX WARN: Removed duplicated region for block: B:30:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0194 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void initView() {
        for (int i = 0; i < this.views.size(); i++) {
            double radians = (this.angle + 180.0f) - ((i * 360) / this.size);
            float x0 = ((float) Math.sin(Math.toRadians(radians))) * this.f195r;
            float f = this.f195r;
            float y0 = ((float) Math.cos(Math.toRadians(radians))) * f;
            float f2 = this.distance;
            float scale0 = (f2 - y0) / (f2 + f);
            this.views.get(i).setScaleX(Math.max(scale0, 0.5f));
            this.views.get(i).setScaleY(Math.max(scale0, 0.5f));
            this.views.get(i).setAlpha(Math.max(scale0, 0.5f));
            float rotationX_y = ((float) Math.sin(Math.toRadians(this.loopRotationX * Math.cos(Math.toRadians(radians))))) * this.f195r;
            float rotationZ_y = (-((float) Math.sin(Math.toRadians(-this.loopRotationZ)))) * x0;
            float rotationZ_x = (((float) Math.cos(Math.toRadians(-this.loopRotationZ))) * x0) - x0;
            this.views.get(i).setTranslationX(x0 + rotationZ_x);
            this.views.get(i).setTranslationY(rotationX_y + rotationZ_y);
            double radians2 = radians % 360.0d;
            if (radians2 < 0.0d) {
                radians2 += 360.0d;
            }
            if (324.0d < radians2 && radians2 < 360.0d) {
                this.views.get(i).setAlpha((float) (1.0d - ((360.0d - radians2) / (360 / this.size))));
                if (this.views.get(i).getOnFocusChangeListener() != null) {
                    Log.i(TAG, "initView: setOnFocusChangeListener view=" + this.views.get(i));
                    this.views.get(i).setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.3
                        @Override // android.view.View.OnFocusChangeListener
                        public void onFocusChange(View view, boolean b) {
                            if (b) {
                                int viewPos = LoopRotarySwitchView.this.views.indexOf(view);
                                if (LoopRotarySwitchView.isCanSwitchItem.booleanValue()) {
                                    LoopRotarySwitchView.this.setSelectItem(viewPos);
                                }
                            }
                        }
                    });
                }
            }
            if (216.0d < radians2 && radians2 < 252.0d) {
                this.views.get(i).setAlpha((float) ((252.0d - radians2) / (360 / this.size)));
            } else if (252.0d > radians2 || radians2 > 324.0d) {
                this.views.get(i).setAlpha(1.0f);
                this.views.get(i).setClickable(true);
            } else {
                this.views.get(i).setAlpha(0.0f);
                this.views.get(i).setClickable(false);
            }
            if (this.views.get(i).getOnFocusChangeListener() != null) {
            }
        }
        List<View> arrayViewList = new ArrayList<>();
        arrayViewList.clear();
        for (int i2 = 0; i2 < this.views.size(); i2++) {
            arrayViewList.add(this.views.get(i2));
        }
        sortList(arrayViewList);
        postInvalidate();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initView();
        if (this.autoRotation) {
            LoopRotarySwitchViewHandler loopRotarySwitchViewHandler = this.loopHandler;
            loopRotarySwitchViewHandler.sendEmptyMessageDelayed(1000, loopRotarySwitchViewHandler.loopTime);
        }
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            checkChildView();
            OnItemSelectedListener onItemSelectedListener = this.onItemSelectedListener;
            if (onItemSelectedListener != null) {
                this.isCanClickListener = true;
                int i = this.selectItem;
                onItemSelectedListener.selected(i, this.views.get(i));
            }
            RAnimation();
        }
    }

    public void RAnimation() {
        RAnimation(1.0f, this.f195r);
    }

    public void RAnimation(boolean fromZeroToLoopR) {
        if (fromZeroToLoopR) {
            RAnimation(1.0f, 200.0f);
        } else {
            RAnimation(200.0f, 1.0f);
        }
    }

    public void RAnimation(float from, float to) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(from, to);
        this.rAnimation = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LoopRotarySwitchView.this.f195r = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.rAnimation.setInterpolator(new DecelerateInterpolator());
        this.rAnimation.setDuration(0L);
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
            view.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.5
                @Override // android.view.View.OnClickListener
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
        if (i == 0) {
            return;
        }
        float part = 360 / i;
        float f = this.angle;
        if (f < 0.0f) {
            part = -part;
        }
        float minvalue = ((int) (f / part)) * part;
        float maxvalue = (((int) (f / part)) * part) + part;
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
        AnimRotationTo(finall, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void AnimRotationTo(float finall, final Runnable complete) {
        float f = this.angle;
        if (f == finall) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, finall);
        this.restAnimator = ofFloat;
        ofFloat.setInterpolator(new DecelerateInterpolator());
        this.restAnimator.setDuration(300L);
        this.restAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!LoopRotarySwitchView.this.touching) {
                    LoopRotarySwitchView.this.angle = ((Float) animation.getAnimatedValue()).floatValue();
                    LoopRotarySwitchView.this.initView();
                }
            }
        });
        this.restAnimator.addListener(new Animator.AnimatorListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.7
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Log.d(LoopRotarySwitchView.TAG, "onAnimationStart: ");
                Boolean unused = LoopRotarySwitchView.isCanSwitchItem = false;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Log.d(LoopRotarySwitchView.TAG, "onAnimationEnd: ");
                Boolean unused = LoopRotarySwitchView.isCanSwitchItem = true;
                if (!LoopRotarySwitchView.this.touching) {
                    LoopRotarySwitchView loopRotarySwitchView = LoopRotarySwitchView.this;
                    loopRotarySwitchView.selectItem = loopRotarySwitchView.calculateItem();
                    Log.i(LoopRotarySwitchView.TAG, "onAnimationEnd: selectitem=" + LoopRotarySwitchView.this.selectItem);
                    if (LoopRotarySwitchView.this.selectItem < 0) {
                        LoopRotarySwitchView loopRotarySwitchView2 = LoopRotarySwitchView.this;
                        loopRotarySwitchView2.selectItem = loopRotarySwitchView2.size + LoopRotarySwitchView.this.selectItem;
                    }
                    if (LoopRotarySwitchView.this.onItemSelectedListener != null) {
                        LoopRotarySwitchView.this.onItemSelectedListener.selected(LoopRotarySwitchView.this.selectItem, (View) LoopRotarySwitchView.this.views.get(LoopRotarySwitchView.this.selectItem));
                    }
                    LoopRotarySwitchView.this.setFocusView();
                    LoopRotarySwitchView.this.isCanClickListener = true;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
            }
        });
        if (complete != null) {
            this.restAnimator.addListener(new Animator.AnimatorListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.8
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    complete.run();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
        this.restAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateItem() {
        float f = this.angle % 360.0f;
        this.angle = f;
        int i = this.size;
        return ((int) (f / (360 / i))) % i;
    }

    private boolean onTouch(MotionEvent event) {
        if (event.getAction() == 0) {
            this.last_angle = this.angle;
            this.touching = true;
        }
        boolean sc = this.mGestureDetector.onTouchEvent(event);
        if (sc) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (event.getAction() == 1 || event.getAction() == 3) {
            this.touching = false;
            restPosition();
            return true;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        OnLoopViewTouchListener onLoopViewTouchListener = this.onLoopViewTouchListener;
        if (onLoopViewTouchListener != null) {
            onLoopViewTouchListener.onTouch(event);
        }
        isCanClickListener(event);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouch(ev);
        OnLoopViewTouchListener onLoopViewTouchListener = this.onLoopViewTouchListener;
        if (onLoopViewTouchListener != null) {
            onLoopViewTouchListener.onTouch(ev);
        }
        isCanClickListener(ev);
        return super.dispatchTouchEvent(ev);
    }

    public void isCanClickListener(MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                this.f196x = event.getX();
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
                float x = event.getX();
                float f = this.f196x;
                if (x - f > this.limitX || f - event.getX() > this.limitX) {
                    this.isCanClickListener = false;
                    return;
                } else {
                    this.isCanClickListener = true;
                    return;
                }
            case 2:
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

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getR() {
        return this.f195r;
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
        if (pos < 0 || pos >= this.views.size()) {
            return;
        }
        int calculateItem = calculateItem();
        this.bottom = calculateItem;
        if (calculateItem == pos) {
            Log.e("setToPos", "bottom");
        }
        int difPos = this.bottom - pos;
        if (difPos < 0.0d - Math.floor(this.views.size() / 2)) {
            difPos += this.views.size();
        }
        if (difPos > Math.floor(this.views.size() / 2)) {
            difPos -= this.views.size();
        }
        if (this.views.size() % 2 == 0 && difPos == this.views.size() / 2) {
            difPos = (this.views.size() / 2) + 0;
        }
        float angle1 = this.angle - ((difPos * 360.0f) / this.views.size());
        Log.i(TAG, "setSelectItem: difPos=" + difPos + " angle1=" + angle1);
        AnimRotationTo(angle1, null);
    }

    public LoopRotarySwitchView setR(float r) {
        this.f195r = r;
        this.distance = this.multiple * r;
        return this;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLoopViewTouchListener(OnLoopViewTouchListener onLoopViewTouchListener) {
        this.onLoopViewTouchListener = onLoopViewTouchListener;
    }

    public LoopRotarySwitchView setAutoRotation(boolean autoRotation) {
        this.autoRotation = autoRotation;
        this.loopHandler.setLoop(autoRotation);
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
        this.distance = this.f195r * mMultiple;
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
        ValueAnimator ofInt = ValueAnimator.ofInt(from, to);
        this.xAnimation = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                LoopRotarySwitchView.this.loopRotationX = ((Integer) animation.getAnimatedValue()).intValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.xAnimation.setInterpolator(new DecelerateInterpolator());
        this.xAnimation.setDuration(2000L);
        if (start) {
            this.xAnimation.start();
        }
    }

    public ValueAnimator createZAnimation(int from, int to, boolean start) {
        ValueAnimator valueAnimator = this.zAnimation;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.zAnimation.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(from, to);
        this.zAnimation = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.wits.ksw.launcher.view.LoopRotarySwitchView.10
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                LoopRotarySwitchView.this.loopRotationZ = ((Integer) animation.getAnimatedValue()).intValue();
                LoopRotarySwitchView.this.initView();
            }
        });
        this.zAnimation.setInterpolator(new DecelerateInterpolator());
        this.zAnimation.setDuration(2000L);
        if (start) {
            this.zAnimation.start();
        }
        return this.zAnimation;
    }

    public LoopRotarySwitchView setOrientation(int mOrientation) {
        setHorizontal(mOrientation == 1, false);
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

    public LoopRotarySwitchView setLoopRotationX(int loopRotationX) {
        this.loopRotationX = loopRotationX;
        return this;
    }

    public LoopRotarySwitchView setLoopRotationZ(int loopRotationZ) {
        this.loopRotationZ = loopRotationZ;
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

    public void setzAnimation(ValueAnimator zAnimation) {
        this.zAnimation = zAnimation;
    }

    public ValueAnimator getzAnimation() {
        return this.zAnimation;
    }

    public void setxAnimation(ValueAnimator xAnimation) {
        this.xAnimation = xAnimation;
    }

    public ValueAnimator getxAnimation() {
        return this.xAnimation;
    }
}
