package com.wits.pms.ksw;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.wits.pms.ksw.OnMcuUpdateProgressListener;

/* loaded from: classes.dex */
public interface IMcuUpdate extends IInterface {
    void mcuUpdate(String str) throws RemoteException;

    void setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener onMcuUpdateProgressListener) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IMcuUpdate {
        @Override // com.wits.pms.ksw.IMcuUpdate
        public void mcuUpdate(String path) throws RemoteException {
        }

        @Override // com.wits.pms.ksw.IMcuUpdate
        public void setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
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
            if (iin != null && (iin instanceof IMcuUpdate)) {
                return (IMcuUpdate) iin;
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
                    mcuUpdate(_arg0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    OnMcuUpdateProgressListener _arg02 = OnMcuUpdateProgressListener.Stub.asInterface(data.readStrongBinder());
                    setOnMcuUpdateProgressListener(_arg02);
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
        private static class Proxy implements IMcuUpdate {
            public static IMcuUpdate sDefaultImpl;
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

            @Override // com.wits.pms.ksw.IMcuUpdate
            public void mcuUpdate(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mcuUpdate(path);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.wits.pms.ksw.IMcuUpdate
            public void setOnMcuUpdateProgressListener(OnMcuUpdateProgressListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOnMcuUpdateProgressListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMcuUpdate impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IMcuUpdate getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
