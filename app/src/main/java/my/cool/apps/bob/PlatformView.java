package my.cool.apps.bob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class PlatformView extends SurfaceView implements Runnable {
    private boolean paused;
    private volatile boolean running;
    private Thread gameThread = null;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;

    private Context context;
    private long startFrameTime;
    private long timeThisFrame;
    private long fps;

    private LevelManager lm;
    private Viewport vp;
    private InputController ic;

    private SoundManager soundManager;
    private PlayerState ps;



    public PlatformView(Context context, int screenWidth, int screenHeight) {
        super(context);
        this.context = context;
        holder = getHolder();
        paint = new Paint();
        vp = new Viewport(screenWidth, screenHeight);
        ps = new PlayerState();
        loadLevel("LevelCave", 15, 2);
        soundManager = SoundManager.instance(context);
    }

    private void loadLevel(String level, float px, float py) {
        PointF location = new PointF(px, py);
        ps.saveLocation(location);

        ic = new InputController(vp.getScreenWidth(), vp.getScreenHeight());
        lm = new LevelManager(context, vp.getPixelsPerMetreX(),
                vp.getScreenWidth(), ic, level, px, py);
        vp.setWorldCenter(
                lm.gameObjects.get(lm.playerIndex).getWorldLocation().x,
                lm.gameObjects.get(lm.playerIndex).getWorldLocation().y);
    }

    @Override
    public void run() {
        while (running) {
            startFrameTime = System.currentTimeMillis();
            update();
            draw();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    private void update() {
        for (GameObject go: lm.getGO()) {
            if (go.isActive()) {
                boolean clipped = vp.clipObject(go.getWorldLocation().x, go.getWorldLocation().y,
                        go.getWidth(), go.getHeight());
                go.setVisible(!clipped);
                if (!vp.clipObject(go.getWorldLocation().x, go.getWorldLocation().y,
                        go.getWidth(), go.getHeight()))
                {
                    go.setVisible(true);
                    // check collisions with player
                    int hit = lm.getPlayer().checkCollisions(go.getHitbox());
                    if (hit > 0) {
                        switch (go.getType()) {
                            case 'c':
                                soundManager.play(SoundManager.Sound.COIN_PICKUP);
                                go.setActive(false);
                                go.setVisible(false);
                                ps.gotCredit();
                                if (hit != 2) { // hit not by feet
                                    lm.getPlayer().restorePreviousVelocity();
                                }
                                break;
                            case 'e':
                                go.setActive(false);
                                go.setVisible(false);
                                soundManager.play(SoundManager.Sound.EXTRA_LIFE);
                                ps.addLife();
                                if (hit != 2) {
                                    lm.getPlayer().restorePreviousVelocity();
                                }
                                break;
                            case 'd':
                                soundManager.play(SoundManager.Sound.EXPLODE);
                                ps.loseLife();
                                PointF loc= new PointF(ps.loadLocation().x,
                                        ps.loadLocation().y);
                                lm.getPlayer().setWorldLocationX(loc.x);
                                lm.getPlayer().setWorldLocationY(loc.y);
                                lm.getPlayer().setxVelocity(0);
                                break;
                            case 'g':
                                soundManager.play(SoundManager.Sound.EXPLODE);
                                ps.loseLife();
                                loc = new PointF(ps.loadLocation().x, ps.loadLocation().y);
                                lm.getPlayer().setWorldLocationX(loc.x);
                                lm.getPlayer().setWorldLocationY(loc.y);
                                lm.getPlayer().setxVelocity(0);
                                break;

                            case 'u':
                                soundManager.play(SoundManager.Sound.GUN_UPGRADE);
                                go.setActive(false);
                                go.setVisible(false);
                                lm.getPlayer().getBfg().upgradeRateOfFire();
                                ps.increaseFireRate();

                            default:
                                if (hit == 1) { // left or right
                                    lm.getPlayer().setxVelocity(0);
                                    lm.getPlayer().setPressingRight(false);
                                }
                                if (hit == 2) { // feet
                                    lm.getPlayer().isFalling = false;
                                }
                                break;
                            case 'f':
                                soundManager.play(SoundManager.Sound.EXPLODE);
                                ps.loseLife();
                                lm.getPlayer().setWorldLocationX(ps.loadLocation().x);
                                lm.getPlayer().setWorldLocationY(ps.loadLocation().y);
                                lm.getPlayer().setxVelocity(0);
                                break;

                        }

                    }
                    for (int i = 0; i < lm.getPlayer().getBfg().getNumBullets(); i++) {
                        RectHitbox r = new RectHitbox();
                        r.setLeft(lm.getPlayer().getBfg().getBulletX(i));
                        r.setTop(lm.getPlayer().getBfg().getBulletY(i));
                        r.setRight(lm.getPlayer().getBfg().getBulletX(i) + .1f);
                        r.setBottom(lm.getPlayer().getBfg().getBulletY(i) + .1f);
                        if (go.getHitbox().intersects(r)) {
                            lm.getPlayer().getBfg().hideBullet(i);
                            switch (go.getType()) {
                                case 'g': // guard
                                    go.setWorldLocationX(go.getWorldLocation().x + 2 * (lm.getPlayer().getBfg().getDirection(i)));
                                    soundManager.play(SoundManager.Sound.HIT_GUARD);
                                    break;
                                case 'd': // drone
                                    soundManager.play(SoundManager.Sound.EXPLODE);
                                    go.setWorldLocation(-100, -100, 0);
                                    break;
                                default:
                                    soundManager.play(SoundManager.Sound.RICOCHET);
                            }
                        }
                    }

                    if (lm.isPlaying())
                     {
                     go.update(fps, lm.getGravity());
                         vp.setWorldCenter(
                                 lm.getPlayer().getWorldLocation().x,
                                 lm.getPlayer().getWorldLocation().y);
                         if (go.getType() == 'd') {
                             Drone d = (Drone) go;
                             d.setWaypoint(lm.getPlayer().getWorldLocation());
                         }

                     }
                }
                else {
                    go.setVisible(false);
                }
            }
        }
    }

        private void draw(){

        if (holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            paint.setColor(Color.argb(255, 0, 0, 255));
            canvas.drawColor(Color.argb(255, 0, 0, 255));
            Rect toScreen2d = new Rect();

            drawBackground(0, -3);  // behind Bob (at z=0)

            for (int layer = -1; layer <= 1; layer++) {
                for (GameObject go : lm.gameObjects) {
                    if (go.isVisible() && go.getWorldLocation().z == layer) {
                        toScreen2d.set(vp.worldToScreen(go.getWorldLocation().x,go.getWorldLocation().y,
                                go.getWidth(), go.getHeight()));
                        if (go.isAnimated())
                        {
                            if (go.getFacing() == GameObject.RIGHT)
                            { // rotate and draw?
                                Matrix flipper = new Matrix();
                                flipper.preScale(-1, 1);
                                Rect r = go.getRectToDraw(System.currentTimeMillis());
                                Bitmap b = Bitmap.createBitmap(lm.getBitmap(go.getType()),
                                        r.left, r.top, r.width(), r.height(), flipper, true);
                                canvas.drawBitmap(b, toScreen2d.left, toScreen2d.top, paint);
                            }
                            else
                                {
                                canvas.drawBitmap(lm.getBitmap(go.getType()),
                                        go.getRectToDraw(System.currentTimeMillis()), toScreen2d, paint);
                                }
                        }
                        else
                            {
                                // no animation; just draw the whole bitmap
                            canvas.drawBitmap(lm.getBitmap(go.getType()),
                                   toScreen2d.left, toScreen2d.top, paint);
                            }
                    }
                }
                drawBackground(4, 0);   // in front of Bob
            }
            //draw buttons
            paint.setColor(Color.argb(80, 255, 255, 255));
            ArrayList<Rect> buttonsToDraw;
            buttonsToDraw = ic.getButtons();
            for (Rect rect : buttonsToDraw)
            {
                RectF rf = new RectF(rect.left, rect.top, rect.right, rect.bottom);
                canvas.drawRoundRect(rf, 15f, 15f, paint);
            }
            // draw paused text
            if (paused) {
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setColor(Color.argb(255, 255, 255, 255));
                paint.setTextSize(120);
                canvas.drawText("Paused", vp.getScreenWidth() / 2,
                        vp.getScreenHeight() / 2, paint);
            }

            Rect bullet = new Rect();
            paint.setColor(Color.argb(255, 255, 255, 255));
            for (int i = 0; i < lm.getPlayer().getBfg().getNumBullets(); i++) {
                bullet.set(vp.worldToScreen(lm.getPlayer().getBfg().getBulletX(i), lm.getPlayer().getBfg().getBulletY(i),
                        .25f, .05f)); // bullet width and height
                canvas.drawRect(bullet, paint);
            }


            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawBackground(int start, int stop) {
        Rect fromRect1 = new Rect(), toRect1 = new Rect();
        Rect fromRect2 = new Rect(), toRect2 = new Rect();
        for (Background bg: lm.backgrounds) {
            if (bg.z < start && bg.z > stop) {

                // clip anything off-screen
                if (!vp.clipObject(-1, bg.y, 1000, bg.height)) {
                    int startY = (int) (vp.getyCentre() -  (vp.getViewportWorldCentreY() - bg.y) *
                            vp.getPixelsPerMetreY());
                    int endY = (int) (vp.getyCentre() - (vp.getViewportWorldCentreY() - bg.endY) *
                            vp.getPixelsPerMetreY());

                    // define what portion of bitmaps to capture and what coordinates to draw them at
                    fromRect1 = new Rect(0, 0, bg.width - bg.xClip,  bg.height);
                    toRect1 = new Rect(bg.xClip, startY, bg.width, endY);
                    fromRect2 = new Rect(bg.width - bg.xClip, 0, bg.width, bg.height);
                    toRect2 = new Rect(0, startY, bg.xClip, endY);
                }
                // draw backgrounds
                if (!bg.reversedFirst) {
                    canvas.drawBitmap(bg.bitmap, fromRect1, toRect1, paint);
                    canvas.drawBitmap(bg.bitmapReversed, fromRect2, toRect2, paint);
                } else {
                    canvas.drawBitmap(bg.bitmap, fromRect2, toRect2, paint);
                    canvas.drawBitmap(bg.bitmapReversed, fromRect1, toRect1, paint);
                }

                // calculate the next value for the background's clipping position by modifying xClip
                // and switching which background is drawn first, if necessary.
                bg.xClip -= lm.getPlayer().getxVelocity() / (20 / bg.speed);
                if (bg.xClip >= bg.width) {
                    bg.xClip = 0;
                    bg.reversedFirst = !bg.reversedFirst;
                } else if (bg.xClip <= 0) {
                    bg.xClip = bg.width;
                    bg.reversedFirst = !bg.reversedFirst;
                }
            }
        }
    }


                @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (lm != null) {
            ic.handleInput(motionEvent, lm, soundManager, vp);
            if(!lm.isPlaying())
            {
                paused = true;
                //pause();
            }
            else {
                if(paused)
                {
                   // resume();
                    paused = false;
                }
            }
        }

        return true;
    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("error", "failed to pause thread");
        }
    }

    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}


