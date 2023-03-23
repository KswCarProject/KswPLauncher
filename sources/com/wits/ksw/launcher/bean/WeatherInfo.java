package com.wits.ksw.launcher.bean;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import com.txznet.weatherquery.HourWeather;
import com.txznet.weatherquery.TXZWeather;
import com.wits.ksw.KswApplication;
import com.wits.ksw.R;
import java.util.ArrayList;

public class WeatherInfo {
    private static final int DETAIL_TYPE_HUMIDITY = 1;
    private static final int DETAIL_TYPE_RAIN_OR_SNOW = 3;
    private static final int DETAIL_TYPE_UV = 4;
    private static final int DETAIL_TYPE_VISIBILITY = 2;
    private static final int DETAIL_TYPE_WIND = 0;
    private static final int ERROR_NO_GPS = 5;
    private static final int ERROR_QUERY_FAILED = 3;
    public static final int ERROR_TIME_INTERNAL_NOT_ENOUGH = 6;
    private static final int ERROR_UNACTIVATED = 4;
    private static final String TAG = "WeatherInfo";
    public ObservableField<String> atmosphericPressure = new ObservableField<>();
    public ObservableField<Drawable> audiCardDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> audiCardDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> audiCardDetailImg3 = new ObservableField<>();
    public ObservableField<String> audiCardDetailText1 = new ObservableField<>();
    public ObservableField<String> audiCardDetailText2 = new ObservableField<>();
    public ObservableField<String> audiCardDetailText3 = new ObservableField<>();
    public ObservableField<Drawable> audiImageIcon = new ObservableField<>();
    public ObservableField<String> bottomDisplay1 = new ObservableField<>();
    public ObservableField<String> bottomDisplay2 = new ObservableField<>();
    public ObservableField<String> bottomDisplay3 = new ObservableField<>();
    public ObservableField<String> cardDetailText1 = new ObservableField<>();
    public ObservableField<String> cardDetailText2 = new ObservableField<>();
    public ObservableField<String> cardDetailText3 = new ObservableField<>();
    public ObservableField<String> city = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> dateOfWeek = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableField<String> humidity = new ObservableField<>();
    public ObservableField<Drawable> id7AlsV2ImageIcon = new ObservableField<>();
    public ObservableField<Drawable> id7CardDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> id7CardDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> id7CardDetailImg3 = new ObservableField<>();
    public ObservableField<Drawable> id8CardDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> id8CardDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> id8CardDetailImg3 = new ObservableField<>();
    public ObservableField<Drawable> id8CardEditDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> id8CardEditDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> id8CardEditDetailImg3 = new ObservableField<>();
    public ObservableField<Drawable> id8EditImage = new ObservableField<>();
    public ObservableField<Drawable> id8GsImageBg = new ObservableField<>();
    public ObservableField<Drawable> id8GsImageIcon = new ObservableField<>();
    public ObservableField<Drawable> id8ImageBg = new ObservableField<>();
    public ObservableField<Drawable> id8ImageIcon = new ObservableField<>();
    public ObservableField<Drawable> image = new ObservableField<>();
    public ObservableField<Boolean> isInitFinished = new ObservableField<>();
    public ObservableField<Boolean> isLoadSuccess = new ObservableField<>();
    public ObservableField<String> maxTemperature = new ObservableField<>();
    public ObservableField<String> minTemperature = new ObservableField<>();
    public ObservableField<String> near1HourClock = new ObservableField<>();
    public ObservableField<String> near1HourClockUnit = new ObservableField<>();
    public ObservableField<Drawable> near1HourImg = new ObservableField<>();
    public ObservableField<String> near1HourTemp = new ObservableField<>();
    public ObservableField<String> near2HourClock = new ObservableField<>();
    public ObservableField<String> near2HourClockUnit = new ObservableField<>();
    public ObservableField<Drawable> near2HourImg = new ObservableField<>();
    public ObservableField<String> near2HourTemp = new ObservableField<>();
    public ObservableField<String> near3HourClock = new ObservableField<>();
    public ObservableField<String> near3HourClockUnit = new ObservableField<>();
    public ObservableField<Drawable> near3HourImg = new ObservableField<>();
    public ObservableField<String> near3HourTemp = new ObservableField<>();
    public ObservableField<String> near4HourClock = new ObservableField<>();
    public ObservableField<String> near4HourClockUnit = new ObservableField<>();
    public ObservableField<Drawable> near4HourImg = new ObservableField<>();
    public ObservableField<String> near4HourTemp = new ObservableField<>();
    public ObservableField<String> near5HourClock = new ObservableField<>();
    public ObservableField<String> near5HourClockUnit = new ObservableField<>();
    public ObservableField<Drawable> near5HourImg = new ObservableField<>();
    public ObservableField<String> near5HourTemp = new ObservableField<>();
    public ObservableField<String> phrase = new ObservableField<>();
    public ObservableField<String> rainingProbability = new ObservableField<>();
    public ObservableField<String> sensibleTemperature = new ObservableField<>();
    public ObservableField<String> snowingProbability = new ObservableField<>();
    public ObservableField<String> temperUnit = new ObservableField<>();
    public ObservableField<String> temperature = new ObservableField<>();
    public ObservableField<String> temperatureRange = new ObservableField<>();
    public ObservableField<String> uvIndex = new ObservableField<>();
    public ObservableField<String> visibility = new ObservableField<>();
    public ObservableField<String> windSpeed = new ObservableField<>();

    public void loadSuccess(Context context, TXZWeather txzWeather, String[] details) {
        if (context != null) {
            this.image.set(context.getResources().getDrawable(getResource(txzWeather.getPhraseID())));
            getID8MainEditResourceV2(txzWeather.getPhraseID(), context);
            getID8GsMainEditResourceV2(txzWeather.getPhraseID(), context);
            this.id8EditImage.set(context.getResources().getDrawable(getID8MainEditResource(txzWeather.getPhraseID())));
            this.id7AlsV2ImageIcon.set(context.getResources().getDrawable(getId7AlsV2Resource(txzWeather.getPhraseID())));
            this.audiImageIcon.set(context.getResources().getDrawable(getAudiResource(txzWeather.getPhraseID())));
            parseCardDetail(context, txzWeather, details);
        }
        Log.w(TAG, "phraseID : " + txzWeather.getPhraseID());
        this.city.set(txzWeather.getCity());
        this.date.set(txzWeather.getDate());
        this.dateOfWeek.set(txzWeather.getDateOfWeek());
        this.phrase.set(txzWeather.getPhrase());
        this.temperUnit.set(txzWeather.getTemperUnit());
        this.temperature.set(txzWeather.getTemperature());
        this.minTemperature.set(txzWeather.getMinTemperature());
        this.maxTemperature.set(txzWeather.getMaxTemperature());
        this.sensibleTemperature.set(txzWeather.getSensibleTemperature());
        this.windSpeed.set(txzWeather.getWindSpeed());
        this.humidity.set(txzWeather.getHumidity());
        this.rainingProbability.set(txzWeather.getRainingProbability());
        this.snowingProbability.set(txzWeather.getSnowingProbability());
        this.atmosphericPressure.set(txzWeather.getAtmosphericPressure());
        this.visibility.set(txzWeather.getVisibility());
        this.uvIndex.set(txzWeather.getUvIndex());
        this.temperatureRange.set(txzWeather.getMinTemperature() + "~" + txzWeather.getMaxTemperature() + txzWeather.getTemperUnit());
        this.isInitFinished.set(true);
        this.isLoadSuccess.set(true);
        ArrayList<HourWeather> hoursData = txzWeather.getHoursData();
        if (hoursData.size() >= 5) {
            parseHourData(context, hoursData.get(0), this.near1HourClock, this.near1HourClockUnit, this.near1HourImg, this.near1HourTemp);
            Context context2 = context;
            parseHourData(context2, hoursData.get(1), this.near2HourClock, this.near2HourClockUnit, this.near2HourImg, this.near2HourTemp);
            parseHourData(context2, hoursData.get(2), this.near3HourClock, this.near3HourClockUnit, this.near3HourImg, this.near3HourTemp);
            parseHourData(context2, hoursData.get(3), this.near4HourClock, this.near4HourClockUnit, this.near4HourImg, this.near4HourTemp);
            parseHourData(context2, hoursData.get(4), this.near5HourClock, this.near5HourClockUnit, this.near5HourImg, this.near5HourTemp);
        }
    }

