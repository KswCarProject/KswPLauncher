package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.widget.SeekBar;

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

    public static void setProgress(SeekBar view, int progress) {
        if (progress != view.getProgress()) {
            view.setProgress(progress);
        }
    }

    public static void setOnSeekBarChangeListener(SeekBar view, final OnStartTrackingTouch start, final OnStopTrackingTouch stop, final OnProgressChanged progressChanged, final InverseBindingListener attrChanged) {
        if (start == null && stop == null && progressChanged == null && attrChanged == null) {
            view.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) null);
        } else {
            view.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    OnProgressChanged onProgressChanged = progressChanged;
                    if (onProgressChanged != null) {
                        onProgressChanged.onProgressChanged(seekBar, progress, fromUser);
                    }
                    InverseBindingListener inverseBindingListener = attrChanged;
                    if (inverseBindingListener != null) {
                        inverseBindingListener.onChange();
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    OnStartTrackingTouch onStartTrackingTouch = start;
                    if (onStartTrackingTouch != null) {
                        onStartTrackingTouch.onStartTrackingTouch(seekBar);
                    }
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    OnStopTrackingTouch onStopTrackingTouch = stop;
                    if (onStopTrackingTouch != null) {
                        onStopTrackingTouch.onStopTrackingTouch(seekBar);
                    }
                }
            });
        }
    }
}
