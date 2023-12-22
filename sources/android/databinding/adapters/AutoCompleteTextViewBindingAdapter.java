package android.databinding.adapters;

import android.databinding.adapters.AdapterViewBindingAdapter;
import android.widget.AutoCompleteTextView;

/* loaded from: classes.dex */
public class AutoCompleteTextViewBindingAdapter {

    /* loaded from: classes.dex */
    public interface FixText {
        CharSequence fixText(CharSequence charSequence);
    }

    /* loaded from: classes.dex */
    public interface IsValid {
        boolean isValid(CharSequence charSequence);
    }

    public static void setValidator(AutoCompleteTextView view, final FixText fixText, final IsValid isValid) {
        if (fixText == null && isValid == null) {
            view.setValidator(null);
        } else {
            view.setValidator(new AutoCompleteTextView.Validator() { // from class: android.databinding.adapters.AutoCompleteTextViewBindingAdapter.1
                @Override // android.widget.AutoCompleteTextView.Validator
                public boolean isValid(CharSequence text) {
                    IsValid isValid2 = IsValid.this;
                    if (isValid2 != null) {
                        return isValid2.isValid(text);
                    }
                    return true;
                }

                @Override // android.widget.AutoCompleteTextView.Validator
                public CharSequence fixText(CharSequence invalidText) {
                    FixText fixText2 = fixText;
                    if (fixText2 != null) {
                        return fixText2.fixText(invalidText);
                    }
                    return invalidText;
                }
            });
        }
    }

    public static void setOnItemSelectedListener(AutoCompleteTextView view, AdapterViewBindingAdapter.OnItemSelected selected, AdapterViewBindingAdapter.OnNothingSelected nothingSelected) {
        if (selected == null && nothingSelected == null) {
            view.setOnItemSelectedListener(null);
        } else {
            view.setOnItemSelectedListener(new AdapterViewBindingAdapter.OnItemSelectedComponentListener(selected, nothingSelected, null));
        }
    }
}
