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
}
