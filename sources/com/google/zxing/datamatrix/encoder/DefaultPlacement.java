package com.google.zxing.datamatrix.encoder;

import java.util.Arrays;

/* loaded from: classes.dex */
public class DefaultPlacement {
    private final byte[] bits;
    private final CharSequence codewords;
    private final int numcols;
    private final int numrows;

    public DefaultPlacement(CharSequence codewords, int numcols, int numrows) {
        this.codewords = codewords;
        this.numcols = numcols;
        this.numrows = numrows;
        byte[] bArr = new byte[numcols * numrows];
        this.bits = bArr;
        Arrays.fill(bArr, (byte) -1);
    }

    final int getNumrows() {
        return this.numrows;
    }

    final int getNumcols() {
        return this.numcols;
    }

    final byte[] getBits() {
        return this.bits;
    }

    public final boolean getBit(int col, int row) {
        return this.bits[(this.numcols * row) + col] == 1;
    }

    private void setBit(int col, int row, boolean bit) {
        this.bits[(this.numcols * row) + col] = bit ? (byte) 1 : (byte) 0;
    }

    private boolean hasBit(int col, int row) {
        return this.bits[(this.numcols * row) + col] >= 0;
    }

    public final void place() {
        int i;
        int i2;
        int pos = 0;
        int row = 4;
        int col = 0;
        while (true) {
            if (row == this.numrows && col == 0) {
                corner1(pos);
                pos++;
            }
            int pos2 = this.numrows;
            if (row == pos2 - 2 && col == 0 && this.numcols % 4 != 0) {
                corner2(pos);
                pos++;
            }
            int pos3 = this.numrows;
            if (row == pos3 - 2 && col == 0 && this.numcols % 8 == 4) {
                corner3(pos);
                pos++;
            }
            int pos4 = this.numrows;
            if (row == pos4 + 4 && col == 2 && this.numcols % 8 == 0) {
                corner4(pos);
                pos++;
            }
            do {
                int pos5 = this.numrows;
                if (row < pos5 && col >= 0 && !hasBit(col, row)) {
                    utah(row, col, pos);
                    pos++;
                }
                row -= 2;
                col += 2;
                if (row < 0) {
                    break;
                }
            } while (col < this.numcols);
            int row2 = row + 1;
            int col2 = col + 3;
            do {
                if (row2 >= 0 && col2 < this.numcols && !hasBit(col2, row2)) {
                    utah(row2, col2, pos);
                    pos++;
                }
                row2 += 2;
                col2 -= 2;
                i = this.numrows;
                if (row2 >= i) {
                    break;
                }
            } while (col2 >= 0);
            row = row2 + 3;
            col = col2 + 1;
            if (row >= i && col >= (i2 = this.numcols)) {
                break;
            }
        }
        if (!hasBit(i2 - 1, i - 1)) {
            setBit(this.numcols - 1, this.numrows - 1, true);
            setBit(this.numcols - 2, this.numrows - 2, true);
        }
    }

    private void module(int row, int col, int pos, int bit) {
        if (row < 0) {
            int i = this.numrows;
            row += i;
            col += 4 - ((i + 4) % 8);
        }
        if (col < 0) {
            int i2 = this.numcols;
            col += i2;
            row += 4 - ((i2 + 4) % 8);
        }
        int v = this.codewords.charAt(pos) & (1 << (8 - bit));
        setBit(col, row, v != 0);
    }

    private void utah(int row, int col, int pos) {
        module(row - 2, col - 2, pos, 1);
        module(row - 2, col - 1, pos, 2);
        module(row - 1, col - 2, pos, 3);
        module(row - 1, col - 1, pos, 4);
        module(row - 1, col, pos, 5);
        module(row, col - 2, pos, 6);
        module(row, col - 1, pos, 7);
        module(row, col, pos, 8);
    }

    private void corner1(int pos) {
        module(this.numrows - 1, 0, pos, 1);
        module(this.numrows - 1, 1, pos, 2);
        module(this.numrows - 1, 2, pos, 3);
        module(0, this.numcols - 2, pos, 4);
        module(0, this.numcols - 1, pos, 5);
        module(1, this.numcols - 1, pos, 6);
        module(2, this.numcols - 1, pos, 7);
        module(3, this.numcols - 1, pos, 8);
    }

    private void corner2(int pos) {
        module(this.numrows - 3, 0, pos, 1);
        module(this.numrows - 2, 0, pos, 2);
        module(this.numrows - 1, 0, pos, 3);
        module(0, this.numcols - 4, pos, 4);
        module(0, this.numcols - 3, pos, 5);
        module(0, this.numcols - 2, pos, 6);
        module(0, this.numcols - 1, pos, 7);
        module(1, this.numcols - 1, pos, 8);
    }

    private void corner3(int pos) {
        module(this.numrows - 3, 0, pos, 1);
        module(this.numrows - 2, 0, pos, 2);
        module(this.numrows - 1, 0, pos, 3);
        module(0, this.numcols - 2, pos, 4);
        module(0, this.numcols - 1, pos, 5);
        module(1, this.numcols - 1, pos, 6);
        module(2, this.numcols - 1, pos, 7);
        module(3, this.numcols - 1, pos, 8);
    }

    private void corner4(int pos) {
        module(this.numrows - 1, 0, pos, 1);
        module(this.numrows - 1, this.numcols - 1, pos, 2);
        module(0, this.numcols - 3, pos, 3);
        module(0, this.numcols - 2, pos, 4);
        module(0, this.numcols - 1, pos, 5);
        module(1, this.numcols - 3, pos, 6);
        module(1, this.numcols - 2, pos, 7);
        module(1, this.numcols - 1, pos, 8);
    }
}
