package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;

/**
 * @author mirzoevnik
 */
@Data
public class Car {

    private Long id;

    private Model model;

    private String number;

    private Garage garage;
}
