package com.possani.exploringmars.enums;

/**
 * Created by hugo. All rights reserved.
 */
public enum CardinalDirection {
    NORTH, SOUTH, WEST, EAST;

    public CardinalDirection left() {
        switch (this) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            case EAST:
                return NORTH;
        }

        throw new IllegalStateException("Invalid Cardinal Direction");
    }

    public CardinalDirection right() {
        switch (this) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            case EAST:
                return SOUTH;
        }

        throw new IllegalStateException("Invalid Cardinal Direction");
    }
}
