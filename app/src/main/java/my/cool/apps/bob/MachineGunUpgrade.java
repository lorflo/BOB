package my.cool.apps.bob;

public class MachineGunUpgrade extends  GameObject
{
    public MachineGunUpgrade(float worldStartX, float worldStartY,  char type) {
        final float HEIGHT = .5f;
        final float WIDTH = .5f;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);
        setBitmapName("clip");
        setWorldLocation(worldStartX, worldStartY, 0);
        setRectHitbox();
    }

    public void update(long fps, float gravity) {}

}
