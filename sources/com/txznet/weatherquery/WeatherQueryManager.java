package com.txznet.weatherquery;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.txznet.weather.IQueryService;
import com.txznet.weatherquery.WeatherQueryManager;
import com.wits.ksw.launcher.view.benzmbux2021new.util.BenzUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: WeatherQueryManager.kt */
@Metadata(m25d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eJ\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eH\u0002J\u0012\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\u0016\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, m24d2 = {"Lcom/txznet/weatherquery/WeatherQueryManager;", "", "()V", "CMD_SYNC_WEATHER_DATA", "", "changeReceiver", "Landroid/content/BroadcastReceiver;", "initFlag", "", "getInitFlag", "()Z", "setInitFlag", "(Z)V", "mContext", "Landroid/content/Context;", "mUserSettingListener", "Lcom/txznet/weatherquery/WeatherQueryManager$UserSettingListener;", "getMUserSettingListener", "()Lcom/txznet/weatherquery/WeatherQueryManager$UserSettingListener;", "setMUserSettingListener", "(Lcom/txznet/weatherquery/WeatherQueryManager$UserSettingListener;)V", "serviceClsName", "weatherPkgName", "doOnDestroy", "", "context", "initParam", "processResult", "result", "", "sendWeatherRequest", "callback", "Lcom/txznet/weatherquery/WeatherQueryManager$WeatherCallback;", "setUserSettingListener", "listener", "Companion", "UserSettingListener", "WeatherCallback", "WeatherQuery_release"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
public final class WeatherQueryManager {
    public static final Companion Companion = new Companion(null);
    private static WeatherCallback mCallback;
    private static WeatherQueryManager mInstance;
    private final String CMD_SYNC_WEATHER_DATA;
    private final BroadcastReceiver changeReceiver;
    private boolean initFlag;
    private Context mContext;
    private UserSettingListener mUserSettingListener;
    private final String serviceClsName;
    private final String weatherPkgName;

    /* compiled from: WeatherQueryManager.kt */
    @Metadata(m25d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, m24d2 = {"Lcom/txznet/weatherquery/WeatherQueryManager$UserSettingListener;", "", "noticeChange", "", "WeatherQuery_release"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
    /* loaded from: classes.dex */
    public interface UserSettingListener {
        void noticeChange();
    }

    /* compiled from: WeatherQueryManager.kt */
    @Metadata(m25d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&\u00a8\u0006\u000b"}, m24d2 = {"Lcom/txznet/weatherquery/WeatherQueryManager$WeatherCallback;", "", "onFailed", "", "errorCode", "", "onSuccess", "result", "Lcom/txznet/weatherquery/TXZWeather;", "texts", "Landroid/os/Bundle;", "WeatherQuery_release"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
    /* loaded from: classes.dex */
    public interface WeatherCallback {
        void onFailed(int i);

        void onSuccess(TXZWeather tXZWeather, Bundle bundle);
    }

    public /* synthetic */ WeatherQueryManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final WeatherQueryManager getInstance() {
        return Companion.getInstance();
    }

    /* compiled from: WeatherQueryManager.kt */
    @Metadata(m25d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004\u00a2\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, m24d2 = {"Lcom/txznet/weatherquery/WeatherQueryManager$Companion;", "", "()V", "instance", "Lcom/txznet/weatherquery/WeatherQueryManager;", "getInstance$annotations", "getInstance", "()Lcom/txznet/weatherquery/WeatherQueryManager;", "<set-?>", "Lcom/txznet/weatherquery/WeatherQueryManager$WeatherCallback;", "mCallback", "getMCallback", "()Lcom/txznet/weatherquery/WeatherQueryManager$WeatherCallback;", "mInstance", "WeatherQuery_release"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final WeatherQueryManager getInstance() {
            if (WeatherQueryManager.mInstance == null) {
                synchronized (WeatherQueryManager.class) {
                    if (WeatherQueryManager.mInstance == null) {
                        Companion companion = WeatherQueryManager.Companion;
                        WeatherQueryManager.mInstance = new WeatherQueryManager(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            WeatherQueryManager weatherQueryManager = WeatherQueryManager.mInstance;
            Intrinsics.checkNotNull(weatherQueryManager);
            return weatherQueryManager;
        }

        public final WeatherCallback getMCallback() {
            WeatherCallback weatherCallback = WeatherQueryManager.mCallback;
            if (weatherCallback != null) {
                return weatherCallback;
            }
            Intrinsics.throwUninitializedPropertyAccessException("mCallback");
            return null;
        }
    }

    private WeatherQueryManager() {
        this.weatherPkgName = BenzUtils.WEATHER_PKG;
        this.serviceClsName = BenzUtils.WEATHER_PKG + ".service.QueryService";
        this.CMD_SYNC_WEATHER_DATA = "sycWeatherFromApp";
        this.changeReceiver = new BroadcastReceiver() { // from class: com.txznet.weatherquery.WeatherQueryManager$changeReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                String action = intent.getAction();
                boolean z = true;
                if (action == null || !action.equals("com.txznet.weather.notify.change")) {
                    z = false;
                }
                if (z) {
                    Log.d("QueryCommunication", "sdk get weather refresh notice from weatherApp");
                    WeatherQueryManager.UserSettingListener mUserSettingListener = WeatherQueryManager.this.getMUserSettingListener();
                    if (mUserSettingListener != null) {
                        mUserSettingListener.noticeChange();
                    }
                }
            }
        };
    }

    public final void sendWeatherRequest(Context context, WeatherCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Log.d("QueryCommunication(0)", "sdk sendWeatherRequest to weatherApp");
        initParam(context);
        mCallback = callback;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.weatherPkgName, this.serviceClsName));
        context.startService(intent);
        context.bindService(intent, new ServiceConnection() { // from class: com.txznet.weatherquery.WeatherQueryManager$sendWeatherRequest$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                String str;
                Log.d("QueryCommunication", "onServiceConnected: into ");
                IQueryService myInterface = IQueryService.Stub.asInterface(service);
                str = WeatherQueryManager.this.CMD_SYNC_WEATHER_DATA;
                byte[] result = myInterface.sendInvoke(null, str, null);
                if (result != null) {
                    WeatherQueryManager.this.processResult(result);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Log.d("WeatherQueryManager", "onServiceDisconnected: ");
            }
        }, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processResult(byte[] result) {
        Log.d("QueryCommunication(4)", "processResult: sdk get the response from txz");
        if (result != null) {
            Log.d("QueryCommunication", "processResult: result != null 2");
            String strResult = new String(result, Charsets.UTF_8);
            Log.d("QueryCommunication", "processResult: strResult:" + strResult);
            WeatherResult finalResult = (WeatherResult) GsonUtil.fromJson(strResult, (Class<Object>) WeatherResult.class);
            if (finalResult.weather != null) {
                Log.d("QueryCommunication(4)", "processResult: source string: " + finalResult.weather);
                TXZWeather weather = (TXZWeather) GsonUtil.fromJson(finalResult.weather, (Class<Object>) TXZWeather.class);
                String[] details = finalResult.details;
                Log.d("QueryCommunication(4)", "processResult: weather:" + weather);
                if (details == null) {
                    WeatherCallback mCallback2 = Companion.getMCallback();
                    Intrinsics.checkNotNullExpressionValue(weather, "weather");
                    mCallback2.onSuccess(weather, null);
                    return;
                }
                Bundle weatherDetail = new Bundle();
                weatherDetail.putStringArray("details", details);
                WeatherCallback mCallback3 = Companion.getMCallback();
                Intrinsics.checkNotNullExpressionValue(weather, "weather");
                mCallback3.onSuccess(weather, weatherDetail);
                return;
            }
            Companion.getMCallback().onFailed(finalResult.resultCode);
        }
    }

    public final boolean getInitFlag() {
        return this.initFlag;
    }

    public final void setInitFlag(boolean z) {
        this.initFlag = z;
    }

    private final void initParam(Context context) {
        if (!this.initFlag) {
            this.mContext = context;
            this.initFlag = true;
        }
    }

    public final UserSettingListener getMUserSettingListener() {
        return this.mUserSettingListener;
    }

    public final void setMUserSettingListener(UserSettingListener userSettingListener) {
        this.mUserSettingListener = userSettingListener;
    }

    public final void setUserSettingListener(Context context, UserSettingListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        IntentFilter weatherFilter = new IntentFilter();
        weatherFilter.addAction("com.txznet.weather.notify.change");
        context.registerReceiver(this.changeReceiver, weatherFilter);
        this.mUserSettingListener = listener;
    }

    public final void doOnDestroy(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            context.unregisterReceiver(this.changeReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
