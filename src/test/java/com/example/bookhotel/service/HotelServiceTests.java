package com.example.bookhotel.service;

import com.example.bookhotel.controller.dto.HotelRequest;
import com.example.bookhotel.domain.HotelEntity;
import com.example.bookhotel.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HotelServiceTests {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

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
    void createHotelTest() {
        HotelRequest request = HotelRequest.builder()
                .name("New Hotel")
                .address("456 New Street")
                .build();

        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(hotelEntity);

        HotelEntity createdHotel = hotelService.createHotel(request);

        assertNotNull(createdHotel);
        assertEquals("Test Hotel", createdHotel.getName());
        assertEquals("123 Test Street", createdHotel.getAddress());
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
    }

    @Test
    void getHotelByIdTest() {
        UUID hotelId = UUID.randomUUID();
        HotelEntity hotelEntity = new HotelEntity(hotelId, "Test Hotel", "123 Test Street");

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotelEntity));

        HotelEntity result = hotelService.getHotelById(hotelId.toString());

        assertNotNull(result);
        assertEquals(hotelId, result.getId());
    }

    @Test
    void getHotelByIdThrowsExceptionTest() {
        UUID hotelId = UUID.randomUUID();

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hotelService.getHotelById(hotelId.toString());
        });

        assertEquals("Hotel not found with ID: " + hotelId, exception.getMessage());
    }

    @Test
    void getAllHotelsTest() {
        List<HotelEntity> hotelList = List.of(
                new HotelEntity(UUID.randomUUID(), "Hotel One", "Address One"),
                new HotelEntity(UUID.randomUUID(), "Hotel Two", "Address Two")
        );

        Page<HotelEntity> hotelPage = new PageImpl<>(hotelList);
        when(hotelRepository.findAll(PageRequest.of(0, 10))).thenReturn(hotelPage);

        Page<HotelEntity> result = hotelService.getAllHotels(null, PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    void updateHotelTest() {
        UUID hotelId = UUID.randomUUID();
        HotelRequest request = HotelRequest.builder()
                .name("Updated Hotel")
                .address("789 Updated Street")
                .build();

        HotelEntity hotelEntity = new HotelEntity(hotelId, "Old Hotel", "123 Old Street");
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(hotelEntity));
        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(hotelEntity);

        HotelEntity updatedHotel = hotelService.updateHotel(hotelId.toString(), request);

        assertNotNull(updatedHotel);
        assertEquals("Updated Hotel", updatedHotel.getName());
        assertEquals("789 Updated Street", updatedHotel.getAddress());
    }

    @Test
    void deleteHotelTest() {
        UUID hotelId = UUID.randomUUID();

        doNothing().when(hotelRepository).deleteById(hotelId);

        hotelService.deleteHotel(hotelId.toString());

        verify(hotelRepository, times(1)).deleteById(hotelId);
    }
}
