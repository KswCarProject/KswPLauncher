package com.txznet.weatherquery;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/txznet/weatherquery/HourWeather;", "", "time", "", "temperature", "phrase", "phraseID", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPhrase", "()Ljava/lang/String;", "setPhrase", "(Ljava/lang/String;)V", "getPhraseID", "setPhraseID", "getTemperature", "setTemperature", "getTime", "setTime", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "WeatherQuery_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: HourWeather.kt */
public final class HourWeather {
    private String phrase;
    private String phraseID;
    private String temperature;
    private String time;

    public static /* synthetic */ HourWeather copy$default(HourWeather hourWeather, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hourWeather.time;
        }
        if ((i & 2) != 0) {
            str2 = hourWeather.temperature;
        }
        if ((i & 4) != 0) {
            str3 = hourWeather.phrase;
        }
        if ((i & 8) != 0) {
            str4 = hourWeather.phraseID;
        }
        return hourWeather.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.time;
    }

    public final String component2() {
        return this.temperature;
    }

    public final String component3() {
        return this.phrase;
    }

    public final String component4() {
        return this.phraseID;
    }

    public final HourWeather copy(String str, String str2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "time");
        Intrinsics.checkNotNullParameter(str2, "temperature");
        Intrinsics.checkNotNullParameter(str3, "phrase");
        Intrinsics.checkNotNullParameter(str4, "phraseID");
        return new HourWeather(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HourWeather)) {
            return false;
        }
        HourWeather hourWeather = (HourWeather) obj;
        return Intrinsics.areEqual((Object) this.time, (Object) hourWeather.time) && Intrinsics.areEqual((Object) this.temperature, (Object) hourWeather.temperature) && Intrinsics.areEqual((Object) this.phrase, (Object) hourWeather.phrase) && Intrinsics.areEqual((Object) this.phraseID, (Object) hourWeather.phraseID);
    }

    public int hashCode() {
        return (((((this.time.hashCode() * 31) + this.temperature.hashCode()) * 31) + this.phrase.hashCode()) * 31) + this.phraseID.hashCode();
    }

    public String toString() {
        return "HourWeather(time=" + this.time + ", temperature=" + this.temperature + ", phrase=" + this.phrase + ", phraseID=" + this.phraseID + ')';
    }

    public HourWeather(String time2, String temperature2, String phrase2, String phraseID2) {
        Intrinsics.checkNotNullParameter(time2, "time");
        Intrinsics.checkNotNullParameter(temperature2, "temperature");
        Intrinsics.checkNotNullParameter(phrase2, "phrase");
        Intrinsics.checkNotNullParameter(phraseID2, "phraseID");
        this.time = time2;
        this.temperature = temperature2;
        this.phrase = phrase2;
        this.phraseID = phraseID2;
    }

    public final String getTime() {
        return this.time;
    }

    public final void setTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.time = str;
    }

    public final String getTemperature() {
        return this.temperature;
    }

    public final void setTemperature(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.temperature = str;
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
}
