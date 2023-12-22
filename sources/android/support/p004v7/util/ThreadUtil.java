package android.support.p004v7.util;

import android.support.p004v7.util.TileList;

/* renamed from: android.support.v7.util.ThreadUtil */
/* loaded from: classes.dex */
interface ThreadUtil<T> {

    /* renamed from: android.support.v7.util.ThreadUtil$BackgroundCallback */
    /* loaded from: classes.dex */
    public interface BackgroundCallback<T> {
        void loadTile(int i, int i2);

        void recycleTile(TileList.Tile<T> tile);

        void refresh(int i);

        void updateRange(int i, int i2, int i3, int i4, int i5);
    }

    /* renamed from: android.support.v7.util.ThreadUtil$MainThreadCallback */
    /* loaded from: classes.dex */
    public interface MainThreadCallback<T> {
        void addTile(int i, TileList.Tile<T> tile);

        void removeTile(int i, int i2);

        void updateItemCount(int i, int i2);
    }

    BackgroundCallback<T> getBackgroundProxy(BackgroundCallback<T> backgroundCallback);

    MainThreadCallback<T> getMainThreadProxy(MainThreadCallback<T> mainThreadCallback);
}
