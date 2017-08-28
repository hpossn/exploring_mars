package com.possani.exploringmars.dto;

import com.possani.exploringmars.enums.CardinalDirection;

/**
 * Created by hugo. All rights reserved.
 */
public class PositionDto {
    Integer x;
    Integer y;
    CardinalDirection cardinalDirection;

    public PositionDto() {
    }

    public PositionDto(Integer x, Integer y, CardinalDirection cardinalDirection) {
        this.x = x;
        this.y = y;
        this.cardinalDirection = cardinalDirection;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public CardinalDirection getCardinalDirection() {
        return cardinalDirection;
    }

    public void setCardinalDirection(CardinalDirection cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
    }
}
