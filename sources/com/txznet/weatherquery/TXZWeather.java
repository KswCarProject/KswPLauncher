package com.txznet.weatherquery;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b@\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BÉ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017\u0012\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017¢\u0006\u0002\u0010\u001aJ\t\u0010E\u001a\u00020\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u0010N\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017HÆ\u0003J\u0019\u0010O\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0003HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0003HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003Jó\u0001\u0010X\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u00172\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017HÆ\u0001J\u0013\u0010Y\u001a\u00020Z2\b\u0010[\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\\\u001a\u00020]HÖ\u0001J\t\u0010^\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR*\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u000e\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001c\"\u0004\b*\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010\u001eR\u001a\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010\u001eR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001c\"\u0004\b0\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010\u001eR\u001a\u0010\u000f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001c\"\u0004\b6\u0010\u001eR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u0010\u001eR\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001c\"\u0004\b:\u0010\u001eR\u001a\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001c\"\u0004\b<\u0010\u001eR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001c\"\u0004\b>\u0010\u001eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u001c\"\u0004\b@\u0010\u001eR*\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010&\"\u0004\bB\u0010(R\u001a\u0010\r\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001c\"\u0004\bD\u0010\u001e¨\u0006_"}, d2 = {"Lcom/txznet/weatherquery/TXZWeather;", "", "city", "", "date", "dateOfWeek", "phrase", "phraseID", "temperUnit", "temperature", "minTemperature", "maxTemperature", "sensibleTemperature", "windSpeed", "humidity", "rainingProbability", "snowingProbability", "atmosphericPressure", "visibility", "uvIndex", "hoursData", "Ljava/util/ArrayList;", "Lcom/txznet/weatherquery/HourWeather;", "Lkotlin/collections/ArrayList;", "weekData", "Lcom/txznet/weatherquery/WeekWeather;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getAtmosphericPressure", "()Ljava/lang/String;", "setAtmosphericPressure", "(Ljava/lang/String;)V", "getCity", "setCity", "getDate", "setDate", "getDateOfWeek", "setDateOfWeek", "getHoursData", "()Ljava/util/ArrayList;", "setHoursData", "(Ljava/util/ArrayList;)V", "getHumidity", "setHumidity", "getMaxTemperature", "setMaxTemperature", "getMinTemperature", "setMinTemperature", "getPhrase", "setPhrase", "getPhraseID", "setPhraseID", "getRainingProbability", "setRainingProbability", "getSensibleTemperature", "setSensibleTemperature", "getSnowingProbability", "setSnowingProbability", "getTemperUnit", "setTemperUnit", "getTemperature", "setTemperature", "getUvIndex", "setUvIndex", "getVisibility", "setVisibility", "getWeekData", "setWeekData", "getWindSpeed", "setWindSpeed", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "WeatherQuery_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: TXZWeather.kt */
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

    public static /* synthetic */ TXZWeather copy$default(TXZWeather tXZWeather, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, ArrayList arrayList, ArrayList arrayList2, int i, Object obj) {
        TXZWeather tXZWeather2 = tXZWeather;
        int i2 = i;
        return tXZWeather.copy((i2 & 1) != 0 ? tXZWeather2.city : str, (i2 & 2) != 0 ? tXZWeather2.date : str2, (i2 & 4) != 0 ? tXZWeather2.dateOfWeek : str3, (i2 & 8) != 0 ? tXZWeather2.phrase : str4, (i2 & 16) != 0 ? tXZWeather2.phraseID : str5, (i2 & 32) != 0 ? tXZWeather2.temperUnit : str6, (i2 & 64) != 0 ? tXZWeather2.temperature : str7, (i2 & 128) != 0 ? tXZWeather2.minTemperature : str8, (i2 & 256) != 0 ? tXZWeather2.maxTemperature : str9, (i2 & 512) != 0 ? tXZWeather2.sensibleTemperature : str10, (i2 & 1024) != 0 ? tXZWeather2.windSpeed : str11, (i2 & 2048) != 0 ? tXZWeather2.humidity : str12, (i2 & 4096) != 0 ? tXZWeather2.rainingProbability : str13, (i2 & 8192) != 0 ? tXZWeather2.snowingProbability : str14, (i2 & 16384) != 0 ? tXZWeather2.atmosphericPressure : str15, (i2 & 32768) != 0 ? tXZWeather2.visibility : str16, (i2 & 65536) != 0 ? tXZWeather2.uvIndex : str17, (i2 & 131072) != 0 ? tXZWeather2.hoursData : arrayList, (i2 & 262144) != 0 ? tXZWeather2.weekData : arrayList2);
    }

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

    public final TXZWeather copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, ArrayList<HourWeather> arrayList, ArrayList<WeekWeather> arrayList2) {
        String str18 = str;
        Intrinsics.checkNotNullParameter(str18, "city");
        Intrinsics.checkNotNullParameter(str3, "dateOfWeek");
        Intrinsics.checkNotNullParameter(str4, "phrase");
        Intrinsics.checkNotNullParameter(str5, "phraseID");
        Intrinsics.checkNotNullParameter(str6, "temperUnit");
        Intrinsics.checkNotNullParameter(str7, "temperature");
        Intrinsics.checkNotNullParameter(str8, "minTemperature");
        Intrinsics.checkNotNullParameter(str9, "maxTemperature");
        Intrinsics.checkNotNullParameter(str11, "windSpeed");
        Intrinsics.checkNotNullParameter(str12, "humidity");
        Intrinsics.checkNotNullParameter(str13, "rainingProbability");
        Intrinsics.checkNotNullParameter(arrayList, "hoursData");
        Intrinsics.checkNotNullParameter(arrayList2, "weekData");
        return new TXZWeather(str18, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, arrayList, arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TXZWeather)) {
            return false;
        }
        TXZWeather tXZWeather = (TXZWeather) obj;
        return Intrinsics.areEqual((Object) this.city, (Object) tXZWeather.city) && Intrinsics.areEqual((Object) this.date, (Object) tXZWeather.date) && Intrinsics.areEqual((Object) this.dateOfWeek, (Object) tXZWeather.dateOfWeek) && Intrinsics.areEqual((Object) this.phrase, (Object) tXZWeather.phrase) && Intrinsics.areEqual((Object) this.phraseID, (Object) tXZWeather.phraseID) && Intrinsics.areEqual((Object) this.temperUnit, (Object) tXZWeather.temperUnit) && Intrinsics.areEqual((Object) this.temperature, (Object) tXZWeather.temperature) && Intrinsics.areEqual((Object) this.minTemperature, (Object) tXZWeather.minTemperature) && Intrinsics.areEqual((Object) this.maxTemperature, (Object) tXZWeather.maxTemperature) && Intrinsics.areEqual((Object) this.sensibleTemperature, (Object) tXZWeather.sensibleTemperature) && Intrinsics.areEqual((Object) this.windSpeed, (Object) tXZWeather.windSpeed) && Intrinsics.areEqual((Object) this.humidity, (Object) tXZWeather.humidity) && Intrinsics.areEqual((Object) this.rainingProbability, (Object) tXZWeather.rainingProbability) && Intrinsics.areEqual((Object) this.snowingProbability, (Object) tXZWeather.snowingProbability) && Intrinsics.areEqual((Object) this.atmosphericPressure, (Object) tXZWeather.atmosphericPressure) && Intrinsics.areEqual((Object) this.visibility, (Object) tXZWeather.visibility) && Intrinsics.areEqual((Object) this.uvIndex, (Object) tXZWeather.uvIndex) && Intrinsics.areEqual((Object) this.hoursData, (Object) tXZWeather.hoursData) && Intrinsics.areEqual((Object) this.weekData, (Object) tXZWeather.weekData);
    }

    public int hashCode() {
        int hashCode = this.city.hashCode() * 31;
        String str = this.date;
        int i = 0;
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
        if (str6 != null) {
            i = str6.hashCode();
        }
        return ((((hashCode6 + i) * 31) + this.hoursData.hashCode()) * 31) + this.weekData.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TXZWeather(city=").append(this.city).append(", date=").append(this.date).append(", dateOfWeek=").append(this.dateOfWeek).append(", phrase=").append(this.phrase).append(", phraseID=").append(this.phraseID).append(", temperUnit=").append(this.temperUnit).append(", temperature=").append(this.temperature).append(", minTemperature=").append(this.minTemperature).append(", maxTemperature=").append(this.maxTemperature).append(", sensibleTemperature=").append(this.sensibleTemperature).append(", windSpeed=").append(this.windSpeed).append(", humidity=");
        sb.append(this.humidity).append(", rainingProbability=").append(this.rainingProbability).append(", snowingProbability=").append(this.snowingProbability).append(", atmosphericPressure=").append(this.atmosphericPressure).append(", visibility=").append(this.visibility).append(", uvIndex=").append(this.uvIndex).append(", hoursData=").append(this.hoursData).append(", weekData=").append(this.weekData).append(')');
        return sb.toString();
    }

    public TXZWeather(String city2, String date2, String dateOfWeek2, String phrase2, String phraseID2, String temperUnit2, String temperature2, String minTemperature2, String maxTemperature2, String sensibleTemperature2, String windSpeed2, String humidity2, String rainingProbability2, String snowingProbability2, String atmosphericPressure2, String visibility2, String uvIndex2, ArrayList<HourWeather> hoursData2, ArrayList<WeekWeather> weekData2) {
        String str = city2;
        String str2 = dateOfWeek2;
        String str3 = phrase2;
        String str4 = phraseID2;
        String str5 = temperUnit2;
        String str6 = temperature2;
        String str7 = minTemperature2;
        String str8 = maxTemperature2;
        String str9 = windSpeed2;
        String str10 = humidity2;
        String str11 = rainingProbability2;
        ArrayList<HourWeather> arrayList = hoursData2;
        ArrayList<WeekWeather> arrayList2 = weekData2;
        Intrinsics.checkNotNullParameter(str, "city");
        Intrinsics.checkNotNullParameter(str2, "dateOfWeek");
        Intrinsics.checkNotNullParameter(str3, "phrase");
        Intrinsics.checkNotNullParameter(str4, "phraseID");
        Intrinsics.checkNotNullParameter(str5, "temperUnit");
        Intrinsics.checkNotNullParameter(str6, "temperature");
        Intrinsics.checkNotNullParameter(str7, "minTemperature");
        Intrinsics.checkNotNullParameter(str8, "maxTemperature");
        Intrinsics.checkNotNullParameter(str9, "windSpeed");
        Intrinsics.checkNotNullParameter(str10, "humidity");
        Intrinsics.checkNotNullParameter(str11, "rainingProbability");
        Intrinsics.checkNotNullParameter(arrayList, "hoursData");
        Intrinsics.checkNotNullParameter(arrayList2, "weekData");
        this.city = str;
        this.date = date2;
        this.dateOfWeek = str2;
        this.phrase = str3;
        this.phraseID = str4;
        this.temperUnit = str5;
        this.temperature = str6;
        this.minTemperature = str7;
        this.maxTemperature = str8;
        this.sensibleTemperature = sensibleTemperature2;
        this.windSpeed = str9;
        this.humidity = str10;
        this.rainingProbability = str11;
        this.snowingProbability = snowingProbability2;
        this.atmosphericPressure = atmosphericPressure2;
        this.visibility = visibility2;
        this.uvIndex = uvIndex2;
        this.hoursData = arrayList;
        this.weekData = arrayList2;
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
