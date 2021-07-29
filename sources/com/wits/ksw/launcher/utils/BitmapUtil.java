package com.wits.ksw.launcher.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import com.wits.ksw.MainActivity;
import com.wits.ksw.R;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Configs;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;

public class BitmapUtil {
    public static Bitmap getDownBitMap() {
        return drawableToBitmap(zoomDrawable(MainActivity.mainActivity.getDrawable(R.drawable.benz_mbux_2021_album_selector), ScreenUtil.dip2px(200.0f), ScreenUtil.dip2px(200.0f)));
    }

    public static Drawable getDefaultMBUX2021BG_OTHER() {
        String bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_INDEX);
        if (TextUtils.isEmpty(bgIndex)) {
            bgIndex = "1";
        }
        int iBgIndex = Integer.parseInt(bgIndex);
        LOGE.D("liuhaoMedia____________________getDefaultMBUX2021BG_OTHER_______________ iBgIndex = " + iBgIndex);
        return MainActivity.mainActivity.getDrawable(BenzMbux2021Configs.BG_OTHER[iBgIndex - 1]);
    }

    public static Drawable getDefaultMBUX2021BG_ONE() {
        String bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), BenzMbux2021Configs.BG_INDEX);
        if (TextUtils.isEmpty(bgIndex)) {
            bgIndex = "1";
        }
        int iBgIndex = Integer.parseInt(bgIndex);
        LOGE.D("liuhaoMedia____________________getDefaultMBUX2021BG_ONE_______________ iBgIndex = " + iBgIndex);
        return MainActivity.mainActivity.getDrawable(BenzMbux2021Configs.BG_ONE[iBgIndex - 1]);
    }

    public static boolean isBenzMbux2021() {
        return UiThemeUtils.isBenz_MBUX_2021(MainActivity.mainActivity);
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) width) / ((float) w), ((float) height) / ((float) h));
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        matrix.postScale(((float) w) / ((float) width), ((float) h) / ((float) height));
        return new BitmapDrawable((Resources) null, Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true));
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, w, h);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w, h / 2, matrix, false);
        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h / 2) + h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        Canvas canvas2 = canvas;
        canvas2.drawRect(0.0f, (float) h, (float) w, (float) (h + 4), new Paint());
        canvas.drawBitmap(reflectionImage, 0.0f, (float) (h + 4), (Paint) null);
        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0.0f, (float) bitmap.getHeight(), 0.0f, (float) (bitmapWithReflection.getHeight() + 4), 1895825407, ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, (float) h, (float) w, (float) (bitmapWithReflection.getHeight() + 4), paint);
        return bitmapWithReflection;
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);
        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }

    public static void saveFile(Bitmap bitmap, String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            LOGE.D("Exception:FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e2) {
            LOGE.D("IOException:IOException");
            e2.printStackTrace();
        }
    }

    public static Drawable getResourceDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    public static Bitmap getResourceBitmap(Context context, int resId) {
        return BitmapFactory.decodeStream(context.getResources().openRawResource(resId));
    }

    public static Uri bitmapToUri(Bitmap bitmap, Context context) {
        return Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, (String) null, (String) null));
    }

    public static Bitmap uriToBitmap(Uri uri, Context context) {
        try {
            return zoomBitmap(MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri), 200, 200);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveBitmap2file(Bitmap bmp, String filename) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream("/sdcard/" + filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmp.compress(format, 90, stream);
    }

    private static Bitmap big(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(1.5f, 1.5f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static File small(File file) {
        File resultFile = file;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(resultFile.getPath(), options);
        int height = options.outHeight;
        int width = options.outWidth;
        int scale = 1;
        if (height > 1000 || width > 1000) {
            scale = 2;
        }
        if (height > 2000 || width > 2000) {
            scale = 3;
        }
        if (height > 300 || width > 3000) {
            scale = 4;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap2 = BitmapFactory.decodeFile(resultFile.getPath(), options);
        try {
            bitmap2.compress(Bitmap.CompressFormat.PNG, 30, new FileOutputStream(resultFile, false));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resultFile;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        float dst_right;
        float dst_top;
        float dst_left;
        float bottom;
        float top;
        float right;
        float left;
        float clip;
        float roundPx;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            roundPx = (float) (width / 2);
            clip = 0.0f;
            left = 0.0f;
            right = (float) width;
            top = (float) width;
            height = width;
            bottom = 0.0f;
            dst_left = 0.0f;
            dst_top = (float) width;
            dst_right = (float) width;
        } else {
            roundPx = (float) (height / 2);
            clip = (float) ((width - height) / 2);
            float f = clip;
            right = ((float) width) - clip;
            width = height;
            left = 0.0f;
            top = (float) height;
            bottom = 0.0f;
            dst_left = 0.0f;
            dst_top = (float) height;
            dst_right = (float) height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        int i = width;
        int i2 = height;
        float f2 = clip;
        float f3 = left;
        Rect src = new Rect((int) clip, (int) left, (int) right, (int) top);
        float f4 = right;
        Rect dst = new Rect((int) bottom, (int) dst_left, (int) dst_top, (int) dst_right);
        new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(roundPx, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    public static Bitmap createBlurBitmap(Bitmap sentBitmap, int radius) {
        int i;
        int h;
        int i2;
        int p = radius;
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        if (p < 1) {
            return null;
        }
        int w = bitmap.getWidth();
        int rbs = bitmap.getHeight();
        int[] pix = new int[(w * rbs)];
        bitmap.getPixels(pix, 0, w, 0, 0, w, rbs);
        int wm = w - 1;
        int p2 = rbs - 1;
        int bsum = w * rbs;
        int div = p + p + 1;
        int[] r = new int[bsum];
        int[] g = new int[bsum];
        int[] b = new int[bsum];
        int[] vmin = new int[Math.max(w, rbs)];
        int divsum = (div + 1) >> 1;
        int divsum2 = divsum * divsum;
        int[] dv = new int[(divsum2 * 256)];
        int i3 = 0;
        while (i3 < divsum2 * 256) {
            dv[i3] = i3 / divsum2;
            i3++;
            Bitmap bitmap2 = sentBitmap;
        }
        int yi = 0;
        int yw = 0;
        int i4 = i3;
        int[] iArr = new int[2];
        iArr[1] = 3;
        iArr[0] = div;
        int[][] stack = (int[][]) Array.newInstance(int.class, iArr);
        int r1 = p + 1;
        int divsum3 = divsum2;
        int y = 0;
        while (y < rbs) {
            int hm = 0;
            int rsum = 0;
            int boutsum = 0;
            int goutsum = 0;
            int routsum = 0;
            int binsum = 0;
            int ginsum = 0;
            int rinsum = 0;
            int wh = bsum;
            int gsum = 0;
            Bitmap bitmap3 = bitmap;
            int i5 = -p;
            int bsum2 = 0;
            while (i5 <= p) {
                int h2 = rbs;
                int h3 = hm;
                int hm2 = p2;
                int p3 = pix[yi + Math.min(wm, Math.max(i5, h3))];
                int[] sir = stack[i5 + p];
                sir[h3] = (p3 & 16711680) >> 16;
                sir[1] = (p3 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                sir[2] = p3 & 255;
                int rbs2 = r1 - Math.abs(i5);
                rsum += sir[0] * rbs2;
                gsum += sir[1] * rbs2;
                bsum2 += sir[2] * rbs2;
                if (i5 > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
                i5++;
                p2 = hm2;
                rbs = h2;
                hm = 0;
            }
            int hm3 = p2;
            int h4 = rbs;
            int stackpointer = radius;
            int x = 0;
            while (x < w) {
                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum2];
                int rsum2 = rsum - routsum;
                int gsum2 = gsum - goutsum;
                int bsum3 = bsum2 - boutsum;
                int[] sir2 = stack[((stackpointer - p) + div) % div];
                int routsum2 = routsum - sir2[0];
                int goutsum2 = goutsum - sir2[1];
                int boutsum2 = boutsum - sir2[2];
                if (y == 0) {
                    i2 = i5;
                    vmin[x] = Math.min(x + p + 1, wm);
                } else {
                    i2 = i5;
                }
                int p4 = pix[yw + vmin[x]];
                sir2[0] = (p4 & 16711680) >> 16;
                sir2[1] = (p4 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                int wm2 = wm;
                sir2[2] = p4 & 255;
                int rinsum2 = rinsum + sir2[0];
                int ginsum2 = ginsum + sir2[1];
                int binsum2 = binsum + sir2[2];
                rsum = rsum2 + rinsum2;
                gsum = gsum2 + ginsum2;
                bsum2 = bsum3 + binsum2;
                stackpointer = (stackpointer + 1) % div;
                int[] sir3 = stack[stackpointer % div];
                routsum = routsum2 + sir3[0];
                goutsum = goutsum2 + sir3[1];
                boutsum = boutsum2 + sir3[2];
                rinsum = rinsum2 - sir3[0];
                ginsum = ginsum2 - sir3[1];
                binsum = binsum2 - sir3[2];
                yi++;
                x++;
                wm = wm2;
                i5 = i2;
            }
            int i6 = i5;
            int i7 = wm;
            yw += w;
            y++;
            p2 = hm3;
            bitmap = bitmap3;
            bsum = wh;
            rbs = h4;
            int gsum3 = i6;
        }
        Bitmap bitmap4 = bitmap;
        int wh2 = bsum;
        int hm4 = p2;
        int h5 = rbs;
        int i8 = wm;
        int x2 = 0;
        int h6 = y;
        while (x2 < w) {
            int bsum4 = 0;
            int gsum4 = 0;
            int rsum3 = 0;
            int boutsum3 = 0;
            int i9 = h6;
            int i10 = -p;
            int yp = (-p) * w;
            int rinsum3 = 0;
            int ginsum3 = 0;
            int binsum3 = 0;
            int routsum3 = 0;
            int goutsum3 = 0;
            int goutsum4 = i9;
            while (i10 <= p) {
                int[] vmin2 = vmin;
                int yi2 = Math.max(0, yp) + x2;
                int[] sir4 = stack[i10 + p];
                sir4[0] = r[yi2];
                sir4[1] = g[yi2];
                sir4[2] = b[yi2];
                int rbs3 = r1 - Math.abs(i10);
                rsum3 += r[yi2] * rbs3;
                gsum4 += g[yi2] * rbs3;
                bsum4 += b[yi2] * rbs3;
                if (i10 > 0) {
                    rinsum3 += sir4[0];
                    ginsum3 += sir4[1];
                    binsum3 += sir4[2];
                } else {
                    routsum3 += sir4[0];
                    goutsum3 += sir4[1];
                    boutsum3 += sir4[2];
                }
                int i11 = rbs3;
                int hm5 = hm4;
                if (i10 < hm5) {
                    yp += w;
                }
                i10++;
                hm4 = hm5;
                vmin = vmin2;
            }
            int[] vmin3 = vmin;
            int hm6 = hm4;
            int rsum4 = rsum3;
            int y2 = 0;
            int stackpointer2 = radius;
            int boutsum4 = boutsum3;
            int yi3 = x2;
            while (true) {
                i = i10;
                h = h5;
                if (y2 >= h) {
                    break;
                }
                pix[yi3] = (pix[yi3] & ViewCompat.MEASURED_STATE_MASK) | (dv[rsum4] << 16) | (dv[gsum4] << 8) | dv[bsum4];
                int rsum5 = rsum4 - routsum3;
                int gsum5 = gsum4 - goutsum3;
                int bsum5 = bsum4 - boutsum4;
                int[] sir5 = stack[((stackpointer2 - p) + div) % div];
                int routsum4 = routsum3 - sir5[0];
                int goutsum5 = goutsum3 - sir5[1];
                int boutsum5 = boutsum4 - sir5[2];
                if (x2 == 0) {
                    vmin3[y2] = Math.min(y2 + r1, hm6) * w;
                }
                int p5 = vmin3[y2] + x2;
                sir5[0] = r[p5];
                sir5[1] = g[p5];
                sir5[2] = b[p5];
                int rinsum4 = rinsum3 + sir5[0];
                int ginsum4 = ginsum3 + sir5[1];
                int binsum4 = binsum3 + sir5[2];
                rsum4 = rsum5 + rinsum4;
                gsum4 = gsum5 + ginsum4;
                bsum4 = bsum5 + binsum4;
                stackpointer2 = (stackpointer2 + 1) % div;
                int[] sir6 = stack[stackpointer2];
                routsum3 = routsum4 + sir6[0];
                goutsum3 = goutsum5 + sir6[1];
                boutsum4 = boutsum5 + sir6[2];
                rinsum3 = rinsum4 - sir6[0];
                ginsum3 = ginsum4 - sir6[1];
                binsum3 = binsum4 - sir6[2];
                yi3 += w;
                y2++;
                p = radius;
                h5 = h;
                i10 = i;
            }
            x2++;
            p = radius;
            hm4 = hm6;
            h5 = h;
            h6 = y2;
            int rsum6 = yi3;
            int yi4 = i;
            vmin = vmin3;
        }
        int i12 = h6;
        int[] iArr2 = dv;
        int i13 = divsum3;
        int i14 = hm4;
        int[] iArr3 = vmin;
        int[] iArr4 = b;
        int[] iArr5 = g;
        int[] iArr6 = r;
        int i15 = wh2;
        bitmap4.setPixels(pix, 0, w, 0, 0, w, h5);
        return bitmap4;
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int cropWidth = w >= (h * 16) / 9 ? h : w;
        int cropHeight = (cropWidth * 720) / 1280;
        return Bitmap.createBitmap(bitmap, (w - cropWidth) / 2, (h - cropHeight) / 2, cropWidth, cropHeight, (Matrix) null, false);
    }

    public static Bitmap compoundBitmap(Bitmap downBitmap, Bitmap upBitmap) {
        Bitmap mBitmap = downBitmap.copy(Bitmap.Config.ARGB_8888, true);
        int mBitmapWidth = mBitmap.getWidth();
        int mBitmapHeight = mBitmap.getHeight();
        if (mBitmapWidth == upBitmap.getWidth() && mBitmapHeight == upBitmap.getHeight()) {
            for (int i = 0; i < mBitmapHeight; i++) {
                for (int j = 0; j < mBitmapWidth; j++) {
                    if (upBitmap.getPixel(j, i) != -16777216) {
                        mBitmap.setPixel(j, i, upBitmap.getPixel(j, i));
                    }
                }
            }
        }
        return mBitmap;
    }

    public static Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(), firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, new Matrix(), (Paint) null);
        canvas.drawBitmap(secondBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmap;
    }

    public static Drawable getResultCompound(Drawable up) {
        return bitmapToDrawable(mergeBitmap(drawableToBitmap(zoomDrawable(up, ScreenUtil.dip2px(200.0f), ScreenUtil.dip2px(200.0f))), getDownBitMap()));
    }

    public static Drawable maskDrawable(Drawable in) {
        Bitmap before = IconUtils.DrawableToBitmap(in);
        Bitmap shade = BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(), R.drawable.benz_mbux_2021_album_selector);
        Bitmap before2 = scaleBitmap(before, shade.getWidth(), shade.getHeight());
        Bitmap after = Bitmap.createBitmap(shade.getWidth(), shade.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(after);
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(shade, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(before2, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(shade, 0.0f, 0.0f, paint);
        return new BitmapDrawable(MainActivity.mainActivity.getResources(), after);
    }

    private static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) newWidth) / ((float) width), ((float) newHeight) / ((float) height));
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, true);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
}
