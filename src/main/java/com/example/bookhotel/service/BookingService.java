package com.example.bookhotel.service;

import com.example.bookhotel.controller.dto.BookingRequest;
import com.example.bookhotel.domain.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    Page<BookingEntity> getAllBookings(String hotelId, Pageable pageable);
    BookingEntity getBookingById(String id);

    BookingEntity createBooking(BookingRequest request);

    BookingEntity updateBooking(String bookingId, BookingRequest request);

    void cancelBooking(String bookingId);
}
