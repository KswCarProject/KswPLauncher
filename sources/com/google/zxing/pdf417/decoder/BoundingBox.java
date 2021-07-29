package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

final class BoundingBox {
    private final ResultPoint bottomLeft;
    private final ResultPoint bottomRight;
    private final BitMatrix image;
    private final int maxX;
    private final int maxY;
    private final int minX;
    private final int minY;
    private final ResultPoint topLeft;
    private final ResultPoint topRight;

    BoundingBox(BitMatrix image2, ResultPoint topLeft2, ResultPoint bottomLeft2, ResultPoint topRight2, ResultPoint bottomRight2) throws NotFoundException {
        boolean rightUnspecified = false;
        boolean leftUnspecified = topLeft2 == null || bottomLeft2 == null;
        rightUnspecified = (topRight2 == null || bottomRight2 == null) ? true : rightUnspecified;
        if (!leftUnspecified || !rightUnspecified) {
            if (leftUnspecified) {
                topLeft2 = new ResultPoint(0.0f, topRight2.getY());
                bottomLeft2 = new ResultPoint(0.0f, bottomRight2.getY());
            } else if (rightUnspecified) {
                topRight2 = new ResultPoint((float) (image2.getWidth() - 1), topLeft2.getY());
                bottomRight2 = new ResultPoint((float) (image2.getWidth() - 1), bottomLeft2.getY());
            }
            this.image = image2;
            this.topLeft = topLeft2;
            this.bottomLeft = bottomLeft2;
            this.topRight = topRight2;
            this.bottomRight = bottomRight2;
            this.minX = (int) Math.min(topLeft2.getX(), bottomLeft2.getX());
            this.maxX = (int) Math.max(topRight2.getX(), bottomRight2.getX());
            this.minY = (int) Math.min(topLeft2.getY(), topRight2.getY());
            this.maxY = (int) Math.max(bottomLeft2.getY(), bottomRight2.getY());
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    BoundingBox(BoundingBox boundingBox) {
        this.image = boundingBox.image;
        this.topLeft = boundingBox.getTopLeft();
        this.bottomLeft = boundingBox.getBottomLeft();
        this.topRight = boundingBox.getTopRight();
        this.bottomRight = boundingBox.getBottomRight();
        this.minX = boundingBox.getMinX();
        this.maxX = boundingBox.getMaxX();
        this.minY = boundingBox.getMinY();
        this.maxY = boundingBox.getMaxY();
    }

    static BoundingBox merge(BoundingBox leftBox, BoundingBox rightBox) throws NotFoundException {
        if (leftBox == null) {
            return rightBox;
        }
        if (rightBox == null) {
            return leftBox;
        }
        return new BoundingBox(leftBox.image, leftBox.topLeft, leftBox.bottomLeft, rightBox.topRight, rightBox.bottomRight);
    }

    /* access modifiers changed from: package-private */
    public BoundingBox addMissingRows(int missingStartRows, int missingEndRows, boolean isLeft) throws NotFoundException {
        ResultPoint newTopLeft = this.topLeft;
        ResultPoint newBottomLeft = this.bottomLeft;
        ResultPoint newTopRight = this.topRight;
        ResultPoint newBottomRight = this.bottomRight;
        if (missingStartRows > 0) {
            ResultPoint resultPoint = isLeft ? this.topLeft : this.topRight;
            ResultPoint top = resultPoint;
            int y = ((int) resultPoint.getY()) - missingStartRows;
            int newMinY = y;
            if (y < 0) {
                newMinY = 0;
            }
            ResultPoint newTop = new ResultPoint(top.getX(), (float) newMinY);
            if (isLeft) {
                newTopLeft = newTop;
            } else {
                newTopRight = newTop;
            }
        }
        if (missingEndRows > 0) {
            ResultPoint resultPoint2 = isLeft ? this.bottomLeft : this.bottomRight;
            ResultPoint bottom = resultPoint2;
            int y2 = ((int) resultPoint2.getY()) + missingEndRows;
            int newMaxY = y2;
            if (y2 >= this.image.getHeight()) {
                newMaxY = this.image.getHeight() - 1;
            }
            ResultPoint newBottom = new ResultPoint(bottom.getX(), (float) newMaxY);
            if (isLeft) {
                newBottomLeft = newBottom;
            } else {
                newBottomRight = newBottom;
            }
        }
        return new BoundingBox(this.image, newTopLeft, newBottomLeft, newTopRight, newBottomRight);
    }

    /* access modifiers changed from: package-private */
    public int getMinX() {
        return this.minX;
    }

    /* access modifiers changed from: package-private */
    public int getMaxX() {
        return this.maxX;
    }

    /* access modifiers changed from: package-private */
    public int getMinY() {
        return this.minY;
    }

    /* access modifiers changed from: package-private */
    public int getMaxY() {
        return this.maxY;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint getTopLeft() {
        return this.topLeft;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint getTopRight() {
        return this.topRight;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint getBottomLeft() {
        return this.bottomLeft;
    }

    /* access modifiers changed from: package-private */
    public ResultPoint getBottomRight() {
        return this.bottomRight;
    }
}
