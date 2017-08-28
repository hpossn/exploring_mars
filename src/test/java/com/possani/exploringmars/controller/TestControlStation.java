package com.possani.exploringmars.controller;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;
import com.possani.exploringmars.model.Field;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by hugo. All rights reserved.
 */

@RunWith(SpringRunner.class)
public class TestControlStation {
    private static final int UPPER_RIGHT_X = 5;
    private static final int UPPER_RIGHT_Y = 5;

    private ControlStation controlStation;

    @Before
    public void setup() {
        Field field = new Field(UPPER_RIGHT_X, UPPER_RIGHT_Y);
        controlStation = new ControlStation(field);
    }

    @Test
    public void completeMissionScenarioWithOneProbe() {
        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] commands = {Command.LEFT,
                              Command.MOVE,
                              Command.LEFT,
                              Command.MOVE,
                              Command.LEFT,
                              Command.MOVE,
                              Command.LEFT,
                              Command.MOVE,
                              Command.MOVE};

        Position finalPosition = controlStation.controlProbe(firstProbe, commands);
        Position expectedPosition = new Position(1, 3, CardinalDirection.NORTH);

        assertEquals(expectedPosition, finalPosition);
    }

    @Test
    public void completeMissionScenarioWithTwoProbesNoCollision() {
        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        Probe secondProbe = new Probe("Second Probe", new Position(3, 3, CardinalDirection.EAST));

        Command[] secondProbeCommands = {Command.MOVE,
                                         Command.MOVE,
                                         Command.RIGHT,
                                         Command.MOVE,
                                         Command.MOVE,
                                         Command.RIGHT,
                                         Command.MOVE,
                                         Command.RIGHT,
                                         Command.RIGHT,
                                         Command.MOVE};

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, secondProbeCommands);
        Position secondProbeExpectedPosition = new Position(5, 1, CardinalDirection.EAST);

        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void borderCollisionWithNoResolution() {
        Probe firstProbe = new Probe("First Probe", new Position(4, 4, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.MOVE,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.RIGHT,
                                        Command.MOVE,
                                        Command.MOVE,
                                        Command.MOVE,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(4, 5, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);
    }

    @Test
    public void borderCollisionWithResolution() {
        Probe firstProbe = new Probe("First Probe", new Position(4, 4, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.MOVE,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.MOVE,
                                        Command.RIGHT,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(3, 4, CardinalDirection.WEST);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);
    }

    @Test
    public void twoProbesWithSameFinalDestination() {
        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] secondProbeCommands = {Command.MOVE, Command.RIGHT, Command.RIGHT};

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, secondProbeCommands);
        Position secondProbeExpectedPosition = new Position(1, 2, CardinalDirection.NORTH);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void twoProbesWithSameFinalDestinationAndResolution() {
        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] secondProbeCommands = {Command.MOVE, Command.RIGHT, Command.MOVE};

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, secondProbeCommands);
        Position secondProbeExpectedPosition = new Position(2, 3, CardinalDirection.EAST);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void twoProbesWithTwoCollisionsAndResolution() {
        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] secondProbeCommands =
                {Command.MOVE, Command.RIGHT, Command.MOVE, Command.RIGHT, Command.RIGHT, Command.MOVE, Command.MOVE};

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, secondProbeCommands);
        Position secondProbeExpectedPosition = new Position(0, 3, CardinalDirection.WEST);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);
    }

    @Test
    public void threeProbesWithTwoCollisionsAndResolution() {
        Probe firstProbe = new Probe("First Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] firstProbeCommands = {Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.LEFT,
                                        Command.MOVE,
                                        Command.MOVE};

        Position firstProbeFinalPosition = controlStation.controlProbe(firstProbe, firstProbeCommands);
        Position firstProbeExpectedPosition = new Position(1, 3, CardinalDirection.NORTH);
        assertEquals(firstProbeExpectedPosition, firstProbeFinalPosition);

        Probe secondProbe = new Probe("Second Probe", new Position(1, 2, CardinalDirection.NORTH));

        Command[] secondProbeCommands =
                {Command.MOVE, Command.RIGHT, Command.MOVE, Command.RIGHT, Command.RIGHT, Command.MOVE, Command.MOVE};

        Position secondProbeFinalPosition = controlStation.controlProbe(secondProbe, secondProbeCommands);
        Position secondProbeExpectedPosition = new Position(0, 3, CardinalDirection.WEST);
        assertEquals(secondProbeExpectedPosition, secondProbeFinalPosition);

        Probe thirdProbe = new Probe("Third Probe", new Position(1, 2, CardinalDirection.EAST));

        Command[] thirdProbeCommands = {Command.MOVE,
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
                                        Command.MOVE};

        Position thirdProbeFinalPosition = controlStation.controlProbe(thirdProbe, thirdProbeCommands);
        Position thirdProbeExpectedPosition = new Position(0, 4, CardinalDirection.NORTH);

        assertEquals(thirdProbeExpectedPosition, thirdProbeFinalPosition);
    }
}
