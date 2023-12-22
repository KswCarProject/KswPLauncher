package com.google.zxing.common;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class BitMatrix implements Cloneable {
    private final int[] bits;
    private final int height;
    private final int rowSize;
    private final int width;

    public BitMatrix(int dimension) {
        this(dimension, dimension);
    }

    public BitMatrix(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.width = width;
        this.height = height;
        int i = (width + 31) / 32;
        this.rowSize = i;
        this.bits = new int[i * height];
    }

    private BitMatrix(int width, int height, int rowSize, int[] bits) {
        this.width = width;
        this.height = height;
        this.rowSize = rowSize;
        this.bits = bits;
    }

    public static BitMatrix parse(boolean[][] image) {
        int height = image.length;
        int width = image[0].length;
        BitMatrix bits = new BitMatrix(width, height);
        for (int i = 0; i < height; i++) {
            boolean[] imageI = image[i];
            for (int j = 0; j < width; j++) {
                if (imageI[j]) {
                    bits.set(j, i);
                }
            }
        }
        return bits;
    }

    public static BitMatrix parse(String stringRepresentation, String setString, String unsetString) {
        if (stringRepresentation == null) {
            throw new IllegalArgumentException();
        }
        boolean[] bits = new boolean[stringRepresentation.length()];
        int bitsPos = 0;
        int rowStartPos = 0;
        int rowLength = -1;
        int nRows = 0;
        int pos = 0;
        while (pos < stringRepresentation.length()) {
            if (stringRepresentation.charAt(pos) == '\n' || stringRepresentation.charAt(pos) == '\r') {
                if (bitsPos > rowStartPos) {
                    if (rowLength == -1) {
                        rowLength = bitsPos - rowStartPos;
                    } else if (bitsPos - rowStartPos != rowLength) {
                        throw new IllegalArgumentException("row lengths do not match");
                    }
                    rowStartPos = bitsPos;
                    nRows++;
                }
                pos++;
            } else if (stringRepresentation.substring(pos, setString.length() + pos).equals(setString)) {
                pos += setString.length();
                bits[bitsPos] = true;
                bitsPos++;
            } else if (stringRepresentation.substring(pos, unsetString.length() + pos).equals(unsetString)) {
                pos += unsetString.length();
                bits[bitsPos] = false;
                bitsPos++;
            } else {
                throw new IllegalArgumentException("illegal character encountered: " + stringRepresentation.substring(pos));
            }
        }
        if (bitsPos > rowStartPos) {
            if (rowLength == -1) {
                rowLength = bitsPos - rowStartPos;
            } else if (bitsPos - rowStartPos != rowLength) {
                throw new IllegalArgumentException("row lengths do not match");
            }
            nRows++;
        }
        BitMatrix matrix = new BitMatrix(rowLength, nRows);
        for (int i = 0; i < bitsPos; i++) {
            if (bits[i]) {
                matrix.set(i % rowLength, i / rowLength);
            }
        }
        return matrix;
    }

    public boolean get(int x, int y) {
        int offset = (this.rowSize * y) + (x / 32);
        return ((this.bits[offset] >>> (x & 31)) & 1) != 0;
    }

    public void set(int x, int y) {
        int offset = (this.rowSize * y) + (x / 32);
        int[] iArr = this.bits;
        iArr[offset] = iArr[offset] | (1 << (x & 31));
    }

    public void unset(int x, int y) {
        int offset = (this.rowSize * y) + (x / 32);
        int[] iArr = this.bits;
        iArr[offset] = iArr[offset] & (~(1 << (x & 31)));
    }

    public void flip(int x, int y) {
        int offset = (this.rowSize * y) + (x / 32);
        int[] iArr = this.bits;
        iArr[offset] = iArr[offset] ^ (1 << (x & 31));
    }

    public void xor(BitMatrix mask) {
        if (this.width != mask.getWidth() || this.height != mask.getHeight() || this.rowSize != mask.getRowSize()) {
            throw new IllegalArgumentException("input matrix dimensions do not match");
        }
        BitArray rowArray = new BitArray(this.width);
        for (int y = 0; y < this.height; y++) {
            int offset = this.rowSize * y;
            int[] row = mask.getRow(y, rowArray).getBitArray();
            for (int x = 0; x < this.rowSize; x++) {
                int[] iArr = this.bits;
                int i = offset + x;
                iArr[i] = iArr[i] ^ row[x];
            }
        }
    }

    public void clear() {
        int max = this.bits.length;
        for (int i = 0; i < max; i++) {
            this.bits[i] = 0;
        }
    }

    public void setRegion(int left, int top, int width, int height) {
        if (top < 0 || left < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int right = left + width;
        int bottom = top + height;
        if (bottom > this.height || right > this.width) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        for (int y = top; y < bottom; y++) {
            int offset = this.rowSize * y;
            for (int x = left; x < right; x++) {
                int[] iArr = this.bits;
                int i = (x / 32) + offset;
                iArr[i] = iArr[i] | (1 << (x & 31));
            }
        }
    }

    public BitArray getRow(int y, BitArray row) {
        if (row == null || row.getSize() < this.width) {
            row = new BitArray(this.width);
        } else {
            row.clear();
        }
        int offset = this.rowSize * y;
        for (int x = 0; x < this.rowSize; x++) {
            row.setBulk(x << 5, this.bits[offset + x]);
        }
        return row;
    }

    public void setRow(int y, BitArray row) {
        int[] bitArray = row.getBitArray();
        int[] iArr = this.bits;
        int i = this.rowSize;
        System.arraycopy(bitArray, 0, iArr, y * i, i);
    }

    public void rotate180() {
        int width = getWidth();
        int height = getHeight();
        BitArray topRow = new BitArray(width);
        BitArray bottomRow = new BitArray(width);
        for (int i = 0; i < (height + 1) / 2; i++) {
            topRow = getRow(i, topRow);
            bottomRow = getRow((height - 1) - i, bottomRow);
            topRow.reverse();
            bottomRow.reverse();
            setRow(i, bottomRow);
            setRow((height - 1) - i, topRow);
        }
    }

    public int[] getEnclosingRectangle() {
        int left = this.width;
        int top = this.height;
        int right = -1;
        int bottom = -1;
        for (int y = 0; y < this.height; y++) {
            int x32 = 0;
            while (true) {
                int i = this.rowSize;
                if (x32 < i) {
                    int theBits = this.bits[(i * y) + x32];
                    if (theBits != 0) {
                        if (y < top) {
                            top = y;
                        }
                        if (y > bottom) {
                            bottom = y;
                        }
                        if ((x32 << 5) < left) {
                            int bit = 0;
                            while ((theBits << (31 - bit)) == 0) {
                                bit++;
                            }
                            if ((x32 << 5) + bit < left) {
                                left = (x32 << 5) + bit;
                            }
                        }
                        int bit2 = x32 << 5;
                        if (bit2 + 31 > right) {
                            int bit3 = 31;
                            while ((theBits >>> bit3) == 0) {
                                bit3--;
                            }
                            if ((x32 << 5) + bit3 > right) {
                                right = (x32 << 5) + bit3;
                            }
                        }
                    }
                    x32++;
                }
            }
        }
        if (right < left || bottom < top) {
            return null;
        }
        return new int[]{left, top, (right - left) + 1, (bottom - top) + 1};
    }

    public int[] getTopLeftOnBit() {
        int[] iArr;
        int bitsOffset = 0;
        while (true) {
            iArr = this.bits;
            if (bitsOffset >= iArr.length || iArr[bitsOffset] != 0) {
                break;
            }
            bitsOffset++;
        }
        if (bitsOffset == iArr.length) {
            return null;
        }
        int i = this.rowSize;
        int y = bitsOffset / i;
        int x = (bitsOffset % i) << 5;
        int theBits = iArr[bitsOffset];
        int bit = 0;
        while ((theBits << (31 - bit)) == 0) {
            bit++;
        }
        return new int[]{x + bit, y};
    }

    public int[] getBottomRightOnBit() {
        int bitsOffset = this.bits.length - 1;
        while (bitsOffset >= 0 && this.bits[bitsOffset] == 0) {
            bitsOffset--;
        }
        if (bitsOffset < 0) {
            return null;
        }
        int i = this.rowSize;
        int y = bitsOffset / i;
        int x = (bitsOffset % i) << 5;
        int theBits = this.bits[bitsOffset];
        int bit = 31;
        while ((theBits >>> bit) == 0) {
            bit--;
        }
        return new int[]{x + bit, y};
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public boolean equals(Object o) {
        if (o instanceof BitMatrix) {
            BitMatrix other = (BitMatrix) o;
            return this.width == other.width && this.height == other.height && this.rowSize == other.rowSize && Arrays.equals(this.bits, other.bits);
        }
        return false;
    }

    public int hashCode() {
        int i = this.width;
        return (((((((i * 31) + i) * 31) + this.height) * 31) + this.rowSize) * 31) + Arrays.hashCode(this.bits);
    }

    public String toString() {
        return toString("X ", "  ");
    }

    public String toString(String setString, String unsetString) {
        return buildToString(setString, unsetString, "\n");
    }

    @Deprecated
    public String toString(String setString, String unsetString, String lineSeparator) {
        return buildToString(setString, unsetString, lineSeparator);
    }

    private String buildToString(String setString, String unsetString, String lineSeparator) {
        StringBuilder result = new StringBuilder(this.height * (this.width + 1));
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                result.append(get(x, y) ? setString : unsetString);
            }
            result.append(lineSeparator);
        }
        return result.toString();
    }

    /* renamed from: clone */
    public BitMatrix m72clone() {
        return new BitMatrix(this.width, this.height, this.rowSize, (int[]) this.bits.clone());
    }
}
