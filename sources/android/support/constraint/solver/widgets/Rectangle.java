package android.support.constraint.solver.widgets;

/* loaded from: classes.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f34x;

    /* renamed from: y */
    public int f35y;

    public void setBounds(int x, int y, int width, int height) {
        this.f34x = x;
        this.f35y = y;
        this.width = width;
        this.height = height;
    }

    void grow(int w, int h) {
        this.f34x -= w;
        this.f35y -= h;
        this.width += w * 2;
        this.height += h * 2;
    }

    boolean intersects(Rectangle bounds) {
        int i;
        int i2;
        int i3 = this.f34x;
        int i4 = bounds.f34x;
        return i3 >= i4 && i3 < i4 + bounds.width && (i = this.f35y) >= (i2 = bounds.f35y) && i < i2 + bounds.height;
    }

    public boolean contains(int x, int y) {
        int i;
        int i2 = this.f34x;
        return x >= i2 && x < i2 + this.width && y >= (i = this.f35y) && y < i + this.height;
    }

    public int getCenterX() {
        return (this.f34x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f35y + this.height) / 2;
    }
}
