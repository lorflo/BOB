package my.cool.apps.bob;

import android.content.Context;

public class ExtraLife extends GameObject
{
    public ExtraLife(Context context, float worldStartX, float worldStartY, char type, int pixelsPerMetre) {
        final int ANIMATION_FPS = 4;
        final int ANIMATION_FRAME_COUNT = 3;
        final String BITMAP_NAME = "lifeplus";
        final float HEIGHT = 1;
        final float WIDTH = 1;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);
        setMoves(false);
        setActive(true);
        setVisible(true);
        setBitmapName(BITMAP_NAME);
        setAnimFps(ANIMATION_FPS);
        setAnimFrameCount(ANIMATION_FRAME_COUNT);
        setAnimated(context, pixelsPerMetre, true);
        setWorldLocation(worldStartX, worldStartY, 0);
        setRectHitbox();
    }

    public void update(long fps, float gravity) {}

}
