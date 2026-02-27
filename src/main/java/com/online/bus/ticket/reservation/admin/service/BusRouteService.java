package com.online.bus.ticket.reservation.admin.service;

import com.online.bus.ticket.reservation.admin.exception.BusRouteException;
import com.online.bus.ticket.reservation.admin.model.BusRoute;
import com.online.bus.ticket.reservation.admin.repository.BusRouteRepository;
import com.online.bus.ticket.reservation.admin.request.BusRouteRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BusRouteService {

    private final BusRouteRepository busRouteRepository;

    public BusRoute createBusRoute(BusRouteRequest busRouteRequest){
        BusRoute busRoute = new BusRoute();
        busRoute.setSource(busRouteRequest.getSource());
        busRoute.setDestination(busRouteRequest.getDestination());
        busRoute.setPrice(busRouteRequest.getPrice());
        busRoute.setTotalSeats(busRouteRequest.getTotalSeats());
        return busRouteRepository.save(busRoute);
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
        return busRouteRepository.save(busRoute);
    }

    public void deleteBusRoute(long busRouteNumber) {
        if (busRouteRepository.findById(busRouteNumber).isEmpty()) {
            throw new BusRouteException("Bus Route Number is not present and unable to delete");
        }
        busRouteRepository.deleteById(busRouteNumber);
    }

    public List<BusRoute> getBusRoutes() {
        return (List<BusRoute>) busRouteRepository.findAll();
    }
}
