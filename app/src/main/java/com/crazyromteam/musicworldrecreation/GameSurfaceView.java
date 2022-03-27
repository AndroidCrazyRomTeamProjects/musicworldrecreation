package com.crazyromteam.musicworldrecreation;

import static android.graphics.Color.WHITE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread mGameThread;
    private final String TAG = "GameSurfaceView";
    Paint tuneyPaint = new Paint();
    Paint targetPaint = new Paint();
    Bitmap tuney = BitmapFactory.decodeResource(getResources(), R.drawable.tuney_basic);
    Bitmap tuneyDead = BitmapFactory.decodeResource(getResources(), R.drawable.tuney_dead_anim_1);
    Bitmap tuneyClicked = BitmapFactory.decodeResource(getResources(), R.drawable.tuney_tap_anim_1);
    public Utils mUtils;
    public int position = 1;
    public int animState = 1;
    int targetPositionAnimFrame;
    public boolean isTuneyClicked = false;

    public GameSurfaceView(Context context) {
        super(context);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getHolder().addCallback(this);
        mGameThread = new GameThread(this);
        mUtils = new Utils();
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        }


    public void TryDraw(SurfaceHolder holder) {
        Canvas mCanvas = holder.lockCanvas();
        holder.addCallback(this);
        if (mCanvas != null) {
            drawTuney(mCanvas);
            holder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void drawTuney(Canvas canvas) {
        super.draw(canvas);
        switch (animState) {
            case 1:
                targetPositionAnimFrame = R.drawable.target_position_anim_1;
                break;
            case 2:
                targetPositionAnimFrame = R.drawable.target_position_anim_2;
                break;
            case 3:
                targetPositionAnimFrame = R.drawable.target_position_anim_3;
                break;
            case 4:
                targetPositionAnimFrame = R.drawable.target_position_anim_4;
                break;
            case 5:
                targetPositionAnimFrame = R.drawable.target_position_anim_5;
                break;
            case 6:
                targetPositionAnimFrame = R.drawable.target_position_anim_6;
                break;
            case 7:
                targetPositionAnimFrame = R.drawable.target_position_anim_7;
                break;
            case 8:
                targetPositionAnimFrame = R.drawable.target_position_anim_8;
                break;
        }
        Bitmap targetpositionanim = BitmapFactory.decodeResource(getResources(), targetPositionAnimFrame);
        canvas.drawBitmap(targetpositionanim, mUtils.convertDpToPixel((float) 338.13, getContext()), mUtils.convertDpToPixel((float) 1011.67 + mGameThread.targetPosition, getContext()), targetPaint);

        tuneyPaint.setColor(WHITE);
        position -= 6;
        canvas.drawCircle(mUtils.convertDpToPixel(360, getContext()), mUtils.convertDpToPixel((float) 1030.67 + position, getContext()), mUtils.convertDpToPixel((float) 25.67, getContext()), tuneyPaint);
        canvas.drawBitmap(tuney, mUtils.convertDpToPixel(343, getContext()), mUtils.convertDpToPixel((float) 1008.67 + position, getContext()), null);
        if (position == mGameThread.targetPosition) {
            if (isTuneyClicked) {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.drawBitmap(tuneyClicked, mUtils.convertDpToPixel((float) 326.67, getContext()), mUtils.convertDpToPixel((float) 1004.33 + mGameThread.targetPosition, getContext()), null);
            }
            else {
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                canvas.drawBitmap(tuneyDead, mUtils.convertDpToPixel((float) 326.67, getContext()), mUtils.convertDpToPixel((float) 1004.33 + mGameThread.targetPosition, getContext()), null);
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        TryDraw(getHolder());
        mGameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.i(TAG, "touch input: X:" + mUtils.convertPixelToDp(event.getX(), getContext()) + "  " + "Y:" + mUtils.convertPixelToDp(event.getY(), getContext()));

            // Check if click is within bounds of circle
            if ((event.getX() >= mUtils.convertDpToPixel(331, getContext()) && event.getX() <= mUtils.convertDpToPixel(355, getContext())) && (event.getY() >= mUtils.convertDpToPixel(1018 + mGameThread.targetPosition, getContext()) && event.getY() <= mUtils.convertDpToPixel(1100 + mGameThread.targetPosition, getContext()))) {
                // Clicked within circle, register further clicks by consuming this click
                Log.i(TAG, "touch success");
                isTuneyClicked = true;

                return true;
            }
        }

        return super.onTouchEvent(event);
    }
}
