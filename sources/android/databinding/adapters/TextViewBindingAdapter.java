package android.databinding.adapters;

import android.databinding.InverseBindingListener;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DialerKeyListener;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TextKeyListener;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.widget.TextView;
import com.android.databinding.library.baseAdapters.R;

public class TextViewBindingAdapter {
    public static final int DECIMAL = 5;
    public static final int INTEGER = 1;
    public static final int SIGNED = 3;
    private static final String TAG = "TextViewBindingAdapters";

    public interface AfterTextChanged {
        void afterTextChanged(Editable editable);
    }

    public interface BeforeTextChanged {
        void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3);
    }

    public interface OnTextChanged {
        void onTextChanged(CharSequence charSequence, int i, int i2, int i3);
    }

    public static void setText(TextView view, CharSequence text) {
        CharSequence oldText = view.getText();
        if (text == oldText) {
            return;
        }
        if (text != null || oldText.length() != 0) {
            if (text instanceof Spanned) {
                if (text.equals(oldText)) {
                    return;
                }
            } else if (!haveContentsChanged(text, oldText)) {
                return;
            }
            view.setText(text);
        }
    }

    public static String getTextString(TextView view) {
        return view.getText().toString();
    }

    public static void setAutoText(TextView view, boolean autoText) {
        KeyListener listener = view.getKeyListener();
        TextKeyListener.Capitalize capitalize = TextKeyListener.Capitalize.NONE;
        int inputType = listener != null ? listener.getInputType() : 0;
        if ((inputType & 4096) != 0) {
            capitalize = TextKeyListener.Capitalize.CHARACTERS;
        } else if ((inputType & 8192) != 0) {
            capitalize = TextKeyListener.Capitalize.WORDS;
        } else if ((inputType & 16384) != 0) {
            capitalize = TextKeyListener.Capitalize.SENTENCES;
        }
        view.setKeyListener(TextKeyListener.getInstance(autoText, capitalize));
    }

    public static void setCapitalize(TextView view, TextKeyListener.Capitalize capitalize) {
        view.setKeyListener(TextKeyListener.getInstance((32768 & view.getKeyListener().getInputType()) != 0, capitalize));
    }

    public static void setBufferType(TextView view, TextView.BufferType bufferType) {
        view.setText(view.getText(), bufferType);
    }

    public static void setDigits(TextView view, CharSequence digits) {
        if (digits != null) {
            view.setKeyListener(DigitsKeyListener.getInstance(digits.toString()));
        } else if (view.getKeyListener() instanceof DigitsKeyListener) {
            view.setKeyListener((KeyListener) null);
        }
    }

    public static void setNumeric(TextView view, int numeric) {
        boolean z = true;
        boolean z2 = (numeric & 3) != 0;
        if ((numeric & 5) == 0) {
            z = false;
        }
        view.setKeyListener(DigitsKeyListener.getInstance(z2, z));
    }

    public static void setPhoneNumber(TextView view, boolean phoneNumber) {
        if (phoneNumber) {
            view.setKeyListener(DialerKeyListener.getInstance());
        } else if (view.getKeyListener() instanceof DialerKeyListener) {
            view.setKeyListener((KeyListener) null);
        }
    }

    private static void setIntrinsicBounds(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    public static void setDrawableBottom(TextView view, Drawable drawable) {
        setIntrinsicBounds(drawable);
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawable);
    }

    public static void setDrawableLeft(TextView view, Drawable drawable) {
        setIntrinsicBounds(drawable);
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawableRight(TextView view, Drawable drawable) {
        setIntrinsicBounds(drawable);
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
    }

    public static void setDrawableTop(TextView view, Drawable drawable) {
        setIntrinsicBounds(drawable);
        Drawable[] drawables = view.getCompoundDrawables();
        view.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
    }

    public static void setDrawableStart(TextView view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 17) {
            setDrawableLeft(view, drawable);
            return;
        }
        setIntrinsicBounds(drawable);
        Drawable[] drawables = view.getCompoundDrawablesRelative();
        view.setCompoundDrawablesRelative(drawable, drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawableEnd(TextView view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 17) {
            setDrawableRight(view, drawable);
            return;
        }
        setIntrinsicBounds(drawable);
        Drawable[] drawables = view.getCompoundDrawablesRelative();
        view.setCompoundDrawablesRelative(drawables[0], drawables[1], drawable, drawables[3]);
    }

    public static void setImeActionLabel(TextView view, CharSequence value) {
        view.setImeActionLabel(value, view.getImeActionId());
    }

    public static void setImeActionLabel(TextView view, int value) {
        view.setImeActionLabel(view.getImeActionLabel(), value);
    }

    public static void setInputMethod(TextView view, CharSequence inputMethod) {
        try {
            view.setKeyListener((KeyListener) Class.forName(inputMethod.toString()).newInstance());
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Could not create input method: " + inputMethod, e);
        } catch (InstantiationException e2) {
            Log.e(TAG, "Could not create input method: " + inputMethod, e2);
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "Could not create input method: " + inputMethod, e3);
        }
    }

    public static void setLineSpacingExtra(TextView view, float value) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setLineSpacing(value, view.getLineSpacingMultiplier());
        } else {
            view.setLineSpacing(value, 1.0f);
        }
    }

    public static void setLineSpacingMultiplier(TextView view, float value) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setLineSpacing(view.getLineSpacingExtra(), value);
        } else {
            view.setLineSpacing(0.0f, value);
        }
    }

    public static void setMaxLength(TextView view, int value) {
        InputFilter[] filters = view.getFilters();
        if (filters == null) {
            filters = new InputFilter[]{new InputFilter.LengthFilter(value)};
        } else {
            boolean foundMaxLength = false;
            int i = 0;
            while (true) {
                if (i >= filters.length) {
                    break;
                }
                InputFilter filter = filters[i];
                if (filter instanceof InputFilter.LengthFilter) {
                    foundMaxLength = true;
                    boolean replace = true;
                    if (Build.VERSION.SDK_INT >= 21) {
                        replace = ((InputFilter.LengthFilter) filter).getMax() != value;
                    }
                    if (replace) {
                        filters[i] = new InputFilter.LengthFilter(value);
                    }
                } else {
                    i++;
                }
            }
            if (!foundMaxLength) {
                InputFilter[] oldFilters = filters;
                filters = new InputFilter[(oldFilters.length + 1)];
                System.arraycopy(oldFilters, 0, filters, 0, oldFilters.length);
                filters[filters.length - 1] = new InputFilter.LengthFilter(value);
            }
        }
        view.setFilters(filters);
    }

    public static void setPassword(TextView view, boolean password) {
        if (password) {
            view.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (view.getTransformationMethod() instanceof PasswordTransformationMethod) {
            view.setTransformationMethod((TransformationMethod) null);
        }
    }

    public static void setShadowColor(TextView view, int color) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setShadowLayer(view.getShadowRadius(), view.getShadowDx(), view.getShadowDy(), color);
        }
    }

    public static void setShadowDx(TextView view, float dx) {
        if (Build.VERSION.SDK_INT >= 16) {
            int color = view.getShadowColor();
            view.setShadowLayer(view.getShadowRadius(), dx, view.getShadowDy(), color);
        }
    }

    public static void setShadowDy(TextView view, float dy) {
        if (Build.VERSION.SDK_INT >= 16) {
            int color = view.getShadowColor();
            view.setShadowLayer(view.getShadowRadius(), view.getShadowDx(), dy, color);
        }
    }

    public static void setShadowRadius(TextView view, float r) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setShadowLayer(r, view.getShadowDx(), view.getShadowDy(), view.getShadowColor());
        }
    }

    public static void setTextSize(TextView view, float size) {
        view.setTextSize(0, size);
    }

    private static boolean haveContentsChanged(CharSequence str1, CharSequence str2) {
        if ((str1 == null) != (str2 == null)) {
            return true;
        }
        if (str1 == null) {
            return false;
        }
        int length = str1.length();
        if (length != str2.length()) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public static void setTextWatcher(TextView view, final BeforeTextChanged before, final OnTextChanged on, final AfterTextChanged after, final InverseBindingListener textAttrChanged) {
        TextWatcher newValue;
        if (before == null && after == null && on == null && textAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new TextWatcher() {
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    BeforeTextChanged beforeTextChanged = before;
                    if (beforeTextChanged != null) {
                        beforeTextChanged.beforeTextChanged(s, start, count, after);
                    }
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    OnTextChanged onTextChanged = on;
                    if (onTextChanged != null) {
                        onTextChanged.onTextChanged(s, start, before, count);
                    }
                    InverseBindingListener inverseBindingListener = textAttrChanged;
                    if (inverseBindingListener != null) {
                        inverseBindingListener.onChange();
                    }
                }

                public void afterTextChanged(Editable s) {
                    AfterTextChanged afterTextChanged = after;
                    if (afterTextChanged != null) {
                        afterTextChanged.afterTextChanged(s);
                    }
                }
            };
        }
        TextWatcher oldValue = (TextWatcher) ListenerUtil.trackListener(view, newValue, R.id.textWatcher);
        if (oldValue != null) {
            view.removeTextChangedListener(oldValue);
        }
        if (newValue != null) {
            view.addTextChangedListener(newValue);
        }
    }
}
