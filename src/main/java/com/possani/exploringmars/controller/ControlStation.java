package com.possani.exploringmars.controller;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;
import com.possani.exploringmars.model.Field;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo. All rights reserved.
 */
public class ControlStation {
    private Field field;

    public ControlStation(Field field) {
        if (null == field) {
            throw new IllegalArgumentException("Field cannot be null");
        }

        this.field = field;
    }

    public Position controlProbe(Probe probe, Command[] commands) {
        if (null == probe) {
            throw new IllegalArgumentException("Probe cannot be null");
        }

        if (null == commands || commands.length == 0) {
            return probe.getCurrentPosition();
        }

        field.allocateNewProbe(probe);

        boolean allowedToMove = true;
        int currentIndex = 0;
        int maxLength = commands.length;

        while (allowedToMove && currentIndex < maxLength) {
            Command command = commands[currentIndex];

            if (command.changesPosition()) {
                boolean moved = field.move(probe);
                if (!moved) {
                    int shiftIndexWithResolution = collisionResolution(probe, commands, currentIndex);

                    if (shiftIndexWithResolution != -1) {
                        currentIndex = currentIndex + shiftIndexWithResolution;
                    } else {
                        allowedToMove = false;
                    }
                } else {
                    currentIndex++;
                }

            } else {
                probe.turn(command);
                currentIndex++;
            }
        }

        return probe.getCurrentPosition();
    }

    private int collisionResolution(Probe probe, Command[] commands, int currentIndex) {
        Position positionCollisionHappened = probe.nextPosition();

        if (currentIndex >= commands.length) return -1;
        List<Command> nextCommands = listCommandsUntilMove(commands, currentIndex);
        if (nextCommands.size() == 0) return -1;

        Position desiredPositionAfterCollision = desiredPositionAfterCollision(positionCollisionHappened, nextCommands);
        
        if (desiredPositionAfterCollision.getX() == probe.getCurrentPosition().getX() &&
            desiredPositionAfterCollision.getY() == probe.getCurrentPosition().getY()) {
            probe.move(desiredPositionAfterCollision);
            return nextCommands.size() + 1;
        } else {
            boolean moved = field.move(probe, desiredPositionAfterCollision);
            if (moved) {
                return nextCommands.size() + 1;
            }
        }

        return -1;
    }

    private List<Command> listCommandsUntilMove(Command[] commands, int currentIndex) {
        List<Command> commandList = new ArrayList<>();
        boolean validList = false;

        for (int i = currentIndex + 1; i < commands.length; i++) {
            Command commandAtIndex = commands[i];
            commandList.add(commandAtIndex);

            if (commandAtIndex == Command.MOVE) {
                validList = true;
                break;
            }
        }

        if (!validList) {
            commandList.clear();
        }

        return commandList;
    }

    private Position desiredPositionAfterCollision(Position positionCollisionHappened, List<Command> nextCommands) {
        Position desiredPosition = new Position(positionCollisionHappened);

        for (Command command : nextCommands) {
            CardinalDirection direction = desiredPosition.getCardinalDirection();

            switch (command) {
                case LEFT:
                    desiredPosition.setCardinalDirection(direction.left());
                    break;
                case RIGHT:
                    desiredPosition.setCardinalDirection(direction.right());
                    break;
                case MOVE:
                    desiredPosition = desiredPosition.nextPosition();
            }
        }

        return desiredPosition;
    }
}
