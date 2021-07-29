package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

final class BarcodeValue {
    private final Map<Integer, Integer> values = new HashMap();

    BarcodeValue() {
    }

    /* access modifiers changed from: package-private */
    public void setValue(int value) {
        Integer num = this.values.get(Integer.valueOf(value));
        Integer confidence = num;
        if (num == null) {
            confidence = 0;
        }
        this.values.put(Integer.valueOf(value), Integer.valueOf(confidence.intValue() + 1));
    }

    /* access modifiers changed from: package-private */
    public int[] getValue() {
        int maxConfidence = -1;
        Collection<Integer> result = new ArrayList<>();
        for (Map.Entry next : this.values.entrySet()) {
            Map.Entry entry = next;
            if (((Integer) next.getValue()).intValue() > maxConfidence) {
                maxConfidence = ((Integer) entry.getValue()).intValue();
                result.clear();
                result.add(entry.getKey());
            } else if (((Integer) entry.getValue()).intValue() == maxConfidence) {
                result.add(entry.getKey());
            }
        }
        return PDF417Common.toIntArray(result);
    }

    /* access modifiers changed from: package-private */
    public Integer getConfidence(int value) {
        return this.values.get(Integer.valueOf(value));
    }
}
