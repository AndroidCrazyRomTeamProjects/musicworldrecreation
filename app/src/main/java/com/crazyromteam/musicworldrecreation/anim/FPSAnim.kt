package com.crazyromteam.musicworldrecreation.anim

class FPSAnim {
    companion object {
        val FPS: Long = 15
        val FRAME_TIME = 1000 / FPS * 1000000L
        var startTime = System.nanoTime()
        var deltaTime: Long = 0
        var sleepTime: Long = 0
    }
}