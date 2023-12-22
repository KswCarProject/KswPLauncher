package com.wits.ksw.launcher.bean;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import com.txznet.weatherquery.HourWeather;
import com.txznet.weatherquery.TXZWeather;
import com.wits.ksw.C0899R;
import com.wits.ksw.KswApplication;
import com.wits.ksw.launcher.utils.UiThemeUtils;
import java.util.ArrayList;

/* loaded from: classes9.dex */
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
    public ObservableField<String> city = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> dateOfWeek = new ObservableField<>();
    public ObservableField<String> phrase = new ObservableField<>();
    public ObservableField<String> temperUnit = new ObservableField<>();
    public ObservableField<String> temperature = new ObservableField<>();
    public ObservableField<String> minTemperature = new ObservableField<>();
    public ObservableField<String> maxTemperature = new ObservableField<>();
    public ObservableField<String> sensibleTemperature = new ObservableField<>();
    public ObservableField<String> windSpeed = new ObservableField<>();
    public ObservableField<String> humidity = new ObservableField<>();
    public ObservableField<String> rainingProbability = new ObservableField<>();
    public ObservableField<String> snowingProbability = new ObservableField<>();
    public ObservableField<String> atmosphericPressure = new ObservableField<>();
    public ObservableField<String> visibility = new ObservableField<>();
    public ObservableField<String> uvIndex = new ObservableField<>();
    public ObservableField<String> temperatureRange = new ObservableField<>();
    public ObservableField<Boolean> isInitFinished = new ObservableField<>();
    public ObservableField<Boolean> isLoadSuccess = new ObservableField<>();
    public ObservableField<String> errorMessage = new ObservableField<>();
    public ObservableField<String> bottomDisplay1 = new ObservableField<>();
    public ObservableField<String> bottomDisplay2 = new ObservableField<>();
    public ObservableField<String> bottomDisplay3 = new ObservableField<>();
    public ObservableField<Drawable> image = new ObservableField<>();
    public ObservableField<Drawable> id8ImageBg = new ObservableField<>();
    public ObservableField<Drawable> id8ImageIcon = new ObservableField<>();
    public ObservableField<Drawable> id8GsImageBg = new ObservableField<>();
    public ObservableField<Drawable> id8GsImageIcon = new ObservableField<>();
    public ObservableField<Drawable> id8EditImage = new ObservableField<>();
    public ObservableField<Drawable> PempId8Image = new ObservableField<>();
    public ObservableField<Drawable> PempId8EditImage = new ObservableField<>();
    public ObservableField<Drawable> id7AlsV2ImageIcon = new ObservableField<>();
    public ObservableField<Drawable> romeoV2ImageIcon = new ObservableField<>();
    public ObservableField<String> near1HourClock = new ObservableField<>();
    public ObservableField<String> near1HourClockUnit = new ObservableField<>();
    public ObservableField<String> near1HourTemp = new ObservableField<>();
    public ObservableField<Drawable> near1HourImg = new ObservableField<>();
    public ObservableField<String> near2HourClock = new ObservableField<>();
    public ObservableField<String> near2HourClockUnit = new ObservableField<>();
    public ObservableField<String> near2HourTemp = new ObservableField<>();
    public ObservableField<Drawable> near2HourImg = new ObservableField<>();
    public ObservableField<String> near3HourClock = new ObservableField<>();
    public ObservableField<String> near3HourClockUnit = new ObservableField<>();
    public ObservableField<String> near3HourTemp = new ObservableField<>();
    public ObservableField<Drawable> near3HourImg = new ObservableField<>();
    public ObservableField<String> near4HourClock = new ObservableField<>();
    public ObservableField<String> near4HourClockUnit = new ObservableField<>();
    public ObservableField<String> near4HourTemp = new ObservableField<>();
    public ObservableField<Drawable> near4HourImg = new ObservableField<>();
    public ObservableField<String> near5HourClock = new ObservableField<>();
    public ObservableField<String> near5HourClockUnit = new ObservableField<>();
    public ObservableField<String> near5HourTemp = new ObservableField<>();
    public ObservableField<Drawable> near5HourImg = new ObservableField<>();
    public ObservableField<Drawable> id7CardDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> id7CardDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> id7CardDetailImg3 = new ObservableField<>();
    public ObservableField<Drawable> id8CardDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> id8CardDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> id8CardDetailImg3 = new ObservableField<>();
    public ObservableField<Drawable> audiImageIcon = new ObservableField<>();
    public ObservableField<Drawable> audiCardDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> audiCardDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> audiCardDetailImg3 = new ObservableField<>();
    public ObservableField<String> audiCardDetailText1 = new ObservableField<>();
    public ObservableField<String> audiCardDetailText2 = new ObservableField<>();
    public ObservableField<String> audiCardDetailText3 = new ObservableField<>();
    public ObservableField<Drawable> id8CardEditDetailImg1 = new ObservableField<>();
    public ObservableField<Drawable> id8CardEditDetailImg2 = new ObservableField<>();
    public ObservableField<Drawable> id8CardEditDetailImg3 = new ObservableField<>();
    public ObservableField<String> cardDetailText1 = new ObservableField<>();
    public ObservableField<String> cardDetailText2 = new ObservableField<>();
    public ObservableField<String> cardDetailText3 = new ObservableField<>();

    public void loadSuccess(Context context, TXZWeather txzWeather, String[] details) {
        if (context != null) {
            this.image.set(context.getResources().getDrawable(getResource(txzWeather.getPhraseID())));
            getID8MainEditResourceV2(txzWeather.getPhraseID(), context);
            getID8GsMainEditResourceV2(txzWeather.getPhraseID(), context);
            this.id8EditImage.set(context.getResources().getDrawable(getID8MainEditResource(txzWeather.getPhraseID())));
            this.PempId8Image.set(context.getResources().getDrawable(getPempID8MainResource(txzWeather.getPhraseID())));
            this.PempId8EditImage.set(context.getResources().getDrawable(getPempID8EditMainResource(txzWeather.getPhraseID())));
            this.id7AlsV2ImageIcon.set(context.getResources().getDrawable(getId7AlsV2Resource(txzWeather.getPhraseID())));
            this.romeoV2ImageIcon.set(context.getResources().getDrawable(getromeoV2Resource(txzWeather.getPhraseID())));
            this.audiImageIcon.set(context.getResources().getDrawable(getAudiResource(txzWeather.getPhraseID())));
            parseCardDetail(context, txzWeather, details);
        }
        Log.w(TAG, "phraseID : " + txzWeather.getPhraseID());
        this.city.set(txzWeather.getCity());
        this.date.set(txzWeather.getDate());
        this.dateOfWeek.set(txzWeather.getDateOfWeek());
        this.phrase.set(txzWeather.getPhrase());
        if (!UiThemeUtils.isUI_PEMP_ID8(KswApplication.appContext)) {
            this.temperUnit.set(txzWeather.getTemperUnit());
        }
        this.temperature.set(txzWeather.getTemperature());
        Log.d(TAG, "loadSuccess: " + txzWeather.getTemperature());
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
        if (hoursData.size() < 5) {
            return;
        }
        parseHourData(context, hoursData.get(0), this.near1HourClock, this.near1HourClockUnit, this.near1HourImg, this.near1HourTemp);
        parseHourData(context, hoursData.get(1), this.near2HourClock, this.near2HourClockUnit, this.near2HourImg, this.near2HourTemp);
        parseHourData(context, hoursData.get(2), this.near3HourClock, this.near3HourClockUnit, this.near3HourImg, this.near3HourTemp);
        parseHourData(context, hoursData.get(3), this.near4HourClock, this.near4HourClockUnit, this.near4HourImg, this.near4HourTemp);
        parseHourData(context, hoursData.get(4), this.near5HourClock, this.near5HourClockUnit, this.near5HourImg, this.near5HourTemp);
    }

    private void parseCardDetail(Context context, TXZWeather txzWeather, String[] details) {
        if (details == null) {
            resetCardDetail(this.id8CardDetailImg1, this.id8CardEditDetailImg1, this.id7CardDetailImg1, this.audiCardDetailImg1, this.cardDetailText1, this.audiCardDetailText1);
            resetCardDetail(this.id8CardDetailImg2, this.id8CardEditDetailImg2, this.id7CardDetailImg2, this.audiCardDetailImg2, this.cardDetailText2, this.audiCardDetailText2);
            resetCardDetail(this.id8CardDetailImg3, this.id8CardEditDetailImg3, this.id7CardDetailImg3, this.audiCardDetailImg3, this.cardDetailText3, this.audiCardDetailText3);
            return;
        }
        if (details.length < 1 || TextUtils.isEmpty(details[0])) {
            resetCardDetail(this.id8CardDetailImg1, this.id8CardEditDetailImg1, this.id7CardDetailImg1, this.audiCardDetailImg1, this.cardDetailText1, this.audiCardDetailText1);
        } else {
            Resources resources = context.getResources();
            parseCardDetail(resources, txzWeather, details[0], this.id8CardDetailImg1, this.id8CardEditDetailImg1, this.id7CardDetailImg1, this.audiCardDetailImg1, this.cardDetailText1, this.audiCardDetailText1);
        }
        if (details.length < 2 || TextUtils.isEmpty(details[1])) {
            resetCardDetail(this.id8CardDetailImg2, this.id8CardEditDetailImg2, this.id7CardDetailImg2, this.audiCardDetailImg2, this.cardDetailText2, this.audiCardDetailText2);
        } else {
            Resources resources2 = context.getResources();
            parseCardDetail(resources2, txzWeather, details[1], this.id8CardDetailImg2, this.id8CardEditDetailImg2, this.id7CardDetailImg2, this.audiCardDetailImg2, this.cardDetailText2, this.audiCardDetailText2);
        }
        if (details.length < 3 || TextUtils.isEmpty(details[2])) {
            resetCardDetail(this.id8CardDetailImg3, this.id8CardEditDetailImg3, this.id7CardDetailImg3, this.audiCardDetailImg3, this.cardDetailText3, this.audiCardDetailText3);
            return;
        }
        Resources resources3 = context.getResources();
        parseCardDetail(resources3, txzWeather, details[2], this.id8CardDetailImg3, this.id8CardEditDetailImg3, this.id7CardDetailImg3, this.audiCardDetailImg3, this.cardDetailText3, this.audiCardDetailText3);
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
                id8CardDetailImgRes = C0899R.C0900drawable.id8_main_icon_weather_wind;
                id8CardEditDetailImgRes = C0899R.C0900drawable.id8_main_edit_icon_weather_wind;
                id7CardDetailImgRes = C0899R.C0900drawable.id7_weather_icon_wind;
                audiCardDetailImgRes = C0899R.C0900drawable.audi_right_weather_wind;
                cardDetailString = txzWeather.getWindSpeed();
                break;
            case 1:
                id8CardDetailImgRes = C0899R.C0900drawable.id8_main_icon_weather_humidity;
                id8CardEditDetailImgRes = C0899R.C0900drawable.id8_main_edit_icon_weather_humidity;
                id7CardDetailImgRes = C0899R.C0900drawable.id7_weather_icon_humidity;
                audiCardDetailImgRes = C0899R.C0900drawable.audi_right_weather_humidity;
                cardDetailString = txzWeather.getHumidity();
                break;
            case 2:
                id8CardDetailImgRes = C0899R.C0900drawable.id8_main_icon_weather_visibility;
                id8CardEditDetailImgRes = C0899R.C0900drawable.id8_main_edit_icon_weather_visibility;
                id7CardDetailImgRes = C0899R.C0900drawable.id7_weather_icon_visibility;
                audiCardDetailImgRes = C0899R.C0900drawable.audi_right_weather_visibility;
                cardDetailString = txzWeather.getVisibility();
                break;
            case 3:
                if (!TextUtils.isEmpty(txzWeather.getSnowingProbability())) {
                    id8CardDetailImgRes = C0899R.C0900drawable.id8_main_icon_weather_snow;
                    id8CardEditDetailImgRes = C0899R.C0900drawable.id8_main_edit_icon_weather_snow;
                    id7CardDetailImgRes = C0899R.C0900drawable.id7_weather_icon_snow;
                    audiCardDetailImgRes = C0899R.C0900drawable.audi_right_weather_snow;
                    cardDetailString = txzWeather.getSnowingProbability();
                    break;
                } else {
                    id8CardDetailImgRes = C0899R.C0900drawable.id8_main_icon_weather_rain;
                    id8CardEditDetailImgRes = C0899R.C0900drawable.id8_main_edit_icon_weather_rain;
                    id7CardDetailImgRes = C0899R.C0900drawable.id7_weather_icon_rain;
                    audiCardDetailImgRes = C0899R.C0900drawable.audi_right_weather_rain;
                    cardDetailString = txzWeather.getRainingProbability();
                    break;
                }
            case 4:
                id8CardDetailImgRes = C0899R.C0900drawable.id8_main_icon_weather_uv;
                id8CardEditDetailImgRes = C0899R.C0900drawable.id8_main_edit_icon_weather_uv;
                id7CardDetailImgRes = C0899R.C0900drawable.id7_weather_icon_uv;
                audiCardDetailImgRes = C0899R.C0900drawable.audi_right_weather_uv;
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
        String unit;
        int hour;
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
        int resourceId = getResource(hourWeather.getPhraseID());
        hourImg.set(context.getResources().getDrawable(resourceId));
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
                message = KswApplication.appContext.getString(C0899R.string.weather_error_failed);
                break;
            case 4:
                message = KswApplication.appContext.getString(C0899R.string.weather_error_unactivated);
                break;
            case 5:
                message = KswApplication.appContext.getString(C0899R.string.weather_error_no_gps);
                break;
        }
        Log.e(TAG, "loadFailed message : " + message);
        this.errorMessage.set(message);
        this.audiCardDetailText1.set("--");
        this.audiCardDetailText2.set("--");
        this.audiCardDetailText3.set("--");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getResource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.weather_baoxue;
            case 1:
                return C0899R.C0900drawable.weather_baoyu;
            case 2:
                return C0899R.C0900drawable.weather_bingyu;
            case 3:
                return C0899R.C0900drawable.weather_dabaoyu;
            case 4:
                return C0899R.C0900drawable.weather_daxue;
            case 5:
                return C0899R.C0900drawable.weather_dayu;
            case 6:
                return C0899R.C0900drawable.weather_duoyun;
            case 7:
                return C0899R.C0900drawable.weather_duoyun_night;
            case '\b':
                return C0899R.C0900drawable.weather_fuchen;
            case '\t':
                return C0899R.C0900drawable.weather_leizhenyu;
            case '\n':
                return C0899R.C0900drawable.weather_leizhenyubanyoubingbao;
            case 11:
                return C0899R.C0900drawable.weather_mai;
            case '\f':
                return C0899R.C0900drawable.weather_qing;
            case '\r':
                return C0899R.C0900drawable.weather_qing_night;
            case 14:
                return C0899R.C0900drawable.weather_shachenbao;
            case 15:
                return C0899R.C0900drawable.weather_tedabaoyu;
            case 16:
                return C0899R.C0900drawable.weather_wu;
            case 17:
                return C0899R.C0900drawable.weather_xiaoxue;
            case 18:
                return C0899R.C0900drawable.weather_xiaoyu;
            case 19:
                return C0899R.C0900drawable.weather_yangsha;
            case 20:
                return C0899R.C0900drawable.weather_yin;
            case 21:
                return C0899R.C0900drawable.weather_yujiaxue;
            case 22:
                return C0899R.C0900drawable.weather_zhenxue;
            case 23:
                return C0899R.C0900drawable.weather_zhenxue_night;
            case 24:
                return C0899R.C0900drawable.weather_zhenyu;
            case 25:
                return C0899R.C0900drawable.weather_zhenyu_night;
            case 26:
                return C0899R.C0900drawable.weather_zhongxue;
            case 27:
                return C0899R.C0900drawable.weather_zhongyu;
            default:
                return C0899R.C0900drawable.weather_na;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getId7AlsV2Resource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.weather_id7alsv2_baoxue;
            case 1:
                return C0899R.C0900drawable.weather_id7alsv2_baoyu;
            case 2:
                return C0899R.C0900drawable.weather_id7alsv2_bingyu;
            case 3:
                return C0899R.C0900drawable.weather_id7alsv2_dabaoyu;
            case 4:
                return C0899R.C0900drawable.weather_id7alsv2_daxue;
            case 5:
                return C0899R.C0900drawable.weather_id7alsv2_dayu;
            case 6:
                return C0899R.C0900drawable.weather_id7alsv2_duoyun;
            case 7:
                return C0899R.C0900drawable.weather_id7alsv2_yejianduoyun;
            case '\b':
                return C0899R.C0900drawable.weather_id7alsv2_fuchen;
            case '\t':
                return C0899R.C0900drawable.weather_id7alsv2_leizhenyu;
            case '\n':
                return C0899R.C0900drawable.weather_id7alsv2_leizhenyubanbingbao;
            case 11:
                return C0899R.C0900drawable.weather_id7alsv2_mai;
            case '\f':
                return C0899R.C0900drawable.weather_id7alsv2_qin;
            case '\r':
                return C0899R.C0900drawable.weather_id7alsv2_yejianqin;
            case 14:
                return C0899R.C0900drawable.weather_id7alsv2_shachenbao;
            case 15:
                return C0899R.C0900drawable.weather_id7alsv2_tedabaoyu;
            case 16:
                return C0899R.C0900drawable.weather_id7alsv2_wu;
            case 17:
                return C0899R.C0900drawable.weather_id7alsv2_xiaoxue;
            case 18:
                return C0899R.C0900drawable.weather_id7alsv2_xiaoyu;
            case 19:
                return C0899R.C0900drawable.weather_id7alsv2_yangsha;
            case 20:
                return C0899R.C0900drawable.weather_id7alsv2_yin;
            case 21:
                return C0899R.C0900drawable.weather_id7alsv2_yujiaxue;
            case 22:
                return C0899R.C0900drawable.weather_id7alsv2_zhenxue;
            case 23:
                return C0899R.C0900drawable.weather_id7alsv2_yejianzhenxue;
            case 24:
                return C0899R.C0900drawable.weather_id7alsv2_zhenyu;
            case 25:
                return C0899R.C0900drawable.weather_id7alsv2_yejianzhenyu;
            case 26:
                return C0899R.C0900drawable.weather_id7alsv2_zhongxue;
            case 27:
                return C0899R.C0900drawable.weather_id7alsv2_zhongyu;
            default:
                return C0899R.C0900drawable.weather_id7alsv2_weizhi;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getromeoV2Resource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.weather_romeo_baoxue;
            case 1:
                return C0899R.C0900drawable.weather_romeo_baoyu;
            case 2:
                return C0899R.C0900drawable.weather_romeo_bingyu;
            case 3:
                return C0899R.C0900drawable.weather_romeo_dabaoyu;
            case 4:
                return C0899R.C0900drawable.weather_romeo_daxue;
            case 5:
                return C0899R.C0900drawable.weather_romeo_dayu;
            case 6:
                return C0899R.C0900drawable.weather_romeo_duoyun;
            case 7:
                return C0899R.C0900drawable.weather_romeo_yejianduoyun;
            case '\b':
                return C0899R.C0900drawable.weather_romeo_fuchen;
            case '\t':
                return C0899R.C0900drawable.weather_romeo_leizhenyu;
            case '\n':
                return C0899R.C0900drawable.weather_romeo_leizhenyubanyoubingbao;
            case 11:
                return C0899R.C0900drawable.weather_romeo_mai;
            case '\f':
                return C0899R.C0900drawable.weather_romeo_qing;
            case '\r':
                return C0899R.C0900drawable.weather_romeo_yejianqing;
            case 14:
                return C0899R.C0900drawable.weather_romeo_shachenbao;
            case 15:
                return C0899R.C0900drawable.weather_romeo_tedabaoyu;
            case 16:
                return C0899R.C0900drawable.weather_romeo_wu;
            case 17:
                return C0899R.C0900drawable.weather_romeo_xiaoxue;
            case 18:
                return C0899R.C0900drawable.weather_romeo_xiaoyu;
            case 19:
                return C0899R.C0900drawable.weather_romeo_yangsha;
            case 20:
                return C0899R.C0900drawable.weather_romeo_duoyun;
            case 21:
                return C0899R.C0900drawable.weather_romeo_yujiaxue;
            case 22:
                return C0899R.C0900drawable.weather_romeo_zhenxue;
            case 23:
                return C0899R.C0900drawable.weather_romeo_yejianzhenxue;
            case 24:
                return C0899R.C0900drawable.weather_romeo_zhenyu;
            case 25:
                return C0899R.C0900drawable.weather_romeo_yejianzhenyu;
            case 26:
                return C0899R.C0900drawable.weather_romeo_zhongxue;
            case 27:
                return C0899R.C0900drawable.weather_romeo_zhongyu;
            default:
                return C0899R.C0900drawable.weather_romeo_weizhitianqi;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getAudiResource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.weather_audi_baoxue;
            case 1:
                return C0899R.C0900drawable.weather_audi_baoyu;
            case 2:
                return C0899R.C0900drawable.weather_audi_bingyu;
            case 3:
                return C0899R.C0900drawable.weather_audi_dabaoyu;
            case 4:
                return C0899R.C0900drawable.weather_audi_daxue;
            case 5:
                return C0899R.C0900drawable.weather_audi_dayu;
            case 6:
                return C0899R.C0900drawable.weather_audi_duoyun;
            case 7:
                return C0899R.C0900drawable.weather_audi_duoyun_night;
            case '\b':
                return C0899R.C0900drawable.weather_audi_fuchen;
            case '\t':
                return C0899R.C0900drawable.weather_audi_leizhenyu;
            case '\n':
                return C0899R.C0900drawable.weather_audi_leizhenyubanyoubingbao;
            case 11:
                return C0899R.C0900drawable.weather_audi_mai;
            case '\f':
                return C0899R.C0900drawable.weather_audi_qing;
            case '\r':
                return C0899R.C0900drawable.weather_audi_qing_night;
            case 14:
                return C0899R.C0900drawable.weather_audi_shachenbao;
            case 15:
                return C0899R.C0900drawable.weather_audi_tedabaoyu;
            case 16:
                return C0899R.C0900drawable.weather_audi_wu;
            case 17:
                return C0899R.C0900drawable.weather_audi_xiaoxue;
            case 18:
                return C0899R.C0900drawable.weather_audi_xiaoyu;
            case 19:
                return C0899R.C0900drawable.weather_audi_yangsha;
            case 20:
                return C0899R.C0900drawable.weather_audi_yin;
            case 21:
                return C0899R.C0900drawable.weather_audi_yujiaxue;
            case 22:
                return C0899R.C0900drawable.weather_audi_zhenxue;
            case 23:
                return C0899R.C0900drawable.weather_audi_zhenxue_night;
            case 24:
                return C0899R.C0900drawable.weather_audi_zhenyu;
            case 25:
                return C0899R.C0900drawable.weather_audi_zhenyu_night;
            case 26:
                return C0899R.C0900drawable.weather_audi_zhongxue;
            case 27:
                return C0899R.C0900drawable.weather_audi_zhongyu;
            default:
                return C0899R.C0900drawable.weather_audi_na;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getID8MainEditResource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.id8_main_icon_weather_baoxue_edit;
            case 1:
                return C0899R.C0900drawable.id8_main_icon_weather_baoyu_edit;
            case 2:
                return C0899R.C0900drawable.id8_main_icon_weather_bingyu_edit;
            case 3:
                return C0899R.C0900drawable.id8_main_icon_weather_dabaoyu_edit;
            case 4:
                return C0899R.C0900drawable.id8_main_icon_weather_daxue_edit;
            case 5:
                return C0899R.C0900drawable.id8_main_icon_weather_dayu_edit;
            case 6:
                return C0899R.C0900drawable.id8_main_icon_weather_duoyun_edit;
            case 7:
                return C0899R.C0900drawable.id8_main_icon_weather_duoyun_night_edit;
            case '\b':
                return C0899R.C0900drawable.id8_main_icon_weather_fuchen_edit;
            case '\t':
                return C0899R.C0900drawable.id8_main_icon_weather_leizhenyu_edit;
            case '\n':
                return C0899R.C0900drawable.id8_main_icon_weather_leizhenyubanyoubingbao_edit;
            case 11:
                return C0899R.C0900drawable.id8_main_icon_weather_mai_edit;
            case '\f':
                return C0899R.C0900drawable.id8_main_icon_weather_qing_edit;
            case '\r':
                return C0899R.C0900drawable.id8_main_icon_weather_qing_night_edit;
            case 14:
                return C0899R.C0900drawable.id8_main_icon_weather_shachenbao_edit;
            case 15:
                return C0899R.C0900drawable.id8_main_icon_weather_tedabaoyu_edit;
            case 16:
                return C0899R.C0900drawable.id8_main_icon_weather_wu_edit;
            case 17:
                return C0899R.C0900drawable.id8_main_icon_weather_xiaoxue_edit;
            case 18:
                return C0899R.C0900drawable.id8_main_icon_weather_xiaoyu_edit;
            case 19:
                return C0899R.C0900drawable.id8_main_icon_weather_yangsha_edit;
            case 20:
                return C0899R.C0900drawable.id8_main_icon_weather_yin_edit;
            case 21:
                return C0899R.C0900drawable.id8_main_icon_weather_yujiaxue_edit;
            case 22:
                return C0899R.C0900drawable.id8_main_icon_weather_zhenxue_edit;
            case 23:
                return C0899R.C0900drawable.id8_main_icon_weather_zhenxue_night_edit;
            case 24:
                return C0899R.C0900drawable.id8_main_icon_weather_zhenyu_edit;
            case 25:
                return C0899R.C0900drawable.id8_main_icon_weather_zhenyu_night_edit;
            case 26:
                return C0899R.C0900drawable.id8_main_icon_weather_zhongxue_edit;
            case 27:
                return C0899R.C0900drawable.id8_main_icon_weather_zhongyu_edit;
            default:
                return C0899R.C0900drawable.id8_main_icon_weather_na_edit;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getPempID8MainResource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_blizzard;
            case 1:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_rainstorm;
            case 2:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_freezing_rain;
            case 3:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_rainstorm_l;
            case 4:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_heavy_snow;
            case 5:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_heavy_rain;
            case 6:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_cloudy;
            case 7:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_cloudy_at_night;
            case '\b':
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_dusty;
            case '\t':
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_thunderstorms;
            case '\n':
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_thunderstorms_with_hail;
            case 11:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_haze;
            case '\f':
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_sunny;
            case '\r':
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_night_sunny;
            case 14:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_sandstorm;
            case 15:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_rainstorm_xl;
            case 16:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_fog;
            case 17:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_light_snow;
            case 18:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_drizzle;
            case 19:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_blowingsand;
            case 20:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_overcast;
            case 21:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_sleet;
            case 22:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_snow_showers;
            case 23:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_snow_showers_at_night;
            case 24:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_showers;
            case 25:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_night_showers;
            case 26:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_medium_snow;
            case 27:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_moderate_rain;
            default:
                return C0899R.C0900drawable.pemp_id8_main_icon_weather_unknown;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getPempID8EditMainResource(String imageName) {
        char c;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_blizzard;
            case 1:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_rainstorm;
            case 2:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_freezing_rain;
            case 3:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_rainstorm_l;
            case 4:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_heavy_snow;
            case 5:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_heavy_rain;
            case 6:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_cloudy;
            case 7:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_night_sunny;
            case '\b':
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_dusty;
            case '\t':
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_thunderstorms;
            case '\n':
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_thunderstorms_with_hail;
            case 11:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_haze;
            case '\f':
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_sunny;
            case '\r':
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_night_sunny;
            case 14:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_sandstorm;
            case 15:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_rainstorm_xl;
            case 16:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_fog;
            case 17:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_light_snow;
            case 18:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_drizzle;
            case 19:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_blowing_sand;
            case 20:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_overcast;
            case 21:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_sleet;
            case 22:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_snow_showers;
            case 23:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_snow_showers_at_night;
            case 24:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_showers;
            case 25:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_night_showers;
            case 26:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_medium_snow;
            case 27:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_moderate_rain;
            default:
                return C0899R.C0900drawable.pemp_id8_main_edit_icon_weather_unknown;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void getID8MainEditResourceV2(String imageName, Context context) {
        char c;
        int bgDrawableId;
        int iconDrawableId;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_baoxue;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_baoxue_icon_big;
                break;
            case 1:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_baoyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_baoyu_icon_big;
                break;
            case 2:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_bingyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_bingyu_icon_big;
                break;
            case 3:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_dabaoyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_dabaoyu_icon_big;
                break;
            case 4:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_daxue;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_daxue_icon_big;
                break;
            case 5:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_dayu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_dayu_icon_big;
                break;
            case 6:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_duoyun;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_duoyun_icon_big;
                break;
            case 7:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_duoyun_night;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_duoyun_night_icon_big;
                break;
            case '\b':
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_fuchen;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_fuchen_icon_big;
                break;
            case '\t':
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_leizhenyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_leizhenyu_icon_big;
                break;
            case '\n':
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_leizhenyubanyoubingbao;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_leizhenyubanyoubingbao_icon_big;
                break;
            case 11:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_mai;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_mai_icon_big;
                break;
            case '\f':
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_qing;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_qing_icon_big;
                break;
            case '\r':
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_qing_night;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_qing_night_icon_big;
                break;
            case 14:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_shachenbao;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_shachenbao_icon_big;
                break;
            case 15:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_tedabaoyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_tedabaoyu_icon_big;
                break;
            case 16:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_wu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_wu_icon_big;
                break;
            case 17:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_xiaoxue;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_xiaoxue_icon_big;
                break;
            case 18:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_xiaoyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_xiaoyu_icon_big;
                break;
            case 19:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_yangsha;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_yangsha_icon_big;
                break;
            case 20:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_yin;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_yin_icon_big;
                break;
            case 21:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_yujiaxue;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_yujiaxue_icon_big;
                break;
            case 22:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenxue;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenxue_icon_big;
                break;
            case 23:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenxue_night;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenxue_night_icon_big;
                break;
            case 24:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenyu_icon_big;
                break;
            case 25:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenyu_night;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhenyu_night_icon_big;
                break;
            case 26:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhongxue;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhongxue_icon_big;
                break;
            case 27:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhongyu;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_zhongyu_icon_big;
                break;
            default:
                bgDrawableId = C0899R.C0900drawable.id8_main_icon_weather_na;
                iconDrawableId = C0899R.C0900drawable.id8_main_icon_weather_na_icon_big;
                break;
        }
        this.id8ImageBg.set(context.getResources().getDrawable(bgDrawableId));
        this.id8ImageIcon.set(context.getResources().getDrawable(iconDrawableId));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void getID8GsMainEditResourceV2(String imageName, Context context) {
        char c;
        int iconDrawableId;
        switch (imageName.hashCode()) {
            case -2007006149:
                if (imageName.equals("weather_yangsha.png")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case -1964169151:
                if (imageName.equals("weather_zhenxue.png")) {
                    c = 22;
                    break;
                }
                c = '\uffff';
                break;
            case -1893213074:
                if (imageName.equals("weather_tedabaoyu.png")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1762304422:
                if (imageName.equals("weather_zhenxue_night.png")) {
                    c = 23;
                    break;
                }
                c = '\uffff';
                break;
            case -1570070136:
                if (imageName.equals("weather_yujiaxue.png")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case -1522097648:
                if (imageName.equals("weather_qing_night.png")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case -1512778085:
                if (imageName.equals("weather_daxue.png")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -1461142052:
                if (imageName.equals("weather_baoyu.png")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1024703373:
                if (imageName.equals("weather_leizhenyu.png")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case -637245050:
                if (imageName.equals("weather_zhongyu.png")) {
                    c = 27;
                    break;
                }
                c = '\uffff';
                break;
            case -390972850:
                if (imageName.equals("weather_yin.png")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -337726012:
                if (imageName.equals("weather_wu.png")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -242160521:
                if (imageName.equals("weather_qing.png")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -175999878:
                if (imageName.equals("weather_duoyun.png")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 14618050:
                if (imageName.equals("weather_bingyu.png")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 264624306:
                if (imageName.equals("weather_zhenyu_night.png")) {
                    c = 25;
                    break;
                }
                c = '\uffff';
                break;
            case 365061948:
                if (imageName.equals("weather_shachenbao.png")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 393896703:
                if (imageName.equals("weather_dayu.png")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 514120659:
                if (imageName.equals("weather_duoyun_night.png")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 517883033:
                if (imageName.equals("weather_zhenyu.png")) {
                    c = 24;
                    break;
                }
                c = '\uffff';
                break;
            case 881565940:
                if (imageName.equals("weather_zhongxue.png")) {
                    c = 26;
                    break;
                }
                c = '\uffff';
                break;
            case 967042239:
                if (imageName.equals("weather_dabaoyu.png")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 1110562654:
                if (imageName.equals("weather_baoxue.png")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1457102115:
                if (imageName.equals("weather_leizhenyubanyoubingbao.png")) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1609652513:
                if (imageName.equals("weather_xiaoyu.png")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case 1610234053:
                if (imageName.equals("weather_mai.png")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1626325763:
                if (imageName.equals("weather_fuchen.png")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1815913657:
                if (imageName.equals("weather_xiaoxue.png")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_baoxue_icon_big;
                break;
            case 1:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_baoyu_icon_big;
                break;
            case 2:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_bingyu_icon_big;
                break;
            case 3:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_dabaoyu_icon_big;
                break;
            case 4:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_daxue_icon_big;
                break;
            case 5:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_dayu_icon_big;
                break;
            case 6:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_duoyun_icon_big;
                break;
            case 7:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_duoyun_night_icon_big;
                break;
            case '\b':
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_fuchen_icon_big;
                break;
            case '\t':
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_leizhenyu_icon_big;
                break;
            case '\n':
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_leizhenyubanyoubingbao_icon_big;
                break;
            case 11:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_mai_icon_big;
                break;
            case '\f':
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_qing_icon_big;
                break;
            case '\r':
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_qing_night_icon_big;
                break;
            case 14:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_shachenbao_icon_big;
                break;
            case 15:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_tedabaoyu_icon_big;
                break;
            case 16:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_wu_icon_big;
                break;
            case 17:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_xiaoxue_icon_big;
                break;
            case 18:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_xiaoyu_icon_big;
                break;
            case 19:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_yangsha_icon_big;
                break;
            case 20:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_yin_icon_big;
                break;
            case 21:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_yujiaxue_icon_big;
                break;
            case 22:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_zhenxue_icon_big;
                break;
            case 23:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_zhenxue_night_icon_big;
                break;
            case 24:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_zhenyu_icon_big;
                break;
            case 25:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_zhenyu_night_icon_big;
                break;
            case 26:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_zhongxue_icon_big;
                break;
            case 27:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_zhongyu_icon_big;
                break;
            default:
                iconDrawableId = C0899R.C0900drawable.gs_id8_main_icon_weather_na_icon_big;
                break;
        }
        this.id8GsImageBg.set(context.getResources().getDrawable(C0899R.C0900drawable.gs_id8_main_icon_weather));
        this.id8GsImageIcon.set(context.getResources().getDrawable(iconDrawableId));
    }
}
