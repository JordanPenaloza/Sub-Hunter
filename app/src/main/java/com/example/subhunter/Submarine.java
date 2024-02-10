package com.example.subhunter;


public class Submarine extends GameObject {
    public int subHorizontalPosition;
    public int subVerticalPosition;

    public boolean isSubmarineHit(float horTouch, int subHorPos, float vertTouch, int subVertPos) {
        return horTouch == subHorPos && vertTouch == subVertPos;
    }
}

