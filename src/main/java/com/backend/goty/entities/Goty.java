package com.backend.goty.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "goty")
@Data
public class Goty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "votos")
    private Integer votos;

    @Column(name = "url")
    private String url;
}
