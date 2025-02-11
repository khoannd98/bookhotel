package com.example.bookhotel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "hotels")
@ToString(of = {"id", "name", "address"})
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity implements Serializable {
    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String address;

//    private List<RoomEntity> rooms;
}
