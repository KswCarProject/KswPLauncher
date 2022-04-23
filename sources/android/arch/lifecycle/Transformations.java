package android.arch.lifecycle;

import android.arch.core.util.Function;

public class Transformations {
    private Transformations() {
    }

    public static <X, Y> LiveData<Y> map(LiveData<X> source, final Function<X, Y> func) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();
        result.addSource(source, new Observer<X>() {
            public void onChanged(X x) {
                result.setValue(func.apply(x));
            }
        });
        return result;
    }

    public static <X, Y> LiveData<Y> switchMap(LiveData<X> trigger, final Function<X, LiveData<Y>> func) {
        final MediatorLiveData<Y> result = new MediatorLiveData<>();
        result.addSource(trigger, new Observer<X>() {
            LiveData<Y> mSource;

            public void onChanged(X x) {
                LiveData<Y> newLiveData = (LiveData) func.apply(x);
                LiveData<Y> liveData = this.mSource;
                if (liveData != newLiveData) {
                    if (liveData != null) {
                        result.removeSource(liveData);
                    }
                    this.mSource = newLiveData;
                    if (newLiveData != null) {
                        result.addSource(newLiveData, new Observer<Y>() {
                            public void onChanged(Y y) {
                                result.setValue(y);
                            }
                        });
                    }
                }
            }
        });
        return result;
    }
}
