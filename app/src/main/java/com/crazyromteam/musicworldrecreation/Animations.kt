package com.crazyromteam.musicworldrecreation

import android.graphics.BitmapFactory

class Animations {
    val mGameActivity = GameActivity()
    var targetPositionAnimFrame = 0
    fun TargetPosition() : Int {
        when (mGameActivity.targetAnimState) {
            1 -> targetPositionAnimFrame = R.drawable.target_position_anim_1
            2 -> targetPositionAnimFrame = R.drawable.target_position_anim_2
            3 -> targetPositionAnimFrame = R.drawable.target_position_anim_3
            4 -> targetPositionAnimFrame = R.drawable.target_position_anim_4
            5 -> targetPositionAnimFrame = R.drawable.target_position_anim_5
            6 -> targetPositionAnimFrame = R.drawable.target_position_anim_6
            7 -> targetPositionAnimFrame = R.drawable.target_position_anim_7
            8 -> targetPositionAnimFrame = R.drawable.target_position_anim_8
        }
        return targetPositionAnimFrame
    }
}