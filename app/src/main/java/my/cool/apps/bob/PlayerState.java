package my.cool.apps.bob;

import android.graphics.PointF;

public class PlayerState
{
    private int numCredits;
    private int mgFireRate;
    private int lives;
    private int sheild;
    private float restartX = 0;
    private float restartY = 0;
    private PointF loc;

    public PlayerState() {
        lives = 3;
        mgFireRate = 1;
        numCredits = 0;
        loc = new PointF(restartX,restartY);
    }

    public void saveLocation(PointF location)
    {
        restartX = location.x;
        restartY = location.y;
        loc.set(restartX,restartY);
    }

    public PointF loadLocation()
    {

        return loc;

    }

    public void increaseFireRate()
    {
        mgFireRate++;
    }
    public int getMgFireRate()
    {
        return mgFireRate;
    }

    public int getNumCredits()
    {
        return numCredits;
    }
    public void gotCredit()
    {
         numCredits++;
    }

    public void addLife()
    {
        lives++;
    }
    public void loseLife()
    {
        lives--;
    }
    public int getLives()
    {
        return lives;
    }


}
