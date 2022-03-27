package com.crazyromteam.musicworldrecreation;


public class GameThread extends Thread {
    public final GameSurfaceView mGameSurfaceView;
    private final String TAG = "GameThread";
    public int targetPosition = -497;


    public GameThread(GameSurfaceView mGameSurfaceView) {
        this.mGameSurfaceView = mGameSurfaceView;
    }

    @Override
    public void run() {
        super.run();
        long FPS = 15;
        long FRAME_TIME = 1000 / FPS * 1000000L;
        long startTime = System.nanoTime();
        long deltaTime;
        long sleepTime;
        while (mGameSurfaceView.position > targetPosition) {
            mGameSurfaceView.TryDraw(mGameSurfaceView.getHolder());
            if (mGameSurfaceView.animState == 8) {
                mGameSurfaceView.animState = 0;
            }
            mGameSurfaceView.animState++;
            deltaTime = System.nanoTime() - startTime;
            sleepTime = (FRAME_TIME - deltaTime) / 1000000L;
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            startTime = System.nanoTime();
        }
    }
}