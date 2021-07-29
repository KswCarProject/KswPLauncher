package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.util.List;

public class ResourceDrawableDecoder implements ResourceDecoder<Uri, Drawable> {
    private static final String ANDROID_PACKAGE_NAME = "android";
    private static final int ID_PATH_SEGMENTS = 1;
    private static final int MISSING_RESOURCE_ID = 0;
    private static final int NAME_PATH_SEGMENT_INDEX = 1;
    private static final int NAME_URI_PATH_SEGMENTS = 2;
    private static final int RESOURCE_ID_SEGMENT_INDEX = 0;
    private static final int TYPE_PATH_SEGMENT_INDEX = 0;
    private final Context context;

    public ResourceDrawableDecoder(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public boolean handles(Uri source, Options options) {
        return source.getScheme().equals("android.resource");
    }

    public Resource<Drawable> decode(Uri source, int width, int height, Options options) {
        Context targetContext = findContextForPackage(source, source.getAuthority());
        return NonOwnedDrawableResource.newInstance(DrawableDecoderCompat.getDrawable(this.context, targetContext, findResourceIdFromUri(targetContext, source)));
    }

    private Context findContextForPackage(Uri source, String packageName) {
        if (packageName.equals(this.context.getPackageName())) {
            return this.context;
        }
        try {
            return this.context.createPackageContext(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            if (packageName.contains(this.context.getPackageName())) {
                return this.context;
            }
            throw new IllegalArgumentException("Failed to obtain context or unrecognized Uri format for: " + source, e);
        }
    }

    private int findResourceIdFromUri(Context context2, Uri source) {
        List<String> segments = source.getPathSegments();
        if (segments.size() == 2) {
            return findResourceIdFromTypeAndNameResourceUri(context2, source);
        }
        if (segments.size() == 1) {
            return findResourceIdFromResourceIdUri(source);
        }
        throw new IllegalArgumentException("Unrecognized Uri format: " + source);
    }

    private int findResourceIdFromTypeAndNameResourceUri(Context context2, Uri source) {
        List<String> segments = source.getPathSegments();
        String packageName = source.getAuthority();
        String typeName = segments.get(0);
        String resourceName = segments.get(1);
        int result = context2.getResources().getIdentifier(resourceName, typeName, packageName);
        if (result == 0) {
            result = Resources.getSystem().getIdentifier(resourceName, typeName, ANDROID_PACKAGE_NAME);
        }
        if (result != 0) {
            return result;
        }
        throw new IllegalArgumentException("Failed to find resource id for: " + source);
    }

    private int findResourceIdFromResourceIdUri(Uri source) {
        try {
            return Integer.parseInt(source.getPathSegments().get(0));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unrecognized Uri format: " + source, e);
        }
    }
}
