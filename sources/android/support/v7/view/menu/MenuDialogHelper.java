package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.MenuPresenter;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {
    private AlertDialog mDialog;
    private MenuBuilder mMenu;
    ListMenuPresenter mPresenter;
    private MenuPresenter.Callback mPresenterCallback;

    public MenuDialogHelper(MenuBuilder menu) {
        this.mMenu = menu;
    }

    public void show(IBinder windowToken) {
        MenuBuilder menu = this.mMenu;
        AlertDialog.Builder builder = new AlertDialog.Builder(menu.getContext());
        this.mPresenter = new ListMenuPresenter(builder.getContext(), R.layout.abc_list_menu_item_layout);
        this.mPresenter.setCallback(this);
        this.mMenu.addMenuPresenter(this.mPresenter);
        builder.setAdapter(this.mPresenter.getAdapter(), this);
        View headerView = menu.getHeaderView();
        if (headerView != null) {
            builder.setCustomTitle(headerView);
        } else {
            builder.setIcon(menu.getHeaderIcon()).setTitle(menu.getHeaderTitle());
        }
        builder.setOnKeyListener(this);
        this.mDialog = builder.create();
        this.mDialog.setOnDismissListener(this);
        WindowManager.LayoutParams lp = this.mDialog.getWindow().getAttributes();
        lp.type = PointerIconCompat.TYPE_HELP;
        if (windowToken != null) {
            lp.token = windowToken;
        }
        lp.flags |= 131072;
        this.mDialog.show();
    }

    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        Window win;
        View decor;
        KeyEvent.DispatcherState ds;
        View decor2;
        KeyEvent.DispatcherState ds2;
        if (keyCode == 82 || keyCode == 4) {
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                Window win2 = this.mDialog.getWindow();
                if (!(win2 == null || (decor2 = win2.getDecorView()) == null || (ds2 = decor2.getKeyDispatcherState()) == null)) {
                    ds2.startTracking(event, this);
                    return true;
                }
            } else if (event.getAction() == 1 && !event.isCanceled() && (win = this.mDialog.getWindow()) != null && (decor = win.getDecorView()) != null && (ds = decor.getKeyDispatcherState()) != null && ds.isTracking(event)) {
                this.mMenu.close(true);
                dialog.dismiss();
                return true;
            }
        }
        return this.mMenu.performShortcut(keyCode, event, 0);
    }

    public void setPresenterCallback(MenuPresenter.Callback cb) {
        this.mPresenterCallback = cb;
    }

    public void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialog) {
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        if (allMenusAreClosing || menu == this.mMenu) {
            dismiss();
        }
        if (this.mPresenterCallback != null) {
            this.mPresenterCallback.onCloseMenu(menu, allMenusAreClosing);
        }
    }

    public boolean onOpenSubMenu(MenuBuilder subMenu) {
        if (this.mPresenterCallback != null) {
            return this.mPresenterCallback.onOpenSubMenu(subMenu);
        }
        return false;
    }

    public void onClick(DialogInterface dialog, int which) {
        this.mMenu.performItemAction((MenuItemImpl) this.mPresenter.getAdapter().getItem(which), 0);
    }
}
