package android.databinding;

import android.annotation.TargetApi;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.res.ColorStateList;
import android.databinding.CallbackRegistry;
import android.databinding.Observable;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import com.android.databinding.library.R;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public abstract class ViewDataBinding extends BaseObservable {
    private static final int BINDING_NUMBER_START = BINDING_TAG_PREFIX.length();
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final CreateWeakListener CREATE_LIST_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakListListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_LIVE_DATA_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new LiveDataListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_MAP_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakMapListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final CreateWeakListener CREATE_PROPERTY_LISTENER = new CreateWeakListener() {
        public WeakListener create(ViewDataBinding viewDataBinding, int localFieldId) {
            return new WeakPropertyListener(viewDataBinding, localFieldId).getListener();
        }
    };
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER = new CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void>() {
        public void onNotifyCallback(OnRebindCallback callback, ViewDataBinding sender, int mode, Void arg2) {
            switch (mode) {
                case 1:
                    if (!callback.onPreBind(sender)) {
                        boolean unused = sender.mRebindHalted = true;
                        return;
                    }
                    return;
                case 2:
                    callback.onCanceled(sender);
                    return;
                case 3:
                    callback.onBound(sender);
                    return;
                default:
                    return;
            }
        }
    };
    private static final int REBOUND = 3;
    /* access modifiers changed from: private */
    public static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    static int SDK_INT = Build.VERSION.SDK_INT;
    private static final boolean USE_CHOREOGRAPHER = (SDK_INT >= 16);
    /* access modifiers changed from: private */
    public static final ReferenceQueue<ViewDataBinding> sReferenceQueue = new ReferenceQueue<>();
    protected final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final Choreographer.FrameCallback mFrameCallback;
    private boolean mInLiveDataRegisterObserver;
    private boolean mIsExecutingPendingBindings;
    private LifecycleOwner mLifecycleOwner;
    private WeakListener[] mLocalFieldObservers;
    private OnStartListener mOnStartListener;
    /* access modifiers changed from: private */
    public boolean mPendingRebind = false;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    /* access modifiers changed from: private */
    public boolean mRebindHalted = false;
    /* access modifiers changed from: private */
    public final Runnable mRebindRunnable = new Runnable() {
        public void run() {
            synchronized (this) {
                boolean unused = ViewDataBinding.this.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (Build.VERSION.SDK_INT < 19 || ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.executePendingBindings();
                return;
            }
            ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
            ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
        }
    };
    /* access modifiers changed from: private */
    public final View mRoot;
    private Handler mUIThreadHandler;

    private interface CreateWeakListener {
        WeakListener create(ViewDataBinding viewDataBinding, int i);
    }

    private interface ObservableReference<T> {
        void addListener(T t);

        WeakListener<T> getListener();

        void removeListener(T t);

        void setLifecycleOwner(LifecycleOwner lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public abstract void executeBindings();

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    /* access modifiers changed from: protected */
    public abstract boolean onFieldChange(int i, Object obj, int i2);

    public abstract boolean setVariable(int i, @Nullable Object obj);

    static {
        if (Build.VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new View.OnAttachStateChangeListener() {
                @TargetApi(19)
                public void onViewAttachedToWindow(View v) {
                    ViewDataBinding.getBinding(v).mRebindRunnable.run();
                    v.removeOnAttachStateChangeListener(this);
                }

                public void onViewDetachedFromWindow(View v) {
                }
            };
        }
    }

    protected ViewDataBinding(DataBindingComponent bindingComponent, View root, int localFieldCount) {
        this.mBindingComponent = bindingComponent;
        this.mLocalFieldObservers = new WeakListener[localFieldCount];
        this.mRoot = root;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (USE_CHOREOGRAPHER) {
            this.mChoreographer = Choreographer.getInstance();
            this.mFrameCallback = new Choreographer.FrameCallback() {
                public void doFrame(long frameTimeNanos) {
                    ViewDataBinding.this.mRebindRunnable.run();
                }
            };
        } else {
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
        }
    }

    /* access modifiers changed from: protected */
    public void setRootTag(View view) {
        view.setTag(R.id.dataBinding, this);
    }

    /* access modifiers changed from: protected */
    public void setRootTag(View[] views) {
        for (View view : views) {
            view.setTag(R.id.dataBinding, this);
        }
    }

    public static int getBuildSdkInt() {
        return SDK_INT;
    }

    @MainThread
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        if (this.mLifecycleOwner != lifecycleOwner) {
            if (this.mLifecycleOwner != null) {
                this.mLifecycleOwner.getLifecycle().removeObserver(this.mOnStartListener);
            }
            this.mLifecycleOwner = lifecycleOwner;
            if (lifecycleOwner != null) {
                if (this.mOnStartListener == null) {
                    this.mOnStartListener = new OnStartListener();
                }
                lifecycleOwner.getLifecycle().addObserver(this.mOnStartListener);
            }
            for (WeakListener<?> weakListener : this.mLocalFieldObservers) {
                if (weakListener != null) {
                    weakListener.setLifecycleOwner(lifecycleOwner);
                }
            }
        }
    }

    public void addOnRebindCallback(@NonNull OnRebindCallback listener) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry<>(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(listener);
    }

    public void removeOnRebindCallback(@NonNull OnRebindCallback listener) {
        if (this.mRebindCallbacks != null) {
            this.mRebindCallbacks.remove(listener);
        }
    }

    public void executePendingBindings() {
        if (this.mContainingBinding == null) {
            executeBindingsInternal();
        } else {
            this.mContainingBinding.executePendingBindings();
        }
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            if (this.mRebindCallbacks != null) {
                this.mRebindCallbacks.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                if (this.mRebindCallbacks != null) {
                    this.mRebindCallbacks.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    protected static void executeBindingsOn(ViewDataBinding other) {
        other.executeBindingsInternal();
    }

    /* access modifiers changed from: package-private */
    public void forceExecuteBindings() {
        executeBindings();
    }

    public void unbind() {
        for (WeakListener weakListener : this.mLocalFieldObservers) {
            if (weakListener != null) {
                weakListener.unregister();
            }
        }
    }

    static ViewDataBinding getBinding(View v) {
        if (v != null) {
            return (ViewDataBinding) v.getTag(R.id.dataBinding);
        }
        return null;
    }

    @NonNull
    public View getRoot() {
        return this.mRoot;
    }

    /* access modifiers changed from: private */
    public void handleFieldChange(int mLocalFieldId, Object object, int fieldId) {
        if (!this.mInLiveDataRegisterObserver && onFieldChange(mLocalFieldId, object, fieldId)) {
            requestRebind();
        }
    }

    /* access modifiers changed from: protected */
    public boolean unregisterFrom(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener != null) {
            return listener.unregister();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        if (r2.mLifecycleOwner == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r2.mLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(android.arch.lifecycle.Lifecycle.State.STARTED) != false) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (USE_CHOREOGRAPHER == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        r2.mChoreographer.postFrameCallback(r2.mFrameCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        r2.mUIThreadHandler.post(r2.mRebindRunnable);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestRebind() {
        /*
            r2 = this;
            android.databinding.ViewDataBinding r0 = r2.mContainingBinding
            if (r0 == 0) goto L_0x000a
            android.databinding.ViewDataBinding r0 = r2.mContainingBinding
            r0.requestRebind()
            goto L_0x003f
        L_0x000a:
            monitor-enter(r2)
            boolean r0 = r2.mPendingRebind     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0011
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            return
        L_0x0011:
            r0 = 1
            r2.mPendingRebind = r0     // Catch:{ all -> 0x0040 }
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            android.arch.lifecycle.LifecycleOwner r0 = r2.mLifecycleOwner
            if (r0 == 0) goto L_0x002c
            android.arch.lifecycle.LifecycleOwner r0 = r2.mLifecycleOwner
            android.arch.lifecycle.Lifecycle r0 = r0.getLifecycle()
            android.arch.lifecycle.Lifecycle$State r0 = r0.getCurrentState()
            android.arch.lifecycle.Lifecycle$State r1 = android.arch.lifecycle.Lifecycle.State.STARTED
            boolean r1 = r0.isAtLeast(r1)
            if (r1 != 0) goto L_0x002c
            return
        L_0x002c:
            boolean r0 = USE_CHOREOGRAPHER
            if (r0 == 0) goto L_0x0038
            android.view.Choreographer r0 = r2.mChoreographer
            android.view.Choreographer$FrameCallback r1 = r2.mFrameCallback
            r0.postFrameCallback(r1)
            goto L_0x003f
        L_0x0038:
            android.os.Handler r0 = r2.mUIThreadHandler
            java.lang.Runnable r1 = r2.mRebindRunnable
            r0.post(r1)
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0040 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.requestRebind():void");
    }

    /* access modifiers changed from: protected */
    public Object getObservedField(int localFieldId) {
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            return null;
        }
        return listener.getTarget();
    }

    private boolean updateRegistration(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable == null) {
            return unregisterFrom(localFieldId);
        }
        WeakListener listener = this.mLocalFieldObservers[localFieldId];
        if (listener == null) {
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        } else if (listener.getTarget() == observable) {
            return false;
        } else {
            unregisterFrom(localFieldId);
            registerTo(localFieldId, observable, listenerCreator);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public boolean updateRegistration(int localFieldId, Observable observable) {
        return updateRegistration(localFieldId, observable, CREATE_PROPERTY_LISTENER);
    }

    /* access modifiers changed from: protected */
    public boolean updateRegistration(int localFieldId, ObservableList observable) {
        return updateRegistration(localFieldId, observable, CREATE_LIST_LISTENER);
    }

    /* access modifiers changed from: protected */
    public boolean updateRegistration(int localFieldId, ObservableMap observable) {
        return updateRegistration(localFieldId, observable, CREATE_MAP_LISTENER);
    }

    /* access modifiers changed from: protected */
    public boolean updateLiveDataRegistration(int localFieldId, LiveData<?> observable) {
        this.mInLiveDataRegisterObserver = true;
        try {
            return updateRegistration(localFieldId, observable, CREATE_LIVE_DATA_LISTENER);
        } finally {
            this.mInLiveDataRegisterObserver = false;
        }
    }

    /* access modifiers changed from: protected */
    public void ensureBindingComponentIsNotNull(Class<?> oneExample) {
        if (this.mBindingComponent == null) {
            throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + oneExample.getCanonicalName() + " is not static and requires an object to use, retrieved from the DataBindingComponent. If you don't use an inflation method taking a DataBindingComponent, use DataBindingUtil.setDefaultComponent or make all BindingAdapter methods static.");
        }
    }

    /* access modifiers changed from: protected */
    public void registerTo(int localFieldId, Object observable, CreateWeakListener listenerCreator) {
        if (observable != null) {
            WeakListener listener = this.mLocalFieldObservers[localFieldId];
            if (listener == null) {
                listener = listenerCreator.create(this, localFieldId);
                this.mLocalFieldObservers[localFieldId] = listener;
                if (this.mLifecycleOwner != null) {
                    listener.setLifecycleOwner(this.mLifecycleOwner);
                }
            }
            listener.setTarget(observable);
        }
    }

    protected static ViewDataBinding bind(DataBindingComponent bindingComponent, View view, int layoutId) {
        return DataBindingUtil.bind(bindingComponent, view, layoutId);
    }

    protected static Object[] mapBindings(DataBindingComponent bindingComponent, View root, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        mapBindings(bindingComponent, root, bindings, includes, viewsWithIds, true);
        return bindings;
    }

    protected static boolean parse(String str, boolean fallback) {
        if (str == null) {
            return fallback;
        }
        return Boolean.parseBoolean(str);
    }

    protected static byte parse(String str, byte fallback) {
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    protected static short parse(String str, short fallback) {
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    protected static int parse(String str, int fallback) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    protected static long parse(String str, long fallback) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    protected static float parse(String str, float fallback) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    protected static double parse(String str, double fallback) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    protected static char parse(String str, char fallback) {
        if (str == null || str.isEmpty()) {
            return fallback;
        }
        return str.charAt(0);
    }

    protected static int getColorFromResource(View view, int resourceId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(resourceId);
        }
        return view.getResources().getColor(resourceId);
    }

    protected static ColorStateList getColorStateListFromResource(View view, int resourceId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getContext().getColorStateList(resourceId);
        }
        return view.getResources().getColorStateList(resourceId);
    }

    protected static Drawable getDrawableFromResource(View view, int resourceId) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getContext().getDrawable(resourceId);
        }
        return view.getResources().getDrawable(resourceId);
    }

    protected static <T> T getFromArray(T[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return null;
        }
        return arr[index];
    }

    protected static <T> void setTo(T[] arr, int index, T value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static boolean getFromArray(boolean[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return false;
        }
        return arr[index];
    }

    protected static void setTo(boolean[] arr, int index, boolean value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static byte getFromArray(byte[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(byte[] arr, int index, byte value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static short getFromArray(short[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(short[] arr, int index, short value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static char getFromArray(char[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(char[] arr, int index, char value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static int getFromArray(int[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(int[] arr, int index, int value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static long getFromArray(long[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0;
        }
        return arr[index];
    }

    protected static void setTo(long[] arr, int index, long value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static float getFromArray(float[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0.0f;
        }
        return arr[index];
    }

    protected static void setTo(float[] arr, int index, float value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static double getFromArray(double[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return 0.0d;
        }
        return arr[index];
    }

    protected static void setTo(double[] arr, int index, double value) {
        if (arr != null && index >= 0 && index < arr.length) {
            arr[index] = value;
        }
    }

    protected static <T> T getFromList(List<T> list, int index) {
        if (list == null || index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    protected static <T> void setTo(List<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.set(index, value);
        }
    }

    protected static <T> T getFromList(SparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get(index);
    }

    protected static <T> void setTo(SparseArray<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    @TargetApi(16)
    protected static <T> T getFromList(LongSparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get((long) index);
    }

    @TargetApi(16)
    protected static <T> void setTo(LongSparseArray<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put((long) index, value);
        }
    }

    protected static <T> T getFromList(android.support.v4.util.LongSparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get((long) index);
    }

    protected static <T> void setTo(android.support.v4.util.LongSparseArray<T> list, int index, T value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put((long) index, value);
        }
    }

    protected static boolean getFromList(SparseBooleanArray list, int index) {
        if (list == null || index < 0) {
            return false;
        }
        return list.get(index);
    }

    protected static void setTo(SparseBooleanArray list, int index, boolean value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    protected static int getFromList(SparseIntArray list, int index) {
        if (list == null || index < 0) {
            return 0;
        }
        return list.get(index);
    }

    protected static void setTo(SparseIntArray list, int index, int value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    @TargetApi(18)
    protected static long getFromList(SparseLongArray list, int index) {
        if (list == null || index < 0) {
            return 0;
        }
        return list.get(index);
    }

    @TargetApi(18)
    protected static void setTo(SparseLongArray list, int index, long value) {
        if (list != null && index >= 0 && index < list.size()) {
            list.put(index, value);
        }
    }

    protected static <K, T> T getFrom(Map<K, T> map, K key) {
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    protected static <K, T> void setTo(Map<K, T> map, K key, T value) {
        if (map != null) {
            map.put(key, value);
        }
    }

    protected static void setBindingInverseListener(ViewDataBinding binder, InverseBindingListener oldListener, PropertyChangedInverseListener listener) {
        if (oldListener != listener) {
            if (oldListener != null) {
                binder.removeOnPropertyChangedCallback((PropertyChangedInverseListener) oldListener);
            }
            if (listener != null) {
                binder.addOnPropertyChangedCallback(listener);
            }
        }
    }

    protected static int safeUnbox(Integer boxed) {
        if (boxed == null) {
            return 0;
        }
        return boxed.intValue();
    }

    protected static long safeUnbox(Long boxed) {
        if (boxed == null) {
            return 0;
        }
        return boxed.longValue();
    }

    protected static short safeUnbox(Short boxed) {
        if (boxed == null) {
            return 0;
        }
        return boxed.shortValue();
    }

    protected static byte safeUnbox(Byte boxed) {
        if (boxed == null) {
            return 0;
        }
        return boxed.byteValue();
    }

    protected static char safeUnbox(Character boxed) {
        if (boxed == null) {
            return 0;
        }
        return boxed.charValue();
    }

    protected static double safeUnbox(Double boxed) {
        if (boxed == null) {
            return 0.0d;
        }
        return boxed.doubleValue();
    }

    protected static float safeUnbox(Float boxed) {
        if (boxed == null) {
            return 0.0f;
        }
        return boxed.floatValue();
    }

    protected static boolean safeUnbox(Boolean boxed) {
        if (boxed == null) {
            return false;
        }
        return boxed.booleanValue();
    }

    /* access modifiers changed from: protected */
    public void setContainedBinding(ViewDataBinding included) {
        if (included != null) {
            included.mContainingBinding = this;
        }
    }

    protected static Object[] mapBindings(DataBindingComponent bindingComponent, View[] roots, int numBindings, IncludedLayouts includes, SparseIntArray viewsWithIds) {
        Object[] bindings = new Object[numBindings];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= roots.length) {
                return bindings;
            }
            mapBindings(bindingComponent, roots[i2], bindings, includes, viewsWithIds, true);
            i = i2 + 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0140  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void mapBindings(android.databinding.DataBindingComponent r24, android.view.View r25, java.lang.Object[] r26, android.databinding.ViewDataBinding.IncludedLayouts r27, android.util.SparseIntArray r28, boolean r29) {
        /*
            r6 = r24
            r7 = r25
            r8 = r27
            r9 = r28
            android.databinding.ViewDataBinding r10 = getBinding(r25)
            if (r10 == 0) goto L_0x000f
            return
        L_0x000f:
            java.lang.Object r11 = r25.getTag()
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L_0x001b
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            r12 = r0
            r0 = 0
            r1 = -1
            if (r29 == 0) goto L_0x0053
            if (r12 == 0) goto L_0x0053
            java.lang.String r2 = "layout"
            boolean r2 = r12.startsWith(r2)
            if (r2 == 0) goto L_0x0053
            r2 = 95
            int r2 = r12.lastIndexOf(r2)
            if (r2 <= 0) goto L_0x004e
            int r3 = r2 + 1
            boolean r3 = isNumeric(r12, r3)
            if (r3 == 0) goto L_0x004e
            int r3 = r2 + 1
            int r3 = parseTagInt(r12, r3)
            r4 = r26[r3]
            if (r4 != 0) goto L_0x0047
            r26[r3] = r7
        L_0x0047:
            if (r8 != 0) goto L_0x004b
            r4 = r1
            goto L_0x004c
        L_0x004b:
            r4 = r3
        L_0x004c:
            r0 = 1
            goto L_0x004f
        L_0x004e:
            r4 = r1
        L_0x004f:
            r2 = r4
        L_0x0051:
            r13 = r0
            goto L_0x0073
        L_0x0053:
            if (r12 == 0) goto L_0x0071
            java.lang.String r2 = "binding_"
            boolean r2 = r12.startsWith(r2)
            if (r2 == 0) goto L_0x0071
            int r2 = BINDING_NUMBER_START
            int r2 = parseTagInt(r12, r2)
            r3 = r26[r2]
            if (r3 != 0) goto L_0x0069
            r26[r2] = r7
        L_0x0069:
            r0 = 1
            if (r8 != 0) goto L_0x006e
            r3 = r1
            goto L_0x006f
        L_0x006e:
            r3 = r2
        L_0x006f:
            r2 = r3
            goto L_0x0051
        L_0x0071:
            r13 = r0
            r2 = r1
        L_0x0073:
            r5 = r2
            if (r13 != 0) goto L_0x008b
            int r0 = r25.getId()
            if (r0 <= 0) goto L_0x008b
            if (r9 == 0) goto L_0x008b
            int r1 = r9.get(r0, r1)
            r2 = r1
            if (r1 < 0) goto L_0x008b
            r1 = r26[r2]
            if (r1 != 0) goto L_0x008b
            r26[r2] = r7
        L_0x008b:
            boolean r0 = r7 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x0157
            r4 = r7
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            int r3 = r4.getChildCount()
            r0 = 0
            r14 = 0
            r1 = r0
            r0 = r14
        L_0x009a:
            if (r0 >= r3) goto L_0x0157
            android.view.View r2 = r4.getChildAt(r0)
            r15 = 0
            if (r5 < 0) goto L_0x0122
            r16 = r3
            java.lang.Object r3 = r2.getTag()
            boolean r3 = r3 instanceof java.lang.String
            if (r3 == 0) goto L_0x011f
            java.lang.Object r3 = r2.getTag()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r7 = "_0"
            boolean r7 = r3.endsWith(r7)
            if (r7 == 0) goto L_0x011f
            java.lang.String r7 = "layout"
            boolean r7 = r3.startsWith(r7)
            if (r7 == 0) goto L_0x011f
            r7 = 47
            int r7 = r3.indexOf(r7)
            if (r7 <= 0) goto L_0x011f
            int r7 = findIncludeIndex(r3, r1, r8, r5)
            if (r7 < 0) goto L_0x011f
            r15 = 1
            int r1 = r7 + 1
            r17 = r1
            int[][] r1 = r8.indexes
            r1 = r1[r5]
            r1 = r1[r7]
            r18 = r3
            int[][] r3 = r8.layoutIds
            r3 = r3[r5]
            r3 = r3[r7]
            r19 = r5
            int r5 = findLastMatching(r4, r0)
            if (r5 != r0) goto L_0x00f4
            android.databinding.ViewDataBinding r20 = android.databinding.DataBindingUtil.bind((android.databinding.DataBindingComponent) r6, (android.view.View) r2, (int) r3)
            r26[r1] = r20
        L_0x00f2:
            r7 = r0
            goto L_0x0129
        L_0x00f4:
            int r20 = r5 - r0
            r21 = r5
            int r5 = r20 + 1
            r22 = r7
            android.view.View[] r7 = new android.view.View[r5]
            r20 = r14
        L_0x0100:
            r23 = r20
            r8 = r23
            if (r8 >= r5) goto L_0x0115
            int r9 = r0 + r8
            android.view.View r9 = r4.getChildAt(r9)
            r7[r8] = r9
            int r20 = r8 + 1
            r8 = r27
            r9 = r28
            goto L_0x0100
        L_0x0115:
            android.databinding.ViewDataBinding r8 = android.databinding.DataBindingUtil.bind((android.databinding.DataBindingComponent) r6, (android.view.View[]) r7, (int) r3)
            r26[r1] = r8
            int r8 = r5 + -1
            int r0 = r0 + r8
            goto L_0x00f2
        L_0x011f:
            r19 = r5
            goto L_0x0126
        L_0x0122:
            r16 = r3
            r19 = r5
        L_0x0126:
            r7 = r0
            r17 = r1
        L_0x0129:
            if (r15 != 0) goto L_0x0140
            r5 = 0
            r0 = r24
            r1 = r2
            r8 = r2
            r2 = r26
            r9 = r16
            r3 = r27
            r16 = r4
            r4 = r28
            r18 = r19
            mapBindings(r0, r1, r2, r3, r4, r5)
            goto L_0x0146
        L_0x0140:
            r9 = r16
            r18 = r19
            r16 = r4
        L_0x0146:
            int r0 = r7 + 1
            r3 = r9
            r4 = r16
            r1 = r17
            r5 = r18
            r7 = r25
            r8 = r27
            r9 = r28
            goto L_0x009a
        L_0x0157:
            r18 = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ViewDataBinding.mapBindings(android.databinding.DataBindingComponent, android.view.View, java.lang.Object[], android.databinding.ViewDataBinding$IncludedLayouts, android.util.SparseIntArray, boolean):void");
    }

    private static int findIncludeIndex(String tag, int minInclude, IncludedLayouts included, int includedIndex) {
        CharSequence layoutName = tag.subSequence(tag.indexOf(47) + 1, tag.length() - 2);
        String[] layouts = included.layouts[includedIndex];
        int length = layouts.length;
        for (int i = minInclude; i < length; i++) {
            if (TextUtils.equals(layoutName, layouts[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int firstIncludedIndex) {
        String firstViewTag = (String) viewGroup.getChildAt(firstIncludedIndex).getTag();
        String tagBase = firstViewTag.substring(0, firstViewTag.length() - 1);
        int tagSequenceIndex = tagBase.length();
        int count = viewGroup.getChildCount();
        int max = firstIncludedIndex;
        for (int i = firstIncludedIndex + 1; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            String tag = view.getTag() instanceof String ? (String) view.getTag() : null;
            if (tag != null && tag.startsWith(tagBase)) {
                if (tag.length() == firstViewTag.length() && tag.charAt(tag.length() - 1) == '0') {
                    return max;
                }
                if (isNumeric(tag, tagSequenceIndex)) {
                    max = i;
                }
            }
        }
        return max;
    }

    private static boolean isNumeric(String tag, int startIndex) {
        int length = tag.length();
        if (length == startIndex) {
            return false;
        }
        for (int i = startIndex; i < length; i++) {
            if (!Character.isDigit(tag.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int parseTagInt(String str, int startIndex) {
        int val = 0;
        for (int i = startIndex; i < str.length(); i++) {
            val = (val * 10) + (str.charAt(i) - '0');
        }
        return val;
    }

    /* access modifiers changed from: private */
    public static void processReferenceQueue() {
        while (true) {
            Reference<? extends ViewDataBinding> poll = sReferenceQueue.poll();
            Reference<? extends ViewDataBinding> ref = poll;
            if (poll == null) {
                return;
            }
            if (ref instanceof WeakListener) {
                ((WeakListener) ref).unregister();
            }
        }
    }

    private static class WeakListener<T> extends WeakReference<ViewDataBinding> {
        protected final int mLocalFieldId;
        private final ObservableReference<T> mObservable;
        private T mTarget;

        public WeakListener(ViewDataBinding binder, int localFieldId, ObservableReference<T> observable) {
            super(binder, ViewDataBinding.sReferenceQueue);
            this.mLocalFieldId = localFieldId;
            this.mObservable = observable;
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
            this.mObservable.setLifecycleOwner(lifecycleOwner);
        }

        public void setTarget(T object) {
            unregister();
            this.mTarget = object;
            if (this.mTarget != null) {
                this.mObservable.addListener(this.mTarget);
            }
        }

        public boolean unregister() {
            boolean unregistered = false;
            if (this.mTarget != null) {
                this.mObservable.removeListener(this.mTarget);
                unregistered = true;
            }
            this.mTarget = null;
            return unregistered;
        }

        public T getTarget() {
            return this.mTarget;
        }

        /* access modifiers changed from: protected */
        public ViewDataBinding getBinder() {
            ViewDataBinding binder = (ViewDataBinding) get();
            if (binder == null) {
                unregister();
            }
            return binder;
        }
    }

    private static class WeakPropertyListener extends Observable.OnPropertyChangedCallback implements ObservableReference<Observable> {
        final WeakListener<Observable> mListener;

        public WeakPropertyListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener<>(binder, localFieldId, this);
        }

        public WeakListener<Observable> getListener() {
            return this.mListener;
        }

        public void addListener(Observable target) {
            target.addOnPropertyChangedCallback(this);
        }

        public void removeListener(Observable target) {
            target.removeOnPropertyChangedCallback(this);
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }

        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && this.mListener.getTarget() == sender) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, propertyId);
            }
        }
    }

    private static class WeakListListener extends ObservableList.OnListChangedCallback implements ObservableReference<ObservableList> {
        final WeakListener<ObservableList> mListener;

        public WeakListListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener<>(binder, localFieldId, this);
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }

        public WeakListener<ObservableList> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableList target) {
            target.addOnListChangedCallback(this);
        }

        public void removeListener(ObservableList target) {
            target.removeOnListChangedCallback(this);
        }

        public void onChanged(ObservableList sender) {
            ObservableList target;
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && (target = this.mListener.getTarget()) == sender) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, target, 0);
            }
        }

        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            onChanged(sender);
        }

        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }
    }

    private static class WeakMapListener extends ObservableMap.OnMapChangedCallback implements ObservableReference<ObservableMap> {
        final WeakListener<ObservableMap> mListener;

        public WeakMapListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener<>(binder, localFieldId, this);
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }

        public WeakListener<ObservableMap> getListener() {
            return this.mListener;
        }

        public void addListener(ObservableMap target) {
            target.addOnMapChangedCallback(this);
        }

        public void removeListener(ObservableMap target) {
            target.removeOnMapChangedCallback(this);
        }

        public void onMapChanged(ObservableMap sender, Object key) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null && sender == this.mListener.getTarget()) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, sender, 0);
            }
        }
    }

    private static class LiveDataListener implements Observer, ObservableReference<LiveData<?>> {
        LifecycleOwner mLifecycleOwner;
        final WeakListener<LiveData<?>> mListener;

        public LiveDataListener(ViewDataBinding binder, int localFieldId) {
            this.mListener = new WeakListener<>(binder, localFieldId, this);
        }

        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
            LifecycleOwner owner = lifecycleOwner;
            LiveData<?> liveData = this.mListener.getTarget();
            if (liveData != null) {
                if (this.mLifecycleOwner != null) {
                    liveData.removeObserver(this);
                }
                if (lifecycleOwner != null) {
                    liveData.observe(owner, this);
                }
            }
            this.mLifecycleOwner = owner;
        }

        public WeakListener<LiveData<?>> getListener() {
            return this.mListener;
        }

        public void addListener(LiveData<?> target) {
            if (this.mLifecycleOwner != null) {
                target.observe(this.mLifecycleOwner, this);
            }
        }

        public void removeListener(LiveData<?> target) {
            target.removeObserver(this);
        }

        public void onChanged(@Nullable Object o) {
            this.mListener.getBinder().handleFieldChange(this.mListener.mLocalFieldId, this.mListener.getTarget(), 0);
        }
    }

    protected static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int bindingCount) {
            this.layouts = new String[bindingCount][];
            this.indexes = new int[bindingCount][];
            this.layoutIds = new int[bindingCount][];
        }

        public void setIncludes(int index, String[] layouts2, int[] indexes2, int[] layoutIds2) {
            this.layouts[index] = layouts2;
            this.indexes[index] = indexes2;
            this.layoutIds[index] = layoutIds2;
        }
    }

    protected static abstract class PropertyChangedInverseListener extends Observable.OnPropertyChangedCallback implements InverseBindingListener {
        final int mPropertyId;

        public PropertyChangedInverseListener(int propertyId) {
            this.mPropertyId = propertyId;
        }

        public void onPropertyChanged(Observable sender, int propertyId) {
            if (propertyId == this.mPropertyId || propertyId == 0) {
                onChange();
            }
        }
    }

    public class OnStartListener implements LifecycleObserver {
        private OnStartListener() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding.this.executePendingBindings();
        }
    }
}
