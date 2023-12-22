package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.os.Build;
import android.widget.TimePicker;

/* loaded from: classes.dex */
public class TimePickerBindingAdapter {
    public static void setHour(TimePicker view, int hour) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (view.getHour() != hour) {
                view.setHour(hour);
            }
        } else if (view.getCurrentHour().intValue() != hour) {
            view.setCurrentHour(Integer.valueOf(hour));
        }
    }

    public static void setMinute(TimePicker view, int minute) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (view.getMinute() != minute) {
                view.setMinute(minute);
            }
        } else if (view.getCurrentMinute().intValue() != minute) {
            view.setCurrentHour(Integer.valueOf(minute));
        }
    }

    public static int getHour(TimePicker view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getHour();
        }
        Integer hour = view.getCurrentHour();
        if (hour == null) {
            return 0;
        }
        return hour.intValue();
    }

    public static int getMinute(TimePicker view) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getMinute();
        }
        Integer minute = view.getCurrentMinute();
        if (minute == null) {
            return 0;
        }
        return minute.intValue();
    }

    public static void setListeners(TimePicker view, final TimePicker.OnTimeChangedListener listener, final InverseBindingListener hourChange, final InverseBindingListener minuteChange) {
        if (hourChange == null && minuteChange == null) {
            view.setOnTimeChangedListener(listener);
        } else {
            view.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() { // from class: android.databinding.adapters.TimePickerBindingAdapter.1
                @Override // android.widget.TimePicker.OnTimeChangedListener
                public void onTimeChanged(TimePicker view2, int hourOfDay, int minute) {
                    TimePicker.OnTimeChangedListener onTimeChangedListener = listener;
                    if (onTimeChangedListener != null) {
                        onTimeChangedListener.onTimeChanged(view2, hourOfDay, minute);
                    }
                    InverseBindingListener inverseBindingListener = hourChange;
                    if (inverseBindingListener != null) {
                        inverseBindingListener.onChange();
                    }
                    InverseBindingListener inverseBindingListener2 = minuteChange;
                    if (inverseBindingListener2 != null) {
                        inverseBindingListener2.onChange();
                    }
                }
            });
        }
    }
}
