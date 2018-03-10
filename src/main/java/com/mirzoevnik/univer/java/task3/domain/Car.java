package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @author mirzoevnik
 */
@Data
@ToString(exclude = {"id", "garage"})
public class Car {

    private Long id;

    private Model model;

    private String number;

    private Garage garage;
}
