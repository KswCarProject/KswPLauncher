package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;

/* loaded from: classes10.dex */
public class LexusSetToAndSysLayout extends RelativeLayout {
    public LexusSetToAndSysLayout(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(C0899R.C0902layout.lexus_layout_set_none, (ViewGroup) null);
        TextView tv = (TextView) view.findViewById(C0899R.C0901id.tv_oneDefaul);
        tv.setOnClickListener(new View.OnClickListener() { // from class: com.wits.ksw.settings.lexus.layout_one.LexusSetToAndSysLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Intent intent = new Intent("android.settings.SETTINGS");
                context.startActivity(intent);
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        view.setLayoutParams(layoutParams);
        addView(view);
    }
}
