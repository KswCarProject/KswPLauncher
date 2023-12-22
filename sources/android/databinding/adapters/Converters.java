package android.databinding.adapters;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;

/* loaded from: classes.dex */
public class Converters {
    public static ColorDrawable convertColorToDrawable(int color) {
        return new ColorDrawable(color);
    }

    public static ColorStateList convertColorToColorStateList(int color) {
        return ColorStateList.valueOf(color);
    }
}
