package com.example.bookhotel.repository;

import com.example.bookhotel.domain.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    Page<BookingEntity> findByHotelId(UUID hotelId, Pageable pageable);
}
