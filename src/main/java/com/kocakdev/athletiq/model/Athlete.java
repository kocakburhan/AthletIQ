package com.kocakdev.athletiq.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kocakdev.athletiq.enums.Gender;

@Entity
@Table(name = "athletes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String password;
    private String profilePicture;
    private Double weight;
    private Double height;
    private Gender gender;

    @ManyToMany(mappedBy = "athletes")
    private Set<Trainer> trainers = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "gear_id")
    private Gear gear;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TrainingSession> trainingSessions = new ArrayList<>();
}