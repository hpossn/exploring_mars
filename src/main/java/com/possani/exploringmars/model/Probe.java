package com.possani.exploringmars.model;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;
import org.springframework.stereotype.Component;

/**
 * Created by hugo. All rights reserved.
 */
@Component
public class Probe {

    private final Position initialPosition;
    private String name;
    private Position currentPosition;

    public Probe(String name, Position initialPosition) {
        this.name = name;

        // TODO : Validate initial position
        this.initialPosition = initialPosition;
        this.currentPosition = initialPosition;
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
            case L:
                currentPosition.setCardinalDirection(currentDirection.left());
                break;
            case R:
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
            case N:
                nextPosition.setY(nextPosition.getY() + 1);
                break;
            case S:
                nextPosition.setY(nextPosition.getY() - 1);
                break;
            case W:
                nextPosition.setY(nextPosition.getX() - 1);
                break;
            case E:
                nextPosition.setY(nextPosition.getX() + 1);
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

    public Position readCommands(Command[] commands) {
        return null;
    }
}
