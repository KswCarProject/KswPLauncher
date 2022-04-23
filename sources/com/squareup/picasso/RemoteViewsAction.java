package com.squareup.picasso;

import android.app.Notification;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import com.squareup.picasso.Picasso;

abstract class RemoteViewsAction extends Action<RemoteViewsTarget> {
    final RemoteViews remoteViews;
    private RemoteViewsTarget target;
    final int viewId;

    /* access modifiers changed from: package-private */
    public abstract void update();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteViewsAction(Picasso picasso, Request data, RemoteViews remoteViews2, int viewId2, int errorResId, int memoryPolicy, int networkPolicy, Object tag, String key) {
        super(picasso, null, data, memoryPolicy, networkPolicy, errorResId, (Drawable) null, key, tag, false);
        this.remoteViews = remoteViews2;
        this.viewId = viewId2;
    }

    /* access modifiers changed from: package-private */
    public void complete(Bitmap result, Picasso.LoadedFrom from) {
        this.remoteViews.setImageViewBitmap(this.viewId, result);
        update();
    }

    public void error() {
        if (this.errorResId != 0) {
            setImageResource(this.errorResId);
        }
    }

    /* access modifiers changed from: package-private */
    public RemoteViewsTarget getTarget() {
        if (this.target == null) {
            this.target = new RemoteViewsTarget(this.remoteViews, this.viewId);
        }
        return this.target;
    }

    /* access modifiers changed from: package-private */
    public void setImageResource(int resId) {
        this.remoteViews.setImageViewResource(this.viewId, resId);
        update();
    }

    static class RemoteViewsTarget {
        final RemoteViews remoteViews;
        final int viewId;

        RemoteViewsTarget(RemoteViews remoteViews2, int viewId2) {
            this.remoteViews = remoteViews2;
            this.viewId = viewId2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RemoteViewsTarget remoteViewsTarget = (RemoteViewsTarget) o;
            if (this.viewId != remoteViewsTarget.viewId || !this.remoteViews.equals(remoteViewsTarget.remoteViews)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.remoteViews.hashCode() * 31) + this.viewId;
        }
    }

    static class AppWidgetAction extends RemoteViewsAction {
        private final int[] appWidgetIds;

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Object getTarget() {
            return RemoteViewsAction.super.getTarget();
        }

        AppWidgetAction(Picasso picasso, Request data, RemoteViews remoteViews, int viewId, int[] appWidgetIds2, int memoryPolicy, int networkPolicy, String key, Object tag, int errorResId) {
            super(picasso, data, remoteViews, viewId, errorResId, memoryPolicy, networkPolicy, tag, key);
            this.appWidgetIds = appWidgetIds2;
        }

        /* access modifiers changed from: package-private */
        public void update() {
            AppWidgetManager.getInstance(this.picasso.context).updateAppWidget(this.appWidgetIds, this.remoteViews);
        }
    }

    static class NotificationAction extends RemoteViewsAction {
        private final Notification notification;
        private final int notificationId;

        /* access modifiers changed from: package-private */
        public /* bridge */ /* synthetic */ Object getTarget() {
            return RemoteViewsAction.super.getTarget();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        NotificationAction(Picasso picasso, Request data, RemoteViews remoteViews, int viewId, int notificationId2, Notification notification2, int memoryPolicy, int networkPolicy, String key, Object tag, int errorResId) {
            super(picasso, data, remoteViews, viewId, errorResId, memoryPolicy, networkPolicy, tag, key);
            this.notificationId = notificationId2;
            this.notification = notification2;
        }

        /* access modifiers changed from: package-private */
        public void update() {
            ((NotificationManager) Utils.getService(this.picasso.context, "notification")).notify(this.notificationId, this.notification);
        }
    }
}
