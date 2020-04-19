package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.Option;

public abstract class DownsampleStrategy {
    public static final DownsampleStrategy AT_LEAST = new AtLeast();
    public static final DownsampleStrategy AT_MOST = new AtMost();
    public static final DownsampleStrategy CENTER_INSIDE = new CenterInside();
    public static final DownsampleStrategy CENTER_OUTSIDE = new CenterOutside();
    public static final DownsampleStrategy DEFAULT = CENTER_OUTSIDE;
    public static final DownsampleStrategy FIT_CENTER = new FitCenter();
    public static final DownsampleStrategy NONE = new None();
    public static final Option<DownsampleStrategy> OPTION = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DEFAULT);

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public abstract SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4);

    public abstract float getScaleFactor(int i, int i2, int i3, int i4);

    private static class FitCenter extends DownsampleStrategy {
        FitCenter() {
        }

        public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return Math.min(((float) requestedWidth) / ((float) sourceWidth), ((float) requestedHeight) / ((float) sourceHeight));
        }

        public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class CenterOutside extends DownsampleStrategy {
        CenterOutside() {
        }

        public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return Math.max(((float) requestedWidth) / ((float) sourceWidth), ((float) requestedHeight) / ((float) sourceHeight));
        }

        public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class AtLeast extends DownsampleStrategy {
        AtLeast() {
        }

        public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            int minIntegerFactor = Math.min(sourceHeight / requestedHeight, sourceWidth / requestedWidth);
            if (minIntegerFactor == 0) {
                return 1.0f;
            }
            return 1.0f / ((float) Integer.highestOneBit(minIntegerFactor));
        }

        public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class AtMost extends DownsampleStrategy {
        AtMost() {
        }

        public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            int maxIntegerFactor = (int) Math.ceil((double) Math.max(((float) sourceHeight) / ((float) requestedHeight), ((float) sourceWidth) / ((float) requestedWidth)));
            int i = 1;
            int lesserOrEqualSampleSize = Math.max(1, Integer.highestOneBit(maxIntegerFactor));
            if (lesserOrEqualSampleSize >= maxIntegerFactor) {
                i = 0;
            }
            return 1.0f / ((float) (lesserOrEqualSampleSize << i));
        }

        public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return SampleSizeRounding.MEMORY;
        }
    }

    private static class None extends DownsampleStrategy {
        None() {
        }

        public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return 1.0f;
        }

        public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class CenterInside extends DownsampleStrategy {
        CenterInside() {
        }

        public float getScaleFactor(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return Math.min(1.0f, FIT_CENTER.getScaleFactor(sourceWidth, sourceHeight, requestedWidth, requestedHeight));
        }

        public SampleSizeRounding getSampleSizeRounding(int sourceWidth, int sourceHeight, int requestedWidth, int requestedHeight) {
            return SampleSizeRounding.QUALITY;
        }
    }
}
