package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.annotation.RestrictTo;
import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AbsSpinnerBindingAdapter {
    @BindingAdapter({"android:entries"})
    public static <T extends CharSequence> void setEntries(AbsSpinner view, T[] entries) {
        if (entries != null) {
            SpinnerAdapter oldAdapter = view.getAdapter();
            boolean changed = true;
            if (oldAdapter != null && oldAdapter.getCount() == entries.length) {
                changed = false;
                int i = 0;
                while (true) {
                    if (i >= entries.length) {
                        break;
                    } else if (!entries[i].equals(oldAdapter.getItem(i))) {
                        changed = true;
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (changed) {
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(view.getContext(), 17367048, entries);
                adapter.setDropDownViewResource(17367049);
                view.setAdapter(adapter);
                return;
            }
            return;
        }
        view.setAdapter((SpinnerAdapter) null);
    }

    @BindingAdapter({"android:entries"})
    public static <T> void setEntries(AbsSpinner view, List<T> entries) {
        if (entries != null) {
            SpinnerAdapter oldAdapter = view.getAdapter();
            if (oldAdapter instanceof ObservableListAdapter) {
                ((ObservableListAdapter) oldAdapter).setList(entries);
            } else {
                view.setAdapter(new ObservableListAdapter(view.getContext(), entries, 17367048, 17367049, 0));
            }
        } else {
            view.setAdapter((SpinnerAdapter) null);
        }
    }
}
