package com.txznet.weatherquery;

import com.ibm.icu.text.PluralRules;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TXZWeather.kt */
@Metadata(m25d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b@\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00c9\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017\u0012\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017\u00a2\u0006\u0002\u0010\u001aJ\t\u0010E\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010G\u001a\u00020\u0003H\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0019\u0010N\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017H\u00c6\u0003J\u0019\u0010O\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\t\u0010R\u001a\u00020\u0003H\u00c6\u0003J\t\u0010S\u001a\u00020\u0003H\u00c6\u0003J\t\u0010T\u001a\u00020\u0003H\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u0003H\u00c6\u0003J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\u00f3\u0001\u0010X\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00172\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017H\u00c6\u0001J\u0013\u0010Y\u001a\u00020Z2\b\u0010[\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\\\u001a\u00020]H\u00d6\u0001J\t\u0010^\u001a\u00020\u0003H\u00d6\u0001R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR*\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010\u001eR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010\u001eR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001c\"\u0004\b0\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010\u001eR\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001c\"\u0004\b6\u0010\u001eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u0010\u001eR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001c\"\u0004\b:\u0010\u001eR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001c\"\u0004\b<\u0010\u001eR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001c\"\u0004\b>\u0010\u001eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u001c\"\u0004\b@\u0010\u001eR*\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010&\"\u0004\bB\u0010(R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001c\"\u0004\bD\u0010\u001e\u00a8\u0006_"}, m24d2 = {"Lcom/txznet/weatherquery/TXZWeather;", "", "city", "", "date", "dateOfWeek", "phrase", "phraseID", "temperUnit", "temperature", "minTemperature", "maxTemperature", "sensibleTemperature", "windSpeed", "humidity", "rainingProbability", "snowingProbability", "atmosphericPressure", "visibility", "uvIndex", "hoursData", "Ljava/util/ArrayList;", "Lcom/txznet/weatherquery/HourWeather;", "Lkotlin/collections/ArrayList;", "weekData", "Lcom/txznet/weatherquery/WeekWeather;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getAtmosphericPressure", "()Ljava/lang/String;", "setAtmosphericPressure", "(Ljava/lang/String;)V", "getCity", "setCity", "getDate", "setDate", "getDateOfWeek", "setDateOfWeek", "getHoursData", "()Ljava/util/ArrayList;", "setHoursData", "(Ljava/util/ArrayList;)V", "getHumidity", "setHumidity", "getMaxTemperature", "setMaxTemperature", "getMinTemperature", "setMinTemperature", "getPhrase", "setPhrase", "getPhraseID", "setPhraseID", "getRainingProbability", "setRainingProbability", "getSensibleTemperature", "setSensibleTemperature", "getSnowingProbability", "setSnowingProbability", "getTemperUnit", "setTemperUnit", "getTemperature", "setTemperature", "getUvIndex", "setUvIndex", "getVisibility", "setVisibility", "getWeekData", "setWeekData", "getWindSpeed", "setWindSpeed", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", PluralRules.KEYWORD_OTHER, "hashCode", "", "toString", "WeatherQuery_release"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
public final class TXZWeather {
    private String atmosphericPressure;
    private String city;
    private String date;
    private String dateOfWeek;
    private ArrayList<HourWeather> hoursData;
    private String humidity;
    private String maxTemperature;
    private String minTemperature;
    private String phrase;
    private String phraseID;
    private String rainingProbability;
    private String sensibleTemperature;
    private String snowingProbability;
    private String temperUnit;
    private String temperature;
    private String uvIndex;
    private String visibility;
    private ArrayList<WeekWeather> weekData;
    private String windSpeed;

    public final String component1() {
        return this.city;
    }

    public final String component10() {
        return this.sensibleTemperature;
    }

    public final String component11() {
        return this.windSpeed;
    }

    public final String component12() {
        return this.humidity;
    }

    public final String component13() {
        return this.rainingProbability;
    }

    public final String component14() {
        return this.snowingProbability;
    }

    public final String component15() {
        return this.atmosphericPressure;
    }

    public final String component16() {
        return this.visibility;
    }

    public final String component17() {
        return this.uvIndex;
    }

    public final ArrayList<HourWeather> component18() {
        return this.hoursData;
    }

    public final ArrayList<WeekWeather> component19() {
        return this.weekData;
    }

    public final String component2() {
        return this.date;
    }

    public final String component3() {
        return this.dateOfWeek;
    }

    public final String component4() {
        return this.phrase;
    }

    public final String component5() {
        return this.phraseID;
    }

    public final String component6() {
        return this.temperUnit;
    }

    public final String component7() {
        return this.temperature;
    }

    public final String component8() {
        return this.minTemperature;
    }

    public final String component9() {
        return this.maxTemperature;
    }

    public final TXZWeather copy(String city, String str, String dateOfWeek, String phrase, String phraseID, String temperUnit, String temperature, String minTemperature, String maxTemperature, String str2, String windSpeed, String humidity, String rainingProbability, String str3, String str4, String str5, String str6, ArrayList<HourWeather> hoursData, ArrayList<WeekWeather> weekData) {
        Intrinsics.checkNotNullParameter(city, "city");
        Intrinsics.checkNotNullParameter(dateOfWeek, "dateOfWeek");
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        Intrinsics.checkNotNullParameter(phraseID, "phraseID");
        Intrinsics.checkNotNullParameter(temperUnit, "temperUnit");
        Intrinsics.checkNotNullParameter(temperature, "temperature");
        Intrinsics.checkNotNullParameter(minTemperature, "minTemperature");
        Intrinsics.checkNotNullParameter(maxTemperature, "maxTemperature");
        Intrinsics.checkNotNullParameter(windSpeed, "windSpeed");
        Intrinsics.checkNotNullParameter(humidity, "humidity");
        Intrinsics.checkNotNullParameter(rainingProbability, "rainingProbability");
        Intrinsics.checkNotNullParameter(hoursData, "hoursData");
        Intrinsics.checkNotNullParameter(weekData, "weekData");
        return new TXZWeather(city, str, dateOfWeek, phrase, phraseID, temperUnit, temperature, minTemperature, maxTemperature, str2, windSpeed, humidity, rainingProbability, str3, str4, str5, str6, hoursData, weekData);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TXZWeather) {
            TXZWeather tXZWeather = (TXZWeather) obj;
            return Intrinsics.areEqual(this.city, tXZWeather.city) && Intrinsics.areEqual(this.date, tXZWeather.date) && Intrinsics.areEqual(this.dateOfWeek, tXZWeather.dateOfWeek) && Intrinsics.areEqual(this.phrase, tXZWeather.phrase) && Intrinsics.areEqual(this.phraseID, tXZWeather.phraseID) && Intrinsics.areEqual(this.temperUnit, tXZWeather.temperUnit) && Intrinsics.areEqual(this.temperature, tXZWeather.temperature) && Intrinsics.areEqual(this.minTemperature, tXZWeather.minTemperature) && Intrinsics.areEqual(this.maxTemperature, tXZWeather.maxTemperature) && Intrinsics.areEqual(this.sensibleTemperature, tXZWeather.sensibleTemperature) && Intrinsics.areEqual(this.windSpeed, tXZWeather.windSpeed) && Intrinsics.areEqual(this.humidity, tXZWeather.humidity) && Intrinsics.areEqual(this.rainingProbability, tXZWeather.rainingProbability) && Intrinsics.areEqual(this.snowingProbability, tXZWeather.snowingProbability) && Intrinsics.areEqual(this.atmosphericPressure, tXZWeather.atmosphericPressure) && Intrinsics.areEqual(this.visibility, tXZWeather.visibility) && Intrinsics.areEqual(this.uvIndex, tXZWeather.uvIndex) && Intrinsics.areEqual(this.hoursData, tXZWeather.hoursData) && Intrinsics.areEqual(this.weekData, tXZWeather.weekData);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.city.hashCode() * 31;
        String str = this.date;
        int hashCode2 = (((((((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.dateOfWeek.hashCode()) * 31) + this.phrase.hashCode()) * 31) + this.phraseID.hashCode()) * 31) + this.temperUnit.hashCode()) * 31) + this.temperature.hashCode()) * 31) + this.minTemperature.hashCode()) * 31) + this.maxTemperature.hashCode()) * 31;
        String str2 = this.sensibleTemperature;
        int hashCode3 = (((((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.windSpeed.hashCode()) * 31) + this.humidity.hashCode()) * 31) + this.rainingProbability.hashCode()) * 31;
        String str3 = this.snowingProbability;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.atmosphericPressure;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.visibility;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.uvIndex;
        return ((((hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.hoursData.hashCode()) * 31) + this.weekData.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TXZWeather(city=").append(this.city).append(", date=").append(this.date).append(", dateOfWeek=").append(this.dateOfWeek).append(", phrase=").append(this.phrase).append(", phraseID=").append(this.phraseID).append(", temperUnit=").append(this.temperUnit).append(", temperature=").append(this.temperature).append(", minTemperature=").append(this.minTemperature).append(", maxTemperature=").append(this.maxTemperature).append(", sensibleTemperature=").append(this.sensibleTemperature).append(", windSpeed=").append(this.windSpeed).append(", humidity=");
        sb.append(this.humidity).append(", rainingProbability=").append(this.rainingProbability).append(", snowingProbability=").append(this.snowingProbability).append(", atmosphericPressure=").append(this.atmosphericPressure).append(", visibility=").append(this.visibility).append(", uvIndex=").append(this.uvIndex).append(", hoursData=").append(this.hoursData).append(", weekData=").append(this.weekData).append(')');
        return sb.toString();
    }

    public TXZWeather(String city, String date, String dateOfWeek, String phrase, String phraseID, String temperUnit, String temperature, String minTemperature, String maxTemperature, String sensibleTemperature, String windSpeed, String humidity, String rainingProbability, String snowingProbability, String atmosphericPressure, String visibility, String uvIndex, ArrayList<HourWeather> hoursData, ArrayList<WeekWeather> weekData) {
        Intrinsics.checkNotNullParameter(city, "city");
        Intrinsics.checkNotNullParameter(dateOfWeek, "dateOfWeek");
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        Intrinsics.checkNotNullParameter(phraseID, "phraseID");
        Intrinsics.checkNotNullParameter(temperUnit, "temperUnit");
        Intrinsics.checkNotNullParameter(temperature, "temperature");
        Intrinsics.checkNotNullParameter(minTemperature, "minTemperature");
        Intrinsics.checkNotNullParameter(maxTemperature, "maxTemperature");
        Intrinsics.checkNotNullParameter(windSpeed, "windSpeed");
        Intrinsics.checkNotNullParameter(humidity, "humidity");
        Intrinsics.checkNotNullParameter(rainingProbability, "rainingProbability");
        Intrinsics.checkNotNullParameter(hoursData, "hoursData");
        Intrinsics.checkNotNullParameter(weekData, "weekData");
        this.city = city;
        this.date = date;
        this.dateOfWeek = dateOfWeek;
        this.phrase = phrase;
        this.phraseID = phraseID;
        this.temperUnit = temperUnit;
        this.temperature = temperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.sensibleTemperature = sensibleTemperature;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.rainingProbability = rainingProbability;
        this.snowingProbability = snowingProbability;
        this.atmosphericPressure = atmosphericPressure;
        this.visibility = visibility;
        this.uvIndex = uvIndex;
        this.hoursData = hoursData;
        this.weekData = weekData;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.city = str;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        this.date = str;
    }

    public final String getDateOfWeek() {
        return this.dateOfWeek;
    }

    public final void setDateOfWeek(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dateOfWeek = str;
    }

    public final String getPhrase() {
        return this.phrase;
    }

    public final void setPhrase(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phrase = str;
    }

    public final String getPhraseID() {
        return this.phraseID;
    }

    public final void setPhraseID(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phraseID = str;
    }

    public final String getTemperUnit() {
        return this.temperUnit;
    }

    public final void setTemperUnit(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.temperUnit = str;
    }

    public final String getTemperature() {
        return this.temperature;
    }

    public final void setTemperature(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.temperature = str;
    }

    public final String getMinTemperature() {
        return this.minTemperature;
    }

    public final void setMinTemperature(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.minTemperature = str;
    }

    public final String getMaxTemperature() {
        return this.maxTemperature;
    }

    public final void setMaxTemperature(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.maxTemperature = str;
    }

    public final String getSensibleTemperature() {
        return this.sensibleTemperature;
    }

    public final void setSensibleTemperature(String str) {
        this.sensibleTemperature = str;
    }

    public final String getWindSpeed() {
        return this.windSpeed;
    }

    public final void setWindSpeed(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.windSpeed = str;
    }

    public final String getHumidity() {
        return this.humidity;
    }

    public final void setHumidity(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.humidity = str;
    }

    public final String getRainingProbability() {
        return this.rainingProbability;
    }

    public final void setRainingProbability(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.rainingProbability = str;
    }

    public final String getSnowingProbability() {
        return this.snowingProbability;
    }

    public final void setSnowingProbability(String str) {
        this.snowingProbability = str;
    }

    public final String getAtmosphericPressure() {
        return this.atmosphericPressure;
    }

    public final void setAtmosphericPressure(String str) {
        this.atmosphericPressure = str;
    }

    public final String getVisibility() {
        return this.visibility;
    }

    public final void setVisibility(String str) {
        this.visibility = str;
    }

    public final String getUvIndex() {
        return this.uvIndex;
    }

    public final void setUvIndex(String str) {
        this.uvIndex = str;
    }

    public final ArrayList<HourWeather> getHoursData() {
        return this.hoursData;
    }

    public final void setHoursData(ArrayList<HourWeather> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.hoursData = arrayList;
    }

    public final ArrayList<WeekWeather> getWeekData() {
        return this.weekData;
    }

    public final void setWeekData(ArrayList<WeekWeather> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.weekData = arrayList;
    }
}
