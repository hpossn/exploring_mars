package com.possani.exploringmars.dto;

/**
 * Created by hugo. All rights reserved.
 */
public class FieldDto {
    int upperRightX;
    int upperRightY;

    public FieldDto() {
    }

    public FieldDto(int upperRightX, int upperRightY) {
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
    }

    public int getUpperRightX() {
        return upperRightX;
    }

    public void setUpperRightX(int upperRightX) {
        this.upperRightX = upperRightX;
    }

    public int getUpperRightY() {
        return upperRightY;
    }

    public void setUpperRightY(int upperRightY) {
        this.upperRightY = upperRightY;
    }
}
