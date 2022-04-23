package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
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

    public String getMediaId() {
        return this.mMediaId;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

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
        Object build = MediaDescriptionCompatApi21.Builder.build(bob);
        this.mDescriptionObj = build;
        return build;
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v4.media.MediaDescriptionCompat fromMediaDescription(java.lang.Object r7) {
        /*
            if (r7 == 0) goto L_0x0080
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L_0x0080
            android.support.v4.media.MediaDescriptionCompat$Builder r0 = new android.support.v4.media.MediaDescriptionCompat$Builder
            r0.<init>()
            java.lang.String r1 = android.support.v4.media.MediaDescriptionCompatApi21.getMediaId(r7)
            r0.setMediaId(r1)
            java.lang.CharSequence r1 = android.support.v4.media.MediaDescriptionCompatApi21.getTitle(r7)
            r0.setTitle(r1)
            java.lang.CharSequence r1 = android.support.v4.media.MediaDescriptionCompatApi21.getSubtitle(r7)
            r0.setSubtitle(r1)
            java.lang.CharSequence r1 = android.support.v4.media.MediaDescriptionCompatApi21.getDescription(r7)
            r0.setDescription(r1)
            android.graphics.Bitmap r1 = android.support.v4.media.MediaDescriptionCompatApi21.getIconBitmap(r7)
            r0.setIconBitmap(r1)
            android.net.Uri r1 = android.support.v4.media.MediaDescriptionCompatApi21.getIconUri(r7)
            r0.setIconUri(r1)
            android.os.Bundle r1 = android.support.v4.media.MediaDescriptionCompatApi21.getExtras(r7)
            r2 = 0
            java.lang.String r3 = "android.support.v4.media.description.MEDIA_URI"
            if (r1 == 0) goto L_0x004a
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r1)
            android.os.Parcelable r4 = r1.getParcelable(r3)
            r2 = r4
            android.net.Uri r2 = (android.net.Uri) r2
        L_0x004a:
            if (r2 == 0) goto L_0x0063
            java.lang.String r4 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r5 = r1.containsKey(r4)
            if (r5 == 0) goto L_0x005d
            int r5 = r1.size()
            r6 = 2
            if (r5 != r6) goto L_0x005d
            r1 = 0
            goto L_0x0063
        L_0x005d:
            r1.remove(r3)
            r1.remove(r4)
        L_0x0063:
            r0.setExtras(r1)
            if (r2 == 0) goto L_0x006c
            r0.setMediaUri(r2)
            goto L_0x0079
        L_0x006c:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 23
            if (r3 < r4) goto L_0x0079
            android.net.Uri r3 = android.support.v4.media.MediaDescriptionCompatApi23.getMediaUri(r7)
            r0.setMediaUri(r3)
        L_0x0079:
            android.support.v4.media.MediaDescriptionCompat r3 = r0.build()
            r3.mDescriptionObj = r7
            return r3
        L_0x0080:
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

        public Builder setMediaId(String mediaId) {
            this.mMediaId = mediaId;
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.mTitle = title;
            return this;
        }

        public Builder setSubtitle(CharSequence subtitle) {
            this.mSubtitle = subtitle;
            return this;
        }

        public Builder setDescription(CharSequence description) {
            this.mDescription = description;
            return this;
        }

        public Builder setIconBitmap(Bitmap icon) {
            this.mIcon = icon;
            return this;
        }

        public Builder setIconUri(Uri iconUri) {
            this.mIconUri = iconUri;
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public Builder setMediaUri(Uri mediaUri) {
            this.mMediaUri = mediaUri;
            return this;
        }

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, this.mMediaUri);
        }
    }
}
