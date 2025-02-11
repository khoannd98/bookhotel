package com.example.bookhotel.service;

import com.example.bookhotel.controller.dto.HotelRequest;
import com.example.bookhotel.domain.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

    Page<HotelEntity> getAllHotels(String nameFilter, Pageable pageable);

    HotelEntity getHotelById(String id);

    HotelEntity createHotel(HotelRequest request);

    HotelEntity updateHotel(String hotelId, HotelRequest request);

    void deleteHotel(String hotelId);
}
