package my.cool.apps.bob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public abstract class GameObject {
    private Vector2Point5D worldLocation;
    private  float  width;// in meters
    private float height;
    private  boolean active = true;
    private  boolean visible = true;
    private int animFrameCount = 1;
    private char type;
    private String bitmapName;
    private float xVelocity;
    private float yVelocity;
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    private int facing;
    private boolean moves;
    private RectHitbox rectHitbox = new RectHitbox();
    // Most objects only have 1 frame
    // And don't need to bother with these
    private Animation anim = null;
    private boolean animated;
    private int animFps = 1;
    private boolean traversable = false;



    public void update(long fps, float gravity){}

    public void setAnimFps(int animFps) { this.animFps = animFps; }
    public void setAnimFrameCount(int animFrameCount) { this.animFrameCount = animFrameCount; }
    public boolean isAnimated() { return animated; }
    public void setAnimated(Context context, int pixelsPerMetre,  boolean animated) {
        this.animated = animated;
        this.anim = new Animation(context, bitmapName, height,
                width, animFps, animFrameCount, pixelsPerMetre);
    }

    public Rect getRectToDraw(long deltaTime) {
        return anim.getCurrentFrame(deltaTime, xVelocity,yVelocity, isMoves());
    }


        /** To be called by update(). */
    public void move(long fps) {
        if (xVelocity != 0) {
            worldLocation.x += xVelocity / fps;
        }
        if (yVelocity != 0) {
            worldLocation.y += yVelocity / fps;
        }
    }

    public Bitmap prepareBitmap(Context context,
                                String bitmapName, int pixelsPerMeter) {
        int resId = context.getResources().getIdentifier(bitmapName,
                "drawable", context.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(
                context.getResources(), resId);
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (width * animFrameCount * pixelsPerMeter),
                (int) (height * pixelsPerMeter),
                false);
        System.out.println(height * pixelsPerMeter);
        return bitmap;
    }

    public void setRectHitbox() {
        rectHitbox.setTop(worldLocation.y);
        rectHitbox.setLeft(worldLocation.x);
        rectHitbox.setBottom(worldLocation.y + height);
        rectHitbox.setRight(worldLocation.x + width);
    }
    public RectHitbox getHitbox() {
        return rectHitbox;
    }

    public Vector2Point5D getWorldLocation() {
        return worldLocation;
    }
    public void setWorldLocation(float x, float y, int z)
    {
        this.worldLocation = new Vector2Point5D();
        this.worldLocation.x = x;
        this.worldLocation.y = y;
        this.worldLocation.z = z;
    }
    public void setWorldLocationY(float y)
    {
        this.worldLocation.y = y;
    }
    public void setWorldLocationX(float x)
    {
        this.worldLocation.x = x;
    }

    public String getBitmapName() {
        return bitmapName;
    }
    public void setBitmapName(String turf)
    {
        bitmapName = turf;
    }

    public float getHeight() {
        return height;
    }
    public void setHeight(float height)
    {

        this.height = height;
    }

    public float getWidth() {
        return width;
    }
    public void setWidth(float width)
    {
        this.width = width;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }
    public float getxVelocity() {  return xVelocity; }
    public void setxVelocity(float xVelocity) {
        // Only allow for objects that can move
        if (moves) {
            this.xVelocity = xVelocity;
        }
    }
    public float getyVelocity() {  return yVelocity; }
    public void setyVelocity(float yVelocity) {
        // Only allow for objects that can move
        if (moves) {
            this.yVelocity = yVelocity;
        }
    }
    public boolean isMoves() {  return moves; }
    public void setMoves(boolean moves) {  this.moves = moves; }


    public void setFacing(int facing)
    {
        this.facing = facing;
    }
    public int getFacing()
    {
        return facing;
    }

    public void setTraversable(){
        traversable = true;
    }
    public boolean isTraversable(){
        return traversable;
    }


}
