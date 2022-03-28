package com.crazyromteam.musicworldrecreation

import java.lang.Exception

class GameThread(val mGameSurfaceView: GameSurfaceView) : Thread() {
    val mGameActivity = GameActivity()
    private val TAG = "GameThread"
    var targetPosition = -497f
    override fun run() {
        super.run()
        val FPS: Long = 15
        val FRAME_TIME = 1000 / FPS * 1000000L
        var startTime = System.nanoTime()
        var deltaTime: Long
        var sleepTime: Long
        while (mGameSurfaceView.position != targetPosition && !mGameSurfaceView.isClicked) {
            mGameSurfaceView.TryDraw(mGameSurfaceView.holder)
            if (mGameActivity.targetAnimState == 8) {
                mGameActivity.targetAnimState = 0
            }
            mGameActivity.targetAnimState++
            deltaTime = System.nanoTime() - startTime
            sleepTime = (FRAME_TIME - deltaTime) / 1000000L
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            startTime = System.nanoTime()
        }
    }
}