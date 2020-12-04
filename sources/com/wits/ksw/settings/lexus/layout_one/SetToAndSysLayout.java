package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;

public class SetToAndSysLayout extends RelativeLayout {
    public SetToAndSysLayout(final Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.lexus_layout_set_none, (ViewGroup) null);
        ((TextView) view.findViewById(R.id.tv_oneDefaul)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                context.startActivity(new Intent("android.settings.SETTINGS"));
            }
        });
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(view);
    }
}
