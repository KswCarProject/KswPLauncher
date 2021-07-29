package com.google.zxing.pdf417.decoder.ec;

final class ModulusPoly {
    private final int[] coefficients;
    private final ModulusGF field;

    ModulusPoly(ModulusGF field2, int[] coefficients2) {
        if (coefficients2.length != 0) {
            this.field = field2;
            int length = coefficients2.length;
            int coefficientsLength = length;
            if (length <= 1 || coefficients2[0] != 0) {
                this.coefficients = coefficients2;
                return;
            }
            int firstNonZero = 1;
            while (firstNonZero < coefficientsLength && coefficients2[firstNonZero] == 0) {
                firstNonZero++;
            }
            if (firstNonZero == coefficientsLength) {
                this.coefficients = new int[]{0};
                return;
            }
            int[] iArr = new int[(coefficientsLength - firstNonZero)];
            this.coefficients = iArr;
            System.arraycopy(coefficients2, firstNonZero, iArr, 0, iArr.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int[] getCoefficients() {
        return this.coefficients;
    }

    /* access modifiers changed from: package-private */
    public int getDegree() {
        return this.coefficients.length - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    /* access modifiers changed from: package-private */
    public int getCoefficient(int degree) {
        int[] iArr = this.coefficients;
        return iArr[(iArr.length - 1) - degree];
    }

    /* access modifiers changed from: package-private */
    public int evaluateAt(int a) {
        if (a == 0) {
            return getCoefficient(0);
        }
        if (a == 1) {
            int result = 0;
            for (int coefficient : this.coefficients) {
                result = this.field.add(result, coefficient);
            }
            return result;
        }
        int[] iArr = this.coefficients;
        int result2 = iArr[0];
        int size = iArr.length;
        for (int i = 1; i < size; i++) {
            ModulusGF modulusGF = this.field;
            result2 = modulusGF.add(modulusGF.multiply(a, result2), this.coefficients[i]);
        }
        return result2;
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly add(ModulusPoly other) {
        if (!this.field.equals(other.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (isZero()) {
            return other;
        } else {
            if (other.isZero()) {
                return this;
            }
            int[] smallerCoefficients = this.coefficients;
            int[] largerCoefficients = other.coefficients;
            if (smallerCoefficients.length > largerCoefficients.length) {
                int[] temp = smallerCoefficients;
                smallerCoefficients = largerCoefficients;
                largerCoefficients = temp;
            }
            int[] sumDiff = new int[largerCoefficients.length];
            int lengthDiff = largerCoefficients.length - smallerCoefficients.length;
            System.arraycopy(largerCoefficients, 0, sumDiff, 0, lengthDiff);
            for (int i = lengthDiff; i < largerCoefficients.length; i++) {
                sumDiff[i] = this.field.add(smallerCoefficients[i - lengthDiff], largerCoefficients[i]);
            }
            return new ModulusPoly(this.field, sumDiff);
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly subtract(ModulusPoly other) {
        if (!this.field.equals(other.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (other.isZero()) {
            return this;
        } else {
            return add(other.negative());
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly multiply(ModulusPoly other) {
        if (!this.field.equals(other.field)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (isZero() || other.isZero()) {
            return this.field.getZero();
        } else {
            int[] iArr = this.coefficients;
            int[] aCoefficients = iArr;
            int aLength = iArr.length;
            int[] iArr2 = other.coefficients;
            int[] bCoefficients = iArr2;
            int bLength = iArr2.length;
            int[] product = new int[((aLength + bLength) - 1)];
            for (int i = 0; i < aLength; i++) {
                int aCoeff = aCoefficients[i];
                for (int j = 0; j < bLength; j++) {
                    ModulusGF modulusGF = this.field;
                    product[i + j] = modulusGF.add(product[i + j], modulusGF.multiply(aCoeff, bCoefficients[j]));
                }
            }
            return new ModulusPoly(this.field, product);
        }
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly negative() {
        int length = this.coefficients.length;
        int size = length;
        int[] negativeCoefficients = new int[length];
        for (int i = 0; i < size; i++) {
            negativeCoefficients[i] = this.field.subtract(0, this.coefficients[i]);
        }
        return new ModulusPoly(this.field, negativeCoefficients);
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly multiply(int scalar) {
        if (scalar == 0) {
            return this.field.getZero();
        }
        if (scalar == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int size = length;
        int[] product = new int[length];
        for (int i = 0; i < size; i++) {
            product[i] = this.field.multiply(this.coefficients[i], scalar);
        }
        return new ModulusPoly(this.field, product);
    }

    /* access modifiers changed from: package-private */
    public ModulusPoly multiplyByMonomial(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        } else if (coefficient == 0) {
            return this.field.getZero();
        } else {
            int length = this.coefficients.length;
            int size = length;
            int[] product = new int[(length + degree)];
            for (int i = 0; i < size; i++) {
                product[i] = this.field.multiply(this.coefficients[i], coefficient);
            }
            return new ModulusPoly(this.field, product);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder(getDegree() * 8);
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            int coefficient2 = coefficient;
            if (coefficient != 0) {
                if (coefficient2 < 0) {
                    result.append(" - ");
                    coefficient2 = -coefficient2;
                } else if (result.length() > 0) {
                    result.append(" + ");
                }
                if (degree == 0 || coefficient2 != 1) {
                    result.append(coefficient2);
                }
                if (degree != 0) {
                    if (degree == 1) {
                        result.append('x');
                    } else {
                        result.append("x^");
                        result.append(degree);
                    }
                }
            }
        }
        return result.toString();
    }
}
