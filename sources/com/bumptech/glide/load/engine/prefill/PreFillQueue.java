package com.bumptech.glide.load.engine.prefill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class PreFillQueue {
    private final Map<PreFillType, Integer> bitmapsPerType;
    private int bitmapsRemaining;
    private int keyIndex;
    private final List<PreFillType> keyList;

    public PreFillQueue(Map<PreFillType, Integer> bitmapsPerType2) {
        this.bitmapsPerType = bitmapsPerType2;
        this.keyList = new ArrayList(bitmapsPerType2.keySet());
        for (Integer count : bitmapsPerType2.values()) {
            this.bitmapsRemaining += count.intValue();
        }
    }

    public PreFillType remove() {
        PreFillType result = this.keyList.get(this.keyIndex);
        Integer countForResult = this.bitmapsPerType.get(result);
        if (countForResult.intValue() == 1) {
            this.bitmapsPerType.remove(result);
            this.keyList.remove(this.keyIndex);
        } else {
            this.bitmapsPerType.put(result, Integer.valueOf(countForResult.intValue() - 1));
        }
        this.bitmapsRemaining--;
        this.keyIndex = this.keyList.isEmpty() ? 0 : (this.keyIndex + 1) % this.keyList.size();
        return result;
    }

    public int getSize() {
        return this.bitmapsRemaining;
    }

    public boolean isEmpty() {
        return this.bitmapsRemaining == 0;
    }
}
