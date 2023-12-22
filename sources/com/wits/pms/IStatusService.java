package com.wits.pms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wits.pms.IStatusObserver;

/* loaded from: classes.dex */
public interface IStatusService extends IInterface {
    void addBooleanStatus(String str, boolean z) throws RemoteException;

    void addIntStatus(String str, int i) throws RemoteException;

    void addStringStatus(String str, String str2) throws RemoteException;

    boolean getStatusBoolean(String str) throws RemoteException;

    int getStatusInt(String str) throws RemoteException;

    String getStatusString(String str) throws RemoteException;

    void registerStatusListener(String str, IStatusObserver iStatusObserver) throws RemoteException;

    void unregisterStatusListener(IStatusObserver iStatusObserver) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IStatusService {
        @Override // com.wits.pms.IStatusService
        public void registerStatusListener(String var1, IStatusObserver var2) throws RemoteException {
        }

        @Override // com.wits.pms.IStatusService
        public void unregisterStatusListener(IStatusObserver var1) throws RemoteException {
        }

        @Override // com.wits.pms.IStatusService
        public void addIntStatus(String var1, int var2) throws RemoteException {
        }

        @Override // com.wits.pms.IStatusService
        public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
        }

        @Override // com.wits.pms.IStatusService
        public void addStringStatus(String var1, String var2) throws RemoteException {
        }

        @Override // com.wits.pms.IStatusService
        public boolean getStatusBoolean(String var1) throws RemoteException {
            return false;
        }

        @Override // com.wits.pms.IStatusService
        public int getStatusInt(String var1) throws RemoteException {
            return 0;
        }

        @Override // com.wits.pms.IStatusService
        public String getStatusString(String var1) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IStatusService {
        private static final String DESCRIPTOR = "com.wits.pms.IStatusService";
        static final int TRANSACTION_addBooleanStatus = 4;
        static final int TRANSACTION_addIntStatus = 3;
        static final int TRANSACTION_addStringStatus = 5;
        static final int TRANSACTION_getStatusBoolean = 6;
        static final int TRANSACTION_getStatusInt = 7;
        static final int TRANSACTION_getStatusString = 8;
        static final int TRANSACTION_registerStatusListener = 1;
        static final int TRANSACTION_unregisterStatusListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStatusService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IStatusService)) {
                return (IStatusService) iin;
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
                    IStatusObserver _arg1 = IStatusObserver.Stub.asInterface(data.readStrongBinder());
                    registerStatusListener(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    IStatusObserver _arg02 = IStatusObserver.Stub.asInterface(data.readStrongBinder());
                    unregisterStatusListener(_arg02);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    int _arg12 = data.readInt();
                    addIntStatus(_arg03, _arg12);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg04 = data.readString();
                    boolean _arg13 = data.readInt() != 0;
                    addBooleanStatus(_arg04, _arg13);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg05 = data.readString();
                    String _arg14 = data.readString();
                    addStringStatus(_arg05, _arg14);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg06 = data.readString();
                    boolean statusBoolean = getStatusBoolean(_arg06);
                    reply.writeNoException();
                    reply.writeInt(statusBoolean ? 1 : 0);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg07 = data.readString();
                    int _result = getStatusInt(_arg07);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg08 = data.readString();
                    String _result2 = getStatusString(_arg08);
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* loaded from: classes.dex */
        private static class Proxy implements IStatusService {
            public static IStatusService sDefaultImpl;
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

            @Override // com.wits.pms.IStatusService
            public void registerStatusListener(String var1, IStatusObserver var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeStrongBinder(var2 != null ? var2.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerStatusListener(var1, var2);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IStatusService
            public void unregisterStatusListener(IStatusObserver var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterStatusListener(var1);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.IStatusService
            public void addIntStatus(String var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
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

            @Override // com.wits.pms.IStatusService
            public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2 ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
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

            @Override // com.wits.pms.IStatusService
            public void addStringStatus(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
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

            @Override // com.wits.pms.IStatusService
            public boolean getStatusBoolean(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
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

            @Override // com.wits.pms.IStatusService
            public int getStatusInt(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
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

            @Override // com.wits.pms.IStatusService
            public String getStatusString(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
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
        }

        public static boolean setDefaultImpl(IStatusService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IStatusService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
