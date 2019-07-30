package my.cool.apps.bob;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    private PlatformView platformView;
    public static SoundManager soundManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            Display display = getWindowManager().getDefaultDisplay();
            Point resolution = new Point();
            display.getSize(resolution);
            platformView = new PlatformView(this, resolution.x, resolution.y);
            soundManager  = SoundManager.instance(this);
            setContentView(platformView);

    }
    @Override
    protected void onPause() {
        super.onPause();
        platformView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        platformView.resume();
    }
}
