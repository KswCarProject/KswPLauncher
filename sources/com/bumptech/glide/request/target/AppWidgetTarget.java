package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final ComponentName componentName;
    private final Context context;
    private final RemoteViews remoteViews;
    private final int viewId;
    private final int[] widgetIds;

    public AppWidgetTarget(Context context2, int width, int height, int viewId2, RemoteViews remoteViews2, int... widgetIds2) {
        super(width, height);
        if (widgetIds2.length != 0) {
            this.context = (Context) Preconditions.checkNotNull(context2, "Context can not be null!");
            this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
            this.widgetIds = (int[]) Preconditions.checkNotNull(widgetIds2, "WidgetIds can not be null!");
            this.viewId = viewId2;
            this.componentName = null;
            return;
        }
        throw new IllegalArgumentException("WidgetIds must have length > 0");
    }

    public AppWidgetTarget(Context context2, int viewId2, RemoteViews remoteViews2, int... widgetIds2) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, viewId2, remoteViews2, widgetIds2);
    }

    public AppWidgetTarget(Context context2, int width, int height, int viewId2, RemoteViews remoteViews2, ComponentName componentName2) {
        super(width, height);
        this.context = (Context) Preconditions.checkNotNull(context2, "Context can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
        this.componentName = (ComponentName) Preconditions.checkNotNull(componentName2, "ComponentName can not be null!");
        this.viewId = viewId2;
        this.widgetIds = null;
    }

    public AppWidgetTarget(Context context2, int viewId2, RemoteViews remoteViews2, ComponentName componentName2) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, viewId2, remoteViews2, componentName2);
    }

    private void update() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.context);
        ComponentName componentName2 = this.componentName;
        if (componentName2 != null) {
            appWidgetManager.updateAppWidget(componentName2, this.remoteViews);
        } else {
            appWidgetManager.updateAppWidget(this.widgetIds, this.remoteViews);
        }
    }

    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, resource);
        update();
    }
}
