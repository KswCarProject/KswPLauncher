package android.databinding;

import android.databinding.ObservableList;
import java.util.ArrayList;
import java.util.Collection;

public class ObservableArrayList<T> extends ArrayList<T> implements ObservableList<T> {
    private transient ListChangeRegistry mListeners = new ListChangeRegistry();

    public void addOnListChangedCallback(ObservableList.OnListChangedCallback listener) {
        if (this.mListeners == null) {
            this.mListeners = new ListChangeRegistry();
        }
        this.mListeners.add(listener);
    }

    public void removeOnListChangedCallback(ObservableList.OnListChangedCallback listener) {
        if (this.mListeners != null) {
            this.mListeners.remove(listener);
        }
    }

    public boolean add(T object) {
        super.add(object);
        notifyAdd(size() - 1, 1);
        return true;
    }

    public void add(int index, T object) {
        super.add(index, object);
        notifyAdd(index, 1);
    }

    public boolean addAll(Collection<? extends T> collection) {
        int oldSize = size();
        boolean added = super.addAll(collection);
        if (added) {
            notifyAdd(oldSize, size() - oldSize);
        }
        return added;
    }

    public boolean addAll(int index, Collection<? extends T> collection) {
        boolean added = super.addAll(index, collection);
        if (added) {
            notifyAdd(index, collection.size());
        }
        return added;
    }

    public void clear() {
        int oldSize = size();
        super.clear();
        if (oldSize != 0) {
            notifyRemove(0, oldSize);
        }
    }

    public T remove(int index) {
        T val = super.remove(index);
        notifyRemove(index, 1);
        return val;
    }

    public boolean remove(Object object) {
        int index = indexOf(object);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    public T set(int index, T object) {
        T val = super.set(index, object);
        if (this.mListeners != null) {
            this.mListeners.notifyChanged(this, index, 1);
        }
        return val;
    }

    /* access modifiers changed from: protected */
    public void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
        notifyRemove(fromIndex, toIndex - fromIndex);
    }

    private void notifyAdd(int start, int count) {
        if (this.mListeners != null) {
            this.mListeners.notifyInserted(this, start, count);
        }
    }

    private void notifyRemove(int start, int count) {
        if (this.mListeners != null) {
            this.mListeners.notifyRemoved(this, start, count);
        }
    }
}
