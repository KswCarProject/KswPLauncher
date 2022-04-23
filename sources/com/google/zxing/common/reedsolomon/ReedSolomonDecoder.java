package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(GenericGF field2) {
        this.field = field2;
    }

    public void decode(int[] iArr, int i) throws ReedSolomonException {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.field, iArr);
        int[] iArr2 = new int[i];
        int i2 = 0;
        boolean z = true;
        for (int i3 = 0; i3 < i; i3++) {
            GenericGF genericGF = this.field;
            int evaluateAt = genericGFPoly.evaluateAt(genericGF.exp(genericGF.getGeneratorBase() + i3));
            iArr2[(i - 1) - i3] = evaluateAt;
            if (evaluateAt != 0) {
                z = false;
            }
        }
        if (!z) {
            GenericGFPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.buildMonomial(i, 1), new GenericGFPoly(this.field, iArr2), i);
            GenericGFPoly genericGFPoly2 = runEuclideanAlgorithm[0];
            GenericGFPoly genericGFPoly3 = runEuclideanAlgorithm[1];
            int[] findErrorLocations = findErrorLocations(genericGFPoly2);
            int[] findErrorMagnitudes = findErrorMagnitudes(genericGFPoly3, findErrorLocations);
            while (i2 < findErrorLocations.length) {
                int length = (iArr.length - 1) - this.field.log(findErrorLocations[i2]);
                if (length >= 0) {
                    iArr[length] = GenericGF.addOrSubtract(iArr[length], findErrorMagnitudes[i2]);
                    i2++;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, int i) throws ReedSolomonException {
        if (genericGFPoly.getDegree() < genericGFPoly2.getDegree()) {
            GenericGFPoly genericGFPoly3 = genericGFPoly2;
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly3;
        }
        GenericGFPoly zero = this.field.getZero();
        GenericGFPoly one = this.field.getOne();
        while (true) {
            GenericGFPoly genericGFPoly4 = r11;
            r11 = genericGFPoly;
            genericGFPoly = genericGFPoly4;
            GenericGFPoly genericGFPoly5 = one;
            GenericGFPoly genericGFPoly6 = zero;
            zero = genericGFPoly5;
            if (genericGFPoly.getDegree() < i / 2) {
                int coefficient = zero.getCoefficient(0);
                if (coefficient != 0) {
                    int inverse = this.field.inverse(coefficient);
                    return new GenericGFPoly[]{zero.multiply(inverse), genericGFPoly.multiply(inverse)};
                }
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            } else if (!genericGFPoly.isZero()) {
                GenericGFPoly zero2 = this.field.getZero();
                int inverse2 = this.field.inverse(genericGFPoly.getCoefficient(genericGFPoly.getDegree()));
                while (r11.getDegree() >= genericGFPoly.getDegree() && !r11.isZero()) {
                    int degree = r11.getDegree() - genericGFPoly.getDegree();
                    int multiply = this.field.multiply(r11.getCoefficient(r11.getDegree()), inverse2);
                    zero2 = zero2.addOrSubtract(this.field.buildMonomial(degree, multiply));
                    r11 = r11.addOrSubtract(genericGFPoly.multiplyByMonomial(degree, multiply));
                }
                one = zero2.multiply(zero).addOrSubtract(genericGFPoly6);
                if (r11.getDegree() >= genericGFPoly.getDegree()) {
                    throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
                }
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        }
    }

    private int[] findErrorLocations(GenericGFPoly errorLocator) throws ReedSolomonException {
        int degree = errorLocator.getDegree();
        int numErrors = degree;
        if (degree == 1) {
            return new int[]{errorLocator.getCoefficient(1)};
        }
        int[] result = new int[numErrors];
        int e = 0;
        for (int i = 1; i < this.field.getSize() && e < numErrors; i++) {
            if (errorLocator.evaluateAt(i) == 0) {
                result[e] = this.field.inverse(i);
                e++;
            }
        }
        if (e == numErrors) {
            return result;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GenericGFPoly errorEvaluator, int[] errorLocations) {
        int length = errorLocations.length;
        int s = length;
        int[] result = new int[length];
        for (int i = 0; i < s; i++) {
            int xiInverse = this.field.inverse(errorLocations[i]);
            int denominator = 1;
            for (int j = 0; j < s; j++) {
                if (i != j) {
                    int multiply = this.field.multiply(errorLocations[j], xiInverse);
                    int term = multiply;
                    denominator = this.field.multiply(denominator, (multiply & 1) == 0 ? term | 1 : term & -2);
                }
            }
            result[i] = this.field.multiply(errorEvaluator.evaluateAt(xiInverse), this.field.inverse(denominator));
            if (this.field.getGeneratorBase() != 0) {
                result[i] = this.field.multiply(result[i], xiInverse);
            }
        }
        return result;
    }
}
