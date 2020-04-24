package android.support.v7.view.menu;

class BaseWrapper<T> {
    final T mWrappedObject;

    BaseWrapper(T object) {
        if (object != null) {
            this.mWrappedObject = object;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}
