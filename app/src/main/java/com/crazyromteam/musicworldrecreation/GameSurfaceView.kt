package com.crazyromteam.musicworldrecreation

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.crazyromteam.musicworldrecreation.anim.BitmapAnim


class GameSurfaceView : SurfaceView, SurfaceHolder.Callback {
    private var mGameThread: GameThread? = null
    private val TAG = "GameSurfaceView"
    private var tuneyPaint = Paint()
    private val tuneyDead = BitmapFactory.decodeResource(resources, R.drawable.tuney_dead_anim_1)
    private val pauseButtonSmall = BitmapFactory.decodeResource(resources, R.drawable.pause_button_small)
    private val pauseButtonHoldSmall = BitmapFactory.decodeResource(resources, R.drawable.pause_button_hold_small)
    private val circleButton = BitmapFactory.decodeResource(resources, R.drawable.circle_button)
    private val waitingTuney = BitmapFactory.decodeResource(resources, R.drawable.waiting_tuney)
    var targetAnimState = 1
    var tuneyAnimState = 1
    var targetPositionAnimFrame = 0
    var tuneyTapBlueAnimFrame = 0
    var tuneyAnimFrame = 0
    var x = 0
    private var mUtils: Utils? = null
    private var mBitmapAnim: BitmapAnim? = null

    @JvmField
    var position = 1f
    private var isTuneyClicked = false

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
        mBitmapAnim = BitmapAnim()
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSLUCENT)
    }

    fun tryDraw(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()
        holder.addCallback(this)
        if (canvas != null) {
            drawTuney(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    private fun drawTuney(canvas: Canvas) {
        super.draw(canvas)
        var tuney = BitmapFactory.decodeResource(resources, mBitmapAnim!!.tuney(this))
        for (x in 3..408 step 13) {
            for (y in 855 downTo 820 step 13) {
                canvas.drawBitmap(
                    waitingTuney,
                    mUtils!!.convertDpToPixel(x.toFloat(), context),
                    mUtils!!.convertDpToPixel(y.toFloat(), context),
                    null
                )
            }
        }
        canvas.drawBitmap(
            circleButton,
            mUtils!!.convertDpToPixel(377f, context),
            mUtils!!.convertDpToPixel(839f, context),
            null
        )
        canvas.drawBitmap(
            pauseButtonSmall,
            mUtils!!.convertDpToPixel(383f, context),
            mUtils!!.convertDpToPixel(846f, context),
            null
        )
        val targetPositionAnim =
            BitmapFactory.decodeResource(resources, mBitmapAnim!!.targetPosition(this))
        canvas.drawBitmap(
            targetPositionAnim,
            mUtils!!.convertDpToPixel(mGameThread!!.targetX[x], context),
            mUtils!!.convertDpToPixel(
                mGameThread!!.targetY[x] + mGameThread!!.targetPosition[x],
                context
            ),
            null
        )
        tuneyPaint.color = Color.WHITE
        position -= 6f
        canvas.drawCircle(
            mUtils!!.convertDpToPixel(mGameThread!!.circleX[x], context),
            mUtils!!.convertDpToPixel(mGameThread!!.circleY[x] + position, context),
            mUtils!!.convertDpToPixel(25.67.toFloat(), context),
            tuneyPaint
        )
        canvas.drawBitmap(
            tuney,
            mUtils!!.convertDpToPixel(mGameThread!!.tuneyX[x], context),
            mUtils!!.convertDpToPixel(mGameThread!!.tuneyY[x] + position, context),
            null
        )
        if (position != mGameThread!!.targetPosition[x] && isClicked) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.drawBitmap(
                tuneyDead,
                mUtils!!.convertDpToPixel(326.67.toFloat(), context),
                mUtils!!.convertDpToPixel(1004.33.toFloat() + position, context),
                null
            )
            position = 1f
        }
        if (position == mGameThread!!.targetPosition[x]) {
            tuney = BitmapFactory.decodeResource(resources, R.drawable.tuney_basic_finish)
            canvas.drawBitmap(
                targetPositionAnim,
                mUtils!!.convertDpToPixel(338.13.toFloat(), context),
                mUtils!!.convertDpToPixel(
                    1011.67.toFloat() + mGameThread!!.targetPosition[x],
                    context
                ),
                null
            )
            if (isTuneyClicked) {
                for (Frame in 1..mBitmapAnim!!.maxTuneyTspBlueFrame) {
                    holder.setFormat(PixelFormat.TRANSLUCENT)
                    val tuneyClicked =
                        BitmapFactory.decodeResource(
                            resources,
                            mBitmapAnim!!.tuneyTapBlue(this, Frame)
                        )
                    canvas.drawBitmap(
                        tuneyClicked,
                        mUtils!!.convertDpToPixel(326.67.toFloat(), context),
                        mUtils!!.convertDpToPixel(
                            1004.33.toFloat() + mGameThread!!.targetPosition[x],
                            context
                        ),
                        null
                    )
                }
            } else {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                canvas.drawBitmap(
                    tuneyDead,
                    mUtils!!.convertDpToPixel(326.67.toFloat(), context),
                    mUtils!!.convertDpToPixel(
                        1004.33.toFloat() + mGameThread!!.targetPosition[x],
                        context
                    ),
                    null
                )
            }
            if (x == 1) {
                mGameThread!!.isruning = false
            } else {
                x++
                position = 1f
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        tryDraw(getHolder())
        mGameThread!!.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
    override fun surfaceDestroyed(holder: SurfaceHolder) {}
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            Log.i(
                TAG,
                "touch input: X:" + mUtils!!.convertPixelToDp(
                    event.x,
                    context
                ) + "  " + "Y:" + mUtils!!.convertPixelToDp(event.y, context)
            )
            isClicked = true

            // Check if click is within bounds of circle
            if (event.x >= mUtils!!.convertDpToPixel(
                    331f,
                    context
                ) && event.x <= mUtils!!.convertDpToPixel(
                    355f,
                    context
                ) && event.y >= mUtils!!.convertDpToPixel(
                    (1018 + mGameThread!!.targetPosition[x]),
                    context
                ) && event.y <= mUtils!!.convertDpToPixel(
                    (1100 + mGameThread!!.targetPosition[x]),
                    context
                )

            ) {
                // Clicked within circle, register further clicks by consuming this click
                Log.i(TAG, "touch success")
                if (position == mGameThread!!.targetPosition[x]) {
                    isTuneyClicked = true
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }
}
