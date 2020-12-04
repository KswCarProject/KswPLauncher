package com.bumptech.glide.util.pool;

public final class GlideTrace {
    private static final int MAX_LENGTH = 127;
    private static final boolean TRACING_ENABLED = false;

    private GlideTrace() {
    }

    private static String truncateTag(String tag) {
        if (tag.length() > 127) {
            return tag.substring(0, 126);
        }
        return tag;
    }

    public static void beginSection(String tag) {
    }

    public static void beginSectionFormat(String format, Object arg1) {
    }

    public static void beginSectionFormat(String format, Object arg1, Object arg2) {
    }

    public static void beginSectionFormat(String format, Object arg1, Object arg2, Object arg3) {
    }

    public static void endSection() {
    }
}
