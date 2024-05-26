package com.very.cursed.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "buses")
public class Buses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "number")
    private String  number;
    @Column(name = "driver_name")
    private String  driverName;
    @Column(name = "route_number")
    private String  routeNumber;
    @Column(name = "in_park")
    private boolean inpark;

}
