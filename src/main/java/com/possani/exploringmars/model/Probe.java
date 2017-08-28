package com.possani.exploringmars.model;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;

import java.util.List;

/**
 * Created by hugo. All rights reserved.
 */
public class Probe {

    private final Position initialPosition;
    private final String name;
    private final List<Command> commands;
    private Position currentPosition;

    public Probe(String name, Position initialPosition, List<Command> commands) {
        this.name = name;
        this.initialPosition = new Position(initialPosition);
        this.currentPosition = new Position(initialPosition);
        this.commands = commands;
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

    public List<Command> getCommands() {
        return commands;
    }

    public Position turn(Command turnToDirection) {
        CardinalDirection currentDirection = currentPosition.getCardinalDirection();
        currentPosition.setCardinalDirection(currentDirection.turn(turnToDirection));
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
