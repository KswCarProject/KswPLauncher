package android.support.p001v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.p001v4.provider.FontsContractCompat;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: android.support.v4.graphics.TypefaceCompatApi21Impl */
/* loaded from: classes.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    TypefaceCompatApi21Impl() {
    }

    private File getFile(ParcelFileDescriptor fd) {
        try {
            String path = Os.readlink("/proc/self/fd/" + fd.getFd());
            if (!OsConstants.S_ISREG(Os.stat(path).st_mode)) {
                return null;
            }
            return new File(path);
        } catch (ErrnoException e) {
            return null;
        }
    }

    @Override // android.support.p001v4.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fonts, int style) {
        if (fonts.length < 1) {
            return null;
        }
        FontsContractCompat.FontInfo bestFont = findBestInfo(fonts, style);
        ContentResolver resolver = context.getContentResolver();
        try {
            ParcelFileDescriptor pfd = resolver.openFileDescriptor(bestFont.getUri(), "r", cancellationSignal);
            File file = getFile(pfd);
            if (file != null && file.canRead()) {
                Typeface createFromFile = Typeface.createFromFile(file);
                if (pfd != null) {
                    pfd.close();
                }
                return createFromFile;
            }
            FileInputStream fis = new FileInputStream(pfd.getFileDescriptor());
            try {
                Typeface createFromInputStream = super.createFromInputStream(context, fis);
                fis.close();
                if (pfd != null) {
                    pfd.close();
                }
                return createFromInputStream;
            } finally {
            }
        } catch (IOException e) {
            return null;
        }
    }
}
