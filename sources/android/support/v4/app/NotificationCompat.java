package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.compat.R;
import android.support.v4.app.Person;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.ViewCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ArrayList<Action> mActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList<Action> mInvisibleActions;
        Bitmap mLargeIcon;
        boolean mLocalOnly;
        Notification mNotification;
        int mNumber;
        @Deprecated
        public ArrayList<String> mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        String mShortcutId;
        boolean mShowWhen;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        public Builder(@NonNull Context context, @NonNull String channelId) {
            this.mActions = new ArrayList<>();
            this.mInvisibleActions = new ArrayList<>();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mNotification = new Notification();
            this.mContext = context;
            this.mChannelId = channelId;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<>();
        }

        @Deprecated
        public Builder(Context context) {
            this(context, (String) null);
        }

        public Builder setWhen(long when) {
            this.mNotification.when = when;
            return this;
        }

        public Builder setShowWhen(boolean show) {
            this.mShowWhen = show;
            return this;
        }

        public Builder setUsesChronometer(boolean b) {
            this.mUseChronometer = b;
            return this;
        }

        public Builder setSmallIcon(int icon) {
            this.mNotification.icon = icon;
            return this;
        }

        public Builder setSmallIcon(int icon, int level) {
            this.mNotification.icon = icon;
            this.mNotification.iconLevel = level;
            return this;
        }

        public Builder setContentTitle(CharSequence title) {
            this.mContentTitle = limitCharSequenceLength(title);
            return this;
        }

        public Builder setContentText(CharSequence text) {
            this.mContentText = limitCharSequenceLength(text);
            return this;
        }

        public Builder setSubText(CharSequence text) {
            this.mSubText = limitCharSequenceLength(text);
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] text) {
            this.mRemoteInputHistory = text;
            return this;
        }

        public Builder setNumber(int number) {
            this.mNumber = number;
            return this;
        }

        public Builder setContentInfo(CharSequence info) {
            this.mContentInfo = limitCharSequenceLength(info);
            return this;
        }

        public Builder setProgress(int max, int progress, boolean indeterminate) {
            this.mProgressMax = max;
            this.mProgress = progress;
            this.mProgressIndeterminate = indeterminate;
            return this;
        }

        public Builder setContent(RemoteViews views) {
            this.mNotification.contentView = views;
            return this;
        }

        public Builder setContentIntent(PendingIntent intent) {
            this.mContentIntent = intent;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent intent) {
            this.mNotification.deleteIntent = intent;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent intent, boolean highPriority) {
            this.mFullScreenIntent = intent;
            setFlag(128, highPriority);
            return this;
        }

        public Builder setTicker(CharSequence tickerText) {
            this.mNotification.tickerText = limitCharSequenceLength(tickerText);
            return this;
        }

        public Builder setTicker(CharSequence tickerText, RemoteViews views) {
            this.mNotification.tickerText = limitCharSequenceLength(tickerText);
            this.mTickerView = views;
            return this;
        }

        public Builder setLargeIcon(Bitmap icon) {
            this.mLargeIcon = reduceLargeIconSize(icon);
            return this;
        }

        private Bitmap reduceLargeIconSize(Bitmap icon) {
            if (icon == null || Build.VERSION.SDK_INT >= 27) {
                return icon;
            }
            Resources res = this.mContext.getResources();
            int maxWidth = res.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
            int maxHeight = res.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            if (icon.getWidth() <= maxWidth && icon.getHeight() <= maxHeight) {
                return icon;
            }
            double scale = Math.min(((double) maxWidth) / ((double) Math.max(1, icon.getWidth())), ((double) maxHeight) / ((double) Math.max(1, icon.getHeight())));
            return Bitmap.createScaledBitmap(icon, (int) Math.ceil(((double) icon.getWidth()) * scale), (int) Math.ceil(((double) icon.getHeight()) * scale), true);
        }

        public Builder setSound(Uri sound) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = -1;
            if (Build.VERSION.SDK_INT >= 21) {
                this.mNotification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            }
            return this;
        }

        public Builder setSound(Uri sound, int streamType) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = streamType;
            if (Build.VERSION.SDK_INT >= 21) {
                this.mNotification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(streamType).build();
            }
            return this;
        }

        public Builder setVibrate(long[] pattern) {
            this.mNotification.vibrate = pattern;
            return this;
        }

        public Builder setLights(@ColorInt int argb, int onMs, int offMs) {
            this.mNotification.ledARGB = argb;
            this.mNotification.ledOnMS = onMs;
            this.mNotification.ledOffMS = offMs;
            int i = 1;
            boolean showLights = (this.mNotification.ledOnMS == 0 || this.mNotification.ledOffMS == 0) ? false : true;
            Notification notification = this.mNotification;
            int i2 = this.mNotification.flags & -2;
            if (!showLights) {
                i = 0;
            }
            notification.flags = i | i2;
            return this;
        }

        public Builder setOngoing(boolean ongoing) {
            setFlag(2, ongoing);
            return this;
        }

        public Builder setColorized(boolean colorize) {
            this.mColorized = colorize;
            this.mColorizedSet = true;
            return this;
        }

        public Builder setOnlyAlertOnce(boolean onlyAlertOnce) {
            setFlag(8, onlyAlertOnce);
            return this;
        }

        public Builder setAutoCancel(boolean autoCancel) {
            setFlag(16, autoCancel);
            return this;
        }

        public Builder setLocalOnly(boolean b) {
            this.mLocalOnly = b;
            return this;
        }

        public Builder setCategory(String category) {
            this.mCategory = category;
            return this;
        }

        public Builder setDefaults(int defaults) {
            this.mNotification.defaults = defaults;
            if ((defaults & 4) != 0) {
                this.mNotification.flags |= 1;
            }
            return this;
        }

        private void setFlag(int mask, boolean value) {
            if (value) {
                this.mNotification.flags |= mask;
                return;
            }
            this.mNotification.flags &= ~mask;
        }

        public Builder setPriority(int pri) {
            this.mPriority = pri;
            return this;
        }

        public Builder addPerson(String uri) {
            this.mPeople.add(uri);
            return this;
        }

        public Builder setGroup(String groupKey) {
            this.mGroupKey = groupKey;
            return this;
        }

        public Builder setGroupSummary(boolean isGroupSummary) {
            this.mGroupSummary = isGroupSummary;
            return this;
        }

        public Builder setSortKey(String sortKey) {
            this.mSortKey = sortKey;
            return this;
        }

        public Builder addExtras(Bundle extras) {
            if (extras != null) {
                if (this.mExtras == null) {
                    this.mExtras = new Bundle(extras);
                } else {
                    this.mExtras.putAll(extras);
                }
            }
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        public Builder addAction(int icon, CharSequence title, PendingIntent intent) {
            this.mActions.add(new Action(icon, title, intent));
            return this;
        }

        public Builder addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(int icon, CharSequence title, PendingIntent intent) {
            return addInvisibleAction(new Action(icon, title, intent));
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(Action action) {
            this.mInvisibleActions.add(action);
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setColor(@ColorInt int argb) {
            this.mColor = argb;
            return this;
        }

        public Builder setVisibility(int visibility) {
            this.mVisibility = visibility;
            return this;
        }

        public Builder setPublicVersion(Notification n) {
            this.mPublicVersion = n;
            return this;
        }

        public Builder setCustomContentView(RemoteViews contentView) {
            this.mContentView = contentView;
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews contentView) {
            this.mBigContentView = contentView;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews contentView) {
            this.mHeadsUpContentView = contentView;
            return this;
        }

        public Builder setChannelId(@NonNull String channelId) {
            this.mChannelId = channelId;
            return this;
        }

        public Builder setTimeoutAfter(long durationMs) {
            this.mTimeout = durationMs;
            return this;
        }

        public Builder setShortcutId(String shortcutId) {
            this.mShortcutId = shortcutId;
            return this;
        }

        public Builder setBadgeIconType(int icon) {
            this.mBadgeIcon = icon;
            return this;
        }

        public Builder setGroupAlertBehavior(int groupAlertBehavior) {
            this.mGroupAlertBehavior = groupAlertBehavior;
            return this;
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        protected static CharSequence limitCharSequenceLength(CharSequence cs) {
            if (cs != null && cs.length() > MAX_CHARSEQUENCE_LENGTH) {
                return cs.subSequence(0, MAX_CHARSEQUENCE_LENGTH);
            }
            return cs;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.mContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            if (this.mShowWhen) {
                return this.mNotification.when;
            }
            return 0;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.mPriority;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.mColor;
        }
    }

    public static abstract class Style {
        CharSequence mBigContentTitle;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet = false;

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (this.mBuilder != null) {
                    this.mBuilder.setStyle(this);
                }
            }
        }

        public Notification build() {
            if (this.mBuilder != null) {
                return this.mBuilder.build();
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle extras) {
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void restoreFromCompatExtras(Bundle extras) {
        }

        /* JADX WARNING: Removed duplicated region for block: B:69:0x01d6  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x01f2  */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x0200  */
        /* JADX WARNING: Removed duplicated region for block: B:84:0x0249  */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x024b  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x0255  */
        @android.support.annotation.RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.widget.RemoteViews applyStandardTemplate(boolean r20, int r21, boolean r22) {
            /*
                r19 = this;
                r0 = r19
                android.support.v4.app.NotificationCompat$Builder r1 = r0.mBuilder
                android.content.Context r1 = r1.mContext
                android.content.res.Resources r1 = r1.getResources()
                android.widget.RemoteViews r2 = new android.widget.RemoteViews
                android.support.v4.app.NotificationCompat$Builder r3 = r0.mBuilder
                android.content.Context r3 = r3.mContext
                java.lang.String r3 = r3.getPackageName()
                r4 = r21
                r2.<init>(r3, r4)
                r3 = 0
                r5 = 0
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                int r6 = r6.getPriority()
                r7 = -1
                r12 = 0
                if (r6 >= r7) goto L_0x0027
                r6 = 1
                goto L_0x0028
            L_0x0027:
                r6 = r12
            L_0x0028:
                r13 = r6
                int r6 = android.os.Build.VERSION.SDK_INT
                r8 = 21
                r14 = 16
                if (r6 < r14) goto L_0x005c
                int r6 = android.os.Build.VERSION.SDK_INT
                if (r6 >= r8) goto L_0x005c
                if (r13 == 0) goto L_0x004a
                int r6 = android.support.compat.R.id.notification_background
                java.lang.String r9 = "setBackgroundResource"
                int r10 = android.support.compat.R.drawable.notification_bg_low
                r2.setInt(r6, r9, r10)
                int r6 = android.support.compat.R.id.icon
                java.lang.String r9 = "setBackgroundResource"
                int r10 = android.support.compat.R.drawable.notification_template_icon_low_bg
                r2.setInt(r6, r9, r10)
                goto L_0x005c
            L_0x004a:
                int r6 = android.support.compat.R.id.notification_background
                java.lang.String r9 = "setBackgroundResource"
                int r10 = android.support.compat.R.drawable.notification_bg
                r2.setInt(r6, r9, r10)
                int r6 = android.support.compat.R.id.icon
                java.lang.String r9 = "setBackgroundResource"
                int r10 = android.support.compat.R.drawable.notification_template_icon_bg
                r2.setInt(r6, r9, r10)
            L_0x005c:
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                android.graphics.Bitmap r6 = r6.mLargeIcon
                r10 = 8
                if (r6 == 0) goto L_0x00c5
                int r6 = android.os.Build.VERSION.SDK_INT
                if (r6 < r14) goto L_0x0077
                int r6 = android.support.compat.R.id.icon
                r2.setViewVisibility(r6, r12)
                int r6 = android.support.compat.R.id.icon
                android.support.v4.app.NotificationCompat$Builder r9 = r0.mBuilder
                android.graphics.Bitmap r9 = r9.mLargeIcon
                r2.setImageViewBitmap(r6, r9)
                goto L_0x007c
            L_0x0077:
                int r6 = android.support.compat.R.id.icon
                r2.setViewVisibility(r6, r10)
            L_0x007c:
                if (r20 == 0) goto L_0x0110
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                android.app.Notification r6 = r6.mNotification
                int r6 = r6.icon
                if (r6 == 0) goto L_0x0110
                int r6 = android.support.compat.R.dimen.notification_right_icon_size
                int r6 = r1.getDimensionPixelSize(r6)
                int r9 = android.support.compat.R.dimen.notification_small_icon_background_padding
                int r9 = r1.getDimensionPixelSize(r9)
                int r9 = r9 * 2
                int r9 = r6 - r9
                int r11 = android.os.Build.VERSION.SDK_INT
                if (r11 < r8) goto L_0x00b0
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                android.app.Notification r7 = r7.mNotification
                int r7 = r7.icon
                android.support.v4.app.NotificationCompat$Builder r11 = r0.mBuilder
                int r11 = r11.getColor()
                android.graphics.Bitmap r7 = r0.createIconWithBackground(r7, r6, r9, r11)
                int r11 = android.support.compat.R.id.right_icon
                r2.setImageViewBitmap(r11, r7)
                goto L_0x00bf
            L_0x00b0:
                int r11 = android.support.compat.R.id.right_icon
                android.support.v4.app.NotificationCompat$Builder r14 = r0.mBuilder
                android.app.Notification r14 = r14.mNotification
                int r14 = r14.icon
                android.graphics.Bitmap r7 = r0.createColoredBitmap(r14, r7)
                r2.setImageViewBitmap(r11, r7)
            L_0x00bf:
                int r7 = android.support.compat.R.id.right_icon
                r2.setViewVisibility(r7, r12)
                goto L_0x0110
            L_0x00c5:
                if (r20 == 0) goto L_0x0110
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                android.app.Notification r6 = r6.mNotification
                int r6 = r6.icon
                if (r6 == 0) goto L_0x0110
                int r6 = android.support.compat.R.id.icon
                r2.setViewVisibility(r6, r12)
                int r6 = android.os.Build.VERSION.SDK_INT
                if (r6 < r8) goto L_0x0101
                int r6 = android.support.compat.R.dimen.notification_large_icon_width
                int r6 = r1.getDimensionPixelSize(r6)
                int r7 = android.support.compat.R.dimen.notification_big_circle_margin
                int r7 = r1.getDimensionPixelSize(r7)
                int r6 = r6 - r7
                int r7 = android.support.compat.R.dimen.notification_small_icon_size_as_large
                int r7 = r1.getDimensionPixelSize(r7)
                android.support.v4.app.NotificationCompat$Builder r9 = r0.mBuilder
                android.app.Notification r9 = r9.mNotification
                int r9 = r9.icon
                android.support.v4.app.NotificationCompat$Builder r11 = r0.mBuilder
                int r11 = r11.getColor()
                android.graphics.Bitmap r9 = r0.createIconWithBackground(r9, r6, r7, r11)
                int r11 = android.support.compat.R.id.icon
                r2.setImageViewBitmap(r11, r9)
                goto L_0x0110
            L_0x0101:
                int r6 = android.support.compat.R.id.icon
                android.support.v4.app.NotificationCompat$Builder r9 = r0.mBuilder
                android.app.Notification r9 = r9.mNotification
                int r9 = r9.icon
                android.graphics.Bitmap r7 = r0.createColoredBitmap(r9, r7)
                r2.setImageViewBitmap(r6, r7)
            L_0x0110:
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                java.lang.CharSequence r6 = r6.mContentTitle
                if (r6 == 0) goto L_0x011f
                int r6 = android.support.compat.R.id.title
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                java.lang.CharSequence r7 = r7.mContentTitle
                r2.setTextViewText(r6, r7)
            L_0x011f:
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                java.lang.CharSequence r6 = r6.mContentText
                if (r6 == 0) goto L_0x012f
                int r6 = android.support.compat.R.id.text
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                java.lang.CharSequence r7 = r7.mContentText
                r2.setTextViewText(r6, r7)
                r3 = 1
            L_0x012f:
                int r6 = android.os.Build.VERSION.SDK_INT
                if (r6 >= r8) goto L_0x013b
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                android.graphics.Bitmap r6 = r6.mLargeIcon
                if (r6 == 0) goto L_0x013b
                r6 = 1
                goto L_0x013c
            L_0x013b:
                r6 = r12
            L_0x013c:
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                java.lang.CharSequence r7 = r7.mContentInfo
                if (r7 == 0) goto L_0x0155
                int r7 = android.support.compat.R.id.info
                android.support.v4.app.NotificationCompat$Builder r8 = r0.mBuilder
                java.lang.CharSequence r8 = r8.mContentInfo
                r2.setTextViewText(r7, r8)
                int r7 = android.support.compat.R.id.info
                r2.setViewVisibility(r7, r12)
                r3 = 1
                r6 = 1
            L_0x0152:
                r11 = r3
                r3 = r6
                goto L_0x0195
            L_0x0155:
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                int r7 = r7.mNumber
                if (r7 <= 0) goto L_0x018d
                int r7 = android.support.compat.R.integer.status_bar_notification_info_maxnum
                int r7 = r1.getInteger(r7)
                android.support.v4.app.NotificationCompat$Builder r8 = r0.mBuilder
                int r8 = r8.mNumber
                if (r8 <= r7) goto L_0x0173
                int r8 = android.support.compat.R.id.info
                int r9 = android.support.compat.R.string.status_bar_notification_info_overflow
                java.lang.String r9 = r1.getString(r9)
                r2.setTextViewText(r8, r9)
                goto L_0x0185
            L_0x0173:
                java.text.NumberFormat r8 = java.text.NumberFormat.getIntegerInstance()
                int r9 = android.support.compat.R.id.info
                android.support.v4.app.NotificationCompat$Builder r11 = r0.mBuilder
                int r11 = r11.mNumber
                long r10 = (long) r11
                java.lang.String r10 = r8.format(r10)
                r2.setTextViewText(r9, r10)
            L_0x0185:
                int r8 = android.support.compat.R.id.info
                r2.setViewVisibility(r8, r12)
                r3 = 1
                r6 = 1
                goto L_0x0152
            L_0x018d:
                int r7 = android.support.compat.R.id.info
                r8 = 8
                r2.setViewVisibility(r7, r8)
                goto L_0x0152
            L_0x0195:
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                java.lang.CharSequence r6 = r6.mSubText
                if (r6 == 0) goto L_0x01cb
                int r6 = android.os.Build.VERSION.SDK_INT
                r7 = 16
                if (r6 < r7) goto L_0x01cb
                int r6 = android.support.compat.R.id.text
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                java.lang.CharSequence r7 = r7.mSubText
                r2.setTextViewText(r6, r7)
                android.support.v4.app.NotificationCompat$Builder r6 = r0.mBuilder
                java.lang.CharSequence r6 = r6.mContentText
                if (r6 == 0) goto L_0x01c3
                int r6 = android.support.compat.R.id.text2
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                java.lang.CharSequence r7 = r7.mContentText
                r2.setTextViewText(r6, r7)
                int r6 = android.support.compat.R.id.text2
                r2.setViewVisibility(r6, r12)
                r5 = 1
                r14 = r5
                r10 = 8
                goto L_0x01ce
            L_0x01c3:
                int r6 = android.support.compat.R.id.text2
                r10 = 8
                r2.setViewVisibility(r6, r10)
                goto L_0x01cd
            L_0x01cb:
                r10 = 8
            L_0x01cd:
                r14 = r5
            L_0x01ce:
                if (r14 == 0) goto L_0x01f2
                int r5 = android.os.Build.VERSION.SDK_INT
                r6 = 16
                if (r5 < r6) goto L_0x01f2
                if (r22 == 0) goto L_0x01e4
                int r5 = android.support.compat.R.dimen.notification_subtext_size
                int r5 = r1.getDimensionPixelSize(r5)
                float r5 = (float) r5
                int r6 = android.support.compat.R.id.text
                r2.setTextViewTextSize(r6, r12, r5)
            L_0x01e4:
                int r6 = android.support.compat.R.id.line1
                r7 = 0
                r8 = 0
                r9 = 0
                r15 = 0
                r5 = r2
                r16 = r10
                r10 = r15
                r5.setViewPadding(r6, r7, r8, r9, r10)
                goto L_0x01f4
            L_0x01f2:
                r16 = r10
            L_0x01f4:
                android.support.v4.app.NotificationCompat$Builder r5 = r0.mBuilder
                long r5 = r5.getWhenIfShowing()
                r7 = 0
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 == 0) goto L_0x0245
                android.support.v4.app.NotificationCompat$Builder r5 = r0.mBuilder
                boolean r5 = r5.mUseChronometer
                if (r5 == 0) goto L_0x0232
                int r5 = android.os.Build.VERSION.SDK_INT
                r6 = 16
                if (r5 < r6) goto L_0x0232
                int r5 = android.support.compat.R.id.chronometer
                r2.setViewVisibility(r5, r12)
                int r5 = android.support.compat.R.id.chronometer
                java.lang.String r6 = "setBase"
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                long r7 = r7.getWhenIfShowing()
                long r9 = android.os.SystemClock.elapsedRealtime()
                long r17 = java.lang.System.currentTimeMillis()
                long r9 = r9 - r17
                long r7 = r7 + r9
                r2.setLong(r5, r6, r7)
                int r5 = android.support.compat.R.id.chronometer
                java.lang.String r6 = "setStarted"
                r7 = 1
                r2.setBoolean(r5, r6, r7)
                goto L_0x0244
            L_0x0232:
                int r5 = android.support.compat.R.id.time
                r2.setViewVisibility(r5, r12)
                int r5 = android.support.compat.R.id.time
                java.lang.String r6 = "setTime"
                android.support.v4.app.NotificationCompat$Builder r7 = r0.mBuilder
                long r7 = r7.getWhenIfShowing()
                r2.setLong(r5, r6, r7)
            L_0x0244:
                r3 = 1
            L_0x0245:
                int r5 = android.support.compat.R.id.right_side
                if (r3 == 0) goto L_0x024b
                r6 = r12
                goto L_0x024d
            L_0x024b:
                r6 = r16
            L_0x024d:
                r2.setViewVisibility(r5, r6)
                int r5 = android.support.compat.R.id.line3
                if (r11 == 0) goto L_0x0255
                goto L_0x0257
            L_0x0255:
                r12 = r16
            L_0x0257:
                r2.setViewVisibility(r5, r12)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompat.Style.applyStandardTemplate(boolean, int, boolean):android.widget.RemoteViews");
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int iconId, int color) {
            return createColoredBitmap(iconId, color, 0);
        }

        private Bitmap createColoredBitmap(int iconId, int color, int size) {
            Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(iconId);
            int width = size == 0 ? drawable.getIntrinsicWidth() : size;
            int height = size == 0 ? drawable.getIntrinsicHeight() : size;
            Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            drawable.setBounds(0, 0, width, height);
            if (color != 0) {
                drawable.mutate().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
            drawable.draw(new Canvas(resultBitmap));
            return resultBitmap;
        }

        private Bitmap createIconWithBackground(int iconId, int size, int iconSize, int color) {
            Bitmap coloredBitmap = createColoredBitmap(R.drawable.notification_icon_background, color == 0 ? 0 : color, size);
            Canvas canvas = new Canvas(coloredBitmap);
            Drawable icon = this.mBuilder.mContext.getResources().getDrawable(iconId).mutate();
            icon.setFilterBitmap(true);
            int inset = (size - iconSize) / 2;
            icon.setBounds(inset, inset, iconSize + inset, iconSize + inset);
            icon.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            icon.draw(canvas);
            return coloredBitmap;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews outerView, RemoteViews innerView) {
            hideNormalContent(outerView);
            outerView.removeAllViews(R.id.notification_main_column);
            outerView.addView(R.id.notification_main_column, innerView.clone());
            outerView.setViewVisibility(R.id.notification_main_column, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                outerView.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
            }
        }

        private void hideNormalContent(RemoteViews outerView) {
            outerView.setViewVisibility(R.id.title, 8);
            outerView.setViewVisibility(R.id.text2, 8);
            outerView.setViewVisibility(R.id.text, 8);
        }

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int padding = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int largePadding = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float largeFactor = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - largeFactor) * ((float) padding)) + (((float) largePadding) * largeFactor));
        }

        private static float constrain(float amount, float low, float high) {
            if (amount < low) {
                return low;
            }
            return amount > high ? high : amount;
        }
    }

    public static class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle() {
        }

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigPictureStyle setBigContentTitle(CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap b) {
            this.mPicture = b;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Bitmap b) {
            this.mBigLargeIcon = b;
            this.mBigLargeIconSet = true;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigPictureStyle style = new Notification.BigPictureStyle(builder.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
                if (this.mBigLargeIconSet) {
                    style.bigLargeIcon(this.mBigLargeIcon);
                }
                if (this.mSummaryTextSet) {
                    style.setSummaryText(this.mSummaryText);
                }
            }
        }
    }

    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigTextStyle setBigContentTitle(CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle bigText(CharSequence cs) {
            this.mBigText = Builder.limitCharSequenceLength(cs);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle style = new Notification.BigTextStyle(builder.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
                if (this.mSummaryTextSet) {
                    style.setSummaryText(this.mSummaryText);
                }
            }
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        @Nullable
        private CharSequence mConversationTitle;
        @Nullable
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages = new ArrayList();
        private Person mUser;

        private MessagingStyle() {
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence userDisplayName) {
            this.mUser = new Person.Builder().setName(userDisplayName).build();
        }

        public MessagingStyle(@NonNull Person user) {
            if (!TextUtils.isEmpty(user.getName())) {
                this.mUser = user;
                return;
            }
            throw new IllegalArgumentException("User's name must not be empty.");
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public Person getUser() {
            return this.mUser;
        }

        public MessagingStyle setConversationTitle(@Nullable CharSequence conversationTitle) {
            this.mConversationTitle = conversationTitle;
            return this;
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence text, long timestamp, CharSequence sender) {
            this.mMessages.add(new Message(text, timestamp, new Person.Builder().setName(sender).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence text, long timestamp, Person person) {
            addMessage(new Message(text, timestamp, person));
            return this;
        }

        public MessagingStyle addMessage(Message message) {
            this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public MessagingStyle setGroupConversation(boolean isGroupConversation) {
            this.mIsGroupConversation = Boolean.valueOf(isGroupConversation);
            return this;
        }

        public boolean isGroupConversation() {
            if (this.mBuilder == null || this.mBuilder.mContext.getApplicationInfo().targetSdkVersion >= 28 || this.mIsGroupConversation != null) {
                if (this.mIsGroupConversation != null) {
                    return this.mIsGroupConversation.booleanValue();
                }
                return false;
            } else if (this.mConversationTitle != null) {
                return true;
            } else {
                return false;
            }
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null && !extras.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) && !extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return null;
            }
            try {
                MessagingStyle style = new MessagingStyle();
                style.restoreFromCompatExtras(extras);
                return style;
            } catch (ClassCastException e) {
                return null;
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            CharSequence charSequence;
            Notification.MessagingStyle frameworkStyle;
            Notification.MessagingStyle.Message frameworkMessage;
            setGroupConversation(isGroupConversation());
            if (Build.VERSION.SDK_INT >= 24) {
                if (Build.VERSION.SDK_INT >= 28) {
                    frameworkStyle = new Notification.MessagingStyle(this.mUser.toAndroidPerson());
                } else {
                    frameworkStyle = new Notification.MessagingStyle(this.mUser.getName());
                }
                if (this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28) {
                    frameworkStyle.setConversationTitle(this.mConversationTitle);
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    frameworkStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
                }
                for (Message compatMessage : this.mMessages) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        Person compatMessagePerson = compatMessage.getPerson();
                        frameworkMessage = new Notification.MessagingStyle.Message(compatMessage.getText(), compatMessage.getTimestamp(), compatMessagePerson == null ? null : compatMessagePerson.toAndroidPerson());
                    } else {
                        CharSequence name = null;
                        if (compatMessage.getPerson() != null) {
                            name = compatMessage.getPerson().getName();
                        }
                        frameworkMessage = new Notification.MessagingStyle.Message(compatMessage.getText(), compatMessage.getTimestamp(), name);
                    }
                    if (compatMessage.getDataMimeType() != null) {
                        frameworkMessage.setData(compatMessage.getDataMimeType(), compatMessage.getDataUri());
                    }
                    frameworkStyle.addMessage(frameworkMessage);
                }
                frameworkStyle.setBuilder(builder.getBuilder());
                return;
            }
            Message latestIncomingMessage = findLatestIncomingMessage();
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                builder.getBuilder().setContentTitle(this.mConversationTitle);
            } else if (latestIncomingMessage != null) {
                builder.getBuilder().setContentTitle("");
                if (latestIncomingMessage.getPerson() != null) {
                    builder.getBuilder().setContentTitle(latestIncomingMessage.getPerson().getName());
                }
            }
            if (latestIncomingMessage != null) {
                Notification.Builder builder2 = builder.getBuilder();
                if (this.mConversationTitle != null) {
                    charSequence = makeMessageLine(latestIncomingMessage);
                } else {
                    charSequence = latestIncomingMessage.getText();
                }
                builder2.setContentText(charSequence);
            }
            if (Build.VERSION.SDK_INT >= 16) {
                SpannableStringBuilder completeMessage = new SpannableStringBuilder();
                boolean showNames = this.mConversationTitle != null || hasMessagesWithoutSender();
                for (int i = this.mMessages.size() - 1; i >= 0; i--) {
                    Message message = this.mMessages.get(i);
                    CharSequence line = showNames ? makeMessageLine(message) : message.getText();
                    if (i != this.mMessages.size() - 1) {
                        completeMessage.insert(0, "\n");
                    }
                    completeMessage.insert(0, line);
                }
                new Notification.BigTextStyle(builder.getBuilder()).setBigContentTitle((CharSequence) null).bigText(completeMessage);
            }
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            for (int i = this.mMessages.size() - 1; i >= 0; i--) {
                Message message = this.mMessages.get(i);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            if (!this.mMessages.isEmpty()) {
                return this.mMessages.get(this.mMessages.size() - 1);
            }
            return null;
        }

        private boolean hasMessagesWithoutSender() {
            for (int i = this.mMessages.size() - 1; i >= 0; i--) {
                Message message = this.mMessages.get(i);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        private CharSequence makeMessageLine(Message message) {
            BidiFormatter bidi = BidiFormatter.getInstance();
            SpannableStringBuilder sb = new SpannableStringBuilder();
            boolean afterLollipop = Build.VERSION.SDK_INT >= 21;
            int color = afterLollipop ? ViewCompat.MEASURED_STATE_MASK : -1;
            String replyName = message.getPerson() == null ? "" : message.getPerson().getName();
            if (TextUtils.isEmpty(replyName)) {
                replyName = this.mUser.getName();
                color = (!afterLollipop || this.mBuilder.getColor() == 0) ? color : this.mBuilder.getColor();
            }
            CharSequence senderText = bidi.unicodeWrap(replyName);
            sb.append(senderText);
            sb.setSpan(makeFontColorSpan(color), sb.length() - senderText.length(), sb.length(), 33);
            sb.append("  ").append(bidi.unicodeWrap(message.getText() == null ? "" : message.getText()));
            return sb;
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int color) {
            return new TextAppearanceSpan((String) null, 0, 0, ColorStateList.valueOf(color), (ColorStateList) null);
        }

        public void addCompatExtras(Bundle extras) {
            super.addCompatExtras(extras);
            extras.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            extras.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            extras.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                extras.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                extras.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.getBundleArrayForMessages(this.mMessages));
            }
            if (this.mIsGroupConversation != null) {
                extras.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, this.mIsGroupConversation.booleanValue());
            }
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void restoreFromCompatExtras(Bundle extras) {
            this.mMessages.clear();
            if (extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(extras.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                this.mUser = new Person.Builder().setName(extras.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            this.mConversationTitle = extras.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            if (this.mConversationTitle == null) {
                this.mConversationTitle = extras.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelables = extras.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelables != null) {
                this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelables));
            }
            if (extras.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(extras.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            @Nullable
            private String mDataMimeType;
            @Nullable
            private Uri mDataUri;
            private Bundle mExtras;
            @Nullable
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence text, long timestamp, @Nullable Person person) {
                this.mExtras = new Bundle();
                this.mText = text;
                this.mTimestamp = timestamp;
                this.mPerson = person;
            }

            @Deprecated
            public Message(CharSequence text, long timestamp, CharSequence sender) {
                this(text, timestamp, new Person.Builder().setName(sender).build());
            }

            public Message setData(String dataMimeType, Uri dataUri) {
                this.mDataMimeType = dataMimeType;
                this.mDataUri = dataUri;
                return this;
            }

            @NonNull
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                if (this.mPerson == null) {
                    return null;
                }
                return this.mPerson.getName();
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                if (this.mText != null) {
                    bundle.putCharSequence(KEY_TEXT, this.mText);
                }
                bundle.putLong(KEY_TIMESTAMP, this.mTimestamp);
                if (this.mPerson != null) {
                    bundle.putCharSequence(KEY_SENDER, this.mPerson.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle.putParcelable(KEY_NOTIFICATION_PERSON, this.mPerson.toAndroidPerson());
                    } else {
                        bundle.putBundle(KEY_PERSON, this.mPerson.toBundle());
                    }
                }
                if (this.mDataMimeType != null) {
                    bundle.putString(KEY_DATA_MIME_TYPE, this.mDataMimeType);
                }
                if (this.mDataUri != null) {
                    bundle.putParcelable(KEY_DATA_URI, this.mDataUri);
                }
                if (this.mExtras != null) {
                    bundle.putBundle(KEY_EXTRAS_BUNDLE, this.mExtras);
                }
                return bundle;
            }

            @NonNull
            static Bundle[] getBundleArrayForMessages(List<Message> messages) {
                Bundle[] bundles = new Bundle[messages.size()];
                int N = messages.size();
                for (int i = 0; i < N; i++) {
                    bundles[i] = messages.get(i).toBundle();
                }
                return bundles;
            }

            @NonNull
            static List<Message> getMessagesFromBundleArray(Parcelable[] bundles) {
                Message message;
                List<Message> messages = new ArrayList<>(bundles.length);
                for (int i = 0; i < bundles.length; i++) {
                    if ((bundles[i] instanceof Bundle) && (message = getMessageFromBundle(bundles[i])) != null) {
                        messages.add(message);
                    }
                }
                return messages;
            }

            @Nullable
            static Message getMessageFromBundle(Bundle bundle) {
                try {
                    if (bundle.containsKey(KEY_TEXT)) {
                        if (bundle.containsKey(KEY_TIMESTAMP)) {
                            Person person = null;
                            if (bundle.containsKey(KEY_PERSON)) {
                                person = Person.fromBundle(bundle.getBundle(KEY_PERSON));
                            } else if (bundle.containsKey(KEY_NOTIFICATION_PERSON) && Build.VERSION.SDK_INT >= 28) {
                                person = Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(KEY_NOTIFICATION_PERSON));
                            } else if (bundle.containsKey(KEY_SENDER)) {
                                person = new Person.Builder().setName(bundle.getCharSequence(KEY_SENDER)).build();
                            }
                            Message message = new Message(bundle.getCharSequence(KEY_TEXT), bundle.getLong(KEY_TIMESTAMP), person);
                            if (bundle.containsKey(KEY_DATA_MIME_TYPE) && bundle.containsKey(KEY_DATA_URI)) {
                                message.setData(bundle.getString(KEY_DATA_MIME_TYPE), (Uri) bundle.getParcelable(KEY_DATA_URI));
                            }
                            if (bundle.containsKey(KEY_EXTRAS_BUNDLE)) {
                                message.getExtras().putAll(bundle.getBundle(KEY_EXTRAS_BUNDLE));
                            }
                            return message;
                        }
                    }
                    return null;
                } catch (ClassCastException e) {
                    return null;
                }
            }
        }
    }

    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> mTexts = new ArrayList<>();

        public InboxStyle() {
        }

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle setBigContentTitle(CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle addLine(CharSequence cs) {
            this.mTexts.add(Builder.limitCharSequenceLength(cs));
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.InboxStyle style = new Notification.InboxStyle(builder.getBuilder()).setBigContentTitle(this.mBigContentTitle);
                if (this.mSummaryTextSet) {
                    style.setSummaryText(this.mSummaryText);
                }
                Iterator<CharSequence> it = this.mTexts.iterator();
                while (it.hasNext()) {
                    style.addLine(it.next());
                }
            }
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT >= 24) {
                builder.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT < 24 && this.mBuilder.getContentView() != null) {
                return createRemoteViews(this.mBuilder.getContentView(), false);
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.mBuilder.getBigContentView();
            RemoteViews innerView = bigContentView != null ? bigContentView : this.mBuilder.getContentView();
            if (innerView == null) {
                return null;
            }
            return createRemoteViews(innerView, true);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUp = this.mBuilder.getHeadsUpContentView();
            RemoteViews innerView = headsUp != null ? headsUp : this.mBuilder.getContentView();
            if (headsUp == null) {
                return null;
            }
            return createRemoteViews(innerView, true);
        }

        private RemoteViews createRemoteViews(RemoteViews innerView, boolean showActions) {
            int numActions;
            int actionVisibility = 0;
            RemoteViews remoteViews = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            remoteViews.removeAllViews(R.id.actions);
            boolean actionsVisible = false;
            if (showActions && this.mBuilder.mActions != null && (numActions = Math.min(this.mBuilder.mActions.size(), 3)) > 0) {
                actionsVisible = true;
                for (int i = 0; i < numActions; i++) {
                    remoteViews.addView(R.id.actions, generateActionButton(this.mBuilder.mActions.get(i)));
                }
            }
            if (!actionsVisible) {
                actionVisibility = 8;
            }
            remoteViews.setViewVisibility(R.id.actions, actionVisibility);
            remoteViews.setViewVisibility(R.id.action_divider, actionVisibility);
            buildIntoRemoteViews(remoteViews, innerView);
            return remoteViews;
        }

        private RemoteViews generateActionButton(Action action) {
            boolean tombstone = action.actionIntent == null;
            RemoteViews button = new RemoteViews(this.mBuilder.mContext.getPackageName(), tombstone ? R.layout.notification_action_tombstone : R.layout.notification_action);
            button.setImageViewBitmap(R.id.action_image, createColoredBitmap(action.getIcon(), this.mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
            button.setTextViewText(R.id.action_text, action.title);
            if (!tombstone) {
                button.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            if (Build.VERSION.SDK_INT >= 15) {
                button.setContentDescription(R.id.action_container, action.title);
            }
            return button;
        }
    }

    public static class Action {
        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public Action(int icon2, CharSequence title2, PendingIntent intent) {
            this(icon2, title2, intent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true);
        }

        Action(int icon2, CharSequence title2, PendingIntent intent, Bundle extras, RemoteInput[] remoteInputs, RemoteInput[] dataOnlyRemoteInputs, boolean allowGeneratedReplies, int semanticAction, boolean showsUserInterface) {
            this.mShowsUserInterface = true;
            this.icon = icon2;
            this.title = Builder.limitCharSequenceLength(title2);
            this.actionIntent = intent;
            this.mExtras = extras != null ? extras : new Bundle();
            this.mRemoteInputs = remoteInputs;
            this.mDataOnlyRemoteInputs = dataOnlyRemoteInputs;
            this.mAllowGeneratedReplies = allowGeneratedReplies;
            this.mSemanticAction = semanticAction;
            this.mShowsUserInterface = showsUserInterface;
        }

        public int getIcon() {
            return this.icon;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int icon, CharSequence title, PendingIntent intent) {
                this(icon, title, intent, new Bundle(), (RemoteInput[]) null, true, 0, true);
            }

            public Builder(Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.mShowsUserInterface);
            }

            private Builder(int icon, CharSequence title, PendingIntent intent, Bundle extras, RemoteInput[] remoteInputs, boolean allowGeneratedReplies, int semanticAction, boolean showsUserInterface) {
                ArrayList<RemoteInput> arrayList;
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = icon;
                this.mTitle = Builder.limitCharSequenceLength(title);
                this.mIntent = intent;
                this.mExtras = extras;
                if (remoteInputs == null) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList<>(Arrays.asList(remoteInputs));
                }
                this.mRemoteInputs = arrayList;
                this.mAllowGeneratedReplies = allowGeneratedReplies;
                this.mSemanticAction = semanticAction;
                this.mShowsUserInterface = showsUserInterface;
            }

            public Builder addExtras(Bundle extras) {
                if (extras != null) {
                    this.mExtras.putAll(extras);
                }
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }

            public Builder setAllowGeneratedReplies(boolean allowGeneratedReplies) {
                this.mAllowGeneratedReplies = allowGeneratedReplies;
                return this;
            }

            public Builder setSemanticAction(int semanticAction) {
                this.mSemanticAction = semanticAction;
                return this;
            }

            public Builder setShowsUserInterface(boolean showsUserInterface) {
                this.mShowsUserInterface = showsUserInterface;
                return this;
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            /* JADX WARNING: type inference failed for: r2v5, types: [java.lang.Object[]] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.support.v4.app.NotificationCompat.Action build() {
                /*
                    r14 = this;
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>()
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r1.<init>()
                    java.util.ArrayList<android.support.v4.app.RemoteInput> r2 = r14.mRemoteInputs
                    if (r2 == 0) goto L_0x002e
                    java.util.ArrayList<android.support.v4.app.RemoteInput> r2 = r14.mRemoteInputs
                    java.util.Iterator r2 = r2.iterator()
                L_0x0014:
                    boolean r3 = r2.hasNext()
                    if (r3 == 0) goto L_0x002e
                    java.lang.Object r3 = r2.next()
                    android.support.v4.app.RemoteInput r3 = (android.support.v4.app.RemoteInput) r3
                    boolean r4 = r3.isDataOnly()
                    if (r4 == 0) goto L_0x002a
                    r0.add(r3)
                    goto L_0x002d
                L_0x002a:
                    r1.add(r3)
                L_0x002d:
                    goto L_0x0014
                L_0x002e:
                    boolean r2 = r0.isEmpty()
                    r3 = 0
                    if (r2 == 0) goto L_0x0037
                    r10 = r3
                    goto L_0x0044
                L_0x0037:
                    int r2 = r0.size()
                    android.support.v4.app.RemoteInput[] r2 = new android.support.v4.app.RemoteInput[r2]
                    java.lang.Object[] r2 = r0.toArray(r2)
                    android.support.v4.app.RemoteInput[] r2 = (android.support.v4.app.RemoteInput[]) r2
                    r10 = r2
                L_0x0044:
                    boolean r2 = r1.isEmpty()
                    if (r2 == 0) goto L_0x004c
                L_0x004a:
                    r9 = r3
                    goto L_0x005a
                L_0x004c:
                    int r2 = r1.size()
                    android.support.v4.app.RemoteInput[] r2 = new android.support.v4.app.RemoteInput[r2]
                    java.lang.Object[] r2 = r1.toArray(r2)
                    r3 = r2
                    android.support.v4.app.RemoteInput[] r3 = (android.support.v4.app.RemoteInput[]) r3
                    goto L_0x004a
                L_0x005a:
                    android.support.v4.app.NotificationCompat$Action r2 = new android.support.v4.app.NotificationCompat$Action
                    int r5 = r14.mIcon
                    java.lang.CharSequence r6 = r14.mTitle
                    android.app.PendingIntent r7 = r14.mIntent
                    android.os.Bundle r8 = r14.mExtras
                    boolean r11 = r14.mAllowGeneratedReplies
                    int r12 = r14.mSemanticAction
                    boolean r13 = r14.mShowsUserInterface
                    r4 = r2
                    r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompat.Action.Builder.build():android.support.v4.app.NotificationCompat$Action");
            }
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags = 1;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
            }

            public WearableExtender(Action action) {
                Bundle wearableBundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (wearableBundle != null) {
                    this.mFlags = wearableBundle.getInt(KEY_FLAGS, 1);
                    this.mInProgressLabel = wearableBundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = wearableBundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = wearableBundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            public Builder extend(Builder builder) {
                Bundle wearableBundle = new Bundle();
                if (this.mFlags != 1) {
                    wearableBundle.putInt(KEY_FLAGS, this.mFlags);
                }
                if (this.mInProgressLabel != null) {
                    wearableBundle.putCharSequence(KEY_IN_PROGRESS_LABEL, this.mInProgressLabel);
                }
                if (this.mConfirmLabel != null) {
                    wearableBundle.putCharSequence(KEY_CONFIRM_LABEL, this.mConfirmLabel);
                }
                if (this.mCancelLabel != null) {
                    wearableBundle.putCharSequence(KEY_CANCEL_LABEL, this.mCancelLabel);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, wearableBundle);
                return builder;
            }

            public WearableExtender clone() {
                WearableExtender that = new WearableExtender();
                that.mFlags = this.mFlags;
                that.mInProgressLabel = this.mInProgressLabel;
                that.mConfirmLabel = this.mConfirmLabel;
                that.mCancelLabel = this.mCancelLabel;
                return that;
            }

            public WearableExtender setAvailableOffline(boolean availableOffline) {
                setFlag(1, availableOffline);
                return this;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            private void setFlag(int mask, boolean value) {
                if (value) {
                    this.mFlags |= mask;
                } else {
                    this.mFlags &= ~mask;
                }
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence label) {
                this.mInProgressLabel = label;
                return this;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence label) {
                this.mConfirmLabel = label;
                return this;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence label) {
                this.mCancelLabel = label;
                return this;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            public WearableExtender setHintLaunchesActivity(boolean hintLaunchesActivity) {
                setFlag(2, hintLaunchesActivity);
                return this;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            public WearableExtender setHintDisplayActionInline(boolean hintDisplayInline) {
                setFlag(4, hintDisplayInline);
                return this;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions = new ArrayList<>();
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex = -1;
        private int mContentIcon;
        private int mContentIconGravity = 8388613;
        private int mCustomContentHeight;
        private int mCustomSizePreset = 0;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags = 1;
        private int mGravity = 80;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages = new ArrayList<>();

        public WearableExtender() {
        }

        public WearableExtender(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle wearableBundle = extras != null ? extras.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (wearableBundle != null) {
                ArrayList<Parcelable> parcelables = wearableBundle.getParcelableArrayList(KEY_ACTIONS);
                if (Build.VERSION.SDK_INT >= 16 && parcelables != null) {
                    Action[] actions = new Action[parcelables.size()];
                    for (int i = 0; i < actions.length; i++) {
                        if (Build.VERSION.SDK_INT >= 20) {
                            actions[i] = NotificationCompat.getActionCompatFromAction((Notification.Action) parcelables.get(i));
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            actions[i] = NotificationCompatJellybean.getActionFromBundle((Bundle) parcelables.get(i));
                        }
                    }
                    Collections.addAll(this.mActions, actions);
                }
                this.mFlags = wearableBundle.getInt(KEY_FLAGS, 1);
                this.mDisplayIntent = (PendingIntent) wearableBundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] pages = NotificationCompat.getNotificationArrayFromBundle(wearableBundle, KEY_PAGES);
                if (pages != null) {
                    Collections.addAll(this.mPages, pages);
                }
                this.mBackground = (Bitmap) wearableBundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = wearableBundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = wearableBundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = wearableBundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = wearableBundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = wearableBundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = wearableBundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = wearableBundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = wearableBundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = wearableBundle.getString(KEY_BRIDGE_TAG);
            }
        }

        public Builder extend(Builder builder) {
            Bundle wearableBundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                if (Build.VERSION.SDK_INT >= 16) {
                    ArrayList<Parcelable> parcelables = new ArrayList<>(this.mActions.size());
                    Iterator<Action> it = this.mActions.iterator();
                    while (it.hasNext()) {
                        Action action = it.next();
                        if (Build.VERSION.SDK_INT >= 20) {
                            parcelables.add(getActionFromActionCompat(action));
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            parcelables.add(NotificationCompatJellybean.getBundleForAction(action));
                        }
                    }
                    wearableBundle.putParcelableArrayList(KEY_ACTIONS, parcelables);
                } else {
                    wearableBundle.putParcelableArrayList(KEY_ACTIONS, (ArrayList) null);
                }
            }
            if (this.mFlags != 1) {
                wearableBundle.putInt(KEY_FLAGS, this.mFlags);
            }
            if (this.mDisplayIntent != null) {
                wearableBundle.putParcelable(KEY_DISPLAY_INTENT, this.mDisplayIntent);
            }
            if (!this.mPages.isEmpty()) {
                wearableBundle.putParcelableArray(KEY_PAGES, (Parcelable[]) this.mPages.toArray(new Notification[this.mPages.size()]));
            }
            if (this.mBackground != null) {
                wearableBundle.putParcelable(KEY_BACKGROUND, this.mBackground);
            }
            if (this.mContentIcon != 0) {
                wearableBundle.putInt(KEY_CONTENT_ICON, this.mContentIcon);
            }
            if (this.mContentIconGravity != 8388613) {
                wearableBundle.putInt(KEY_CONTENT_ICON_GRAVITY, this.mContentIconGravity);
            }
            if (this.mContentActionIndex != -1) {
                wearableBundle.putInt(KEY_CONTENT_ACTION_INDEX, this.mContentActionIndex);
            }
            if (this.mCustomSizePreset != 0) {
                wearableBundle.putInt(KEY_CUSTOM_SIZE_PRESET, this.mCustomSizePreset);
            }
            if (this.mCustomContentHeight != 0) {
                wearableBundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, this.mCustomContentHeight);
            }
            if (this.mGravity != 80) {
                wearableBundle.putInt(KEY_GRAVITY, this.mGravity);
            }
            if (this.mHintScreenTimeout != 0) {
                wearableBundle.putInt(KEY_HINT_SCREEN_TIMEOUT, this.mHintScreenTimeout);
            }
            if (this.mDismissalId != null) {
                wearableBundle.putString(KEY_DISMISSAL_ID, this.mDismissalId);
            }
            if (this.mBridgeTag != null) {
                wearableBundle.putString(KEY_BRIDGE_TAG, this.mBridgeTag);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, wearableBundle);
            return builder;
        }

        @RequiresApi(20)
        private static Notification.Action getActionFromActionCompat(Action actionCompat) {
            Bundle actionExtras;
            Notification.Action.Builder actionBuilder = new Notification.Action.Builder(actionCompat.getIcon(), actionCompat.getTitle(), actionCompat.getActionIntent());
            if (actionCompat.getExtras() != null) {
                actionExtras = new Bundle(actionCompat.getExtras());
            } else {
                actionExtras = new Bundle();
            }
            actionExtras.putBoolean("android.support.allowGeneratedReplies", actionCompat.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                actionBuilder.setAllowGeneratedReplies(actionCompat.getAllowGeneratedReplies());
            }
            actionBuilder.addExtras(actionExtras);
            RemoteInput[] remoteInputCompats = actionCompat.getRemoteInputs();
            if (remoteInputCompats != null) {
                for (RemoteInput remoteInput : RemoteInput.fromCompat(remoteInputCompats)) {
                    actionBuilder.addRemoteInput(remoteInput);
                }
            }
            return actionBuilder.build();
        }

        public WearableExtender clone() {
            WearableExtender that = new WearableExtender();
            that.mActions = new ArrayList<>(this.mActions);
            that.mFlags = this.mFlags;
            that.mDisplayIntent = this.mDisplayIntent;
            that.mPages = new ArrayList<>(this.mPages);
            that.mBackground = this.mBackground;
            that.mContentIcon = this.mContentIcon;
            that.mContentIconGravity = this.mContentIconGravity;
            that.mContentActionIndex = this.mContentActionIndex;
            that.mCustomSizePreset = this.mCustomSizePreset;
            that.mCustomContentHeight = this.mCustomContentHeight;
            that.mGravity = this.mGravity;
            that.mHintScreenTimeout = this.mHintScreenTimeout;
            that.mDismissalId = this.mDismissalId;
            that.mBridgeTag = this.mBridgeTag;
            return that;
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> actions) {
            this.mActions.addAll(actions);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        public WearableExtender setDisplayIntent(PendingIntent intent) {
            this.mDisplayIntent = intent;
            return this;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        public WearableExtender addPage(Notification page) {
            this.mPages.add(page);
            return this;
        }

        public WearableExtender addPages(List<Notification> pages) {
            this.mPages.addAll(pages);
            return this;
        }

        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public List<Notification> getPages() {
            return this.mPages;
        }

        public WearableExtender setBackground(Bitmap background) {
            this.mBackground = background;
            return this;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        @Deprecated
        public WearableExtender setContentIcon(int icon) {
            this.mContentIcon = icon;
            return this;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int contentIconGravity) {
            this.mContentIconGravity = contentIconGravity;
            return this;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public WearableExtender setContentAction(int actionIndex) {
            this.mContentActionIndex = actionIndex;
            return this;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public WearableExtender setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int sizePreset) {
            this.mCustomSizePreset = sizePreset;
            return this;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int height) {
            this.mCustomContentHeight = height;
            return this;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        public WearableExtender setStartScrollBottom(boolean startScrollBottom) {
            setFlag(8, startScrollBottom);
            return this;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean contentIntentAvailableOffline) {
            setFlag(1, contentIntentAvailableOffline);
            return this;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean hintHideIcon) {
            setFlag(2, hintHideIcon);
            return this;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean hintShowBackgroundOnly) {
            setFlag(4, hintShowBackgroundOnly);
            return this;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean hintAvoidBackgroundClipping) {
            setFlag(16, hintAvoidBackgroundClipping);
            return this;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int timeout) {
            this.mHintScreenTimeout = timeout;
            return this;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        public WearableExtender setHintAmbientBigPicture(boolean hintAmbientBigPicture) {
            setFlag(32, hintAmbientBigPicture);
            return this;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean hintContentIntentLaunchesActivity) {
            setFlag(64, hintContentIntentLaunchesActivity);
            return this;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        public WearableExtender setDismissalId(String dismissalId) {
            this.mDismissalId = dismissalId;
            return this;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public WearableExtender setBridgeTag(String bridgeTag) {
            this.mBridgeTag = bridgeTag;
            return this;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        private void setFlag(int mask, boolean value) {
            if (value) {
                this.mFlags |= mask;
            } else {
                this.mFlags &= ~mask;
            }
        }
    }

    public static final class CarExtender implements Extender {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor = 0;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
        }

        public CarExtender(Notification notification) {
            Bundle carBundle;
            if (Build.VERSION.SDK_INT >= 21) {
                if (NotificationCompat.getExtras(notification) == null) {
                    carBundle = null;
                } else {
                    carBundle = NotificationCompat.getExtras(notification).getBundle(EXTRA_CAR_EXTENDER);
                }
                if (carBundle != null) {
                    this.mLargeIcon = (Bitmap) carBundle.getParcelable(EXTRA_LARGE_ICON);
                    this.mColor = carBundle.getInt(EXTRA_COLOR, 0);
                    this.mUnreadConversation = getUnreadConversationFromBundle(carBundle.getBundle(EXTRA_CONVERSATION));
                }
            }
        }

        @RequiresApi(21)
        private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle b) {
            Bundle bundle = b;
            RemoteInput remoteInputCompat = null;
            if (bundle == null) {
                return null;
            }
            Parcelable[] parcelableMessages = bundle.getParcelableArray(KEY_MESSAGES);
            String[] messages = null;
            if (parcelableMessages != null) {
                String[] tmp = new String[parcelableMessages.length];
                boolean success = true;
                int i = 0;
                while (true) {
                    if (i >= tmp.length) {
                        break;
                    } else if (!(parcelableMessages[i] instanceof Bundle)) {
                        success = false;
                        break;
                    } else {
                        tmp[i] = ((Bundle) parcelableMessages[i]).getString(KEY_TEXT);
                        if (tmp[i] == null) {
                            success = false;
                            break;
                        }
                        i++;
                    }
                }
                if (!success) {
                    return null;
                }
                messages = tmp;
            }
            PendingIntent onRead = (PendingIntent) bundle.getParcelable(KEY_ON_READ);
            PendingIntent onReply = (PendingIntent) bundle.getParcelable(KEY_ON_REPLY);
            RemoteInput remoteInput = (RemoteInput) bundle.getParcelable(KEY_REMOTE_INPUT);
            String[] participants = bundle.getStringArray(KEY_PARTICIPANTS);
            if (participants == null || participants.length != 1) {
                return null;
            }
            if (remoteInput != null) {
                remoteInputCompat = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras(), (Set<String>) null);
            }
            return new UnreadConversation(messages, remoteInputCompat, onReply, onRead, participants, bundle.getLong(KEY_TIMESTAMP));
        }

        @RequiresApi(21)
        private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation uc) {
            Bundle b = new Bundle();
            String author = null;
            if (uc.getParticipants() != null && uc.getParticipants().length > 1) {
                author = uc.getParticipants()[0];
            }
            Parcelable[] messages = new Parcelable[uc.getMessages().length];
            for (int i = 0; i < messages.length; i++) {
                Bundle m = new Bundle();
                m.putString(KEY_TEXT, uc.getMessages()[i]);
                m.putString(KEY_AUTHOR, author);
                messages[i] = m;
            }
            b.putParcelableArray(KEY_MESSAGES, messages);
            RemoteInput remoteInputCompat = uc.getRemoteInput();
            if (remoteInputCompat != null) {
                b.putParcelable(KEY_REMOTE_INPUT, new RemoteInput.Builder(remoteInputCompat.getResultKey()).setLabel(remoteInputCompat.getLabel()).setChoices(remoteInputCompat.getChoices()).setAllowFreeFormInput(remoteInputCompat.getAllowFreeFormInput()).addExtras(remoteInputCompat.getExtras()).build());
            }
            b.putParcelable(KEY_ON_REPLY, uc.getReplyPendingIntent());
            b.putParcelable(KEY_ON_READ, uc.getReadPendingIntent());
            b.putStringArray(KEY_PARTICIPANTS, uc.getParticipants());
            b.putLong(KEY_TIMESTAMP, uc.getLatestTimestamp());
            return b;
        }

        public Builder extend(Builder builder) {
            if (Build.VERSION.SDK_INT < 21) {
                return builder;
            }
            Bundle carExtensions = new Bundle();
            if (this.mLargeIcon != null) {
                carExtensions.putParcelable(EXTRA_LARGE_ICON, this.mLargeIcon);
            }
            if (this.mColor != 0) {
                carExtensions.putInt(EXTRA_COLOR, this.mColor);
            }
            if (this.mUnreadConversation != null) {
                carExtensions.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(this.mUnreadConversation));
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, carExtensions);
            return builder;
        }

        public CarExtender setColor(@ColorInt int color) {
            this.mColor = color;
            return this;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        public CarExtender setLargeIcon(Bitmap largeIcon) {
            this.mLargeIcon = largeIcon;
            return this;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            UnreadConversation(String[] messages, RemoteInput remoteInput, PendingIntent replyPendingIntent, PendingIntent readPendingIntent, String[] participants, long latestTimestamp) {
                this.mMessages = messages;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = readPendingIntent;
                this.mReplyPendingIntent = replyPendingIntent;
                this.mParticipants = participants;
                this.mLatestTimestamp = latestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public String getParticipant() {
                if (this.mParticipants.length > 0) {
                    return this.mParticipants[0];
                }
                return null;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages = new ArrayList();
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String name) {
                    this.mParticipant = name;
                }

                public Builder addMessage(String message) {
                    this.mMessages.add(message);
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setLatestTimestamp(long timestamp) {
                    this.mLatestTimestamp = timestamp;
                    return this;
                }

                public UnreadConversation build() {
                    String[] participants = {this.mParticipant};
                    return new UnreadConversation((String[]) this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, participants, this.mLatestTimestamp);
                }
            }
        }
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle, String key) {
        Parcelable[] array = bundle.getParcelableArray(key);
        if ((array instanceof Notification[]) || array == null) {
            return (Notification[]) array;
        }
        Notification[] typedArray = new Notification[array.length];
        for (int i = 0; i < array.length; i++) {
            typedArray[i] = (Notification) array[i];
        }
        bundle.putParcelableArray(key, typedArray);
        return typedArray;
    }

    @Nullable
    public static Bundle getExtras(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification);
        }
        return null;
    }

    public static int getActionCount(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (notification.actions != null) {
                return notification.actions.length;
            }
            return 0;
        } else if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getActionCount(notification);
        } else {
            return 0;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.os.Bundle} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.app.NotificationCompat.Action getAction(android.app.Notification r6, int r7) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 20
            if (r0 < r1) goto L_0x000f
            android.app.Notification$Action[] r0 = r6.actions
            r0 = r0[r7]
            android.support.v4.app.NotificationCompat$Action r0 = getActionCompatFromAction(r0)
            return r0
        L_0x000f:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 19
            if (r0 < r1) goto L_0x0036
            android.app.Notification$Action[] r0 = r6.actions
            r0 = r0[r7]
            r1 = 0
            android.os.Bundle r2 = r6.extras
            java.lang.String r3 = "android.support.actionExtras"
            android.util.SparseArray r2 = r2.getSparseParcelableArray(r3)
            if (r2 == 0) goto L_0x002b
            java.lang.Object r3 = r2.get(r7)
            r1 = r3
            android.os.Bundle r1 = (android.os.Bundle) r1
        L_0x002b:
            int r3 = r0.icon
            java.lang.CharSequence r4 = r0.title
            android.app.PendingIntent r5 = r0.actionIntent
            android.support.v4.app.NotificationCompat$Action r3 = android.support.v4.app.NotificationCompatJellybean.readAction(r3, r4, r5, r1)
            return r3
        L_0x0036:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x0041
            android.support.v4.app.NotificationCompat$Action r0 = android.support.v4.app.NotificationCompatJellybean.getAction(r6, r7)
            return r0
        L_0x0041:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.NotificationCompat.getAction(android.app.Notification, int):android.support.v4.app.NotificationCompat$Action");
    }

    @RequiresApi(20)
    static Action getActionCompatFromAction(Notification.Action action) {
        RemoteInput[] remoteInputs;
        boolean z;
        int semanticAction;
        RemoteInput[] srcArray = action.getRemoteInputs();
        if (srcArray == null) {
            remoteInputs = null;
        } else {
            remoteInputs = new RemoteInput[srcArray.length];
            for (int i = 0; i < srcArray.length; i++) {
                RemoteInput src = srcArray[i];
                remoteInputs[i] = new RemoteInput(src.getResultKey(), src.getLabel(), src.getChoices(), src.getAllowFreeFormInput(), src.getExtras(), (Set<String>) null);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            z = action.getExtras().getBoolean("android.support.allowGeneratedReplies") || action.getAllowGeneratedReplies();
        } else {
            z = action.getExtras().getBoolean("android.support.allowGeneratedReplies");
        }
        boolean allowGeneratedReplies = z;
        boolean showsUserInterface = action.getExtras().getBoolean("android.support.action.showsUserInterface", true);
        if (Build.VERSION.SDK_INT >= 28) {
            semanticAction = action.getSemanticAction();
        } else {
            semanticAction = action.getExtras().getInt("android.support.action.semanticAction", 0);
        }
        return new Action(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputs, (RemoteInput[]) null, allowGeneratedReplies, semanticAction, showsUserInterface);
    }

    @RequiresApi(21)
    public static List<Action> getInvisibleActions(Notification notification) {
        Bundle listBundle;
        ArrayList<Action> result = new ArrayList<>();
        Bundle carExtenderBundle = notification.extras.getBundle("android.car.EXTENSIONS");
        if (!(carExtenderBundle == null || (listBundle = carExtenderBundle.getBundle("invisible_actions")) == null)) {
            for (int i = 0; i < listBundle.size(); i++) {
                result.add(NotificationCompatJellybean.getActionFromBundle(listBundle.getBundle(Integer.toString(i))));
            }
        }
        return result;
    }

    @RequiresApi(19)
    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    public static String getCategory(Notification notification) {
        if (Build.VERSION.SDK_INT >= 21) {
            return notification.category;
        }
        return null;
    }

    public static boolean getLocalOnly(Notification notification) {
        if (Build.VERSION.SDK_INT >= 20) {
            if ((notification.flags & 256) != 0) {
                return true;
            }
            return false;
        } else if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras.getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
        } else {
            if (Build.VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification).getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            }
            return false;
        }
    }

    public static String getGroup(Notification notification) {
        if (Build.VERSION.SDK_INT >= 20) {
            return notification.getGroup();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras.getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification).getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        return null;
    }

    public static boolean isGroupSummary(Notification notification) {
        if (Build.VERSION.SDK_INT >= 20) {
            if ((notification.flags & 512) != 0) {
                return true;
            }
            return false;
        } else if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras.getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
        } else {
            if (Build.VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification).getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            }
            return false;
        }
    }

    public static String getSortKey(Notification notification) {
        if (Build.VERSION.SDK_INT >= 20) {
            return notification.getSortKey();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras.getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification).getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        return null;
    }

    public static String getChannelId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getChannelId();
        }
        return null;
    }

    public static long getTimeoutAfter(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getTimeoutAfter();
        }
        return 0;
    }

    public static int getBadgeIconType(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getBadgeIconType();
        }
        return 0;
    }

    public static String getShortcutId(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getShortcutId();
        }
        return null;
    }

    public static int getGroupAlertBehavior(Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getGroupAlertBehavior();
        }
        return 0;
    }
}
