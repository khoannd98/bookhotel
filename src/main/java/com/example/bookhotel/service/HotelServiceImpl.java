package com.example.bookhotel.service;

import com.example.bookhotel.controller.dto.HotelRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.bookhotel.domain.HotelEntity;
import com.example.bookhotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;

    @Autowired
    public void setHotelRepository(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Page<HotelEntity> getAllHotels(String nameFilter, Pageable pageable) {
        return (nameFilter != null && !nameFilter.isEmpty()) ?
                hotelRepository.findByNameContainingIgnoreCase(nameFilter, pageable) :
                hotelRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "hotels", key = "#id")
    public HotelEntity getHotelById(String id) {
        return hotelRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found with ID: " + id));
    }

    @Override
    public HotelEntity createHotel(HotelRequest request) {
        HotelEntity hotel = HotelEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();
        return hotelRepository.save(hotel);
    }

    @Override
    public HotelEntity updateHotel(String hotelId, HotelRequest request) {
        HotelEntity hotel = getHotelById(hotelId);
        hotel.setName(request.getName());
        hotel.setAddress(request.getAddress());
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(String hotelId) {
        hotelRepository.deleteById(UUID.fromString(hotelId));
    }
}
