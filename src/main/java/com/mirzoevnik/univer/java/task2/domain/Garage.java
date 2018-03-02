package com.mirzoevnik.univer.java.task2.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mirzoevnik
 */
@Getter
@Setter
@ToString(exclude = "id")
public class Garage {
    private Long id;
    private List<Car> cars = new ArrayList<>();
}
