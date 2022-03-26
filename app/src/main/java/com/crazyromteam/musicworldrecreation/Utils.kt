package com.crazyromteam.musicworldrecreation

import android.content.Context
import android.util.DisplayMetrics

class Utils {
    fun convertDpToPixel(dp: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun convertPixelToDp(pixel: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return pixel / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}