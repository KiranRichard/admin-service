package com.online.bus.ticket.reservation.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.bus.ticket.reservation.admin.exception.BusRouteException;
import com.online.bus.ticket.reservation.admin.kafka.ProducerService;
import com.online.bus.ticket.reservation.admin.model.BusRoute;
import com.online.bus.ticket.reservation.admin.repository.BusRouteRepository;
import com.online.bus.ticket.reservation.admin.request.BusInventoryRequest;
import com.online.bus.ticket.reservation.admin.request.BusRouteRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;
    @Autowired
    private ProducerService producerService;
//    @Autowired
//    private ObjectMapper objectMapper;

    public BusRoute createBusRoute(BusRouteRequest busRouteRequest){
        BusRoute busRoute = new BusRoute();
        busRoute.setSource(busRouteRequest.getSource());
        busRoute.setDestination(busRouteRequest.getDestination());
        busRoute.setPrice(busRouteRequest.getPrice());
        busRoute.setTotalSeats(busRouteRequest.getTotalSeats());

        BusRoute savedBusRoute = busRouteRepository.save(busRoute);
        BusInventoryRequest busInventoryRequest = new BusInventoryRequest();
        busInventoryRequest.setBusRouteNumber(savedBusRoute.getBusRouteNumber());
        busInventoryRequest.setTotalSeats(savedBusRoute.getTotalSeats());
        busInventoryRequest.setAvailableSeats(savedBusRoute.getTotalSeats());
        //Send kafka event for inventory service: busInventoryRequest


       // Map<String, Object> routeEvent = objectMapper.convertValue(saved, Map.class);
        //kafkaTemplate.send("bus.route.created", routeEvent);
        producerService.sendMessage(busInventoryRequest.toString());
        return savedBusRoute;
    }

    public BusRoute getBusRoute(long busRouteNumber) {
        BusRoute busRoute = busRouteRepository.findById(busRouteNumber).orElse(null);
        if (Objects.isNull(busRoute)){
            throw new BusRouteException("Bus Route not present");
        }
        return busRoute;
    }

    public BusRoute editBusRoute(BusRouteRequest busRouteRequest, long busRouteNumber) {
        BusRoute busRoute = busRouteRepository.findById(busRouteNumber).orElse(null);
        if (Objects.isNull(busRoute)){
            throw new BusRouteException("Bus Route Number is not present and unable to update");
        }
        busRoute.setSource(busRouteRequest.getSource());
        busRoute.setDestination(busRouteRequest.getDestination());
        busRoute.setPrice(busRouteRequest.getPrice());
        busRoute.setTotalSeats(busRouteRequest.getTotalSeats());

        BusRoute savedBusRoute = busRouteRepository.save(busRoute);
        BusInventoryRequest busInventoryRequest = new BusInventoryRequest();
        busInventoryRequest.setBusRouteNumber(savedBusRoute.getBusRouteNumber());
        busInventoryRequest.setTotalSeats(savedBusRoute.getTotalSeats());
        busInventoryRequest.setAvailableSeats(savedBusRoute.getTotalSeats());//need to check the logic here
        //Send kafka event for inventory service: busInventoryRequest
        return savedBusRoute;
    }

    public void deleteBusRoute(long busRouteNumber) {
        if (busRouteRepository.findById(busRouteNumber).isEmpty()) {
            throw new BusRouteException("Bus Route Number is not present and unable to delete");
        }
        busRouteRepository.deleteById(busRouteNumber);

        //busRouteNumber passed to delete
        //Send kafka event for inventory service: busInventoryRequest
    }

    public List<BusRoute> getBusRoutes() {
        return (List<BusRoute>) busRouteRepository.findAll();
    }
}
