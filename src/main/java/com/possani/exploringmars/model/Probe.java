package com.possani.exploringmars.model;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;

/**
 * Created by hugo. All rights reserved.
 */
public class Probe {

    private final Position initialPosition;
    private String name;
    private Position currentPosition;

    public Probe(String name, Position initialPosition) {
        this.name = name;

        // TODO : Validate initial position
        this.initialPosition = new Position(initialPosition);
        this.currentPosition = new Position(initialPosition);
    }

    public String getName() {
        return name;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position turn(Command turnToDirection) {
        CardinalDirection currentDirection = currentPosition.getCardinalDirection();

        switch (turnToDirection) {
            case LEFT:
                currentPosition.setCardinalDirection(currentDirection.left());
                break;
            case RIGHT:
                currentPosition.setCardinalDirection(currentDirection.right());
            default:
                break;
        }

        return currentPosition;

    }

    public Position nextPosition() {
        CardinalDirection currentDirection = currentPosition.getCardinalDirection();
        Position nextPosition = new Position(currentPosition);

        switch (currentDirection) {
            case NORTH:
                nextPosition.setY(nextPosition.getY() + 1);
                break;
            case SOUTH:
                nextPosition.setY(nextPosition.getY() - 1);
                break;
            case WEST:
                nextPosition.setX(nextPosition.getX() - 1);
                break;
            case EAST:
                nextPosition.setX(nextPosition.getX() + 1);
                break;
            default:
                throw new IllegalStateException("Invalid cardinal direction");
        }

        return nextPosition;
    }

    public Position move(Position nextPosition) {
        this.currentPosition = nextPosition;
        return currentPosition;
    }
}
