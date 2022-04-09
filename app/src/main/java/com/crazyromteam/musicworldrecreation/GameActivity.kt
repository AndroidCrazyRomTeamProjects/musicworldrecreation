package com.crazyromteam.musicworldrecreation

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle

class GameActivity : Activity() {
    private var gameSound = null as MediaPlayer?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        SystemUi().hideSystemUI(window)
        gameSound = MediaPlayer.create(this.applicationContext, R.raw.morning_dew)
        gameSound?.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        gameSound?.stop()
        val titleActivity = Intent(this, TitleActivity::class.java)
        startActivity(titleActivity)
        finish()
    }

    override fun onUserLeaveHint() {
        gameSound?.stop()
    }
}