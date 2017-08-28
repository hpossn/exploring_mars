package com.possani.exploringmars.model;

import com.possani.exploringmars.enums.CardinalDirection;
import com.possani.exploringmars.enums.Command;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by hugo. All rights reserved.
 */

@RunWith(SpringRunner.class)
public class TestProbe {

    private Probe firstProbe;

    @Before
    public void setup() {
        firstProbe = new Probe("First Probe", new Position(0, 0, CardinalDirection.NORTH));
    }

    @Test
    public void createProbeInInitialPosition() {
        Position currentPosition = firstProbe.getCurrentPosition();

        assertEquals(0, currentPosition.getX());
        assertEquals(0, currentPosition.getY());
        assertEquals(CardinalDirection.NORTH, currentPosition.getCardinalDirection());
    }

    @Test
    public void northTurnRight() {
        Position currentPosition = firstProbe.getCurrentPosition();
        assertEquals(CardinalDirection.NORTH, currentPosition.getCardinalDirection());

        Position nextPosition = firstProbe.turn(Command.RIGHT);
        assertEquals(CardinalDirection.EAST, nextPosition.getCardinalDirection());
    }

    @Test
    public void northTurnLeft() {
        Position currentPosition = firstProbe.getCurrentPosition();
        assertEquals(CardinalDirection.NORTH, currentPosition.getCardinalDirection());

        Position nextPosition = firstProbe.turn(Command.LEFT);
        assertEquals(CardinalDirection.WEST, nextPosition.getCardinalDirection());
    }
}
