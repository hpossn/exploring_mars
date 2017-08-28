package com.possani.exploringmars.model;

import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by hugo. All rights reserved.
 */
@Component
public class Field {

    private final Probe[][] fieldMatrix;
    private final int upperRightX;
    private final int upperRightY;

    public Field(int upperRightX, int upperRightY) {
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
        fieldMatrix = new Probe[this.upperRightX + 1][this.upperRightY + 1];
    }

    public boolean allocateNewProbe(Probe probe) {
        Position position = probe.getInitialPosition();
        if (isFree(position)) {
            int x = position.getX();
            int y = position.getY();

            fieldMatrix[x][y] = probe;
            return true;
        }

        return false;
    }

    public boolean move(Probe probe) {
        if (null == probe) throw new IllegalArgumentException("Probe cannot be null");

        Position destination = probe.nextPosition();
        Position origin = probe.getCurrentPosition();

        if (isFree(destination)) {
            Optional<Probe> optionalProbe = probeAtPosition(probe.getCurrentPosition());

            if (!optionalProbe.isPresent() || !optionalProbe.get().equals(probe)) return false;

            int originX = origin.getX();
            int originY = origin.getY();

            int destinationX = destination.getX();
            int destinationY = destination.getY();

            fieldMatrix[originX][originY] = null;
            fieldMatrix[destinationX][destinationY] = probe;

            probe.move(destination);
            return true;
        }

        return false;
    }

    public boolean isFree(Position position) {
        if (null == position) throw new IllegalArgumentException("Position cannot be null");

        return isFree(position.getX(), position.getY());
    }

    public boolean isFree(int x, int y) {
        if(x < 0 || y < 0) return false;
        if (x > upperRightX || y > upperRightY) return false;
        return fieldMatrix[x][y] == null;
    }

    private Optional<Probe> probeAtPosition(Position position) {
        if (null == position) throw new IllegalArgumentException("Position cannot be null");

        int x = position.getX();
        int y = position.getY();

        if (x > upperRightX || y > upperRightY) return Optional.empty();

        return Optional.ofNullable(fieldMatrix[x][y]);
    }

    public int getUpperRightX() {
        return upperRightX;
    }

    public int getUpperRightY() {
        return upperRightY;
    }
}
