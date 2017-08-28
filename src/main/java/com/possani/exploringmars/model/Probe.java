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
        return currentPosition.nextPosition();
    }

    public Position move(Position nextPosition) {
        this.currentPosition = nextPosition;
        return currentPosition;
    }
}
