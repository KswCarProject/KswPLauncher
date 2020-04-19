package android.databinding;

import android.databinding.CallbackRegistry;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v4.util.Pools;

public class ListChangeRegistry extends CallbackRegistry<ObservableList.OnListChangedCallback, ObservableList, ListChanges> {
    private static final int ALL = 0;
    private static final int CHANGED = 1;
    private static final int INSERTED = 2;
    private static final int MOVED = 3;
    private static final CallbackRegistry.NotifierCallback<ObservableList.OnListChangedCallback, ObservableList, ListChanges> NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback<ObservableList.OnListChangedCallback, ObservableList, ListChanges>() {
        public void onNotifyCallback(ObservableList.OnListChangedCallback callback, ObservableList sender, int notificationType, ListChanges listChanges) {
            switch (notificationType) {
                case 1:
                    callback.onItemRangeChanged(sender, listChanges.start, listChanges.count);
                    return;
                case 2:
                    callback.onItemRangeInserted(sender, listChanges.start, listChanges.count);
                    return;
                case 3:
                    callback.onItemRangeMoved(sender, listChanges.start, listChanges.to, listChanges.count);
                    return;
                case 4:
                    callback.onItemRangeRemoved(sender, listChanges.start, listChanges.count);
                    return;
                default:
                    callback.onChanged(sender);
                    return;
            }
        }
    };
    private static final int REMOVED = 4;
    private static final Pools.SynchronizedPool<ListChanges> sListChanges = new Pools.SynchronizedPool<>(10);

    public void notifyChanged(@NonNull ObservableList list) {
        notifyCallbacks(list, 0, (ListChanges) null);
    }

    public void notifyChanged(@NonNull ObservableList list, int start, int count) {
        notifyCallbacks(list, 1, acquire(start, 0, count));
    }

    public void notifyInserted(@NonNull ObservableList list, int start, int count) {
        notifyCallbacks(list, 2, acquire(start, 0, count));
    }

    public void notifyMoved(@NonNull ObservableList list, int from, int to, int count) {
        notifyCallbacks(list, 3, acquire(from, to, count));
    }

    public void notifyRemoved(@NonNull ObservableList list, int start, int count) {
        notifyCallbacks(list, 4, acquire(start, 0, count));
    }

    private static ListChanges acquire(int start, int to, int count) {
        ListChanges listChanges = sListChanges.acquire();
        if (listChanges == null) {
            listChanges = new ListChanges();
        }
        listChanges.start = start;
        listChanges.to = to;
        listChanges.count = count;
        return listChanges;
    }

    public synchronized void notifyCallbacks(@NonNull ObservableList sender, int notificationType, ListChanges listChanges) {
        super.notifyCallbacks(sender, notificationType, listChanges);
        if (listChanges != null) {
            sListChanges.release(listChanges);
        }
    }

    public ListChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }

    static class ListChanges {
        public int count;
        public int start;
        public int to;

        ListChanges() {
        }
    }
}
