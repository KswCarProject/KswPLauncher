package com.wits.ksw.settings.audi_mib3.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.wits.ksw.R;
import com.wits.ksw.settings.audi_mib3.widget.AudiMib3DateView;
import java.util.Date;
import java.util.Locale;

public class AudiMib3DateView extends AppCompatTextView {
    private static final String TAG = "DateView";
    private final Date mCurrentTime = new Date();
    /* access modifiers changed from: private */
    public DateFormat mDateFormat;
    private String mDatePattern;
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.TIME_TICK".equals(action) || "android.intent.action.TIME_SET".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action) || "android.intent.action.LOCALE_CHANGED".equals(action)) {
                if ("android.intent.action.LOCALE_CHANGED".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                    AudiMib3DateView.this.getHandler().post(new Runnable() {
                        public final void run() {
                            AudiMib3DateView.AnonymousClass1.this.lambda$onReceive$0$AudiMib3DateView$1();
                        }
                    });
                }
                AudiMib3DateView.this.getHandler().post(new Runnable() {
                    public final void run() {
                        AudiMib3DateView.AnonymousClass1.this.lambda$onReceive$1$AudiMib3DateView$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onReceive$0$AudiMib3DateView$1() {
            DateFormat unused = AudiMib3DateView.this.mDateFormat = null;
        }

        public /* synthetic */ void lambda$onReceive$1$AudiMib3DateView$1() {
            AudiMib3DateView.this.updateClock();
        }
    };
    private String mLastText;

    /* JADX INFO: finally extract failed */
    public AudiMib3DateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DateView, 0, 0);
        try {
            this.mDatePattern = a.getString(0);
            a.recycle();
            if (this.mDatePattern == null) {
                this.mDatePattern = "YYYYMMMMd";
            }
        } catch (Throwable th) {
            a.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIME_TICK");
        filter.addAction("android.intent.action.TIME_SET");
        filter.addAction("android.intent.action.TIMEZONE_CHANGED");
        filter.addAction("android.intent.action.LOCALE_CHANGED");
        getContext().registerReceiver(this.mIntentReceiver, filter);
        updateClock();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDateFormat = null;
        getContext().unregisterReceiver(this.mIntentReceiver);
    }

    /* access modifiers changed from: protected */
    public void updateClock() {
        if (this.mDateFormat == null) {
            DateFormat format = DateFormat.getInstanceForSkeleton(this.mDatePattern, Locale.getDefault());
            format.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
            this.mDateFormat = format;
        }
        this.mCurrentTime.setTime(System.currentTimeMillis());
        String text = this.mDateFormat.format(this.mCurrentTime);
        if (!text.equals(this.mLastText)) {
            setText(text);
            this.mLastText = text;
        }
    }

    public void setDatePattern(String pattern) {
        if (!TextUtils.equals(pattern, this.mDatePattern)) {
            this.mDatePattern = pattern;
            this.mDateFormat = null;
            if (isAttachedToWindow()) {
                updateClock();
            }
        }
    }
}
