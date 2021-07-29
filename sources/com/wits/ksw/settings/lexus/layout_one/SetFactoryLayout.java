package com.wits.ksw.settings.lexus.layout_one;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wits.ksw.R;

public class SetFactoryLayout extends RelativeLayout implements View.OnClickListener {
    private Context context;
    private Handler handler;
    private ImageView img_btn0;
    private ImageView img_btn1;
    private ImageView img_btn2;
    private ImageView img_btn3;
    private ImageView img_btn4;
    private ImageView img_btn5;
    private ImageView img_btn6;
    private ImageView img_btn7;
    private ImageView img_btn8;
    private ImageView img_btn9;
    private ImageView img_del;
    private ImageView img_enter;
    /* access modifiers changed from: private */
    public StringBuffer pwdInput = new StringBuffer();
    /* access modifiers changed from: private */
    public TextView tv_showPwd;

    public SetFactoryLayout(Context context2, Handler handler2) {
        super(context2);
        this.context = context2;
        this.handler = handler2;
        View view = LayoutInflater.from(context2).inflate(R.layout.lexus_layout_set_factory, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        initData();
        initView(view);
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    private void initData() {
    }

    private void initView(View view) {
        this.tv_showPwd = (TextView) view.findViewById(R.id.tv_showPwd);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_btn1);
        this.img_btn1 = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.img_btn2);
        this.img_btn2 = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.img_btn3);
        this.img_btn3 = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) view.findViewById(R.id.img_btn4);
        this.img_btn4 = imageView4;
        imageView4.setOnClickListener(this);
        ImageView imageView5 = (ImageView) view.findViewById(R.id.img_btn5);
        this.img_btn5 = imageView5;
        imageView5.setOnClickListener(this);
        ImageView imageView6 = (ImageView) view.findViewById(R.id.img_btn6);
        this.img_btn6 = imageView6;
        imageView6.setOnClickListener(this);
        ImageView imageView7 = (ImageView) view.findViewById(R.id.img_btn7);
        this.img_btn7 = imageView7;
        imageView7.setOnClickListener(this);
        ImageView imageView8 = (ImageView) view.findViewById(R.id.img_btn8);
        this.img_btn8 = imageView8;
        imageView8.setOnClickListener(this);
        ImageView imageView9 = (ImageView) view.findViewById(R.id.img_btn9);
        this.img_btn9 = imageView9;
        imageView9.setOnClickListener(this);
        ImageView imageView10 = (ImageView) view.findViewById(R.id.img_btn0);
        this.img_btn0 = imageView10;
        imageView10.setOnClickListener(this);
        ImageView imageView11 = (ImageView) view.findViewById(R.id.img_enter);
        this.img_enter = imageView11;
        imageView11.setOnClickListener(this);
        ImageView imageView12 = (ImageView) view.findViewById(R.id.img_del);
        this.img_del = imageView12;
        imageView12.setOnClickListener(this);
        this.img_del.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (SetFactoryLayout.this.pwdInput.length() <= 0) {
                    return true;
                }
                SetFactoryLayout.this.pwdInput.delete(0, SetFactoryLayout.this.pwdInput.length());
                SetFactoryLayout.this.tv_showPwd.setText(SetFactoryLayout.this.pwdInput.toString());
                return true;
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn0:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("0");
                    break;
                }
                break;
            case R.id.img_btn1:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("1");
                    break;
                }
                break;
            case R.id.img_btn2:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("2");
                    break;
                }
                break;
            case R.id.img_btn3:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("3");
                    break;
                }
                break;
            case R.id.img_btn4:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("4");
                    break;
                }
                break;
            case R.id.img_btn5:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("5");
                    break;
                }
                break;
            case R.id.img_btn6:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("6");
                    break;
                }
                break;
            case R.id.img_btn7:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("7");
                    break;
                }
                break;
            case R.id.img_btn8:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("8");
                    break;
                }
                break;
            case R.id.img_btn9:
                if (this.pwdInput.length() <= 8) {
                    this.pwdInput.append("9");
                    break;
                }
                break;
            case R.id.img_del:
                if (this.pwdInput.length() > 0) {
                    StringBuffer stringBuffer = this.pwdInput;
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    break;
                }
                break;
            case R.id.img_enter:
                Message message = new Message();
                message.obj = this.pwdInput.toString();
                message.what = 2;
                if (this.pwdInput.length() > 0) {
                    StringBuffer stringBuffer2 = this.pwdInput;
                    stringBuffer2.delete(0, stringBuffer2.length());
                    this.tv_showPwd.setText(this.pwdInput.toString());
                }
                this.handler.sendMessage(message);
                break;
        }
        this.tv_showPwd.setText(this.pwdInput.toString());
    }

    public void SetTextEEro() {
        this.tv_showPwd.setText(this.context.getResources().getString(R.string.lb_password_error));
    }
}
