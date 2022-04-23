package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;

class AssetRequestHandler extends RequestHandler {
    protected static final String ANDROID_ASSET = "android_asset";
    private static final int ASSET_PREFIX_LENGTH = "file:///android_asset/".length();
    private final AssetManager assetManager;

    public AssetRequestHandler(Context context) {
        this.assetManager = context.getAssets();
    }

    public boolean canHandleRequest(Request data) {
        Uri uri = data.uri;
        if (!"file".equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !ANDROID_ASSET.equals(uri.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }

    public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
        return new RequestHandler.Result(this.assetManager.open(getFilePath(request)), Picasso.LoadedFrom.DISK);
    }

    static String getFilePath(Request request) {
        return request.uri.toString().substring(ASSET_PREFIX_LENGTH);
    }
}
