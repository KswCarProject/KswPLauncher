package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import java.util.List;

public interface IMediaControllerCallback extends IInterface {
    void onCaptioningEnabledChanged(boolean z) throws RemoteException;

    void onEvent(String str, Bundle bundle) throws RemoteException;

    void onExtrasChanged(Bundle bundle) throws RemoteException;

    void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException;

    void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException;

    void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException;

    void onQueueTitleChanged(CharSequence charSequence) throws RemoteException;

    void onRepeatModeChanged(int i) throws RemoteException;

    void onSessionDestroyed() throws RemoteException;

    void onSessionReady() throws RemoteException;

    void onShuffleModeChanged(int i) throws RemoteException;

    void onShuffleModeChangedRemoved(boolean z) throws RemoteException;

    void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaControllerCallback {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
        static final int TRANSACTION_onCaptioningEnabledChanged = 11;
        static final int TRANSACTION_onEvent = 1;
        static final int TRANSACTION_onExtrasChanged = 7;
        static final int TRANSACTION_onMetadataChanged = 4;
        static final int TRANSACTION_onPlaybackStateChanged = 3;
        static final int TRANSACTION_onQueueChanged = 5;
        static final int TRANSACTION_onQueueTitleChanged = 6;
        static final int TRANSACTION_onRepeatModeChanged = 9;
        static final int TRANSACTION_onSessionDestroyed = 2;
        static final int TRANSACTION_onSessionReady = 13;
        static final int TRANSACTION_onShuffleModeChanged = 12;
        static final int TRANSACTION_onShuffleModeChangedRemoved = 10;
        static final int TRANSACTION_onVolumeInfoChanged = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaControllerCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMediaControllerCallback)) {
                return new Proxy(obj);
            }
            return (IMediaControllerCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.support.v4.media.session.PlaybackStateCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: android.support.v4.media.MediaMetadataCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: android.support.v4.media.session.ParcelableVolumeInfo} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r2v7, types: [android.support.v4.media.session.PlaybackStateCompat] */
        /* JADX WARNING: type inference failed for: r2v10, types: [android.support.v4.media.MediaMetadataCompat] */
        /* JADX WARNING: type inference failed for: r0v21, types: [java.lang.CharSequence] */
        /* JADX WARNING: type inference failed for: r2v13, types: [java.lang.CharSequence] */
        /* JADX WARNING: type inference failed for: r2v19, types: [android.support.v4.media.session.ParcelableVolumeInfo] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r5 == r0) goto L_0x0111
                r0 = 0
                r2 = 0
                switch(r5) {
                    case 1: goto L_0x00f4;
                    case 2: goto L_0x00eb;
                    case 3: goto L_0x00d0;
                    case 4: goto L_0x00b5;
                    case 5: goto L_0x00a6;
                    case 6: goto L_0x008b;
                    case 7: goto L_0x0070;
                    case 8: goto L_0x0055;
                    case 9: goto L_0x0048;
                    case 10: goto L_0x0037;
                    case 11: goto L_0x0026;
                    case 12: goto L_0x0019;
                    case 13: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r0 = super.onTransact(r5, r6, r7, r8)
                return r0
            L_0x0010:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                r4.onSessionReady()
                return r1
            L_0x0019:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                r4.onShuffleModeChanged(r0)
                return r1
            L_0x0026:
                java.lang.String r2 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x0033
                r0 = r1
            L_0x0033:
                r4.onCaptioningEnabledChanged(r0)
                return r1
            L_0x0037:
                java.lang.String r2 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r2)
                int r2 = r6.readInt()
                if (r2 == 0) goto L_0x0044
                r0 = r1
            L_0x0044:
                r4.onShuffleModeChangedRemoved(r0)
                return r1
            L_0x0048:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                r4.onRepeatModeChanged(r0)
                return r1
            L_0x0055:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x006a
                android.os.Parcelable$Creator<android.support.v4.media.session.ParcelableVolumeInfo> r0 = android.support.v4.media.session.ParcelableVolumeInfo.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                r2 = r0
                android.support.v4.media.session.ParcelableVolumeInfo r2 = (android.support.v4.media.session.ParcelableVolumeInfo) r2
                goto L_0x006b
            L_0x006a:
            L_0x006b:
                r0 = r2
                r4.onVolumeInfoChanged(r0)
                return r1
            L_0x0070:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x0085
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                r2 = r0
                android.os.Bundle r2 = (android.os.Bundle) r2
                goto L_0x0086
            L_0x0085:
            L_0x0086:
                r0 = r2
                r4.onExtrasChanged(r0)
                return r1
            L_0x008b:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x00a0
                android.os.Parcelable$Creator r0 = android.text.TextUtils.CHAR_SEQUENCE_CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                r2 = r0
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                goto L_0x00a1
            L_0x00a0:
            L_0x00a1:
                r0 = r2
                r4.onQueueTitleChanged(r0)
                return r1
            L_0x00a6:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                android.os.Parcelable$Creator<android.support.v4.media.session.MediaSessionCompat$QueueItem> r0 = android.support.v4.media.session.MediaSessionCompat.QueueItem.CREATOR
                java.util.ArrayList r0 = r6.createTypedArrayList(r0)
                r4.onQueueChanged(r0)
                return r1
            L_0x00b5:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x00ca
                android.os.Parcelable$Creator<android.support.v4.media.MediaMetadataCompat> r0 = android.support.v4.media.MediaMetadataCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                r2 = r0
                android.support.v4.media.MediaMetadataCompat r2 = (android.support.v4.media.MediaMetadataCompat) r2
                goto L_0x00cb
            L_0x00ca:
            L_0x00cb:
                r0 = r2
                r4.onMetadataChanged(r0)
                return r1
            L_0x00d0:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x00e5
                android.os.Parcelable$Creator<android.support.v4.media.session.PlaybackStateCompat> r0 = android.support.v4.media.session.PlaybackStateCompat.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r6)
                r2 = r0
                android.support.v4.media.session.PlaybackStateCompat r2 = (android.support.v4.media.session.PlaybackStateCompat) r2
                goto L_0x00e6
            L_0x00e5:
            L_0x00e6:
                r0 = r2
                r4.onPlaybackStateChanged(r0)
                return r1
            L_0x00eb:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                r4.onSessionDestroyed()
                return r1
            L_0x00f4:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r6.enforceInterface(r0)
                java.lang.String r0 = r6.readString()
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x010c
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r6)
                android.os.Bundle r2 = (android.os.Bundle) r2
                goto L_0x010d
            L_0x010c:
            L_0x010d:
                r4.onEvent(r0, r2)
                return r1
            L_0x0111:
                java.lang.String r0 = "android.support.v4.media.session.IMediaControllerCallback"
                r7.writeString(r0)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.session.IMediaControllerCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaControllerCallback {
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

            public void onEvent(String event, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(event);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(1, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onSessionDestroyed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metadata != null) {
                        _data.writeInt(1);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(4, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(queue);
                    this.mRemote.transact(5, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onQueueTitleChanged(CharSequence title) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (title != null) {
                        _data.writeInt(1);
                        TextUtils.writeToParcel(title, _data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(6, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onExtrasChanged(Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(7, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (info != null) {
                        _data.writeInt(1);
                        info.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(8, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onRepeatModeChanged(int repeatMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(repeatMode);
                    this.mRemote.transact(9, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onShuffleModeChangedRemoved(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled);
                    this.mRemote.transact(10, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onCaptioningEnabledChanged(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled);
                    this.mRemote.transact(11, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onShuffleModeChanged(int shuffleMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(shuffleMode);
                    this.mRemote.transact(12, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void onSessionReady() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, (Parcel) null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
