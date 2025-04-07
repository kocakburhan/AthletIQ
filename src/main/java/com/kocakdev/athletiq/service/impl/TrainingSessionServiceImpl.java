package com.kocakdev.athletiq.service.impl;

import com.kocakdev.athletiq.dto.GpsDataPoint;
import com.kocakdev.athletiq.dto.TrainingDataCreateDTO;
import com.kocakdev.athletiq.model.Athlete;
import com.kocakdev.athletiq.model.SpeedTimePoint;
import com.kocakdev.athletiq.model.TrainingData;
import com.kocakdev.athletiq.model.TrainingSession;
import com.kocakdev.athletiq.service.TrainingSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Duration;

@Service
public class TrainingSessionServiceImpl implements TrainingSessionService {

    @Override
    public TrainingData createTrainingData(TrainingDataCreateDTO dto) {
        // DTO TrainingData'ya dönüştürülüyor
        TrainingData trainingData = TrainingData.builder()
                .espId(dto.getEspId())
                .date(LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .speedTimePoints(new ArrayList<>())
                .build();
        
        List<SpeedTimePoint> processedPoints = processGpsDataPoints(dto.getGpsData());
        trainingData.setSpeedTimePoints(processedPoints);
        
        return trainingData;
    }
    
    @Override
    public TrainingSession createTrainingSession(List<TrainingData> trainingDataList, Athlete athlete) {
        TrainingSession session = new TrainingSession();
        session.setTrainingDataList(trainingDataList);
        session.setAthlete(athlete);
        
        // Metrikleri hesapla
        calculateMetrics(session);
        
        return session;
    }
    
    /**
     * GpsDataPoint listesini alır, zamanları sıralar, eksik saniyeleri doldurur ve
     * SpeedTimePoint listesi olarak döndürür
     */
    private List<SpeedTimePoint> processGpsDataPoints(List<GpsDataPoint> gpsDataPoints) {
        // Zamanlarına göre sırala
        gpsDataPoints.sort(Comparator.comparing(dp -> 
            LocalTime.parse(dp.getTime(), DateTimeFormatter.ofPattern("HH:mm:ss"))));
        
        List<SpeedTimePoint> result = new ArrayList<>();
        LocalTime prevTime = null;
        double prevSpeed = 0;
        
        for (GpsDataPoint point : gpsDataPoints) {
            LocalTime currentTime = LocalTime.parse(point.getTime(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            double currentSpeed = point.getSpeed();
            
            // İlk veri noktası değilse, eksik saniyeleri doldur
            if (prevTime != null) {
                Duration duration = Duration.between(prevTime, currentTime);
                long secondsBetween = duration.getSeconds();
                
                // Eğer 1 saniyeden fazla fark varsa, eksik saniyeleri doldur
                if (secondsBetween > 1) {
                    for (int i = 1; i < secondsBetween; i++) {
                        LocalTime missingTime = prevTime.plusSeconds(i);
                        
                        // Eksik zaman için doğrusal interpolasyon yap
                        double interpolatedSpeed = prevSpeed + 
                            (currentSpeed - prevSpeed) * ((double) i / secondsBetween);
                        
                        result.add(SpeedTimePoint.builder()
                            .timeStamp(missingTime)
                            .speed(interpolatedSpeed)
                            .interpolated(true)
                            .build());
                    }
                }
            }
            
            result.add(SpeedTimePoint.builder()
                .timeStamp(currentTime)
                .speed(currentSpeed)
                .interpolated(false)
                .build());
            
            prevTime = currentTime;
            prevSpeed = currentSpeed;
        }
        
        return result;
    }
    
    // Metrikler burada hesaplanacak
    private void calculateMetrics(TrainingSession session) {
        
        // Maksimum hız hesaplama
        double maxSpeed = session.getTrainingDataList().stream()
            .flatMap(td -> td.getSpeedTimePoints().stream())
            .mapToDouble(SpeedTimePoint::getSpeed)
            .max()
            .orElse(0);
        
        session.setMaxSpeed(maxSpeed);
        
        // Diğer metrikler hesaplanacak.
    }
}