package com.example.bookhotel.controller;

import com.example.bookhotel.controller.dto.HotelRequest;
import com.example.bookhotel.domain.HotelEntity;
import com.example.bookhotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

    private HotelService hotelService;

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<Page<HotelEntity>> getAllHotels(
            @RequestParam(required = false) String nameFilter,
            Pageable pageable
    ) {
        return ResponseEntity.ok(hotelService.getAllHotels(nameFilter, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelEntity> getHotelById(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping
    public ResponseEntity<HotelEntity> createHotel(@Valid @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.createHotel(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelEntity> updateHotel(@PathVariable String id, @Valid @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.updateHotel(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
