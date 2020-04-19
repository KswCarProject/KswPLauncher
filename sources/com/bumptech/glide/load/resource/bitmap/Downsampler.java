package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
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
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(is, bitmapFactoryOptions, (DownsampleStrategy) options2.get(DownsampleStrategy.OPTION), decodeFormat, options2.get(ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options2.get(ALLOW_HARDWARE_CONFIG)).booleanValue(), requestedWidth, requestedHeight, ((Boolean) options2.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue(), callbacks), this.bitmapPool);
        } finally {
            releaseOptions(bitmapFactoryOptions);
            this.byteArrayPool.put(bytesForOptions);
        }
    }

    private Bitmap decodeFromWrappedStreams(InputStream is, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean isHardwareConfigAllowed, int requestedWidth, int requestedHeight, boolean fixBitmapToRequestedDimensions, DecodeCallbacks callbacks) throws IOException {
        Downsampler downsampler;
        int expectedHeight;
        int expectedWidth;
        InputStream inputStream = is;
        BitmapFactory.Options options2 = options;
        DecodeCallbacks decodeCallbacks = callbacks;
        long startTime = LogTime.getLogTime();
        int[] sourceDimensions = getDimensions(inputStream, options2, decodeCallbacks, this.bitmapPool);
        boolean z = false;
        int sourceWidth = sourceDimensions[0];
        int sourceHeight = sourceDimensions[1];
        String sourceMimeType = options2.outMimeType;
        boolean isHardwareConfigAllowed2 = (sourceWidth == -1 || sourceHeight == -1) ? false : isHardwareConfigAllowed;
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
            ImageHeaderParser.ImageType imageType3 = imageType2;
            downsampler = this;
            if (downsampler.shouldUsePool(imageType3)) {
                if (sourceWidth2 < 0 || sourceHeight2 < 0 || !fixBitmapToRequestedDimensions || !isKitKatOrGreater) {
                    float densityMultiplier = isScaling(options) != 0 ? ((float) options3.inTargetDensity) / ((float) options3.inDensity) : 1.0f;
                    int sampleSize = options3.inSampleSize;
                    ImageHeaderParser.ImageType imageType4 = imageType3;
                    int downsampledHeight = (int) Math.ceil((double) (((float) sourceHeight2) / ((float) sampleSize)));
                    expectedWidth = Math.round(((float) ((int) Math.ceil((double) (((float) sourceWidth2) / ((float) sampleSize))))) * densityMultiplier);
                    int expectedHeight2 = Math.round(((float) downsampledHeight) * densityMultiplier);
                    boolean z2 = isKitKatOrGreater;
                    if (Log.isLoggable(TAG, 2)) {
                        StringBuilder sb = new StringBuilder();
                        int i3 = downsampledHeight;
                        sb.append("Calculated target [");
                        sb.append(expectedWidth);
                        sb.append("x");
                        sb.append(expectedHeight2);
                        sb.append("] for source [");
                        sb.append(sourceWidth2);
                        sb.append("x");
                        sb.append(sourceHeight2);
                        sb.append("], sampleSize: ");
                        sb.append(sampleSize);
                        sb.append(", targetDensity: ");
                        sb.append(options3.inTargetDensity);
                        sb.append(", density: ");
                        sb.append(options3.inDensity);
                        sb.append(", density multiplier: ");
                        sb.append(densityMultiplier);
                        Log.v(TAG, sb.toString());
                    }
                    expectedHeight = expectedHeight2;
                } else {
                    expectedHeight = targetHeight;
                    boolean z3 = isKitKatOrGreater;
                    ImageHeaderParser.ImageType imageType5 = imageType3;
                    expectedWidth = targetWidth;
                }
                if (expectedWidth > 0 && expectedHeight > 0) {
                    setInBitmap(options3, downsampler.bitmapPool, expectedWidth, expectedHeight);
                }
            } else {
                ImageHeaderParser.ImageType imageType6 = imageType3;
            }
        } else {
            boolean z4 = isKitKatOrGreater;
            ImageHeaderParser.ImageType imageType7 = imageType2;
            downsampler = this;
        }
        Bitmap downsampled = decodeStream(is, options3, decodeCallbacks2, downsampler.bitmapPool);
        decodeCallbacks2.onDecodeComplete(downsampler.bitmapPool, downsampled);
        if (Log.isLoggable(TAG, 2)) {
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

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void calculateScaling(com.bumptech.glide.load.ImageHeaderParser.ImageType r27, java.io.InputStream r28, com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r29, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r30, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r31, int r32, int r33, int r34, int r35, int r36, android.graphics.BitmapFactory.Options r37) throws java.io.IOException {
        /*
            r0 = r27
            r1 = r31
            r2 = r32
            r3 = r33
            r4 = r34
            r5 = r35
            r6 = r36
            r7 = r37
            if (r3 <= 0) goto L_0x0229
            if (r4 > 0) goto L_0x0016
            goto L_0x0229
        L_0x0016:
            r8 = 90
            if (r2 == r8) goto L_0x0025
            r8 = 270(0x10e, float:3.78E-43)
            if (r2 != r8) goto L_0x001f
            goto L_0x0025
        L_0x001f:
            float r8 = r1.getScaleFactor(r3, r4, r5, r6)
            goto L_0x0029
        L_0x0025:
            float r8 = r1.getScaleFactor(r4, r3, r5, r6)
        L_0x0029:
            r9 = 0
            int r9 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x01e5
            com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$SampleSizeRounding r9 = r1.getSampleSizeRounding(r3, r4, r5, r6)
            if (r9 == 0) goto L_0x01db
            float r10 = (float) r3
            float r10 = r10 * r8
            double r10 = (double) r10
            int r10 = round(r10)
            float r11 = (float) r4
            float r11 = r11 * r8
            double r11 = (double) r11
            int r11 = round(r11)
            int r12 = r3 / r10
            int r13 = r4 / r11
            com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$SampleSizeRounding r14 = com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.SampleSizeRounding.MEMORY
            if (r9 != r14) goto L_0x0050
            int r14 = java.lang.Math.max(r12, r13)
            goto L_0x0054
        L_0x0050:
            int r14 = java.lang.Math.min(r12, r13)
        L_0x0054:
            int r15 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            r16 = r10
            r10 = 1
            if (r15 > r2) goto L_0x0069
            java.util.Set<java.lang.String> r2 = NO_DOWNSAMPLE_PRE_N_MIME_TYPES
            java.lang.String r15 = r7.outMimeType
            boolean r2 = r2.contains(r15)
            if (r2 == 0) goto L_0x0069
            r2 = 1
            goto L_0x0080
        L_0x0069:
            int r2 = java.lang.Integer.highestOneBit(r14)
            int r2 = java.lang.Math.max(r10, r2)
            com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$SampleSizeRounding r15 = com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.SampleSizeRounding.MEMORY
            if (r9 != r15) goto L_0x0080
            float r15 = (float) r2
            r17 = 1065353216(0x3f800000, float:1.0)
            float r17 = r17 / r8
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L_0x0080
            int r2 = r2 << 1
        L_0x0080:
            r7.inSampleSize = r2
            com.bumptech.glide.load.ImageHeaderParser$ImageType r15 = com.bumptech.glide.load.ImageHeaderParser.ImageType.JPEG
            if (r0 != r15) goto L_0x00ad
            r15 = 8
            int r15 = java.lang.Math.min(r2, r15)
            float r10 = (float) r3
            r18 = r9
            float r9 = (float) r15
            float r10 = r10 / r9
            double r9 = (double) r10
            double r9 = java.lang.Math.ceil(r9)
            int r9 = (int) r9
            float r10 = (float) r4
            r19 = r11
            float r11 = (float) r15
            float r10 = r10 / r11
            double r10 = (double) r10
            double r10 = java.lang.Math.ceil(r10)
            int r10 = (int) r10
            int r11 = r2 / 8
            if (r11 <= 0) goto L_0x00a8
            int r9 = r9 / r11
            int r10 = r10 / r11
        L_0x00a8:
        L_0x00a9:
            r11 = r30
            goto L_0x0138
        L_0x00ad:
            r18 = r9
            r19 = r11
            com.bumptech.glide.load.ImageHeaderParser$ImageType r9 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG
            if (r0 == r9) goto L_0x0120
            com.bumptech.glide.load.ImageHeaderParser$ImageType r9 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG_A
            if (r0 != r9) goto L_0x00bb
            goto L_0x0120
        L_0x00bb:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r9 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP
            if (r0 == r9) goto L_0x00e9
            com.bumptech.glide.load.ImageHeaderParser$ImageType r9 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP_A
            if (r0 != r9) goto L_0x00c4
            goto L_0x00e9
        L_0x00c4:
            int r9 = r3 % r2
            if (r9 != 0) goto L_0x00d2
            int r9 = r4 % r2
            if (r9 == 0) goto L_0x00cd
            goto L_0x00d2
        L_0x00cd:
            int r9 = r3 / r2
            int r10 = r4 / r2
            goto L_0x00a9
        L_0x00d2:
            r9 = r28
            r10 = r29
            r11 = r30
            int[] r15 = getDimensions(r9, r7, r10, r11)
            r17 = 0
            r20 = r15[r17]
            r17 = 1
            r15 = r15[r17]
            r10 = r15
            r9 = r20
            goto L_0x0138
        L_0x00e9:
            r9 = r28
            r10 = r29
            r11 = r30
            int r15 = android.os.Build.VERSION.SDK_INT
            r9 = 24
            if (r15 < r9) goto L_0x0109
            float r9 = (float) r3
            float r15 = (float) r2
            float r9 = r9 / r15
            int r9 = java.lang.Math.round(r9)
            float r15 = (float) r4
            r21 = r9
            float r9 = (float) r2
            float r15 = r15 / r9
            int r9 = java.lang.Math.round(r15)
            r10 = r9
            r9 = r21
            goto L_0x0138
        L_0x0109:
            float r9 = (float) r3
            float r15 = (float) r2
            float r9 = r9 / r15
            double r9 = (double) r9
            double r9 = java.lang.Math.floor(r9)
            int r9 = (int) r9
            float r10 = (float) r4
            float r15 = (float) r2
            float r10 = r10 / r15
            r22 = r9
            double r9 = (double) r10
            double r9 = java.lang.Math.floor(r9)
            int r10 = (int) r9
            r9 = r22
            goto L_0x0138
        L_0x0120:
            r11 = r30
            float r9 = (float) r3
            float r10 = (float) r2
            float r9 = r9 / r10
            double r9 = (double) r9
            double r9 = java.lang.Math.floor(r9)
            int r9 = (int) r9
            float r10 = (float) r4
            float r15 = (float) r2
            float r10 = r10 / r15
            r23 = r9
            double r9 = (double) r10
            double r9 = java.lang.Math.floor(r9)
            int r10 = (int) r9
            r9 = r23
        L_0x0138:
            float r15 = r1.getScaleFactor(r9, r10, r5, r6)
            r24 = r12
            double r11 = (double) r15
            int r15 = android.os.Build.VERSION.SDK_INT
            r25 = r13
            r13 = 19
            if (r15 < r13) goto L_0x0153
            int r13 = adjustTargetDensityForError(r11)
            r7.inTargetDensity = r13
            int r13 = getDensityMultiplier(r11)
            r7.inDensity = r13
        L_0x0153:
            boolean r13 = isScaling(r37)
            if (r13 == 0) goto L_0x015d
            r13 = 1
            r7.inScaled = r13
            goto L_0x0162
        L_0x015d:
            r13 = 0
            r7.inTargetDensity = r13
            r7.inDensity = r13
        L_0x0162:
            java.lang.String r13 = "Downsampler"
            r15 = 2
            boolean r13 = android.util.Log.isLoggable(r13, r15)
            if (r13 == 0) goto L_0x01d8
            java.lang.String r13 = "Downsampler"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r26 = r14
            java.lang.String r14 = "Calculate scaling, source: ["
            r15.append(r14)
            r15.append(r3)
            java.lang.String r14 = "x"
            r15.append(r14)
            r15.append(r4)
            java.lang.String r14 = "], target: ["
            r15.append(r14)
            r15.append(r5)
            java.lang.String r14 = "x"
            r15.append(r14)
            r15.append(r6)
            java.lang.String r14 = "], power of two scaled: ["
            r15.append(r14)
            r15.append(r9)
            java.lang.String r14 = "x"
            r15.append(r14)
            r15.append(r10)
            java.lang.String r14 = "], exact scale factor: "
            r15.append(r14)
            r15.append(r8)
            java.lang.String r14 = ", power of 2 sample size: "
            r15.append(r14)
            r15.append(r2)
            java.lang.String r14 = ", adjusted scale factor: "
            r15.append(r14)
            r15.append(r11)
            java.lang.String r14 = ", target density: "
            r15.append(r14)
            int r14 = r7.inTargetDensity
            r15.append(r14)
            java.lang.String r14 = ", density: "
            r15.append(r14)
            int r14 = r7.inDensity
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            android.util.Log.v(r13, r14)
            goto L_0x01da
        L_0x01d8:
            r26 = r14
        L_0x01da:
            return
        L_0x01db:
            r18 = r9
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Cannot round with null rounding"
            r2.<init>(r9)
            throw r2
        L_0x01e5:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Cannot scale with factor: "
            r9.append(r10)
            r9.append(r8)
            java.lang.String r10 = " from: "
            r9.append(r10)
            r9.append(r1)
            java.lang.String r10 = ", source: ["
            r9.append(r10)
            r9.append(r3)
            java.lang.String r10 = "x"
            r9.append(r10)
            r9.append(r4)
            java.lang.String r10 = "], target: ["
            r9.append(r10)
            r9.append(r5)
            java.lang.String r10 = "x"
            r9.append(r10)
            r9.append(r6)
            java.lang.String r10 = "]"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r2.<init>(r9)
            throw r2
        L_0x0229:
            java.lang.String r2 = "Downsampler"
            r8 = 3
            boolean r2 = android.util.Log.isLoggable(r2, r8)
            if (r2 == 0) goto L_0x025d
            java.lang.String r2 = "Downsampler"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Unable to determine dimensions for: "
            r8.append(r9)
            r8.append(r0)
            java.lang.String r9 = " with target ["
            r8.append(r9)
            r8.append(r5)
            java.lang.String r9 = "x"
            r8.append(r9)
            r8.append(r6)
            java.lang.String r9 = "]"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.d(r2, r8)
        L_0x025d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.calculateScaling(com.bumptech.glide.load.ImageHeaderParser$ImageType, java.io.InputStream, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, int, int, int, int, int, android.graphics.BitmapFactory$Options):void");
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

    @Nullable
    @TargetApi(19)
    private static String getBitmapString(Bitmap bitmap) {
        String sizeString;
        if (bitmap == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            sizeString = " (" + bitmap.getAllocationByteCount() + ")";
        } else {
            sizeString = "";
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + sizeString;
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException e, int outWidth, int outHeight, String outMimeType, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + outWidth + ", outHeight: " + outHeight + ", outMimeType: " + outMimeType + ", inBitmap: " + getInBitmapString(options), e);
    }

    @TargetApi(26)
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
            synchronized (OPTIONS_QUEUE) {
                decodeBitmapOptions = OPTIONS_QUEUE.poll();
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
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(decodeBitmapOptions);
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
