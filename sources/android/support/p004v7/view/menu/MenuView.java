package android.support.p004v7.view.menu;

import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.view.menu.MenuView */
/* loaded from: classes.dex */
public interface MenuView {

    /* renamed from: android.support.v7.view.menu.MenuView$ItemView */
    /* loaded from: classes.dex */
    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i);

        boolean prefersCondensedTitle();

        void setCheckable(boolean z);

        void setChecked(boolean z);

        void setEnabled(boolean z);

        void setIcon(Drawable drawable);

        void setShortcut(boolean z, char c);

        void setTitle(CharSequence charSequence);

        boolean showsIcon();
    }

    int getWindowAnimations();

    void initialize(MenuBuilder menuBuilder);
}
