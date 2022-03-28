package com.crazyromteam.musicworldrecreation

import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.FRAME_TIME
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.deltaTime
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.sleepTime
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.startTime
import java.lang.Exception

class GameThread(val mGameSurfaceView: GameSurfaceView) : Thread() {
    private val TAG = "GameThread"
    var targetPosition = -497f
    override fun run() {
        super.run()
        while (mGameSurfaceView.position != targetPosition && !mGameSurfaceView.isClicked) {
            mGameSurfaceView.TryDraw(mGameSurfaceView.holder)
            if (mGameSurfaceView.targetAnimState == 8) {
                mGameSurfaceView.targetAnimState = 0
            }
            mGameSurfaceView.targetAnimState++
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