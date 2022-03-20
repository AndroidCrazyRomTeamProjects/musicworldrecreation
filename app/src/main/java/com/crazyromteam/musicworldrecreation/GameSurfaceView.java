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
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread mGameThread;
    String image;
    Context mContext;
    private final String TAG = "GameSurfaceView";
    Paint tuneypaint = new Paint();
    Paint targetpaint = new Paint();
    Bitmap tuney = BitmapFactory.decodeResource(getResources(),R.drawable.tuney_basic);
    Bitmap tuneydead = BitmapFactory.decodeResource(getResources(),R.drawable.tuney_dead);
    public int possition = 1;
    public int animstate = 1;
    Bitmap targetpositionanim;

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
        tuneypaint.setColor(WHITE);
        possition-=6;
        canvas.drawCircle(1080 ,2110 + possition, 95, tuneypaint);
        canvas.drawBitmap(tuney, 1015, 2035 + possition, null);
        switch (animstate) {
            case 1:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_1);
                break;
            case 2:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_2);
                break;
            case 3:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_3);
                break;
            case 4:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_4);
                break;
            case 5:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_5);
                break;
            case 6:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_6);
                break;
            case 7:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_7);
                break;
            case 8:
                targetpositionanim = BitmapFactory.decodeResource(getResources(),R.drawable.target_position_anim_8);
                break;
        }
        canvas.drawBitmap(targetpositionanim, 1015, 2035 + mGameThread.targetposition, targetpaint);
        if (possition == mGameThread.targetposition) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            canvas.drawBitmap(tuneydead, 1015, 2035 + possition, null);
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
}
