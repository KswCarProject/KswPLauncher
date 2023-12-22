package com.google.zxing.pdf417.decoder.p005ec;

import com.google.zxing.pdf417.PDF417Common;

/* renamed from: com.google.zxing.pdf417.decoder.ec.ModulusGF */
/* loaded from: classes.dex */
public final class ModulusGF {
    public static final ModulusGF PDF417_GF = new ModulusGF(PDF417Common.NUMBER_OF_CODEWORDS, 3);
    private final int[] expTable;
    private final int[] logTable;
    private final int modulus;
    private final ModulusPoly one;
    private final ModulusPoly zero;

    private ModulusGF(int modulus, int generator) {
        this.modulus = modulus;
        this.expTable = new int[modulus];
        this.logTable = new int[modulus];
        int x = 1;
        for (int i = 0; i < modulus; i++) {
            this.expTable[i] = x;
            x = (x * generator) % modulus;
        }
        for (int i2 = 0; i2 < modulus - 1; i2++) {
            this.logTable[this.expTable[i2]] = i2;
        }
        this.zero = new ModulusPoly(this, new int[]{0});
        this.one = new ModulusPoly(this, new int[]{1});
    }

    ModulusPoly getZero() {
        return this.zero;
    }

    ModulusPoly getOne() {
        return this.one;
    }

    ModulusPoly buildMonomial(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        }
        if (coefficient == 0) {
            return this.zero;
        }
        int[] coefficients = new int[degree + 1];
        coefficients[0] = coefficient;
        return new ModulusPoly(this, coefficients);
    }

    int add(int a, int b) {
        return (a + b) % this.modulus;
    }

    int subtract(int a, int b) {
        int i = this.modulus;
        return ((i + a) - b) % i;
    }

    int exp(int a) {
        return this.expTable[a];
    }

    int log(int a) {
        if (a == 0) {
            throw new IllegalArgumentException();
        }
        return this.logTable[a];
    }

    int inverse(int a) {
        if (a == 0) {
            throw new ArithmeticException();
        }
        return this.expTable[(this.modulus - this.logTable[a]) - 1];
    }

    int multiply(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int[] iArr = this.expTable;
        int[] iArr2 = this.logTable;
        return iArr[(iArr2[a] + iArr2[b]) % (this.modulus - 1)];
    }

    int getSize() {
        return this.modulus;
    }
}
