package com.online.bus.ticket.reservation.admin.exception;

public class BusRouteException extends RuntimeException {

    private String errorMessage;

    public BusRouteException() {
        super();
    }

    public BusRouteException(String errorMessage) {
        super(errorMessage);
    }
}
