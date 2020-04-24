package butterknife;

import android.view.View;
import java.util.AbstractList;
import java.util.RandomAccess;

final class ImmutableViewList<T extends View> extends AbstractList<T> implements RandomAccess {
    private final T[] views;

    ImmutableViewList(T[] views2) {
        this.views = views2;
    }

    public T get(int index) {
        return this.views[index];
    }

    public int size() {
        return this.views.length;
    }

    public boolean contains(Object o) {
        for (View view : this.views) {
            if (view == o) {
                return true;
            }
        }
        return false;
    }
}
