package android.databinding;

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
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.viewbinding.ViewBinding;
import com.android.databinding.library.R;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public abstract class ViewDataBinding extends BaseObservable implements ViewBinding {
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
    public boolean mPendingRebind;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    /* access modifiers changed from: private */
    public boolean mRebindHalted;
    /* access modifiers changed from: private */
    public final Runnable mRebindRunnable;
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

    public abstract boolean setVariable(int i, Object obj);

    static {
        if (Build.VERSION.SDK_INT < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new View.OnAttachStateChangeListener() {
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
        this.mRebindRunnable = new Runnable() {
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
        this.mPendingRebind = false;
        this.mRebindHalted = false;
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

    protected ViewDataBinding(Object bindingComponent, View root, int localFieldCount) {
        this(checkAndCastToBindingComponent(bindingComponent), root, localFieldCount);
    }

    private static DataBindingComponent checkAndCastToBindingComponent(Object bindingComponent) {
        if (bindingComponent == null) {
            return null;
        }
        if (bindingComponent instanceof DataBindingComponent) {
            return (DataBindingComponent) bindingComponent;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
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

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        LifecycleOwner lifecycleOwner2 = this.mLifecycleOwner;
        if (lifecycleOwner2 != lifecycleOwner) {
            if (lifecycleOwner2 != null) {
                lifecycleOwner2.getLifecycle().removeObserver(this.mOnStartListener);
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

    public LifecycleOwner getLifecycleOwner() {
        return this.mLifecycleOwner;
    }

    public void addOnRebindCallback(OnRebindCallback listener) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry<>(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(listener);
    }

    public void removeOnRebindCallback(OnRebindCallback listener) {
        CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry = this.mRebindCallbacks;
        if (callbackRegistry != null) {
            callbackRegistry.remove(listener);
        }
    }

    public void executePendingBindings() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding == null) {
            executeBindingsInternal();
        } else {
            viewDataBinding.executePendingBindings();
        }
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry = this.mRebindCallbacks;
            if (callbackRegistry != null) {
                callbackRegistry.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry2 = this.mRebindCallbacks;
                if (callbackRegistry2 != null) {
                    callbackRegistry2.notifyCallbacks(this, 3, null);
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
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        if (USE_CHOREOGRAPHER == false) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        r3.mChoreographer.postFrameCallback(r3.mFrameCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r3.mUIThreadHandler.post(r3.mRebindRunnable);
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
            r3 = this;
            android.databinding.ViewDataBinding r0 = r3.mContainingBinding
            if (r0 == 0) goto L_0x0008
            r0.requestRebind()
            goto L_0x003b
        L_0x0008:
            android.arch.lifecycle.LifecycleOwner r0 = r3.mLifecycleOwner
            if (r0 == 0) goto L_0x001d
            android.arch.lifecycle.Lifecycle r1 = r0.getLifecycle()
            android.arch.lifecycle.Lifecycle$State r1 = r1.getCurrentState()
            android.arch.lifecycle.Lifecycle$State r2 = android.arch.lifecycle.Lifecycle.State.STARTED
            boolean r2 = r1.isAtLeast(r2)
            if (r2 != 0) goto L_0x001d
            return
        L_0x001d:
            monitor-enter(r3)
            boolean r1 = r3.mPendingRebind     // Catch:{ all -> 0x003c }
            if (r1 == 0) goto L_0x0024
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            return
        L_0x0024:
            r1 = 1
            r3.mPendingRebind = r1     // Catch:{ all -> 0x003c }
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            boolean r1 = USE_CHOREOGRAPHER
            if (r1 == 0) goto L_0x0034
            android.view.Choreographer r1 = r3.mChoreographer
            android.view.Choreographer$FrameCallback r2 = r3.mFrameCallback
            r1.postFrameCallback(r2)
            goto L_0x003b
        L_0x0034:
            android.os.Handler r1 = r3.mUIThreadHandler
            java.lang.Runnable r2 = r3.mRebindRunnable
            r1.post(r2)
        L_0x003b:
            return
        L_0x003c:
            r1 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            throw r1
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
                LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
                if (lifecycleOwner != null) {
                    listener.setLifecycleOwner(lifecycleOwner);
                }
            }
            listener.setTarget(observable);
        }
    }

    protected static ViewDataBinding bind(Object bindingComponent, View view, int layoutId) {
        return DataBindingUtil.bind(checkAndCastToBindingComponent(bindingComponent), view, layoutId);
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

    protected static <T> T getFromList(LongSparseArray<T> list, int index) {
        if (list == null || index < 0) {
            return null;
        }
        return list.get((long) index);
    }

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

    protected static long getFromList(SparseLongArray list, int index) {
        if (list == null || index < 0) {
            return 0;
        }
        return list.get(index);
    }

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
        for (View mapBindings : roots) {
            mapBindings(bindingComponent, mapBindings, bindings, includes, viewsWithIds, true);
        }
        return bindings;
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0143  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void mapBindings(android.databinding.DataBindingComponent r23, android.view.View r24, java.lang.Object[] r25, android.databinding.ViewDataBinding.IncludedLayouts r26, android.util.SparseIntArray r27, boolean r28) {
        /*
            r6 = r23
            r7 = r24
            r8 = r26
            r9 = r27
            android.databinding.ViewDataBinding r10 = getBinding(r24)
            if (r10 == 0) goto L_0x000f
            return
        L_0x000f:
            java.lang.Object r11 = r24.getTag()
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
            java.lang.String r13 = "layout"
            r1 = -1
            if (r28 == 0) goto L_0x0052
            if (r12 == 0) goto L_0x0052
            boolean r2 = r12.startsWith(r13)
            if (r2 == 0) goto L_0x0052
            r2 = 95
            int r2 = r12.lastIndexOf(r2)
            if (r2 <= 0) goto L_0x004e
            int r3 = r2 + 1
            boolean r3 = isNumeric(r12, r3)
            if (r3 == 0) goto L_0x004e
            int r3 = r2 + 1
            int r3 = parseTagInt(r12, r3)
            r4 = r25[r3]
            if (r4 != 0) goto L_0x0047
            r25[r3] = r7
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
            r4 = -1
        L_0x004f:
            r14 = r0
            r15 = r4
            goto L_0x0075
        L_0x0052:
            if (r12 == 0) goto L_0x0072
            java.lang.String r2 = "binding_"
            boolean r2 = r12.startsWith(r2)
            if (r2 == 0) goto L_0x0072
            int r2 = BINDING_NUMBER_START
            int r2 = parseTagInt(r12, r2)
            r3 = r25[r2]
            if (r3 != 0) goto L_0x0068
            r25[r2] = r7
        L_0x0068:
            r0 = 1
            if (r8 != 0) goto L_0x006d
            r3 = r1
            goto L_0x006e
        L_0x006d:
            r3 = r2
        L_0x006e:
            r4 = r3
            r14 = r0
            r15 = r4
            goto L_0x0075
        L_0x0072:
            r4 = -1
            r14 = r0
            r15 = r4
        L_0x0075:
            if (r14 != 0) goto L_0x008c
            int r0 = r24.getId()
            if (r0 <= 0) goto L_0x008c
            if (r9 == 0) goto L_0x008c
            int r1 = r9.get(r0, r1)
            r2 = r1
            if (r1 < 0) goto L_0x008c
            r1 = r25[r2]
            if (r1 != 0) goto L_0x008c
            r25[r2] = r7
        L_0x008c:
            boolean r0 = r7 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x015a
            r5 = r7
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            int r4 = r5.getChildCount()
            r0 = 0
            r1 = 0
        L_0x0099:
            if (r1 >= r4) goto L_0x0156
            android.view.View r3 = r5.getChildAt(r1)
            r2 = 0
            if (r15 < 0) goto L_0x0128
            r16 = r2
            java.lang.Object r2 = r3.getTag()
            boolean r2 = r2 instanceof java.lang.String
            if (r2 == 0) goto L_0x0125
            java.lang.Object r2 = r3.getTag()
            java.lang.String r2 = (java.lang.String) r2
            r17 = r4
            java.lang.String r4 = "_0"
            boolean r4 = r2.endsWith(r4)
            if (r4 == 0) goto L_0x0122
            boolean r4 = r2.startsWith(r13)
            if (r4 == 0) goto L_0x011f
            r4 = 47
            int r4 = r2.indexOf(r4)
            if (r4 <= 0) goto L_0x011f
            int r4 = findIncludeIndex(r2, r0, r8, r15)
            if (r4 < 0) goto L_0x011a
            r16 = 1
            int r0 = r4 + 1
            r18 = r0
            int[][] r0 = r8.indexes
            r0 = r0[r15]
            r0 = r0[r4]
            r19 = r2
            int[][] r2 = r8.layoutIds
            r2 = r2[r15]
            r2 = r2[r4]
            r20 = r4
            int r4 = findLastMatching(r5, r1)
            if (r4 != r1) goto L_0x00f4
            android.databinding.ViewDataBinding r21 = android.databinding.DataBindingUtil.bind((android.databinding.DataBindingComponent) r6, (android.view.View) r3, (int) r2)
            r25[r0] = r21
            r7 = r1
            goto L_0x012f
        L_0x00f4:
            int r21 = r4 - r1
            r22 = r4
            int r4 = r21 + 1
            android.view.View[] r7 = new android.view.View[r4]
            r21 = 0
            r8 = r21
        L_0x0100:
            if (r8 >= r4) goto L_0x010f
            int r9 = r1 + r8
            android.view.View r9 = r5.getChildAt(r9)
            r7[r8] = r9
            int r8 = r8 + 1
            r9 = r27
            goto L_0x0100
        L_0x010f:
            android.databinding.ViewDataBinding r8 = android.databinding.DataBindingUtil.bind((android.databinding.DataBindingComponent) r6, (android.view.View[]) r7, (int) r2)
            r25[r0] = r8
            int r8 = r4 + -1
            int r1 = r1 + r8
            r7 = r1
            goto L_0x012f
        L_0x011a:
            r19 = r2
            r20 = r4
            goto L_0x012c
        L_0x011f:
            r19 = r2
            goto L_0x012c
        L_0x0122:
            r19 = r2
            goto L_0x012c
        L_0x0125:
            r17 = r4
            goto L_0x012c
        L_0x0128:
            r16 = r2
            r17 = r4
        L_0x012c:
            r18 = r0
            r7 = r1
        L_0x012f:
            if (r16 != 0) goto L_0x0143
            r8 = 0
            r0 = r23
            r1 = r3
            r2 = r25
            r9 = r3
            r3 = r26
            r4 = r27
            r19 = r5
            r5 = r8
            mapBindings(r0, r1, r2, r3, r4, r5)
            goto L_0x0146
        L_0x0143:
            r9 = r3
            r19 = r5
        L_0x0146:
            int r1 = r7 + 1
            r7 = r24
            r8 = r26
            r9 = r27
            r4 = r17
            r0 = r18
            r5 = r19
            goto L_0x0099
        L_0x0156:
            r17 = r4
            r19 = r5
        L_0x015a:
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
        int end = str.length();
        int val = 0;
        for (int i = startIndex; i < end; i++) {
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

    protected static <T extends ViewDataBinding> T inflateInternal(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent, Object bindingComponent) {
        return DataBindingUtil.inflate(inflater, layoutId, parent, attachToParent, checkAndCastToBindingComponent(bindingComponent));
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
            if (object != null) {
                this.mObservable.addListener(object);
            }
        }

        public boolean unregister() {
            boolean unregistered = false;
            T t = this.mTarget;
            if (t != null) {
                this.mObservable.removeListener(t);
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
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            if (lifecycleOwner != null) {
                target.observe(lifecycleOwner, this);
            }
        }

        public void removeListener(LiveData<?> target) {
            target.removeObserver(this);
        }

        public void onChanged(Object o) {
            ViewDataBinding binder = this.mListener.getBinder();
            if (binder != null) {
                binder.handleFieldChange(this.mListener.mLocalFieldId, this.mListener.getTarget(), 0);
            }
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

    static class OnStartListener implements LifecycleObserver {
        final WeakReference<ViewDataBinding> mBinding;

        private OnStartListener(ViewDataBinding binding) {
            this.mBinding = new WeakReference<>(binding);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding dataBinding = (ViewDataBinding) this.mBinding.get();
            if (dataBinding != null) {
                dataBinding.executePendingBindings();
            }
        }
    }
}
