package com.example.bookhotel.service;

import com.example.bookhotel.controller.dto.BookingRequest;
import com.example.bookhotel.domain.BookingEntity;
import com.example.bookhotel.domain.HotelEntity;
import com.example.bookhotel.repository.BookingRepository;
import com.example.bookhotel.repository.HotelRepository;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private HotelRepository hotelRepository;

    private MeterRegistry meterRegistry;

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public Page<BookingEntity> getAllBookings(String hotelId, Pageable pageable) {
        return (hotelId != null) ?
                bookingRepository.findByHotelId(UUID.fromString(hotelId), pageable) :
                bookingRepository.findAll(pageable);
    }

    @Override
    public BookingEntity getBookingById(String id) {
        return bookingRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + id));
    }

    @Override
    public BookingEntity createBooking(BookingRequest request) {
        HotelEntity hotel = hotelRepository.findById(UUID.fromString(request.getHotelId()))
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        BookingEntity booking = BookingEntity.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .bookDate(Instant.now())
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .numberOfPeople(request.getNumberOfPeople())
                .hotel(hotel)
                .build();
        booking = bookingRepository.save(booking);
        meterRegistry.counter("booking.created").increment();
        return booking;
    }

    @Override
    public BookingEntity updateBooking(String bookingId, BookingRequest request) {
        BookingEntity booking = getBookingById(bookingId);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setName(request.getName());
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(String bookingId) {
        bookingRepository.deleteById(UUID.fromString(bookingId));
    }
}
