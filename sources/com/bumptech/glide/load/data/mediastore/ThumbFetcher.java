package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.ExifOrientationStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ThumbFetcher implements DataFetcher<InputStream> {
    private static final String TAG = "MediaStoreThumbFetcher";
    private InputStream inputStream;
    private final Uri mediaStoreImageUri;
    private final ThumbnailStreamOpener opener;

    public static ThumbFetcher buildImageFetcher(Context context, Uri uri) {
        return build(context, uri, new ImageThumbnailQuery(context.getContentResolver()));
    }

    public static ThumbFetcher buildVideoFetcher(Context context, Uri uri) {
        return build(context, uri, new VideoThumbnailQuery(context.getContentResolver()));
    }

    private static ThumbFetcher build(Context context, Uri uri, ThumbnailQuery query) {
        return new ThumbFetcher(uri, new ThumbnailStreamOpener(Glide.get(context).getRegistry().getImageHeaderParsers(), query, Glide.get(context).getArrayPool(), context.getContentResolver()));
    }

    @VisibleForTesting
    ThumbFetcher(Uri mediaStoreImageUri2, ThumbnailStreamOpener opener2) {
        this.mediaStoreImageUri = mediaStoreImageUri2;
        this.opener = opener2;
    }

    public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super InputStream> callback) {
        try {
            this.inputStream = openThumbInputStream();
            callback.onDataReady(this.inputStream);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to find thumbnail file", e);
            }
            callback.onLoadFailed(e);
        }
    }

    private InputStream openThumbInputStream() throws FileNotFoundException {
        InputStream result = this.opener.open(this.mediaStoreImageUri);
        int orientation = -1;
        if (result != null) {
            orientation = this.opener.getOrientation(this.mediaStoreImageUri);
        }
        if (orientation != -1) {
            return new ExifOrientationStream(result, orientation);
        }
        return result;
    }

    public void cleanup() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public void cancel() {
    }

    @NonNull
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }

    static class VideoThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND video_id = ?";
        private final ContentResolver contentResolver;

        VideoThumbnailQuery(ContentResolver contentResolver2) {
            this.contentResolver = contentResolver2;
        }

        public Cursor query(Uri uri) {
            String videoId = uri.getLastPathSegment();
            return this.contentResolver.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{videoId}, (String) null);
        }
    }

    static class ImageThumbnailQuery implements ThumbnailQuery {
        private static final String[] PATH_PROJECTION = {"_data"};
        private static final String PATH_SELECTION = "kind = 1 AND image_id = ?";
        private final ContentResolver contentResolver;

        ImageThumbnailQuery(ContentResolver contentResolver2) {
            this.contentResolver = contentResolver2;
        }

        public Cursor query(Uri uri) {
            String imageId = uri.getLastPathSegment();
            return this.contentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, PATH_PROJECTION, PATH_SELECTION, new String[]{imageId}, (String) null);
        }
    }
}
