package skin.support.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface SkinLayoutInflater {
    View createView(Context context, String str, AttributeSet attributeSet);
}
