package com.crazyromteam.musicworldrecreation

class Animations {
    fun TargetPosition(mGameSurfaceView: GameSurfaceView) : Int {
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
}