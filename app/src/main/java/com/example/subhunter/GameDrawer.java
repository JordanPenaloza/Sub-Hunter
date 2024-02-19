// GameDrawer.java

package com.example.subhunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

public class GameDrawer {
    private final SubHunter activity;
    private final ImageView gameView;
    private final Bitmap blankBitmap;
    private final Canvas canvas;
    private final Paint paint;

    public GameDrawer(SubHunter activity, ImageView gameView, Bitmap blankBitmap, Canvas canvas, Paint paint) {
        this.activity = activity;
        this.gameView = gameView;
        this.blankBitmap = blankBitmap;
        this.canvas = canvas;
        this.paint = paint;
    }

    public void draw() {
        gameView.setImageBitmap(blankBitmap);
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        paint.setColor(Color.argb(255, 0, 0, 0));

        drawVerticalLines();
        drawHorizontalLines();
        drawPlayerShot();
        resizeText();

        Log.d("Debugging", "In draw");
        if (activity.debugging) {
            activity.printDebuggingText();
        }
    }

    public void drawVerticalLines() {
        for(int i = 0; i < activity.gridWidth; i++){
            canvas.drawLine(activity.blockSize * i, 0,
                    activity.blockSize * i, activity.numberVerticalPixels,
                    paint);
        }
    }

    public void drawHorizontalLines() {
        for(int i = 0; i < activity.gridHeight; i++){
            canvas.drawLine(0, activity.blockSize * i,
                    activity.numberHorizontalPixels, activity.blockSize * i,
                    paint);
        }
    }

    public void drawPlayerShot() {
        canvas.drawRect(activity.horizontalTouched * activity.blockSize,
                activity.verticalTouched * activity.blockSize,
                (activity.horizontalTouched * activity.blockSize) + activity.blockSize,
                (activity.verticalTouched * activity.blockSize) + activity.blockSize,
                paint);
    }

    public void resizeText() {
        paint.setTextSize(activity.blockSize * 2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText(
                "Shots Taken: " + activity.shotsTaken +
                        "  Distance: " + activity.distanceFromSub(),
                activity.blockSize, activity.blockSize * 1.75f,
                paint);
    }
}