package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mirzoevnik
 */
@Data
@Entity
@Table(name = "garage")
@ToString(exclude = "id")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "garage")
    private List<Car> cars = new ArrayList<>();
}
