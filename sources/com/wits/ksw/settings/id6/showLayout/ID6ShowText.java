package com.wits.ksw.settings.id6.showLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.C0899R;

/* loaded from: classes13.dex */
public class ID6ShowText extends RelativeLayout {
    private Context context;
    private TextView tv_id6ShowMsg;
    private TextView tv_id6ShowTitle;
    private int type;
    private View view;

    public ID6ShowText(Context context, int type) {
        super(context);
        this.context = context;
        this.type = type;
        this.view = LayoutInflater.from(context).inflate(C0899R.C0902layout.layout_id6_show_text, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initView(this.view);
        this.view.setLayoutParams(layoutParams);
        addView(this.view);
    }

    private void initView(View view) {
        this.tv_id6ShowTitle = (TextView) view.findViewById(C0899R.C0901id.tv_id6ShowTitle);
        TextView textView = (TextView) view.findViewById(C0899R.C0901id.tv_id6ShowMsg);
        this.tv_id6ShowMsg = textView;
        if (this.type == 0) {
            textView.setText(this.context.getString(C0899R.string.show_msg_one));
            this.tv_id6ShowTitle.setText(this.context.getString(C0899R.string.item8));
            return;
        }
        textView.setText(this.context.getString(C0899R.string.show_msg_two));
        this.tv_id6ShowTitle.setText(this.context.getString(C0899R.string.item9));
    }
}
