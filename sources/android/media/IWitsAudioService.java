package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWitsAudioService extends IInterface {
    int AUXINSetMute(long j) throws RemoteException;

    int RadioChangeMode(long j) throws RemoteException;

    byte[] RadioGetRDS() throws RemoteException;

    int RadioRDSCfg(int i) throws RemoteException;

    int RadioSetLongOrNear(long j) throws RemoteException;

    int RadioSetLongOrNearForAM(long j) throws RemoteException;

    int RadioSetMute(long j) throws RemoteException;

    int RadioTune(long j) throws RemoteException;

    int closeSivit() throws RemoteException;

    int codecInit() throws RemoteException;

    int getAudioMute() throws RemoteException;

    int getAudioVolume() throws RemoteException;

    int getRadioRssi() throws RemoteException;

    int getRadioST() throws RemoteException;

    int initSivit() throws RemoteException;

    void modeInCallSwitch(boolean z) throws RemoteException;

    void muteOtherMedia(boolean z, int i) throws RemoteException;

    int openBT(boolean z) throws RemoteException;

    int openDVD(boolean z) throws RemoteException;

    int openTV(boolean z) throws RemoteException;

    int setAudioMute(int i) throws RemoteException;

    int setAudioMuteWithNoAlert(int i) throws RemoteException;

    int setAudioVolume(int i) throws RemoteException;

    int setAudioVolumeForDigree(int i) throws RemoteException;

    int setDelay(int[] iArr) throws RemoteException;

    int setDspHifiEqBass(int i) throws RemoteException;

    int setDspHifiEqFilter(int[] iArr) throws RemoteException;

    int setDspMixVolume(int i, int i2) throws RemoteException;

    int setDspMixVolumeForAll(int[] iArr) throws RemoteException;

    int setDspSpkEqAppForDebug(int[] iArr) throws RemoteException;

    int setEQDspSpkVolumeInt(int i, int i2) throws RemoteException;

    int setRadioMonoStereo(int i) throws RemoteException;

    int setRadioMute(int i) throws RemoteException;

    int setTargetVolume(float f, int i) throws RemoteException;

    int setToneControlBass(int[] iArr) throws RemoteException;

    int setUserAudioSource(int[] iArr) throws RemoteException;

    int setUserDspSpkEqApp(int[] iArr) throws RemoteException;

    int setUserEq(int[] iArr) throws RemoteException;

    int setUserSpkEq(int[] iArr, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IWitsAudioService {
        private static final String DESCRIPTOR = "android.media.IWitsAudioService";
        static final int TRANSACTION_AUXINSetMute = 21;
        static final int TRANSACTION_RadioChangeMode = 22;
        static final int TRANSACTION_RadioGetRDS = 32;
        static final int TRANSACTION_RadioRDSCfg = 33;
        static final int TRANSACTION_RadioSetLongOrNear = 26;
        static final int TRANSACTION_RadioSetLongOrNearForAM = 27;
        static final int TRANSACTION_RadioSetMute = 20;
        static final int TRANSACTION_RadioTune = 23;
        static final int TRANSACTION_closeSivit = 2;
        static final int TRANSACTION_codecInit = 8;
        static final int TRANSACTION_getAudioMute = 7;
        static final int TRANSACTION_getAudioVolume = 6;
        static final int TRANSACTION_getRadioRssi = 25;
        static final int TRANSACTION_getRadioST = 24;
        static final int TRANSACTION_initSivit = 1;
        static final int TRANSACTION_modeInCallSwitch = 38;
        static final int TRANSACTION_muteOtherMedia = 39;
        static final int TRANSACTION_openBT = 34;
        static final int TRANSACTION_openDVD = 36;
        static final int TRANSACTION_openTV = 35;
        static final int TRANSACTION_setAudioMute = 3;
        static final int TRANSACTION_setAudioMuteWithNoAlert = 4;
        static final int TRANSACTION_setAudioVolume = 5;
        static final int TRANSACTION_setAudioVolumeForDigree = 30;
        static final int TRANSACTION_setDelay = 19;
        static final int TRANSACTION_setDspHifiEqBass = 11;
        static final int TRANSACTION_setDspHifiEqFilter = 10;
        static final int TRANSACTION_setDspMixVolume = 37;
        static final int TRANSACTION_setDspMixVolumeForAll = 17;
        static final int TRANSACTION_setDspSpkEqAppForDebug = 15;
        static final int TRANSACTION_setEQDspSpkVolumeInt = 12;
        static final int TRANSACTION_setRadioMonoStereo = 28;
        static final int TRANSACTION_setRadioMute = 29;
        static final int TRANSACTION_setTargetVolume = 31;
        static final int TRANSACTION_setToneControlBass = 16;
        static final int TRANSACTION_setUserAudioSource = 14;
        static final int TRANSACTION_setUserDspSpkEqApp = 18;
        static final int TRANSACTION_setUserEq = 9;
        static final int TRANSACTION_setUserSpkEq = 13;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWitsAudioService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IWitsAudioService)) {
                return new Proxy(obj);
            }
            return (IWitsAudioService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0 = false;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = initSivit();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = closeSivit();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = setAudioMute(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = setAudioMuteWithNoAlert(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = setAudioVolume(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getAudioVolume();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getAudioMute();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _result8 = codecInit();
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = setUserEq(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = setDspHifiEqFilter(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = setDspHifiEqBass(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _result12 = setEQDspSpkVolumeInt(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _result13 = setUserSpkEq(data.createIntArray(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int _result14 = setUserAudioSource(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result14);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _result15 = setDspSpkEqAppForDebug(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result15);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int _result16 = setToneControlBass(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result16);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _result17 = setDspMixVolumeForAll(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int _result18 = setUserDspSpkEqApp(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result18);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    int _result19 = setDelay(data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result19);
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    int _result20 = RadioSetMute(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result20);
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    int _result21 = AUXINSetMute(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result21);
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    int _result22 = RadioChangeMode(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result22);
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    int _result23 = RadioTune(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result23);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    int _result24 = getRadioST();
                    reply.writeNoException();
                    reply.writeInt(_result24);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    int _result25 = getRadioRssi();
                    reply.writeNoException();
                    reply.writeInt(_result25);
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    int _result26 = RadioSetLongOrNear(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result26);
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    int _result27 = RadioSetLongOrNearForAM(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(_result27);
                    return true;
                case 28:
                    data.enforceInterface(DESCRIPTOR);
                    int _result28 = setRadioMonoStereo(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result28);
                    return true;
                case 29:
                    data.enforceInterface(DESCRIPTOR);
                    int _result29 = setRadioMute(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result29);
                    return true;
                case 30:
                    data.enforceInterface(DESCRIPTOR);
                    int _result30 = setAudioVolumeForDigree(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result30);
                    return true;
                case 31:
                    data.enforceInterface(DESCRIPTOR);
                    int _result31 = setTargetVolume(data.readFloat(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result31);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result32 = RadioGetRDS();
                    reply.writeNoException();
                    reply.writeByteArray(_result32);
                    return true;
                case 33:
                    data.enforceInterface(DESCRIPTOR);
                    int _result33 = RadioRDSCfg(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result33);
                    return true;
                case 34:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    int _result34 = openBT(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result34);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    int _result35 = openTV(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result35);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    int _result36 = openDVD(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result36);
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    int _result37 = setDspMixVolume(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result37);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    modeInCallSwitch(_arg0);
                    reply.writeNoException();
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    muteOtherMedia(_arg0, data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IWitsAudioService {
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

            public int initSivit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int closeSivit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setAudioMute(int mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mute);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setAudioMuteWithNoAlert(int mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mute);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setAudioVolume(int vol) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(vol);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getAudioVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getAudioMute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int codecInit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setUserEq(int[] eq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setDspHifiEqFilter(int[] eq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setDspHifiEqBass(int bassVolume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(bassVolume);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setEQDspSpkVolumeInt(int flag, int speakerInt) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flag);
                    _data.writeInt(speakerInt);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setUserSpkEq(int[] eq, int index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    _data.writeInt(index);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setUserAudioSource(int[] audioSourceVol) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(audioSourceVol);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setDspSpkEqAppForDebug(int[] eq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setToneControlBass(int[] eq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setDspMixVolumeForAll(int[] eq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setUserDspSpkEqApp(int[] eq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(eq);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setDelay(int[] delay) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(delay);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int RadioSetMute(long mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(mute);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int AUXINSetMute(long mute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(mute);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int RadioChangeMode(long mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(mode);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int RadioTune(long freq) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(freq);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getRadioST() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getRadioRssi() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int RadioSetLongOrNear(long change) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(change);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int RadioSetLongOrNearForAM(long change) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(change);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setRadioMonoStereo(int valueInt) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(valueInt);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setRadioMute(int muteInt) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(muteInt);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setAudioVolumeForDigree(int volume) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(volume);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setTargetVolume(float volume, int pid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(volume);
                    _data.writeInt(pid);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] RadioGetRDS() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int RadioRDSCfg(int change) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(change);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int openBT(boolean open) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(open ? 1 : 0);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int openTV(boolean open) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(open ? 1 : 0);
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int openDVD(boolean open) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(open ? 1 : 0);
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int setDspMixVolume(int arm, int other) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(arm);
                    _data.writeInt(other);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void modeInCallSwitch(boolean open) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(open ? 1 : 0);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void muteOtherMedia(boolean mute, int mediaType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mute ? 1 : 0);
                    _data.writeInt(mediaType);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
