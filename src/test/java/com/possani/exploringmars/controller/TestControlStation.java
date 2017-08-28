package com.possani.exploringmars.controller;

import com.possani.exploringmars.business.ControlStation;
import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;
import com.possani.exploringmars.model.Field;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by hugo. All rights reserved.
 */

@RunWith(SpringRunner.class)
public class TestControlStation {
    private static final int UPPER_RIGHT_X = 5;
    private static final int UPPER_RIGHT_Y = 5;

    private ControlStation controlStation;
    private Field field;

    @Before
    public void setup() {
        field = new Field(UPPER_RIGHT_X, UPPER_RIGHT_Y);
        controlStation = new ControlStation();
    }

    @Test
    public void completeMissionScenarioWithOneProbe() {
        List<Command> commands = Arrays.asList(Command.LEFT,
                                               Command.MOVE,
                                               Command.LEFT,
                                               Command.MOVE,
                                               Command.LEFT,
                                               Command.MOVE,
                                               Command.LEFT,
                                               Command.MOVE,
                                               Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH), commands);


        Position finalPosition = controlStation.controlProbe(firstProbe, field);
        Position expectedPosition = new Position(1, 3, CardinalDirection.NORTH);

        assertEquals(expectedPosition, finalPosition);
    }

    @Test
    public void completeMissionScenarioWithTwoProbesNoCollision() {
        List<Command> firstProbeCommands = Arrays.asList(Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH), firstProbeCommands);

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        List<Command> secondProbeCommands = Arrays.asList(Command.MOVE,
                                                          Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.MOVE,
                                                          Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.RIGHT,
                                                          Command.MOVE);

        Probe secondProbe = new Probe("Second Probe", new Position(3, 3, CardinalDirection.EAST), secondProbeCommands);

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, field);
        Position secondProbeExpectedPosition = new Position(5, 1, CardinalDirection.EAST);

        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void borderCollisionWithNoResolution() {
        List<Command> firstProbeCommands = Arrays.asList(Command.MOVE,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.RIGHT,
                                                         Command.MOVE,
                                                         Command.MOVE,
                                                         Command.MOVE,
                                                         Command.MOVE);
        Probe firstProbe = new Probe("First Probe", new Position(4, 4, CardinalDirection.NORTH), firstProbeCommands);

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(4, 5, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);
    }

    @Test
    public void borderCollisionWithResolution() {
        List<Command> firstProbeCommands = Arrays.asList(Command.MOVE,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.MOVE,
                                                         Command.RIGHT,
                                                         Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(4, 4, CardinalDirection.NORTH), firstProbeCommands);

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(3, 4, CardinalDirection.WEST);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);
    }

    @Test
    public void twoProbesWithSameFinalDestination() {
        List<Command> firstProbeCommands = Arrays.asList(Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH), firstProbeCommands);

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        List<Command> secondProbeCommands = Arrays.asList(Command.MOVE, Command.RIGHT, Command.RIGHT);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH), secondProbeCommands);

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, field);
        Position secondProbeExpectedPosition = new Position(1, 2, CardinalDirection.NORTH);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void twoProbesWithSameFinalDestinationAndResolution() {
        List<Command> firstProbeCommands = Arrays.asList(Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH), firstProbeCommands);
        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        List<Command> secondProbeCommands = Arrays.asList(Command.MOVE, Command.RIGHT, Command.MOVE);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH), secondProbeCommands);
        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, field);
        Position secondProbeExpectedPosition = new Position(2, 3, CardinalDirection.EAST);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void twoProbesWithTwoCollisionsAndResolution() {
        List<Command> firstProbeCommands = Arrays.asList(Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH), firstProbeCommands);

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        List<Command> secondProbeCommands = Arrays.asList(Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.RIGHT,
                                                          Command.MOVE,
                                                          Command.MOVE);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH), secondProbeCommands);

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, field);
        Position secondProbeExpectedPosition = new Position(0, 3, CardinalDirection.WEST);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void threeProbesWithTwoCollisionsAndResolution() {
        List<Command> firstProbeCommands = Arrays.asList(Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.MOVE);

        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH), firstProbeCommands);

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, field);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        List<Command> secondProbeCommands = Arrays.asList(Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.MOVE,
                                                          Command.RIGHT,
                                                          Command.RIGHT,
                                                          Command.MOVE,
                                                          Command.MOVE);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH), secondProbeCommands);
        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, field);
        Position secondProbeExpectedPosition = new Position(0, 3, CardinalDirection.WEST);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);

        List<Command> thirdProbeCommands = Arrays.asList(Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.RIGHT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.MOVE,
                                                         Command.LEFT,
                                                         Command.LEFT,
                                                         Command.MOVE);

        Probe thirdProbe = new Probe("Third Probe", new Position(1, 2, CardinalDirection.EAST), thirdProbeCommands);
        Position thirdProbeFinalPosition = controlStation.controlProbe(thirdProbe, field);
        Position thirdProbeExpectedPosition = new Position(0, 4, CardinalDirection.NORTH);
        assertEquals(thirdProbeExpectedPosition, thirdProbeFinalPosition);
    }
}
