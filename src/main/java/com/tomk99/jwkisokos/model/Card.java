package com.tomk99.jwkisokos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Period period;
    private String responsible;
    private LocalDate lastPerformed;

    @Lob
    @Column(nullable = false)
    private byte[] fileData;

    @Column(nullable = false)
    private String fileType; // pl. "application/pdf" vagy "image/png"
}
