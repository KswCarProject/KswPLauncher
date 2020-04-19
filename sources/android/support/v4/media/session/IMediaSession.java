package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.List;

public interface IMediaSession extends IInterface {
    void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException;

    void adjustVolume(int i, int i2, String str) throws RemoteException;

    void fastForward() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    long getFlags() throws RemoteException;

    PendingIntent getLaunchPendingIntent() throws RemoteException;

    MediaMetadataCompat getMetadata() throws RemoteException;

    String getPackageName() throws RemoteException;

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException;

    CharSequence getQueueTitle() throws RemoteException;

    int getRatingType() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    String getTag() throws RemoteException;

    ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    boolean isShuffleModeEnabledRemoved() throws RemoteException;

    boolean isTransportControlEnabled() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFromMediaId(String str, Bundle bundle) throws RemoteException;

    void playFromSearch(String str, Bundle bundle) throws RemoteException;

    void playFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void prepare() throws RemoteException;

    void prepareFromMediaId(String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void previous() throws RemoteException;

    void rate(RatingCompat ratingCompat) throws RemoteException;

    void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void removeQueueItemAt(int i) throws RemoteException;

    void rewind() throws RemoteException;

    void seekTo(long j) throws RemoteException;

    void sendCommand(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) throws RemoteException;

    void sendCustomAction(String str, Bundle bundle) throws RemoteException;

    boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException;

    void setCaptioningEnabled(boolean z) throws RemoteException;

    void setRepeatMode(int i) throws RemoteException;

    void setShuffleMode(int i) throws RemoteException;

    void setShuffleModeEnabledRemoved(boolean z) throws RemoteException;

    void setVolumeTo(int i, int i2, String str) throws RemoteException;

    void skipToQueueItem(long j) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_addQueueItem = 41;
        static final int TRANSACTION_addQueueItemAt = 42;
        static final int TRANSACTION_adjustVolume = 11;
        static final int TRANSACTION_fastForward = 22;
        static final int TRANSACTION_getExtras = 31;
        static final int TRANSACTION_getFlags = 9;
        static final int TRANSACTION_getLaunchPendingIntent = 8;
        static final int TRANSACTION_getMetadata = 27;
        static final int TRANSACTION_getPackageName = 6;
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getQueue = 29;
        static final int TRANSACTION_getQueueTitle = 30;
        static final int TRANSACTION_getRatingType = 32;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getShuffleMode = 47;
        static final int TRANSACTION_getTag = 7;
        static final int TRANSACTION_getVolumeAttributes = 10;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_isShuffleModeEnabledRemoved = 38;
        static final int TRANSACTION_isTransportControlEnabled = 5;
        static final int TRANSACTION_next = 20;
        static final int TRANSACTION_pause = 18;
        static final int TRANSACTION_play = 13;
        static final int TRANSACTION_playFromMediaId = 14;
        static final int TRANSACTION_playFromSearch = 15;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_prepare = 33;
        static final int TRANSACTION_prepareFromMediaId = 34;
        static final int TRANSACTION_prepareFromSearch = 35;
        static final int TRANSACTION_prepareFromUri = 36;
        static final int TRANSACTION_previous = 21;
        static final int TRANSACTION_rate = 25;
        static final int TRANSACTION_rateWithExtras = 51;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_removeQueueItem = 43;
        static final int TRANSACTION_removeQueueItemAt = 44;
        static final int TRANSACTION_rewind = 23;
        static final int TRANSACTION_seekTo = 24;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendCustomAction = 26;
        static final int TRANSACTION_sendMediaButton = 2;
        static final int TRANSACTION_setCaptioningEnabled = 46;
        static final int TRANSACTION_setRepeatMode = 39;
        static final int TRANSACTION_setShuffleMode = 48;
        static final int TRANSACTION_setShuffleModeEnabledRemoved = 40;
        static final int TRANSACTION_setVolumeTo = 12;
        static final int TRANSACTION_skipToQueueItem = 17;
        static final int TRANSACTION_stop = 19;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaSession)) {
                return new Proxy(obj);
            }
            return (IMediaSession) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v9 */
        /* JADX WARNING: type inference failed for: r1v31 */
        /* JADX WARNING: type inference failed for: r1v60 */
        /* JADX WARNING: type inference failed for: r1v63 */
        /* JADX WARNING: type inference failed for: r1v67 */
        /* JADX WARNING: type inference failed for: r1v72 */
        /* JADX WARNING: type inference failed for: r1v73 */
        /* JADX WARNING: type inference failed for: r1v74 */
        /* JADX WARNING: type inference failed for: r1v75 */
        /* JADX WARNING: type inference failed for: r1v76 */
        /* JADX WARNING: type inference failed for: r1v77 */
        /* JADX WARNING: type inference failed for: r1v78 */
        /* JADX WARNING: type inference failed for: r1v79 */
        /* JADX WARNING: type inference failed for: r1v80 */
        /* JADX WARNING: type inference failed for: r1v81 */
        /* JADX WARNING: type inference failed for: r1v82 */
        /* JADX WARNING: type inference failed for: r1v83 */
        /* JADX WARNING: type inference failed for: r1v84 */
        /* JADX WARNING: type inference failed for: r1v85 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r6, android.os.Parcel r7, android.os.Parcel r8, int r9) throws android.os.RemoteException {
            /*
                r5 = this;
                r0 = 51
                r1 = 0
                r2 = 1
                if (r6 == r0) goto L_0x0446
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r6 == r0) goto L_0x0440
                r0 = 0
                switch(r6) {
                    case 1: goto L_0x0410;
                    case 2: goto L_0x03ee;
                    case 3: goto L_0x03da;
                    case 4: goto L_0x03c6;
                    case 5: goto L_0x03b6;
                    case 6: goto L_0x03a6;
                    case 7: goto L_0x0396;
                    case 8: goto L_0x037d;
                    case 9: goto L_0x036d;
                    case 10: goto L_0x0354;
                    case 11: goto L_0x033c;
                    case 12: goto L_0x0324;
                    case 13: goto L_0x0318;
                    case 14: goto L_0x02f8;
                    case 15: goto L_0x02d8;
                    case 16: goto L_0x02ac;
                    case 17: goto L_0x029c;
                    case 18: goto L_0x0290;
                    case 19: goto L_0x0284;
                    case 20: goto L_0x0278;
                    case 21: goto L_0x026c;
                    case 22: goto L_0x0260;
                    case 23: goto L_0x0254;
                    case 24: goto L_0x0244;
                    case 25: goto L_0x0226;
                    case 26: goto L_0x0206;
                    case 27: goto L_0x01ed;
                    case 28: goto L_0x01d4;
                    case 29: goto L_0x01c4;
                    case 30: goto L_0x01ab;
                    case 31: goto L_0x0192;
                    case 32: goto L_0x0182;
                    case 33: goto L_0x0176;
                    case 34: goto L_0x0156;
                    case 35: goto L_0x0136;
                    case 36: goto L_0x010a;
                    case 37: goto L_0x00fa;
                    case 38: goto L_0x00ea;
                    case 39: goto L_0x00da;
                    case 40: goto L_0x00c6;
                    case 41: goto L_0x00a8;
                    case 42: goto L_0x0086;
                    case 43: goto L_0x0068;
                    case 44: goto L_0x0058;
                    case 45: goto L_0x0048;
                    case 46: goto L_0x0034;
                    case 47: goto L_0x0024;
                    case 48: goto L_0x0014;
                    default: goto L_0x000f;
                }
            L_0x000f:
                boolean r0 = super.onTransact(r6, r7, r8, r9)
                return r0
            L_0x0014:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                r5.setShuffleMode(r0)
                r8.writeNoException()
                return r2
            L_0x0024:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r5.getShuffleMode()
                r8.writeNoException()
                r8.writeInt(r0)
                return r2
            L_0x0034:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                int r1 = r7.readInt()
                if (r1 == 0) goto L_0x0041
                r0 = r2
            L_0x0041:
                r5.setCaptioningEnabled(r0)
                r8.writeNoException()
                return r2
            L_0x0048:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                boolean r0 = r5.isCaptioningEnabled()
                r8.writeNoException()
                r8.writeInt(r0)
                return r2
            L_0x0058:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                r5.removeQueueItemAt(r0)
                r8.writeNoException()
                return r2
            L_0x0068:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x007d
                android.os.Parcelable$Creator<android.support.v4.media.MediaDescriptionCompat> r0 = android.support.v4.media.MediaDescriptionCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                r1 = r0
                android.support.v4.media.MediaDescriptionCompat r1 = (android.support.v4.media.MediaDescriptionCompat) r1
                goto L_0x007e
            L_0x007d:
            L_0x007e:
                r0 = r1
                r5.removeQueueItem(r0)
                r8.writeNoException()
                return r2
            L_0x0086:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x009b
                android.os.Parcelable$Creator<android.support.v4.media.MediaDescriptionCompat> r0 = android.support.v4.media.MediaDescriptionCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                r1 = r0
                android.support.v4.media.MediaDescriptionCompat r1 = (android.support.v4.media.MediaDescriptionCompat) r1
                goto L_0x009c
            L_0x009b:
            L_0x009c:
                r0 = r1
                int r1 = r7.readInt()
                r5.addQueueItemAt(r0, r1)
                r8.writeNoException()
                return r2
            L_0x00a8:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x00bd
                android.os.Parcelable$Creator<android.support.v4.media.MediaDescriptionCompat> r0 = android.support.v4.media.MediaDescriptionCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                r1 = r0
                android.support.v4.media.MediaDescriptionCompat r1 = (android.support.v4.media.MediaDescriptionCompat) r1
                goto L_0x00be
            L_0x00bd:
            L_0x00be:
                r0 = r1
                r5.addQueueItem(r0)
                r8.writeNoException()
                return r2
            L_0x00c6:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                int r1 = r7.readInt()
                if (r1 == 0) goto L_0x00d3
                r0 = r2
            L_0x00d3:
                r5.setShuffleModeEnabledRemoved(r0)
                r8.writeNoException()
                return r2
            L_0x00da:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                r5.setRepeatMode(r0)
                r8.writeNoException()
                return r2
            L_0x00ea:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                boolean r0 = r5.isShuffleModeEnabledRemoved()
                r8.writeNoException()
                r8.writeInt(r0)
                return r2
            L_0x00fa:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r5.getRepeatMode()
                r8.writeNoException()
                r8.writeInt(r0)
                return r2
            L_0x010a:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x011e
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.net.Uri r0 = (android.net.Uri) r0
                goto L_0x011f
            L_0x011e:
                r0 = r1
            L_0x011f:
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x012e
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x012f
            L_0x012e:
            L_0x012f:
                r5.prepareFromUri(r0, r1)
                r8.writeNoException()
                return r2
            L_0x0136:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r7.readString()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x014e
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x014f
            L_0x014e:
            L_0x014f:
                r5.prepareFromSearch(r0, r1)
                r8.writeNoException()
                return r2
            L_0x0156:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r7.readString()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x016e
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x016f
            L_0x016e:
            L_0x016f:
                r5.prepareFromMediaId(r0, r1)
                r8.writeNoException()
                return r2
            L_0x0176:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.prepare()
                r8.writeNoException()
                return r2
            L_0x0182:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r5.getRatingType()
                r8.writeNoException()
                r8.writeInt(r0)
                return r2
            L_0x0192:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                android.os.Bundle r1 = r5.getExtras()
                r8.writeNoException()
                if (r1 == 0) goto L_0x01a7
                r8.writeInt(r2)
                r1.writeToParcel(r8, r2)
                goto L_0x01aa
            L_0x01a7:
                r8.writeInt(r0)
            L_0x01aa:
                return r2
            L_0x01ab:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                java.lang.CharSequence r1 = r5.getQueueTitle()
                r8.writeNoException()
                if (r1 == 0) goto L_0x01c0
                r8.writeInt(r2)
                android.text.TextUtils.writeToParcel(r1, r8, r2)
                goto L_0x01c3
            L_0x01c0:
                r8.writeInt(r0)
            L_0x01c3:
                return r2
            L_0x01c4:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.util.List r0 = r5.getQueue()
                r8.writeNoException()
                r8.writeTypedList(r0)
                return r2
            L_0x01d4:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                android.support.v4.media.session.PlaybackStateCompat r1 = r5.getPlaybackState()
                r8.writeNoException()
                if (r1 == 0) goto L_0x01e9
                r8.writeInt(r2)
                r1.writeToParcel(r8, r2)
                goto L_0x01ec
            L_0x01e9:
                r8.writeInt(r0)
            L_0x01ec:
                return r2
            L_0x01ed:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                android.support.v4.media.MediaMetadataCompat r1 = r5.getMetadata()
                r8.writeNoException()
                if (r1 == 0) goto L_0x0202
                r8.writeInt(r2)
                r1.writeToParcel(r8, r2)
                goto L_0x0205
            L_0x0202:
                r8.writeInt(r0)
            L_0x0205:
                return r2
            L_0x0206:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r7.readString()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x021e
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x021f
            L_0x021e:
            L_0x021f:
                r5.sendCustomAction(r0, r1)
                r8.writeNoException()
                return r2
            L_0x0226:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x023b
                android.os.Parcelable$Creator<android.support.v4.media.RatingCompat> r0 = android.support.v4.media.RatingCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                r1 = r0
                android.support.v4.media.RatingCompat r1 = (android.support.v4.media.RatingCompat) r1
                goto L_0x023c
            L_0x023b:
            L_0x023c:
                r0 = r1
                r5.rate(r0)
                r8.writeNoException()
                return r2
            L_0x0244:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                long r0 = r7.readLong()
                r5.seekTo(r0)
                r8.writeNoException()
                return r2
            L_0x0254:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.rewind()
                r8.writeNoException()
                return r2
            L_0x0260:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.fastForward()
                r8.writeNoException()
                return r2
            L_0x026c:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.previous()
                r8.writeNoException()
                return r2
            L_0x0278:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.next()
                r8.writeNoException()
                return r2
            L_0x0284:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.stop()
                r8.writeNoException()
                return r2
            L_0x0290:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.pause()
                r8.writeNoException()
                return r2
            L_0x029c:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                long r0 = r7.readLong()
                r5.skipToQueueItem(r0)
                r8.writeNoException()
                return r2
            L_0x02ac:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x02c0
                android.os.Parcelable$Creator r0 = android.net.Uri.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.net.Uri r0 = (android.net.Uri) r0
                goto L_0x02c1
            L_0x02c0:
                r0 = r1
            L_0x02c1:
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x02d0
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x02d1
            L_0x02d0:
            L_0x02d1:
                r5.playFromUri(r0, r1)
                r8.writeNoException()
                return r2
            L_0x02d8:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r7.readString()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x02f0
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x02f1
            L_0x02f0:
            L_0x02f1:
                r5.playFromSearch(r0, r1)
                r8.writeNoException()
                return r2
            L_0x02f8:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r7.readString()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x0310
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x0311
            L_0x0310:
            L_0x0311:
                r5.playFromMediaId(r0, r1)
                r8.writeNoException()
                return r2
            L_0x0318:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                r5.play()
                r8.writeNoException()
                return r2
            L_0x0324:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                int r1 = r7.readInt()
                java.lang.String r3 = r7.readString()
                r5.setVolumeTo(r0, r1, r3)
                r8.writeNoException()
                return r2
            L_0x033c:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                int r1 = r7.readInt()
                java.lang.String r3 = r7.readString()
                r5.adjustVolume(r0, r1, r3)
                r8.writeNoException()
                return r2
            L_0x0354:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                android.support.v4.media.session.ParcelableVolumeInfo r1 = r5.getVolumeAttributes()
                r8.writeNoException()
                if (r1 == 0) goto L_0x0369
                r8.writeInt(r2)
                r1.writeToParcel(r8, r2)
                goto L_0x036c
            L_0x0369:
                r8.writeInt(r0)
            L_0x036c:
                return r2
            L_0x036d:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                long r0 = r5.getFlags()
                r8.writeNoException()
                r8.writeLong(r0)
                return r2
            L_0x037d:
                java.lang.String r1 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r1)
                android.app.PendingIntent r1 = r5.getLaunchPendingIntent()
                r8.writeNoException()
                if (r1 == 0) goto L_0x0392
                r8.writeInt(r2)
                r1.writeToParcel(r8, r2)
                goto L_0x0395
            L_0x0392:
                r8.writeInt(r0)
            L_0x0395:
                return r2
            L_0x0396:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r5.getTag()
                r8.writeNoException()
                r8.writeString(r0)
                return r2
            L_0x03a6:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r5.getPackageName()
                r8.writeNoException()
                r8.writeString(r0)
                return r2
            L_0x03b6:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                boolean r0 = r5.isTransportControlEnabled()
                r8.writeNoException()
                r8.writeInt(r0)
                return r2
            L_0x03c6:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                android.support.v4.media.session.IMediaControllerCallback r0 = android.support.v4.media.session.IMediaControllerCallback.Stub.asInterface(r0)
                r5.unregisterCallbackListener(r0)
                r8.writeNoException()
                return r2
            L_0x03da:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                android.os.IBinder r0 = r7.readStrongBinder()
                android.support.v4.media.session.IMediaControllerCallback r0 = android.support.v4.media.session.IMediaControllerCallback.Stub.asInterface(r0)
                r5.registerCallbackListener(r0)
                r8.writeNoException()
                return r2
            L_0x03ee:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x0403
                android.os.Parcelable$Creator r0 = android.view.KeyEvent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                r1 = r0
                android.view.KeyEvent r1 = (android.view.KeyEvent) r1
                goto L_0x0404
            L_0x0403:
            L_0x0404:
                r0 = r1
                boolean r1 = r5.sendMediaButton(r0)
                r8.writeNoException()
                r8.writeInt(r1)
                return r2
            L_0x0410:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                java.lang.String r0 = r7.readString()
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x0428
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r7)
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x0429
            L_0x0428:
                r3 = r1
            L_0x0429:
                int r4 = r7.readInt()
                if (r4 == 0) goto L_0x0438
                android.os.Parcelable$Creator<android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper> r1 = android.support.v4.media.session.MediaSessionCompat.ResultReceiverWrapper.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper r1 = (android.support.v4.media.session.MediaSessionCompat.ResultReceiverWrapper) r1
                goto L_0x0439
            L_0x0438:
            L_0x0439:
                r5.sendCommand(r0, r3, r1)
                r8.writeNoException()
                return r2
            L_0x0440:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r8.writeString(r0)
                return r2
            L_0x0446:
                java.lang.String r0 = "android.support.v4.media.session.IMediaSession"
                r7.enforceInterface(r0)
                int r0 = r7.readInt()
                if (r0 == 0) goto L_0x045a
                android.os.Parcelable$Creator<android.support.v4.media.RatingCompat> r0 = android.support.v4.media.RatingCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r7)
                android.support.v4.media.RatingCompat r0 = (android.support.v4.media.RatingCompat) r0
                goto L_0x045b
            L_0x045a:
                r0 = r1
            L_0x045b:
                int r3 = r7.readInt()
                if (r3 == 0) goto L_0x046a
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r1.createFromParcel(r7)
                android.os.Bundle r1 = (android.os.Bundle) r1
                goto L_0x046b
            L_0x046a:
            L_0x046b:
                r5.rateWithExtras(r0, r1)
                r8.writeNoException()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.IMediaSession.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaSession {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void sendCommand(String command, Bundle args, MediaSessionCompat.ResultReceiverWrapper cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(command);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (cb != null) {
                        _data.writeInt(1);
                        cb.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean sendMediaButton(KeyEvent mediaButton) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (mediaButton != null) {
                        _data.writeInt(1);
                        mediaButton.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void registerCallbackListener(IMediaControllerCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void unregisterCallbackListener(IMediaControllerCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isTransportControlEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getPackageName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getTag() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public PendingIntent getLaunchPendingIntent() throws RemoteException {
                PendingIntent _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getFlags() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readLong();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ParcelableVolumeInfo getVolumeAttributes() throws RemoteException {
                ParcelableVolumeInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelableVolumeInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void adjustVolume(int direction, int flags, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(direction);
                    _data.writeInt(flags);
                    _data.writeString(packageName);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setVolumeTo(int value, int flags, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(value);
                    _data.writeInt(flags);
                    _data.writeString(packageName);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public MediaMetadataCompat getMetadata() throws RemoteException {
                MediaMetadataCompat _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = MediaMetadataCompat.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                PlaybackStateCompat _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PlaybackStateCompat.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public CharSequence getQueueTitle() throws RemoteException {
                CharSequence _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle getExtras() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getRatingType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isCaptioningEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getRepeatMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isShuffleModeEnabledRemoved() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getShuffleMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addQueueItem(MediaDescriptionCompat description) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (description != null) {
                        _data.writeInt(1);
                        description.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addQueueItemAt(MediaDescriptionCompat description, int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (description != null) {
                        _data.writeInt(1);
                        description.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(index);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeQueueItem(MediaDescriptionCompat description) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (description != null) {
                        _data.writeInt(1);
                        description.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeQueueItemAt(int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(index);
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void prepare() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void prepareFromMediaId(String uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uri);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void prepareFromSearch(String string, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(string);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void prepareFromUri(Uri uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void play() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void playFromMediaId(String uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uri);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void playFromSearch(String string, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(string);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void playFromUri(Uri uri, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void skipToQueueItem(long id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(id);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void stop() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void next() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void previous() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void fastForward() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void rewind() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void seekTo(long pos) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(pos);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void rate(RatingCompat rating) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rating != null) {
                        _data.writeInt(1);
                        rating.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void rateWithExtras(RatingCompat rating, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rating != null) {
                        _data.writeInt(1);
                        rating.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(51, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setCaptioningEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled);
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setRepeatMode(int repeatMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(repeatMode);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setShuffleModeEnabledRemoved(boolean shuffleMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(shuffleMode);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setShuffleMode(int shuffleMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(shuffleMode);
                    this.mRemote.transact(48, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void sendCustomAction(String action, Bundle args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(action);
                    if (args != null) {
                        _data.writeInt(1);
                        args.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
