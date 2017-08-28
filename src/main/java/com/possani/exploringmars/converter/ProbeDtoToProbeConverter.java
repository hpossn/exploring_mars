package com.possani.exploringmars.converter;

import com.possani.exploringmars.dto.PositionDto;
import com.possani.exploringmars.dto.ProbeDto;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by hugo. All rights reserved.
 */
public class ProbeDtoToProbeConverter implements Converter<ProbeDto, Probe> {
    @Override
    public Probe convert(ProbeDto source) {
        PositionDto positionDto = source.getInitialPosition();
        Position position = new Position(positionDto.getX(), positionDto.getY(), positionDto.getCardinalDirection());

        return new Probe(source.getName(), position, source.getCommands());
    }
}