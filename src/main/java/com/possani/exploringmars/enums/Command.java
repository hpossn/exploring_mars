package com.possani.exploringmars.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hugo. All rights reserved.
 */
public enum Command {
    MOVE, RIGHT, LEFT;

    private static final Map<String, Command> codeMap = new HashMap<>();

    static {
        codeMap.put("M", MOVE);
        codeMap.put("R", RIGHT);
        codeMap.put("L", LEFT);
    }

    @JsonCreator
    public static Command forValue(String value) {
        if(null == value) {
            throw new IllegalArgumentException();
        }

        return codeMap.get(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        for (Map.Entry<String, Command> entry : codeMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }

        return null;
    }

    public boolean changesPosition() {
        switch (this) {
            case MOVE:
                return true;
            default:
                return false;
        }
    }
}
