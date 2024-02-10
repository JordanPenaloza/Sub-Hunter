// GameDrawer.java

package com.example.subhunter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

public class GameDrawer {
    private final ImageView gameView;
    private final Bitmap blankBitmap;
    private final Canvas canvas;
    private final Paint paint;
    private final GameObject gameObject;

    public GameDrawer(GameObject gameObject, ImageView gameView, Bitmap blankBitmap, Canvas canvas, Paint paint) {
        this.gameObject = gameObject;
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
    }

    public void drawVerticalLines() {
        for(int i = 0; i < gameObject.gridWidth; i++){
            canvas.drawLine(gameObject.blockSize * i, 0,
                    gameObject.blockSize * i, gameObject.numberVerticalPixels,
                    paint);
        }
    }

    public void drawHorizontalLines() {
        for(int i = 0; i < gameObject.gridHeight; i++){
            canvas.drawLine(0, gameObject.blockSize * i,
                    gameObject.numberHorizontalPixels, gameObject.blockSize * i,
                    paint);
        }
    }

    public void drawPlayerShot() {
        canvas.drawRect(gameObject.horizontalTouched * gameObject.blockSize,
                gameObject.verticalTouched * gameObject.blockSize,
                (gameObject.horizontalTouched * gameObject.blockSize) + gameObject.blockSize,
                (gameObject.verticalTouched * gameObject.blockSize) + gameObject.blockSize,
                paint);
    }

    public void resizeText() {
        paint.setTextSize(gameObject.blockSize * 2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText(
                "Shots Taken: " + gameObject.shotsTaken +
                        "  Distance: " + gameObject.distanceFromSub,
                gameObject.blockSize, gameObject.blockSize * 1.75f,
                paint);
    }
}
