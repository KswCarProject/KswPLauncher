package com.wits.pms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wits.pms.ICmdListener;
import com.wits.pms.IContentObserver;

/* loaded from: classes.dex */
public interface IPowerManagerAppService extends IInterface {
    void addBooleanStatus(String str, boolean z) throws RemoteException;

    void addIntStatus(String str, int i) throws RemoteException;

    void addStringStatus(String str, String str2) throws RemoteException;

    String getJsonConfig(String str) throws RemoteException;

    int getSettingsInt(String str) throws RemoteException;

    String getSettingsString(String str) throws RemoteException;

    boolean getStatusBoolean(String str) throws RemoteException;

    int getStatusInt(String str) throws RemoteException;

    String getStatusString(String str) throws RemoteException;

    void registerCmdListener(ICmdListener iCmdListener) throws RemoteException;

    void registerObserver(String str, IContentObserver iContentObserver) throws RemoteException;

    void saveJsonConfig(String str, String str2) throws RemoteException;

    boolean sendCommand(String str) throws RemoteException;

    boolean sendStatus(String str) throws RemoteException;

    void setSettingsInt(String str, int i) throws RemoteException;

    void setSettingsString(String str, String str2) throws RemoteException;

    void unregisterCmdListener(ICmdListener iCmdListener) throws RemoteException;

    void unregisterObserver(IContentObserver iContentObserver) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IPowerManagerAppService {
        @Override // com.wits.pms.IPowerManagerAppService
        public boolean sendCommand(String var1) throws RemoteException {
            return false;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public boolean sendStatus(String var1) throws RemoteException {
            return false;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void registerCmdListener(ICmdListener var1) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void unregisterCmdListener(ICmdListener var1) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void registerObserver(String var1, IContentObserver var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void unregisterObserver(IContentObserver var1) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public boolean getStatusBoolean(String var1) throws RemoteException {
            return false;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public int getStatusInt(String var1) throws RemoteException {
            return 0;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public String getStatusString(String var1) throws RemoteException {
            return null;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public int getSettingsInt(String var1) throws RemoteException {
            return 0;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public String getSettingsString(String var1) throws RemoteException {
            return null;
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void setSettingsInt(String var1, int var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void setSettingsString(String var1, String var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void addIntStatus(String var1, int var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void addStringStatus(String var1, String var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public void saveJsonConfig(String var1, String var2) throws RemoteException {
        }

        @Override // com.wits.pms.IPowerManagerAppService
        public String getJsonConfig(String var1) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IPowerManagerAppService {
        private static final String DESCRIPTOR = "com.wits.pms.IPowerManagerAppService";
        static final int TRANSACTION_addBooleanStatus = 15;
        static final int TRANSACTION_addIntStatus = 14;
        static final int TRANSACTION_addStringStatus = 16;
        static final int TRANSACTION_getJsonConfig = 18;
        static final int TRANSACTION_getSettingsInt = 10;
        static final int TRANSACTION_getSettingsString = 11;
        static final int TRANSACTION_getStatusBoolean = 7;
        static final int TRANSACTION_getStatusInt = 8;
        static final int TRANSACTION_getStatusString = 9;
        static final int TRANSACTION_registerCmdListener = 3;
        static final int TRANSACTION_registerObserver = 5;
        static final int TRANSACTION_saveJsonConfig = 17;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendStatus = 2;
        static final int TRANSACTION_setSettingsInt = 12;
        static final int TRANSACTION_setSettingsString = 13;
        static final int TRANSACTION_unregisterCmdListener = 4;
        static final int TRANSACTION_unregisterObserver = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPowerManagerAppService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IPowerManagerAppService)) {
                return (IPowerManagerAppService) iin;
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
                    String _arg0 = data.readString();
                    boolean sendCommand = sendCommand(_arg0);
                    reply.writeNoException();
                    reply.writeInt(sendCommand ? 1 : 0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg02 = data.readString();
                    boolean sendStatus = sendStatus(_arg02);
                    reply.writeNoException();
                    reply.writeInt(sendStatus ? 1 : 0);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    ICmdListener _arg03 = ICmdListener.Stub.asInterface(data.readStrongBinder());
                    registerCmdListener(_arg03);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ICmdListener _arg04 = ICmdListener.Stub.asInterface(data.readStrongBinder());
                    unregisterCmdListener(_arg04);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    IContentObserver _arg1 = IContentObserver.Stub.asInterface(data.readStrongBinder());
                    registerObserver(_arg05, _arg1);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    IContentObserver _arg06 = IContentObserver.Stub.asInterface(data.readStrongBinder());
                    unregisterObserver(_arg06);
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    boolean statusBoolean = getStatusBoolean(_arg07);
                    reply.writeNoException();
                    reply.writeInt(statusBoolean ? 1 : 0);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    int _result = getStatusInt(_arg08);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg09 = data.readString();
                    String _result2 = getStatusString(_arg09);
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg010 = data.readString();
                    int _result3 = getSettingsInt(_arg010);
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg011 = data.readString();
                    String _result4 = getSettingsString(_arg011);
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg012 = data.readString();
                    int _arg12 = data.readInt();
                    setSettingsInt(_arg012, _arg12);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg013 = data.readString();
                    String _arg13 = data.readString();
                    setSettingsString(_arg013, _arg13);
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg014 = data.readString();
                    int _arg14 = data.readInt();
                    addIntStatus(_arg014, _arg14);
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg015 = data.readString();
                    boolean _arg15 = data.readInt() != 0;
                    addBooleanStatus(_arg015, _arg15);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg016 = data.readString();
                    String _arg16 = data.readString();
                    addStringStatus(_arg016, _arg16);
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg017 = data.readString();
                    String _arg17 = data.readString();
                    saveJsonConfig(_arg017, _arg17);
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg018 = data.readString();
                    String _result5 = getJsonConfig(_arg018);
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IPowerManagerAppService {
            public static IPowerManagerAppService sDefaultImpl;
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

            @Override // com.wits.pms.IPowerManagerAppService
            public boolean sendCommand(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendCommand(var1);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public boolean sendStatus(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendStatus(var1);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void registerCmdListener(ICmdListener var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCmdListener(var1);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void unregisterCmdListener(ICmdListener var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCmdListener(var1);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void registerObserver(String var1, IContentObserver var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeStrongBinder(var2 != null ? var2.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerObserver(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void unregisterObserver(IContentObserver var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterObserver(var1);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public boolean getStatusBoolean(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusBoolean(var1);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public int getStatusInt(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusInt(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public String getStatusString(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusString(var1);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public int getSettingsInt(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsInt(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public String getSettingsString(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsString(var1);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void setSettingsInt(String var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSettingsInt(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void setSettingsString(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSettingsString(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void addIntStatus(String var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addIntStatus(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2 ? 1 : 0);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addBooleanStatus(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void addStringStatus(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addStringStatus(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public void saveJsonConfig(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().saveJsonConfig(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IPowerManagerAppService
            public String getJsonConfig(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getJsonConfig(var1);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPowerManagerAppService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IPowerManagerAppService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
