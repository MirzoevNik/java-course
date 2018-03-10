package com.mirzoevnik.univer.java.task3.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirzoevnik
 */
@Data
public class Garage {

    private Long id;

    private String name;

    private List<Car> cars = new ArrayList<>();
}
