package skin.support.load;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import skin.support.utils.SkinConstants;
import skin.support.utils.SkinFileUtils;

/* loaded from: classes.dex */
public class SkinAssetsLoader extends SkinSDCardLoader {
    @Override // skin.support.load.SkinSDCardLoader
    protected String getSkinPath(Context context, String skinName) {
        return copySkinFromAssets(context, skinName);
    }

    @Override // skin.support.load.SkinSDCardLoader, skin.support.SkinCompatManager.SkinLoaderStrategy
    public String getTargetResourceEntryName(Context context, String skinName, int resId) {
        return null;
    }

    @Override // skin.support.SkinCompatManager.SkinLoaderStrategy
    public int getType() {
        return 0;
    }

    private String copySkinFromAssets(Context context, String name) {
        String skinPath = new File(SkinFileUtils.getSkinDir(context), name).getAbsolutePath();
        try {
            InputStream is = context.getAssets().open(SkinConstants.SKIN_DEPLOY_PATH + File.separator + name);
            OutputStream os = new FileOutputStream(skinPath);
            byte[] bytes = new byte[1024];
            while (true) {
                int byteCount = is.read(bytes);
                if (byteCount == -1) {
                    break;
                }
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinPath;
    }
}
