package com.example.bookhotel.controller.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class BookingResponse {
    private UUID bookingId;
    private Instant checkInDate;
    private Instant checkOutDate;
    private Instant bookDate;
    private String phoneNumber;
    private String name;
    private int numberOfPeople;
    private UUID hotelId;
    private String hotelName;
}
