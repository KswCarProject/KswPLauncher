package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.CalendarView;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:date", type = CalendarView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class CalendarViewBindingAdapter {
    @BindingAdapter({"android:date"})
    public static void setDate(CalendarView view, long date) {
        if (view.getDate() != date) {
            view.setDate(date);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onSelectedDayChange", "android:dateAttrChanged"})
    public static void setListeners(CalendarView view, final CalendarView.OnDateChangeListener onDayChange, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnDateChangeListener(onDayChange);
        } else {
            view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    if (onDayChange != null) {
                        onDayChange.onSelectedDayChange(view, year, month, dayOfMonth);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
