package my.cool.apps.bob;

import android.graphics.Rect;

public class Viewport {
    private int pixelsPerMeterX;
    private int pixelsPerMeterY;
    private  int screenXResolution;
    private int screenYResolution;
    private int screenCenterX;
    private int screenCenterY;
    private int metresToShowX;
    private int metresToShowY;
    private int numClipped;
    private Rect convertedRect;
    private Vector2Point5D currentViewportWorldCenter;


    public Viewport(int screenWidth, int screenHieght) {
        screenXResolution = screenWidth;
        screenYResolution = screenHieght;
        screenCenterX = screenXResolution / 2;
        screenCenterY = screenYResolution / 2;
        pixelsPerMeterX = screenXResolution / 32;
        pixelsPerMeterY = screenYResolution / 18;
        metresToShowX = 34;
        metresToShowY = 20;
        convertedRect = new Rect();
        currentViewportWorldCenter = new Vector2Point5D();
    }

    public void setWorldCenter(float x, float y){
        currentViewportWorldCenter.x  = x;
    currentViewportWorldCenter.y  = y;
    }

    public Rect worldToScreen(float x, float y, float width, float height) {
        int left = (int) (screenCenterX - (currentViewportWorldCenter.x - x) * pixelsPerMeterX);
        int top = (int) (screenCenterY - (currentViewportWorldCenter.y - y) * pixelsPerMeterY);
        int right = (int) (left + width * pixelsPerMeterX);
        int bottom = (int) (top + height * pixelsPerMeterY);
        convertedRect.set(left, top, right, bottom);
        return convertedRect;
    }
    public boolean clipObject(float x, float y, float width, float height) {
        boolean isInside = (x - width < currentViewportWorldCenter.x + metresToShowX / 2)
                && (x + width > currentViewportWorldCenter.x - metresToShowX / 2)
                && (y - height < currentViewportWorldCenter.y + metresToShowY / 2)
                && (y + height > currentViewportWorldCenter.y - metresToShowY / 2);
        if (!isInside) { // for debugging
            numClipped++;
        }
        return !isInside;
    }

    public boolean clipObjects(float objectX,   float objectY,   float objectWidth,   float objectHeight) {
        boolean clipped = true;
        if (objectX - objectWidth < currentViewportWorldCenter.x + (metresToShowX / 2)) {
            if (objectX + objectWidth > currentViewportWorldCenter.x - (metresToShowX / 2)) {
                if (objectY - objectHeight < currentViewportWorldCenter.y + (metresToShowY / 2)) {
                    if (objectY + objectHeight > currentViewportWorldCenter.y - (metresToShowY / 2)) {
                        clipped = false;
                    }
                }
            }
        }
        // For debugging
        if(clipped){
            numClipped++;
        }
        return clipped;
    }
    public void moveViewportRight(int maxWidth) {
        if (currentViewportWorldCenter.x < maxWidth - metresToShowX /2 +3) {
            currentViewportWorldCenter.x ++;
        }
    }
    public void moveViewportLeft() {
        if (currentViewportWorldCenter.x > metresToShowX / 2 - 3 ) {
            currentViewportWorldCenter.x --;
        }
    }
    public void moveViewportUp() {
        if (currentViewportWorldCenter.y > metresToShowY / 2 - 3) {
            currentViewportWorldCenter.y --;
        }
    }
    public void moveViewportDown(int maxHeight) {
        if (currentViewportWorldCenter.y < maxHeight - metresToShowY / 2 + 3) {
            currentViewportWorldCenter.y ++;
        }
    }


    public int getScreenWidth(){
        return  screenXResolution;
    }

    public int getScreenHeight(){
        return  screenYResolution;
    }

    public int getPixelsPerMetreX(){
        return  pixelsPerMeterX;
    }


    public float getyCentre() {
        return 0;
    }

    public float getViewportWorldCentreY() {
        return currentViewportWorldCenter.y;
    }

    public float getPixelsPerMetreY() {
        return pixelsPerMeterY;
    }
}
