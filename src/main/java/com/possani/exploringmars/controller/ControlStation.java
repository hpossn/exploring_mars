package com.possani.exploringmars.controller;

import com.possani.exploringmars.enums.Command;
import com.possani.exploringmars.model.Field;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;

/**
 * Created by hugo. All rights reserved.
 */
public class ControlStation {
    private Field field;

    public ControlStation(Field field) {
        if(null == field) {
            throw new IllegalArgumentException("Field cannot be null");
        }

        this.field = field;
    }

    public Position controlProbe(Probe probe, Command[] commands) {
        if(null == probe) {
            throw new IllegalArgumentException("Probe cannot be null");
        }

        if(null == commands || commands.length == 0) {
            return probe.getCurrentPosition();
        }

        field.allocateNewProbe(probe);

        for(int i = 0; i < commands.length; i++) {
            Command command = commands[i];

            if(command.changesPosition()) {
                field.move(probe);
            } else {
                probe.turn(command);
            }
        }

        return probe.getCurrentPosition();
    }
}
