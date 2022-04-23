package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class Downsampler {
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode", false);
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    @Deprecated
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = DownsampleStrategy.OPTION;
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() {
        public void onObtainBounds() {
        }

        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap downsampled) {
        }
    };
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    private static final String ICO_MIME_TYPE = "image/x-ico";
    private static final int MARK_POSITION = 10485760;
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{WBMP_MIME_TYPE, ICO_MIME_TYPE})));
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
    static final String TAG = "Downsampler";
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final String WBMP_MIME_TYPE = "image/vnd.wap.wbmp";
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final List<ImageHeaderParser> parsers;

    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    public Downsampler(List<ImageHeaderParser> parsers2, DisplayMetrics displayMetrics2, BitmapPool bitmapPool2, ArrayPool byteArrayPool2) {
        this.parsers = parsers2;
        this.displayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics2);
        this.bitmapPool = (BitmapPool) Preconditions.checkNotNull(bitmapPool2);
        this.byteArrayPool = (ArrayPool) Preconditions.checkNotNull(byteArrayPool2);
    }

    public boolean handles(InputStream is) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream is, int outWidth, int outHeight, Options options) throws IOException {
        return decode(is, outWidth, outHeight, options, EMPTY_CALLBACKS);
    }

    public Resource<Bitmap> decode(InputStream is, int requestedWidth, int requestedHeight, Options options, DecodeCallbacks callbacks) throws IOException {
        Options options2 = options;
        Preconditions.checkArgument(is.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bytesForOptions = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options bitmapFactoryOptions = getDefaultOptions();
        bitmapFactoryOptions.inTempStorage = bytesForOptions;
        DecodeFormat decodeFormat = (DecodeFormat) options2.get(DECODE_FORMAT);
        DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options2.get(DownsampleStrategy.OPTION);
        boolean fixBitmapToRequestedDimensions = ((Boolean) options2.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue();
        Option option = ALLOW_HARDWARE_CONFIG;
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(is, bitmapFactoryOptions, downsampleStrategy, decodeFormat, options2.get(option) != null && ((Boolean) options2.get(option)).booleanValue(), requestedWidth, requestedHeight, fixBitmapToRequestedDimensions, callbacks), this.bitmapPool);
        } finally {
            releaseOptions(bitmapFactoryOptions);
            this.byteArrayPool.put(bytesForOptions);
        }
    }

    private Bitmap decodeFromWrappedStreams(InputStream is, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean isHardwareConfigAllowed, int requestedWidth, int requestedHeight, boolean fixBitmapToRequestedDimensions, DecodeCallbacks callbacks) throws IOException {
        boolean isHardwareConfigAllowed2;
        Downsampler downsampler;
        String str;
        int expectedHeight;
        int expectedWidth;
        int expectedWidth2;
        InputStream inputStream = is;
        BitmapFactory.Options options2 = options;
        DecodeCallbacks decodeCallbacks = callbacks;
        long startTime = LogTime.getLogTime();
        int[] sourceDimensions = getDimensions(inputStream, options2, decodeCallbacks, this.bitmapPool);
        boolean z = false;
        int sourceWidth = sourceDimensions[0];
        int sourceHeight = sourceDimensions[1];
        String sourceMimeType = options2.outMimeType;
        if (sourceWidth == -1 || sourceHeight == -1) {
            isHardwareConfigAllowed2 = false;
        } else {
            isHardwareConfigAllowed2 = isHardwareConfigAllowed;
        }
        int orientation = ImageHeaderParserUtils.getOrientation(this.parsers, inputStream, this.byteArrayPool);
        int degreesToRotate = TransformationUtils.getExifOrientationDegrees(orientation);
        boolean isExifOrientationRequired = TransformationUtils.isExifOrientationRequired(orientation);
        int i = requestedWidth;
        int targetWidth = i == Integer.MIN_VALUE ? sourceWidth : i;
        int i2 = requestedHeight;
        int targetHeight = i2 == Integer.MIN_VALUE ? sourceHeight : i2;
        ImageHeaderParser.ImageType imageType = ImageHeaderParserUtils.getType(this.parsers, inputStream, this.byteArrayPool);
        BitmapPool bitmapPool2 = this.bitmapPool;
        ImageHeaderParser.ImageType imageType2 = imageType;
        calculateScaling(imageType, is, callbacks, bitmapPool2, downsampleStrategy, degreesToRotate, sourceWidth, sourceHeight, targetWidth, targetHeight, options);
        int orientation2 = orientation;
        String sourceMimeType2 = sourceMimeType;
        int sourceHeight2 = sourceHeight;
        int sourceWidth2 = sourceWidth;
        DecodeCallbacks decodeCallbacks2 = decodeCallbacks;
        BitmapFactory.Options options3 = options2;
        calculateConfig(is, decodeFormat, isHardwareConfigAllowed2, isExifOrientationRequired, options, targetWidth, targetHeight);
        if (Build.VERSION.SDK_INT >= 19) {
            z = true;
        }
        boolean isKitKatOrGreater = z;
        if (options3.inSampleSize == 1 || isKitKatOrGreater) {
            downsampler = this;
            if (downsampler.shouldUsePool(imageType2)) {
                if (sourceWidth2 < 0 || sourceHeight2 < 0 || !fixBitmapToRequestedDimensions || !isKitKatOrGreater) {
                    float densityMultiplier = isScaling(options) != 0 ? ((float) options3.inTargetDensity) / ((float) options3.inDensity) : 1.0f;
                    int sampleSize = options3.inSampleSize;
                    String str2 = TAG;
                    int downsampledHeight = (int) Math.ceil((double) (((float) sourceHeight2) / ((float) sampleSize)));
                    int expectedWidth3 = Math.round(((float) ((int) Math.ceil((double) (((float) sourceWidth2) / ((float) sampleSize))))) * densityMultiplier);
                    int expectedHeight2 = Math.round(((float) downsampledHeight) * densityMultiplier);
                    boolean z2 = isKitKatOrGreater;
                    str = str2;
                    if (Log.isLoggable(str, 2)) {
                        int i3 = downsampledHeight;
                        expectedWidth2 = expectedWidth3;
                        Log.v(str, "Calculated target [" + expectedWidth3 + "x" + expectedHeight2 + "] for source [" + sourceWidth2 + "x" + sourceHeight2 + "], sampleSize: " + sampleSize + ", targetDensity: " + options3.inTargetDensity + ", density: " + options3.inDensity + ", density multiplier: " + densityMultiplier);
                    } else {
                        expectedWidth2 = expectedWidth3;
                    }
                    expectedHeight = expectedHeight2;
                    expectedWidth = expectedWidth2;
                } else {
                    expectedWidth = targetWidth;
                    expectedHeight = targetHeight;
                    boolean z3 = isKitKatOrGreater;
                    str = TAG;
                }
                if (expectedWidth > 0 && expectedHeight > 0) {
                    setInBitmap(options3, downsampler.bitmapPool, expectedWidth, expectedHeight);
                }
            } else {
                str = TAG;
            }
        } else {
            downsampler = this;
            boolean z4 = isKitKatOrGreater;
            str = TAG;
            ImageHeaderParser.ImageType imageType3 = imageType2;
        }
        Bitmap downsampled = decodeStream(is, options3, decodeCallbacks2, downsampler.bitmapPool);
        decodeCallbacks2.onDecodeComplete(downsampler.bitmapPool, downsampled);
        if (Log.isLoggable(str, 2)) {
            logDecode(sourceWidth2, sourceHeight2, sourceMimeType2, options, downsampled, requestedWidth, requestedHeight, startTime);
        }
        Bitmap rotated = null;
        if (downsampled != null) {
            downsampled.setDensity(downsampler.displayMetrics.densityDpi);
            rotated = TransformationUtils.rotateImageExif(downsampler.bitmapPool, downsampled, orientation2);
            if (!downsampled.equals(rotated)) {
                downsampler.bitmapPool.put(downsampled);
            }
        }
        return rotated;
    }

    private static void calculateScaling(ImageHeaderParser.ImageType imageType, InputStream is, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool2, DownsampleStrategy downsampleStrategy, int degreesToRotate, int sourceWidth, int sourceHeight, int targetWidth, int targetHeight, BitmapFactory.Options options) throws IOException {
        float exactScaleFactor;
        int scaleFactor;
        int powerOfTwoSampleSize;
        float exactScaleFactor2;
        int powerOfTwoHeight;
        int powerOfTwoWidth;
        ImageHeaderParser.ImageType imageType2 = imageType;
        DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
        int i = degreesToRotate;
        int i2 = sourceWidth;
        int i3 = sourceHeight;
        int i4 = targetWidth;
        int i5 = targetHeight;
        BitmapFactory.Options options2 = options;
        if (i2 > 0 && i3 > 0) {
            if (i == 90 || i == 270) {
                exactScaleFactor = downsampleStrategy2.getScaleFactor(i3, i2, i4, i5);
            } else {
                exactScaleFactor = downsampleStrategy2.getScaleFactor(i2, i3, i4, i5);
            }
            if (exactScaleFactor > 0.0f) {
                DownsampleStrategy.SampleSizeRounding rounding = downsampleStrategy2.getSampleSizeRounding(i2, i3, i4, i5);
                if (rounding != null) {
                    int outWidth = round((double) (((float) i2) * exactScaleFactor));
                    int outHeight = round((double) (((float) i3) * exactScaleFactor));
                    int widthScaleFactor = i2 / outWidth;
                    int heightScaleFactor = i3 / outHeight;
                    int i6 = outWidth;
                    if (rounding == DownsampleStrategy.SampleSizeRounding.MEMORY) {
                        scaleFactor = Math.max(widthScaleFactor, heightScaleFactor);
                    } else {
                        scaleFactor = Math.min(widthScaleFactor, heightScaleFactor);
                    }
                    int i7 = heightScaleFactor;
                    int i8 = outHeight;
                    int i9 = widthScaleFactor;
                    if (Build.VERSION.SDK_INT > 23 || !NO_DOWNSAMPLE_PRE_N_MIME_TYPES.contains(options2.outMimeType)) {
                        powerOfTwoSampleSize = Math.max(1, Integer.highestOneBit(scaleFactor));
                        if (rounding == DownsampleStrategy.SampleSizeRounding.MEMORY && ((float) powerOfTwoSampleSize) < 1.0f / exactScaleFactor) {
                            powerOfTwoSampleSize <<= 1;
                        }
                    } else {
                        powerOfTwoSampleSize = 1;
                    }
                    options2.inSampleSize = powerOfTwoSampleSize;
                    if (imageType2 == ImageHeaderParser.ImageType.JPEG) {
                        int nativeScaling = Math.min(powerOfTwoSampleSize, 8);
                        DownsampleStrategy.SampleSizeRounding sampleSizeRounding = rounding;
                        exactScaleFactor2 = exactScaleFactor;
                        int scaleFactor2 = scaleFactor;
                        powerOfTwoWidth = (int) Math.ceil((double) (((float) i2) / ((float) nativeScaling)));
                        int i10 = nativeScaling;
                        powerOfTwoHeight = (int) Math.ceil((double) (((float) i3) / ((float) nativeScaling)));
                        int secondaryScaling = powerOfTwoSampleSize / 8;
                        if (secondaryScaling > 0) {
                            powerOfTwoWidth /= secondaryScaling;
                            powerOfTwoHeight /= secondaryScaling;
                        }
                        BitmapPool bitmapPool3 = bitmapPool2;
                        int i11 = scaleFactor2;
                    } else {
                        exactScaleFactor2 = exactScaleFactor;
                        int scaleFactor3 = scaleFactor;
                        if (imageType2 == ImageHeaderParser.ImageType.PNG) {
                            BitmapPool bitmapPool4 = bitmapPool2;
                            int i12 = scaleFactor3;
                        } else if (imageType2 == ImageHeaderParser.ImageType.PNG_A) {
                            BitmapPool bitmapPool5 = bitmapPool2;
                            int i13 = scaleFactor3;
                        } else {
                            if (imageType2 == ImageHeaderParser.ImageType.WEBP) {
                                InputStream inputStream = is;
                                DecodeCallbacks decodeCallbacks2 = decodeCallbacks;
                                BitmapPool bitmapPool6 = bitmapPool2;
                            } else if (imageType2 == ImageHeaderParser.ImageType.WEBP_A) {
                                InputStream inputStream2 = is;
                                DecodeCallbacks decodeCallbacks3 = decodeCallbacks;
                                BitmapPool bitmapPool7 = bitmapPool2;
                            } else if (i2 % powerOfTwoSampleSize == 0 && i3 % powerOfTwoSampleSize == 0) {
                                powerOfTwoWidth = i2 / powerOfTwoSampleSize;
                                powerOfTwoHeight = i3 / powerOfTwoSampleSize;
                                BitmapPool bitmapPool8 = bitmapPool2;
                                int i14 = scaleFactor3;
                            } else {
                                int[] dimensions = getDimensions(is, options2, decodeCallbacks, bitmapPool2);
                                int powerOfTwoWidth2 = dimensions[0];
                                powerOfTwoHeight = dimensions[1];
                                powerOfTwoWidth = powerOfTwoWidth2;
                                int powerOfTwoWidth3 = scaleFactor3;
                            }
                            int i15 = scaleFactor3;
                            if (Build.VERSION.SDK_INT >= 24) {
                                int powerOfTwoWidth4 = Math.round(((float) i2) / ((float) powerOfTwoSampleSize));
                                powerOfTwoHeight = Math.round(((float) i3) / ((float) powerOfTwoSampleSize));
                                powerOfTwoWidth = powerOfTwoWidth4;
                            } else {
                                int powerOfTwoWidth5 = (int) Math.floor((double) (((float) i2) / ((float) powerOfTwoSampleSize)));
                                powerOfTwoHeight = (int) Math.floor((double) (((float) i3) / ((float) powerOfTwoSampleSize)));
                                powerOfTwoWidth = powerOfTwoWidth5;
                            }
                        }
                        int powerOfTwoWidth6 = (int) Math.floor((double) (((float) i2) / ((float) powerOfTwoSampleSize)));
                        powerOfTwoHeight = (int) Math.floor((double) (((float) i3) / ((float) powerOfTwoSampleSize)));
                        powerOfTwoWidth = powerOfTwoWidth6;
                    }
                    double adjustedScaleFactor = (double) downsampleStrategy2.getScaleFactor(powerOfTwoWidth, powerOfTwoHeight, i4, i5);
                    if (Build.VERSION.SDK_INT >= 19) {
                        options2.inTargetDensity = adjustTargetDensityForError(adjustedScaleFactor);
                        options2.inDensity = getDensityMultiplier(adjustedScaleFactor);
                    }
                    if (isScaling(options)) {
                        options2.inScaled = true;
                    } else {
                        options2.inTargetDensity = 0;
                        options2.inDensity = 0;
                    }
                    if (Log.isLoggable(TAG, 2)) {
                        Log.v(TAG, "Calculate scaling, source: [" + i2 + "x" + i3 + "], target: [" + i4 + "x" + i5 + "], power of two scaled: [" + powerOfTwoWidth + "x" + powerOfTwoHeight + "], exact scale factor: " + exactScaleFactor2 + ", power of 2 sample size: " + powerOfTwoSampleSize + ", adjusted scale factor: " + adjustedScaleFactor + ", target density: " + options2.inTargetDensity + ", density: " + options2.inDensity);
                    }
                } else {
                    throw new IllegalArgumentException("Cannot round with null rounding");
                }
            } else {
                throw new IllegalArgumentException("Cannot scale with factor: " + exactScaleFactor + " from: " + downsampleStrategy2 + ", source: [" + i2 + "x" + i3 + "], target: [" + i4 + "x" + i5 + "]");
            }
        } else if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unable to determine dimensions for: " + imageType + " with target [" + i4 + "x" + i5 + "]");
        } else {
            ImageHeaderParser.ImageType imageType3 = imageType;
        }
    }

    private static int adjustTargetDensityForError(double adjustedScaleFactor) {
        int densityMultiplier = getDensityMultiplier(adjustedScaleFactor);
        int targetDensity = round(((double) densityMultiplier) * adjustedScaleFactor);
        return round(((double) targetDensity) * (adjustedScaleFactor / ((double) (((float) targetDensity) / ((float) densityMultiplier)))));
    }

    private static int getDensityMultiplier(double adjustedScaleFactor) {
        return (int) Math.round((adjustedScaleFactor <= 1.0d ? adjustedScaleFactor : 1.0d / adjustedScaleFactor) * 2.147483647E9d);
    }

    private static int round(double value) {
        return (int) (0.5d + value);
    }

    private boolean shouldUsePool(ImageHeaderParser.ImageType imageType) {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return TYPES_THAT_USE_POOL_PRE_KITKAT.contains(imageType);
    }

    private void calculateConfig(InputStream is, DecodeFormat format, boolean isHardwareConfigAllowed, boolean isExifOrientationRequired, BitmapFactory.Options optionsWithScaling, int targetWidth, int targetHeight) {
        if (!this.hardwareConfigState.setHardwareConfigIfAllowed(targetWidth, targetHeight, optionsWithScaling, format, isHardwareConfigAllowed, isExifOrientationRequired)) {
            if (format == DecodeFormat.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
                optionsWithScaling.inPreferredConfig = Bitmap.Config.ARGB_8888;
                return;
            }
            boolean hasAlpha = false;
            try {
                hasAlpha = ImageHeaderParserUtils.getType(this.parsers, is, this.byteArrayPool).hasAlpha();
            } catch (IOException e) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Cannot determine whether the image has alpha or not from header, format " + format, e);
                }
            }
            optionsWithScaling.inPreferredConfig = hasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            if (optionsWithScaling.inPreferredConfig == Bitmap.Config.RGB_565) {
                optionsWithScaling.inDither = true;
            }
        }
    }

    private static int[] getDimensions(InputStream is, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool2) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(is, options, decodeCallbacks, bitmapPool2);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static Bitmap decodeStream(InputStream is, BitmapFactory.Options options, DecodeCallbacks callbacks, BitmapPool bitmapPool2) throws IOException {
        IOException bitmapAssertionException;
        if (options.inJustDecodeBounds) {
            is.mark(MARK_POSITION);
        } else {
            callbacks.onObtainBounds();
        }
        int sourceWidth = options.outWidth;
        int sourceHeight = options.outHeight;
        String outMimeType = options.outMimeType;
        TransformationUtils.getBitmapDrawableLock().lock();
        try {
            Bitmap result = BitmapFactory.decodeStream(is, (Rect) null, options);
            TransformationUtils.getBitmapDrawableLock().unlock();
            if (options.inJustDecodeBounds) {
                is.reset();
            }
            return result;
        } catch (IOException e) {
            throw bitmapAssertionException;
        } catch (IllegalArgumentException e2) {
            bitmapAssertionException = newIoExceptionForInBitmapAssertion(e2, sourceWidth, sourceHeight, outMimeType, options);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to decode with inBitmap, trying again without Bitmap re-use", bitmapAssertionException);
            }
            if (options.inBitmap != null) {
                is.reset();
                bitmapPool2.put(options.inBitmap);
                options.inBitmap = null;
                Bitmap decodeStream = decodeStream(is, options, callbacks, bitmapPool2);
                TransformationUtils.getBitmapDrawableLock().unlock();
                return decodeStream;
            }
            throw bitmapAssertionException;
        } catch (Throwable th) {
            TransformationUtils.getBitmapDrawableLock().unlock();
            throw th;
        }
    }

    private static boolean isScaling(BitmapFactory.Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    private static void logDecode(int sourceWidth, int sourceHeight, String outMimeType, BitmapFactory.Options options, Bitmap result, int requestedWidth, int requestedHeight, long startTime) {
        Log.v(TAG, "Decoded " + getBitmapString(result) + " from [" + sourceWidth + "x" + sourceHeight + "] " + outMimeType + " with inBitmap " + getInBitmapString(options) + " for [" + requestedWidth + "x" + requestedHeight + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + LogTime.getElapsedMillis(startTime));
    }

    private static String getInBitmapString(BitmapFactory.Options options) {
        return getBitmapString(options.inBitmap);
    }

    private static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + (Build.VERSION.SDK_INT >= 19 ? " (" + bitmap.getAllocationByteCount() + ")" : "");
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException e, int outWidth, int outHeight, String outMimeType, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + outWidth + ", outHeight: " + outHeight + ", outMimeType: " + outMimeType + ", inBitmap: " + getInBitmapString(options), e);
    }

    private static void setInBitmap(BitmapFactory.Options options, BitmapPool bitmapPool2, int width, int height) {
        Bitmap.Config expectedConfig = null;
        if (Build.VERSION.SDK_INT >= 26) {
            if (options.inPreferredConfig != Bitmap.Config.HARDWARE) {
                expectedConfig = options.outConfig;
            } else {
                return;
            }
        }
        if (expectedConfig == null) {
            expectedConfig = options.inPreferredConfig;
        }
        options.inBitmap = bitmapPool2.getDirty(width, height, expectedConfig);
    }

    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options decodeBitmapOptions;
        synchronized (Downsampler.class) {
            Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
            synchronized (queue) {
                decodeBitmapOptions = queue.poll();
            }
            if (decodeBitmapOptions == null) {
                decodeBitmapOptions = new BitmapFactory.Options();
                resetOptions(decodeBitmapOptions);
            }
        }
        return decodeBitmapOptions;
    }

    private static void releaseOptions(BitmapFactory.Options decodeBitmapOptions) {
        resetOptions(decodeBitmapOptions);
        Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
        synchronized (queue) {
            queue.offer(decodeBitmapOptions);
        }
    }

    private static void resetOptions(BitmapFactory.Options decodeBitmapOptions) {
        decodeBitmapOptions.inTempStorage = null;
        decodeBitmapOptions.inDither = false;
        decodeBitmapOptions.inScaled = false;
        decodeBitmapOptions.inSampleSize = 1;
        decodeBitmapOptions.inPreferredConfig = null;
        decodeBitmapOptions.inJustDecodeBounds = false;
        decodeBitmapOptions.inDensity = 0;
        decodeBitmapOptions.inTargetDensity = 0;
        decodeBitmapOptions.outWidth = 0;
        decodeBitmapOptions.outHeight = 0;
        decodeBitmapOptions.outMimeType = null;
        decodeBitmapOptions.inBitmap = null;
        decodeBitmapOptions.inMutable = true;
    }
}
