package android.support.p004v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.p001v4.content.PermissionChecker;
import android.util.Log;
import java.util.Calendar;

/* renamed from: android.support.v7.app.TwilightManager */
/* loaded from: classes.dex */
class TwilightManager {
    private static final int SUNRISE = 6;
    private static final int SUNSET = 22;
    private static final String TAG = "TwilightManager";
    private static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState = new TwilightState();

    static TwilightManager getInstance(Context context) {
        if (sInstance == null) {
            Context context2 = context.getApplicationContext();
            sInstance = new TwilightManager(context2, (LocationManager) context2.getSystemService("location"));
        }
        return sInstance;
    }

    static void setInstance(TwilightManager twilightManager) {
        sInstance = twilightManager;
    }

    TwilightManager(Context context, LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    boolean isNight() {
        TwilightState state = this.mTwilightState;
        if (isStateValid()) {
            return state.isNight;
        }
        Location location = getLastKnownLocation();
        if (location != null) {
            updateState(location);
            return state.isNight;
        }
        Log.i(TAG, "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(11);
        return hour < 6 || hour >= 22;
    }

    private Location getLastKnownLocation() {
        Location coarseLoc = null;
        Location fineLoc = null;
        int permission = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION");
        if (permission == 0) {
            coarseLoc = getLastKnownLocationForProvider("network");
        }
        int permission2 = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION");
        if (permission2 == 0) {
            fineLoc = getLastKnownLocationForProvider("gps");
        }
        return (fineLoc == null || coarseLoc == null) ? fineLoc != null ? fineLoc : coarseLoc : fineLoc.getTime() > coarseLoc.getTime() ? fineLoc : coarseLoc;
    }

    private Location getLastKnownLocationForProvider(String provider) {
        try {
            if (this.mLocationManager.isProviderEnabled(provider)) {
                return this.mLocationManager.getLastKnownLocation(provider);
            }
            return null;
        } catch (Exception e) {
            Log.d(TAG, "Failed to get last known location", e);
            return null;
        }
    }

    private boolean isStateValid() {
        return this.mTwilightState.nextUpdate > System.currentTimeMillis();
    }

    private void updateState(Location location) {
        long nextUpdate;
        long nextUpdate2;
        TwilightState state = this.mTwilightState;
        long now = System.currentTimeMillis();
        TwilightCalculator calculator = TwilightCalculator.getInstance();
        calculator.calculateTwilight(now - 86400000, location.getLatitude(), location.getLongitude());
        long yesterdaySunset = calculator.sunset;
        calculator.calculateTwilight(now, location.getLatitude(), location.getLongitude());
        boolean isNight = calculator.state == 1;
        long todaySunrise = calculator.sunrise;
        long todaySunset = calculator.sunset;
        calculator.calculateTwilight(86400000 + now, location.getLatitude(), location.getLongitude());
        long tomorrowSunrise = calculator.sunrise;
        if (todaySunrise == -1 || todaySunset == -1) {
            nextUpdate = now + 43200000;
        } else {
            if (now > todaySunset) {
                nextUpdate2 = 0 + tomorrowSunrise;
            } else if (now > todaySunrise) {
                nextUpdate2 = 0 + todaySunset;
            } else {
                nextUpdate2 = 0 + todaySunrise;
            }
            nextUpdate = nextUpdate2 + 60000;
        }
        state.isNight = isNight;
        state.yesterdaySunset = yesterdaySunset;
        state.todaySunrise = todaySunrise;
        state.todaySunset = todaySunset;
        state.tomorrowSunrise = tomorrowSunrise;
        state.nextUpdate = nextUpdate;
    }

    /* renamed from: android.support.v7.app.TwilightManager$TwilightState */
    /* loaded from: classes.dex */
    private static class TwilightState {
        boolean isNight;
        long nextUpdate;
        long todaySunrise;
        long todaySunset;
        long tomorrowSunrise;
        long yesterdaySunset;

        TwilightState() {
        }
    }
}
