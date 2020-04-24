package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.media.MediaDescriptionCompatApi21;
import android.support.v4.media.MediaDescriptionCompatApi23;
import android.text.TextUtils;

public final class MediaDescriptionCompat implements Parcelable {
    public static final long BT_FOLDER_TYPE_ALBUMS = 2;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3;
    public static final long BT_FOLDER_TYPE_GENRES = 4;
    public static final long BT_FOLDER_TYPE_MIXED = 0;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5;
    public static final long BT_FOLDER_TYPE_TITLES = 1;
    public static final long BT_FOLDER_TYPE_YEARS = 6;
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new Parcelable.Creator<MediaDescriptionCompat>() {
        public MediaDescriptionCompat createFromParcel(Parcel in) {
            if (Build.VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(in);
            }
            return MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(in));
        }

        public MediaDescriptionCompat[] newArray(int size) {
            return new MediaDescriptionCompat[size];
        }
    };
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
    public static final long STATUS_DOWNLOADED = 2;
    public static final long STATUS_DOWNLOADING = 1;
    public static final long STATUS_NOT_DOWNLOADED = 0;
    private final CharSequence mDescription;
    private Object mDescriptionObj;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    MediaDescriptionCompat(String mediaId, CharSequence title, CharSequence subtitle, CharSequence description, Bitmap icon, Uri iconUri, Bundle extras, Uri mediaUri) {
        this.mMediaId = mediaId;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mDescription = description;
        this.mIcon = icon;
        this.mIconUri = iconUri;
        this.mExtras = extras;
        this.mMediaUri = mediaUri;
    }

    MediaDescriptionCompat(Parcel in) {
        this.mMediaId = in.readString();
        this.mTitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.mSubtitle = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.mDescription = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        ClassLoader loader = getClass().getClassLoader();
        this.mIcon = (Bitmap) in.readParcelable(loader);
        this.mIconUri = (Uri) in.readParcelable(loader);
        this.mExtras = in.readBundle(loader);
        this.mMediaUri = (Uri) in.readParcelable(loader);
    }

    @Nullable
    public String getMediaId() {
        return this.mMediaId;
    }

    @Nullable
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    @Nullable
    public CharSequence getDescription() {
        return this.mDescription;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    @Nullable
    public Uri getIconUri() {
        return this.mIconUri;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Nullable
    public Uri getMediaUri() {
        return this.mMediaUri;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (Build.VERSION.SDK_INT < 21) {
            dest.writeString(this.mMediaId);
            TextUtils.writeToParcel(this.mTitle, dest, flags);
            TextUtils.writeToParcel(this.mSubtitle, dest, flags);
            TextUtils.writeToParcel(this.mDescription, dest, flags);
            dest.writeParcelable(this.mIcon, flags);
            dest.writeParcelable(this.mIconUri, flags);
            dest.writeBundle(this.mExtras);
            dest.writeParcelable(this.mMediaUri, flags);
            return;
        }
        MediaDescriptionCompatApi21.writeToParcel(getMediaDescription(), dest, flags);
    }

    public String toString() {
        return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
    }

    public Object getMediaDescription() {
        if (this.mDescriptionObj != null || Build.VERSION.SDK_INT < 21) {
            return this.mDescriptionObj;
        }
        Object bob = MediaDescriptionCompatApi21.Builder.newInstance();
        MediaDescriptionCompatApi21.Builder.setMediaId(bob, this.mMediaId);
        MediaDescriptionCompatApi21.Builder.setTitle(bob, this.mTitle);
        MediaDescriptionCompatApi21.Builder.setSubtitle(bob, this.mSubtitle);
        MediaDescriptionCompatApi21.Builder.setDescription(bob, this.mDescription);
        MediaDescriptionCompatApi21.Builder.setIconBitmap(bob, this.mIcon);
        MediaDescriptionCompatApi21.Builder.setIconUri(bob, this.mIconUri);
        Bundle extras = this.mExtras;
        if (Build.VERSION.SDK_INT < 23 && this.mMediaUri != null) {
            if (extras == null) {
                extras = new Bundle();
                extras.putBoolean(DESCRIPTION_KEY_NULL_BUNDLE_FLAG, true);
            }
            extras.putParcelable(DESCRIPTION_KEY_MEDIA_URI, this.mMediaUri);
        }
        MediaDescriptionCompatApi21.Builder.setExtras(bob, extras);
        if (Build.VERSION.SDK_INT >= 23) {
            MediaDescriptionCompatApi23.Builder.setMediaUri(bob, this.mMediaUri);
        }
        this.mDescriptionObj = MediaDescriptionCompatApi21.Builder.build(bob);
        return this.mDescriptionObj;
    }

    /* JADX WARNING: type inference failed for: r3v9, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat fromMediaDescription(java.lang.Object r5) {
        /*
            if (r5 == 0) goto L_0x0084
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0084
            android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
            r0.<init>()
            java.lang.String r1 = android.support.v4.media.MediaDescriptionCompatApi21.getMediaId(r5)
            r0.setMediaId(r1)
            java.lang.CharSequence r1 = android.support.v4.media.MediaDescriptionCompatApi21.getTitle(r5)
            r0.setTitle(r1)
            java.lang.CharSequence r1 = android.support.v4.media.MediaDescriptionCompatApi21.getSubtitle(r5)
            r0.setSubtitle(r1)
            java.lang.CharSequence r1 = android.support.v4.media.MediaDescriptionCompatApi21.getDescription(r5)
            r0.setDescription(r1)
            android.graphics.Bitmap r1 = android.support.v4.media.MediaDescriptionCompatApi21.getIconBitmap(r5)
            r0.setIconBitmap(r1)
            android.net.Uri r1 = android.support.v4.media.MediaDescriptionCompatApi21.getIconUri(r5)
            r0.setIconUri(r1)
            android.os.Bundle r1 = android.support.v4.media.MediaDescriptionCompatApi21.getExtras(r5)
            r2 = 0
            if (r1 == 0) goto L_0x004a
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r1)
            java.lang.String r3 = "android.support.v4.media.description.MEDIA_URI"
            android.os.Parcelable r3 = r1.getParcelable(r3)
            r2 = r3
            android.net.Uri r2 = (android.net.Uri) r2
        L_0x004a:
            if (r2 == 0) goto L_0x0067
            java.lang.String r3 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r3 = r1.containsKey(r3)
            if (r3 == 0) goto L_0x005d
            int r3 = r1.size()
            r4 = 2
            if (r3 != r4) goto L_0x005d
            r1 = 0
            goto L_0x0067
        L_0x005d:
            java.lang.String r3 = "android.support.v4.media.description.MEDIA_URI"
            r1.remove(r3)
            java.lang.String r3 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            r1.remove(r3)
        L_0x0067:
            r0.setExtras(r1)
            if (r2 == 0) goto L_0x0070
            r0.setMediaUri(r2)
            goto L_0x007d
        L_0x0070:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r3 < r4) goto L_0x007d
            android.net.Uri r3 = android.support.v4.media.MediaDescriptionCompatApi23.getMediaUri(r5)
            r0.setMediaUri(r3)
        L_0x007d:
            android.support.v4.media.MediaDescriptionCompat r3 = r0.build()
            r3.mDescriptionObj = r5
            return r3
        L_0x0084:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.fromMediaDescription(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    public static final class Builder {
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder setMediaId(@Nullable String mediaId) {
            this.mMediaId = mediaId;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setSubtitle(@Nullable CharSequence subtitle) {
            this.mSubtitle = subtitle;
            return this;
        }

        public Builder setDescription(@Nullable CharSequence description) {
            this.mDescription = description;
            return this;
        }

        public Builder setIconBitmap(@Nullable Bitmap icon) {
            this.mIcon = icon;
            return this;
        }

        public Builder setIconUri(@Nullable Uri iconUri) {
            this.mIconUri = iconUri;
            return this;
        }

        public Builder setExtras(@Nullable Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public Builder setMediaUri(@Nullable Uri mediaUri) {
            this.mMediaUri = mediaUri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }
    }
}
