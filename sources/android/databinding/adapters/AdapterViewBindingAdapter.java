package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

public class AdapterViewBindingAdapter {

    public interface OnItemSelected {
        void onItemSelected(AdapterView<?> adapterView, View view, int i, long j);
    }

    public interface OnNothingSelected {
        void onNothingSelected(AdapterView<?> adapterView);
    }

    public static void setSelectedItemPosition(AdapterView view, int position) {
        if (view.getSelectedItemPosition() != position) {
            view.setSelection(position);
        }
    }

    public static void setSelection(AdapterView view, int position) {
        setSelectedItemPosition(view, position);
    }

    public static void setSelectedItemPosition(AdapterView view, int position, Adapter adapter) {
        if (adapter != view.getAdapter()) {
            view.setAdapter(adapter);
            view.setSelection(position);
        } else if (view.getSelectedItemPosition() != position) {
            view.setSelection(position);
        }
    }

    public static void setSelection(AdapterView view, int position, Adapter adapter) {
        setSelectedItemPosition(view, position, adapter);
    }

    public static void setOnItemSelectedListener(AdapterView view, OnItemSelected selected, OnNothingSelected nothingSelected, InverseBindingListener attrChanged) {
        if (selected == null && nothingSelected == null && attrChanged == null) {
            view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        } else {
            view.setOnItemSelectedListener(new OnItemSelectedComponentListener(selected, nothingSelected, attrChanged));
        }
    }

    public static class OnItemSelectedComponentListener implements AdapterView.OnItemSelectedListener {
        private final InverseBindingListener mAttrChanged;
        private final OnNothingSelected mNothingSelected;
        private final OnItemSelected mSelected;

        public OnItemSelectedComponentListener(OnItemSelected selected, OnNothingSelected nothingSelected, InverseBindingListener attrChanged) {
            this.mSelected = selected;
            this.mNothingSelected = nothingSelected;
            this.mAttrChanged = attrChanged;
        }

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            OnItemSelected onItemSelected = this.mSelected;
            if (onItemSelected != null) {
                onItemSelected.onItemSelected(parent, view, position, id);
            }
            InverseBindingListener inverseBindingListener = this.mAttrChanged;
            if (inverseBindingListener != null) {
                inverseBindingListener.onChange();
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
            OnNothingSelected onNothingSelected = this.mNothingSelected;
            if (onNothingSelected != null) {
                onNothingSelected.onNothingSelected(parent);
            }
            InverseBindingListener inverseBindingListener = this.mAttrChanged;
            if (inverseBindingListener != null) {
                inverseBindingListener.onChange();
            }
        }
    }
}
