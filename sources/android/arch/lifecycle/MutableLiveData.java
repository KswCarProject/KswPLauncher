package android.arch.lifecycle;

/* loaded from: classes.dex */
public class MutableLiveData<T> extends LiveData<T> {
    @Override // android.arch.lifecycle.LiveData
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override // android.arch.lifecycle.LiveData
    public void setValue(T value) {
        super.setValue(value);
    }
}
