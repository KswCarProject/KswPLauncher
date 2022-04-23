package skin.support.observe;

import java.util.ArrayList;

public class SkinObservable {
    private final ArrayList<SkinObserver> observers = new ArrayList<>();

    public synchronized void addObserver(SkinObserver o) {
        if (o == null) {
            throw new NullPointerException();
        } else if (!this.observers.contains(o)) {
            this.observers.add(o);
        }
    }

    public synchronized void deleteObserver(SkinObserver o) {
        this.observers.remove(o);
    }

    public void notifyUpdateSkin() {
        notifyUpdateSkin((Object) null);
    }

    public void notifyUpdateSkin(Object arg) {
        SkinObserver[] arrLocal;
        synchronized (this) {
            ArrayList<SkinObserver> arrayList = this.observers;
            arrLocal = (SkinObserver[]) arrayList.toArray(new SkinObserver[arrayList.size()]);
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].updateSkin(this, arg);
        }
    }

    public synchronized void deleteObservers() {
        this.observers.clear();
    }

    public synchronized int countObservers() {
        return this.observers.size();
    }
}
