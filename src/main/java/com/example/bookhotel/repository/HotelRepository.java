package com.example.bookhotel.repository;

import com.example.bookhotel.domain.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    Page<HotelEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
