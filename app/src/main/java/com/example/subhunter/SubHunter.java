// This is our package
// If you are copy & pasting the code then this line will probably be different to yours
// If so, keep YOUR line- not this one
package com.example.subhunter;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.util.Log;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.widget.ImageView;



public class SubHunter extends Activity {

    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;
    float horizontalTouched = -100;
    float verticalTouched = -100;
    int subHorizontalPosition;
    int subVerticalPosition;
    boolean hit = false;
    int shotsTaken;
    boolean debugging = false;

    // Here are all the objects(instances)
    // of classes that we need to do some drawing
    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;
    private GameDrawer gameDrawer;

    private Submarine submarine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;



        // Initialize all the objects ready for drawing
        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels,
                numberVerticalPixels,
                Bitmap.Config.ARGB_8888);

        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();
        submarine = new Submarine(gridWidth, gridHeight);
        gameDrawer = new GameDrawer(this, gameView, blankBitmap, canvas, paint);

        // Tell Android to set our drawing
        // as the view for this app
        setContentView(gameView);

        Log.d("Debugging", "In onCreate");
        newGame();
        draw();
    }
    void newGame(){
        submarine.setNewPosition(gridWidth, gridHeight);
        shotsTaken = 0;

        Log.d("Debugging", "In newGame");

    }
    public void draw() {
        gameDrawer.draw();
    }





    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("Debugging", "In onTouchEvent");

        boolean Touching = (motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP;
        if(Touching) {
            takeShot(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }

    void takeShot(float touchX, float touchY){
        Log.d("Debugging", "In takeShot");
        shotsTaken ++;
        horizontalTouched =  (int) touchX / blockSize;
        verticalTouched =  (int) touchY / blockSize;

        if(isSubmarineHit(horizontalTouched, verticalTouched))
            boom();
        else draw();
    }
    public boolean isSubmarineHit(float horizontalTouched, float verticalTouched) {
        hit = horizontalTouched == submarine.getHorizontalPosition()
                && verticalTouched == submarine.getVerticalPosition();
        return hit;
    }
    public int horizontalGap() {
        return (int)horizontalTouched -
                submarine.getHorizontalPosition();
    }
    public int verticalGap(){
        return (int)verticalTouched -
                submarine.getVerticalPosition();
    }
    public int distanceFromSub() {
        return (int)Math.sqrt(
                ((horizontalGap() * horizontalGap()) +
                        (verticalGap() * verticalGap())));
    }


    void boom(){

        gameView.setImageBitmap(blankBitmap);
        canvas.drawColor(Color.argb(255, 255, 0, 0));
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(blockSize * 10);
        canvas.drawText("BOOM!", blockSize * 4,
                blockSize * 14, paint);

        paint.setTextSize(blockSize * 2);
        canvas.drawText("Take a shot to start again",
                blockSize * 8,
                blockSize * 18, paint);
        newGame();
    }

    public void printDebuggingText(){
        paint.setTextSize(blockSize);
        canvas.drawText("numberHorizontalPixels = "
                        + numberHorizontalPixels,
                50, blockSize * 3, paint);
        canvas.drawText("numberVerticalPixels = "
                        + numberVerticalPixels,
                50, blockSize * 4, paint);
        canvas.drawText("blockSize = " + blockSize,
                50, blockSize * 5, paint);
        canvas.drawText("gridWidth = " + gridWidth,
                50, blockSize * 6, paint);
        canvas.drawText("gridHeight = " + gridHeight,
                50, blockSize * 7, paint);
        canvas.drawText("horizontalTouched = " +
                        horizontalTouched, 50,
                blockSize * 8, paint);
        canvas.drawText("verticalTouched = " +
                        verticalTouched, 50,
                blockSize * 9, paint);
        canvas.drawText("subHorizontalPosition = " +
                        subHorizontalPosition, 50,
                blockSize * 10, paint);
        canvas.drawText("subVerticalPosition = " +
                        subVerticalPosition, 50,
                blockSize * 11, paint);
        canvas.drawText("hit = " + hit,
                50, blockSize * 12, paint);
        canvas.drawText("shotsTaken = " +
                        shotsTaken,
                50, blockSize * 13, paint);
        canvas.drawText("debugging = " + debugging,
                50, blockSize * 14, paint);

    }
}