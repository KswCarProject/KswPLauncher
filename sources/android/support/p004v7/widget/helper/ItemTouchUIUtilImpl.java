package android.support.p004v7.widget.helper;

import android.graphics.Canvas;
import android.os.Build;
import android.support.p001v4.view.ViewCompat;
import android.support.p004v7.recyclerview.C0372R;
import android.support.p004v7.widget.RecyclerView;
import android.view.View;

/* renamed from: android.support.v7.widget.helper.ItemTouchUIUtilImpl */
/* loaded from: classes.dex */
class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE = new ItemTouchUIUtilImpl();

    ItemTouchUIUtilImpl() {
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchUIUtil
    public void onDraw(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (Build.VERSION.SDK_INT >= 21 && isCurrentlyActive) {
            Object originalElevation = view.getTag(C0372R.C0374id.item_touch_helper_previous_elevation);
            if (originalElevation == null) {
                Object originalElevation2 = Float.valueOf(ViewCompat.getElevation(view));
                float newElevation = findMaxElevation(recyclerView, view) + 1.0f;
                ViewCompat.setElevation(view, newElevation);
                view.setTag(C0372R.C0374id.item_touch_helper_previous_elevation, originalElevation2);
            }
        }
        view.setTranslationX(dX);
        view.setTranslationY(dY);
    }

    private static float findMaxElevation(RecyclerView recyclerView, View itemView) {
        int childCount = recyclerView.getChildCount();
        float max = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            if (child != itemView) {
                float elevation = ViewCompat.getElevation(child);
                if (elevation > max) {
                    max = elevation;
                }
            }
        }
        return max;
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchUIUtil
    public void onDrawOver(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchUIUtil
    public void clearView(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            Object tag = view.getTag(C0372R.C0374id.item_touch_helper_previous_elevation);
            if (tag != null && (tag instanceof Float)) {
                ViewCompat.setElevation(view, ((Float) tag).floatValue());
            }
            view.setTag(C0372R.C0374id.item_touch_helper_previous_elevation, null);
        }
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    @Override // android.support.p004v7.widget.helper.ItemTouchUIUtil
    public void onSelected(View view) {
    }
}
