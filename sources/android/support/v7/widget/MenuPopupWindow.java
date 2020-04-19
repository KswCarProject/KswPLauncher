package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    private static final String TAG = "MenuPopupWindow";
    private static Method sSetTouchModalMethod;
    private MenuItemHoverListener mHoverListener;

    static {
        Class<PopupWindow> cls = PopupWindow.class;
        try {
            sSetTouchModalMethod = cls.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
        } catch (NoSuchMethodException e) {
            Log.i(TAG, "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public MenuPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /* access modifiers changed from: package-private */
    public DropDownListView createDropDownListView(Context context, boolean hijackFocus) {
        MenuDropDownListView view = new MenuDropDownListView(context, hijackFocus);
        view.setHoverListener(this);
        return view;
    }

    public void setEnterTransition(Object enterTransition) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPopup.setEnterTransition((Transition) enterTransition);
        }
    }

    public void setExitTransition(Object exitTransition) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mPopup.setExitTransition((Transition) exitTransition);
        }
    }

    public void setHoverListener(MenuItemHoverListener hoverListener) {
        this.mHoverListener = hoverListener;
    }

    public void setTouchModal(boolean touchModal) {
        if (sSetTouchModalMethod != null) {
            try {
                sSetTouchModalMethod.invoke(this.mPopup, new Object[]{Boolean.valueOf(touchModal)});
            } catch (Exception e) {
                Log.i(TAG, "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public void onItemHoverEnter(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverEnter(menu, item);
        }
    }

    public void onItemHoverExit(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
        if (this.mHoverListener != null) {
            this.mHoverListener.onItemHoverExit(menu, item);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public /* bridge */ /* synthetic */ int lookForSelectablePosition(int i, boolean z) {
            return super.lookForSelectablePosition(i, z);
        }

        public /* bridge */ /* synthetic */ int measureHeightOfChildrenCompat(int i, int i2, int i3, int i4, int i5) {
            return super.measureHeightOfChildrenCompat(i, i2, i3, i4, i5);
        }

        public /* bridge */ /* synthetic */ boolean onForwardedEvent(MotionEvent motionEvent, int i) {
            return super.onForwardedEvent(motionEvent, i);
        }

        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }

        public MenuDropDownListView(Context context, boolean hijackFocus) {
            super(context, hijackFocus);
            Configuration config = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != config.getLayoutDirection()) {
                this.mAdvanceKey = 22;
                this.mRetreatKey = 21;
                return;
            }
            this.mAdvanceKey = 21;
            this.mRetreatKey = 22;
        }

        public void setHoverListener(MenuItemHoverListener hoverListener) {
            this.mHoverListener = hoverListener;
        }

        public void clearSelection() {
            setSelection(-1);
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            ListMenuItemView selectedItem = (ListMenuItemView) getSelectedView();
            if (selectedItem != null && keyCode == this.mAdvanceKey) {
                if (selectedItem.isEnabled() && selectedItem.getItemData().hasSubMenu()) {
                    performItemClick(selectedItem, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (selectedItem == null || keyCode != this.mRetreatKey) {
                return super.onKeyDown(keyCode, event);
            } else {
                setSelection(-1);
                ((MenuAdapter) getAdapter()).getAdapterMenu().close(false);
                return true;
            }
        }

        /* JADX WARNING: type inference failed for: r3v3, types: [android.widget.ListAdapter] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onHoverEvent(android.view.MotionEvent r8) {
            /*
                r7 = this;
                android.support.v7.widget.MenuItemHoverListener r0 = r7.mHoverListener
                if (r0 == 0) goto L_0x005f
                android.widget.ListAdapter r0 = r7.getAdapter()
                boolean r1 = r0 instanceof android.widget.HeaderViewListAdapter
                if (r1 == 0) goto L_0x001b
                r1 = r0
                android.widget.HeaderViewListAdapter r1 = (android.widget.HeaderViewListAdapter) r1
                int r2 = r1.getHeadersCount()
                android.widget.ListAdapter r3 = r1.getWrappedAdapter()
                r1 = r3
                android.support.v7.view.menu.MenuAdapter r1 = (android.support.v7.view.menu.MenuAdapter) r1
                goto L_0x001f
            L_0x001b:
                r2 = 0
                r1 = r0
                android.support.v7.view.menu.MenuAdapter r1 = (android.support.v7.view.menu.MenuAdapter) r1
            L_0x001f:
                r3 = 0
                int r4 = r8.getAction()
                r5 = 10
                if (r4 == r5) goto L_0x0047
                float r4 = r8.getX()
                int r4 = (int) r4
                float r5 = r8.getY()
                int r5 = (int) r5
                int r4 = r7.pointToPosition(r4, r5)
                r5 = -1
                if (r4 == r5) goto L_0x0047
                int r5 = r4 - r2
                if (r5 < 0) goto L_0x0047
                int r6 = r1.getCount()
                if (r5 >= r6) goto L_0x0047
                android.support.v7.view.menu.MenuItemImpl r3 = r1.getItem((int) r5)
            L_0x0047:
                android.view.MenuItem r4 = r7.mHoveredMenuItem
                if (r4 == r3) goto L_0x005f
                android.support.v7.view.menu.MenuBuilder r5 = r1.getAdapterMenu()
                if (r4 == 0) goto L_0x0056
                android.support.v7.widget.MenuItemHoverListener r6 = r7.mHoverListener
                r6.onItemHoverExit(r5, r4)
            L_0x0056:
                r7.mHoveredMenuItem = r3
                if (r3 == 0) goto L_0x005f
                android.support.v7.widget.MenuItemHoverListener r6 = r7.mHoverListener
                r6.onItemHoverEnter(r5, r3)
            L_0x005f:
                boolean r0 = super.onHoverEvent(r8)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.MenuPopupWindow.MenuDropDownListView.onHoverEvent(android.view.MotionEvent):boolean");
        }
    }
}
