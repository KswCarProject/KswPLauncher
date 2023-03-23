package com.wits.pms.ksw;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wits.pms.ksw.OnMcuUpdateProgressListener;

public interface IMcuUpdate extends IInterface {
    void mcuUpdate(String str) throws RemoteException;

    void setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener onMcuUpdateProgressListener) throws RemoteException;

    public static class Default implements IMcuUpdate {
        public void mcuUpdate(String path) throws RemoteException {
        }

        public void setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener listener) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMcuUpdate {
        private static final String DESCRIPTOR = "com.wits.pms.ksw.IMcuUpdate";
        static final int TRANSACTION_mcuUpdate = 1;
        static final int TRANSACTION_setOnMcuUpdateProgressListener = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMcuUpdate asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMcuUpdate)) {
                return new Proxy(obj);
            }
            return (IMcuUpdate) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    mcuUpdate(data.readString());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IMcuUpdate {
            public static IMcuUpdate sDefaultImpl;
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

            public void mcuUpdate(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().mcuUpdate(path);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setOnMcuUpdateProgressListener(listener);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMcuUpdate impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMcuUpdate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
