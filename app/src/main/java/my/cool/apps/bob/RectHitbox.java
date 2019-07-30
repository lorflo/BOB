package my.cool.apps.bob;

public class RectHitbox {
    private float top;
    private float left;
    private float bottom;
    private float right;
    private float height;

    public boolean intersects(RectHitbox rectHitbox) {
        return (this.right > rectHitbox.left
                && this.left < rectHitbox.right)
                && (this.top < rectHitbox.bottom
                && this.bottom > rectHitbox.top);

    }

    public void setTop(float y) {
        top = y;
    }
    public float getTop() {
        return top;
    }

    public void setLeft(float x) {
        left = x;
    }
    public float getLeft() {
        return left;
    }

    public void setBottom(float v) {
        bottom = v;
    }
    public float getBottom() {
        return bottom;
    }

    public void setRight(float v) {
        right = v;
    }
    public float getRight() {
        return right;
    }
}

