package com.crazyromteam.musicworldrecreation

import android.app.Activity
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View

class GameActivity : Activity() {
    var gamesound = null as MediaPlayer?
    var mTitleActivity = null as TitleActivity?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mTitleActivity = TitleActivity()
        window.decorView.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                systemUiVisibility =
                    View.SYSTEM_UI_FLAG_FULLSCREEN
            } else {
                systemUiVisibility =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
        }
        gamesound = MediaPlayer.create(this.applicationContext, R.raw.morning_dew)
        gamesound?.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        gamesound?.stop()
        mTitleActivity?.titlesound?.start()
    }
}