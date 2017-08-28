package com.possani.exploringmars.controller;

import com.possani.exploringmars.business.ControlStation;
import com.possani.exploringmars.dto.FieldDto;
import com.possani.exploringmars.dto.PositionDto;
import com.possani.exploringmars.dto.ProbeDto;
import com.possani.exploringmars.model.Field;
import com.possani.exploringmars.model.Position;
import com.possani.exploringmars.model.Probe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo. All rights reserved.
 */

@RestController
public class ExplorationController {

    private final ConversionService conversionService;
    private final ControlStation controlStation;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Field field;

    @Autowired
    public ExplorationController(ControlStation controlStation, ConversionService conversionService) {
        this.controlStation = controlStation;
        assert controlStation != null;

        this.conversionService = conversionService;
        assert conversionService != null;
    }

    @RequestMapping(path = "/field", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createField(@RequestBody FieldDto fieldDto) {
        field = new Field(fieldDto.getUpperRightX(), fieldDto.getUpperRightY());

        logger.info("Field created successfully");
        return new ResponseEntity<>("Field created successfully", HttpStatus.OK);
    }

    @RequestMapping(path = "/field", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteField() {
        field = null;

        logger.info("Field deleted successfully");
        return new ResponseEntity<>("Field deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(path = "/probes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<PositionDto>> createProbes(@RequestBody List<ProbeDto> probesDtoList) {
        if (null == field) {
            logger.error("Field has not being created yet");
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        List<PositionDto> positionDtoList = new ArrayList<>();

        for (ProbeDto probeDto : probesDtoList) {
            Probe probe = conversionService.convert(probeDto, Probe.class);
            Position finalPosition = controlStation.controlProbe(probe, field);

            PositionDto positionDto =
                    new PositionDto(finalPosition.getX(), finalPosition.getY(), finalPosition.getCardinalDirection());

            String probeName = probe.getName();
            if (null == probeName || "".equals(probeName)) probeName = "Probe " + probesDtoList.indexOf(probeDto);

            positionDtoList.add(positionDto);
            logger.info(String.format("Probe %s has finished exploration", probeName));
        }

        return new ResponseEntity<>(positionDtoList, HttpStatus.OK);
    }
}