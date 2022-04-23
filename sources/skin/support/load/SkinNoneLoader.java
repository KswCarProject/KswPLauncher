package skin.support.load;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import skin.support.SkinCompatManager;

public class SkinNoneLoader implements SkinCompatManager.SkinLoaderStrategy {
    public String loadSkinInBackground(Context context, String skinName) {
        return "";
    }

    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return "";
    }

    public ColorStateList getColor(Context context, String skinName, int resId) {
        return null;
    }

    public ColorStateList getColorStateList(Context context, String skinName, int resId) {
        return null;
    }

    public Drawable getDrawable(Context context, String skinName, int resId) {
        return null;
    }

    public int getType() {
        return -1;
    }
}
