package android.support.p004v7.view.menu;

/* renamed from: android.support.v7.view.menu.BaseWrapper */
/* loaded from: classes.dex */
class BaseWrapper<T> {
    final T mWrappedObject;

    BaseWrapper(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = object;
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}
