package com.google.zxing.oned.rss;

/* loaded from: classes.dex */
public class DataCharacter {
    private final int checksumPortion;
    private final int value;

    public DataCharacter(int value, int checksumPortion) {
        this.value = value;
        this.checksumPortion = checksumPortion;
    }

    public final int getValue() {
        return this.value;
    }

    public final int getChecksumPortion() {
        return this.checksumPortion;
    }

    public final String toString() {
        return this.value + "(" + this.checksumPortion + ')';
    }

    public final boolean equals(Object o) {
        if (o instanceof DataCharacter) {
            DataCharacter that = (DataCharacter) o;
            return this.value == that.value && this.checksumPortion == that.checksumPortion;
        }
        return false;
    }

    public final int hashCode() {
        return this.value ^ this.checksumPortion;
    }
}
