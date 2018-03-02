package com.mirzoevnik.univer.java.task2.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author mirzoevnik
 */
@Getter
@Setter
public class Garage {
    private Long id;
    private List<Car> cars;
}
