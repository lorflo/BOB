package my.cool.apps.bob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends GameObject
{
    private static final float MAX_X_VELOCITY = 10;
    private boolean isPressingRight = false;
    private boolean isPressingLeft = false;
    public boolean isFalling;
    private boolean isJumping;
    private long jumpTime;
    private long maxJumpTime = 700; // jump 7 10ths of second
    private RectHitbox rectHitboxHead;
    private RectHitbox rectHitboxFeet;
    private RectHitbox rectHitboxLeft;
    private RectHitbox rectHitboxRight;
    private MachineGun bfg;




    public Player(Context context, float worldStartX,
                  float worldStartY, int pixelsPerMetre) {
        final float HEIGHT = 2;
        final float WIDTH = 1;
        setHeight(HEIGHT); // 2 meters tall
        setWidth(WIDTH); // 1 meter wide
        setType('p');
        setBitmapName("player");
        setMoves(true);

        final int ANIMATION_FPS = 16;
        final int ANIMATION_FRAME_COUNT = 5;
        // Set this object up to be animated
        setAnimFps(ANIMATION_FPS);
        setAnimFrameCount(ANIMATION_FRAME_COUNT);
        setAnimated(context, pixelsPerMetre, true);

        setWorldLocation(worldStartX, worldStartY, 0);
        rectHitboxFeet = new RectHitbox();
        rectHitboxRight = new RectHitbox();
        rectHitboxLeft = new RectHitbox();
        rectHitboxHead = new RectHitbox();
        bfg = new MachineGun();
    }

    public void update(long fps, float gravity) {
        if (isPressingRight) {
            setxVelocity(MAX_X_VELOCITY);
        } else if (isPressingLeft) {
            setxVelocity(-MAX_X_VELOCITY);
        } else {
            setxVelocity(0);
        }

        if (isJumping) {
            long timeJumping = System.currentTimeMillis() - jumpTime;
            if (timeJumping < maxJumpTime) {
                if (timeJumping < maxJumpTime / 2) {
                    setyVelocity(-gravity); // on the way up
                } else if (timeJumping > maxJumpTime / 2) {
                    setyVelocity(gravity); // going down
                }
            } else {
                isJumping = false;
            }
        } else {
            setyVelocity(gravity);
            isFalling = true;
        }
        Vector2Point5D location = getWorldLocation();

        bfg.update(fps, gravity);
        move(fps);

        float lx = location.x;
        float ly = location.y;
        rectHitboxFeet.setTop(ly + getHeight() * .95f);
        rectHitboxFeet.setLeft(lx + getWidth() * .2f);
        rectHitboxFeet.setBottom(ly + getHeight() * .98f);
        rectHitboxFeet.setRight(lx + getWidth() * .8f);

        rectHitboxHead.setTop(ly);
        rectHitboxHead.setLeft(lx + getWidth() * .4f);
        rectHitboxHead.setBottom(ly + getHeight() * .2f);
        rectHitboxHead.setRight(lx + getWidth() * .6f);

        rectHitboxLeft.setTop(ly + getHeight() * .2f);
        rectHitboxLeft.setLeft(lx + getWidth() * .2f);
        rectHitboxLeft.setBottom(ly + getHeight() * .8f);
        rectHitboxLeft.setRight(lx + getWidth() * .3f);

        rectHitboxRight.setTop(ly + getHeight() * .2f);
        rectHitboxRight.setLeft(lx + getWidth() * .8f);
        rectHitboxRight.setBottom(ly + getHeight() * .8f);
        rectHitboxRight.setRight(lx + getWidth() * .7f);

        // which way is player facing?
        if (getxVelocity() > 0) {
            setFacing(RIGHT);
        } else if (getxVelocity() < 0) {
            setFacing(LEFT);
        }
    }
   public void draw(Canvas canvas,LevelManager lm,Rect toScreen2d)
   {

   }
    public int checkCollisions(RectHitbox rectHitbox) {
        int collided = 0; // no collision
        if (this.rectHitboxLeft.intersects(rectHitbox)) {
            // move player just to right of current hitbox
            this.setWorldLocationX(rectHitbox.getRight() - getWidth() * .2f);
            collided = 1;
        }
        if (this.rectHitboxRight.intersects(rectHitbox)) {
            // move player just to left of current hitbox
            this.setWorldLocationX(rectHitbox.getLeft() - getWidth() * .8f);
            collided = 1;
        }
        if (this.rectHitboxFeet.intersects(rectHitbox)) {
            // move feet to just above current hitbox
            this.setWorldLocationY(rectHitbox.getTop() - getHeight());
            collided = 2;
        }
        if (this.rectHitboxHead.intersects(rectHitbox)) {
            // move head to just below current hitbox bottom
            this.setWorldLocationY(rectHitbox.getBottom());
            collided = 3;
        }
        return collided;
    }

    public boolean pullTrigger() {
        return bfg.shoot(getWorldLocation().x, getWorldLocation().y,
                getFacing(), getHeight());
    }

    public MachineGun getBfg()
    {
        return  bfg;
    }

    public void restorePreviousVelocity() {
        if (!isJumping && !isFalling) {
            if (getFacing() == LEFT) {
                isPressingLeft = true;
                setxVelocity(-MAX_X_VELOCITY);
            } else {
                isPressingRight = true;
                setxVelocity(MAX_X_VELOCITY);
            }
        }
    }



    public void setPressingRight(boolean flag) {
        isPressingRight = flag;
    }

    public void setPressingLeft(boolean flag) {
        isPressingLeft = flag;
    }

    public void startJump(SoundManager sm) {
        if (!isFalling && // can't jump if falling
                !isJumping) { // not already jumping?
            isJumping = true;
            jumpTime = System.currentTimeMillis();
            sm.play(SoundManager.Sound.JUMP);
        }
    }

}

