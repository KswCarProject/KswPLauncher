package com.ibm.icu.text;

import com.ibm.icu.impl.UCaseProps;

/* loaded from: classes.dex */
class ReplaceableContextIterator implements UCaseProps.ContextIterator {
    protected Replaceable rep = null;
    protected int contextLimit = 0;
    protected int contextStart = 0;
    protected int index = 0;
    protected int cpLimit = 0;
    protected int cpStart = 0;
    protected int limit = 0;
    protected int dir = 0;
    protected boolean reachedLimit = false;

    ReplaceableContextIterator() {
    }

    public void setText(Replaceable rep) {
        this.rep = rep;
        int length = rep.length();
        this.contextLimit = length;
        this.limit = length;
        this.contextStart = 0;
        this.index = 0;
        this.cpLimit = 0;
        this.cpStart = 0;
        this.dir = 0;
        this.reachedLimit = false;
    }

    public void setIndex(int index) {
        this.cpLimit = index;
        this.cpStart = index;
        this.index = 0;
        this.dir = 0;
        this.reachedLimit = false;
    }

    public int getCaseMapCPStart() {
        return this.cpStart;
    }

    public void setLimit(int lim) {
        if (lim >= 0 && lim <= this.rep.length()) {
            this.limit = lim;
        } else {
            this.limit = this.rep.length();
        }
        this.reachedLimit = false;
    }

    public void setContextLimits(int contextStart, int contextLimit) {
        if (contextStart < 0) {
            this.contextStart = 0;
        } else if (contextStart <= this.rep.length()) {
            this.contextStart = contextStart;
        } else {
            this.contextStart = this.rep.length();
        }
        int i = this.contextStart;
        if (contextLimit < i) {
            this.contextLimit = i;
        } else if (contextLimit <= this.rep.length()) {
            this.contextLimit = contextLimit;
        } else {
            this.contextLimit = this.rep.length();
        }
        this.reachedLimit = false;
    }

    public int nextCaseMapCP() {
        int i = this.cpLimit;
        if (i < this.limit) {
            this.cpStart = i;
            int c = this.rep.char32At(i);
            this.cpLimit += UTF16.getCharCount(c);
            return c;
        }
        return -1;
    }

    public int replace(String text) {
        int length = text.length();
        int i = this.cpLimit;
        int i2 = this.cpStart;
        int delta = length - (i - i2);
        this.rep.replace(i2, i, text);
        this.cpLimit += delta;
        this.limit += delta;
        this.contextLimit += delta;
        return delta;
    }

    public boolean didReachLimit() {
        return this.reachedLimit;
    }

    public void reset(int direction) {
        if (direction > 0) {
            this.dir = 1;
            this.index = this.cpLimit;
        } else if (direction < 0) {
            this.dir = -1;
            this.index = this.cpStart;
        } else {
            this.dir = 0;
            this.index = 0;
        }
        this.reachedLimit = false;
    }

    public int next() {
        int i;
        int i2 = this.dir;
        if (i2 > 0) {
            int i3 = this.index;
            if (i3 < this.contextLimit) {
                int c = this.rep.char32At(i3);
                this.index += UTF16.getCharCount(c);
                return c;
            }
            this.reachedLimit = true;
            return -1;
        } else if (i2 < 0 && (i = this.index) > this.contextStart) {
            int c2 = this.rep.char32At(i - 1);
            this.index -= UTF16.getCharCount(c2);
            return c2;
        } else {
            return -1;
        }
    }
}
