package com.wits.ksw.launcher.bmw_id8_ui.adapter;

import android.graphics.Bitmap;
import android.support.p004v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class RecycleHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public RecycleHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
    }

    public <T extends View> T findView(int ViewId) {
        T t = (T) this.mViews.get(ViewId);
        if (t == null) {
            View view = this.itemView.findViewById(ViewId);
            T t2 = (T) view;
            this.mViews.put(ViewId, t2);
            return t2;
        }
        return t;
    }

    public RecycleHolder setText(int viewId, String text) {
        TextView tv = (TextView) findView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleHolder setText(int viewId, int text) {
        TextView tv = (TextView) findView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleHolder setImageResource(int viewId, int ImageId) {
        ImageView image = (ImageView) findView(viewId);
        image.setImageResource(ImageId);
        return this;
    }

    public RecycleHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = (ImageView) findView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }
}
