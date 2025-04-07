package com.kocakdev.athletiq.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "training_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSession {


    @OneToMany
    private List<TrainingData> trainingDataList;

    int trainingDataCount = trainingDataList.size();

    

    // Hesaplanacak metrikler
    private double totalDistance; // koşulan toplam mesafe
    private double maxSpeed; // maksimum hız
    private double maxDeceleration; // maksimum yavaşlama ivmesi
    private double minDeceleration; // minimum yavaşlama ivmesi
    private double avgDeceleration; // ortalama yavaşlama ivmesi
    private double maxAcceleration; // maksimum hızlanma ivmesi
    private double minAcceleration; // minimum hızlanma ivmesi
    private double avgAcceleration; // ortalama hızlanma ivmesi
    private double avgSpeed; // km/h
    private int sprintCount; // sayı
    private double sprintDistance; // metre
    private double highMetabolicLoadDistance; // metre



    @ManyToOne()
    private Athlete athlete;

}
