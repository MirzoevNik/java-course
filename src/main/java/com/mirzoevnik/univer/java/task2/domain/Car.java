package com.mirzoevnik.univer.java.task2.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mirzoevnik
 */
@Getter
@Setter
@ToString(exclude = "id")
public class Car {
    private Long id;
    private String mark;
    private String model;
    private String number;
}
