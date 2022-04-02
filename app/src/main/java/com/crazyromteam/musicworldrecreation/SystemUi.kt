package com.crazyromteam.musicworldrecreation

import android.R
import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class SystemUi {
    fun hideSystemUI(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(
                window,
                window.decorView.findViewById(R.id.content)
            ).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())

                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
}