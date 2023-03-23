package com.txznet.weatherquery;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.txznet.weather.IQueryService;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, d2 = {"com/txznet/weatherquery/WeatherQueryManager$sendWeatherRequest$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "WeatherQuery_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WeatherQueryManager.kt */
public final class WeatherQueryManager$sendWeatherRequest$1 implements ServiceConnection {
    final /* synthetic */ WeatherQueryManager this$0;

    WeatherQueryManager$sendWeatherRequest$1(WeatherQueryManager $receiver) {
        this.this$0 = $receiver;
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("QueryCommunication", "onServiceConnected: into ");
        byte[] result = IQueryService.Stub.asInterface(service).sendInvoke((String) null, this.this$0.CMD_SYNC_WEATHER_DATA, (byte[]) null);
        if (result != null) {
            this.this$0.processResult(result);
        }
    }

    public void onServiceDisconnected(ComponentName name) {
        Log.d("WeatherQueryManager", "onServiceDisconnected: ");
    }
}
