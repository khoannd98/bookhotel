package com.example.bookhotel.service;

import com.example.bookhotel.controller.dto.BookingRequest;
import com.example.bookhotel.domain.BookingEntity;
import com.example.bookhotel.domain.HotelEntity;
import com.example.bookhotel.repository.BookingRepository;
import com.example.bookhotel.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTests {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private HotelEntity hotelEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        hotelEntity = HotelEntity.builder()
                .id(UUID.randomUUID())
                .name("Test Hotel")
                .address("123 Test Street")
                .build();
    }

    @Test
    void createBookingTest() {
        BookingRequest request = BookingRequest.builder()
                .checkInDate(Instant.now())
                .checkOutDate(Instant.now().plusSeconds(86400))
                .phoneNumber("123-456-7890")
                .name("John Doe")
                .numberOfPeople(2)
                .hotelId(hotelEntity.getId().toString())
                .build();

        when(hotelRepository.findById(UUID.fromString(request.getHotelId()))).thenReturn(Optional.of(hotelEntity));
        when(bookingRepository.save(any(BookingEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BookingEntity booking = bookingService.createBooking(request);

        assertNotNull(booking);
        assertEquals("John Doe", booking.getName());
        verify(bookingRepository, times(1)).save(any(BookingEntity.class));
    }

    @Test
    void getBookingByIdTest() {
        UUID bookingId = UUID.randomUUID();
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setId(bookingId);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(bookingEntity));

        BookingEntity result = bookingService.getBookingById(bookingId.toString());

        assertNotNull(result);
        assertEquals(bookingId, result.getId());
    }

    @Test
    void cancelBookingTest() {
        UUID bookingId = UUID.randomUUID();
        doNothing().when(bookingRepository).deleteById(bookingId);

        bookingService.cancelBooking(bookingId.toString());

        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}
