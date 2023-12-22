package com.wits.ksw.launcher.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.support.p004v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.wits.ksw.C0899R;
import com.wits.ksw.launcher.view.DateView;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes16.dex */
public class DateView extends AppCompatTextView {
    private static final String TAG = "DateView";
    private final Date mCurrentTime;
    private DateFormat mDateFormat;
    private String mDatePattern;
    private BroadcastReceiver mIntentReceiver;
    private String mLastText;

    /* renamed from: com.wits.ksw.launcher.view.DateView$1 */
    /* loaded from: classes16.dex */
    class C10801 extends BroadcastReceiver {
        C10801() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.TIME_TICK".equals(action) || "android.intent.action.TIME_SET".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action) || "android.intent.action.LOCALE_CHANGED".equals(action)) {
                if ("android.intent.action.LOCALE_CHANGED".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                    DateView.this.getHandler().post(new Runnable() { // from class: com.wits.ksw.launcher.view.-$$Lambda$DateView$1$vol8MlSc9Y-2zxoEpMFaLib2nfM
                        @Override // java.lang.Runnable
                        public final void run() {
                            DateView.C10801.this.lambda$onReceive$0$DateView$1();
                        }
                    });
                }
                DateView.this.getHandler().post(new Runnable() { // from class: com.wits.ksw.launcher.view.-$$Lambda$DateView$1$w1oIB6NKo4zPPvLbHE3RjSNhGm8
                    @Override // java.lang.Runnable
                    public final void run() {
                        DateView.C10801.this.lambda$onReceive$1$DateView$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onReceive$0$DateView$1() {
            DateView.this.mDateFormat = null;
        }

        public /* synthetic */ void lambda$onReceive$1$DateView$1() {
            DateView.this.updateClock();
        }
    }

    public DateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCurrentTime = new Date();
        this.mIntentReceiver = new C10801();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C0899R.styleable.DateView, 0, 0);
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

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.TIME_TICK");
        filter.addAction("android.intent.action.TIME_SET");
        filter.addAction("android.intent.action.TIMEZONE_CHANGED");
        filter.addAction("android.intent.action.LOCALE_CHANGED");
        getContext().registerReceiver(this.mIntentReceiver, filter);
        updateClock();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDateFormat = null;
        getContext().unregisterReceiver(this.mIntentReceiver);
    }

    protected void updateClock() {
        if (this.mDateFormat == null) {
            Locale l = Locale.getDefault();
            DateFormat format = DateFormat.getInstanceForSkeleton(this.mDatePattern, l);
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
        if (TextUtils.equals(pattern, this.mDatePattern)) {
            return;
        }
        this.mDatePattern = pattern;
        this.mDateFormat = null;
        if (isAttachedToWindow()) {
            updateClock();
        }
    }
}
