package com.squareup.picasso;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import java.io.InputStream;

class MediaStoreRequestHandler extends ContentStreamRequestHandler {
    private static final String[] CONTENT_ORIENTATION = {"orientation"};

    MediaStoreRequestHandler(Context context) {
        super(context);
    }

    public boolean canHandleRequest(Request data) {
        Uri uri = data.uri;
        return "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
        Bitmap bitmap;
        Request request2 = request;
        ContentResolver contentResolver = this.context.getContentResolver();
        int exifOrientation = getExifOrientation(contentResolver, request2.uri);
        String mimeType = contentResolver.getType(request2.uri);
        int kind = 1;
        boolean isVideo = mimeType != null && mimeType.startsWith("video/");
        if (request.hasSize()) {
            PicassoKind picassoKind = getPicassoKind(request2.targetWidth, request2.targetHeight);
            if (!isVideo && picassoKind == PicassoKind.FULL) {
                return new RequestHandler.Result((Bitmap) null, getInputStream(request), Picasso.LoadedFrom.DISK, exifOrientation);
            }
            long id = ContentUris.parseId(request2.uri);
            BitmapFactory.Options options = createBitmapOptions(request);
            options.inJustDecodeBounds = true;
            long id2 = id;
            calculateInSampleSize(request2.targetWidth, request2.targetHeight, picassoKind.width, picassoKind.height, options, request);
            if (isVideo) {
                if (picassoKind != PicassoKind.FULL) {
                    kind = picassoKind.androidKind;
                }
                bitmap = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, id2, kind, options);
            } else {
                bitmap = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, id2, picassoKind.androidKind, options);
            }
            if (bitmap != null) {
                return new RequestHandler.Result(bitmap, (InputStream) null, Picasso.LoadedFrom.DISK, exifOrientation);
            }
        }
        return new RequestHandler.Result((Bitmap) null, getInputStream(request), Picasso.LoadedFrom.DISK, exifOrientation);
    }

    static PicassoKind getPicassoKind(int targetWidth, int targetHeight) {
        if (targetWidth <= PicassoKind.MICRO.width && targetHeight <= PicassoKind.MICRO.height) {
            return PicassoKind.MICRO;
        }
        if (targetWidth > PicassoKind.MINI.width || targetHeight > PicassoKind.MINI.height) {
            return PicassoKind.FULL;
        }
        return PicassoKind.MINI;
    }

    static int getExifOrientation(ContentResolver contentResolver, Uri uri) {
        Cursor cursor = null;
        try {
            Cursor cursor2 = contentResolver.query(uri, CONTENT_ORIENTATION, (String) null, (String[]) null, (String) null);
            if (cursor2 != null) {
                if (cursor2.moveToFirst()) {
                    int i = cursor2.getInt(0);
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return i;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return 0;
        } catch (RuntimeException e) {
            if (cursor != null) {
                cursor.close();
            }
            return 0;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    enum PicassoKind {
        MICRO(3, 96, 96),
        MINI(1, 512, 384),
        FULL(2, -1, -1);
        
        final int androidKind;
        final int height;
        final int width;

        private PicassoKind(int androidKind2, int width2, int height2) {
            this.androidKind = androidKind2;
            this.width = width2;
            this.height = height2;
        }
    }
}
