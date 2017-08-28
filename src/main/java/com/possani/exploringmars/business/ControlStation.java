package com.possani.exploringmars.business;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;
import com.possani.exploringmars.model.Field;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo. All rights reserved.
 */
@Component
@Scope("singleton")
public class ControlStation {

    public Position controlProbe(Probe probe, Field field) {
        if (null == probe || null == field) {
            throw new IllegalArgumentException();
        }

        List<Command> commands = probe.getCommands();

        if (null == commands || commands.size() == 0) {
            return probe.getCurrentPosition();
        }

        field.allocateNewProbe(probe);

        boolean allowedToMove = true;
        int currentIndex = 0;
        int maxLength = commands.size();

        while (allowedToMove && currentIndex < maxLength) {
            Command command = commands.get(currentIndex);

            if (command.changesPosition()) {
                boolean moved = field.move(probe);
                if (!moved) {
                    int shiftIndexWithResolution = collisionResolution(probe, commands, currentIndex, field);

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

    private int collisionResolution(Probe probe, List<Command> commands, int currentIndex, Field field) {
        Position positionCollisionHappened = probe.nextPosition();

        if (currentIndex >= commands.size()) return -1;
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

    private List<Command> listCommandsUntilMove(List<Command> commands, int currentIndex) {
        List<Command> commandList = new ArrayList<>();
        boolean validList = false;

        for (int i = currentIndex + 1; i < commands.size(); i++) {
            Command commandAtIndex = commands.get(i);
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

            if(command.changesPosition()) {
                desiredPosition = desiredPosition.nextPosition();
            } else {
                desiredPosition.setCardinalDirection(direction.turn(command));
            }
        }

        return desiredPosition;
    }
}
