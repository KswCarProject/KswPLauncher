package com.squareup.picasso;

import android.app.Notification;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.squareup.picasso.Picasso;

/* loaded from: classes.dex */
abstract class RemoteViewsAction extends Action<RemoteViewsTarget> {
    final RemoteViews remoteViews;
    private RemoteViewsTarget target;
    final int viewId;

    abstract void update();

    RemoteViewsAction(Picasso picasso, Request data, RemoteViews remoteViews, int viewId, int errorResId, int memoryPolicy, int networkPolicy, Object tag, String key) {
        super(picasso, null, data, memoryPolicy, networkPolicy, errorResId, null, key, tag, false);
        this.remoteViews = remoteViews;
        this.viewId = viewId;
    }

    @Override // com.squareup.picasso.Action
    void complete(Bitmap result, Picasso.LoadedFrom from) {
        this.remoteViews.setImageViewBitmap(this.viewId, result);
        update();
    }

    @Override // com.squareup.picasso.Action
    public void error() {
        if (this.errorResId != 0) {
            setImageResource(this.errorResId);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.picasso.Action
    public RemoteViewsTarget getTarget() {
        if (this.target == null) {
            this.target = new RemoteViewsTarget(this.remoteViews, this.viewId);
        }
        return this.target;
    }

    void setImageResource(int resId) {
        this.remoteViews.setImageViewResource(this.viewId, resId);
        update();
    }

    /* loaded from: classes.dex */
    static class RemoteViewsTarget {
        final RemoteViews remoteViews;
        final int viewId;

        RemoteViewsTarget(RemoteViews remoteViews, int viewId) {
            this.remoteViews = remoteViews;
            this.viewId = viewId;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RemoteViewsTarget remoteViewsTarget = (RemoteViewsTarget) o;
            if (this.viewId == remoteViewsTarget.viewId && this.remoteViews.equals(remoteViewsTarget.remoteViews)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.remoteViews.hashCode() * 31) + this.viewId;
        }
    }

    /* loaded from: classes.dex */
    static class AppWidgetAction extends RemoteViewsAction {
        private final int[] appWidgetIds;

        @Override // com.squareup.picasso.RemoteViewsAction, com.squareup.picasso.Action
        /* bridge */ /* synthetic */ RemoteViewsTarget getTarget() {
            return super.getTarget();
        }

        AppWidgetAction(Picasso picasso, Request data, RemoteViews remoteViews, int viewId, int[] appWidgetIds, int memoryPolicy, int networkPolicy, String key, Object tag, int errorResId) {
            super(picasso, data, remoteViews, viewId, errorResId, memoryPolicy, networkPolicy, tag, key);
            this.appWidgetIds = appWidgetIds;
        }

        @Override // com.squareup.picasso.RemoteViewsAction
        void update() {
            AppWidgetManager manager = AppWidgetManager.getInstance(this.picasso.context);
            manager.updateAppWidget(this.appWidgetIds, this.remoteViews);
        }
    }

    /* loaded from: classes.dex */
    static class NotificationAction extends RemoteViewsAction {
        private final Notification notification;
        private final int notificationId;

        @Override // com.squareup.picasso.RemoteViewsAction, com.squareup.picasso.Action
        /* bridge */ /* synthetic */ RemoteViewsTarget getTarget() {
            return super.getTarget();
        }

        NotificationAction(Picasso picasso, Request data, RemoteViews remoteViews, int viewId, int notificationId, Notification notification, int memoryPolicy, int networkPolicy, String key, Object tag, int errorResId) {
            super(picasso, data, remoteViews, viewId, errorResId, memoryPolicy, networkPolicy, tag, key);
            this.notificationId = notificationId;
            this.notification = notification;
        }

        @Override // com.squareup.picasso.RemoteViewsAction
        void update() {
            NotificationManager manager = (NotificationManager) Utils.getService(this.picasso.context, "notification");
            manager.notify(this.notificationId, this.notification);
        }
    }
}
