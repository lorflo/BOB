package my.cool.apps.bob;

public class Bullet
{
    private float x;
    private float y;
    private float xVelocity;
    private int direction;

    public Bullet(float x, float y, int speed, int direction) {
        this.direction = direction;
        this.x = x;
        this.y = y;
        if(speed == 0)
        {
            xVelocity = 6;
        }
        else
        this.xVelocity = speed * direction;
    }

    public void update(long fps, float gravity) {
        x += xVelocity / fps;
    }

    public void hideBullet() {
        this.x = -100;
        this.xVelocity = 0;
    }

    public float getX()
    {
        return x ;
    }
    public float getY()
    {
        return y;
    }
    public float getXVelocity()
    {
        return xVelocity ;
    }
    public int getDirection()
    {
        return direction;
    }
}
