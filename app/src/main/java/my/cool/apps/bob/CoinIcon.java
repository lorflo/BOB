package my.cool.apps.bob;

public class CoinIcon extends GameObject {
    public CoinIcon(int worldStartX,int worldStartY,char type) {
        final float HEIGHT = .5f;
        final float WIDTH = .5f;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);
        setVisible(false);
        setActive(false);
        setBitmapName("coin");
        setWorldLocation(worldStartX, worldStartY, 0);
        setRectHitbox();
    }

    public void update(long fps, float gravity) {}
}
