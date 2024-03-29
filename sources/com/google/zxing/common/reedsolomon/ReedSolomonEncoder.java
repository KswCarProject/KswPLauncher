package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators;
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF field2) {
        this.field = field2;
        ArrayList arrayList = new ArrayList();
        this.cachedGenerators = arrayList;
        arrayList.add(new GenericGFPoly(field2, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int degree) {
        if (degree >= this.cachedGenerators.size()) {
            List<GenericGFPoly> list = this.cachedGenerators;
            GenericGFPoly lastGenerator = list.get(list.size() - 1);
            for (int d = this.cachedGenerators.size(); d <= degree; d++) {
                GenericGF genericGF = this.field;
                GenericGFPoly nextGenerator = lastGenerator.multiply(new GenericGFPoly(genericGF, new int[]{1, genericGF.exp((d - 1) + genericGF.getGeneratorBase())}));
                this.cachedGenerators.add(nextGenerator);
                lastGenerator = nextGenerator;
            }
        }
        return this.cachedGenerators.get(degree);
    }

    public void encode(int[] toEncode, int ecBytes) {
        if (ecBytes != 0) {
            int length = toEncode.length - ecBytes;
            int dataBytes = length;
            if (length > 0) {
                GenericGFPoly generator = buildGenerator(ecBytes);
                int[] infoCoefficients = new int[dataBytes];
                System.arraycopy(toEncode, 0, infoCoefficients, 0, dataBytes);
                int[] coefficients = new GenericGFPoly(this.field, infoCoefficients).multiplyByMonomial(ecBytes, 1).divide(generator)[1].getCoefficients();
                int numZeroCoefficients = ecBytes - coefficients.length;
                for (int i = 0; i < numZeroCoefficients; i++) {
                    toEncode[dataBytes + i] = 0;
                }
                System.arraycopy(coefficients, 0, toEncode, dataBytes + numZeroCoefficients, coefficients.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
