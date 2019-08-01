package my.cool.apps.bob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class Background {
    Bitmap bitmap;
    Bitmap bitmapReversed;
    int width; // of bitmap
    int height;
    boolean reversedFirst;
    int xClip; // clip the bitmap
    float y;
    float endY;
    int z;
    float speed;
    boolean isParallax;  // not used

    Background(Context context, int yPixelsPerMetre,
               int screenWidth, BackgroundData data) {
        int resID = context.getResources().getIdentifier
                (data.bitmapName, "drawable", context.getPackageName());
        bitmap = BitmapFactory.decodeResource(context.getResources(), resID);
        reversedFirst = false;
        xClip = 0;
        y = data.startY;
        endY = data.endY;
        z = data.layer;
        isParallax = data.isParallax;
        speed = data.speed;
        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth,
               (int)(endY* yPixelsPerMetre), true);
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        // create a mirror image of the background
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1); // horizontal mirror effect.
        bitmapReversed = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
    }


}
