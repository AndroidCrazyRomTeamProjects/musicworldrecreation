package com.crazyromteam.musicworldrecreation;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class GameThread extends Thread {
    public final GameSurfaceView mGameSurfaceView;
    private final String TAG = "GameThread";
    public int targetposition = -497;


    public GameThread(GameSurfaceView mGameSurfaceView) {
        this.mGameSurfaceView = mGameSurfaceView;
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