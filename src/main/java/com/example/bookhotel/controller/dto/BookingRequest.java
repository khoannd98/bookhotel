package com.example.bookhotel.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingRequest {
    @NotBlank
    private Instant checkInDate;

    @NotBlank
    private Instant checkOutDate;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String name;

    private int numberOfPeople;

    @NotBlank
    private String hotelId;
}
