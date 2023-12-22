package com.ibm.icu.text;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.Output;
import java.util.Arrays;
import java.util.EnumSet;

@Deprecated
/* loaded from: classes.dex */
public final class PluralRanges implements Freezable<PluralRanges>, Comparable<PluralRanges> {
    private volatile boolean isFrozen;
    private Matrix matrix = new Matrix();
    private boolean[] explicit = new boolean[StandardPlural.COUNT];

    /* loaded from: classes.dex */
    private static final class Matrix implements Comparable<Matrix>, Cloneable {
        private byte[] data = new byte[StandardPlural.COUNT * StandardPlural.COUNT];

        Matrix() {
            int i = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i < bArr.length) {
                    bArr[i] = -1;
                    i++;
                } else {
                    return;
                }
            }
        }

        void set(StandardPlural start, StandardPlural end, StandardPlural result) {
            this.data[(start.ordinal() * StandardPlural.COUNT) + end.ordinal()] = result == null ? (byte) -1 : (byte) result.ordinal();
        }

        void setIfNew(StandardPlural start, StandardPlural end, StandardPlural result) {
            byte old = this.data[(start.ordinal() * StandardPlural.COUNT) + end.ordinal()];
            if (old >= 0) {
                throw new IllegalArgumentException("Previously set value for <" + start + ", " + end + ", " + StandardPlural.VALUES.get(old) + ">");
            }
            this.data[(start.ordinal() * StandardPlural.COUNT) + end.ordinal()] = result == null ? (byte) -1 : (byte) result.ordinal();
        }

        StandardPlural get(StandardPlural start, StandardPlural end) {
            byte result = this.data[(start.ordinal() * StandardPlural.COUNT) + end.ordinal()];
            if (result < 0) {
                return null;
            }
            return (StandardPlural) StandardPlural.VALUES.get(result);
        }

        StandardPlural endSame(StandardPlural end) {
            StandardPlural first = null;
            for (StandardPlural start : StandardPlural.VALUES) {
                StandardPlural item = get(start, end);
                if (item != null) {
                    if (first == null) {
                        first = item;
                    } else if (first != item) {
                        return null;
                    }
                }
            }
            return first;
        }

        StandardPlural startSame(StandardPlural start, EnumSet<StandardPlural> endDone, Output<Boolean> emit) {
            emit.value = false;
            StandardPlural first = null;
            for (StandardPlural end : StandardPlural.VALUES) {
                StandardPlural item = get(start, end);
                if (item != null) {
                    if (first == null) {
                        first = item;
                    } else if (first != item) {
                        return null;
                    } else {
                        if (!endDone.contains(end)) {
                            emit.value = true;
                        }
                    }
                }
            }
            return first;
        }

        public int hashCode() {
            int result = 0;
            int i = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i < bArr.length) {
                    result = (result * 37) + bArr[i];
                    i++;
                } else {
                    return result;
                }
            }
        }

        public boolean equals(Object other) {
            return (other instanceof Matrix) && compareTo((Matrix) other) == 0;
        }

        @Override // java.lang.Comparable
        public int compareTo(Matrix o) {
            int i = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i < bArr.length) {
                    int diff = bArr[i] - o.data[i];
                    if (diff == 0) {
                        i++;
                    } else {
                        return diff;
                    }
                } else {
                    return 0;
                }
            }
        }

        /* renamed from: clone */
        public Matrix m83clone() {
            Matrix result = new Matrix();
            result.data = (byte[]) this.data.clone();
            return result;
        }

        public String toString() {
            StandardPlural[] values;
            StandardPlural[] values2;
            StringBuilder result = new StringBuilder();
            for (StandardPlural i : StandardPlural.values()) {
                for (StandardPlural j : StandardPlural.values()) {
                    StandardPlural x = get(i, j);
                    if (x != null) {
                        result.append(i + " & " + j + " \u2192 " + x + ";\n");
                    }
                }
            }
            return result.toString();
        }
    }

    @Deprecated
    public void add(StandardPlural rangeStart, StandardPlural rangeEnd, StandardPlural result) {
        StandardPlural[] values;
        StandardPlural[] values2;
        StandardPlural[] values3;
        if (this.isFrozen) {
            throw new UnsupportedOperationException();
        }
        this.explicit[result.ordinal()] = true;
        if (rangeStart == null) {
            for (StandardPlural rs : StandardPlural.values()) {
                if (rangeEnd == null) {
                    for (StandardPlural re : StandardPlural.values()) {
                        this.matrix.setIfNew(rs, re, result);
                    }
                } else {
                    this.explicit[rangeEnd.ordinal()] = true;
                    this.matrix.setIfNew(rs, rangeEnd, result);
                }
            }
        } else if (rangeEnd == null) {
            this.explicit[rangeStart.ordinal()] = true;
            for (StandardPlural re2 : StandardPlural.values()) {
                this.matrix.setIfNew(rangeStart, re2, result);
            }
        } else {
            this.explicit[rangeStart.ordinal()] = true;
            this.explicit[rangeEnd.ordinal()] = true;
            this.matrix.setIfNew(rangeStart, rangeEnd, result);
        }
    }

    @Deprecated
    public StandardPlural get(StandardPlural start, StandardPlural end) {
        StandardPlural result = this.matrix.get(start, end);
        return result == null ? end : result;
    }

    @Deprecated
    public boolean isExplicit(StandardPlural start, StandardPlural end) {
        return this.matrix.get(start, end) != null;
    }

    @Deprecated
    public boolean isExplicitlySet(StandardPlural count) {
        return this.explicit[count.ordinal()];
    }

    @Deprecated
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof PluralRanges) {
            PluralRanges otherPR = (PluralRanges) other;
            return this.matrix.equals(otherPR.matrix) && Arrays.equals(this.explicit, otherPR.explicit);
        }
        return false;
    }

    @Deprecated
    public int hashCode() {
        return this.matrix.hashCode();
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(PluralRanges that) {
        return this.matrix.compareTo(that.matrix);
    }

    @Deprecated
    public boolean isFrozen() {
        return this.isFrozen;
    }

    @Deprecated
    /* renamed from: freeze */
    public PluralRanges m82freeze() {
        this.isFrozen = true;
        return this;
    }

    @Deprecated
    /* renamed from: cloneAsThawed */
    public PluralRanges m81cloneAsThawed() {
        PluralRanges result = new PluralRanges();
        result.explicit = (boolean[]) this.explicit.clone();
        result.matrix = this.matrix.m83clone();
        return result;
    }

    @Deprecated
    public String toString() {
        return this.matrix.toString();
    }
}
