package my.cool.apps.bob;

import android.content.Context;

public class Guard extends GameObject
{
    private float waypointLeft;
    private float waypointRight;
    private int currentWaypoint;
    private static final float MAX_X_VELOCITY = 3;

    Guard(Context context, float worldStartX,
          float worldStartY, char type, int pixelsPerMetre) {
        final int ANIMATION_FPS = 8;
        final int ANIMATION_FRAME_COUNT = 5;
        final float HEIGHT = 2f;
        final float WIDTH = 1;
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setType(type);
        setBitmapName("guard");
        setMoves(true);
        setActive(true);
        setVisible(true);
        setAnimFps(ANIMATION_FPS);
        setAnimFrameCount(ANIMATION_FRAME_COUNT);
        setAnimated(context, pixelsPerMetre, true);
        setWorldLocation(worldStartX, worldStartY, 0);
        setxVelocity(-MAX_X_VELOCITY);
        currentWaypoint = LEFT;
    }

    public void setWaypoints(float left, float right){
        waypointLeft = left;
        waypointRight = right;
    }

    public void update(long fps, float gravity) {
        if (currentWaypoint == LEFT &&
                getWorldLocation().x <= waypointLeft) {
            currentWaypoint = RIGHT;
            setxVelocity(MAX_X_VELOCITY);
            setFacing(RIGHT);
        }
        if (currentWaypoint == RIGHT &&
                getWorldLocation().x >= waypointRight) {
            currentWaypoint = LEFT;
            setxVelocity(-MAX_X_VELOCITY);
            setFacing(LEFT);
        }
        move(fps);
        setRectHitbox();
    }

}

