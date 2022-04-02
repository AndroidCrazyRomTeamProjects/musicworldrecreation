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
        SystemUi().hideSystemUI(window)
        gamesound = MediaPlayer.create(this.applicationContext, R.raw.morning_dew)
        gamesound?.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        gamesound?.stop()
        mTitleActivity?.titlesound?.start()
    }
}