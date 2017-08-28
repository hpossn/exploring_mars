package com.possani.exploringmars.dto;

import com.possani.exploringmars.enums.Command;

import java.util.List;
import java.util.Objects;

/**
 * Created by hugo. All rights reserved.
 */
public class ProbeDto {
    String name;
    PositionDto initialPosition;
    List<Command> commands;

    public ProbeDto() {
    }

    public ProbeDto(String name, PositionDto initialPosition, List<Command> commands) {
        this.name = name;
        this.initialPosition = initialPosition;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PositionDto getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(PositionDto initialPosition) {
        this.initialPosition = initialPosition;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProbeDto probeDto = (ProbeDto) o;
        return Objects.equals(name, probeDto.name) &&
               Objects.equals(initialPosition, probeDto.initialPosition) &&
               Objects.equals(commands, probeDto.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, initialPosition, commands);
    }
}
