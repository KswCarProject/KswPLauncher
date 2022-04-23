package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.GravityCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuView;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
    }

    public void initForMenu(Context context, MenuBuilder menu) {
        super.initForMenu(context, menu);
        Resources res = context.getResources();
        ActionBarPolicy abp = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = abp.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = abp.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = abp.getMaxActionButtons();
        }
        int width = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
                if (this.mPendingOverflowIconSet) {
                    overflowMenuButton.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int spec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(spec, spec);
            }
            width -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = width;
        this.mMinCellSize = (int) (res.getDisplayMetrics().density * 56.0f);
        this.mScrapActionButtonView = null;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }
        if (this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    public void setWidthLimit(int width, boolean strict) {
        this.mWidthLimit = width;
        this.mStrictWidthLimit = strict;
        this.mWidthLimitSet = true;
    }

    public void setReserveOverflow(boolean reserveOverflow) {
        this.mReserveOverflow = reserveOverflow;
        this.mReserveOverflowSet = true;
    }

    public void setItemLimit(int itemCount) {
        this.mMaxItems = itemCount;
        this.mMaxItemsSet = true;
    }

    public void setExpandedActionViewsExclusive(boolean isExclusive) {
        this.mExpandedActionViewsExclusive = isExclusive;
    }

    public void setOverflowIcon(Drawable icon) {
        OverflowMenuButton overflowMenuButton = this.mOverflowButton;
        if (overflowMenuButton != null) {
            overflowMenuButton.setImageDrawable(icon);
            return;
        }
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = icon;
    }

    public Drawable getOverflowIcon() {
        OverflowMenuButton overflowMenuButton = this.mOverflowButton;
        if (overflowMenuButton != null) {
            return overflowMenuButton.getDrawable();
        }
        if (this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }
        return null;
    }

    public MenuView getMenuView(ViewGroup root) {
        MenuView oldMenuView = this.mMenuView;
        MenuView result = super.getMenuView(root);
        if (oldMenuView != result) {
            ((ActionMenuView) result).setPresenter(this);
        }
        return result;
    }

    public View getItemView(MenuItemImpl item, View convertView, ViewGroup parent) {
        View actionView = item.getActionView();
        if (actionView == null || item.hasCollapsibleActionView()) {
            actionView = super.getItemView(item, convertView, parent);
        }
        actionView.setVisibility(item.isActionViewExpanded() ? 8 : 0);
        ActionMenuView menuParent = (ActionMenuView) parent;
        ViewGroup.LayoutParams lp = actionView.getLayoutParams();
        if (!menuParent.checkLayoutParams(lp)) {
            actionView.setLayoutParams(menuParent.generateLayoutParams(lp));
        }
        return actionView;
    }

    public void bindItemView(MenuItemImpl item, MenuView.ItemView itemView) {
        itemView.initialize(item, 0);
        ActionMenuItemView actionItemView = (ActionMenuItemView) itemView;
        actionItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback();
        }
        actionItemView.setPopupCallback(this.mPopupCallback);
    }

    public boolean shouldIncludeItem(int childIndex, MenuItemImpl item) {
        return item.isActionButton();
    }

    public void updateMenuView(boolean cleared) {
        super.updateMenuView(cleared);
        ((View) this.mMenuView).requestLayout();
        if (this.mMenu != null) {
            ArrayList<MenuItemImpl> actionItems = this.mMenu.getActionItems();
            int count = actionItems.size();
            for (int i = 0; i < count; i++) {
                ActionProvider provider = actionItems.get(i).getSupportActionProvider();
                if (provider != null) {
                    provider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList<MenuItemImpl> nonActionItems = this.mMenu != null ? this.mMenu.getNonActionItems() : null;
        boolean hasOverflow = false;
        if (this.mReserveOverflow && nonActionItems != null) {
            int count2 = nonActionItems.size();
            boolean z = false;
            if (count2 == 1) {
                hasOverflow = !nonActionItems.get(0).isActionViewExpanded();
            } else {
                if (count2 > 0) {
                    z = true;
                }
                hasOverflow = z;
            }
        }
        if (hasOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
            }
            ViewGroup parent = (ViewGroup) this.mOverflowButton.getParent();
            if (parent != this.mMenuView) {
                if (parent != null) {
                    parent.removeView(this.mOverflowButton);
                }
                ActionMenuView menuView = (ActionMenuView) this.mMenuView;
                menuView.addView(this.mOverflowButton, menuView.generateOverflowButtonLayoutParams());
            }
        } else {
            OverflowMenuButton overflowMenuButton = this.mOverflowButton;
            if (overflowMenuButton != null && overflowMenuButton.getParent() == this.mMenuView) {
                ((ViewGroup) this.mMenuView).removeView(this.mOverflowButton);
            }
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }

    public boolean filterLeftoverView(ViewGroup parent, int childIndex) {
        if (parent.getChildAt(childIndex) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(parent, childIndex);
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.view.Menu] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onSubMenuSelected(android.support.v7.view.menu.SubMenuBuilder r8) {
        /*
            r7 = this;
            boolean r0 = r8.hasVisibleItems()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = r8
        L_0x0009:
            android.view.Menu r2 = r0.getParentMenu()
            android.support.v7.view.menu.MenuBuilder r3 = r7.mMenu
            if (r2 == r3) goto L_0x0019
            android.view.Menu r2 = r0.getParentMenu()
            r0 = r2
            android.support.v7.view.menu.SubMenuBuilder r0 = (android.support.v7.view.menu.SubMenuBuilder) r0
            goto L_0x0009
        L_0x0019:
            android.view.MenuItem r2 = r0.getItem()
            android.view.View r2 = r7.findViewForItem(r2)
            if (r2 != 0) goto L_0x0024
            return r1
        L_0x0024:
            android.view.MenuItem r1 = r8.getItem()
            int r1 = r1.getItemId()
            r7.mOpenSubMenuId = r1
            r1 = 0
            int r3 = r8.size()
            r4 = 0
        L_0x0034:
            if (r4 >= r3) goto L_0x004b
            android.view.MenuItem r5 = r8.getItem(r4)
            boolean r6 = r5.isVisible()
            if (r6 == 0) goto L_0x0048
            android.graphics.drawable.Drawable r6 = r5.getIcon()
            if (r6 == 0) goto L_0x0048
            r1 = 1
            goto L_0x004b
        L_0x0048:
            int r4 = r4 + 1
            goto L_0x0034
        L_0x004b:
            android.support.v7.widget.ActionMenuPresenter$ActionButtonSubmenu r4 = new android.support.v7.widget.ActionMenuPresenter$ActionButtonSubmenu
            android.content.Context r5 = r7.mContext
            r4.<init>(r5, r8, r2)
            r7.mActionButtonPopup = r4
            r4.setForceShowIcon(r1)
            android.support.v7.widget.ActionMenuPresenter$ActionButtonSubmenu r4 = r7.mActionButtonPopup
            r4.show()
            super.onSubMenuSelected(r8)
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuPresenter.onSubMenuSelected(android.support.v7.view.menu.SubMenuBuilder):boolean");
    }

    private View findViewForItem(MenuItem item) {
        ViewGroup parent = (ViewGroup) this.mMenuView;
        if (parent == null) {
            return null;
        }
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            if ((child instanceof MenuView.ItemView) && ((MenuView.ItemView) child).getItemData() == item) {
                return child;
            }
        }
        return null;
    }

    public boolean showOverflowMenu() {
        if (!this.mReserveOverflow || isOverflowMenuShowing() || this.mMenu == null || this.mMenuView == null || this.mPostedOpenRunnable != null || this.mMenu.getNonActionItems().isEmpty()) {
            return false;
        }
        this.mPostedOpenRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
        ((View) this.mMenuView).post(this.mPostedOpenRunnable);
        super.onSubMenuSelected((SubMenuBuilder) null);
        return true;
    }

    public boolean hideOverflowMenu() {
        if (this.mPostedOpenRunnable == null || this.mMenuView == null) {
            MenuPopupHelper popup = this.mOverflowPopup;
            if (popup == null) {
                return false;
            }
            popup.dismiss();
            return true;
        }
        ((View) this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean dismissPopupMenus() {
        return hideOverflowMenu() | hideSubMenus();
    }

    public boolean hideSubMenus() {
        ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
        if (actionButtonSubmenu == null) {
            return false;
        }
        actionButtonSubmenu.dismiss();
        return true;
    }

    public boolean isOverflowMenuShowing() {
        OverflowPopup overflowPopup = this.mOverflowPopup;
        return overflowPopup != null && overflowPopup.isShowing();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0173  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean flagActionItems() {
        /*
            r24 = this;
            r0 = r24
            android.support.v7.view.menu.MenuBuilder r1 = r0.mMenu
            if (r1 == 0) goto L_0x0011
            android.support.v7.view.menu.MenuBuilder r1 = r0.mMenu
            java.util.ArrayList r1 = r1.getVisibleItems()
            int r2 = r1.size()
            goto L_0x0013
        L_0x0011:
            r1 = 0
            r2 = 0
        L_0x0013:
            int r3 = r0.mMaxItems
            int r4 = r0.mActionItemWidthLimit
            r5 = 0
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r5, r5)
            android.support.v7.view.menu.MenuView r7 = r0.mMenuView
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x0025:
            if (r12 >= r2) goto L_0x004e
            java.lang.Object r13 = r1.get(r12)
            android.support.v7.view.menu.MenuItemImpl r13 = (android.support.v7.view.menu.MenuItemImpl) r13
            boolean r14 = r13.requiresActionButton()
            if (r14 == 0) goto L_0x0036
            int r8 = r8 + 1
            goto L_0x0040
        L_0x0036:
            boolean r14 = r13.requestsActionButton()
            if (r14 == 0) goto L_0x003f
            int r9 = r9 + 1
            goto L_0x0040
        L_0x003f:
            r11 = 1
        L_0x0040:
            boolean r14 = r0.mExpandedActionViewsExclusive
            if (r14 == 0) goto L_0x004b
            boolean r14 = r13.isActionViewExpanded()
            if (r14 == 0) goto L_0x004b
            r3 = 0
        L_0x004b:
            int r12 = r12 + 1
            goto L_0x0025
        L_0x004e:
            boolean r12 = r0.mReserveOverflow
            if (r12 == 0) goto L_0x005a
            if (r11 != 0) goto L_0x0058
            int r12 = r8 + r9
            if (r12 <= r3) goto L_0x005a
        L_0x0058:
            int r3 = r3 + -1
        L_0x005a:
            int r3 = r3 - r8
            android.util.SparseBooleanArray r12 = r0.mActionButtonGroups
            r12.clear()
            r13 = 0
            r14 = 0
            boolean r15 = r0.mStrictWidthLimit
            if (r15 == 0) goto L_0x0070
            int r15 = r0.mMinCellSize
            int r14 = r4 / r15
            int r16 = r4 % r15
            int r17 = r16 / r14
            int r13 = r15 + r17
        L_0x0070:
            r15 = 0
        L_0x0071:
            if (r15 >= r2) goto L_0x0199
            java.lang.Object r17 = r1.get(r15)
            r5 = r17
            android.support.v7.view.menu.MenuItemImpl r5 = (android.support.v7.view.menu.MenuItemImpl) r5
            boolean r17 = r5.requiresActionButton()
            if (r17 == 0) goto L_0x00c6
            r17 = r2
            android.view.View r2 = r0.mScrapActionButtonView
            android.view.View r2 = r0.getItemView(r5, r2, r7)
            r19 = r8
            android.view.View r8 = r0.mScrapActionButtonView
            if (r8 != 0) goto L_0x0091
            r0.mScrapActionButtonView = r2
        L_0x0091:
            boolean r8 = r0.mStrictWidthLimit
            if (r8 == 0) goto L_0x009d
            r8 = 0
            int r20 = android.support.v7.widget.ActionMenuView.measureChildForCells(r2, r13, r14, r6, r8)
            int r14 = r14 - r20
            goto L_0x00a0
        L_0x009d:
            r2.measure(r6, r6)
        L_0x00a0:
            int r8 = r2.getMeasuredWidth()
            int r4 = r4 - r8
            if (r10 != 0) goto L_0x00a8
            r10 = r8
        L_0x00a8:
            r20 = r2
            int r2 = r5.getGroupId()
            if (r2 == 0) goto L_0x00b7
            r21 = r4
            r4 = 1
            r12.put(r2, r4)
            goto L_0x00ba
        L_0x00b7:
            r21 = r4
            r4 = 1
        L_0x00ba:
            r5.setIsActionButton(r4)
            r22 = r7
            r4 = r21
            r0 = 0
            r21 = r1
            goto L_0x018a
        L_0x00c6:
            r17 = r2
            r19 = r8
            boolean r2 = r5.requestsActionButton()
            if (r2 == 0) goto L_0x0180
            int r2 = r5.getGroupId()
            boolean r8 = r12.get(r2)
            if (r3 > 0) goto L_0x00e0
            if (r8 == 0) goto L_0x00dd
            goto L_0x00e0
        L_0x00dd:
            r20 = r3
            goto L_0x00ee
        L_0x00e0:
            if (r4 <= 0) goto L_0x00ec
            r20 = r3
            boolean r3 = r0.mStrictWidthLimit
            if (r3 == 0) goto L_0x00ea
            if (r14 <= 0) goto L_0x00ee
        L_0x00ea:
            r3 = 1
            goto L_0x00ef
        L_0x00ec:
            r20 = r3
        L_0x00ee:
            r3 = 0
        L_0x00ef:
            if (r3 == 0) goto L_0x0135
            r21 = r3
            android.view.View r3 = r0.mScrapActionButtonView
            android.view.View r3 = r0.getItemView(r5, r3, r7)
            r22 = r7
            android.view.View r7 = r0.mScrapActionButtonView
            if (r7 != 0) goto L_0x0101
            r0.mScrapActionButtonView = r3
        L_0x0101:
            boolean r7 = r0.mStrictWidthLimit
            if (r7 == 0) goto L_0x0112
            r7 = 0
            int r23 = android.support.v7.widget.ActionMenuView.measureChildForCells(r3, r13, r14, r6, r7)
            int r14 = r14 - r23
            if (r23 != 0) goto L_0x0111
            r7 = 0
            r21 = r7
        L_0x0111:
            goto L_0x0115
        L_0x0112:
            r3.measure(r6, r6)
        L_0x0115:
            int r7 = r3.getMeasuredWidth()
            int r4 = r4 - r7
            if (r10 != 0) goto L_0x011d
            r10 = r7
        L_0x011d:
            r23 = r3
            boolean r3 = r0.mStrictWidthLimit
            if (r3 == 0) goto L_0x012b
            if (r4 < 0) goto L_0x0127
            r3 = 1
            goto L_0x0128
        L_0x0127:
            r3 = 0
        L_0x0128:
            r3 = r21 & r3
            goto L_0x0139
        L_0x012b:
            int r3 = r4 + r10
            if (r3 <= 0) goto L_0x0131
            r3 = 1
            goto L_0x0132
        L_0x0131:
            r3 = 0
        L_0x0132:
            r3 = r21 & r3
            goto L_0x0139
        L_0x0135:
            r21 = r3
            r22 = r7
        L_0x0139:
            if (r3 == 0) goto L_0x0144
            if (r2 == 0) goto L_0x0144
            r7 = 1
            r12.put(r2, r7)
            r21 = r1
            goto L_0x0175
        L_0x0144:
            if (r8 == 0) goto L_0x0173
            r7 = 0
            r12.put(r2, r7)
            r7 = 0
        L_0x014b:
            if (r7 >= r15) goto L_0x0170
            java.lang.Object r18 = r1.get(r7)
            r0 = r18
            android.support.v7.view.menu.MenuItemImpl r0 = (android.support.v7.view.menu.MenuItemImpl) r0
            r21 = r1
            int r1 = r0.getGroupId()
            if (r1 != r2) goto L_0x0169
            boolean r1 = r0.isActionButton()
            if (r1 == 0) goto L_0x0165
            int r20 = r20 + 1
        L_0x0165:
            r1 = 0
            r0.setIsActionButton(r1)
        L_0x0169:
            int r7 = r7 + 1
            r0 = r24
            r1 = r21
            goto L_0x014b
        L_0x0170:
            r21 = r1
            goto L_0x0175
        L_0x0173:
            r21 = r1
        L_0x0175:
            if (r3 == 0) goto L_0x0179
            int r20 = r20 + -1
        L_0x0179:
            r5.setIsActionButton(r3)
            r3 = r20
            r0 = 0
            goto L_0x018a
        L_0x0180:
            r21 = r1
            r20 = r3
            r22 = r7
            r0 = 0
            r5.setIsActionButton(r0)
        L_0x018a:
            int r15 = r15 + 1
            r5 = r0
            r2 = r17
            r8 = r19
            r1 = r21
            r7 = r22
            r0 = r24
            goto L_0x0071
        L_0x0199:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionMenuPresenter.flagActionItems():boolean");
    }

    public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        dismissPopupMenus();
        super.onCloseMenu(menu, allMenusAreClosing);
    }

    public Parcelable onSaveInstanceState() {
        SavedState state = new SavedState();
        state.openSubMenuId = this.mOpenSubMenuId;
        return state;
    }

    public void onRestoreInstanceState(Parcelable state) {
        MenuItem item;
        if (state instanceof SavedState) {
            SavedState saved = (SavedState) state;
            if (saved.openSubMenuId > 0 && (item = this.mMenu.findItem(saved.openSubMenuId)) != null) {
                onSubMenuSelected((SubMenuBuilder) item.getSubMenu());
            }
        }
    }

    public void onSubUiVisibilityChanged(boolean isVisible) {
        if (isVisible) {
            super.onSubMenuSelected((SubMenuBuilder) null);
        } else if (this.mMenu != null) {
            this.mMenu.close(false);
        }
    }

    public void setMenuView(ActionMenuView menuView) {
        this.mMenuView = menuView;
        menuView.initialize(this.mMenu);
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        public int openSubMenuId;

        SavedState() {
        }

        SavedState(Parcel in) {
            this.openSubMenuId = in.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.openSubMenuId);
        }
    }

    private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        private final float[] mTempPts = new float[2];

        public OverflowMenuButton(Context context) {
            super(context, (AttributeSet) null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat.setTooltipText(this, getContentDescription());
            setOnTouchListener(new ForwardingListener(this, ActionMenuPresenter.this) {
                public ShowableListMenu getPopup() {
                    if (ActionMenuPresenter.this.mOverflowPopup == null) {
                        return null;
                    }
                    return ActionMenuPresenter.this.mOverflowPopup.getPopup();
                }

                public boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                public boolean onForwardingStopped() {
                    if (ActionMenuPresenter.this.mPostedOpenRunnable != null) {
                        return false;
                    }
                    ActionMenuPresenter.this.hideOverflowMenu();
                    return true;
                }
            });
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public boolean needsDividerAfter() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean setFrame(int l, int t, int r, int b) {
            boolean changed = super.setFrame(l, t, r, b);
            Drawable d = getDrawable();
            Drawable bg = getBackground();
            if (!(d == null || bg == null)) {
                int width = getWidth();
                int height = getHeight();
                int halfEdge = Math.max(width, height) / 2;
                int offsetX = getPaddingLeft() - getPaddingRight();
                int centerX = (width + offsetX) / 2;
                int centerY = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(bg, centerX - halfEdge, centerY - halfEdge, centerX + halfEdge, centerY + halfEdge);
            }
            return changed;
        }
    }

    private class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menu, View anchorView, boolean overflowOnly) {
            super(context, menu, anchorView, overflowOnly, R.attr.actionOverflowMenuStyle);
            setGravity(GravityCompat.END);
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        /* access modifiers changed from: protected */
        public void onDismiss() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.close();
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    private class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenu, View anchorView) {
            super(context, subMenu, anchorView, false, R.attr.actionOverflowMenuStyle);
            if (!((MenuItemImpl) subMenu.getItem()).isActionButton()) {
                setAnchorView(ActionMenuPresenter.this.mOverflowButton == null ? (View) ActionMenuPresenter.this.mMenuView : ActionMenuPresenter.this.mOverflowButton);
            }
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        /* access modifiers changed from: protected */
        public void onDismiss() {
            ActionMenuPresenter.this.mActionButtonPopup = null;
            ActionMenuPresenter.this.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    private class PopupPresenterCallback implements MenuPresenter.Callback {
        PopupPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            if (subMenu == null) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder) subMenu).getItem().getItemId();
            MenuPresenter.Callback cb = ActionMenuPresenter.this.getCallback();
            if (cb != null) {
                return cb.onOpenSubMenu(subMenu);
            }
            return false;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            if (menu instanceof SubMenuBuilder) {
                menu.getRootMenu().close(false);
            }
            MenuPresenter.Callback cb = ActionMenuPresenter.this.getCallback();
            if (cb != null) {
                cb.onCloseMenu(menu, allMenusAreClosing);
            }
        }
    }

    private class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup popup) {
            this.mPopup = popup;
        }

        public void run() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }
            View menuView = (View) ActionMenuPresenter.this.mMenuView;
            if (!(menuView == null || menuView.getWindowToken() == null || !this.mPopup.tryShow())) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        ActionMenuPopupCallback() {
        }

        public ShowableListMenu getPopup() {
            if (ActionMenuPresenter.this.mActionButtonPopup != null) {
                return ActionMenuPresenter.this.mActionButtonPopup.getPopup();
            }
            return null;
        }
    }
}
