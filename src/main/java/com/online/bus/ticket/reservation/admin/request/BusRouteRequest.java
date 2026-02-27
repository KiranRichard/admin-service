package com.online.bus.ticket.reservation.admin.request;

import lombok.Data;

@Data
public class BusRouteRequest {

    private String source;
    private String destination;
    private double price;
    private int totalSeats;
}
