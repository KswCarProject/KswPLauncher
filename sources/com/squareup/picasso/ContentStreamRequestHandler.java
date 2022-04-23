package com.squareup.picasso;

import android.content.Context;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class ContentStreamRequestHandler extends RequestHandler {
    final Context context;

    ContentStreamRequestHandler(Context context2) {
        this.context = context2;
    }

    public boolean canHandleRequest(Request data) {
        return "content".equals(data.uri.getScheme());
    }

    public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
        return new RequestHandler.Result(getInputStream(request), Picasso.LoadedFrom.DISK);
    }

    /* access modifiers changed from: package-private */
    public InputStream getInputStream(Request request) throws FileNotFoundException {
        return this.context.getContentResolver().openInputStream(request.uri);
    }
}
