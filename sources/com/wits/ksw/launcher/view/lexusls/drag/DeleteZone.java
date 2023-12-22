package com.wits.ksw.launcher.view.lexusls.drag;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.p001v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wits.ksw.C0899R;

/* loaded from: classes13.dex */
public class DeleteZone extends LinearLayout implements DropTarget {
    private DragController mDragController;
    private boolean mEnabled;

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public DeleteZone(Context context) {
        super(context);
        this.mEnabled = true;
    }

    public DeleteZone(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mEnabled = true;
    }

    public DeleteZone(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.mEnabled = true;
    }

    public DragController getDragController() {
        return this.mDragController;
    }

    public void setDragController(DragController newValue) {
        this.mDragController = newValue;
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DropTarget
    public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DropTarget
    public void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        LOGE.m43E("onDragEnter 1");
        if (isEnabled() && getBackground() != null) {
            getBackground().setLevel(2);
            LOGE.m43E("onDragEnter 2");
            ((ImageView) findViewById(C0899R.C0901id.deleteImg)).setImageDrawable(tintDrawable(getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_del_d), getResources().getColorStateList(C0899R.color.orange_ff6633)));
            return;
        }
        ((ImageView) findViewById(C0899R.C0901id.deleteImg)).setImageDrawable(getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_del_d));
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DropTarget
    public void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DropTarget
    public void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        LOGE.m43E("onDragExit 1");
        if (isEnabled() && getBackground() != null) {
            getBackground().setLevel(1);
            LOGE.m43E("onDragExit 2");
            findViewById(C0899R.C0901id.deleteImg).setBackground(getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_del_n));
            return;
        }
        ((ImageView) findViewById(C0899R.C0901id.deleteImg)).setImageDrawable(getResources().getDrawable(C0899R.C0900drawable.lexus_ls_main_icon_del_n));
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DropTarget
    public boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo) {
        return isEnabled() && source.isDelete();
    }

    @Override // com.wits.ksw.launcher.view.lexusls.drag.DropTarget
    public Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset, DragView dragView, Object dragInfo, Rect recycle) {
        return null;
    }

    @Override // android.view.View
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
