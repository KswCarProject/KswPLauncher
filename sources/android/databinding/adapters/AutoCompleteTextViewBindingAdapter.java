package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.databinding.adapters.AdapterViewBindingAdapter;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextViewBindingAdapter {

    public interface FixText {
        CharSequence fixText(CharSequence charSequence);
    }

    public interface IsValid {
        boolean isValid(CharSequence charSequence);
    }

    public static void setValidator(AutoCompleteTextView view, final FixText fixText, final IsValid isValid) {
        if (fixText == null && isValid == null) {
            view.setValidator((AutoCompleteTextView.Validator) null);
        } else {
            view.setValidator(new AutoCompleteTextView.Validator() {
                public boolean isValid(CharSequence text) {
                    IsValid isValid = isValid;
                    if (isValid != null) {
                        return isValid.isValid(text);
                    }
                    return true;
                }

                public CharSequence fixText(CharSequence invalidText) {
                    FixText fixText = fixText;
                    if (fixText != null) {
                        return fixText.fixText(invalidText);
                    }
                    return invalidText;
                }
            });
        }
    }

    public static void setOnItemSelectedListener(AutoCompleteTextView view, AdapterViewBindingAdapter.OnItemSelected selected, AdapterViewBindingAdapter.OnNothingSelected nothingSelected) {
        if (selected == null && nothingSelected == null) {
            view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        } else {
            view.setOnItemSelectedListener(new AdapterViewBindingAdapter.OnItemSelectedComponentListener(selected, nothingSelected, (InverseBindingListener) null));
        }
    }
}
