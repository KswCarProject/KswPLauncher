package com.google.zxing.common.reedsolomon;

final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    GenericGFPoly(GenericGF field2, int[] coefficients2) {
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
                result = GenericGF.addOrSubtract(result, coefficient);
            }
            return result;
        }
        int[] iArr = this.coefficients;
        int result2 = iArr[0];
        int size = iArr.length;
        for (int i = 1; i < size; i++) {
            result2 = GenericGF.addOrSubtract(this.field.multiply(a, result2), this.coefficients[i]);
        }
        return result2;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly addOrSubtract(GenericGFPoly other) {
        if (!this.field.equals(other.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
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
                sumDiff[i] = GenericGF.addOrSubtract(smallerCoefficients[i - lengthDiff], largerCoefficients[i]);
            }
            return new GenericGFPoly(this.field, sumDiff);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly multiply(GenericGFPoly other) {
        if (!this.field.equals(other.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
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
                    product[i + j] = GenericGF.addOrSubtract(product[i + j], this.field.multiply(aCoeff, bCoefficients[j]));
                }
            }
            return new GenericGFPoly(this.field, product);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly multiply(int scalar) {
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
        return new GenericGFPoly(this.field, product);
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly multiplyByMonomial(int degree, int coefficient) {
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
            return new GenericGFPoly(this.field, product);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly[] divide(GenericGFPoly other) {
        if (!this.field.equals(other.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!other.isZero()) {
            GenericGFPoly quotient = this.field.getZero();
            GenericGFPoly remainder = this;
            int inverseDenominatorLeadingTerm = this.field.inverse(other.getCoefficient(other.getDegree()));
            while (remainder.getDegree() >= other.getDegree() && !remainder.isZero()) {
                int degreeDifference = remainder.getDegree() - other.getDegree();
                int scale = this.field.multiply(remainder.getCoefficient(remainder.getDegree()), inverseDenominatorLeadingTerm);
                GenericGFPoly term = other.multiplyByMonomial(degreeDifference, scale);
                quotient = quotient.addOrSubtract(this.field.buildMonomial(degreeDifference, scale));
                remainder = remainder.addOrSubtract(term);
            }
            return new GenericGFPoly[]{quotient, remainder};
        } else {
            throw new IllegalArgumentException("Divide by 0");
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
                    int log = this.field.log(coefficient2);
                    int alphaPower = log;
                    if (log == 0) {
                        result.append('1');
                    } else if (alphaPower == 1) {
                        result.append('a');
                    } else {
                        result.append("a^");
                        result.append(alphaPower);
                    }
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
