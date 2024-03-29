package com.possani.exploringmars.model;

import com.possani.exploringmars.enums.CardinalDirection;

import java.util.Objects;

/**
 * Created by hugo. All rights reserved.
 */
public class Position {
    private int x;
    private int y;
    private CardinalDirection cardinalDirection;

    public Position(int x, int y, CardinalDirection cardinalDirection) {
        this.x = x;
        this.y = y;
        this.cardinalDirection = cardinalDirection;
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
        this.cardinalDirection = position.cardinalDirection;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CardinalDirection getCardinalDirection() {
        return cardinalDirection;
    }

    public void setCardinalDirection(CardinalDirection cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
    }

    public void setCoordinate(int x, int y, CardinalDirection cardinalDirection) {
        this.x = x;
        this.y = y;
        this.cardinalDirection = cardinalDirection;
    }

    public Position nextPosition() {
        Position nextPosition = new Position(this);

        switch (cardinalDirection) {
            case NORTH:
                nextPosition.setY(this.getY() + 1);
                break;
            case SOUTH:
                nextPosition.setY(this.getY() - 1);
                break;
            case WEST:
                nextPosition.setX(this.getX() - 1);
                break;
            case EAST:
                nextPosition.setX(this.getX() + 1);
                break;
        }

        return nextPosition;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + ", cardinalDirection=" + cardinalDirection + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, cardinalDirection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;
        return x == that.x && y == that.y && cardinalDirection == that.cardinalDirection;
    }
}
