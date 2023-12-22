package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
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

    /* loaded from: classes.dex */
    public static class Default implements IWitsAudioService {
        @Override // android.media.IWitsAudioService
        public int initSivit() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int closeSivit() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setAudioMute(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setAudioMuteWithNoAlert(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setAudioVolume(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int getAudioVolume() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int getAudioMute() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int codecInit() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setUserEq(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setDspHifiEqFilter(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setDspHifiEqBass(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setEQDspSpkVolumeInt(int var1, int var2) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setUserSpkEq(int[] var1, int var2) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setUserAudioSource(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setDspSpkEqAppForDebug(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setToneControlBass(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setDspMixVolumeForAll(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setUserDspSpkEqApp(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setDelay(int[] var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int RadioSetMute(long var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int AUXINSetMute(long var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int RadioChangeMode(long var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int RadioTune(long var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int getRadioST() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int getRadioRssi() throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int RadioSetLongOrNear(long var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int RadioSetLongOrNearForAM(long var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setRadioMonoStereo(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setRadioMute(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setAudioVolumeForDigree(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setTargetVolume(float var1, int var2) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public byte[] RadioGetRDS() throws RemoteException {
            return null;
        }

        @Override // android.media.IWitsAudioService
        public int RadioRDSCfg(int var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int openBT(boolean var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int openTV(boolean var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int openDVD(boolean var1) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public int setDspMixVolume(int var1, int var2) throws RemoteException {
            return 0;
        }

        @Override // android.media.IWitsAudioService
        public void modeInCallSwitch(boolean var1) throws RemoteException {
        }

        @Override // android.media.IWitsAudioService
        public void muteOtherMedia(boolean var1, int var2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
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
            if (iin != null && (iin instanceof IWitsAudioService)) {
                return (IWitsAudioService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
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
                    int[] _arg0 = data.createIntArray();
                    int _result9 = setUserEq(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    reply.writeIntArray(_arg0);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg02 = data.createIntArray();
                    int _result10 = setDspHifiEqFilter(_arg02);
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    reply.writeIntArray(_arg02);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = setDspHifiEqBass(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg03 = data.readInt();
                    int _arg1 = data.readInt();
                    int _result12 = setEQDspSpkVolumeInt(_arg03, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg04 = data.createIntArray();
                    int _arg12 = data.readInt();
                    int _result13 = setUserSpkEq(_arg04, _arg12);
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    reply.writeIntArray(_arg04);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg05 = data.createIntArray();
                    int _result14 = setUserAudioSource(_arg05);
                    reply.writeNoException();
                    reply.writeInt(_result14);
                    reply.writeIntArray(_arg05);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg06 = data.createIntArray();
                    int _result15 = setDspSpkEqAppForDebug(_arg06);
                    reply.writeNoException();
                    reply.writeInt(_result15);
                    reply.writeIntArray(_arg06);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg07 = data.createIntArray();
                    int _result16 = setToneControlBass(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result16);
                    reply.writeIntArray(_arg07);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg08 = data.createIntArray();
                    int _result17 = setDspMixVolumeForAll(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    reply.writeIntArray(_arg08);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg09 = data.createIntArray();
                    int _result18 = setUserDspSpkEqApp(_arg09);
                    reply.writeNoException();
                    reply.writeInt(_result18);
                    reply.writeIntArray(_arg09);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg010 = data.createIntArray();
                    int _result19 = setDelay(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result19);
                    reply.writeIntArray(_arg010);
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
                    float _arg011 = data.readFloat();
                    int _arg13 = data.readInt();
                    int _result31 = setTargetVolume(_arg011, _arg13);
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
                    int _result34 = openBT(data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeInt(_result34);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    int _result35 = openTV(data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeInt(_result35);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    int _result36 = openDVD(data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeInt(_result36);
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    int _arg012 = data.readInt();
                    int _arg14 = data.readInt();
                    int _result37 = setDspMixVolume(_arg012, _arg14);
                    reply.writeNoException();
                    reply.writeInt(_result37);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    modeInCallSwitch(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg013 = data.readInt() != 0;
                    int _arg15 = data.readInt();
                    muteOtherMedia(_arg013, _arg15);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IWitsAudioService {
            public static IWitsAudioService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.media.IWitsAudioService
            public int initSivit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().initSivit();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int closeSivit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closeSivit();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setAudioMute(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAudioMute(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setAudioMuteWithNoAlert(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAudioMuteWithNoAlert(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setAudioVolume(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAudioVolume(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int getAudioVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioVolume();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int getAudioMute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioMute();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int codecInit() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().codecInit();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setUserEq(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUserEq(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setDspHifiEqFilter(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDspHifiEqFilter(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setDspHifiEqBass(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDspHifiEqBass(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setEQDspSpkVolumeInt(int var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEQDspSpkVolumeInt(var1, var2);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setUserSpkEq(int[] var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUserSpkEq(var1, var2);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setUserAudioSource(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUserAudioSource(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setDspSpkEqAppForDebug(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDspSpkEqAppForDebug(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setToneControlBass(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setToneControlBass(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setDspMixVolumeForAll(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDspMixVolumeForAll(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setUserDspSpkEqApp(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUserDspSpkEqApp(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setDelay(int[] var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(var1);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDelay(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.readIntArray(var1);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int RadioSetMute(long var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(var1);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioSetMute(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int AUXINSetMute(long var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(var1);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().AUXINSetMute(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int RadioChangeMode(long var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(var1);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioChangeMode(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int RadioTune(long var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(var1);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioTune(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int getRadioST() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioST();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int getRadioRssi() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioRssi();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int RadioSetLongOrNear(long var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(var1);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioSetLongOrNear(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int RadioSetLongOrNearForAM(long var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(var1);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioSetLongOrNearForAM(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setRadioMonoStereo(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadioMonoStereo(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setRadioMute(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRadioMute(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setAudioVolumeForDigree(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAudioVolumeForDigree(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setTargetVolume(float var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTargetVolume(var1, var2);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public byte[] RadioGetRDS() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioGetRDS();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int RadioRDSCfg(int var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().RadioRDSCfg(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int openBT(boolean var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1 ? 1 : 0);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openBT(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int openTV(boolean var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1 ? 1 : 0);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openTV(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int openDVD(boolean var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1 ? 1 : 0);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openDVD(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public int setDspMixVolume(int var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDspMixVolume(var1, var2);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public void modeInCallSwitch(boolean var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1 ? 1 : 0);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().modeInCallSwitch(var1);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.media.IWitsAudioService
            public void muteOtherMedia(boolean var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(var1 ? 1 : 0);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().muteOtherMedia(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWitsAudioService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IWitsAudioService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
