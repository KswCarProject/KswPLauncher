package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.CalendarView;

public class CalendarViewBindingAdapter {
    public static void setDate(CalendarView view, long date) {
        if (view.getDate() != date) {
            view.setDate(date);
        }
    }

    public static void setListeners(CalendarView view, final CalendarView.OnDateChangeListener onDayChange, final InverseBindingListener attrChange) {
        if (attrChange == null) {
            view.setOnDateChangeListener(onDayChange);
        } else {
            view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    CalendarView.OnDateChangeListener onDateChangeListener = onDayChange;
                    if (onDateChangeListener != null) {
                        onDateChangeListener.onSelectedDayChange(view, year, month, dayOfMonth);
                    }
                    attrChange.onChange();
                }
            });
        }
    }
}
