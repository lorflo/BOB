package my.cool.apps.bob;

public class BackgroundData {
    String bitmapName;
    boolean isParallax; // not used
    int layer;
    float startY;
    float endY;
    float speed;
    int width;
    public BackgroundData(String bitmap, boolean isParallax, int layer,
                          float startY, float endY, float speed) {
        this.bitmapName = bitmap;
        this.isParallax = isParallax;
        this.layer = layer;
        this.startY = startY;
        this.endY = endY;
        this.speed = speed;
    }

}
