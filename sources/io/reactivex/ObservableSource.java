package io.reactivex;

/* loaded from: classes.dex */
public interface ObservableSource<T> {
    void subscribe(Observer<? super T> observer);
}
