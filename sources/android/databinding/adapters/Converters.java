package android.databinding.adapters;

import android.content.res.ColorStateList;
import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;

public class Converters {
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }

    @BindingConversion
    public static ColorStateList convertColorToColorStateList(int color) {
        return ColorStateList.valueOf(color);
    }
}
