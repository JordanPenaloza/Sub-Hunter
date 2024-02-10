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
import java.util.Random;


public class SubHunter extends Activity {

    // Here are all the objects(instances)
    // of classes that we need to do some drawing
    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;
    private GameDrawer gameDrawer;
    private Submarine submarine;
    private GameObject gameObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();
        submarine = new Submarine();
        gameDrawer = new GameDrawer(gameObject, gameView, blankBitmap, canvas, paint);
        gameObject = new GameObject();

        gameObject.numberHorizontalPixels = size.x;
        gameObject.numberVerticalPixels = size.y;
        gameObject.blockSize = gameObject.numberHorizontalPixels / gameObject.gridWidth;
        gameObject.gridHeight = gameObject.numberVerticalPixels / gameObject.blockSize;



        // Initialize all the objects ready for drawing
        blankBitmap = Bitmap.createBitmap(gameObject.numberHorizontalPixels,
                gameObject.numberVerticalPixels,
                Bitmap.Config.ARGB_8888);

        // Tell Android to set our drawing
        // as the view for this app
        setContentView(gameView);

        Log.d("Debugging", "In onCreate");
        newGame();
        draw();
    }
    void newGame(){
        Random random = new Random();
        submarine.subHorizontalPosition = random.nextInt(gameObject.gridWidth);
        submarine.subVerticalPosition = random.nextInt(gameObject.gridHeight);        gameObject.shotsTaken = 0;

        Log.d("Debugging", "In newGame");

    }
    public void draw() {
        gameDrawer.draw();
        Log.d("Debugging", "In draw");
        if (gameObject.debugging) {
            printDebuggingText();
        }
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
        gameObject.shotsTaken ++;
        gameObject.horizontalTouched =  (int) touchX / gameObject.blockSize;
        gameObject.verticalTouched =  (int) touchY / gameObject.blockSize;

        if(submarine.isSubmarineHit(gameObject.horizontalTouched, submarine.subHorizontalPosition, gameObject.verticalTouched, submarine.subVerticalPosition))
            boom();
        else draw();
    }
    public int horizontalGap() {
        return (int)gameObject.horizontalTouched -
                submarine.getHorizontalPosition();
    }
    public int verticalGap(){
        return (int)gameObject.verticalTouched -
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
        paint.setTextSize(gameObject.blockSize * 10);
        canvas.drawText("BOOM!", gameObject.blockSize * 4,
                gameObject.blockSize * 14, paint);

        paint.setTextSize(gameObject.blockSize * 2);
        canvas.drawText("Take a shot to start again",
                gameObject.blockSize * 8,
                gameObject.blockSize * 18, paint);
        newGame();
    }

    public void printDebuggingText(){
        paint.setTextSize(gameObject.blockSize);
        canvas.drawText("gameObject.numberHorizontalPixels = "
                        + gameObject.numberHorizontalPixels,
                50, gameObject.blockSize * 3, paint);
        canvas.drawText("gameObject.numberVerticalPixels = "
                        + gameObject.numberVerticalPixels,
                50, gameObject.blockSize * 4, paint);
        canvas.drawText("gameObject.blockSize = " + gameObject.blockSize,
                50, gameObject.blockSize * 5, paint);
        canvas.drawText("gameObject.gridWidth = " + gameObject.gridWidth,
                50, gameObject.blockSize * 6, paint);
        canvas.drawText("gameObject.gridHeight = " + gameObject.gridHeight,
                50, gameObject.blockSize * 7, paint);
        canvas.drawText("gameObject.horizontalTouched = " +
                        gameObject.horizontalTouched, 50,
                gameObject.blockSize * 8, paint);
        canvas.drawText("gameObject.verticalTouched = " +
                        gameObject.verticalTouched, 50,
                gameObject.blockSize * 9, paint);
        canvas.drawText("gameObject.subHorizontalPosition = " +
                        gameObject.subHorizontalPosition, 50,
                gameObject.blockSize * 10, paint);
        canvas.drawText("gameObject.subVerticalPosition = " +
                        gameObject.subVerticalPosition, 50,
                gameObject.blockSize * 11, paint);
        canvas.drawText("gameObject.hit = " + gameObject.hit,
                50, gameObject.blockSize * 12, paint);
        canvas.drawText("gameObject.shotsTaken = " +
                        gameObject.shotsTaken,
                50, gameObject.blockSize * 13, paint);
        canvas.drawText("gameObject.debugging = " + gameObject.debugging,
                50, gameObject.blockSize * 14, paint);

    }
}