    private void parseCardDetail(Context context, TXZWeather txzWeather, String[] details) {
        String[] strArr = details;
        if (strArr == null) {
            resetCardDetail(this.id8CardDetailImg1, this.id8CardEditDetailImg1, this.id7CardDetailImg1, this.audiCardDetailImg1, this.cardDetailText1, this.audiCardDetailText1);
            resetCardDetail(this.id8CardDetailImg2, this.id8CardEditDetailImg2, this.id7CardDetailImg2, this.audiCardDetailImg2, this.cardDetailText2, this.audiCardDetailText2);
            resetCardDetail(this.id8CardDetailImg3, this.id8CardEditDetailImg3, this.id7CardDetailImg3, this.audiCardDetailImg3, this.cardDetailText3, this.audiCardDetailText3);
            return;
        }
        if (strArr.length < 1 || TextUtils.isEmpty(strArr[0])) {
            resetCardDetail(this.id8CardDetailImg1, this.id8CardEditDetailImg1, this.id7CardDetailImg1, this.audiCardDetailImg1, this.cardDetailText1, this.audiCardDetailText1);
        } else {
            parseCardDetail(context.getResources(), txzWeather, strArr[0], this.id8CardDetailImg1, this.id8CardEditDetailImg1, this.id7CardDetailImg1, this.audiCardDetailImg1, this.cardDetailText1, this.audiCardDetailText1);
        }
        if (strArr.length < 2 || TextUtils.isEmpty(strArr[1])) {
            resetCardDetail(this.id8CardDetailImg2, this.id8CardEditDetailImg2, this.id7CardDetailImg2, this.audiCardDetailImg2, this.cardDetailText2, this.audiCardDetailText2);
        } else {
            parseCardDetail(context.getResources(), txzWeather, strArr[1], this.id8CardDetailImg2, this.id8CardEditDetailImg2, this.id7CardDetailImg2, this.audiCardDetailImg2, this.cardDetailText2, this.audiCardDetailText2);
        }
        if (strArr.length < 3 || TextUtils.isEmpty(strArr[2])) {
            resetCardDetail(this.id8CardDetailImg3, this.id8CardEditDetailImg3, this.id7CardDetailImg3, this.audiCardDetailImg3, this.cardDetailText3, this.audiCardDetailText3);
            return;
        }
        parseCardDetail(context.getResources(), txzWeather, strArr[2], this.id8CardDetailImg3, this.id8CardEditDetailImg3, this.id7CardDetailImg3, this.audiCardDetailImg3, this.cardDetailText3, this.audiCardDetailText3);
    }

    private void resetCardDetail(ObservableField<Drawable> id8CardDetailImg, ObservableField<Drawable> id8CardEditDetailImg, ObservableField<Drawable> id7CardDetailImg, ObservableField<Drawable> audiCardDetailImg, ObservableField<String> cardDetailText, ObservableField<String> audiCardDetailText) {
        id8CardDetailImg.set(null);
        id8CardEditDetailImg.set(null);
        id7CardDetailImg.set(null);
        audiCardDetailImg.set(null);
        cardDetailText.set("");
        audiCardDetailText.set("--");
    }

    private void parseCardDetail(Resources resources, TXZWeather txzWeather, String detail, ObservableField<Drawable> id8CardDetailImg, ObservableField<Drawable> id8CardEditDetailImg, ObservableField<Drawable> id7CardDetailImg, ObservableField<Drawable> audiCardDetailImg, ObservableField<String> cardDetailText, ObservableField<String> audiCardDetailText) {
        Resources resources2 = resources;
        int id8CardDetailImgRes = -1;
        int id8CardEditDetailImgRes = -1;
        int id7CardDetailImgRes = -1;
        int audiCardDetailImgRes = -1;
        String cardDetailString = "";
        Log.w(TAG, "parseCardDetail: detail : " + detail);
        int resultType = Integer.parseInt(detail);
        Log.w(TAG, "parseCardDetail: resultType : " + resultType);
        switch (resultType) {
            case 0:
                id8CardDetailImgRes = R.drawable.id8_main_icon_weather_wind;
                id8CardEditDetailImgRes = R.drawable.id8_main_edit_icon_weather_wind;
                id7CardDetailImgRes = R.drawable.id7_weather_icon_wind;
                audiCardDetailImgRes = R.drawable.audi_right_weather_wind;
                cardDetailString = txzWeather.getWindSpeed();
                break;
            case 1:
                id8CardDetailImgRes = R.drawable.id8_main_icon_weather_humidity;
                id8CardEditDetailImgRes = R.drawable.id8_main_edit_icon_weather_humidity;
                id7CardDetailImgRes = R.drawable.id7_weather_icon_humidity;
                audiCardDetailImgRes = R.drawable.audi_right_weather_humidity;
                cardDetailString = txzWeather.getHumidity();
                break;
            case 2:
                id8CardDetailImgRes = R.drawable.id8_main_icon_weather_visibility;
                id8CardEditDetailImgRes = R.drawable.id8_main_edit_icon_weather_visibility;
                id7CardDetailImgRes = R.drawable.id7_weather_icon_visibility;
                audiCardDetailImgRes = R.drawable.audi_right_weather_visibility;
                cardDetailString = txzWeather.getVisibility();
                break;
            case 3:
                if (TextUtils.isEmpty(txzWeather.getSnowingProbability())) {
                    id8CardDetailImgRes = R.drawable.id8_main_icon_weather_rain;
                    id8CardEditDetailImgRes = R.drawable.id8_main_edit_icon_weather_rain;
                    id7CardDetailImgRes = R.drawable.id7_weather_icon_rain;
                    audiCardDetailImgRes = R.drawable.audi_right_weather_rain;
                    cardDetailString = txzWeather.getRainingProbability();
                    break;
                } else {
                    id8CardDetailImgRes = R.drawable.id8_main_icon_weather_snow;
                    id8CardEditDetailImgRes = R.drawable.id8_main_edit_icon_weather_snow;
                    id7CardDetailImgRes = R.drawable.id7_weather_icon_snow;
                    audiCardDetailImgRes = R.drawable.audi_right_weather_snow;
                    cardDetailString = txzWeather.getSnowingProbability();
                    break;
                }
            case 4:
                id8CardDetailImgRes = R.drawable.id8_main_icon_weather_uv;
                id8CardEditDetailImgRes = R.drawable.id8_main_edit_icon_weather_uv;
                id7CardDetailImgRes = R.drawable.id7_weather_icon_uv;
                audiCardDetailImgRes = R.drawable.audi_right_weather_uv;
                cardDetailString = txzWeather.getUvIndex();
                break;
        }
        id8CardDetailImg.set(resources.getDrawable(id8CardDetailImgRes));
        id8CardEditDetailImg.set(resources.getDrawable(id8CardEditDetailImgRes));
        id7CardDetailImg.set(resources.getDrawable(id7CardDetailImgRes));
        audiCardDetailImg.set(resources.getDrawable(audiCardDetailImgRes));
        cardDetailText.set(cardDetailString);
        audiCardDetailText.set(cardDetailString);
    }

    private void parseHourData(Context context, HourWeather hourWeather, ObservableField<String> hourClock, ObservableField<String> hourClockUnit, ObservableField<Drawable> hourImg, ObservableField<String> hourTemp) {
        int hour;
        String unit;
        Integer integer = Integer.valueOf(hourWeather.getTime().substring(0, 2));
        if (integer.intValue() > 12) {
            unit = "PM";
            hour = integer.intValue() - 12;
        } else {
            unit = "AM";
            hour = integer.intValue();
        }
        hourClock.set(String.valueOf(hour));
        hourClockUnit.set(unit);
        hourImg.set(context.getResources().getDrawable(getResource(hourWeather.getPhraseID())));
        hourTemp.set(hourWeather.getTemperature());
    }

    public void loading() {
        this.isInitFinished.set(false);
        this.isLoadSuccess.set(false);
    }

