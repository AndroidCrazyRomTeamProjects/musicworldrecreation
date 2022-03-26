package com.crazyromteam.musicworldrecreation

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_title.*


class TitleActivity : Activity() {
    var titlesound = null as MediaPlayer?
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
        val animatedBackroundImage = (title_view.background as AnimationDrawable)
        animatedBackroundImage.start()
        titlesound = MediaPlayer.create(this.applicationContext, R.raw.title_screen)
        titlesound?.start()
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            val titletext = findViewById<View>(R.id.textstart) as TextView

            val anim: Animation = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 600 //You can manage the blinking time with this parameter

            anim.startOffset = 20
            anim.repeatMode = Animation.REVERSE
            anim.repeatCount = Animation.INFINITE
            titletext.startAnimation(anim)
        }

    }

    fun TitleClicked(view: View) {
        titlesound?.stop()
        val gameactivity = Intent(this, GameActivity::class.java)
        startActivity(gameactivity)
    }
}