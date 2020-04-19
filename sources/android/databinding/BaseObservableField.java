package android.databinding;

import android.databinding.Observable;

abstract class BaseObservableField extends BaseObservable {
    public BaseObservableField() {
    }

    public BaseObservableField(Observable... dependencies) {
        if (dependencies != null && dependencies.length != 0) {
            DependencyCallback callback = new DependencyCallback();
            for (Observable addOnPropertyChangedCallback : dependencies) {
                addOnPropertyChangedCallback.addOnPropertyChangedCallback(callback);
            }
        }
    }

    class DependencyCallback extends Observable.OnPropertyChangedCallback {
        DependencyCallback() {
        }

        public void onPropertyChanged(Observable sender, int propertyId) {
            BaseObservableField.this.notifyChange();
        }
    }
}
