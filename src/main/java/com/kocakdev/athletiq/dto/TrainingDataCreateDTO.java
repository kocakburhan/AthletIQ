package com.kocakdev.athletiq.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    // Örnek JSON verisi:
    // {
    //   "espId": "ESP32-001187468917246",
    //   "date": "03/02/2025",
    //   "gpsData": [
    //     { "speed": 35.6, "time": "15:16:17" },
    //     { "speed": 36.2, "time": "15:16:18" },
    //     { "speed": 34.8, "time": "15:16:19" }
    //   ]
    // }



// gps'ten gelen JSON verisi DTO olarak alınacak
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingDataCreateDTO {
    
    private String espId;
    private String date; // YYYY-MM-DD formatında gelecek service katmanında LocalDate'e parse edilecek.

    private List<GpsDataPoint> gpsData; //JSON'daki array




}
