package com.example.bookhotel.mapper;

import com.example.bookhotel.controller.dto.BookingResponse;
import com.example.bookhotel.domain.BookingEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {
    public BookingResponse toResponse(BookingEntity booking) {
        return BookingResponse.builder()
                .bookingId(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .bookDate(booking.getBookDate())
                .phoneNumber(booking.getPhoneNumber())
                .name(booking.getName())
                .numberOfPeople(booking.getNumberOfPeople())
                .hotelId(booking.getHotel().getId())
                .hotelName(booking.getHotel().getName())
                .build();
    }

    public List<BookingResponse> toResponseList(List<BookingEntity> bookings) {
        return bookings.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
