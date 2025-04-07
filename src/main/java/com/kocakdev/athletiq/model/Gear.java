package com.kocakdev.athletiq.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gears")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Gear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String espId;
    private Boolean isActive = true;
    private String gearVersion;

    @OneToOne(mappedBy = "gear")
    private Athlete athlete;
    
}