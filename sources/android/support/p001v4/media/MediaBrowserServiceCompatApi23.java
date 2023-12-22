package android.support.p001v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.p001v4.media.MediaBrowserServiceCompatApi21;

/* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi23 */
/* loaded from: classes.dex */
class MediaBrowserServiceCompatApi23 {

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi23$ServiceCompatProxy */
    /* loaded from: classes.dex */
    public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        void onLoadItem(String str, MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> resultWrapper);
    }

    public static Object createService(Context context, ServiceCompatProxy serviceProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceProxy);
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi23$MediaBrowserServiceAdaptor */
    /* loaded from: classes.dex */
    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor {
        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceWrapper) {
            super(context, serviceWrapper);
        }

        @Override // android.service.media.MediaBrowserService
        public void onLoadItem(String itemId, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
            ((ServiceCompatProxy) this.mServiceProxy).onLoadItem(itemId, new MediaBrowserServiceCompatApi21.ResultWrapper<>(result));
        }
    }

    private MediaBrowserServiceCompatApi23() {
    }
}
