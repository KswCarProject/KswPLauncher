package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder implements SupportMenu {
    private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
    private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
    private static final String PRESENTER_KEY = "android:menu:presenters";
    private static final String TAG = "MenuBuilder";
    private static final int[] sCategoryToOrder = {1, 4, 5, 3, 2, 0};
    private ArrayList<MenuItemImpl> mActionItems;
    private Callback mCallback;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private int mDefaultShowAsAction = 0;
    private MenuItemImpl mExpandedItem;
    private SparseArray<Parcelable> mFrozenViewStates;
    private boolean mGroupDividerEnabled = false;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private boolean mIsActionItemsStale;
    private boolean mIsClosing = false;
    private boolean mIsVisibleItemsStale;
    private ArrayList<MenuItemImpl> mItems;
    private boolean mItemsChangedWhileDispatchPrevented = false;
    private ArrayList<MenuItemImpl> mNonActionItems;
    private boolean mOptionalIconsVisible = false;
    private boolean mOverrideVisibleItems;
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> mPresenters = new CopyOnWriteArrayList<>();
    private boolean mPreventDispatchingItemsChanged = false;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private boolean mStructureChangedWhileDispatchPrevented = false;
    private ArrayList<MenuItemImpl> mTempShortcutItemList = new ArrayList<>();
    private ArrayList<MenuItemImpl> mVisibleItems;

    public interface Callback {
        boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem);

        void onMenuModeChange(MenuBuilder menuBuilder);
    }

    public interface ItemInvoker {
        boolean invokeItem(MenuItemImpl menuItemImpl);
    }

    public MenuBuilder(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mItems = new ArrayList<>();
        this.mVisibleItems = new ArrayList<>();
        this.mIsVisibleItemsStale = true;
        this.mActionItems = new ArrayList<>();
        this.mNonActionItems = new ArrayList<>();
        this.mIsActionItemsStale = true;
        setShortcutsVisibleInner(true);
    }

    public MenuBuilder setDefaultShowAsAction(int defaultShowAsAction) {
        this.mDefaultShowAsAction = defaultShowAsAction;
        return this;
    }

    public void addMenuPresenter(MenuPresenter presenter) {
        addMenuPresenter(presenter, this.mContext);
    }

    public void addMenuPresenter(MenuPresenter presenter, Context menuContext) {
        this.mPresenters.add(new WeakReference(presenter));
        presenter.initForMenu(menuContext, this);
        this.mIsActionItemsStale = true;
    }

    public void removeMenuPresenter(MenuPresenter presenter) {
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter item = (MenuPresenter) ref.get();
            if (item == null || item == presenter) {
                this.mPresenters.remove(ref);
            }
        }
    }

    private void dispatchPresenterUpdate(boolean cleared) {
        if (!this.mPresenters.isEmpty()) {
            stopDispatchingItemsChanged();
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    this.mPresenters.remove(ref);
                } else {
                    presenter.updateMenuView(cleared);
                }
            }
            startDispatchingItemsChanged();
        }
    }

    private boolean dispatchSubMenuSelected(SubMenuBuilder subMenu, MenuPresenter preferredPresenter) {
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        boolean result = false;
        if (preferredPresenter != null) {
            result = preferredPresenter.onSubMenuSelected(subMenu);
        }
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter presenter = (MenuPresenter) ref.get();
            if (presenter == null) {
                this.mPresenters.remove(ref);
            } else if (!result) {
                result = presenter.onSubMenuSelected(subMenu);
            }
        }
        return result;
    }

    private void dispatchSaveInstanceState(Bundle outState) {
        Parcelable state;
        if (!this.mPresenters.isEmpty()) {
            SparseArray<Parcelable> presenterStates = new SparseArray<>();
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    this.mPresenters.remove(ref);
                } else {
                    int id = presenter.getId();
                    if (id > 0 && (state = presenter.onSaveInstanceState()) != null) {
                        presenterStates.put(id, state);
                    }
                }
            }
            outState.putSparseParcelableArray(PRESENTER_KEY, presenterStates);
        }
    }

    private void dispatchRestoreInstanceState(Bundle state) {
        Parcelable parcel;
        SparseArray<Parcelable> presenterStates = state.getSparseParcelableArray(PRESENTER_KEY);
        if (presenterStates != null && !this.mPresenters.isEmpty()) {
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    this.mPresenters.remove(ref);
                } else {
                    int id = presenter.getId();
                    if (id > 0 && (parcel = presenterStates.get(id)) != null) {
                        presenter.onRestoreInstanceState(parcel);
                    }
                }
            }
        }
    }

    public void savePresenterStates(Bundle outState) {
        dispatchSaveInstanceState(outState);
    }

    public void restorePresenterStates(Bundle state) {
        dispatchRestoreInstanceState(state);
    }

    public void saveActionViewStates(Bundle outStates) {
        SparseArray<Parcelable> viewStates = null;
        int itemCount = size();
        for (int i = 0; i < itemCount; i++) {
            MenuItem item = getItem(i);
            View v = item.getActionView();
            if (!(v == null || v.getId() == -1)) {
                if (viewStates == null) {
                    viewStates = new SparseArray<>();
                }
                v.saveHierarchyState(viewStates);
                if (item.isActionViewExpanded()) {
                    outStates.putInt(EXPANDED_ACTION_VIEW_ID, item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).saveActionViewStates(outStates);
            }
        }
        if (viewStates != null) {
            outStates.putSparseParcelableArray(getActionViewStatesKey(), viewStates);
        }
    }

    public void restoreActionViewStates(Bundle states) {
        MenuItem itemToExpand;
        if (states != null) {
            SparseArray<Parcelable> viewStates = states.getSparseParcelableArray(getActionViewStatesKey());
            int itemCount = size();
            for (int i = 0; i < itemCount; i++) {
                MenuItem item = getItem(i);
                View v = item.getActionView();
                if (!(v == null || v.getId() == -1)) {
                    v.restoreHierarchyState(viewStates);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).restoreActionViewStates(states);
                }
            }
            int expandedId = states.getInt(EXPANDED_ACTION_VIEW_ID);
            if (expandedId > 0 && (itemToExpand = findItem(expandedId)) != null) {
                itemToExpand.expandActionView();
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getActionViewStatesKey() {
        return ACTION_VIEW_STATES_KEY;
    }

    public void setCallback(Callback cb) {
        this.mCallback = cb;
    }

    /* access modifiers changed from: protected */
    public MenuItem addInternal(int group, int id, int categoryOrder, CharSequence title) {
        int ordering = getOrdering(categoryOrder);
        MenuItemImpl item = createNewMenuItem(group, id, categoryOrder, ordering, title, this.mDefaultShowAsAction);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.mCurrentMenuInfo;
        if (contextMenuInfo != null) {
            item.setMenuInfo(contextMenuInfo);
        }
        ArrayList<MenuItemImpl> arrayList = this.mItems;
        arrayList.add(findInsertIndex(arrayList, ordering), item);
        onItemsChanged(true);
        return item;
    }

    private MenuItemImpl createNewMenuItem(int group, int id, int categoryOrder, int ordering, CharSequence title, int defaultShowAsAction) {
        return new MenuItemImpl(this, group, id, categoryOrder, ordering, title, defaultShowAsAction);
    }

    public MenuItem add(CharSequence title) {
        return addInternal(0, 0, 0, title);
    }

    public MenuItem add(int titleRes) {
        return addInternal(0, 0, 0, this.mResources.getString(titleRes));
    }

    public MenuItem add(int group, int id, int categoryOrder, CharSequence title) {
        return addInternal(group, id, categoryOrder, title);
    }

    public MenuItem add(int group, int id, int categoryOrder, int title) {
        return addInternal(group, id, categoryOrder, this.mResources.getString(title));
    }

    public SubMenu addSubMenu(CharSequence title) {
        return addSubMenu(0, 0, 0, title);
    }

    public SubMenu addSubMenu(int titleRes) {
        return addSubMenu(0, 0, 0, (CharSequence) this.mResources.getString(titleRes));
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, CharSequence title) {
        MenuItemImpl item = (MenuItemImpl) addInternal(group, id, categoryOrder, title);
        SubMenuBuilder subMenu = new SubMenuBuilder(this.mContext, this, item);
        item.setSubMenu(subMenu);
        return subMenu;
    }

    public SubMenu addSubMenu(int group, int id, int categoryOrder, int title) {
        return addSubMenu(group, id, categoryOrder, (CharSequence) this.mResources.getString(title));
    }

    public void setGroupDividerEnabled(boolean enabled) {
        this.mGroupDividerEnabled = enabled;
    }

    public boolean isGroupDividerEnabled() {
        return this.mGroupDividerEnabled;
    }

    public int addIntentOptions(int group, int id, int categoryOrder, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
        Intent[] intentArr = specifics;
        PackageManager pm = this.mContext.getPackageManager();
        int N = 0;
        Intent intent2 = intent;
        List<ResolveInfo> lri = pm.queryIntentActivityOptions(caller, intentArr, intent2, 0);
        if (lri != null) {
            N = lri.size();
        }
        if ((flags & 1) == 0) {
            removeGroup(group);
        }
        for (int i = 0; i < N; i++) {
            ResolveInfo ri = lri.get(i);
            Intent rintent = new Intent(ri.specificIndex < 0 ? intent2 : intentArr[ri.specificIndex]);
            rintent.setComponent(new ComponentName(ri.activityInfo.applicationInfo.packageName, ri.activityInfo.name));
            MenuItem item = add(group, id, categoryOrder, ri.loadLabel(pm)).setIcon(ri.loadIcon(pm)).setIntent(rintent);
            if (outSpecificItems != null && ri.specificIndex >= 0) {
                outSpecificItems[ri.specificIndex] = item;
            }
        }
        int i2 = group;
        int i3 = id;
        int i4 = categoryOrder;
        return N;
    }

    public void removeItem(int id) {
        removeItemAtInt(findItemIndex(id), true);
    }

    public void removeGroup(int group) {
        int i = findGroupIndex(group);
        if (i >= 0) {
            int maxRemovable = this.mItems.size() - i;
            int numRemoved = 0;
            while (true) {
                int numRemoved2 = numRemoved + 1;
                if (numRemoved >= maxRemovable || this.mItems.get(i).getGroupId() != group) {
                    onItemsChanged(true);
                } else {
                    removeItemAtInt(i, false);
                    numRemoved = numRemoved2;
                }
            }
            onItemsChanged(true);
        }
    }

    private void removeItemAtInt(int index, boolean updateChildrenOnMenuViews) {
        if (index >= 0 && index < this.mItems.size()) {
            this.mItems.remove(index);
            if (updateChildrenOnMenuViews) {
                onItemsChanged(true);
            }
        }
    }

    public void removeItemAt(int index) {
        removeItemAtInt(index, true);
    }

    public void clearAll() {
        this.mPreventDispatchingItemsChanged = true;
        clear();
        clearHeader();
        this.mPresenters.clear();
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
        onItemsChanged(true);
    }

    public void clear() {
        MenuItemImpl menuItemImpl = this.mExpandedItem;
        if (menuItemImpl != null) {
            collapseItemActionView(menuItemImpl);
        }
        this.mItems.clear();
        onItemsChanged(true);
    }

    /* access modifiers changed from: package-private */
    public void setExclusiveItemChecked(MenuItem item) {
        int group = item.getGroupId();
        int N = this.mItems.size();
        stopDispatchingItemsChanged();
        for (int i = 0; i < N; i++) {
            MenuItemImpl curItem = this.mItems.get(i);
            if (curItem.getGroupId() == group && curItem.isExclusiveCheckable() && curItem.isCheckable()) {
                curItem.setCheckedInt(curItem == item);
            }
        }
        startDispatchingItemsChanged();
    }

    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
        int N = this.mItems.size();
        for (int i = 0; i < N; i++) {
            MenuItemImpl item = this.mItems.get(i);
            if (item.getGroupId() == group) {
                item.setExclusiveCheckable(exclusive);
                item.setCheckable(checkable);
            }
        }
    }

    public void setGroupVisible(int group, boolean visible) {
        int N = this.mItems.size();
        boolean changedAtLeastOneItem = false;
        for (int i = 0; i < N; i++) {
            MenuItemImpl item = this.mItems.get(i);
            if (item.getGroupId() == group && item.setVisibleInt(visible)) {
                changedAtLeastOneItem = true;
            }
        }
        if (changedAtLeastOneItem) {
            onItemsChanged(true);
        }
    }

    public void setGroupEnabled(int group, boolean enabled) {
        int N = this.mItems.size();
        for (int i = 0; i < N; i++) {
            MenuItemImpl item = this.mItems.get(i);
            if (item.getGroupId() == group) {
                item.setEnabled(enabled);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.mOverrideVisibleItems) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.mItems.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int id) {
        MenuItem possibleItem;
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItemImpl item = this.mItems.get(i);
            if (item.getItemId() == id) {
                return item;
            }
            if (item.hasSubMenu() && (possibleItem = item.getSubMenu().findItem(id)) != null) {
                return possibleItem;
            }
        }
        return null;
    }

    public int findItemIndex(int id) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.mItems.get(i).getItemId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int findGroupIndex(int group) {
        return findGroupIndex(group, 0);
    }

    public int findGroupIndex(int group, int start) {
        int size = size();
        if (start < 0) {
            start = 0;
        }
        for (int i = start; i < size; i++) {
            if (this.mItems.get(i).getGroupId() == group) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return this.mItems.size();
    }

    public MenuItem getItem(int index) {
        return this.mItems.get(index);
    }

    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        return findItemWithShortcutForKey(keyCode, event) != null;
    }

    public void setQwertyMode(boolean isQwerty) {
        this.mQwertyMode = isQwerty;
        onItemsChanged(false);
    }

    private static int getOrdering(int categoryOrder) {
        int index = (-65536 & categoryOrder) >> 16;
        if (index >= 0) {
            int[] iArr = sCategoryToOrder;
            if (index < iArr.length) {
                return (iArr[index] << 16) | (65535 & categoryOrder);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    /* access modifiers changed from: package-private */
    public boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    public void setShortcutsVisible(boolean shortcutsVisible) {
        if (this.mShortcutsVisible != shortcutsVisible) {
            setShortcutsVisibleInner(shortcutsVisible);
            onItemsChanged(false);
        }
    }

    private void setShortcutsVisibleInner(boolean shortcutsVisible) {
        boolean z = true;
        if (!shortcutsVisible || this.mResources.getConfiguration().keyboard == 1 || !ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(this.mContext), this.mContext)) {
            z = false;
        }
        this.mShortcutsVisible = z;
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    /* access modifiers changed from: package-private */
    public Resources getResources() {
        return this.mResources;
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public boolean dispatchMenuItemSelected(MenuBuilder menu, MenuItem item) {
        Callback callback = this.mCallback;
        return callback != null && callback.onMenuItemSelected(menu, item);
    }

    public void changeMenuMode() {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onMenuModeChange(this);
        }
    }

    private static int findInsertIndex(ArrayList<MenuItemImpl> items, int ordering) {
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).getOrdering() <= ordering) {
                return i + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
        MenuItemImpl item = findItemWithShortcutForKey(keyCode, event);
        boolean handled = false;
        if (item != null) {
            handled = performItemAction(item, flags);
        }
        if ((flags & 2) != 0) {
            close(true);
        }
        return handled;
    }

    /* access modifiers changed from: package-private */
    public void findItemsWithShortcutForKey(List<MenuItemImpl> items, int keyCode, KeyEvent event) {
        List<MenuItemImpl> list = items;
        int i = keyCode;
        KeyEvent keyEvent = event;
        boolean qwerty = isQwertyMode();
        int modifierState = event.getModifiers();
        KeyCharacterMap.KeyData possibleChars = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(possibleChars) || i == 67) {
            int N = this.mItems.size();
            for (int i2 = 0; i2 < N; i2++) {
                MenuItemImpl item = this.mItems.get(i2);
                if (item.hasSubMenu()) {
                    ((MenuBuilder) item.getSubMenu()).findItemsWithShortcutForKey(list, i, keyEvent);
                }
                char shortcutChar = qwerty ? item.getAlphabeticShortcut() : item.getNumericShortcut();
                if (((modifierState & SupportMenu.SUPPORTED_MODIFIERS_MASK) == (69647 & (qwerty ? item.getAlphabeticModifiers() : item.getNumericModifiers()))) && shortcutChar != 0 && ((shortcutChar == possibleChars.meta[0] || shortcutChar == possibleChars.meta[2] || (qwerty && shortcutChar == 8 && i == 67)) && item.isEnabled())) {
                    list.add(item);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MenuItemImpl findItemWithShortcutForKey(int keyCode, KeyEvent event) {
        char shortcutChar;
        ArrayList<MenuItemImpl> items = this.mTempShortcutItemList;
        items.clear();
        findItemsWithShortcutForKey(items, keyCode, event);
        if (items.isEmpty()) {
            return null;
        }
        int metaState = event.getMetaState();
        KeyCharacterMap.KeyData possibleChars = new KeyCharacterMap.KeyData();
        event.getKeyData(possibleChars);
        int size = items.size();
        if (size == 1) {
            return items.get(0);
        }
        boolean qwerty = isQwertyMode();
        for (int i = 0; i < size; i++) {
            MenuItemImpl item = items.get(i);
            if (qwerty) {
                shortcutChar = item.getAlphabeticShortcut();
            } else {
                shortcutChar = item.getNumericShortcut();
            }
            if ((shortcutChar == possibleChars.meta[0] && (metaState & 2) == 0) || ((shortcutChar == possibleChars.meta[2] && (metaState & 2) != 0) || (qwerty && shortcutChar == 8 && keyCode == 67))) {
                return item;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int id, int flags) {
        return performItemAction(findItem(id), flags);
    }

    public boolean performItemAction(MenuItem item, int flags) {
        return performItemAction(item, (MenuPresenter) null, flags);
    }

    public boolean performItemAction(MenuItem item, MenuPresenter preferredPresenter, int flags) {
        MenuItemImpl itemImpl = (MenuItemImpl) item;
        if (itemImpl == null || !itemImpl.isEnabled()) {
            return false;
        }
        boolean invoked = itemImpl.invoke();
        ActionProvider provider = itemImpl.getSupportActionProvider();
        boolean providerHasSubMenu = provider != null && provider.hasSubMenu();
        if (itemImpl.hasCollapsibleActionView()) {
            invoked |= itemImpl.expandActionView();
            if (invoked) {
                close(true);
            }
        } else if (itemImpl.hasSubMenu() || providerHasSubMenu) {
            if ((flags & 4) == 0) {
                close(false);
            }
            if (!itemImpl.hasSubMenu()) {
                itemImpl.setSubMenu(new SubMenuBuilder(getContext(), this, itemImpl));
            }
            SubMenuBuilder subMenu = (SubMenuBuilder) itemImpl.getSubMenu();
            if (providerHasSubMenu) {
                provider.onPrepareSubMenu(subMenu);
            }
            invoked |= dispatchSubMenuSelected(subMenu, preferredPresenter);
            if (!invoked) {
                close(true);
            }
        } else if ((flags & 1) == 0) {
            close(true);
        }
        return invoked;
    }

    public final void close(boolean closeAllMenus) {
        if (!this.mIsClosing) {
            this.mIsClosing = true;
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    this.mPresenters.remove(ref);
                } else {
                    presenter.onCloseMenu(this, closeAllMenus);
                }
            }
            this.mIsClosing = false;
        }
    }

    public void close() {
        close(true);
    }

    public void onItemsChanged(boolean structureChanged) {
        if (!this.mPreventDispatchingItemsChanged) {
            if (structureChanged) {
                this.mIsVisibleItemsStale = true;
                this.mIsActionItemsStale = true;
            }
            dispatchPresenterUpdate(structureChanged);
            return;
        }
        this.mItemsChangedWhileDispatchPrevented = true;
        if (structureChanged) {
            this.mStructureChangedWhileDispatchPrevented = true;
        }
    }

    public void stopDispatchingItemsChanged() {
        if (!this.mPreventDispatchingItemsChanged) {
            this.mPreventDispatchingItemsChanged = true;
            this.mItemsChangedWhileDispatchPrevented = false;
            this.mStructureChangedWhileDispatchPrevented = false;
        }
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if (this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            onItemsChanged(this.mStructureChangedWhileDispatchPrevented);
        }
    }

    /* access modifiers changed from: package-private */
    public void onItemVisibleChanged(MenuItemImpl item) {
        this.mIsVisibleItemsStale = true;
        onItemsChanged(true);
    }

    /* access modifiers changed from: package-private */
    public void onItemActionRequestChanged(MenuItemImpl item) {
        this.mIsActionItemsStale = true;
        onItemsChanged(true);
    }

    public ArrayList<MenuItemImpl> getVisibleItems() {
        if (!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int itemsSize = this.mItems.size();
        for (int i = 0; i < itemsSize; i++) {
            MenuItemImpl item = this.mItems.get(i);
            if (item.isVisible()) {
                this.mVisibleItems.add(item);
            }
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    public void flagActionItems() {
        ArrayList<MenuItemImpl> visibleItems = getVisibleItems();
        if (this.mIsActionItemsStale) {
            boolean flagged = false;
            Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference<MenuPresenter> ref = it.next();
                MenuPresenter presenter = (MenuPresenter) ref.get();
                if (presenter == null) {
                    this.mPresenters.remove(ref);
                } else {
                    flagged |= presenter.flagActionItems();
                }
            }
            if (flagged) {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                int itemsSize = visibleItems.size();
                for (int i = 0; i < itemsSize; i++) {
                    MenuItemImpl item = visibleItems.get(i);
                    if (item.isActionButton()) {
                        this.mActionItems.add(item);
                    } else {
                        this.mNonActionItems.add(item);
                    }
                }
            } else {
                this.mActionItems.clear();
                this.mNonActionItems.clear();
                this.mNonActionItems.addAll(getVisibleItems());
            }
            this.mIsActionItemsStale = false;
        }
    }

    public ArrayList<MenuItemImpl> getActionItems() {
        flagActionItems();
        return this.mActionItems;
    }

    public ArrayList<MenuItemImpl> getNonActionItems() {
        flagActionItems();
        return this.mNonActionItems;
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        onItemsChanged(false);
    }

    private void setHeaderInternal(int titleRes, CharSequence title, int iconRes, Drawable icon, View view) {
        Resources r = getResources();
        if (view != null) {
            this.mHeaderView = view;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        } else {
            if (titleRes > 0) {
                this.mHeaderTitle = r.getText(titleRes);
            } else if (title != null) {
                this.mHeaderTitle = title;
            }
            if (iconRes > 0) {
                this.mHeaderIcon = ContextCompat.getDrawable(getContext(), iconRes);
            } else if (icon != null) {
                this.mHeaderIcon = icon;
            }
            this.mHeaderView = null;
        }
        onItemsChanged(false);
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderTitleInt(CharSequence title) {
        setHeaderInternal(0, title, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderTitleInt(int titleRes) {
        setHeaderInternal(titleRes, (CharSequence) null, 0, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderIconInt(Drawable icon) {
        setHeaderInternal(0, (CharSequence) null, 0, icon, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderIconInt(int iconRes) {
        setHeaderInternal(0, (CharSequence) null, iconRes, (Drawable) null, (View) null);
        return this;
    }

    /* access modifiers changed from: protected */
    public MenuBuilder setHeaderViewInt(View view) {
        setHeaderInternal(0, (CharSequence) null, 0, (Drawable) null, view);
        return this;
    }

    public CharSequence getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo menuInfo) {
        this.mCurrentMenuInfo = menuInfo;
    }

    public void setOptionalIconsVisible(boolean visible) {
        this.mOptionalIconsVisible = visible;
    }

    /* access modifiers changed from: package-private */
    public boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    public boolean expandItemActionView(MenuItemImpl item) {
        if (this.mPresenters.isEmpty()) {
            return false;
        }
        boolean expanded = false;
        stopDispatchingItemsChanged();
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter presenter = (MenuPresenter) ref.get();
            if (presenter == null) {
                this.mPresenters.remove(ref);
            } else {
                boolean expandItemActionView = presenter.expandItemActionView(this, item);
                expanded = expandItemActionView;
                if (expandItemActionView) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (expanded) {
            this.mExpandedItem = item;
        }
        return expanded;
    }

    public boolean collapseItemActionView(MenuItemImpl item) {
        if (this.mPresenters.isEmpty() || this.mExpandedItem != item) {
            return false;
        }
        boolean collapsed = false;
        stopDispatchingItemsChanged();
        Iterator<WeakReference<MenuPresenter>> it = this.mPresenters.iterator();
        while (it.hasNext()) {
            WeakReference<MenuPresenter> ref = it.next();
            MenuPresenter presenter = (MenuPresenter) ref.get();
            if (presenter == null) {
                this.mPresenters.remove(ref);
            } else {
                boolean collapseItemActionView = presenter.collapseItemActionView(this, item);
                collapsed = collapseItemActionView;
                if (collapseItemActionView) {
                    break;
                }
            }
        }
        startDispatchingItemsChanged();
        if (collapsed) {
            this.mExpandedItem = null;
        }
        return collapsed;
    }

    public MenuItemImpl getExpandedItem() {
        return this.mExpandedItem;
    }

    public void setOverrideVisibleItems(boolean override) {
        this.mOverrideVisibleItems = override;
    }
}
