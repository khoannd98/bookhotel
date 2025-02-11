package com.example.bookhotel.controller;

import com.example.bookhotel.controller.dto.BookingRequest;
import com.example.bookhotel.controller.dto.BookingResponse;
import com.example.bookhotel.mapper.BookingMapper;
import com.example.bookhotel.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@Slf4j
public class BookingController {
    private BookingService bookingService;

    private BookingMapper bookingMapper;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setBookingMapper(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        try {
            BookingResponse response = bookingMapper.toResponse(bookingService.createBooking(request));
            log.info("Create booking successfully");
            return response;
        } catch (Exception e) {
            log.error("Fail to create booking");
            throw e;
        }

    }

    @GetMapping
    public Page<BookingResponse> getAllBookings(
            @RequestParam(required = false) String hotelId,
            Pageable pageable
    ) {
        return bookingService.getAllBookings(hotelId, pageable)
                .map(bookingMapper::toResponse);
    }

    @PutMapping("/{id}")
    public BookingResponse updateBooking(@PathVariable String id, @RequestBody BookingRequest bookingRequest) {
        try {
            BookingResponse response = bookingMapper.toResponse(bookingService.updateBooking(id, bookingRequest));
            log.info("Update booking successfully");
            return response;
        } catch (Exception e) {
            log.info("Fail to update booking");
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable String id) {

        try {
            bookingService.cancelBooking(id);
            log.info("Cancel booking successfully");
        } catch (Exception e) {
            log.info("Fail to cancel booking");
            throw e;
        }
    }
}
