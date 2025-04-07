package com.kocakdev.athletiq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "speed_time_points")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeedTimePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime timeStamp;
    private Double speed;
    
    @Column(name = "is_interpolated")
    private boolean interpolated; // saniye atlamaları sistem hız değeri hesaplayıp atadıysa interpolated=true olacak, eğer gerçek GPS verisi ise interpolated=false olacak.
}