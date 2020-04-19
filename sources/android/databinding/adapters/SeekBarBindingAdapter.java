package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.RestrictTo;
import android.widget.SeekBar;

@InverseBindingMethods({@InverseBindingMethod(attribute = "android:progress", type = SeekBar.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class SeekBarBindingAdapter {

    public interface OnProgressChanged {
        void onProgressChanged(SeekBar seekBar, int i, boolean z);
    }

    public interface OnStartTrackingTouch {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    public interface OnStopTrackingTouch {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    @BindingAdapter({"android:progress"})
    public static void setProgress(SeekBar view, int progress) {
        if (progress != view.getProgress()) {
            view.setProgress(progress);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged", "android:progressAttrChanged"})
    public static void setOnSeekBarChangeListener(SeekBar view, final OnStartTrackingTouch start, final OnStopTrackingTouch stop, final OnProgressChanged progressChanged, final InverseBindingListener attrChanged) {
        if (start == null && stop == null && progressChanged == null && attrChanged == null) {
            view.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        } else {
            view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (progressChanged != null) {
                        progressChanged.onProgressChanged(seekBar, progress, fromUser);
                    }
                    if (attrChanged != null) {
                        attrChanged.onChange();
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    if (start != null) {
                        start.onStartTrackingTouch(seekBar);
                    }
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (stop != null) {
                        stop.onStopTrackingTouch(seekBar);
                    }
                }
            });
        }
    }
}
