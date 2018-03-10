package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author mirzoevnik
 */
@Data
@Entity
@Table(name = "car")
@ToString(exclude = {"id", "garage"})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private Model model;


    private String number;

    @ManyToOne
    @JoinColumn(name = "garage_id")
    private Garage garage;
}
