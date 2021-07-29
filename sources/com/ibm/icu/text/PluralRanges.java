package com.ibm.icu.text;

import com.ibm.icu.impl.StandardPlural;
import com.ibm.icu.util.Freezable;
import com.ibm.icu.util.Output;
import java.util.Arrays;
import java.util.EnumSet;

@Deprecated
public final class PluralRanges implements Freezable<PluralRanges>, Comparable<PluralRanges> {
    private boolean[] explicit = new boolean[StandardPlural.COUNT];
    private volatile boolean isFrozen;
    private Matrix matrix = new Matrix();

    private static final class Matrix implements Comparable<Matrix>, Cloneable {
        private byte[] data = new byte[(StandardPlural.COUNT * StandardPlural.COUNT)];

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

        /* access modifiers changed from: package-private */
        public void set(StandardPlural start, StandardPlural end, StandardPlural result) {
            byte b;
            byte[] bArr = this.data;
            int ordinal = (start.ordinal() * StandardPlural.COUNT) + end.ordinal();
            if (result == null) {
                b = -1;
            } else {
                b = (byte) result.ordinal();
            }
            bArr[ordinal] = b;
        }

        /* access modifiers changed from: package-private */
        public void setIfNew(StandardPlural start, StandardPlural end, StandardPlural result) {
            byte b;
            byte old = this.data[(start.ordinal() * StandardPlural.COUNT) + end.ordinal()];
            if (old < 0) {
                byte[] bArr = this.data;
                int ordinal = (start.ordinal() * StandardPlural.COUNT) + end.ordinal();
                if (result == null) {
                    b = -1;
                } else {
                    b = (byte) result.ordinal();
                }
                bArr[ordinal] = b;
                return;
            }
            throw new IllegalArgumentException("Previously set value for <" + start + ", " + end + ", " + StandardPlural.VALUES.get(old) + ">");
        }

        /* access modifiers changed from: package-private */
        public StandardPlural get(StandardPlural start, StandardPlural end) {
            byte result = this.data[(start.ordinal() * StandardPlural.COUNT) + end.ordinal()];
            if (result < 0) {
                return null;
            }
            return (StandardPlural) StandardPlural.VALUES.get(result);
        }

        /* access modifiers changed from: package-private */
        public StandardPlural endSame(StandardPlural end) {
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

        /* access modifiers changed from: package-private */
        public StandardPlural startSame(StandardPlural start, EnumSet<StandardPlural> endDone, Output<Boolean> emit) {
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
                if (i >= bArr.length) {
                    return result;
                }
                result = (result * 37) + bArr[i];
                i++;
            }
        }

        public boolean equals(Object other) {
            if ((other instanceof Matrix) && compareTo((Matrix) other) == 0) {
                return true;
            }
            return false;
        }

        public int compareTo(Matrix o) {
            int i = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i >= bArr.length) {
                    return 0;
                }
                int diff = bArr[i] - o.data[i];
                if (diff != 0) {
                    return diff;
                }
                i++;
            }
        }

        public Matrix clone() {
            Matrix result = new Matrix();
            result.data = (byte[]) this.data.clone();
            return result;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            for (StandardPlural i : StandardPlural.values()) {
                for (StandardPlural j : StandardPlural.values()) {
                    StandardPlural x = get(i, j);
                    if (x != null) {
                        result.append(i + " & " + j + " → " + x + ";\n");
                    }
                }
            }
            return result.toString();
        }
    }

    @Deprecated
    public void add(StandardPlural rangeStart, StandardPlural rangeEnd, StandardPlural result) {
        if (!this.isFrozen) {
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
        } else {
            throw new UnsupportedOperationException();
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
        if (!(other instanceof PluralRanges)) {
            return false;
        }
        PluralRanges otherPR = (PluralRanges) other;
        if (!this.matrix.equals(otherPR.matrix) || !Arrays.equals(this.explicit, otherPR.explicit)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public int hashCode() {
        return this.matrix.hashCode();
    }

    @Deprecated
    public int compareTo(PluralRanges that) {
        return this.matrix.compareTo(that.matrix);
    }

    @Deprecated
    public boolean isFrozen() {
        return this.isFrozen;
    }

    @Deprecated
    public PluralRanges freeze() {
        this.isFrozen = true;
        return this;
    }

    @Deprecated
    public PluralRanges cloneAsThawed() {
        PluralRanges result = new PluralRanges();
        result.explicit = (boolean[]) this.explicit.clone();
        result.matrix = this.matrix.clone();
        return result;
    }

    @Deprecated
    public String toString() {
        return this.matrix.toString();
    }
}
