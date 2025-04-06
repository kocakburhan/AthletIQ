package com.kocakdev.athletiq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "clubs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Club {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String logoPicture;

    @OneToMany(mappedBy = "club", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Athlete> athletes = new ArrayList<>();

    @OneToMany(mappedBy = "club", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Trainer> trainers = new ArrayList<>();

}
