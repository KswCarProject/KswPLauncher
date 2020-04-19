package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.adapters.AdapterViewBindingAdapter;
import android.support.annotation.RestrictTo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

@BindingMethods({@BindingMethod(attribute = "android:completionThreshold", method = "setThreshold", type = AutoCompleteTextView.class), @BindingMethod(attribute = "android:popupBackground", method = "setDropDownBackgroundDrawable", type = AutoCompleteTextView.class), @BindingMethod(attribute = "android:onDismiss", method = "setOnDismissListener", type = AutoCompleteTextView.class), @BindingMethod(attribute = "android:onItemClick", method = "setOnItemClickListener", type = AutoCompleteTextView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class AutoCompleteTextViewBindingAdapter {

    public interface FixText {
        CharSequence fixText(CharSequence charSequence);
    }

    public interface IsValid {
        boolean isValid(CharSequence charSequence);
    }

    @BindingAdapter(requireAll = false, value = {"android:fixText", "android:isValid"})
    public static void setValidator(AutoCompleteTextView view, final FixText fixText, final IsValid isValid) {
        if (fixText == null && isValid == null) {
            view.setValidator((AutoCompleteTextView.Validator) null);
        } else {
            view.setValidator(new AutoCompleteTextView.Validator() {
                public boolean isValid(CharSequence text) {
                    if (isValid != null) {
                        return isValid.isValid(text);
                    }
                    return true;
                }

                public CharSequence fixText(CharSequence invalidText) {
                    if (fixText != null) {
                        return fixText.fixText(invalidText);
                    }
                    return invalidText;
                }
            });
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onItemSelected", "android:onNothingSelected"})
    public static void setOnItemSelectedListener(AutoCompleteTextView view, AdapterViewBindingAdapter.OnItemSelected selected, AdapterViewBindingAdapter.OnNothingSelected nothingSelected) {
        if (selected == null && nothingSelected == null) {
            view.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        } else {
            view.setOnItemSelectedListener(new AdapterViewBindingAdapter.OnItemSelectedComponentListener(selected, nothingSelected, (InverseBindingListener) null));
        }
    }
}
