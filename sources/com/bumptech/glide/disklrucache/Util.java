package com.bumptech.glide.disklrucache;

import com.bumptech.glide.load.Key;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

final class Util {
    static final Charset US_ASCII = Charset.forName("US-ASCII");
    static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);

    private Util() {
    }

    static String readFully(Reader reader) throws IOException {
        try {
            StringWriter writer = new StringWriter();
            char[] buffer = new char[1024];
            while (true) {
                int read = reader.read(buffer);
                int count = read;
                if (read == -1) {
                    return writer.toString();
                }
                writer.write(buffer, 0, count);
            }
        } finally {
            reader.close();
        }
    }

    static void deleteContents(File dir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null) {
            int length = files.length;
            int i = 0;
            while (i < length) {
                File file = files[i];
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                if (file.delete()) {
                    i++;
                } else {
                    throw new IOException("failed to delete file: " + file);
                }
            }
            return;
        }
        throw new IOException("not a readable directory: " + dir);
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }
}
