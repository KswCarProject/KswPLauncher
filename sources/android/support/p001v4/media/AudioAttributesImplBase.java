package android.support.p001v4.media;

import android.os.Bundle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.Arrays;

/* renamed from: android.support.v4.media.AudioAttributesImplBase */
/* loaded from: classes.dex */
class AudioAttributesImplBase implements AudioAttributesImpl {
    int mContentType;
    int mFlags;
    int mLegacyStream;
    int mUsage;

    AudioAttributesImplBase() {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
        this.mLegacyStream = -1;
    }

    AudioAttributesImplBase(int contentType, int flags, int usage, int legacyStream) {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
        this.mLegacyStream = -1;
        this.mContentType = contentType;
        this.mFlags = flags;
        this.mUsage = usage;
        this.mLegacyStream = legacyStream;
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public Object getAudioAttributes() {
        return null;
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i = this.mLegacyStream;
        if (i != -1) {
            return i;
        }
        return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.mLegacyStream;
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public int getContentType() {
        return this.mContentType;
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public int getUsage() {
        return this.mUsage;
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public int getFlags() {
        int flags = this.mFlags;
        int legacyStream = getLegacyStreamType();
        if (legacyStream == 6) {
            flags |= 4;
        } else if (legacyStream == 7) {
            flags |= 1;
        }
        return flags & BaseQuickAdapter.HEADER_VIEW;
    }

    @Override // android.support.p001v4.media.AudioAttributesImpl
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("android.support.v4.media.audio_attrs.USAGE", this.mUsage);
        bundle.putInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", this.mContentType);
        bundle.putInt("android.support.v4.media.audio_attrs.FLAGS", this.mFlags);
        int i = this.mLegacyStream;
        if (i != -1) {
            bundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    public boolean equals(Object o) {
        if (o instanceof AudioAttributesImplBase) {
            AudioAttributesImplBase that = (AudioAttributesImplBase) o;
            return this.mContentType == that.getContentType() && this.mFlags == that.getFlags() && this.mUsage == that.getUsage() && this.mLegacyStream == that.mLegacyStream;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream != -1) {
            sb.append(" stream=").append(this.mLegacyStream);
            sb.append(" derived");
        }
        sb.append(" usage=").append(AudioAttributesCompat.usageToString(this.mUsage)).append(" content=").append(this.mContentType).append(" flags=0x").append(Integer.toHexString(this.mFlags).toUpperCase());
        return sb.toString();
    }

    public static AudioAttributesImpl fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        int usage = bundle.getInt("android.support.v4.media.audio_attrs.USAGE", 0);
        int contentType = bundle.getInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", 0);
        int flags = bundle.getInt("android.support.v4.media.audio_attrs.FLAGS", 0);
        int legacyStream = bundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1);
        return new AudioAttributesImplBase(contentType, flags, usage, legacyStream);
    }
}
