package android.support.v4.app;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.os.Bundle;
import android.support.v4.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager {

    public interface LoaderCallbacks<D> {
        Loader<D> onCreateLoader(int i, Bundle bundle);

        void onLoadFinished(Loader<D> loader, D d);

        void onLoaderReset(Loader<D> loader);
    }

    public abstract void destroyLoader(int i);

    @Deprecated
    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract <D> Loader<D> getLoader(int i);

    public abstract <D> Loader<D> initLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks);

    public abstract void markForRedelivery();

    public abstract <D> Loader<D> restartLoader(int i, Bundle bundle, LoaderCallbacks<D> loaderCallbacks);

    public static <T extends LifecycleOwner & ViewModelStoreOwner> LoaderManager getInstance(T owner) {
        return new LoaderManagerImpl(owner, ((ViewModelStoreOwner) owner).getViewModelStore());
    }

    public static void enableDebugLogging(boolean enabled) {
        LoaderManagerImpl.DEBUG = enabled;
    }

    public boolean hasRunningLoaders() {
        return false;
    }
}
