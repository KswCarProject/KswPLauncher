package com.wits.pms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wits.pms.ICmdListener;
import com.wits.pms.IContentObserver;

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

    public static class Default implements IPowerManagerAppService {
        public boolean sendCommand(String var1) throws RemoteException {
            return false;
        }

        public boolean sendStatus(String var1) throws RemoteException {
            return false;
        }

        public void registerCmdListener(ICmdListener var1) throws RemoteException {
        }

        public void unregisterCmdListener(ICmdListener var1) throws RemoteException {
        }

        public void registerObserver(String var1, IContentObserver var2) throws RemoteException {
        }

        public void unregisterObserver(IContentObserver var1) throws RemoteException {
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

        public int getSettingsInt(String var1) throws RemoteException {
            return 0;
        }

        public String getSettingsString(String var1) throws RemoteException {
            return null;
        }

        public void setSettingsInt(String var1, int var2) throws RemoteException {
        }

        public void setSettingsString(String var1, String var2) throws RemoteException {
        }

        public void addIntStatus(String var1, int var2) throws RemoteException {
        }

        public void addBooleanStatus(String var1, boolean var2) throws RemoteException {
        }

        public void addStringStatus(String var1, String var2) throws RemoteException {
        }

        public void saveJsonConfig(String var1, String var2) throws RemoteException {
        }

        public String getJsonConfig(String var1) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

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
            if (iin == null || !(iin instanceof IPowerManagerAppService)) {
                return new Proxy(obj);
            }
            return (IPowerManagerAppService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = sendCommand(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result2 = sendStatus(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    registerCmdListener(ICmdListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterCmdListener(ICmdListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    registerObserver(data.readString(), IContentObserver.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterObserver(IContentObserver.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result3 = getStatusBoolean(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getStatusInt(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = getStatusString(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getSettingsInt(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    String _result7 = getSettingsString(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result7);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    setSettingsInt(data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    setSettingsString(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    addIntStatus(data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    addBooleanStatus(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    addStringStatus(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    saveJsonConfig(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    String _result8 = getJsonConfig(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result8);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IPowerManagerAppService {
            public static IPowerManagerAppService sDefaultImpl;
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

            public boolean sendCommand(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean z = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendCommand(var1);
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

            public boolean sendStatus(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    boolean z = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendStatus(var1);
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

            public void registerCmdListener(ICmdListener var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerCmdListener(var1);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void unregisterCmdListener(ICmdListener var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCmdListener(var1);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void registerObserver(String var1, IContentObserver var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeStrongBinder(var2 != null ? var2.asBinder() : null);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerObserver(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void unregisterObserver(IContentObserver var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(var1 != null ? var1.asBinder() : null);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterObserver(var1);
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
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
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
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
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
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
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

            public int getSettingsInt(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    if (!this.mRemote.transact(10, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsInt(var1);
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

            public String getSettingsString(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingsString(var1);
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

            public void setSettingsInt(String var1, int var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeInt(var2);
                    if (this.mRemote.transact(12, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setSettingsInt(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setSettingsString(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    if (this.mRemote.transact(13, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setSettingsString(var1, var2);
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
                    if (this.mRemote.transact(14, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
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
                    if (this.mRemote.transact(15, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
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
                    if (this.mRemote.transact(16, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
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

            public void saveJsonConfig(String var1, String var2) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    _data.writeString(var2);
                    if (this.mRemote.transact(17, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().saveJsonConfig(var1, var2);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getJsonConfig(String var1) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(var1);
                    if (!this.mRemote.transact(18, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getJsonConfig(var1);
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

        public static boolean setDefaultImpl(IPowerManagerAppService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IPowerManagerAppService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
