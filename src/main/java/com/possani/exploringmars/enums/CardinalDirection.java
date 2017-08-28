package com.possani.exploringmars.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hugo. All rights reserved.
 */
public enum CardinalDirection {
    NORTH, SOUTH, WEST, EAST;

    private static final Map<String, CardinalDirection> codeMap = new HashMap<>();

    static {
        codeMap.put("N", NORTH);
        codeMap.put("S", SOUTH);
        codeMap.put("W", WEST);
        codeMap.put("E", EAST);
    }

    @JsonCreator
    public static CardinalDirection forValue(String value) {
        if (null == value) {
            throw new IllegalArgumentException();
        }

        return codeMap.get(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        for (Map.Entry<String, CardinalDirection> entry : codeMap.entrySet()) {
            if (entry.getValue() == this) return entry.getKey();
        }

        return null;
    }

    public CardinalDirection turn(Command direction) {
        switch (this) {
            case NORTH:
                return direction == Command.LEFT ? WEST : EAST;
            case SOUTH:
                return direction == Command.LEFT ? EAST : WEST;
            case WEST:
                return direction == Command.LEFT ? SOUTH : NORTH;
            case EAST:
                return direction == Command.LEFT ? NORTH : SOUTH;
        }

        throw new IllegalStateException("Invalid Cardinal Direction");
    }
}
