package com.crazyromteam.musicworldrecreation

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_title.*

class TitleActivity : Activity() {
    private var titleSound = null as MediaPlayer?
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
        val animatedBackgroundImage = (title_view.background as AnimationDrawable)
        animatedBackgroundImage.start()
        titleSound = MediaPlayer.create(this.applicationContext, R.raw.title_screen)
        titleSound?.start()

        SystemUi().hideSystemUI(window)
        val titleText = findViewById<View>(R.id.textstart) as TextView

        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 600 //You can manage the blinking time with this parameter

        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        titleText.startAnimation(anim)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.exit_music_world))
        builder.setMessage(resources.getString(R.string.exit_music_world_confirmation))
            .setCancelable(false)
            .setPositiveButton(
                resources.getString(R.string.yes)
            ) { dialog, id -> this@TitleActivity.finish(); titleSound?.stop() }
            .setNegativeButton(
                resources.getString(R.string.no)
            ) { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    override fun onUserLeaveHint() {
        titleSound?.stop()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if ((event.action != MotionEvent.ACTION_MOVE)) {
                titleSound?.stop()
                val gameActivity = Intent(this, GameActivity::class.java)
                startActivity(gameActivity)
                finish()
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}