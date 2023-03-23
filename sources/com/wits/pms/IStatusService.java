package com.wits.pms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wits.pms.IStatusObserver;

public interface IStatusService extends IInterface {
    void addBooleanStatus(String str, boolean z) throws RemoteException;

    void addIntStatus(String str, int i) throws RemoteException;

    void addStringStatus(String str, String str2) throws RemoteException;

    boolean getStatusBoolean(String str) throws RemoteException;

    int getStatusInt(String str) throws RemoteException;

    String getStatusString(String str) throws RemoteException;

    void registerStatusListener(String str, IStatusObserver iStatusObserver) throws RemoteException;

    void unregisterStatusListener(IStatusObserver iStatusObserver) throws RemoteException;

    public static class Default implements IStatusService {
        public void registerStatusListener(String var1, IStatusObserver var2) throws RemoteException {
        }

        public void unregisterStatusListener(IStatusObserver var1) throws RemoteException {
        }

        public void addIntStatus(String var1, int var2) throws RemoteException {
        }

        public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
        }

        public void addStringStatus(String var1, String var2) throws RemoteException {
        }

        public boolean getStatusBoolean(String var1) throws RemoteException {
            return false;
        }

        public int getStatusInt(String var1) throws RemoteException {
            return 0;
        }

        public String getStatusString(String var1) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

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
            if (iin == null || !(iin instanceof IStatusService)) {
                return new Proxy(obj);
            }
            return (IStatusService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    registerStatusListener(data.readString(), IStatusObserver.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterStatusListener(IStatusObserver.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    addIntStatus(data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    addBooleanStatus(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    addStringStatus(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = getStatusBoolean(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = getStatusInt(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _result3 = getStatusString(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IStatusService {
            public static IStatusService sDefaultImpl;
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

            public void registerStatusListener(String var1, IStatusObserver var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeStrongBinder(var2 != null ? var2.asBinder() : null);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerStatusListener(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void unregisterStatusListener(IStatusObserver var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterStatusListener(var1);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addIntStatus(String var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().addIntStatus(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2 ? 1 : 0);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().addBooleanStatus(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addStringStatus(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().addStringStatus(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean getStatusBoolean(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean z = false;
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusBoolean(var1);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        z = true;
                    }
                    boolean _status = z;
                    _reply.recycle();
                    _data.recycle();
                    return _status;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getStatusInt(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusInt(var1);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getStatusString(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatusString(var1);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
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
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IStatusService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
