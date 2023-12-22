package io.reactivex;

/* loaded from: classes.dex */
public interface SingleOnSubscribe<T> {
    void subscribe(SingleEmitter<T> singleEmitter) throws Exception;
}
