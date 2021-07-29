package com.google.zxing.oned.rss.expanded.decoders;

final class BlockParsedResult {
    private final DecodedInformation decodedInformation;
    private final boolean finished;

    BlockParsedResult(boolean finished2) {
        this((DecodedInformation) null, finished2);
    }

    BlockParsedResult(DecodedInformation information, boolean finished2) {
        this.finished = finished2;
        this.decodedInformation = information;
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation getDecodedInformation() {
        return this.decodedInformation;
    }

    /* access modifiers changed from: package-private */
    public boolean isFinished() {
        return this.finished;
    }
}
