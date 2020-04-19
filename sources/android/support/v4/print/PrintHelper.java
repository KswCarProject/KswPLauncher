package android.support.v4.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class PrintHelper {
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT;
    private static final String LOG_TAG = "PrintHelper";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = (Build.VERSION.SDK_INT < 20 || Build.VERSION.SDK_INT > 23);
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode = 2;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions = null;
    final Object mLock = new Object();
    int mOrientation = 1;
    int mScaleMode = 2;

    public interface OnPrintFinishCallback {
        void onFinish();
    }

    static {
        boolean z = false;
        if (Build.VERSION.SDK_INT != 23) {
            z = true;
        }
        IS_MIN_MARGINS_HANDLING_CORRECT = z;
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public PrintHelper(@NonNull Context context) {
        this.mContext = context;
    }

    public void setScaleMode(int scaleMode) {
        this.mScaleMode = scaleMode;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setColorMode(int colorMode) {
        this.mColorMode = colorMode;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }

    public int getOrientation() {
        if (Build.VERSION.SDK_INT < 19 || this.mOrientation != 0) {
            return this.mOrientation;
        }
        return 1;
    }

    public void printBitmap(@NonNull String jobName, @NonNull Bitmap bitmap) {
        printBitmap(jobName, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String jobName, @NonNull Bitmap bitmap, @Nullable OnPrintFinishCallback callback) {
        PrintAttributes.MediaSize mediaSize;
        if (Build.VERSION.SDK_INT >= 19 && bitmap != null) {
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            if (isPortrait(bitmap)) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            } else {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            }
            printManager.print(jobName, new PrintBitmapAdapter(jobName, this.mScaleMode, bitmap, callback), new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
        }
    }

    @RequiresApi(19)
    private class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        PrintBitmapAdapter(String jobName, int fittingMode, Bitmap bitmap, OnPrintFinishCallback callback) {
            this.mJobName = jobName;
            this.mFittingMode = fittingMode;
            this.mBitmap = bitmap;
            this.mCallback = callback;
        }

        public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.mAttributes = newPrintAttributes;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), true ^ newPrintAttributes.equals(oldPrintAttributes));
        }

        public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
        }

        public void onFinish() {
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
        }
    }

    public void printBitmap(@NonNull String jobName, @NonNull Uri imageFile) throws FileNotFoundException {
        printBitmap(jobName, imageFile, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String jobName, @NonNull Uri imageFile, @Nullable OnPrintFinishCallback callback) throws FileNotFoundException {
        if (Build.VERSION.SDK_INT >= 19) {
            PrintUriAdapter printUriAdapter = new PrintUriAdapter(jobName, imageFile, callback, this.mScaleMode);
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
            }
            printManager.print(jobName, printUriAdapter, builder.build());
        }
    }

    @RequiresApi(19)
    private class PrintUriAdapter extends PrintDocumentAdapter {
        PrintAttributes mAttributes;
        Bitmap mBitmap = null;
        final OnPrintFinishCallback mCallback;
        final int mFittingMode;
        final Uri mImageFile;
        final String mJobName;
        AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

        PrintUriAdapter(String jobName, Uri imageFile, OnPrintFinishCallback callback, int fittingMode) {
            this.mJobName = jobName;
            this.mImageFile = imageFile;
            this.mCallback = callback;
            this.mFittingMode = fittingMode;
        }

        public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            synchronized (this) {
                this.mAttributes = newPrintAttributes;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.mBitmap != null) {
                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), true ^ newPrintAttributes.equals(oldPrintAttributes));
            } else {
                final CancellationSignal cancellationSignal2 = cancellationSignal;
                final PrintAttributes printAttributes = newPrintAttributes;
                final PrintAttributes printAttributes2 = oldPrintAttributes;
                final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback2 = layoutResultCallback;
                this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() {
                    /* access modifiers changed from: protected */
                    public void onPreExecute() {
                        cancellationSignal2.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                            public void onCancel() {
                                PrintUriAdapter.this.cancelLoad();
                                AnonymousClass1.this.cancel(false);
                            }
                        });
                    }

                    /* access modifiers changed from: protected */
                    public Bitmap doInBackground(Uri... uris) {
                        try {
                            return PrintHelper.this.loadConstrainedBitmap(PrintUriAdapter.this.mImageFile);
                        } catch (FileNotFoundException e) {
                            return null;
                        }
                    }

                    /* access modifiers changed from: protected */
                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
                        if (r1 == null) goto L_0x004c;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
                        if (r1.isPortrait() == android.support.v4.print.PrintHelper.isPortrait(r12)) goto L_0x004c;
                     */
                    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
                        r2 = new android.graphics.Matrix();
                        r2.postRotate(90.0f);
                        r12 = android.graphics.Bitmap.createBitmap(r12, 0, 0, r12.getWidth(), r12.getHeight(), r2, true);
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onPostExecute(android.graphics.Bitmap r12) {
                        /*
                            r11 = this;
                            super.onPostExecute(r12)
                            r0 = 0
                            if (r12 == 0) goto L_0x004c
                            boolean r1 = android.support.v4.print.PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION
                            if (r1 == 0) goto L_0x0012
                            android.support.v4.print.PrintHelper$PrintUriAdapter r1 = android.support.v4.print.PrintHelper.PrintUriAdapter.this
                            android.support.v4.print.PrintHelper r1 = android.support.v4.print.PrintHelper.this
                            int r1 = r1.mOrientation
                            if (r1 != 0) goto L_0x004c
                        L_0x0012:
                            monitor-enter(r11)
                            android.support.v4.print.PrintHelper$PrintUriAdapter r1 = android.support.v4.print.PrintHelper.PrintUriAdapter.this     // Catch:{ all -> 0x0049 }
                            android.print.PrintAttributes r1 = r1.mAttributes     // Catch:{ all -> 0x0049 }
                            android.print.PrintAttributes$MediaSize r1 = r1.getMediaSize()     // Catch:{ all -> 0x0049 }
                            monitor-exit(r11)     // Catch:{ all -> 0x0044 }
                            if (r1 == 0) goto L_0x004c
                            boolean r2 = r1.isPortrait()
                            boolean r3 = android.support.v4.print.PrintHelper.isPortrait(r12)
                            if (r2 == r3) goto L_0x004c
                            android.graphics.Matrix r2 = new android.graphics.Matrix
                            r2.<init>()
                            r3 = 1119092736(0x42b40000, float:90.0)
                            r2.postRotate(r3)
                            r4 = 0
                            r5 = 0
                            int r6 = r12.getWidth()
                            int r7 = r12.getHeight()
                            r9 = 1
                            r3 = r12
                            r8 = r2
                            android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r3, r4, r5, r6, r7, r8, r9)
                            goto L_0x004c
                        L_0x0044:
                            r0 = move-exception
                            r10 = r1
                            r1 = r0
                            r0 = r10
                            goto L_0x004a
                        L_0x0049:
                            r1 = move-exception
                        L_0x004a:
                            monitor-exit(r11)     // Catch:{ all -> 0x0049 }
                            throw r1
                        L_0x004c:
                            android.support.v4.print.PrintHelper$PrintUriAdapter r1 = android.support.v4.print.PrintHelper.PrintUriAdapter.this
                            r1.mBitmap = r12
                            if (r12 == 0) goto L_0x0077
                            android.print.PrintDocumentInfo$Builder r1 = new android.print.PrintDocumentInfo$Builder
                            android.support.v4.print.PrintHelper$PrintUriAdapter r2 = android.support.v4.print.PrintHelper.PrintUriAdapter.this
                            java.lang.String r2 = r2.mJobName
                            r1.<init>(r2)
                            r2 = 1
                            android.print.PrintDocumentInfo$Builder r1 = r1.setContentType(r2)
                            android.print.PrintDocumentInfo$Builder r1 = r1.setPageCount(r2)
                            android.print.PrintDocumentInfo r1 = r1.build()
                            android.print.PrintAttributes r3 = r5
                            android.print.PrintAttributes r4 = r6
                            boolean r3 = r3.equals(r4)
                            r2 = r2 ^ r3
                            android.print.PrintDocumentAdapter$LayoutResultCallback r3 = r7
                            r3.onLayoutFinished(r1, r2)
                            goto L_0x007c
                        L_0x0077:
                            android.print.PrintDocumentAdapter$LayoutResultCallback r1 = r7
                            r1.onLayoutFailed(r0)
                        L_0x007c:
                            android.support.v4.print.PrintHelper$PrintUriAdapter r1 = android.support.v4.print.PrintHelper.PrintUriAdapter.this
                            r1.mLoadBitmap = r0
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.print.PrintHelper.PrintUriAdapter.AnonymousClass1.onPostExecute(android.graphics.Bitmap):void");
                    }

                    /* access modifiers changed from: protected */
                    public void onCancelled(Bitmap result) {
                        layoutResultCallback2.onLayoutCancelled();
                        PrintUriAdapter.this.mLoadBitmap = null;
                    }
                }.execute(new Uri[0]);
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelLoad() {
            synchronized (PrintHelper.this.mLock) {
                if (PrintHelper.this.mDecodeOptions != null) {
                    if (Build.VERSION.SDK_INT < 24) {
                        PrintHelper.this.mDecodeOptions.requestCancelDecode();
                    }
                    PrintHelper.this.mDecodeOptions = null;
                }
            }
        }

        public void onFinish() {
            super.onFinish();
            cancelLoad();
            if (this.mLoadBitmap != null) {
                this.mLoadBitmap.cancel(true);
            }
            if (this.mCallback != null) {
                this.mCallback.onFinish();
            }
            if (this.mBitmap != null) {
                this.mBitmap.recycle();
                this.mBitmap = null;
            }
        }

        public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static boolean isPortrait(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    @RequiresApi(19)
    private static PrintAttributes.Builder copyAttributes(PrintAttributes other) {
        PrintAttributes.Builder b = new PrintAttributes.Builder().setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
        if (other.getColorMode() != 0) {
            b.setColorMode(other.getColorMode());
        }
        if (Build.VERSION.SDK_INT >= 23 && other.getDuplexMode() != 0) {
            b.setDuplexMode(other.getDuplexMode());
        }
        return b;
    }

    static Matrix getMatrix(int imageWidth, int imageHeight, RectF content, int fittingMode) {
        float scale;
        Matrix matrix = new Matrix();
        float scale2 = content.width() / ((float) imageWidth);
        if (fittingMode == 2) {
            scale = Math.max(scale2, content.height() / ((float) imageHeight));
        } else {
            scale = Math.min(scale2, content.height() / ((float) imageHeight));
        }
        matrix.postScale(scale, scale);
        matrix.postTranslate((content.width() - (((float) imageWidth) * scale)) / 2.0f, (content.height() - (((float) imageHeight) * scale)) / 2.0f);
        return matrix;
    }

    /* access modifiers changed from: package-private */
    @RequiresApi(19)
    public void writeBitmap(PrintAttributes attributes, int fittingMode, Bitmap bitmap, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        PrintAttributes build;
        if (IS_MIN_MARGINS_HANDLING_CORRECT) {
            build = attributes;
        } else {
            build = copyAttributes(attributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
        }
        final PrintAttributes pdfAttributes = build;
        final CancellationSignal cancellationSignal2 = cancellationSignal;
        final Bitmap bitmap2 = bitmap;
        final PrintAttributes printAttributes = attributes;
        final int i = fittingMode;
        final ParcelFileDescriptor parcelFileDescriptor = fileDescriptor;
        final PrintDocumentAdapter.WriteResultCallback writeResultCallback2 = writeResultCallback;
        new AsyncTask<Void, Void, Throwable>() {
            /* access modifiers changed from: protected */
            public Throwable doInBackground(Void... params) {
                PrintedPdfDocument pdfDocument;
                Bitmap maybeGrayscale;
                RectF contentRect;
                try {
                    if (cancellationSignal2.isCanceled()) {
                        return null;
                    }
                    pdfDocument = new PrintedPdfDocument(PrintHelper.this.mContext, pdfAttributes);
                    maybeGrayscale = PrintHelper.convertBitmapForColorMode(bitmap2, pdfAttributes.getColorMode());
                    if (cancellationSignal2.isCanceled()) {
                        return null;
                    }
                    PdfDocument.Page page = pdfDocument.startPage(1);
                    if (PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                        contentRect = new RectF(page.getInfo().getContentRect());
                    } else {
                        PrintedPdfDocument dummyDocument = new PrintedPdfDocument(PrintHelper.this.mContext, printAttributes);
                        PdfDocument.Page dummyPage = dummyDocument.startPage(1);
                        RectF contentRect2 = new RectF(dummyPage.getInfo().getContentRect());
                        dummyDocument.finishPage(dummyPage);
                        dummyDocument.close();
                        contentRect = contentRect2;
                    }
                    Matrix matrix = PrintHelper.getMatrix(maybeGrayscale.getWidth(), maybeGrayscale.getHeight(), contentRect, i);
                    if (!PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                        matrix.postTranslate(contentRect.left, contentRect.top);
                        page.getCanvas().clipRect(contentRect);
                    }
                    page.getCanvas().drawBitmap(maybeGrayscale, matrix, (Paint) null);
                    pdfDocument.finishPage(page);
                    if (cancellationSignal2.isCanceled()) {
                        pdfDocument.close();
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException e) {
                            }
                        }
                        if (maybeGrayscale != bitmap2) {
                            maybeGrayscale.recycle();
                        }
                        return null;
                    }
                    pdfDocument.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                    pdfDocument.close();
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (maybeGrayscale != bitmap2) {
                        maybeGrayscale.recycle();
                    }
                    return null;
                } catch (Throwable t) {
                    return t;
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Throwable throwable) {
                if (cancellationSignal2.isCanceled()) {
                    writeResultCallback2.onWriteCancelled();
                } else if (throwable == null) {
                    writeResultCallback2.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                } else {
                    Log.e(PrintHelper.LOG_TAG, "Error writing printed content", throwable);
                    writeResultCallback2.onWriteFailed((CharSequence) null);
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        loadBitmap(uri, opt);
        int w = opt.outWidth;
        int h = opt.outHeight;
        if (w <= 0 || h <= 0) {
            return null;
        }
        int imageSide = Math.max(w, h);
        int sampleSize = 1;
        while (imageSide > MAX_PRINT_SIZE) {
            imageSide >>>= 1;
            sampleSize <<= 1;
        }
        if (sampleSize <= 0 || Math.min(w, h) / sampleSize <= 0) {
            return null;
        }
        synchronized (this.mLock) {
            try {
                this.mDecodeOptions = new BitmapFactory.Options();
                this.mDecodeOptions.inMutable = true;
                this.mDecodeOptions.inSampleSize = sampleSize;
                BitmapFactory.Options decodeOptions = this.mDecodeOptions;
                try {
                    try {
                        Bitmap loadBitmap = loadBitmap(uri, decodeOptions);
                        synchronized (this.mLock) {
                            this.mDecodeOptions = null;
                        }
                        return loadBitmap;
                    } catch (Throwable th) {
                        synchronized (this.mLock) {
                            this.mDecodeOptions = null;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    BitmapFactory.Options options = decodeOptions;
                    th = th3;
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        }
    }

    private Bitmap loadBitmap(Uri uri, BitmapFactory.Options o) throws FileNotFoundException {
        String str;
        if (uri == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream is = null;
        try {
            is = this.mContext.getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(is, (Rect) null, o);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException t) {
                    str = "close fail ";
                    Log.w(LOG_TAG, str, t);
                }
            }
        }
    }

    static Bitmap convertBitmapForColorMode(Bitmap original, int colorMode) {
        if (colorMode != 1) {
            return original;
        }
        Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(grayscale);
        Paint p = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0.0f);
        p.setColorFilter(new ColorMatrixColorFilter(cm));
        c.drawBitmap(original, 0.0f, 0.0f, p);
        c.setBitmap((Bitmap) null);
        return grayscale;
    }
}
