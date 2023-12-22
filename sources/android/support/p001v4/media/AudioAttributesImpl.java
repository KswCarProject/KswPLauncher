package android.support.p001v4.media;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;

/* renamed from: android.support.v4.media.AudioAttributesImpl */
/* loaded from: classes.dex */
interface AudioAttributesImpl extends VersionedParcelable {
    Object getAudioAttributes();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getRawLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();

    Bundle toBundle();
}
