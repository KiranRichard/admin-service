package com.online.bus.ticket.reservation.admin.request;

import lombok.Data;

@Data
public class BusInventoryRequest {
    private long busRouteNumber;
    private int totalSeats;
    private int availableSeats;
    private double price;
}
