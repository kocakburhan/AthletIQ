package com.kocakdev.athletiq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GpsDataPoint {
    private Double speed;
    private String time; // "15:16:17" formatında gelecek service katmanında LocalTime'e parse edilecek.
}