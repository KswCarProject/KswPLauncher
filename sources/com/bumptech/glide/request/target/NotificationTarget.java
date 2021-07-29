package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

public class NotificationTarget extends SimpleTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final String notificationTag;
    private final RemoteViews remoteViews;
    private final int viewId;

    public NotificationTarget(Context context2, int viewId2, RemoteViews remoteViews2, Notification notification2, int notificationId2) {
        this(context2, viewId2, remoteViews2, notification2, notificationId2, (String) null);
    }

    public NotificationTarget(Context context2, int viewId2, RemoteViews remoteViews2, Notification notification2, int notificationId2, String notificationTag2) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, viewId2, remoteViews2, notification2, notificationId2, notificationTag2);
    }

    public NotificationTarget(Context context2, int width, int height, int viewId2, RemoteViews remoteViews2, Notification notification2, int notificationId2, String notificationTag2) {
        super(width, height);
        this.context = (Context) Preconditions.checkNotNull(context2, "Context must not be null!");
        this.notification = (Notification) Preconditions.checkNotNull(notification2, "Notification object can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
        this.viewId = viewId2;
        this.notificationId = notificationId2;
        this.notificationTag = notificationTag2;
    }

    private void update() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.context.getSystemService("notification"))).notify(this.notificationTag, this.notificationId, this.notification);
    }

    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, resource);
        update();
    }
}
