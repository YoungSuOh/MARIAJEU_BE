package org.example.mariajeu.domain;

import jakarta.persistence.*;

public class Atmosphere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atmosphere_id", unique = true, nullable = false)
    private Long id; // 분위기 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private AtmosphereStatus atmosphereStatus;
}
