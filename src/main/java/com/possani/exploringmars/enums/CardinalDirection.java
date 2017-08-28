package com.possani.exploringmars.enums;

/**
 * Created by hugo. All rights reserved.
 */
public enum CardinalDirection {
    N, S, W, E;

    public CardinalDirection left() {
        switch (this) {
            case N:
                return W;
            case S:
                return E;
            case W:
                return S;
            case E:
                return N;
        }

        throw new IllegalStateException("Invalid Cardinal Direction");
    }

    public CardinalDirection right() {
        switch (this) {
            case N:
                return E;
            case S:
                return W;
            case W:
                return N;
            case E:
                return S;
        }

        throw new IllegalStateException("Invalid Cardinal Direction");
    }
}
