package com.online.bus.ticket.reservation.admin.controller;

import com.online.bus.ticket.reservation.admin.model.BusRoute;
import com.online.bus.ticket.reservation.admin.request.BusRouteRequest;
import com.online.bus.ticket.reservation.admin.service.BusRouteService;
import com.online.bus.ticket.reservation.admin.validator.BusRouteRequestValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin/busRoutes")
@AllArgsConstructor
public class BusRouteController {

    private final BusRouteService busRouteService;
    private final BusRouteRequestValidator busRouteRequestValidator;

    @PostMapping
    public BusRoute createBusRoute(@RequestBody BusRouteRequest busRouteRequest) {
        log.info("Inside BusRouteController createBusRoute Method");
        busRouteRequestValidator.validateBusRouteRequest(busRouteRequest);
        return busRouteService.createBusRoute(busRouteRequest);
    }

    @GetMapping("/{busRouteNumber}")
    public BusRoute getBusRoute(@PathVariable("busRouteNumber") long busRouteNumber) {
        log.info("Inside BusRouteController getBusRoute Method with busRouteNumber: {}", busRouteNumber);
        busRouteRequestValidator.validateBusRouteNumber(busRouteNumber);
        return busRouteService.getBusRoute(busRouteNumber);
    }

    @GetMapping()
    public List<BusRoute> getBusRoutes() {
        log.info("Inside BusRouteController getBusRoutes Method");
        return busRouteService.getBusRoutes();
    }

    @PutMapping("/{busRouteNumber}")
    public BusRoute editBusRoute(@RequestBody BusRouteRequest busRouteRequest, @PathVariable("busRouteNumber") long busRouteNumber) {
        log.info("Inside BusRouteController editBusRoute Method with busRouteNumber: {}", busRouteNumber);
        busRouteRequestValidator.validateBusRouteNumber(busRouteNumber);
        busRouteRequestValidator.validateBusRouteRequest(busRouteRequest);
        return busRouteService.editBusRoute(busRouteRequest, busRouteNumber);
    }

    @DeleteMapping("/{busRouteNumber}")
    public void deleteBusRoute(@PathVariable("busRouteNumber") long busRouteNumber) {
        log.info("Inside BusRouteController deleteBusRoute Method with busRouteNumber: {}", busRouteNumber);
        busRouteRequestValidator.validateBusRouteNumber(busRouteNumber);
        busRouteService.deleteBusRoute(busRouteNumber);
    }
}
