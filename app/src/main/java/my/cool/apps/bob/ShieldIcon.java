package my.cool.apps.bob;

public class ShieldIcon extends GameObject {
    public ShieldIcon(int worldStartX,int worldStartY,char type) {
        final float HEIGHT = .5f;
        final float WIDTH = .5f;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);
        setBitmapName("shield");
        setVisible(false);
        setActive(false);
        setWorldLocation(worldStartX, worldStartY, 0);
        setRectHitbox();
    }

    public void update(long fps, float gravity) {}
}
