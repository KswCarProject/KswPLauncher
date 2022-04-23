package android.databinding;

import java.util.ArrayList;
import java.util.List;

public class CallbackRegistry<C, T, A> implements Cloneable {
    private static final String TAG = "CallbackRegistry";
    private List<C> mCallbacks = new ArrayList();
    private long mFirst64Removed = 0;
    private int mNotificationLevel;
    private final NotifierCallback<C, T, A> mNotifier;
    private long[] mRemainderRemoved;

    public static abstract class NotifierCallback<C, T, A> {
        public abstract void onNotifyCallback(C c, T t, int i, A a);
    }

    public CallbackRegistry(NotifierCallback<C, T, A> notifier) {
        this.mNotifier = notifier;
    }

    public synchronized void notifyCallbacks(T sender, int arg, A arg2) {
        this.mNotificationLevel++;
        notifyRecurse(sender, arg, arg2);
        int i = this.mNotificationLevel - 1;
        this.mNotificationLevel = i;
        if (i == 0) {
            long[] jArr = this.mRemainderRemoved;
            if (jArr != null) {
                for (int i2 = jArr.length - 1; i2 >= 0; i2--) {
                    long removedBits = this.mRemainderRemoved[i2];
                    if (removedBits != 0) {
                        removeRemovedCallbacks((i2 + 1) * 64, removedBits);
                        this.mRemainderRemoved[i2] = 0;
                    }
                }
            }
            long j = this.mFirst64Removed;
            if (j != 0) {
                removeRemovedCallbacks(0, j);
                this.mFirst64Removed = 0;
            }
        }
    }

    private void notifyFirst64(T sender, int arg, A arg2) {
        notifyCallbacks(sender, arg, arg2, 0, Math.min(64, this.mCallbacks.size()), this.mFirst64Removed);
    }

    private void notifyRecurse(T sender, int arg, A arg2) {
        int callbackCount = this.mCallbacks.size();
        long[] jArr = this.mRemainderRemoved;
        int remainderIndex = jArr == null ? -1 : jArr.length - 1;
        notifyRemainder(sender, arg, arg2, remainderIndex);
        notifyCallbacks(sender, arg, arg2, (remainderIndex + 2) * 64, callbackCount, 0);
    }

    private void notifyRemainder(T sender, int arg, A arg2, int remainderIndex) {
        if (remainderIndex < 0) {
            notifyFirst64(sender, arg, arg2);
            T t = sender;
            int i = arg;
            A a = arg2;
            return;
        }
        long bits = this.mRemainderRemoved[remainderIndex];
        int startIndex = (remainderIndex + 1) * 64;
        int endIndex = Math.min(this.mCallbacks.size(), startIndex + 64);
        notifyRemainder(sender, arg, arg2, remainderIndex - 1);
        notifyCallbacks(sender, arg, arg2, startIndex, endIndex, bits);
    }

    private void notifyCallbacks(T sender, int arg, A arg2, int startIndex, int endIndex, long bits) {
        long bitMask = 1;
        for (int i = startIndex; i < endIndex; i++) {
            if ((bits & bitMask) == 0) {
                this.mNotifier.onNotifyCallback(this.mCallbacks.get(i), sender, arg, arg2);
            }
            bitMask <<= 1;
        }
    }

    public synchronized void add(C callback) {
        if (callback != null) {
            int index = this.mCallbacks.lastIndexOf(callback);
            if (index < 0 || isRemoved(index)) {
                this.mCallbacks.add(callback);
            }
        } else {
            throw new IllegalArgumentException("callback cannot be null");
        }
    }

    private boolean isRemoved(int index) {
        int maskIndex;
        if (index < 64) {
            if ((this.mFirst64Removed & (1 << index)) != 0) {
                return true;
            }
            return false;
        }
        long[] jArr = this.mRemainderRemoved;
        if (jArr == null || (maskIndex = (index / 64) - 1) >= jArr.length) {
            return false;
        }
        if ((jArr[maskIndex] & (1 << (index % 64))) != 0) {
            return true;
        }
        return false;
    }

    private void removeRemovedCallbacks(int startIndex, long removed) {
        long bitMask = Long.MIN_VALUE;
        for (int i = (startIndex + 64) - 1; i >= startIndex; i--) {
            if ((removed & bitMask) != 0) {
                this.mCallbacks.remove(i);
            }
            bitMask >>>= 1;
        }
    }

    public synchronized void remove(C callback) {
        if (this.mNotificationLevel == 0) {
            this.mCallbacks.remove(callback);
        } else {
            int index = this.mCallbacks.lastIndexOf(callback);
            if (index >= 0) {
                setRemovalBit(index);
            }
        }
    }

    private void setRemovalBit(int index) {
        if (index < 64) {
            this.mFirst64Removed |= 1 << index;
            return;
        }
        int remainderIndex = (index / 64) - 1;
        long[] jArr = this.mRemainderRemoved;
        if (jArr == null) {
            this.mRemainderRemoved = new long[(this.mCallbacks.size() / 64)];
        } else if (jArr.length <= remainderIndex) {
            long[] newRemainders = new long[(this.mCallbacks.size() / 64)];
            long[] jArr2 = this.mRemainderRemoved;
            System.arraycopy(jArr2, 0, newRemainders, 0, jArr2.length);
            this.mRemainderRemoved = newRemainders;
        }
        long[] jArr3 = this.mRemainderRemoved;
        jArr3[remainderIndex] = jArr3[remainderIndex] | (1 << (index % 64));
    }

    public synchronized ArrayList<C> copyCallbacks() {
        ArrayList<C> callbacks;
        callbacks = new ArrayList<>(this.mCallbacks.size());
        int numListeners = this.mCallbacks.size();
        for (int i = 0; i < numListeners; i++) {
            if (!isRemoved(i)) {
                callbacks.add(this.mCallbacks.get(i));
            }
        }
        return callbacks;
    }

    public synchronized void copyCallbacks(List<C> callbacks) {
        callbacks.clear();
        int numListeners = this.mCallbacks.size();
        for (int i = 0; i < numListeners; i++) {
            if (!isRemoved(i)) {
                callbacks.add(this.mCallbacks.get(i));
            }
        }
    }

    public synchronized boolean isEmpty() {
        if (this.mCallbacks.isEmpty()) {
            return true;
        }
        if (this.mNotificationLevel == 0) {
            return false;
        }
        int numListeners = this.mCallbacks.size();
        for (int i = 0; i < numListeners; i++) {
            if (!isRemoved(i)) {
                return false;
            }
        }
        return true;
    }

    public synchronized void clear() {
        if (this.mNotificationLevel == 0) {
            this.mCallbacks.clear();
        } else if (!this.mCallbacks.isEmpty()) {
            for (int i = this.mCallbacks.size() - 1; i >= 0; i--) {
                setRemovalBit(i);
            }
        }
    }

    public synchronized CallbackRegistry<C, T, A> clone() {
        CallbackRegistry<C, T, A> clone;
        clone = null;
        try {
            clone = (CallbackRegistry) super.clone();
            clone.mFirst64Removed = 0;
            clone.mRemainderRemoved = null;
            clone.mNotificationLevel = 0;
            clone.mCallbacks = new ArrayList();
            int numListeners = this.mCallbacks.size();
            for (int i = 0; i < numListeners; i++) {
                if (!isRemoved(i)) {
                    clone.mCallbacks.add(this.mCallbacks.get(i));
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
