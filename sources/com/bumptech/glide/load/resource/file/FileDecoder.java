package com.bumptech.glide.load.resource.file;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.io.File;

public class FileDecoder implements ResourceDecoder<File, File> {
    public boolean handles(@NonNull File source, @NonNull Options options) {
        return true;
    }

    public Resource<File> decode(@NonNull File source, int width, int height, @NonNull Options options) {
        return new FileResource(source);
    }
}
