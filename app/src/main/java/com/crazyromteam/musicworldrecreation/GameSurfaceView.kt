package com.crazyromteam.musicworldrecreation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurfaceView : SurfaceView, SurfaceHolder.Callback {
    private var mGameThread: GameThread? = null
    private val TAG = "GameSurfaceView"
    var tuneyPaint = Paint()
    var targetPaint = Paint()
    var tuneyDeadPaint = Paint()
    var tuney = BitmapFactory.decodeResource(resources, R.drawable.tuney_basic)
    val tuneyDead_anim_1 = BitmapFactory.decodeResource(resources, R.drawable.tuney_dead_anim_1)
    val tuneyDead_anim_2 = BitmapFactory.decodeResource(resources, R.drawable.tuney_dead_anim_2)
    var tuneyClicked = BitmapFactory.decodeResource(resources, R.drawable.tuney_tap_anim_1)
    var mUtils: Utils? = null
    var mAnimations: Animations? = null

    @JvmField
    var position = 1f
    var isTuneyClicked = false

    @JvmField
    var isClicked = false

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        holder.addCallback(this)
        mGameThread = GameThread(this)
        mUtils = Utils()
        mAnimations = Animations()
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSLUCENT)
    }

    fun TryDraw(holder: SurfaceHolder) {
        val mCanvas = holder.lockCanvas()
        holder.addCallback(this)
        if (mCanvas != null) {
            drawTuney(mCanvas)
            holder.unlockCanvasAndPost(mCanvas)
        }
    }

    fun drawTuney(canvas: Canvas) {
        super.draw(canvas)
        val targetpositionanim = BitmapFactory.decodeResource(resources, mAnimations!!.TargetPosition())
        canvas.drawBitmap(targetpositionanim, mUtils!!.convertDpToPixel(338.13.toFloat(), context), mUtils!!.convertDpToPixel(1011.67.toFloat() + mGameThread!!.targetPosition, context), targetPaint)
        tuneyPaint.color = Color.WHITE
        position -= 6f
        canvas.drawCircle(mUtils!!.convertDpToPixel(360f, context), mUtils!!.convertDpToPixel(1030.67.toFloat() + position, context), mUtils!!.convertDpToPixel(25.67.toFloat(), context), tuneyPaint)
        canvas.drawBitmap(tuney, mUtils!!.convertDpToPixel(343f, context), mUtils!!.convertDpToPixel(1008.67.toFloat() + position, context),null)
        if (position != mGameThread!!.targetPosition && isClicked) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.drawBitmap(tuneyDead_anim_1, mUtils!!.convertDpToPixel(326.67.toFloat(), context), mUtils!!.convertDpToPixel(1004.33.toFloat() + position, context), null)
        }
        if (position == mGameThread!!.targetPosition) {
            if (isTuneyClicked) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                canvas.drawBitmap(tuneyClicked, mUtils!!.convertDpToPixel(326.67.toFloat(), context), mUtils!!.convertDpToPixel(1004.33.toFloat() + mGameThread!!.targetPosition, context), null)
            } else {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                canvas.drawBitmap(tuneyDead_anim_1, mUtils!!.convertDpToPixel(326.67.toFloat(), context), mUtils!!.convertDpToPixel(1004.33.toFloat() + mGameThread!!.targetPosition, context), tuneyDeadPaint)
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        TryDraw(getHolder())
        mGameThread!!.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    override fun surfaceDestroyed(holder: SurfaceHolder) {}
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "touch input: X:" + mUtils!!.convertPixelToDp(event.x, context) + "  " + "Y:" + mUtils!!.convertPixelToDp(event.y, context))
            isClicked = true

            // Check if click is within bounds of circle
            if (event.x >= mUtils!!.convertDpToPixel(331f, context) && event.x <= mUtils!!.convertDpToPixel(355f, context) && event.y >= mUtils!!.convertDpToPixel((1018 + mGameThread!!.targetPosition), context) && event.y <= mUtils!!.convertDpToPixel((1100 + mGameThread!!.targetPosition), context))
            {
                // Clicked within circle, register further clicks by consuming this click
                Log.i(TAG, "touch success")
                if (position == mGameThread!!.targetPosition) {
                    isTuneyClicked = true
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}