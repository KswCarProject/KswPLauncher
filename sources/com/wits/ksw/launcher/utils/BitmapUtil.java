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
import android.support.p001v4.view.MotionEventCompat;
import android.support.p001v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import com.wits.ksw.C0899R;
import com.wits.ksw.MainActivity;
import com.wits.ksw.launcher.view.benzmbux2021.BenzMbux2021Configs;
import com.wits.ksw.launcher.view.benzmbux2021ksw.bean.BenzMbux2021KswConfigs;
import com.wits.ksw.launcher.view.lexusls.drag.LOGE;
import com.wits.ksw.settings.TxzMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

/* loaded from: classes11.dex */
public class BitmapUtil {
    public static Bitmap getDownBitMap() {
        return drawableToBitmap(zoomDrawable(MainActivity.mainActivity.getDrawable(C0899R.C0900drawable.benz_mbux_2021_album_selector), ScreenUtil.dip2px(200.0f), ScreenUtil.dip2px(200.0f)));
    }

    public static Drawable getDefaultMBUX2021BG_OTHER() {
        String bgIndex;
        if (UiThemeUtils.isBenz_MBUX_2021_KSW(MainActivity.mainActivity) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024_V2(MainActivity.mainActivity)) {
            bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), "BG_INDEX");
        } else {
            bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), "BG_INDEX");
        }
        if (TextUtils.isEmpty(bgIndex)) {
            bgIndex = TxzMessage.TXZ_SHOW;
        }
        int iBgIndex = Integer.parseInt(bgIndex);
        LOGE.m44D("liuhaoMedia____________________getDefaultMBUX2021BG_OTHER_______________ iBgIndex = " + iBgIndex);
        if (UiThemeUtils.isBenz_MBUX_2021_KSW(MainActivity.mainActivity) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024_V2(MainActivity.mainActivity)) {
            return MainActivity.mainActivity.getDrawable(BenzMbux2021KswConfigs.BG_OTHER[iBgIndex - 1]);
        }
        return MainActivity.mainActivity.getDrawable(BenzMbux2021Configs.BG_OTHER[iBgIndex - 1]);
    }

    public static Drawable getDefaultMBUX2021BG_ONE() {
        String bgIndex;
        if (UiThemeUtils.isBenz_MBUX_2021_KSW(MainActivity.mainActivity) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024_V2(MainActivity.mainActivity)) {
            bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), "BG_INDEX");
        } else {
            bgIndex = Settings.System.getString(MainActivity.mainActivity.getContentResolver(), "BG_INDEX");
        }
        if (TextUtils.isEmpty(bgIndex)) {
            bgIndex = TxzMessage.TXZ_SHOW;
        }
        int iBgIndex = Integer.parseInt(bgIndex);
        LOGE.m44D("liuhaoMedia____________________getDefaultMBUX2021BG_ONE_______________ iBgIndex = " + iBgIndex);
        if (UiThemeUtils.isBenz_MBUX_2021_KSW(MainActivity.mainActivity) || UiThemeUtils.isBenz_MBUX_2021_KSW_V2(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024(MainActivity.mainActivity) || UiThemeUtils.isUI_MBUX_2021_KSW_1024_V2(MainActivity.mainActivity)) {
            return MainActivity.mainActivity.getDrawable(BenzMbux2021KswConfigs.BG_ONE[iBgIndex - 1]);
        }
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
        float scaleWidth = width / w;
        float scaleHeight = height / h;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = w / width;
        float scaleHeight = h / height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
        return new BitmapDrawable((Resources) null, newbmp);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(), C0899R.color.transparent);
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        if (w <= 0 || h <= 0) {
            return BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(), C0899R.color.transparent);
        }
        Bitmap.Config config = drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
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
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0.0f, h, w, h + 4, deafalutPaint);
        canvas.drawBitmap(reflectionImage, 0.0f, h + 4, (Paint) null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0.0f, bitmap.getHeight(), 0.0f, bitmapWithReflection.getHeight() + 4, 1895825407, (int) ViewCompat.MEASURED_SIZE_MASK, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0.0f, h, w, bitmapWithReflection.getHeight() + 4, paint);
        return bitmapWithReflection;
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        return bd;
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
            LOGE.m44D("Exception:FileNotFoundException");
            e.printStackTrace();
        } catch (IOException e2) {
            LOGE.m44D("IOException:IOException");
            e2.printStackTrace();
        }
    }

    public static Drawable getResourceDrawable(Context context, int resId) {
        Resources resources = context.getResources();
        return resources.getDrawable(resId);
    }

    public static Bitmap getResourceBitmap(Context context, int resId) {
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is);
    }

    public static Uri bitmapToUri(Bitmap bitmap, Context context) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, (String) null, (String) null));
        return uri;
    }

    public static Bitmap uriToBitmap(Uri uri, Context context) {
        try {
            Bitmap bitmap = zoomBitmap(MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri), 200, 200);
            return bitmap;
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
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public static File small(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);
        int height = options.outHeight;
        int width = options.outWidth;
        int scale = 1;
        scale = (height > 1000 || width > 1000) ? 2 : 2;
        scale = (height > 2000 || width > 2000) ? 3 : 3;
        scale = (height > 300 || width > 3000) ? 4 : 4;
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap2 = BitmapFactory.decodeFile(file.getPath(), options);
        try {
            FileOutputStream out = new FileOutputStream(file, false);
            bitmap2.compress(Bitmap.CompressFormat.PNG, 30, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        float roundPx;
        float clip;
        float right;
        float left;
        float top;
        float bottom;
        float dst_left;
        float dst_top;
        float dst_right;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            roundPx = width / 2;
            clip = 0.0f;
            left = 0.0f;
            right = width;
            top = width;
            height = width;
            bottom = 0.0f;
            dst_left = 0.0f;
            dst_top = width;
            dst_right = width;
        } else {
            roundPx = height / 2;
            clip = (width - height) / 2;
            right = width - clip;
            float bottom2 = height;
            width = height;
            float dst_right2 = height;
            left = 0.0f;
            top = bottom2;
            bottom = 0.0f;
            dst_left = 0.0f;
            dst_top = dst_right2;
            dst_right = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect src = new Rect((int) clip, (int) left, (int) right, (int) top);
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
        int i2;
        int p = radius;
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        if (p < 1) {
            return null;
        }
        int w = bitmap.getWidth();
        int rbs = bitmap.getHeight();
        int[] pix = new int[w * rbs];
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
        int[] dv = new int[divsum2 * 256];
        for (int i3 = 0; i3 < divsum2 * 256; i3++) {
            dv[i3] = i3 / divsum2;
        }
        int yi = 0;
        int yw = 0;
        int[][] stack = (int[][]) Array.newInstance(int.class, div, 3);
        int r1 = p + 1;
        int divsum3 = 0;
        while (divsum3 < rbs) {
            int hm = 0;
            int rsum = 0;
            int boutsum = 0;
            int goutsum = 0;
            int routsum = 0;
            int binsum = 0;
            int ginsum = 0;
            int rinsum = 0;
            int wh = bsum;
            int wh2 = -p;
            int i4 = 0;
            Bitmap bitmap2 = bitmap;
            int p3 = wh2;
            int bsum2 = 0;
            while (p3 <= p) {
                int h = rbs;
                int h2 = hm;
                int hm2 = p2;
                int hm3 = Math.max(p3, h2);
                int p4 = pix[yi + Math.min(wm, hm3)];
                int[] sir = stack[p3 + p];
                sir[h2] = (p4 & 16711680) >> 16;
                sir[1] = (p4 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                sir[2] = p4 & 255;
                int rbs2 = r1 - Math.abs(p3);
                rsum += sir[0] * rbs2;
                i4 += sir[1] * rbs2;
                bsum2 += sir[2] * rbs2;
                if (p3 > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
                p3++;
                p2 = hm2;
                rbs = h;
                hm = 0;
            }
            int hm4 = p2;
            int h3 = rbs;
            int stackpointer = radius;
            int x = 0;
            while (x < w) {
                r[yi] = dv[rsum];
                g[yi] = dv[i4];
                b[yi] = dv[bsum2];
                int rsum2 = rsum - routsum;
                int gsum = i4 - goutsum;
                int bsum3 = bsum2 - boutsum;
                int stackstart = (stackpointer - p) + div;
                int[] sir2 = stack[stackstart % div];
                int routsum2 = routsum - sir2[0];
                int goutsum2 = goutsum - sir2[1];
                int boutsum2 = boutsum - sir2[2];
                if (divsum3 != 0) {
                    i2 = p3;
                } else {
                    i2 = p3;
                    int i5 = x + p + 1;
                    vmin[x] = Math.min(i5, wm);
                }
                int i6 = vmin[x];
                int p5 = pix[yw + i6];
                sir2[0] = (p5 & 16711680) >> 16;
                sir2[1] = (p5 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                int wm2 = wm;
                int wm3 = p5 & 255;
                sir2[2] = wm3;
                int rinsum2 = rinsum + sir2[0];
                int ginsum2 = ginsum + sir2[1];
                int binsum2 = binsum + sir2[2];
                rsum = rsum2 + rinsum2;
                i4 = gsum + ginsum2;
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
                p3 = i2;
            }
            yw += w;
            divsum3++;
            p2 = hm4;
            bitmap = bitmap2;
            bsum = wh;
            rbs = h3;
        }
        Bitmap bitmap3 = bitmap;
        int hm5 = p2;
        int h4 = rbs;
        int x2 = 0;
        int h5 = divsum3;
        while (x2 < w) {
            int bsum4 = 0;
            int gsum2 = 0;
            int rsum3 = 0;
            int yp = (-p) * w;
            int yp2 = -p;
            int i7 = 0;
            int y = yp2;
            int yp3 = yp;
            int rinsum3 = 0;
            int ginsum3 = 0;
            int binsum3 = 0;
            int binsum4 = 0;
            int routsum3 = 0;
            while (y <= p) {
                int[] vmin2 = vmin;
                int yi2 = Math.max(0, yp3) + x2;
                int[] sir4 = stack[y + p];
                sir4[0] = r[yi2];
                sir4[1] = g[yi2];
                sir4[2] = b[yi2];
                int rbs3 = r1 - Math.abs(y);
                rsum3 += r[yi2] * rbs3;
                gsum2 += g[yi2] * rbs3;
                bsum4 += b[yi2] * rbs3;
                if (y > 0) {
                    rinsum3 += sir4[0];
                    ginsum3 += sir4[1];
                    binsum3 += sir4[2];
                } else {
                    binsum4 += sir4[0];
                    routsum3 += sir4[1];
                    i7 += sir4[2];
                }
                int rbs4 = hm5;
                if (y < rbs4) {
                    yp3 += w;
                }
                y++;
                hm5 = rbs4;
                vmin = vmin2;
            }
            int[] vmin3 = vmin;
            int hm6 = hm5;
            int yi3 = x2;
            int yi4 = rsum3;
            int rsum4 = 0;
            int stackpointer2 = radius;
            int stackpointer3 = i7;
            int boutsum3 = yi3;
            while (true) {
                int i8 = y;
                i = h4;
                if (rsum4 < i) {
                    pix[boutsum3] = (pix[boutsum3] & ViewCompat.MEASURED_STATE_MASK) | (dv[yi4] << 16) | (dv[gsum2] << 8) | dv[bsum4];
                    int rsum5 = yi4 - binsum4;
                    int gsum3 = gsum2 - routsum3;
                    int bsum5 = bsum4 - stackpointer3;
                    int stackstart2 = (stackpointer2 - p) + div;
                    int[] sir5 = stack[stackstart2 % div];
                    int routsum4 = binsum4 - sir5[0];
                    int goutsum3 = routsum3 - sir5[1];
                    int boutsum4 = stackpointer3 - sir5[2];
                    if (x2 == 0) {
                        vmin3[rsum4] = Math.min(rsum4 + r1, hm6) * w;
                    }
                    int p6 = vmin3[rsum4] + x2;
                    sir5[0] = r[p6];
                    sir5[1] = g[p6];
                    sir5[2] = b[p6];
                    int rinsum4 = rinsum3 + sir5[0];
                    int ginsum4 = ginsum3 + sir5[1];
                    int binsum5 = binsum3 + sir5[2];
                    yi4 = rsum5 + rinsum4;
                    gsum2 = gsum3 + ginsum4;
                    bsum4 = bsum5 + binsum5;
                    stackpointer2 = (stackpointer2 + 1) % div;
                    int[] sir6 = stack[stackpointer2];
                    binsum4 = routsum4 + sir6[0];
                    routsum3 = goutsum3 + sir6[1];
                    stackpointer3 = boutsum4 + sir6[2];
                    rinsum3 = rinsum4 - sir6[0];
                    ginsum3 = ginsum4 - sir6[1];
                    binsum3 = binsum5 - sir6[2];
                    boutsum3 += w;
                    rsum4++;
                    p = radius;
                    h4 = i;
                    y = i8;
                }
            }
            x2++;
            p = radius;
            hm5 = hm6;
            h4 = i;
            h5 = rsum4;
            vmin = vmin3;
        }
        int y2 = h4;
        bitmap3.setPixels(pix, 0, w, 0, 0, w, y2);
        return bitmap3;
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int cropWidth = w >= (h * 16) / 9 ? h : w;
        int cropHeight = (cropWidth * 720) / 1280;
        int xTopLeft = (w - cropWidth) / 2;
        int yTopLeft = (h - cropHeight) / 2;
        return Bitmap.createBitmap(bitmap, xTopLeft, yTopLeft, cropWidth, cropHeight, (Matrix) null, false);
    }

    public static Bitmap compoundBitmap(Bitmap downBitmap, Bitmap upBitmap) {
        Bitmap mBitmap = downBitmap.copy(Bitmap.Config.ARGB_8888, true);
        int mBitmapWidth = mBitmap.getWidth();
        int mBitmapHeight = mBitmap.getHeight();
        if (mBitmapWidth == upBitmap.getWidth() && mBitmapHeight == upBitmap.getHeight()) {
            for (int i = 0; i < mBitmapHeight; i++) {
                for (int j = 0; j < mBitmapWidth; j++) {
                    int color = upBitmap.getPixel(j, i);
                    if (color != -16777216) {
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
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 0.0f, 0.0f, (Paint) null);
        return bitmap;
    }

    public static Drawable getResultCompound(Drawable up) {
        return bitmapToDrawable(mergeBitmap(drawableToBitmap(zoomDrawable(up, ScreenUtil.dip2px(200.0f), ScreenUtil.dip2px(200.0f))), getDownBitMap()));
    }

    public static Drawable maskDrawable(Drawable in) {
        Bitmap before = IconUtils.DrawableToBitmap(in);
        Bitmap shade = BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(), C0899R.C0900drawable.benz_mbux_2021_album_selector);
        Bitmap before2 = scaleBitmap(before, shade.getWidth(), shade.getHeight());
        Bitmap after = Bitmap.createBitmap(shade.getWidth(), shade.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(after);
        Paint paint = new Paint(1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(shade, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(before2, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(shade, 0.0f, 0.0f, paint);
        BitmapDrawable drawable = new BitmapDrawable(MainActivity.mainActivity.getResources(), after);
        return drawable;
    }

    private static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = newWidth / width;
        float scaleHeight = newHeight / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, true);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    public static Bitmap getScaleAndRotateBitmap(Drawable drawable, int w, int h, float angle) {
        if (drawable == null) {
            return null;
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = w / width;
        float scaleHeight = h / height;
        matrix.preScale(scaleWidth, scaleHeight);
        matrix.postRotate(angle);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
        return newbmp;
    }
}
