package my.cool.apps.bob;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;

public class InputController {
    private Rect left, right, jump, shoot, pause;
    private LevelManager lm;

    public InputController(int screenWidth, int screenHeight) {
        //Configure the player buttons
        int buttonWidth = screenWidth / 8;
        int buttonHeight = screenHeight / 7;
        int buttonPadding = screenWidth / 80;
        left = new Rect(buttonPadding, screenHeight - buttonHeight - buttonPadding,
                buttonWidth, screenHeight - buttonPadding);

        right = new Rect(buttonWidth + buttonPadding, screenHeight - buttonHeight - buttonPadding,
                buttonWidth + buttonPadding + buttonWidth, screenHeight - buttonPadding);

        jump = new Rect(screenWidth - buttonWidth - buttonPadding, screenHeight - buttonHeight - buttonPadding - buttonHeight - buttonPadding,
                screenWidth - buttonPadding, screenHeight - buttonPadding - buttonHeight - buttonPadding);

        shoot = new Rect(screenWidth - buttonWidth - buttonPadding, screenHeight - buttonHeight - buttonPadding,
                screenWidth - buttonPadding, screenHeight - buttonPadding);

        pause = new Rect(screenWidth - buttonPadding - buttonWidth, buttonPadding,
                screenWidth - buttonPadding, buttonPadding + buttonHeight);

    }

    public ArrayList getButtons() {   //create an array of buttons for the draw method
        ArrayList<Rect> currentButtonList = new ArrayList<>();
        currentButtonList.add(left);
        currentButtonList.add(right);
        currentButtonList.add(jump);
        currentButtonList.add(shoot);
        currentButtonList.add(pause);
        return currentButtonList;
    }

    public void handleInput(MotionEvent event, LevelManager lm, SoundManager sm, Viewport vp) {
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);
            if (lm.isPlaying()) {
                switch (event.getAction() & event.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (right.contains(x, y)) {
                            lm.getPlayer().setPressingRight(true);
                            lm.getPlayer().setPressingLeft(false);
                        } else if (left.contains(x, y)) {
                            lm.getPlayer().setPressingLeft(true);
                            lm.getPlayer().setPressingRight(false);
                        } else if (jump.contains(x, y)) {
                            lm.getPlayer().startJump(sm);
                        } else if (shoot.contains(x, y)) {
                            if (lm.getPlayer().pullTrigger()) {
                                sm.play(SoundManager.Sound.SHOOT);
                            }
                        } else if (pause.contains(x, y)) {
                            lm.switchPlayingStatus();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (right.contains(x, y)) {
                            lm.getPlayer().setPressingRight(false);
                        } else if (left.contains(x, y)) {
                            lm.getPlayer().setPressingLeft(false);
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (right.contains(x, y)) {
                            lm.getPlayer().setPressingRight(true);
                            lm.getPlayer().setPressingLeft(false);
                        } else if (left.contains(x, y)) {
                            lm.getPlayer().setPressingLeft(true);
                            lm.getPlayer().setPressingRight(false);
                        } else if (jump.contains(x, y)) {
                            lm.getPlayer().startJump(sm);
                        } else if (shoot.contains(x, y)) {
                            if (lm.getPlayer().pullTrigger()) {
                                sm.play(SoundManager.Sound.SHOOT);
                            }
                        } else if (pause.contains(x, y)) {
                            lm.switchPlayingStatus();
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        if (right.contains(x, y)) {
                            lm.getPlayer().setPressingRight(false);
                            Log.w("rightP:", "up");
                        } else if (left.contains(x, y)) {
                            lm.getPlayer().setPressingLeft(false);
                            Log.w("leftP:", "up");
                        } else if (shoot.contains(x, y)) {
                            if (lm.getPlayer().pullTrigger()) {
                                sm.play(SoundManager.Sound.SHOOT);
                            }
                        } else if (jump.contains(x, y)) {
                            lm.getPlayer().startJump(sm);
                        }
                        break;
                }
            } else {
                // Not playing
                // Move the viewport around to explore the map
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                            if (right.contains(x, y)) {
                                vp.moveViewportRight(lm.getMapWidth());
                            } else if (left.contains(x, y)) {
                                vp.moveViewportLeft();
                            } else if (jump.contains(x, y)) {
                                vp.moveViewportUp();
                            } else if (shoot.contains(x, y)) {
                                vp.moveViewportDown(lm.getMapHeight());
                            } else if (pause.contains(x, y)) {
                                lm.switchPlayingStatus();
                            }
                        break;
                }
            }
        }
    }
}
