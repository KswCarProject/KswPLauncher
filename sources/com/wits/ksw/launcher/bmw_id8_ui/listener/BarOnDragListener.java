package com.wits.ksw.launcher.bmw_id8_ui.listener;

import android.content.Context;
import android.view.View;

public class BarOnDragListener implements View.OnDragListener {
    private static final String TAG = "CardOnDragListener";
    private final Context context;

    public BarOnDragListener(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: type inference failed for: r11v3, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r11v5, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r11v7, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r11v9, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r11v11, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r11v13, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onDrag(android.view.View r13, android.view.DragEvent r14) {
        /*
            r12 = this;
            int r0 = r14.getAction()
            r1 = 1
            switch(r0) {
                case 3: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x00c7
        L_0x000a:
            java.lang.String r0 = "CardOnDragListener"
            java.lang.String r2 = "onDrag: 导航栏的"
            android.util.Log.w(r0, r2)
            int r2 = r13.getId()
            r3 = 2131297270(0x7f0903f6, float:1.821248E38)
            r4 = 0
            if (r2 != r3) goto L_0x0027
            android.content.Context r0 = r12.context
            java.lang.String r3 = "这个不允许更换"
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r3, r4)
            r0.show()
            return r1
        L_0x0027:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "onDrag id : "
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.StringBuilder r3 = r3.append(r2)
            java.lang.String r3 = r3.toString()
            android.util.Log.w(r0, r3)
            android.content.ClipData r0 = r14.getClipData()
            android.content.ClipData$Item r3 = r0.getItemAt(r4)
            android.content.Intent r3 = r3.getIntent()
            java.lang.String r4 = "name"
            java.lang.String r4 = r3.getStringExtra(r4)
            java.lang.String r5 = "nameRes"
            r6 = -1
            int r5 = r3.getIntExtra(r5, r6)
            if (r5 != r6) goto L_0x0059
            return r1
        L_0x0059:
            java.lang.String r7 = "iconRes"
            int r7 = r3.getIntExtra(r7, r6)
            if (r7 != r6) goto L_0x0062
            return r1
        L_0x0062:
            boolean r6 = com.wits.ksw.launcher.bmw_id8_ui.ID8LauncherConstants.changeLeftIcon(r2, r4)
            if (r6 != 0) goto L_0x0069
            return r1
        L_0x0069:
            android.content.Context r8 = r12.context
            android.content.res.Resources r8 = r8.getResources()
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeResource(r8, r7)
            r9 = 0
            r10 = 0
            switch(r2) {
                case 2131297271: goto L_0x00a3;
                case 2131297272: goto L_0x008e;
                case 2131297273: goto L_0x0079;
                default: goto L_0x0078;
            }
        L_0x0078:
            goto L_0x00b8
        L_0x0079:
            r11 = 2131297107(0x7f090353, float:1.821215E38)
            android.view.View r11 = r13.findViewById(r11)
            r9 = r11
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r11 = 2131297885(0x7f09065d, float:1.8213728E38)
            android.view.View r11 = r13.findViewById(r11)
            r10 = r11
            android.widget.TextView r10 = (android.widget.TextView) r10
            goto L_0x00b8
        L_0x008e:
            r11 = 2131297106(0x7f090352, float:1.8212148E38)
            android.view.View r11 = r13.findViewById(r11)
            r9 = r11
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r11 = 2131297884(0x7f09065c, float:1.8213726E38)
            android.view.View r11 = r13.findViewById(r11)
            r10 = r11
            android.widget.TextView r10 = (android.widget.TextView) r10
            goto L_0x00b8
        L_0x00a3:
            r11 = 2131297105(0x7f090351, float:1.8212146E38)
            android.view.View r11 = r13.findViewById(r11)
            r9 = r11
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            r11 = 2131297883(0x7f09065b, float:1.8213723E38)
            android.view.View r11 = r13.findViewById(r11)
            r10 = r11
            android.widget.TextView r10 = (android.widget.TextView) r10
        L_0x00b8:
            if (r9 != 0) goto L_0x00bb
            return r1
        L_0x00bb:
            r9.setImageBitmap(r8)
            android.content.Context r11 = r12.context
            java.lang.String r11 = r11.getString(r5)
            r10.setText(r11)
        L_0x00c7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bmw_id8_ui.listener.BarOnDragListener.onDrag(android.view.View, android.view.DragEvent):boolean");
    }
}
