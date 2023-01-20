package com.crazyromteam.musicworldrecreation

import com.crazyromteam.musicworldrecreation.anim.BitmapAnim
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.FRAME_TIME
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.deltaTime
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.sleepTime
import com.crazyromteam.musicworldrecreation.anim.FPSAnim.Companion.startTime

class GameThread(private val mGameSurfaceView: GameSurfaceView) : Thread() {
    private var mBitmapAnim = BitmapAnim()
    private val TAG = "GameThread"
    var isruning = true
    // Array of Tuneys and Target Positions
    val targetX: FloatArray = floatArrayOf(338.13f, 290.13f)
    val targetY: FloatArray = floatArrayOf(1011.67f, 963.67f)
    val targetPosition: FloatArray = floatArrayOf(-497f, -425f)
    val circleX: FloatArray = floatArrayOf(360f, 306f)
    val circleY: FloatArray = floatArrayOf(1030.67f, 982.67f)
    val tuneyX: FloatArray = floatArrayOf(343f, 295f)
    val tuneyY: FloatArray = floatArrayOf(1008.67f, 960.67f)
    override fun run() {
        while (isruning) {
            mGameSurfaceView.tryDraw(mGameSurfaceView.holder)
            if (mGameSurfaceView.targetAnimState == mBitmapAnim.maxTargetPositionFrame) {
                mGameSurfaceView.targetAnimState = 0
            }
            if (mGameSurfaceView.tuneyAnimState == mBitmapAnim.maxTuneyFrame) {
                mGameSurfaceView.tuneyAnimState = 0
            }
            mGameSurfaceView.targetAnimState++
            mGameSurfaceView.tuneyAnimState++
            mGameSurfaceView.rediusAnimState =-1

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