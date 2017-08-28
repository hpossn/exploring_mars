package com.possani.exploringmars.enums;

/**
 * Created by hugo. All rights reserved.
 */
public enum Command {
    MOVE, RIGHT, LEFT;

    public boolean changesPosition() {
        switch (this) {
            case MOVE:
                return true;
            default:
                return false;
        }
    }
}
