package com.online.bus.ticket.reservation.admin.validator;

import com.online.bus.ticket.reservation.admin.exception.RequiredFieldsMissingException;
import com.online.bus.ticket.reservation.admin.request.BusRouteRequest;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class BusRouteRequestValidator {

    public void validateBusRouteRequest(BusRouteRequest busRouteRequest) {
        if (Objects.isNull(busRouteRequest)) {
            log.info("[Error]: Invalid Create busRoute request: {}", busRouteRequest);
            throw new RequiredFieldsMissingException("Invalid Create busRoute request: {}"+ busRouteRequest);
        }
        if (StringUtils.isBlank(busRouteRequest.getSource())) {
            log.info("[Error]: Invalid source field in create busRoute request: {}", busRouteRequest);
            throw new RequiredFieldsMissingException("Invalid source field in create busRoute request: {}"+ busRouteRequest);
        }
        if (StringUtils.isBlank(busRouteRequest.getDestination())) {
            log.info("[Error]: Invalid destination field in create busRoute request: {}", busRouteRequest);
            throw new RequiredFieldsMissingException("Invalid destination field in create busRoute request: {}"+ busRouteRequest);
        }
        if (busRouteRequest.getPrice()<=0) {
            log.info("[Error]: Invalid price field in create busRoute request: {}", busRouteRequest);
            throw new RequiredFieldsMissingException("Invalid price field in create busRoute request: {}"+ busRouteRequest);
        }
        if (busRouteRequest.getTotalSeats()<=0) {
            log.info("[Error]: Invalid total seats field in create busRoute request: {}", busRouteRequest);
            throw new RequiredFieldsMissingException("Invalid total seats field in create busRoute request: {}"+ busRouteRequest);
        }
    }

    public void validateBusRouteNumber(long busRouteNumber) {
        if (busRouteNumber<=0) {
            log.info("[Error]: Invalid bus route number field in create busRoute request: {}", busRouteNumber);
            throw new RequiredFieldsMissingException("Invalid bus route number field in create busRoute request: {}"+ busRouteNumber);
        }
    }
}
