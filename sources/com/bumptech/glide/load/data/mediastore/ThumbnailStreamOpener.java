package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class ThumbnailStreamOpener {
    private static final FileService DEFAULT_SERVICE = new FileService();
    private static final String TAG = "ThumbStreamOpener";
    private final ArrayPool byteArrayPool;
    private final ContentResolver contentResolver;
    private final List<ImageHeaderParser> parsers;
    private final ThumbnailQuery query;
    private final FileService service;

    ThumbnailStreamOpener(List<ImageHeaderParser> parsers2, ThumbnailQuery query2, ArrayPool byteArrayPool2, ContentResolver contentResolver2) {
        this(parsers2, DEFAULT_SERVICE, query2, byteArrayPool2, contentResolver2);
    }

    ThumbnailStreamOpener(List<ImageHeaderParser> parsers2, FileService service2, ThumbnailQuery query2, ArrayPool byteArrayPool2, ContentResolver contentResolver2) {
        this.service = service2;
        this.query = query2;
        this.byteArrayPool = byteArrayPool2;
        this.contentResolver = contentResolver2;
        this.parsers = parsers2;
    }

    /* access modifiers changed from: package-private */
    public int getOrientation(Uri uri) {
        InputStream is = null;
        try {
            InputStream is2 = this.contentResolver.openInputStream(uri);
            int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, is2, this.byteArrayPool);
            if (is2 != null) {
                try {
                    is2.close();
                } catch (IOException e) {
                }
            }
            return orientation;
        } catch (IOException | NullPointerException e2) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to open uri: " + uri, e2);
            }
            if (is == null) {
                return -1;
            }
            try {
                is.close();
                return -1;
            } catch (IOException e3) {
                return -1;
            }
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    public InputStream open(Uri uri) throws FileNotFoundException {
        String path = getPath(uri);
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = this.service.get(path);
        if (!isValid(file)) {
            return null;
        }
        Uri thumbnailUri = Uri.fromFile(file);
        try {
            return this.contentResolver.openInputStream(thumbnailUri);
        } catch (NullPointerException e) {
            throw ((FileNotFoundException) new FileNotFoundException("NPE opening uri: " + uri + " -> " + thumbnailUri).initCause(e));
        }
    }

    private String getPath(Uri uri) {
        Cursor cursor = this.query.query(uri);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    return cursor.getString(0);
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

    private boolean isValid(File file) {
        return this.service.exists(file) && 0 < this.service.length(file);
    }
}
