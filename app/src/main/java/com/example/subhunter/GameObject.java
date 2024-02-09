package com.example.subhunter;

import java.util.Random;

public class GameObject {
    private int horizontalPosition;
    private int verticalPosition;

    public GameObject(int gridWidth, int gridHeight) {
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
}