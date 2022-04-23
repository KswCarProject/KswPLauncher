package android.support.v4.view;

import android.os.Build;
import android.support.v4.internal.view.SupportMenu;
import android.view.Menu;
import android.view.MenuItem;

public final class MenuCompat {
    @Deprecated
    public static void setShowAsAction(MenuItem item, int actionEnum) {
        item.setShowAsAction(actionEnum);
    }

    public static void setGroupDividerEnabled(Menu menu, boolean enabled) {
        if (menu instanceof SupportMenu) {
            ((SupportMenu) menu).setGroupDividerEnabled(enabled);
        } else if (Build.VERSION.SDK_INT >= 28) {
            menu.setGroupDividerEnabled(enabled);
        }
    }

    private MenuCompat() {
    }
}
