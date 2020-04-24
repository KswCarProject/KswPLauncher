package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    @ColorInt
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = StandardGifDecoder.class.getSimpleName();
    @ColorInt
    private int[] act;
    @NonNull
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    @Nullable
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    @ColorInt
    private int[] mainScratch;
    private GifHeaderParser parser;
    @ColorInt
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData2) {
        this(provider, gifHeader, rawData2, 1);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider provider, GifHeader gifHeader, ByteBuffer rawData2, int sampleSize2) {
        this(provider);
        setData(gifHeader, rawData2, sampleSize2);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider provider) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = provider;
        this.header = new GifHeader();
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    @NonNull
    public ByteBuffer getData() {
        return this.rawData;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int n) {
        if (n < 0 || n >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(n).delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return 0;
        }
        return getDelay(this.framePointer);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e9, code lost:
        return null;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r8.header     // Catch:{ all -> 0x00ea }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00ea }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r8.framePointer     // Catch:{ all -> 0x00ea }
            if (r0 >= 0) goto L_0x003b
        L_0x000d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r3.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = "Unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r8.header     // Catch:{ all -> 0x00ea }
            int r4 = r4.frameCount     // Catch:{ all -> 0x00ea }
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r4 = ", framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            int r4 = r8.framePointer     // Catch:{ all -> 0x00ea }
            r3.append(r4)     // Catch:{ all -> 0x00ea }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00ea }
        L_0x0039:
            r8.status = r2     // Catch:{ all -> 0x00ea }
        L_0x003b:
            int r0 = r8.status     // Catch:{ all -> 0x00ea }
            r3 = 0
            if (r0 == r2) goto L_0x00c8
            int r0 = r8.status     // Catch:{ all -> 0x00ea }
            r4 = 2
            if (r0 != r4) goto L_0x0047
            goto L_0x00c8
        L_0x0047:
            r0 = 0
            r8.status = r0     // Catch:{ all -> 0x00ea }
            byte[] r4 = r8.block     // Catch:{ all -> 0x00ea }
            if (r4 != 0) goto L_0x0058
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r4 = r8.bitmapProvider     // Catch:{ all -> 0x00ea }
            r5 = 255(0xff, float:3.57E-43)
            byte[] r4 = r4.obtainByteArray(r5)     // Catch:{ all -> 0x00ea }
            r8.block = r4     // Catch:{ all -> 0x00ea }
        L_0x0058:
            com.bumptech.glide.gifdecoder.GifHeader r4 = r8.header     // Catch:{ all -> 0x00ea }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.frames     // Catch:{ all -> 0x00ea }
            int r5 = r8.framePointer     // Catch:{ all -> 0x00ea }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00ea }
            r5 = 0
            int r6 = r8.framePointer     // Catch:{ all -> 0x00ea }
            int r6 = r6 - r2
            if (r6 < 0) goto L_0x0075
            com.bumptech.glide.gifdecoder.GifHeader r7 = r8.header     // Catch:{ all -> 0x00ea }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r7 = r7.frames     // Catch:{ all -> 0x00ea }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x00ea }
            com.bumptech.glide.gifdecoder.GifFrame r7 = (com.bumptech.glide.gifdecoder.GifFrame) r7     // Catch:{ all -> 0x00ea }
            r5 = r7
        L_0x0075:
            int[] r7 = r4.lct     // Catch:{ all -> 0x00ea }
            if (r7 == 0) goto L_0x007c
            int[] r7 = r4.lct     // Catch:{ all -> 0x00ea }
            goto L_0x0080
        L_0x007c:
            com.bumptech.glide.gifdecoder.GifHeader r7 = r8.header     // Catch:{ all -> 0x00ea }
            int[] r7 = r7.gct     // Catch:{ all -> 0x00ea }
        L_0x0080:
            r8.act = r7     // Catch:{ all -> 0x00ea }
            int[] r7 = r8.act     // Catch:{ all -> 0x00ea }
            if (r7 != 0) goto L_0x00aa
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x00a6
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r7 = "No valid color table found for frame #"
            r1.append(r7)     // Catch:{ all -> 0x00ea }
            int r7 = r8.framePointer     // Catch:{ all -> 0x00ea }
            r1.append(r7)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ea }
        L_0x00a6:
            r8.status = r2     // Catch:{ all -> 0x00ea }
            monitor-exit(r8)
            return r3
        L_0x00aa:
            boolean r1 = r4.transparency     // Catch:{ all -> 0x00ea }
            if (r1 == 0) goto L_0x00c2
            int[] r1 = r8.act     // Catch:{ all -> 0x00ea }
            int[] r2 = r8.pct     // Catch:{ all -> 0x00ea }
            int[] r3 = r8.act     // Catch:{ all -> 0x00ea }
            int r3 = r3.length     // Catch:{ all -> 0x00ea }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00ea }
            int[] r1 = r8.pct     // Catch:{ all -> 0x00ea }
            r8.act = r1     // Catch:{ all -> 0x00ea }
            int[] r1 = r8.act     // Catch:{ all -> 0x00ea }
            int r2 = r4.transIndex     // Catch:{ all -> 0x00ea }
            r1[r2] = r0     // Catch:{ all -> 0x00ea }
        L_0x00c2:
            android.graphics.Bitmap r0 = r8.setPixels(r4, r5)     // Catch:{ all -> 0x00ea }
            monitor-exit(r8)
            return r0
        L_0x00c8:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00ea }
            if (r0 == 0) goto L_0x00e8
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00ea }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ea }
            r1.<init>()     // Catch:{ all -> 0x00ea }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            int r2 = r8.status     // Catch:{ all -> 0x00ea }
            r1.append(r2)     // Catch:{ all -> 0x00ea }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00ea }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00ea }
        L_0x00e8:
            monitor-exit(r8)
            return r3
        L_0x00ea:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int read(@Nullable InputStream is, int contentLength) {
        if (is != null) {
            try {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream(contentLength > 0 ? contentLength + 4096 : 16384);
                byte[] data = new byte[16384];
                while (true) {
                    int read = is.read(data, 0, data.length);
                    int nRead = read;
                    if (read == -1) {
                        break;
                    }
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                read(buffer.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        if (this.mainPixels != null) {
            this.bitmapProvider.release(this.mainPixels);
        }
        if (this.mainScratch != null) {
            this.bitmapProvider.release(this.mainScratch);
        }
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        if (this.block != null) {
            this.bitmapProvider.release(this.block);
        }
    }

    public synchronized void setData(@NonNull GifHeader header2, @NonNull byte[] data) {
        setData(header2, ByteBuffer.wrap(data));
    }

    public synchronized void setData(@NonNull GifHeader header2, @NonNull ByteBuffer buffer) {
        setData(header2, buffer, 1);
    }

    public synchronized void setData(@NonNull GifHeader header2, @NonNull ByteBuffer buffer, int sampleSize2) {
        if (sampleSize2 > 0) {
            int sampleSize3 = Integer.highestOneBit(sampleSize2);
            this.status = 0;
            this.header = header2;
            this.framePointer = -1;
            this.rawData = buffer.asReadOnlyBuffer();
            this.rawData.position(0);
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.savePrevious = false;
            Iterator<GifFrame> it = header2.frames.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            }
            this.sampleSize = sampleSize3;
            this.downsampledWidth = header2.width / sampleSize3;
            this.downsampledHeight = header2.height / sampleSize3;
            this.mainPixels = this.bitmapProvider.obtainByteArray(header2.width * header2.height);
            this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + sampleSize2);
        }
    }

    @NonNull
    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public synchronized int read(@Nullable byte[] data) {
        this.header = getHeaderParser().setData(data).parseHeader();
        if (data != null) {
            setData(this.header, data);
        }
        return this.status;
    }

    public void setDefaultBitmapConfig(@NonNull Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.bitmapConfig = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    private Bitmap setPixels(GifFrame currentFrame, GifFrame previousFrame) {
        int[] dest = this.mainScratch;
        if (previousFrame == null) {
            if (this.previousImage != null) {
                this.bitmapProvider.release(this.previousImage);
            }
            this.previousImage = null;
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose == 3 && this.previousImage == null) {
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose > 0) {
            if (previousFrame.dispose == 2) {
                int c = 0;
                if (!currentFrame.transparency) {
                    c = this.header.bgColor;
                    if (currentFrame.lct != null && this.header.bgIndex == currentFrame.transIndex) {
                        c = 0;
                    }
                } else if (this.framePointer == 0) {
                    this.isFirstFrameTransparent = true;
                }
                int downsampledIH = previousFrame.ih / this.sampleSize;
                int downsampledIY = previousFrame.iy / this.sampleSize;
                int downsampledIW = previousFrame.iw / this.sampleSize;
                int topLeft = (this.downsampledWidth * downsampledIY) + (previousFrame.ix / this.sampleSize);
                int bottomLeft = (this.downsampledWidth * downsampledIH) + topLeft;
                int left = topLeft;
                while (left < bottomLeft) {
                    int right = left + downsampledIW;
                    for (int pointer = left; pointer < right; pointer++) {
                        dest[pointer] = c;
                    }
                    left += this.downsampledWidth;
                }
            } else if (previousFrame.dispose == 3 && this.previousImage != null) {
                this.previousImage.getPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
            }
        }
        decodeBitmapData(currentFrame);
        if (currentFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(currentFrame);
        } else {
            copyIntoScratchFast(currentFrame);
        }
        if (this.savePrevious && (currentFrame.dispose == 0 || currentFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
        }
        Bitmap result = getNextBitmap();
        result.setPixels(dest, 0, this.downsampledWidth, 0, 0, this.downsampledWidth, this.downsampledHeight);
        return result;
    }

    private void copyIntoScratchFast(GifFrame currentFrame) {
        GifFrame gifFrame = currentFrame;
        int[] dest = this.mainScratch;
        int downsampledIH = gifFrame.ih;
        int downsampledIY = gifFrame.iy;
        int downsampledIW = gifFrame.iw;
        int downsampledIX = gifFrame.ix;
        boolean isFirstFrame = this.framePointer == 0;
        int width = this.downsampledWidth;
        byte[] mainPixels2 = this.mainPixels;
        int[] act2 = this.act;
        byte transparentColorIndex = -1;
        int i = 0;
        while (i < downsampledIH) {
            int k = (i + downsampledIY) * width;
            int dx = k + downsampledIX;
            int dlim = dx + downsampledIW;
            if (k + width < dlim) {
                dlim = k + width;
            }
            int sx = gifFrame.iw * i;
            byte transparentColorIndex2 = transparentColorIndex;
            int dx2 = dx;
            while (dx2 < dlim) {
                int downsampledIH2 = downsampledIH;
                byte byteCurrentColorIndex = mainPixels2[sx];
                int downsampledIY2 = downsampledIY;
                byte currentColorIndex = byteCurrentColorIndex & 255;
                if (currentColorIndex != transparentColorIndex2) {
                    int color = act2[currentColorIndex];
                    if (color != 0) {
                        dest[dx2] = color;
                    } else {
                        transparentColorIndex2 = byteCurrentColorIndex;
                    }
                }
                sx++;
                dx2++;
                downsampledIH = downsampledIH2;
                downsampledIY = downsampledIY2;
            }
            int i2 = downsampledIY;
            i++;
            transparentColorIndex = transparentColorIndex2;
            gifFrame = currentFrame;
        }
        int i3 = downsampledIY;
        this.isFirstFrameTransparent = Boolean.valueOf(this.isFirstFrameTransparent == null && isFirstFrame && transparentColorIndex != -1);
    }

    private void copyCopyIntoScratchRobust(GifFrame currentFrame) {
        boolean z;
        int downsampledIX;
        int downsampledIW;
        int downsampledIY;
        GifFrame gifFrame = currentFrame;
        int[] dest = this.mainScratch;
        int downsampledIH = gifFrame.ih / this.sampleSize;
        int downsampledIY2 = gifFrame.iy / this.sampleSize;
        int downsampledIW2 = gifFrame.iw / this.sampleSize;
        int downsampledIX2 = gifFrame.ix / this.sampleSize;
        int iline = 0;
        boolean isFirstFrame = this.framePointer == 0;
        int sampleSize2 = this.sampleSize;
        int downsampledWidth2 = this.downsampledWidth;
        int downsampledHeight2 = this.downsampledHeight;
        byte[] mainPixels2 = this.mainPixels;
        int[] act2 = this.act;
        int pass = 1;
        int inc = 8;
        Boolean isFirstFrameTransparent2 = this.isFirstFrameTransparent;
        int i = 0;
        while (i < downsampledIH) {
            int line = i;
            boolean isFirstFrameTransparent3 = isFirstFrameTransparent2;
            if (gifFrame.interlace) {
                if (iline >= downsampledIH) {
                    pass++;
                    switch (pass) {
                        case 2:
                            iline = 4;
                            break;
                        case 3:
                            iline = 2;
                            inc = 4;
                            break;
                        case 4:
                            iline = 1;
                            inc = 2;
                            break;
                    }
                }
                line = iline;
                iline += inc;
            }
            int line2 = line + downsampledIY2;
            int downsampledIH2 = downsampledIH;
            boolean isNotDownsampling = sampleSize2 == 1;
            if (line2 < downsampledHeight2) {
                int k = line2 * downsampledWidth2;
                int dx = k + downsampledIX2;
                downsampledIY = downsampledIY2;
                int dlim = dx + downsampledIW2;
                downsampledIW = downsampledIW2;
                if (k + downsampledWidth2 < dlim) {
                    dlim = k + downsampledWidth2;
                }
                downsampledIX = downsampledIX2;
                int sx = i * sampleSize2 * gifFrame.iw;
                if (isNotDownsampling) {
                    int sx2 = sx;
                    int dx2 = dx;
                    while (dx2 < dlim) {
                        boolean isNotDownsampling2 = isNotDownsampling;
                        int averageColor = act2[mainPixels2[sx2] & 255];
                        if (averageColor != 0) {
                            dest[dx2] = averageColor;
                        } else if (isFirstFrame && isFirstFrameTransparent3 == null) {
                            isFirstFrameTransparent3 = true;
                        }
                        sx2 += sampleSize2;
                        dx2++;
                        isNotDownsampling = isNotDownsampling2;
                    }
                    isFirstFrameTransparent2 = isFirstFrameTransparent3;
                } else {
                    int maxPositionInSource = ((dlim - dx) * sampleSize2) + sx;
                    int sx3 = sx;
                    int dx3 = dx;
                    while (dx3 < dlim) {
                        int dlim2 = dlim;
                        int averageColor2 = averageColorsNear(sx3, maxPositionInSource, gifFrame.iw);
                        if (averageColor2 != 0) {
                            dest[dx3] = averageColor2;
                        } else if (isFirstFrame && isFirstFrameTransparent3 == null) {
                            isFirstFrameTransparent3 = true;
                            sx3 += sampleSize2;
                            dx3++;
                            dlim = dlim2;
                        }
                        sx3 += sampleSize2;
                        dx3++;
                        dlim = dlim2;
                    }
                    isFirstFrameTransparent2 = isFirstFrameTransparent3;
                }
            } else {
                downsampledIY = downsampledIY2;
                downsampledIW = downsampledIW2;
                downsampledIX = downsampledIX2;
                isFirstFrameTransparent2 = isFirstFrameTransparent3;
            }
            i++;
            downsampledIH = downsampledIH2;
            downsampledIY2 = downsampledIY;
            downsampledIW2 = downsampledIW;
            downsampledIX2 = downsampledIX;
        }
        int i2 = downsampledIY2;
        int i3 = downsampledIW2;
        int i4 = downsampledIX2;
        Boolean isFirstFrameTransparent4 = isFirstFrameTransparent2;
        if (this.isFirstFrameTransparent == null) {
            if (isFirstFrameTransparent4 == null) {
                z = false;
            } else {
                z = isFirstFrameTransparent4.booleanValue();
            }
            this.isFirstFrameTransparent = Boolean.valueOf(z);
        }
    }

    @ColorInt
    private int averageColorsNear(int positionInMainPixels, int maxPositionInMainPixels, int currentFrameIw) {
        int totalAdded = 0;
        int blueSum = 0;
        int greenSum = 0;
        int redSum = 0;
        int alphaSum = 0;
        int i = positionInMainPixels;
        while (i < this.sampleSize + positionInMainPixels && i < this.mainPixels.length && i < maxPositionInMainPixels) {
            int currentColor = this.act[this.mainPixels[i] & 255];
            if (currentColor != 0) {
                alphaSum += (currentColor >> 24) & 255;
                redSum += (currentColor >> 16) & 255;
                greenSum += (currentColor >> 8) & 255;
                blueSum += currentColor & 255;
                totalAdded++;
            }
            i++;
        }
        int i2 = positionInMainPixels + currentFrameIw;
        while (i2 < positionInMainPixels + currentFrameIw + this.sampleSize && i2 < this.mainPixels.length && i2 < maxPositionInMainPixels) {
            int currentColor2 = this.act[this.mainPixels[i2] & 255];
            if (currentColor2 != 0) {
                alphaSum += (currentColor2 >> 24) & 255;
                redSum += (currentColor2 >> 16) & 255;
                greenSum += (currentColor2 >> 8) & 255;
                blueSum += currentColor2 & 255;
                totalAdded++;
            }
            i2++;
        }
        if (totalAdded == 0) {
            return 0;
        }
        return ((alphaSum / totalAdded) << 24) | ((redSum / totalAdded) << 16) | ((greenSum / totalAdded) << 8) | (blueSum / totalAdded);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v11, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x0019
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r2 = r2.width
            com.bumptech.glide.gifdecoder.GifHeader r3 = r0.header
            int r3 = r3.height
        L_0x0017:
            int r2 = r2 * r3
            goto L_0x001e
        L_0x0019:
            int r2 = r1.iw
            int r3 = r1.ih
            goto L_0x0017
        L_0x001e:
            byte[] r3 = r0.mainPixels
            if (r3 == 0) goto L_0x0027
            byte[] r3 = r0.mainPixels
            int r3 = r3.length
            if (r3 >= r2) goto L_0x002f
        L_0x0027:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r3 = r0.bitmapProvider
            byte[] r3 = r3.obtainByteArray(r2)
            r0.mainPixels = r3
        L_0x002f:
            byte[] r3 = r0.mainPixels
            short[] r4 = r0.prefix
            r5 = 4096(0x1000, float:5.74E-42)
            if (r4 != 0) goto L_0x003b
            short[] r4 = new short[r5]
            r0.prefix = r4
        L_0x003b:
            short[] r4 = r0.prefix
            byte[] r6 = r0.suffix
            if (r6 != 0) goto L_0x0045
            byte[] r6 = new byte[r5]
            r0.suffix = r6
        L_0x0045:
            byte[] r6 = r0.suffix
            byte[] r7 = r0.pixelStack
            if (r7 != 0) goto L_0x0051
            r7 = 4097(0x1001, float:5.741E-42)
            byte[] r7 = new byte[r7]
            r0.pixelStack = r7
        L_0x0051:
            byte[] r7 = r0.pixelStack
            int r8 = r27.readByte()
            r9 = 1
            int r10 = r9 << r8
            int r11 = r10 + 1
            int r12 = r10 + 2
            r13 = -1
            int r14 = r8 + 1
            int r15 = r9 << r14
            int r15 = r15 - r9
            r5 = 0
            r16 = r5
        L_0x0067:
            r17 = r16
            r9 = r17
            if (r9 >= r10) goto L_0x0077
            r4[r9] = r5
            byte r5 = (byte) r9
            r6[r9] = r5
            int r16 = r9 + 1
            r5 = 0
            r9 = 1
            goto L_0x0067
        L_0x0077:
            byte[] r5 = r0.block
            r16 = 0
            r17 = r16
            r19 = r16
            r20 = r16
            r21 = r16
            r22 = r16
            r23 = r16
            r24 = r16
            r16 = r15
            r15 = r12
            r12 = r9
            r9 = 0
            r26 = r19
            r19 = r14
            r14 = r26
        L_0x0094:
            if (r9 >= r2) goto L_0x0165
            if (r22 != 0) goto L_0x00a5
            int r22 = r27.readBlock()
            if (r22 > 0) goto L_0x00a3
            r1 = 3
            r0.status = r1
            goto L_0x0165
        L_0x00a3:
            r17 = 0
        L_0x00a5:
            byte r1 = r5[r17]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r23
            int r24 = r24 + r1
            int r23 = r23 + 8
            r1 = 1
            int r17 = r17 + 1
            r1 = -1
            int r22 = r22 + -1
            r25 = r21
            r21 = r14
            r14 = r13
            r13 = r12
            r12 = r19
            r19 = r9
            r9 = r23
        L_0x00c1:
            if (r9 < r12) goto L_0x014f
            r13 = r24 & r16
            int r24 = r24 >> r12
            int r9 = r9 - r12
            if (r13 != r10) goto L_0x00d6
            int r12 = r8 + 1
            r18 = 1
            int r23 = r18 << r12
            int r16 = r23 + -1
            int r15 = r10 + 2
            r14 = -1
            goto L_0x00c1
        L_0x00d6:
            r18 = 1
            if (r13 != r11) goto L_0x00ea
            r23 = r9
            r9 = r19
            r1 = r28
            r19 = r12
            r12 = r13
            r13 = r14
            r14 = r21
            r21 = r25
            goto L_0x0094
        L_0x00ea:
            if (r14 != r1) goto L_0x00f8
            byte r23 = r6[r13]
            r3[r21] = r23
            int r21 = r21 + 1
            int r19 = r19 + 1
            r14 = r13
            r25 = r13
            goto L_0x00c1
        L_0x00f8:
            r23 = r13
            if (r13 < r15) goto L_0x0106
            r1 = r25
            byte r0 = (byte) r1
            r7[r20] = r0
            int r20 = r20 + 1
            r0 = r14
            r13 = r0
            goto L_0x0108
        L_0x0106:
            r1 = r25
        L_0x0108:
            if (r13 < r10) goto L_0x0113
            byte r0 = r6[r13]
            r7[r20] = r0
            int r20 = r20 + 1
            short r13 = r4[r13]
            goto L_0x0108
        L_0x0113:
            byte r0 = r6[r13]
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r0
            r3[r21] = r1
            int r21 = r21 + 1
            int r19 = r19 + 1
        L_0x011e:
            if (r20 <= 0) goto L_0x012b
            int r20 = r20 + -1
            byte r1 = r7[r20]
            r3[r21] = r1
            int r21 = r21 + 1
            int r19 = r19 + 1
            goto L_0x011e
        L_0x012b:
            r1 = 4096(0x1000, float:5.74E-42)
            if (r15 >= r1) goto L_0x0146
            short r1 = (short) r14
            r4[r15] = r1
            byte r1 = (byte) r0
            r6[r15] = r1
            int r15 = r15 + 1
            r1 = r15 & r16
            if (r1 != 0) goto L_0x0144
            r1 = 4096(0x1000, float:5.74E-42)
            if (r15 >= r1) goto L_0x0146
            int r12 = r12 + 1
            int r16 = r16 + r15
            goto L_0x0146
        L_0x0144:
            r1 = 4096(0x1000, float:5.74E-42)
        L_0x0146:
            r14 = r23
            r25 = r0
            r0 = r27
            r1 = -1
            goto L_0x00c1
        L_0x014f:
            r1 = r25
            r18 = 1
            r23 = r9
            r9 = r19
            r0 = r27
            r19 = r12
            r12 = r13
            r13 = r14
            r14 = r21
            r21 = r1
            r1 = r28
            goto L_0x0094
        L_0x0165:
            r0 = 0
            java.util.Arrays.fill(r3, r14, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    private int readBlock() {
        int blockSize = readByte();
        if (blockSize <= 0) {
            return blockSize;
        }
        this.rawData.get(this.block, 0, Math.min(blockSize, this.rawData.remaining()));
        return blockSize;
    }

    private Bitmap getNextBitmap() {
        Bitmap result = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (this.isFirstFrameTransparent == null || this.isFirstFrameTransparent.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        result.setHasAlpha(true);
        return result;
    }
}
