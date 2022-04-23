package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.R;

public class DeleteZone extends LinearLayout implements DropTarget {
    private DragController mDragController;
    private boolean mEnabled = true;

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public DeleteZone(Context context) {
        super(context);
    }

    public DeleteZone(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeleteZone(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
    }

    public DragController getDragController() {
        return this.mDragController;
    }

    public void setDragController(DragController newValue) {
        this.mDragController = newValue;
    }

    public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    public void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        LOGE.E("onDragEnter 1");
        if (!isEnabled() || getBackground() == null) {
            ((ImageView) findViewById(R.id.deleteImg)).setImageDrawable(getResources().getDrawable(R.drawable.lexus_ls_main_icon_del_d));
            return;
        }
        getBackground().setLevel(2);
        LOGE.E("onDragEnter 2");
        ((ImageView) findViewById(R.id.deleteImg)).setImageDrawable(tintDrawable(getResources().getDrawable(R.drawable.lexus_ls_main_icon_del_d), getResources().getColorStateList(R.color.orange_ff6633)));
    }

    public void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    public void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        LOGE.E("onDragExit 1");
        if (!isEnabled() || getBackground() == null) {
            ((ImageView) findViewById(R.id.deleteImg)).setImageDrawable(getResources().getDrawable(R.drawable.lexus_ls_main_icon_del_n));
            return;
        }
        getBackground().setLevel(1);
        LOGE.E("onDragExit 2");
        findViewById(R.id.deleteImg).setBackground(getResources().getDrawable(R.drawable.lexus_ls_main_icon_del_n));
    }

    public boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        return isEnabled() && source.isDelete();
    }

    public Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo, Rect recycle) {
        return null;
    }

    public boolean isEnabled() {
        return this.mEnabled && getVisibility() == 0;
    }

    public void setup(DragController controller) {
        this.mDragController = controller;
        if (controller != null) {
            controller.addDropTarget(this);
        }
    }
}
