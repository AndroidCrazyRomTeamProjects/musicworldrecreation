package com.crazyromteam.musicworldrecreation

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle

class GameActivity : Activity() {
    var gamesound = null as MediaPlayer?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        SystemUi().hideSystemUI(window)
        gamesound = MediaPlayer.create(this.applicationContext, R.raw.morning_dew)
        gamesound?.start()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        gamesound?.stop()
        val titleactivity = Intent(this, TitleActivity::class.java)
        startActivity(titleactivity)
        finish()
    }

    override fun onUserLeaveHint() {
        gamesound?.stop()
    }
}