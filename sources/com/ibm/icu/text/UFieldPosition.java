package com.ibm.icu.text;

import java.text.FieldPosition;
import java.text.Format;

@Deprecated
/* loaded from: classes.dex */
public class UFieldPosition extends FieldPosition {
    private int countVisibleFractionDigits;
    private long fractionDigits;

    @Deprecated
    public UFieldPosition() {
        super(-1);
        this.countVisibleFractionDigits = -1;
        this.fractionDigits = 0L;
    }

    @Deprecated
    public UFieldPosition(int field) {
        super(field);
        this.countVisibleFractionDigits = -1;
        this.fractionDigits = 0L;
    }

    @Deprecated
    public UFieldPosition(Format.Field attribute, int fieldID) {
        super(attribute, fieldID);
        this.countVisibleFractionDigits = -1;
        this.fractionDigits = 0L;
    }

    @Deprecated
    public UFieldPosition(Format.Field attribute) {
        super(attribute);
        this.countVisibleFractionDigits = -1;
        this.fractionDigits = 0L;
    }

    @Deprecated
    public void setFractionDigits(int countVisibleFractionDigits, long fractionDigits) {
        this.countVisibleFractionDigits = countVisibleFractionDigits;
        this.fractionDigits = fractionDigits;
    }

    @Deprecated
    public int getCountVisibleFractionDigits() {
        return this.countVisibleFractionDigits;
    }

    @Deprecated
    public long getFractionDigits() {
        return this.fractionDigits;
    }
}