    public void loadFailed(int errorCode) {
        this.isInitFinished.set(true);
        this.isLoadSuccess.set(false);
        String message = "";
        switch (errorCode) {
            case 3:
                message = KswApplication.appContext.getString(R.string.weather_error_failed);
                break;
            case 4:
                message = KswApplication.appContext.getString(R.string.weather_error_unactivated);
                break;
            case 5:
                message = KswApplication.appContext.getString(R.string.weather_error_no_gps);
                break;
        }
        Log.e(TAG, "loadFailed message : " + message);
        this.errorMessage.set(message);
        this.audiCardDetailText1.set("--");
        this.audiCardDetailText2.set("--");
        this.audiCardDetailText3.set("--");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getResource(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            int r1 = r3.hashCode()
            switch(r1) {
                case -2007006149: goto L_0x013e;
                case -1964169151: goto L_0x0133;
                case -1893213074: goto L_0x0128;
                case -1762304422: goto L_0x011d;
                case -1570070136: goto L_0x0112;
                case -1522097648: goto L_0x0107;
                case -1512778085: goto L_0x00fd;
                case -1461142052: goto L_0x00f3;
                case -1024703373: goto L_0x00e8;
                case -637245050: goto L_0x00dc;
                case -390972850: goto L_0x00d0;
                case -337726012: goto L_0x00c4;
                case -242160521: goto L_0x00b8;
                case -175999878: goto L_0x00ad;
                case 14618050: goto L_0x00a2;
                case 264624306: goto L_0x0096;
                case 365061948: goto L_0x008a;
                case 393896703: goto L_0x007f;
                case 514120659: goto L_0x0074;
                case 517883033: goto L_0x0068;
                case 881565940: goto L_0x005c;
                case 967042239: goto L_0x0051;
                case 1110562654: goto L_0x0046;
                case 1457102115: goto L_0x003a;
                case 1609652513: goto L_0x002e;
                case 1610234053: goto L_0x0022;
                case 1626325763: goto L_0x0016;
                case 1815913657: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0149
        L_0x000a:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 17
            goto L_0x014a
        L_0x0016:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 8
            goto L_0x014a
        L_0x0022:
            java.lang.String r1 = "weather_mai.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 11
            goto L_0x014a
        L_0x002e:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 18
            goto L_0x014a
        L_0x003a:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 10
            goto L_0x014a
        L_0x0046:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x014a
        L_0x0051:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 3
            goto L_0x014a
        L_0x005c:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 26
            goto L_0x014a
        L_0x0068:
            java.lang.String r1 = "weather_zhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 24
            goto L_0x014a
        L_0x0074:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 7
            goto L_0x014a
        L_0x007f:
            java.lang.String r1 = "weather_dayu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 5
            goto L_0x014a
        L_0x008a:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 14
            goto L_0x014a
        L_0x0096:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 25
            goto L_0x014a
        L_0x00a2:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 2
            goto L_0x014a
        L_0x00ad:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 6
            goto L_0x014a
        L_0x00b8:
            java.lang.String r1 = "weather_qing.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 12
            goto L_0x014a
        L_0x00c4:
            java.lang.String r1 = "weather_wu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 16
            goto L_0x014a
        L_0x00d0:
            java.lang.String r1 = "weather_yin.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 20
            goto L_0x014a
        L_0x00dc:
            java.lang.String r1 = "weather_zhongyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 27
            goto L_0x014a
        L_0x00e8:
            java.lang.String r1 = "weather_leizhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 9
            goto L_0x014a
        L_0x00f3:
            java.lang.String r1 = "weather_baoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 1
            goto L_0x014a
        L_0x00fd:
            java.lang.String r1 = "weather_daxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 4
            goto L_0x014a
        L_0x0107:
            java.lang.String r1 = "weather_qing_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 13
            goto L_0x014a
        L_0x0112:
            java.lang.String r1 = "weather_yujiaxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 21
            goto L_0x014a
        L_0x011d:
            java.lang.String r1 = "weather_zhenxue_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 23
            goto L_0x014a
        L_0x0128:
            java.lang.String r1 = "weather_tedabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 15
            goto L_0x014a
        L_0x0133:
            java.lang.String r1 = "weather_zhenxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 22
            goto L_0x014a
        L_0x013e:
            java.lang.String r1 = "weather_yangsha.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 19
            goto L_0x014a
        L_0x0149:
            r1 = -1
        L_0x014a:
            switch(r1) {
                case 0: goto L_0x01c4;
                case 1: goto L_0x01c0;
                case 2: goto L_0x01bc;
                case 3: goto L_0x01b8;
                case 4: goto L_0x01b4;
                case 5: goto L_0x01b0;
                case 6: goto L_0x01ac;
                case 7: goto L_0x01a8;
                case 8: goto L_0x01a4;
                case 9: goto L_0x01a0;
                case 10: goto L_0x019c;
                case 11: goto L_0x0198;
                case 12: goto L_0x0194;
                case 13: goto L_0x0190;
                case 14: goto L_0x018c;
                case 15: goto L_0x0188;
                case 16: goto L_0x0184;
                case 17: goto L_0x0180;
                case 18: goto L_0x017c;
                case 19: goto L_0x0178;
                case 20: goto L_0x0174;
                case 21: goto L_0x0170;
                case 22: goto L_0x016b;
                case 23: goto L_0x0166;
                case 24: goto L_0x0161;
                case 25: goto L_0x015c;
                case 26: goto L_0x0157;
                case 27: goto L_0x0152;
                default: goto L_0x014d;
            }
        L_0x014d:
            r0 = 2131235184(0x7f081170, float:1.8086555E38)
            goto L_0x01c8
        L_0x0152:
            r0 = 2131235200(0x7f081180, float:1.8086587E38)
            goto L_0x01c8
        L_0x0157:
            r0 = 2131235199(0x7f08117f, float:1.8086585E38)
            goto L_0x01c8
        L_0x015c:
            r0 = 2131235198(0x7f08117e, float:1.8086583E38)
            goto L_0x01c8
        L_0x0161:
            r0 = 2131235197(0x7f08117d, float:1.8086581E38)
            goto L_0x01c8
        L_0x0166:
            r0 = 2131235196(0x7f08117c, float:1.808658E38)
            goto L_0x01c8
        L_0x016b:
            r0 = 2131235195(0x7f08117b, float:1.8086577E38)
            goto L_0x01c8
        L_0x0170:
            r0 = 2131235194(0x7f08117a, float:1.8086575E38)
            goto L_0x01c8
        L_0x0174:
            r0 = 2131235193(0x7f081179, float:1.8086573E38)
            goto L_0x01c8
        L_0x0178:
            r0 = 2131235192(0x7f081178, float:1.8086571E38)
            goto L_0x01c8
        L_0x017c:
            r0 = 2131235191(0x7f081177, float:1.808657E38)
            goto L_0x01c8
        L_0x0180:
            r0 = 2131235190(0x7f081176, float:1.8086567E38)
            goto L_0x01c8
        L_0x0184:
            r0 = 2131235189(0x7f081175, float:1.8086565E38)
            goto L_0x01c8
        L_0x0188:
            r0 = 2131235188(0x7f081174, float:1.8086563E38)
            goto L_0x01c8
        L_0x018c:
            r0 = 2131235187(0x7f081173, float:1.808656E38)
            goto L_0x01c8
        L_0x0190:
            r0 = 2131235186(0x7f081172, float:1.8086559E38)
            goto L_0x01c8
        L_0x0194:
            r0 = 2131235185(0x7f081171, float:1.8086557E38)
            goto L_0x01c8
        L_0x0198:
            r0 = 2131235183(0x7f08116f, float:1.8086553E38)
            goto L_0x01c8
        L_0x019c:
            r0 = 2131235182(0x7f08116e, float:1.808655E38)
            goto L_0x01c8
        L_0x01a0:
            r0 = 2131235181(0x7f08116d, float:1.8086549E38)
            goto L_0x01c8
        L_0x01a4:
            r0 = 2131235149(0x7f08114d, float:1.8086484E38)
            goto L_0x01c8
        L_0x01a8:
            r0 = 2131235147(0x7f08114b, float:1.808648E38)
            goto L_0x01c8
        L_0x01ac:
            r0 = 2131235146(0x7f08114a, float:1.8086478E38)
            goto L_0x01c8
        L_0x01b0:
            r0 = 2131235145(0x7f081149, float:1.8086476E38)
            goto L_0x01c8
        L_0x01b4:
            r0 = 2131235144(0x7f081148, float:1.8086474E38)
            goto L_0x01c8
        L_0x01b8:
            r0 = 2131235143(0x7f081147, float:1.8086472E38)
            goto L_0x01c8
        L_0x01bc:
            r0 = 2131235142(0x7f081146, float:1.808647E38)
            goto L_0x01c8
        L_0x01c0:
            r0 = 2131235141(0x7f081145, float:1.8086468E38)
            goto L_0x01c8
        L_0x01c4:
            r0 = 2131235140(0x7f081144, float:1.8086466E38)
        L_0x01c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bean.WeatherInfo.getResource(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getId7AlsV2Resource(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            int r1 = r3.hashCode()
            switch(r1) {
                case -2007006149: goto L_0x013e;
                case -1964169151: goto L_0x0133;
                case -1893213074: goto L_0x0128;
                case -1762304422: goto L_0x011d;
                case -1570070136: goto L_0x0112;
                case -1522097648: goto L_0x0107;
                case -1512778085: goto L_0x00fd;
                case -1461142052: goto L_0x00f3;
                case -1024703373: goto L_0x00e8;
                case -637245050: goto L_0x00dc;
                case -390972850: goto L_0x00d0;
                case -337726012: goto L_0x00c4;
                case -242160521: goto L_0x00b8;
                case -175999878: goto L_0x00ad;
                case 14618050: goto L_0x00a2;
                case 264624306: goto L_0x0096;
                case 365061948: goto L_0x008a;
                case 393896703: goto L_0x007f;
                case 514120659: goto L_0x0074;
                case 517883033: goto L_0x0068;
                case 881565940: goto L_0x005c;
                case 967042239: goto L_0x0051;
                case 1110562654: goto L_0x0046;
                case 1457102115: goto L_0x003a;
                case 1609652513: goto L_0x002e;
                case 1610234053: goto L_0x0022;
                case 1626325763: goto L_0x0016;
                case 1815913657: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0149
        L_0x000a:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 17
            goto L_0x014a
        L_0x0016:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 8
            goto L_0x014a
        L_0x0022:
            java.lang.String r1 = "weather_mai.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 11
            goto L_0x014a
        L_0x002e:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 18
            goto L_0x014a
        L_0x003a:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 10
            goto L_0x014a
        L_0x0046:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x014a
        L_0x0051:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 3
            goto L_0x014a
        L_0x005c:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 26
            goto L_0x014a
        L_0x0068:
            java.lang.String r1 = "weather_zhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 24
            goto L_0x014a
        L_0x0074:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 7
            goto L_0x014a
        L_0x007f:
            java.lang.String r1 = "weather_dayu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 5
            goto L_0x014a
        L_0x008a:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 14
            goto L_0x014a
        L_0x0096:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 25
            goto L_0x014a
        L_0x00a2:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 2
            goto L_0x014a
        L_0x00ad:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 6
            goto L_0x014a
        L_0x00b8:
            java.lang.String r1 = "weather_qing.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 12
            goto L_0x014a
        L_0x00c4:
            java.lang.String r1 = "weather_wu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 16
            goto L_0x014a
        L_0x00d0:
            java.lang.String r1 = "weather_yin.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 20
            goto L_0x014a
        L_0x00dc:
            java.lang.String r1 = "weather_zhongyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 27
            goto L_0x014a
        L_0x00e8:
            java.lang.String r1 = "weather_leizhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 9
            goto L_0x014a
        L_0x00f3:
            java.lang.String r1 = "weather_baoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 1
            goto L_0x014a
        L_0x00fd:
            java.lang.String r1 = "weather_daxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 4
            goto L_0x014a
        L_0x0107:
            java.lang.String r1 = "weather_qing_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 13
            goto L_0x014a
        L_0x0112:
            java.lang.String r1 = "weather_yujiaxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 21
            goto L_0x014a
        L_0x011d:
            java.lang.String r1 = "weather_zhenxue_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 23
            goto L_0x014a
        L_0x0128:
            java.lang.String r1 = "weather_tedabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 15
            goto L_0x014a
        L_0x0133:
            java.lang.String r1 = "weather_zhenxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 22
            goto L_0x014a
        L_0x013e:
            java.lang.String r1 = "weather_yangsha.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 19
            goto L_0x014a
        L_0x0149:
            r1 = -1
        L_0x014a:
            switch(r1) {
                case 0: goto L_0x01c4;
                case 1: goto L_0x01c0;
                case 2: goto L_0x01bc;
                case 3: goto L_0x01b8;
                case 4: goto L_0x01b4;
                case 5: goto L_0x01b0;
                case 6: goto L_0x01ac;
                case 7: goto L_0x01a8;
                case 8: goto L_0x01a4;
                case 9: goto L_0x01a0;
                case 10: goto L_0x019c;
                case 11: goto L_0x0198;
                case 12: goto L_0x0194;
                case 13: goto L_0x0190;
                case 14: goto L_0x018c;
                case 15: goto L_0x0188;
                case 16: goto L_0x0184;
                case 17: goto L_0x0180;
                case 18: goto L_0x017c;
                case 19: goto L_0x0178;
                case 20: goto L_0x0174;
                case 21: goto L_0x0170;
                case 22: goto L_0x016b;
                case 23: goto L_0x0166;
                case 24: goto L_0x0161;
                case 25: goto L_0x015c;
                case 26: goto L_0x0157;
                case 27: goto L_0x0152;
                default: goto L_0x014d;
            }
        L_0x014d:
            r0 = 2131235166(0x7f08115e, float:1.8086518E38)
            goto L_0x01c8
        L_0x0152:
            r0 = 2131235180(0x7f08116c, float:1.8086547E38)
            goto L_0x01c8
        L_0x0157:
            r0 = 2131235179(0x7f08116b, float:1.8086545E38)
            goto L_0x01c8
        L_0x015c:
            r0 = 2131235174(0x7f081166, float:1.8086535E38)
            goto L_0x01c8
        L_0x0161:
            r0 = 2131235178(0x7f08116a, float:1.8086543E38)
            goto L_0x01c8
        L_0x0166:
            r0 = 2131235173(0x7f081165, float:1.8086532E38)
            goto L_0x01c8
        L_0x016b:
            r0 = 2131235177(0x7f081169, float:1.808654E38)
            goto L_0x01c8
        L_0x0170:
            r0 = 2131235176(0x7f081168, float:1.8086539E38)
            goto L_0x01c8
        L_0x0174:
            r0 = 2131235175(0x7f081167, float:1.8086537E38)
            goto L_0x01c8
        L_0x0178:
            r0 = 2131235170(0x7f081162, float:1.8086526E38)
            goto L_0x01c8
        L_0x017c:
            r0 = 2131235169(0x7f081161, float:1.8086524E38)
            goto L_0x01c8
        L_0x0180:
            r0 = 2131235168(0x7f081160, float:1.8086522E38)
            goto L_0x01c8
        L_0x0184:
            r0 = 2131235167(0x7f08115f, float:1.808652E38)
            goto L_0x01c8
        L_0x0188:
            r0 = 2131235165(0x7f08115d, float:1.8086516E38)
            goto L_0x01c8
        L_0x018c:
            r0 = 2131235164(0x7f08115c, float:1.8086514E38)
            goto L_0x01c8
        L_0x0190:
            r0 = 2131235172(0x7f081164, float:1.808653E38)
            goto L_0x01c8
        L_0x0194:
            r0 = 2131235163(0x7f08115b, float:1.8086512E38)
            goto L_0x01c8
        L_0x0198:
            r0 = 2131235161(0x7f081159, float:1.8086508E38)
            goto L_0x01c8
        L_0x019c:
            r0 = 2131235160(0x7f081158, float:1.8086506E38)
            goto L_0x01c8
        L_0x01a0:
            r0 = 2131235159(0x7f081157, float:1.8086504E38)
            goto L_0x01c8
        L_0x01a4:
            r0 = 2131235158(0x7f081156, float:1.8086502E38)
            goto L_0x01c8
        L_0x01a8:
            r0 = 2131235171(0x7f081163, float:1.8086528E38)
            goto L_0x01c8
        L_0x01ac:
            r0 = 2131235157(0x7f081155, float:1.80865E38)
            goto L_0x01c8
        L_0x01b0:
            r0 = 2131235155(0x7f081153, float:1.8086496E38)
            goto L_0x01c8
        L_0x01b4:
            r0 = 2131235154(0x7f081152, float:1.8086494E38)
            goto L_0x01c8
        L_0x01b8:
            r0 = 2131235153(0x7f081151, float:1.8086492E38)
            goto L_0x01c8
        L_0x01bc:
            r0 = 2131235152(0x7f081150, float:1.808649E38)
            goto L_0x01c8
        L_0x01c0:
            r0 = 2131235151(0x7f08114f, float:1.8086488E38)
            goto L_0x01c8
        L_0x01c4:
            r0 = 2131235150(0x7f08114e, float:1.8086486E38)
        L_0x01c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bean.WeatherInfo.getId7AlsV2Resource(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getAudiResource(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            int r1 = r3.hashCode()
            switch(r1) {
                case -2007006149: goto L_0x013e;
                case -1964169151: goto L_0x0133;
                case -1893213074: goto L_0x0128;
                case -1762304422: goto L_0x011d;
                case -1570070136: goto L_0x0112;
                case -1522097648: goto L_0x0107;
                case -1512778085: goto L_0x00fd;
                case -1461142052: goto L_0x00f3;
                case -1024703373: goto L_0x00e8;
                case -637245050: goto L_0x00dc;
                case -390972850: goto L_0x00d0;
                case -337726012: goto L_0x00c4;
                case -242160521: goto L_0x00b8;
                case -175999878: goto L_0x00ad;
                case 14618050: goto L_0x00a2;
                case 264624306: goto L_0x0096;
                case 365061948: goto L_0x008a;
                case 393896703: goto L_0x007f;
                case 514120659: goto L_0x0074;
                case 517883033: goto L_0x0068;
                case 881565940: goto L_0x005c;
                case 967042239: goto L_0x0051;
                case 1110562654: goto L_0x0046;
                case 1457102115: goto L_0x003a;
                case 1609652513: goto L_0x002e;
                case 1610234053: goto L_0x0022;
                case 1626325763: goto L_0x0016;
                case 1815913657: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0149
        L_0x000a:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 17
            goto L_0x014a
        L_0x0016:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 8
            goto L_0x014a
        L_0x0022:
            java.lang.String r1 = "weather_mai.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 11
            goto L_0x014a
        L_0x002e:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 18
            goto L_0x014a
        L_0x003a:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 10
            goto L_0x014a
        L_0x0046:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x014a
        L_0x0051:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 3
            goto L_0x014a
        L_0x005c:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 26
            goto L_0x014a
        L_0x0068:
            java.lang.String r1 = "weather_zhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 24
            goto L_0x014a
        L_0x0074:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 7
            goto L_0x014a
        L_0x007f:
            java.lang.String r1 = "weather_dayu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 5
            goto L_0x014a
        L_0x008a:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 14
            goto L_0x014a
        L_0x0096:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 25
            goto L_0x014a
        L_0x00a2:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 2
            goto L_0x014a
        L_0x00ad:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 6
            goto L_0x014a
        L_0x00b8:
            java.lang.String r1 = "weather_qing.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 12
            goto L_0x014a
        L_0x00c4:
            java.lang.String r1 = "weather_wu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 16
            goto L_0x014a
        L_0x00d0:
            java.lang.String r1 = "weather_yin.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 20
            goto L_0x014a
        L_0x00dc:
            java.lang.String r1 = "weather_zhongyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 27
            goto L_0x014a
        L_0x00e8:
            java.lang.String r1 = "weather_leizhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 9
            goto L_0x014a
        L_0x00f3:
            java.lang.String r1 = "weather_baoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 1
            goto L_0x014a
        L_0x00fd:
            java.lang.String r1 = "weather_daxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 4
            goto L_0x014a
        L_0x0107:
            java.lang.String r1 = "weather_qing_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 13
            goto L_0x014a
        L_0x0112:
            java.lang.String r1 = "weather_yujiaxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 21
            goto L_0x014a
        L_0x011d:
            java.lang.String r1 = "weather_zhenxue_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 23
            goto L_0x014a
        L_0x0128:
            java.lang.String r1 = "weather_tedabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 15
            goto L_0x014a
        L_0x0133:
            java.lang.String r1 = "weather_zhenxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 22
            goto L_0x014a
        L_0x013e:
            java.lang.String r1 = "weather_yangsha.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 19
            goto L_0x014a
        L_0x0149:
            r1 = -1
        L_0x014a:
            switch(r1) {
                case 0: goto L_0x01c4;
                case 1: goto L_0x01c0;
                case 2: goto L_0x01bc;
                case 3: goto L_0x01b8;
                case 4: goto L_0x01b4;
                case 5: goto L_0x01b0;
                case 6: goto L_0x01ac;
                case 7: goto L_0x01a8;
                case 8: goto L_0x01a4;
                case 9: goto L_0x01a0;
                case 10: goto L_0x019c;
                case 11: goto L_0x0198;
                case 12: goto L_0x0194;
                case 13: goto L_0x0190;
                case 14: goto L_0x018c;
                case 15: goto L_0x0188;
                case 16: goto L_0x0184;
                case 17: goto L_0x0180;
                case 18: goto L_0x017c;
                case 19: goto L_0x0178;
                case 20: goto L_0x0174;
                case 21: goto L_0x0170;
                case 22: goto L_0x016b;
                case 23: goto L_0x0166;
                case 24: goto L_0x0161;
                case 25: goto L_0x015c;
                case 26: goto L_0x0157;
                case 27: goto L_0x0152;
                default: goto L_0x014d;
            }
        L_0x014d:
            r0 = 2131235122(0x7f081132, float:1.808643E38)
            goto L_0x01c8
        L_0x0152:
            r0 = 2131235139(0x7f081143, float:1.8086464E38)
            goto L_0x01c8
        L_0x0157:
            r0 = 2131235138(0x7f081142, float:1.8086462E38)
            goto L_0x01c8
        L_0x015c:
            r0 = 2131235137(0x7f081141, float:1.808646E38)
            goto L_0x01c8
        L_0x0161:
            r0 = 2131235136(0x7f081140, float:1.8086457E38)
            goto L_0x01c8
        L_0x0166:
            r0 = 2131235135(0x7f08113f, float:1.8086455E38)
            goto L_0x01c8
        L_0x016b:
            r0 = 2131235134(0x7f08113e, float:1.8086453E38)
            goto L_0x01c8
        L_0x0170:
            r0 = 2131235133(0x7f08113d, float:1.8086451E38)
            goto L_0x01c8
        L_0x0174:
            r0 = 2131235132(0x7f08113c, float:1.808645E38)
            goto L_0x01c8
        L_0x0178:
            r0 = 2131235131(0x7f08113b, float:1.8086447E38)
            goto L_0x01c8
        L_0x017c:
            r0 = 2131235130(0x7f08113a, float:1.8086445E38)
            goto L_0x01c8
        L_0x0180:
            r0 = 2131235129(0x7f081139, float:1.8086443E38)
            goto L_0x01c8
        L_0x0184:
            r0 = 2131235128(0x7f081138, float:1.8086441E38)
            goto L_0x01c8
        L_0x0188:
            r0 = 2131235127(0x7f081137, float:1.808644E38)
            goto L_0x01c8
        L_0x018c:
            r0 = 2131235126(0x7f081136, float:1.8086437E38)
            goto L_0x01c8
        L_0x0190:
            r0 = 2131235125(0x7f081135, float:1.8086435E38)
            goto L_0x01c8
        L_0x0194:
            r0 = 2131235124(0x7f081134, float:1.8086433E38)
            goto L_0x01c8
        L_0x0198:
            r0 = 2131235121(0x7f081131, float:1.8086427E38)
            goto L_0x01c8
        L_0x019c:
            r0 = 2131235120(0x7f081130, float:1.8086425E38)
            goto L_0x01c8
        L_0x01a0:
            r0 = 2131235119(0x7f08112f, float:1.8086423E38)
            goto L_0x01c8
        L_0x01a4:
            r0 = 2131235118(0x7f08112e, float:1.808642E38)
            goto L_0x01c8
        L_0x01a8:
            r0 = 2131235117(0x7f08112d, float:1.8086419E38)
            goto L_0x01c8
        L_0x01ac:
            r0 = 2131235116(0x7f08112c, float:1.8086417E38)
            goto L_0x01c8
        L_0x01b0:
            r0 = 2131235114(0x7f08112a, float:1.8086413E38)
            goto L_0x01c8
        L_0x01b4:
            r0 = 2131235113(0x7f081129, float:1.808641E38)
            goto L_0x01c8
        L_0x01b8:
            r0 = 2131235112(0x7f081128, float:1.8086409E38)
            goto L_0x01c8
        L_0x01bc:
            r0 = 2131235111(0x7f081127, float:1.8086407E38)
            goto L_0x01c8
        L_0x01c0:
            r0 = 2131235110(0x7f081126, float:1.8086405E38)
            goto L_0x01c8
        L_0x01c4:
            r0 = 2131235109(0x7f081125, float:1.8086403E38)
        L_0x01c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bean.WeatherInfo.getAudiResource(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getID8MainEditResource(java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            int r1 = r3.hashCode()
            switch(r1) {
                case -2007006149: goto L_0x013e;
                case -1964169151: goto L_0x0133;
                case -1893213074: goto L_0x0128;
                case -1762304422: goto L_0x011d;
                case -1570070136: goto L_0x0112;
                case -1522097648: goto L_0x0107;
                case -1512778085: goto L_0x00fd;
                case -1461142052: goto L_0x00f3;
                case -1024703373: goto L_0x00e8;
                case -637245050: goto L_0x00dc;
                case -390972850: goto L_0x00d0;
                case -337726012: goto L_0x00c4;
                case -242160521: goto L_0x00b8;
                case -175999878: goto L_0x00ad;
                case 14618050: goto L_0x00a2;
                case 264624306: goto L_0x0096;
                case 365061948: goto L_0x008a;
                case 393896703: goto L_0x007f;
                case 514120659: goto L_0x0074;
                case 517883033: goto L_0x0068;
                case 881565940: goto L_0x005c;
                case 967042239: goto L_0x0051;
                case 1110562654: goto L_0x0046;
                case 1457102115: goto L_0x003a;
                case 1609652513: goto L_0x002e;
                case 1610234053: goto L_0x0022;
                case 1626325763: goto L_0x0016;
                case 1815913657: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0149
        L_0x000a:
            java.lang.String r1 = "weather_xiaoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 17
            goto L_0x014a
        L_0x0016:
            java.lang.String r1 = "weather_fuchen.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 8
            goto L_0x014a
        L_0x0022:
            java.lang.String r1 = "weather_mai.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 11
            goto L_0x014a
        L_0x002e:
            java.lang.String r1 = "weather_xiaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 18
            goto L_0x014a
        L_0x003a:
            java.lang.String r1 = "weather_leizhenyubanyoubingbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 10
            goto L_0x014a
        L_0x0046:
            java.lang.String r1 = "weather_baoxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x014a
        L_0x0051:
            java.lang.String r1 = "weather_dabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 3
            goto L_0x014a
        L_0x005c:
            java.lang.String r1 = "weather_zhongxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 26
            goto L_0x014a
        L_0x0068:
            java.lang.String r1 = "weather_zhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 24
            goto L_0x014a
        L_0x0074:
            java.lang.String r1 = "weather_duoyun_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 7
            goto L_0x014a
        L_0x007f:
            java.lang.String r1 = "weather_dayu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 5
            goto L_0x014a
        L_0x008a:
            java.lang.String r1 = "weather_shachenbao.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 14
            goto L_0x014a
        L_0x0096:
            java.lang.String r1 = "weather_zhenyu_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 25
            goto L_0x014a
        L_0x00a2:
            java.lang.String r1 = "weather_bingyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 2
            goto L_0x014a
        L_0x00ad:
            java.lang.String r1 = "weather_duoyun.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 6
            goto L_0x014a
        L_0x00b8:
            java.lang.String r1 = "weather_qing.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 12
            goto L_0x014a
        L_0x00c4:
            java.lang.String r1 = "weather_wu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 16
            goto L_0x014a
        L_0x00d0:
            java.lang.String r1 = "weather_yin.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 20
            goto L_0x014a
        L_0x00dc:
            java.lang.String r1 = "weather_zhongyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 27
            goto L_0x014a
        L_0x00e8:
            java.lang.String r1 = "weather_leizhenyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 9
            goto L_0x014a
        L_0x00f3:
            java.lang.String r1 = "weather_baoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 1
            goto L_0x014a
        L_0x00fd:
            java.lang.String r1 = "weather_daxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 4
            goto L_0x014a
        L_0x0107:
            java.lang.String r1 = "weather_qing_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 13
            goto L_0x014a
        L_0x0112:
            java.lang.String r1 = "weather_yujiaxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 21
            goto L_0x014a
        L_0x011d:
            java.lang.String r1 = "weather_zhenxue_night.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 23
            goto L_0x014a
        L_0x0128:
            java.lang.String r1 = "weather_tedabaoyu.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 15
            goto L_0x014a
        L_0x0133:
            java.lang.String r1 = "weather_zhenxue.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 22
            goto L_0x014a
        L_0x013e:
            java.lang.String r1 = "weather_yangsha.png"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0008
            r1 = 19
            goto L_0x014a
        L_0x0149:
            r1 = -1
        L_0x014a:
            switch(r1) {
                case 0: goto L_0x01c4;
                case 1: goto L_0x01c0;
                case 2: goto L_0x01bc;
                case 3: goto L_0x01b8;
                case 4: goto L_0x01b4;
                case 5: goto L_0x01b0;
                case 6: goto L_0x01ac;
                case 7: goto L_0x01a8;
                case 8: goto L_0x01a4;
                case 9: goto L_0x01a0;
                case 10: goto L_0x019c;
                case 11: goto L_0x0198;
                case 12: goto L_0x0194;
                case 13: goto L_0x0190;
                case 14: goto L_0x018c;
                case 15: goto L_0x0188;
                case 16: goto L_0x0184;
                case 17: goto L_0x0180;
                case 18: goto L_0x017c;
                case 19: goto L_0x0178;
                case 20: goto L_0x0174;
                case 21: goto L_0x0170;
                case 22: goto L_0x016b;
                case 23: goto L_0x0166;
                case 24: goto L_0x0161;
                case 25: goto L_0x015c;
                case 26: goto L_0x0157;
                case 27: goto L_0x0152;
                default: goto L_0x014d;
            }
        L_0x014d:
            r0 = 2131233308(0x7f080a1c, float:1.808275E38)
            goto L_0x01c8
        L_0x0152:
            r0 = 2131233413(0x7f080a85, float:1.8082963E38)
            goto L_0x01c8
        L_0x0157:
            r0 = 2131233409(0x7f080a81, float:1.8082955E38)
            goto L_0x01c8
        L_0x015c:
            r0 = 2131233405(0x7f080a7d, float:1.8082947E38)
            goto L_0x01c8
        L_0x0161:
            r0 = 2131233401(0x7f080a79, float:1.8082938E38)
            goto L_0x01c8
        L_0x0166:
            r0 = 2131233397(0x7f080a75, float:1.808293E38)
            goto L_0x01c8
        L_0x016b:
            r0 = 2131233393(0x7f080a71, float:1.8082922E38)
            goto L_0x01c8
        L_0x0170:
            r0 = 2131233389(0x7f080a6d, float:1.8082914E38)
            goto L_0x01c8
        L_0x0174:
            r0 = 2131233385(0x7f080a69, float:1.8082906E38)
            goto L_0x01c8
        L_0x0178:
            r0 = 2131233381(0x7f080a65, float:1.8082898E38)
            goto L_0x01c8
        L_0x017c:
            r0 = 2131233375(0x7f080a5f, float:1.8082886E38)
            goto L_0x01c8
        L_0x0180:
            r0 = 2131233371(0x7f080a5b, float:1.8082878E38)
            goto L_0x01c8
        L_0x0184:
            r0 = 2131233367(0x7f080a57, float:1.808287E38)
            goto L_0x01c8
        L_0x0188:
            r0 = 2131233354(0x7f080a4a, float:1.8082843E38)
            goto L_0x01c8
        L_0x018c:
            r0 = 2131233339(0x7f080a3b, float:1.8082813E38)
            goto L_0x01c8
        L_0x0190:
            r0 = 2131233322(0x7f080a2a, float:1.8082778E38)
            goto L_0x01c8
        L_0x0194:
            r0 = 2131233318(0x7f080a26, float:1.808277E38)
            goto L_0x01c8
        L_0x0198:
            r0 = 2131233300(0x7f080a14, float:1.8082734E38)
            goto L_0x01c8
        L_0x019c:
            r0 = 2131233291(0x7f080a0b, float:1.8082715E38)
            goto L_0x01c8
        L_0x01a0:
            r0 = 2131233287(0x7f080a07, float:1.8082707E38)
            goto L_0x01c8
        L_0x01a4:
            r0 = 2131233276(0x7f0809fc, float:1.8082685E38)
            goto L_0x01c8
        L_0x01a8:
            r0 = 2131233264(0x7f0809f0, float:1.808266E38)
            goto L_0x01c8
        L_0x01ac:
            r0 = 2131233260(0x7f0809ec, float:1.8082652E38)
            goto L_0x01c8
        L_0x01b0:
            r0 = 2131233254(0x7f0809e6, float:1.808264E38)
            goto L_0x01c8
        L_0x01b4:
            r0 = 2131233250(0x7f0809e2, float:1.8082632E38)
            goto L_0x01c8
        L_0x01b8:
            r0 = 2131233246(0x7f0809de, float:1.8082624E38)
            goto L_0x01c8
        L_0x01bc:
            r0 = 2131233234(0x7f0809d2, float:1.80826E38)
            goto L_0x01c8
        L_0x01c0:
            r0 = 2131233230(0x7f0809ce, float:1.8082592E38)
            goto L_0x01c8
        L_0x01c4:
            r0 = 2131233226(0x7f0809ca, float:1.8082584E38)
        L_0x01c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bean.WeatherInfo.getID8MainEditResource(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getID8MainEditResourceV2(java.lang.String r5, android.content.Context r6) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            int r2 = r5.hashCode()
            switch(r2) {
                case -2007006149: goto L_0x013f;
                case -1964169151: goto L_0x0134;
                case -1893213074: goto L_0x0129;
                case -1762304422: goto L_0x011e;
                case -1570070136: goto L_0x0113;
                case -1522097648: goto L_0x0108;
                case -1512778085: goto L_0x00fe;
                case -1461142052: goto L_0x00f4;
                case -1024703373: goto L_0x00e9;
                case -637245050: goto L_0x00dd;
                case -390972850: goto L_0x00d1;
                case -337726012: goto L_0x00c5;
                case -242160521: goto L_0x00b9;
                case -175999878: goto L_0x00ae;
                case 14618050: goto L_0x00a3;
                case 264624306: goto L_0x0097;
                case 365061948: goto L_0x008b;
                case 393896703: goto L_0x0080;
                case 514120659: goto L_0x0075;
                case 517883033: goto L_0x0069;
                case 881565940: goto L_0x005d;
                case 967042239: goto L_0x0052;
                case 1110562654: goto L_0x0047;
                case 1457102115: goto L_0x003b;
                case 1609652513: goto L_0x002f;
                case 1610234053: goto L_0x0023;
                case 1626325763: goto L_0x0017;
                case 1815913657: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x014a
        L_0x000b:
            java.lang.String r2 = "weather_xiaoxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 17
            goto L_0x014b
        L_0x0017:
            java.lang.String r2 = "weather_fuchen.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 8
            goto L_0x014b
        L_0x0023:
            java.lang.String r2 = "weather_mai.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 11
            goto L_0x014b
        L_0x002f:
            java.lang.String r2 = "weather_xiaoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 18
            goto L_0x014b
        L_0x003b:
            java.lang.String r2 = "weather_leizhenyubanyoubingbao.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 10
            goto L_0x014b
        L_0x0047:
            java.lang.String r2 = "weather_baoxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 0
            goto L_0x014b
        L_0x0052:
            java.lang.String r2 = "weather_dabaoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 3
            goto L_0x014b
        L_0x005d:
            java.lang.String r2 = "weather_zhongxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 26
            goto L_0x014b
        L_0x0069:
            java.lang.String r2 = "weather_zhenyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 24
            goto L_0x014b
        L_0x0075:
            java.lang.String r2 = "weather_duoyun_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 7
            goto L_0x014b
        L_0x0080:
            java.lang.String r2 = "weather_dayu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 5
            goto L_0x014b
        L_0x008b:
            java.lang.String r2 = "weather_shachenbao.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 14
            goto L_0x014b
        L_0x0097:
            java.lang.String r2 = "weather_zhenyu_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 25
            goto L_0x014b
        L_0x00a3:
            java.lang.String r2 = "weather_bingyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 2
            goto L_0x014b
        L_0x00ae:
            java.lang.String r2 = "weather_duoyun.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 6
            goto L_0x014b
        L_0x00b9:
            java.lang.String r2 = "weather_qing.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 12
            goto L_0x014b
        L_0x00c5:
            java.lang.String r2 = "weather_wu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 16
            goto L_0x014b
        L_0x00d1:
            java.lang.String r2 = "weather_yin.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 20
            goto L_0x014b
        L_0x00dd:
            java.lang.String r2 = "weather_zhongyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 27
            goto L_0x014b
        L_0x00e9:
            java.lang.String r2 = "weather_leizhenyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 9
            goto L_0x014b
        L_0x00f4:
            java.lang.String r2 = "weather_baoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 1
            goto L_0x014b
        L_0x00fe:
            java.lang.String r2 = "weather_daxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 4
            goto L_0x014b
        L_0x0108:
            java.lang.String r2 = "weather_qing_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 13
            goto L_0x014b
        L_0x0113:
            java.lang.String r2 = "weather_yujiaxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 21
            goto L_0x014b
        L_0x011e:
            java.lang.String r2 = "weather_zhenxue_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 23
            goto L_0x014b
        L_0x0129:
            java.lang.String r2 = "weather_tedabaoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 15
            goto L_0x014b
        L_0x0134:
            java.lang.String r2 = "weather_zhenxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 22
            goto L_0x014b
        L_0x013f:
            java.lang.String r2 = "weather_yangsha.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x0009
            r2 = 19
            goto L_0x014b
        L_0x014a:
            r2 = -1
        L_0x014b:
            switch(r2) {
                case 0: goto L_0x0220;
                case 1: goto L_0x0219;
                case 2: goto L_0x0212;
                case 3: goto L_0x020b;
                case 4: goto L_0x0204;
                case 5: goto L_0x01fd;
                case 6: goto L_0x01f6;
                case 7: goto L_0x01ef;
                case 8: goto L_0x01e8;
                case 9: goto L_0x01e1;
                case 10: goto L_0x01da;
                case 11: goto L_0x01d3;
                case 12: goto L_0x01cc;
                case 13: goto L_0x01c5;
                case 14: goto L_0x01be;
                case 15: goto L_0x01b6;
                case 16: goto L_0x01ae;
                case 17: goto L_0x01a6;
                case 18: goto L_0x019e;
                case 19: goto L_0x0196;
                case 20: goto L_0x018e;
                case 21: goto L_0x0186;
                case 22: goto L_0x017e;
                case 23: goto L_0x0176;
                case 24: goto L_0x016e;
                case 25: goto L_0x0166;
                case 26: goto L_0x015e;
                case 27: goto L_0x0156;
                default: goto L_0x014e;
            }
        L_0x014e:
            r0 = 2131233307(0x7f080a1b, float:1.8082748E38)
            r1 = 2131233310(0x7f080a1e, float:1.8082754E38)
            goto L_0x0227
        L_0x0156:
            r0 = 2131233412(0x7f080a84, float:1.808296E38)
            r1 = 2131233415(0x7f080a87, float:1.8082967E38)
            goto L_0x0227
        L_0x015e:
            r0 = 2131233408(0x7f080a80, float:1.8082953E38)
            r1 = 2131233411(0x7f080a83, float:1.8082959E38)
            goto L_0x0227
        L_0x0166:
            r0 = 2131233404(0x7f080a7c, float:1.8082945E38)
            r1 = 2131233407(0x7f080a7f, float:1.808295E38)
            goto L_0x0227
        L_0x016e:
            r0 = 2131233400(0x7f080a78, float:1.8082936E38)
            r1 = 2131233403(0x7f080a7b, float:1.8082943E38)
            goto L_0x0227
        L_0x0176:
            r0 = 2131233396(0x7f080a74, float:1.8082928E38)
            r1 = 2131233399(0x7f080a77, float:1.8082934E38)
            goto L_0x0227
        L_0x017e:
            r0 = 2131233392(0x7f080a70, float:1.808292E38)
            r1 = 2131233395(0x7f080a73, float:1.8082926E38)
            goto L_0x0227
        L_0x0186:
            r0 = 2131233388(0x7f080a6c, float:1.8082912E38)
            r1 = 2131233391(0x7f080a6f, float:1.8082918E38)
            goto L_0x0227
        L_0x018e:
            r0 = 2131233384(0x7f080a68, float:1.8082904E38)
            r1 = 2131233387(0x7f080a6b, float:1.808291E38)
            goto L_0x0227
        L_0x0196:
            r0 = 2131233380(0x7f080a64, float:1.8082896E38)
            r1 = 2131233383(0x7f080a67, float:1.8082902E38)
            goto L_0x0227
        L_0x019e:
            r0 = 2131233374(0x7f080a5e, float:1.8082884E38)
            r1 = 2131233377(0x7f080a61, float:1.808289E38)
            goto L_0x0227
        L_0x01a6:
            r0 = 2131233370(0x7f080a5a, float:1.8082876E38)
            r1 = 2131233373(0x7f080a5d, float:1.8082882E38)
            goto L_0x0227
        L_0x01ae:
            r0 = 2131233366(0x7f080a56, float:1.8082867E38)
            r1 = 2131233369(0x7f080a59, float:1.8082874E38)
            goto L_0x0227
        L_0x01b6:
            r0 = 2131233353(0x7f080a49, float:1.8082841E38)
            r1 = 2131233356(0x7f080a4c, float:1.8082847E38)
            goto L_0x0227
        L_0x01be:
            r0 = 2131233338(0x7f080a3a, float:1.808281E38)
            r1 = 2131233341(0x7f080a3d, float:1.8082817E38)
            goto L_0x0227
        L_0x01c5:
            r0 = 2131233321(0x7f080a29, float:1.8082776E38)
            r1 = 2131233324(0x7f080a2c, float:1.8082782E38)
            goto L_0x0227
        L_0x01cc:
            r0 = 2131233317(0x7f080a25, float:1.8082768E38)
            r1 = 2131233320(0x7f080a28, float:1.8082774E38)
            goto L_0x0227
        L_0x01d3:
            r0 = 2131233299(0x7f080a13, float:1.8082732E38)
            r1 = 2131233302(0x7f080a16, float:1.8082738E38)
            goto L_0x0227
        L_0x01da:
            r0 = 2131233290(0x7f080a0a, float:1.8082713E38)
            r1 = 2131233293(0x7f080a0d, float:1.808272E38)
            goto L_0x0227
        L_0x01e1:
            r0 = 2131233286(0x7f080a06, float:1.8082705E38)
            r1 = 2131233289(0x7f080a09, float:1.8082711E38)
            goto L_0x0227
        L_0x01e8:
            r0 = 2131233275(0x7f0809fb, float:1.8082683E38)
            r1 = 2131233278(0x7f0809fe, float:1.8082689E38)
            goto L_0x0227
        L_0x01ef:
            r0 = 2131233263(0x7f0809ef, float:1.8082659E38)
            r1 = 2131233266(0x7f0809f2, float:1.8082665E38)
            goto L_0x0227
        L_0x01f6:
            r0 = 2131233259(0x7f0809eb, float:1.808265E38)
            r1 = 2131233262(0x7f0809ee, float:1.8082657E38)
            goto L_0x0227
        L_0x01fd:
            r0 = 2131233253(0x7f0809e5, float:1.8082638E38)
            r1 = 2131233256(0x7f0809e8, float:1.8082644E38)
            goto L_0x0227
        L_0x0204:
            r0 = 2131233249(0x7f0809e1, float:1.808263E38)
            r1 = 2131233252(0x7f0809e4, float:1.8082636E38)
            goto L_0x0227
        L_0x020b:
            r0 = 2131233245(0x7f0809dd, float:1.8082622E38)
            r1 = 2131233248(0x7f0809e0, float:1.8082628E38)
            goto L_0x0227
        L_0x0212:
            r0 = 2131233233(0x7f0809d1, float:1.8082598E38)
            r1 = 2131233236(0x7f0809d4, float:1.8082604E38)
            goto L_0x0227
        L_0x0219:
            r0 = 2131233229(0x7f0809cd, float:1.808259E38)
            r1 = 2131233232(0x7f0809d0, float:1.8082596E38)
            goto L_0x0227
        L_0x0220:
            r0 = 2131233225(0x7f0809c9, float:1.8082581E38)
            r1 = 2131233228(0x7f0809cc, float:1.8082588E38)
        L_0x0227:
            android.databinding.ObservableField<android.graphics.drawable.Drawable> r2 = r4.id8ImageBg
            android.content.res.Resources r3 = r6.getResources()
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r0)
            r2.set(r3)
            android.databinding.ObservableField<android.graphics.drawable.Drawable> r2 = r4.id8ImageIcon
            android.content.res.Resources r3 = r6.getResources()
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r1)
            r2.set(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bean.WeatherInfo.getID8MainEditResourceV2(java.lang.String, android.content.Context):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getID8GsMainEditResourceV2(java.lang.String r5, android.content.Context r6) {
        /*
            r4 = this;
            r0 = 2131232705(0x7f0807c1, float:1.8081527E38)
            r1 = 0
            int r2 = r5.hashCode()
            switch(r2) {
                case -2007006149: goto L_0x0141;
                case -1964169151: goto L_0x0136;
                case -1893213074: goto L_0x012b;
                case -1762304422: goto L_0x0120;
                case -1570070136: goto L_0x0115;
                case -1522097648: goto L_0x010a;
                case -1512778085: goto L_0x0100;
                case -1461142052: goto L_0x00f6;
                case -1024703373: goto L_0x00eb;
                case -637245050: goto L_0x00df;
                case -390972850: goto L_0x00d3;
                case -337726012: goto L_0x00c7;
                case -242160521: goto L_0x00bb;
                case -175999878: goto L_0x00b0;
                case 14618050: goto L_0x00a5;
                case 264624306: goto L_0x0099;
                case 365061948: goto L_0x008d;
                case 393896703: goto L_0x0082;
                case 514120659: goto L_0x0077;
                case 517883033: goto L_0x006b;
                case 881565940: goto L_0x005f;
                case 967042239: goto L_0x0054;
                case 1110562654: goto L_0x0049;
                case 1457102115: goto L_0x003d;
                case 1609652513: goto L_0x0031;
                case 1610234053: goto L_0x0025;
                case 1626325763: goto L_0x0019;
                case 1815913657: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x014c
        L_0x000d:
            java.lang.String r2 = "weather_xiaoxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 17
            goto L_0x014d
        L_0x0019:
            java.lang.String r2 = "weather_fuchen.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 8
            goto L_0x014d
        L_0x0025:
            java.lang.String r2 = "weather_mai.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 11
            goto L_0x014d
        L_0x0031:
            java.lang.String r2 = "weather_xiaoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 18
            goto L_0x014d
        L_0x003d:
            java.lang.String r2 = "weather_leizhenyubanyoubingbao.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 10
            goto L_0x014d
        L_0x0049:
            java.lang.String r2 = "weather_baoxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 0
            goto L_0x014d
        L_0x0054:
            java.lang.String r2 = "weather_dabaoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 3
            goto L_0x014d
        L_0x005f:
            java.lang.String r2 = "weather_zhongxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 26
            goto L_0x014d
        L_0x006b:
            java.lang.String r2 = "weather_zhenyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 24
            goto L_0x014d
        L_0x0077:
            java.lang.String r2 = "weather_duoyun_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 7
            goto L_0x014d
        L_0x0082:
            java.lang.String r2 = "weather_dayu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 5
            goto L_0x014d
        L_0x008d:
            java.lang.String r2 = "weather_shachenbao.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 14
            goto L_0x014d
        L_0x0099:
            java.lang.String r2 = "weather_zhenyu_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 25
            goto L_0x014d
        L_0x00a5:
            java.lang.String r2 = "weather_bingyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 2
            goto L_0x014d
        L_0x00b0:
            java.lang.String r2 = "weather_duoyun.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 6
            goto L_0x014d
        L_0x00bb:
            java.lang.String r2 = "weather_qing.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 12
            goto L_0x014d
        L_0x00c7:
            java.lang.String r2 = "weather_wu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 16
            goto L_0x014d
        L_0x00d3:
            java.lang.String r2 = "weather_yin.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 20
            goto L_0x014d
        L_0x00df:
            java.lang.String r2 = "weather_zhongyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 27
            goto L_0x014d
        L_0x00eb:
            java.lang.String r2 = "weather_leizhenyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 9
            goto L_0x014d
        L_0x00f6:
            java.lang.String r2 = "weather_baoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 1
            goto L_0x014d
        L_0x0100:
            java.lang.String r2 = "weather_daxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 4
            goto L_0x014d
        L_0x010a:
            java.lang.String r2 = "weather_qing_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 13
            goto L_0x014d
        L_0x0115:
            java.lang.String r2 = "weather_yujiaxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 21
            goto L_0x014d
        L_0x0120:
            java.lang.String r2 = "weather_zhenxue_night.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 23
            goto L_0x014d
        L_0x012b:
            java.lang.String r2 = "weather_tedabaoyu.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 15
            goto L_0x014d
        L_0x0136:
            java.lang.String r2 = "weather_zhenxue.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 22
            goto L_0x014d
        L_0x0141:
            java.lang.String r2 = "weather_yangsha.png"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x000b
            r2 = 19
            goto L_0x014d
        L_0x014c:
            r2 = -1
        L_0x014d:
            switch(r2) {
                case 0: goto L_0x01c7;
                case 1: goto L_0x01c3;
                case 2: goto L_0x01bf;
                case 3: goto L_0x01bb;
                case 4: goto L_0x01b7;
                case 5: goto L_0x01b3;
                case 6: goto L_0x01af;
                case 7: goto L_0x01ab;
                case 8: goto L_0x01a7;
                case 9: goto L_0x01a3;
                case 10: goto L_0x019f;
                case 11: goto L_0x019b;
                case 12: goto L_0x0197;
                case 13: goto L_0x0193;
                case 14: goto L_0x018f;
                case 15: goto L_0x018b;
                case 16: goto L_0x0187;
                case 17: goto L_0x0183;
                case 18: goto L_0x017f;
                case 19: goto L_0x017b;
                case 20: goto L_0x0177;
                case 21: goto L_0x0173;
                case 22: goto L_0x016e;
                case 23: goto L_0x0169;
                case 24: goto L_0x0164;
                case 25: goto L_0x015f;
                case 26: goto L_0x015a;
                case 27: goto L_0x0155;
                default: goto L_0x0150;
            }
        L_0x0150:
            r1 = 2131232718(0x7f0807ce, float:1.8081553E38)
            goto L_0x01cb
        L_0x0155:
            r1 = 2131232734(0x7f0807de, float:1.8081586E38)
            goto L_0x01cb
        L_0x015a:
            r1 = 2131232733(0x7f0807dd, float:1.8081584E38)
            goto L_0x01cb
        L_0x015f:
            r1 = 2131232732(0x7f0807dc, float:1.8081582E38)
            goto L_0x01cb
        L_0x0164:
            r1 = 2131232731(0x7f0807db, float:1.808158E38)
            goto L_0x01cb
        L_0x0169:
            r1 = 2131232730(0x7f0807da, float:1.8081578E38)
            goto L_0x01cb
        L_0x016e:
            r1 = 2131232729(0x7f0807d9, float:1.8081575E38)
            goto L_0x01cb
        L_0x0173:
            r1 = 2131232728(0x7f0807d8, float:1.8081573E38)
            goto L_0x01cb
        L_0x0177:
            r1 = 2131232727(0x7f0807d7, float:1.8081571E38)
            goto L_0x01cb
        L_0x017b:
            r1 = 2131232726(0x7f0807d6, float:1.808157E38)
            goto L_0x01cb
        L_0x017f:
            r1 = 2131232725(0x7f0807d5, float:1.8081567E38)
            goto L_0x01cb
        L_0x0183:
            r1 = 2131232724(0x7f0807d4, float:1.8081565E38)
            goto L_0x01cb
        L_0x0187:
            r1 = 2131232723(0x7f0807d3, float:1.8081563E38)
            goto L_0x01cb
        L_0x018b:
            r1 = 2131232722(0x7f0807d2, float:1.8081561E38)
            goto L_0x01cb
        L_0x018f:
            r1 = 2131232721(0x7f0807d1, float:1.808156E38)
            goto L_0x01cb
        L_0x0193:
            r1 = 2131232720(0x7f0807d0, float:1.8081557E38)
            goto L_0x01cb
        L_0x0197:
            r1 = 2131232719(0x7f0807cf, float:1.8081555E38)
            goto L_0x01cb
        L_0x019b:
            r1 = 2131232717(0x7f0807cd, float:1.8081551E38)
            goto L_0x01cb
        L_0x019f:
            r1 = 2131232716(0x7f0807cc, float:1.808155E38)
            goto L_0x01cb
        L_0x01a3:
            r1 = 2131232715(0x7f0807cb, float:1.8081547E38)
            goto L_0x01cb
        L_0x01a7:
            r1 = 2131232714(0x7f0807ca, float:1.8081545E38)
            goto L_0x01cb
        L_0x01ab:
            r1 = 2131232713(0x7f0807c9, float:1.8081543E38)
            goto L_0x01cb
        L_0x01af:
            r1 = 2131232712(0x7f0807c8, float:1.808154E38)
            goto L_0x01cb
        L_0x01b3:
            r1 = 2131232711(0x7f0807c7, float:1.8081539E38)
            goto L_0x01cb
        L_0x01b7:
            r1 = 2131232710(0x7f0807c6, float:1.8081537E38)
            goto L_0x01cb
        L_0x01bb:
            r1 = 2131232709(0x7f0807c5, float:1.8081535E38)
            goto L_0x01cb
        L_0x01bf:
            r1 = 2131232708(0x7f0807c4, float:1.8081533E38)
            goto L_0x01cb
        L_0x01c3:
            r1 = 2131232707(0x7f0807c3, float:1.808153E38)
            goto L_0x01cb
        L_0x01c7:
            r1 = 2131232706(0x7f0807c2, float:1.8081529E38)
        L_0x01cb:
            android.databinding.ObservableField<android.graphics.drawable.Drawable> r2 = r4.id8GsImageBg
            android.content.res.Resources r3 = r6.getResources()
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r0)
            r2.set(r3)
            android.databinding.ObservableField<android.graphics.drawable.Drawable> r2 = r4.id8GsImageIcon
            android.content.res.Resources r3 = r6.getResources()
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r1)
            r2.set(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wits.ksw.launcher.bean.WeatherInfo.getID8GsMainEditResourceV2(java.lang.String, android.content.Context):void");
    }
}
