package android.support.p001v4.media;

import android.media.VolumeProvider;

/* renamed from: android.support.v4.media.VolumeProviderCompatApi21 */
/* loaded from: classes.dex */
class VolumeProviderCompatApi21 {

    /* renamed from: android.support.v4.media.VolumeProviderCompatApi21$Delegate */
    /* loaded from: classes.dex */
    public interface Delegate {
        void onAdjustVolume(int i);

        void onSetVolumeTo(int i);
    }

    public static Object createVolumeProvider(int volumeControl, int maxVolume, int currentVolume, final Delegate delegate) {
        return new VolumeProvider(volumeControl, maxVolume, currentVolume) { // from class: android.support.v4.media.VolumeProviderCompatApi21.1
            @Override // android.media.VolumeProvider
            public void onSetVolumeTo(int volume) {
                delegate.onSetVolumeTo(volume);
            }

            @Override // android.media.VolumeProvider
            public void onAdjustVolume(int direction) {
                delegate.onAdjustVolume(direction);
            }
        };
    }

    public static void setCurrentVolume(Object volumeProviderObj, int currentVolume) {
        ((VolumeProvider) volumeProviderObj).setCurrentVolume(currentVolume);
    }

    private VolumeProviderCompatApi21() {
    }
}
