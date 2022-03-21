package com.crazyromteam.musicworldrecreation;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class GameThread extends Thread implements View.OnTouchListener {
    public final GameSurfaceView mGameSurfaceView;
    private final String TAG = "GameThread";
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float previousX;
    private float previousY;
    public int targetposition = -497;


    public GameThread(GameSurfaceView mGameSurfaceView) {
        this.mGameSurfaceView = mGameSurfaceView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
            return true;
        }
            return false;
    }

    @Override
    public void run() {
        super.run();
        while(mGameSurfaceView.possition > targetposition) {
            mGameSurfaceView.TryDraw(mGameSurfaceView.getHolder());
            if (mGameSurfaceView.animstate == 8) {
                mGameSurfaceView.animstate = 0;
            }
            mGameSurfaceView.animstate++;
            try {
                sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}