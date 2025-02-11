package com.example.bookhotel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "bookings")
@ToString(of = {"id", "checkInDate", "checkOutDate"})
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//    private CustomerEntity customer;

    private Instant checkInDate;

    private Instant checkOutDate;

    private Instant bookDate;

    private String phoneNumber;

    private String name;

    private int numberOfPeople;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;
}
