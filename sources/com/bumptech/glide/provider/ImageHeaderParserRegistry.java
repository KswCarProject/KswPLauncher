package com.bumptech.glide.provider;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

public final class ImageHeaderParserRegistry {
    private final List<ImageHeaderParser> parsers = new ArrayList();

    @NonNull
    public synchronized List<ImageHeaderParser> getParsers() {
        return this.parsers;
    }

    public synchronized void add(@NonNull ImageHeaderParser parser) {
        this.parsers.add(parser);
    }
}
