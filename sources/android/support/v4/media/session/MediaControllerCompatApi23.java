package android.support.v4.media.session;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;

class MediaControllerCompatApi23 {

    public static class TransportControls {
        public static void playFromUri(Object controlsObj, Uri uri, Bundle extras) {
            ((MediaController.TransportControls) controlsObj).playFromUri(uri, extras);
        }

        private TransportControls() {
        }
    }

    private MediaControllerCompatApi23() {
    }
}
