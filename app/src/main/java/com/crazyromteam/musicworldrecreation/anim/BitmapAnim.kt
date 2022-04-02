package com.crazyromteam.musicworldrecreation.anim

import com.crazyromteam.musicworldrecreation.GameSurfaceView
import com.crazyromteam.musicworldrecreation.R

class BitmapAnim {
    val maxTargetPositionFrame = 8
    val maxTuneyTspBlueFrame = 5

    fun targetPosition(mGameSurfaceView: GameSurfaceView) : Int {
        when (mGameSurfaceView.targetAnimState) {
            1 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_1
            2 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_2
            3 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_3
            4 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_4
            5 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_5
            6 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_6
            7 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_7
            8 -> mGameSurfaceView.targetPositionAnimFrame = R.drawable.target_position_anim_8
        }
        return mGameSurfaceView.targetPositionAnimFrame
    }
    fun tuneyTapBlue(mGameSurfaceView: GameSurfaceView, Frame: Int) : Int {
        when (Frame) {
            1 -> mGameSurfaceView.tuneyTapBlueAnimFrame = R.drawable.tuney_tap_blue_anim_1
            2 -> mGameSurfaceView.tuneyTapBlueAnimFrame = R.drawable.tuney_tap_blue_anim_2
            3 -> mGameSurfaceView.tuneyTapBlueAnimFrame = R.drawable.tuney_tap_blue_anim_3
            4 -> mGameSurfaceView.tuneyTapBlueAnimFrame = R.drawable.tuney_tap_blue_anim_4
            5 -> mGameSurfaceView.tuneyTapBlueAnimFrame = R.drawable.tuney_tap_blue_anim_5
        }
        return mGameSurfaceView.tuneyTapBlueAnimFrame
    }
}