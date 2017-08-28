package com.possani.exploringmars.model;

import com.possani.exploringmars.enums.CardinalDirection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by hugo. All rights reserved.
 */
@RunWith(SpringRunner.class)
public class TestField {
    private static final int UPPER_RIGHT_X = 10;
    private static final int UPPER_RIGHT_Y = 10;

    private Field field;

    @Before
    public void setup() {
        field = new Field(UPPER_RIGHT_X, UPPER_RIGHT_Y);
    }

    @Test
    public void allPositionsAreFreeAtStart() {
        for (int i = 0; i <= UPPER_RIGHT_X; i++) {
            for (int j = 0; j <= UPPER_RIGHT_Y; j++) {
                assertTrue(field.isFree(i, j));
            }
        }
    }

    // TODO : Use Mockito
    @Test
    public void addProbeAndCheckIfFree() {
        Probe probe = new Probe("first probe", new Position(5, 5, CardinalDirection.NORTH));
        assertTrue(field.allocateNewProbe(probe));
        assertFalse(field.isFree(5, 5));
    }

    @Test
    public void isNotFreeAfterBorder() {
        assertFalse(field.isFree(UPPER_RIGHT_X + 1, UPPER_RIGHT_Y));
    }
}
