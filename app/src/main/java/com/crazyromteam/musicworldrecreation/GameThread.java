package com.crazyromteam.musicworldrecreation;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class GameThread extends Thread implements View.OnTouchListener {
    private final GameSurfaceView mGameSurfaceView;
    private String TAG = "GameThread";
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float previousX;
    private float previousY;
    private MotionEvent event;
    private View mView;
    private Canvas canvas;


    public GameThread(GameSurfaceView mGameSurfaceView) {
        this.mGameSurfaceView = mGameSurfaceView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            return false;
    }

    @Override
    public void run() {
        super.run();
        do {
            mGameSurfaceView.TryDraw(mGameSurfaceView.getHolder());
            try {
                sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(!onTouch(mView, event));
    }

}