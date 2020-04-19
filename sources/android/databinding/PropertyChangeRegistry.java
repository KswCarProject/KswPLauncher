package android.databinding;

import android.databinding.CallbackRegistry;
import android.databinding.Observable;
import android.support.annotation.NonNull;

public class PropertyChangeRegistry extends CallbackRegistry<Observable.OnPropertyChangedCallback, Observable, Void> {
    private static final CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void> NOTIFIER_CALLBACK = new CallbackRegistry.NotifierCallback<Observable.OnPropertyChangedCallback, Observable, Void>() {
        public void onNotifyCallback(Observable.OnPropertyChangedCallback callback, Observable sender, int arg, Void notUsed) {
            callback.onPropertyChanged(sender, arg);
        }
    };

    public PropertyChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }

    public void notifyChange(@NonNull Observable observable, int propertyId) {
        notifyCallbacks(observable, propertyId, null);
    }
}
