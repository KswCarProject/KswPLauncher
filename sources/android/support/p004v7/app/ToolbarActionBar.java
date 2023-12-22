package android.support.p004v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.app.ActionBar;
import android.support.p004v7.view.WindowCallbackWrapper;
import android.support.p004v7.view.menu.MenuBuilder;
import android.support.p004v7.view.menu.MenuPresenter;
import android.support.p004v7.widget.DecorToolbar;
import android.support.p004v7.widget.Toolbar;
import android.support.p004v7.widget.ToolbarWidgetWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;

/* renamed from: android.support.v7.app.ToolbarActionBar */
/* loaded from: classes.dex */
class ToolbarActionBar extends ActionBar {
    DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker;
    boolean mToolbarMenuPrepared;
    Window.Callback mWindowCallback;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    private final Runnable mMenuInvalidator = new Runnable() { // from class: android.support.v7.app.ToolbarActionBar.1
        @Override // java.lang.Runnable
        public void run() {
            ToolbarActionBar.this.populateOptionsMenu();
        }
    };

    ToolbarActionBar(Toolbar toolbar, CharSequence title, Window.Callback windowCallback) {
        Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: android.support.v7.app.ToolbarActionBar.2
            @Override // android.support.p004v7.widget.Toolbar.OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem item) {
                return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, item);
            }
        };
        this.mMenuClicker = onMenuItemClickListener;
        this.mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        ToolbarCallbackWrapper toolbarCallbackWrapper = new ToolbarCallbackWrapper(windowCallback);
        this.mWindowCallback = toolbarCallbackWrapper;
        this.mDecorToolbar.setWindowCallback(toolbarCallbackWrapper);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        this.mDecorToolbar.setWindowTitle(title);
    }

    public Window.Callback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setCustomView(View view) {
        setCustomView(view, new ActionBar.LayoutParams(-2, -2));
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        if (view != null) {
            view.setLayoutParams(layoutParams);
        }
        this.mDecorToolbar.setCustomView(view);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setCustomView(int resId) {
        LayoutInflater inflater = LayoutInflater.from(this.mDecorToolbar.getContext());
        setCustomView(inflater.inflate(resId, this.mDecorToolbar.getViewGroup(), false));
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setIcon(int resId) {
        this.mDecorToolbar.setIcon(resId);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setIcon(Drawable icon) {
        this.mDecorToolbar.setIcon(icon);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setLogo(int resId) {
        this.mDecorToolbar.setLogo(resId);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setLogo(Drawable logo) {
        this.mDecorToolbar.setLogo(logo);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable d) {
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable d) {
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setHomeButtonEnabled(boolean enabled) {
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setElevation(float elevation) {
        ViewCompat.setElevation(this.mDecorToolbar.getViewGroup(), elevation);
    }

    @Override // android.support.p004v7.app.ActionBar
    public float getElevation() {
        return ViewCompat.getElevation(this.mDecorToolbar.getViewGroup());
    }

    @Override // android.support.p004v7.app.ActionBar
    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean isTitleTruncated() {
        return super.isTitleTruncated();
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setHomeAsUpIndicator(Drawable indicator) {
        this.mDecorToolbar.setNavigationIcon(indicator);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setHomeAsUpIndicator(int resId) {
        this.mDecorToolbar.setNavigationIcon(resId);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setHomeActionContentDescription(CharSequence description) {
        this.mDecorToolbar.setNavigationContentDescription(description);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setHomeActionContentDescription(int resId) {
        this.mDecorToolbar.setNavigationContentDescription(resId);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    @Override // android.support.p004v7.app.ActionBar
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter adapter, ActionBar.OnNavigationListener callback) {
        this.mDecorToolbar.setDropdownParams(adapter, new NavItemSelectedListener(callback));
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setSelectedNavigationItem(int position) {
        switch (this.mDecorToolbar.getNavigationMode()) {
            case 1:
                this.mDecorToolbar.setDropdownSelectedPosition(position);
                return;
            default:
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
    }

    @Override // android.support.p004v7.app.ActionBar
    public int getSelectedNavigationIndex() {
        return -1;
    }

    @Override // android.support.p004v7.app.ActionBar
    public int getNavigationItemCount() {
        return 0;
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setTitle(CharSequence title) {
        this.mDecorToolbar.setTitle(title);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setTitle(int resId) {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        decorToolbar.setTitle(resId != 0 ? decorToolbar.getContext().getText(resId) : null);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setWindowTitle(CharSequence title) {
        this.mDecorToolbar.setWindowTitle(title);
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean requestFocus() {
        ViewGroup viewGroup = this.mDecorToolbar.getViewGroup();
        if (viewGroup != null && !viewGroup.hasFocus()) {
            viewGroup.requestFocus();
            return true;
        }
        return false;
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setSubtitle(CharSequence subtitle) {
        this.mDecorToolbar.setSubtitle(subtitle);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setSubtitle(int resId) {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        decorToolbar.setSubtitle(resId != 0 ? decorToolbar.getContext().getText(resId) : null);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayOptions(int options) {
        setDisplayOptions(options, -1);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayOptions(int options, int mask) {
        int currentOptions = this.mDecorToolbar.getDisplayOptions();
        this.mDecorToolbar.setDisplayOptions((options & mask) | ((~mask) & currentOptions));
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean useLogo) {
        setDisplayOptions(useLogo ? 1 : 0, 1);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean showHome) {
        setDisplayOptions(showHome ? 2 : 0, 2);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        setDisplayOptions(showHomeAsUp ? 4 : 0, 4);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean showTitle) {
        setDisplayOptions(showTitle ? 8 : 0, 8);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean showCustom) {
        setDisplayOptions(showCustom ? 16 : 0, 16);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setBackgroundDrawable(Drawable d) {
        this.mDecorToolbar.setBackgroundDrawable(d);
    }

    @Override // android.support.p004v7.app.ActionBar
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    @Override // android.support.p004v7.app.ActionBar
    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    @Override // android.support.p004v7.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    @Override // android.support.p004v7.app.ActionBar
    public int getNavigationMode() {
        return 0;
    }

    @Override // android.support.p004v7.app.ActionBar
    public void setNavigationMode(int mode) {
        if (mode == 2) {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        }
        this.mDecorToolbar.setNavigationMode(mode);
    }

    @Override // android.support.p004v7.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    @Override // android.support.p004v7.app.ActionBar
    public ActionBar.Tab newTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void addTab(ActionBar.Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void addTab(ActionBar.Tab tab, boolean setSelected) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void addTab(ActionBar.Tab tab, int position) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void addTab(ActionBar.Tab tab, int position, boolean setSelected) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void removeTab(ActionBar.Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void removeTabAt(int position) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void removeAllTabs() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public void selectTab(ActionBar.Tab tab) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public ActionBar.Tab getSelectedTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public ActionBar.Tab getTabAt(int index) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override // android.support.p004v7.app.ActionBar
    public int getTabCount() {
        return 0;
    }

    @Override // android.support.p004v7.app.ActionBar
    public int getHeight() {
        return this.mDecorToolbar.getHeight();
    }

    @Override // android.support.p004v7.app.ActionBar
    public void show() {
        this.mDecorToolbar.setVisibility(0);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void hide() {
        this.mDecorToolbar.setVisibility(8);
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean isShowing() {
        return this.mDecorToolbar.getVisibility() == 0;
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean openOptionsMenu() {
        return this.mDecorToolbar.showOverflowMenu();
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean collapseActionView() {
        if (this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            return true;
        }
        return false;
    }

    void populateOptionsMenu() {
        Menu menu = getMenu();
        MenuBuilder mb = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (mb != null) {
            mb.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!this.mWindowCallback.onCreatePanelMenu(0, menu) || !this.mWindowCallback.onPreparePanel(0, null, menu)) {
                menu.clear();
            }
        } finally {
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
        }
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean onMenuKeyEvent(KeyEvent event) {
        if (event.getAction() == 1) {
            openOptionsMenu();
        }
        return true;
    }

    @Override // android.support.p004v7.app.ActionBar
    public boolean onKeyShortcut(int keyCode, KeyEvent ev) {
        Menu menu = getMenu();
        if (menu == null) {
            return false;
        }
        KeyCharacterMap kmap = KeyCharacterMap.load(ev != null ? ev.getDeviceId() : -1);
        menu.setQwertyMode(kmap.getKeyboardType() != 1);
        return menu.performShortcut(keyCode, ev, 0);
    }

    @Override // android.support.p004v7.app.ActionBar
    void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        this.mMenuVisibilityListeners.add(listener);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener listener) {
        this.mMenuVisibilityListeners.remove(listener);
    }

    @Override // android.support.p004v7.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean isVisible) {
        if (isVisible == this.mLastMenuVisibility) {
            return;
        }
        this.mLastMenuVisibility = isVisible;
        int count = this.mMenuVisibilityListeners.size();
        for (int i = 0; i < count; i++) {
            this.mMenuVisibilityListeners.get(i).onMenuVisibilityChanged(isVisible);
        }
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$ToolbarCallbackWrapper */
    /* loaded from: classes.dex */
    private class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(Window.Callback wrapped) {
            super(wrapped);
        }

        @Override // android.support.p004v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean onPreparePanel(int featureId, View view, Menu menu) {
            boolean result = super.onPreparePanel(featureId, view, menu);
            if (result && !ToolbarActionBar.this.mToolbarMenuPrepared) {
                ToolbarActionBar.this.mDecorToolbar.setMenuPrepared();
                ToolbarActionBar.this.mToolbarMenuPrepared = true;
            }
            return result;
        }

        @Override // android.support.p004v7.view.WindowCallbackWrapper, android.view.Window.Callback
        public View onCreatePanelView(int featureId) {
            if (featureId == 0) {
                return new View(ToolbarActionBar.this.mDecorToolbar.getContext());
            }
            return super.onCreatePanelView(featureId);
        }
    }

    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$ActionMenuPresenterCallback */
    /* loaded from: classes.dex */
    private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private boolean mClosingActionMenu;

        ActionMenuPresenterCallback() {
        }

        @Override // android.support.p004v7.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, subMenu);
                return true;
            }
            return false;
        }

        @Override // android.support.p004v7.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            if (this.mClosingActionMenu) {
                return;
            }
            this.mClosingActionMenu = true;
            ToolbarActionBar.this.mDecorToolbar.dismissPopupMenus();
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menu);
            }
            this.mClosingActionMenu = false;
        }
    }

    /* renamed from: android.support.v7.app.ToolbarActionBar$MenuBuilderCallback */
    /* loaded from: classes.dex */
    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        @Override // android.support.p004v7.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            return false;
        }

        @Override // android.support.p004v7.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menu) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                if (ToolbarActionBar.this.mDecorToolbar.isOverflowMenuShowing()) {
                    ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menu);
                } else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, menu)) {
                    ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menu);
                }
            }
        }
    }
}
