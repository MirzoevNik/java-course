package com.mirzoevnik.univer.java.task2.service;

import com.mirzoevnik.univer.java.task2.domain.Car;
import com.mirzoevnik.univer.java.task2.domain.Garage;

/**
 * @author mirzoevnik
 */
public interface GarageService {

    /**
     * Добавить машину в гараж
     * @param garage гараж
     * @param car машина
     */
    void addCar(Garage garage, Car car);

    /**
     * Убрать машину из гаража
     * @param garage гараж
     * @param car машина
     */
    void removeCar(Garage garage, Car car);
}
