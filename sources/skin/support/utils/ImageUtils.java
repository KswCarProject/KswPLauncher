package skin.support.utils;

import android.media.ExifInterface;
import java.io.IOException;

/* loaded from: classes.dex */
public class ImageUtils {
    public static int getImageRotateAngle(String filePath) {
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            exif = null;
        }
        if (exif == null) {
            return 0;
        }
        int ori = exif.getAttributeInt("Orientation", 0);
        switch (ori) {
            case 3:
                return 180;
            case 6:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }
}
