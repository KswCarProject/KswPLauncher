package com.txznet.weatherquery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.txznet.weatherquery.WeatherQueryManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/txznet/weatherquery/WeatherQueryManager$changeReceiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "WeatherQuery_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WeatherQueryManager.kt */
public final class WeatherQueryManager$changeReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ WeatherQueryManager this$0;

    WeatherQueryManager$changeReceiver$1(WeatherQueryManager $receiver) {
        this.this$0 = $receiver;
    }

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
            WeatherQueryManager.UserSettingListener mUserSettingListener = this.this$0.getMUserSettingListener();
            if (mUserSettingListener != null) {
                mUserSettingListener.noticeChange();
            }
        }
    }
}
