package com.txznet.weatherquery;

import com.ibm.icu.text.PluralRules;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeekWeather.kt */
@Metadata(m25d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f\u00a8\u0006!"}, m24d2 = {"Lcom/txznet/weatherquery/WeekWeather;", "", "dayOfWeek", "", "minTemperature", "maxTemperature", "phrase", "phraseID", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDayOfWeek", "()Ljava/lang/String;", "setDayOfWeek", "(Ljava/lang/String;)V", "getMaxTemperature", "setMaxTemperature", "getMinTemperature", "setMinTemperature", "getPhrase", "setPhrase", "getPhraseID", "setPhraseID", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", PluralRules.KEYWORD_OTHER, "hashCode", "", "toString", "WeatherQuery_release"}, m23k = 1, m22mv = {1, 6, 0}, m20xi = 48)
/* loaded from: classes.dex */
public final class WeekWeather {
    private String dayOfWeek;
    private String maxTemperature;
    private String minTemperature;
    private String phrase;
    private String phraseID;

    public static /* synthetic */ WeekWeather copy$default(WeekWeather weekWeather, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = weekWeather.dayOfWeek;
        }
        if ((i & 2) != 0) {
            str2 = weekWeather.minTemperature;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = weekWeather.maxTemperature;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = weekWeather.phrase;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = weekWeather.phraseID;
        }
        return weekWeather.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.dayOfWeek;
    }

    public final String component2() {
        return this.minTemperature;
    }

    public final String component3() {
        return this.maxTemperature;
    }

    public final String component4() {
        return this.phrase;
    }

    public final String component5() {
        return this.phraseID;
    }

    public final WeekWeather copy(String dayOfWeek, String minTemperature, String maxTemperature, String phrase, String phraseID) {
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        Intrinsics.checkNotNullParameter(minTemperature, "minTemperature");
        Intrinsics.checkNotNullParameter(maxTemperature, "maxTemperature");
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        Intrinsics.checkNotNullParameter(phraseID, "phraseID");
        return new WeekWeather(dayOfWeek, minTemperature, maxTemperature, phrase, phraseID);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WeekWeather) {
            WeekWeather weekWeather = (WeekWeather) obj;
            return Intrinsics.areEqual(this.dayOfWeek, weekWeather.dayOfWeek) && Intrinsics.areEqual(this.minTemperature, weekWeather.minTemperature) && Intrinsics.areEqual(this.maxTemperature, weekWeather.maxTemperature) && Intrinsics.areEqual(this.phrase, weekWeather.phrase) && Intrinsics.areEqual(this.phraseID, weekWeather.phraseID);
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.dayOfWeek.hashCode() * 31) + this.minTemperature.hashCode()) * 31) + this.maxTemperature.hashCode()) * 31) + this.phrase.hashCode()) * 31) + this.phraseID.hashCode();
    }

    public String toString() {
        return "WeekWeather(dayOfWeek=" + this.dayOfWeek + ", minTemperature=" + this.minTemperature + ", maxTemperature=" + this.maxTemperature + ", phrase=" + this.phrase + ", phraseID=" + this.phraseID + ')';
    }

    public WeekWeather(String dayOfWeek, String minTemperature, String maxTemperature, String phrase, String phraseID) {
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        Intrinsics.checkNotNullParameter(minTemperature, "minTemperature");
        Intrinsics.checkNotNullParameter(maxTemperature, "maxTemperature");
        Intrinsics.checkNotNullParameter(phrase, "phrase");
        Intrinsics.checkNotNullParameter(phraseID, "phraseID");
        this.dayOfWeek = dayOfWeek;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.phrase = phrase;
        this.phraseID = phraseID;
    }

    public final String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public final void setDayOfWeek(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dayOfWeek = str;
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
