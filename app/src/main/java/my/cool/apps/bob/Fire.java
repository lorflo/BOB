package my.cool.apps.bob;

import android.content.Context;

public class Fire extends GameObject {
    public Fire(Context context, float worldStartX, float worldStartY, char type, int pixelsPerMetre) {
        final int ANIMATION_FPS = 3;
        final int ANIMATION_FRAME_COUNT = 3;
        final String BITMAP_NAME = "fire";
        final float HEIGHT = 1;
        final float WIDTH = 1;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);
        setMoves(false);
        setActive(false);
        setVisible(true);
        setBitmapName(BITMAP_NAME);
        setAnimFps(ANIMATION_FPS);
        setAnimFrameCount(ANIMATION_FRAME_COUNT);
        setAnimated(context, pixelsPerMetre, true);
        setWorldLocation(worldStartX, worldStartY, 0);
        setRectHitbox();
    }
    public void update(long fps, float gravity) { }
}

