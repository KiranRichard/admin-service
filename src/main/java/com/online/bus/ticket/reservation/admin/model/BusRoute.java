package com.online.bus.ticket.reservation.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long busRouteNumber;
    private String source;
    private String destination;
    private double price;
    private int totalSeats;
    @CreationTimestamp
    private LocalDateTime createdDateTime;
    @UpdateTimestamp
    private LocalDateTime updatedDateTime;
}
