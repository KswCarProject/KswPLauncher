package android.databinding;

import android.databinding.Observable;
import android.support.annotation.NonNull;

public class BaseObservable implements Observable {
    private transient PropertyChangeRegistry mCallbacks;

    public void addOnPropertyChangedCallback(@NonNull Observable.OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (this.mCallbacks == null) {
                this.mCallbacks = new PropertyChangeRegistry();
            }
        }
        this.mCallbacks.add(callback);
    }

    public void removeOnPropertyChangedCallback(@NonNull Observable.OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (this.mCallbacks != null) {
                this.mCallbacks.remove(callback);
            }
        }
    }

    public void notifyChange() {
        synchronized (this) {
            if (this.mCallbacks != null) {
                this.mCallbacks.notifyCallbacks(this, 0, null);
            }
        }
    }

    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (this.mCallbacks != null) {
                this.mCallbacks.notifyCallbacks(this, fieldId, null);
            }
        }
    }
}
