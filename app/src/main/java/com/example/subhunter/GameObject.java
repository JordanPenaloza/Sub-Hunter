package com.example.subhunter;

import java.util.Random;

public class GameObject {

   public int numberHorizontalPixels;
    public int numberVerticalPixels;
    public int blockSize;
    public int gridWidth = 40;
    public int gridHeight;
    public float horizontalTouched = -100;
    public float verticalTouched = -100;
    public int subHorizontalPosition;
    public int subVerticalPosition;
    public boolean hit = false;
    public int shotsTaken;
    public int distanceFromSub;
    public boolean debugging = false;
    private int horizontalPosition;
    private int verticalPosition;

    public GameObject() {
        Random random = new Random();
        horizontalPosition = random.nextInt(gridWidth);
        verticalPosition = random.nextInt(gridHeight);
    }

    public int getHorizontalPosition() {

        return horizontalPosition;
    }

    public int getVerticalPosition() {

        return verticalPosition;
    }

    public void setNewPosition(int gridWidth, int gridHeight) {
        Random random = new Random();
        horizontalPosition = random.nextInt(gridWidth);
        verticalPosition = random.nextInt(gridHeight);
    }

    public void newGame() {
        Random random = new Random();
        subHorizontalPosition = random.nextInt(gridWidth);
        subVerticalPosition = random.nextInt(gridHeight);
        shotsTaken = 0;
    }
}