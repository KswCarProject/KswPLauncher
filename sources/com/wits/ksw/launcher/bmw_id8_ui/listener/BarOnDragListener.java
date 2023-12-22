package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants;

/* loaded from: classes10.dex */
public class BarOnDragListener implements View.OnDragListener {
    private static final String TAG = "CardOnDragListener";
    private final Context context;

    public BarOnDragListener(Context context) {
        this.context = context;
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View v, DragEvent event) {
        int iconRes;
        switch (event.getAction()) {
            case 3:
                Log.w(TAG, "onDrag: \u5bfc\u822a\u680f\u7684");
                int id = v.getId();
                if (id != C0899R.C0901id.ll_left_1) {
                    Log.w(TAG, "onDrag id : " + id);
                    ClipData data = event.getClipData();
                    Intent intent = data.getItemAt(0).getIntent();
                    String name = intent.getStringExtra("name");
                    int nameRes = intent.getIntExtra("nameRes", -1);
                    if (nameRes != -1 && (iconRes = intent.getIntExtra("iconRes", -1)) != -1) {
                        boolean changeValid = ID8LauncherConstants.changeLeftIcon(id, name);
                        if (changeValid) {
                            Bitmap bitmap = BitmapFactory.decodeResource(this.context.getResources(), iconRes);
                            ImageView imageView = null;
                            TextView textView = null;
                            switch (id) {
                                case C0899R.C0901id.ll_left_2 /* 2131297300 */:
                                    imageView = (ImageView) v.findViewById(C0899R.C0901id.iv_left_2);
                                    textView = (TextView) v.findViewById(C0899R.C0901id.tv_left_2);
                                    break;
                                case C0899R.C0901id.ll_left_3 /* 2131297301 */:
                                    imageView = (ImageView) v.findViewById(C0899R.C0901id.iv_left_3);
                                    textView = (TextView) v.findViewById(C0899R.C0901id.tv_left_3);
                                    break;
                                case C0899R.C0901id.ll_left_4 /* 2131297302 */:
                                    imageView = (ImageView) v.findViewById(C0899R.C0901id.iv_left_4);
                                    textView = (TextView) v.findViewById(C0899R.C0901id.tv_left_4);
                                    break;
                            }
                            if (imageView != null) {
                                imageView.setImageBitmap(bitmap);
                                textView.setText(this.context.getString(nameRes));
                                break;
                            } else {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                } else {
                    Toast.makeText(this.context, "\u8fd9\u4e2a\u4e0d\u5141\u8bb8\u66f4\u6362", 0).show();
                    return true;
                }
                break;
        }
        return true;
    }
}
